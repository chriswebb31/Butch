package com.butch.game.screens;

import java.lang.reflect.Method;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.audio.Music;
import com.butch.game.ButchGame;
import com.butch.game.components.Collider;
import com.butch.game.gameobjects.Player;
import com.butch.game.gameobjects.abstractinterface.Bullet;

public class GameScreen implements Screen {
    public ButchGame game;
    private SpriteBatch batch;
    private ShapeRenderer colliderRenderer;
    private FitViewport gameViewPort;
    private OrthographicCamera camera;
    private float distanceDivisor =  1.5f;
    private Player player;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private Music music;

    public GameScreen(ButchGame game, FitViewport gameViewPort){
        this.game = game;
        this.gameViewPort = gameViewPort;
        player = new Player(this);
        player.setPosition(new Vector2(0,0));
        batch = new SpriteBatch();
        colliderRenderer = new ShapeRenderer();
        //Setup camera and viewport
        camera = new OrthographicCamera();
        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2, 40);
        camera.zoom = 1.5f;
        tiledMap = ButchGame.assets.get(ButchGame.assets.tilemap1);
        orthogonalTiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 10);
        orthogonalTiledMapRenderer.setView(camera);

        gameViewPort.setCamera(camera);
        gameViewPort.apply();
        music = ButchGame.assets.get(ButchGame.assets.townTheme, Music.class);
        music.setVolume(0.5f);
        music.setLooping(true);
        music.play();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        updateCameraPosition();
        Gdx.gl.glClearColor(	240/255f, 220/255f, 130/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // This cryptic line clears the screen.

        camera.update();
        orthogonalTiledMapRenderer.setView(camera);
        orthogonalTiledMapRenderer.render();

        //batch is sprite renderer, other sprites go here
        player.update(); //player has a sprite so update then draw
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        player.sprite.draw(batch);
        player.activeWeapon.sprite.draw(batch);
        for (Bullet bullet : player.playerBullets) {
            try{
                bullet.sprite.draw(batch);
            } catch (NullPointerException e){
                System.out.println(e);
            }
        }
        batch.end(); //no more sprites to render
    }

    private void updateCameraPosition(){
        Vector2 mousePosition = new Vector2(ButchGame.mousePosition().x, ButchGame.mousePosition().y);
        float newX = mousePosition.x + (player.getPosition().x - mousePosition.x) / distanceDivisor;
        float newY = mousePosition.y + (player.getPosition().y - mousePosition.y) / distanceDivisor;
        camera.position.set(new Vector3(newX, newY, camera.position.z));
    }

    @Override
    public void resize(int width, int height) {
        gameViewPort.update(width,height);
        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,camera.position.z);
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void mapColliderMaker(){
        MapLayer collisionLayer = this.tiledMap.getLayers().get("Layer 3");
        MapObjects mapObjects = collisionLayer.getObjects();

        for(RectangleMapObject rectangleObject : mapObjects.getByType(RectangleMapObject.class)){
            Rectangle rectangle = rectangleObject.getRectangle();
            for(Collider col : player.playerColliders){
                if(rectangle.overlaps(col.getBoundingRectangle())){
                    System.out.println("OH NO COLLISION?!");
                }
            }
        }
    }
}
