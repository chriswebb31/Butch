package com.butch.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.BasicEnemy;
import com.butch.game.gameobjects.Bullet;
import com.butch.game.gameobjects.Player;
import com.butch.game.gameobjects.abstractinterface.Enemy;

import java.util.ArrayList;

public class GameScreen implements Screen {
    /*
        CLASS : GAMESCREEN

        as stated in higher classes in the hierarchy, ButchGame is much the window
        this game screen is a 'scene' within the window. Used for creating levels.

     */
    public ButchGame game; //reference to libgdx main game class
    public SpriteBatch batch; //sprite renderer
    public SpriteBatch hudBatch;
    private FitViewport gameViewPort; //viewports define how the camera will render to the screen. FIT | STRETCH | FILL
    private OrthographicCamera camera; //camera for height position of render
    private float distanceDivisor = 1.5f;
    private Player player;
    private BasicEnemy enemy;
    private BasicEnemy enemy2;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer; //tiled map renderer
    public ArrayList<Rectangle> mapColliders;
    public ArrayList<BasicEnemy> enemies;
    private ShapeRenderer shapeRenderer;
    private Music music;

    public GameScreen(ButchGame game, FitViewport gameViewPort) {
        enemies = new ArrayList<BasicEnemy>();
        this.game = game;
        this.gameViewPort = gameViewPort;
        player = new Player(this); //create new player for screen
        player.setPosition(new Vector2(6960.0f,8630.0f)); //initilize player position
//        player.setPosition(new Vector2(0,0)); //initilize player position

        enemy = new BasicEnemy();
        enemy.position = new Vector2(6960.0f,8630.0f);
        enemy2 = new BasicEnemy();
        enemy2.position = new Vector2(6960.0f,8630.0f);
        enemies.add(enemy);
        enemies.add(enemy2);
        batch = new SpriteBatch(); //create new sprite renderer
        hudBatch = new SpriteBatch();

        //Setup camera and viewport
        camera = new OrthographicCamera(); //create new camera
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 40); //set to middle of screen default pos
        camera.zoom = 1.5f; //set camera height
        tiledMap = ButchGame.assets.get(ButchGame.assets.tilemap1); //get tiled map for this screen
        orthogonalTiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 10); //render tilemap with scalar of ten
        shapeRenderer = new ShapeRenderer();

        MapObjects mapObjects = tiledMap.getLayers().get(3).getObjects();
        mapColliders = new ArrayList<Rectangle>();
        for(RectangleMapObject rectangleMapObject : mapObjects.getByType(RectangleMapObject.class)){
            float newX = rectangleMapObject.getRectangle().x * 10;
            float newY = rectangleMapObject.getRectangle().y * 10;
            float newWidth = rectangleMapObject.getRectangle().width * 10;
            float newHeight = rectangleMapObject.getRectangle().height * 10;
            Rectangle collider = new Rectangle(newX, newY, newWidth, newHeight);

            mapColliders.add(collider);
            System.out.println("created collider: "+ "x:"+collider.x+" y:"+ collider.y+" width:"+collider.width+" height:" +collider.height);
        }

        orthogonalTiledMapRenderer.setView(camera); //render using camera perspective

        gameViewPort.setCamera(camera); //set main camera
        gameViewPort.apply(); //apply changes to vp settings
        music = ButchGame.assets.get(ButchGame.assets.townTheme, Music.class);
        music.setVolume(0.3f);
        music.setLooping(true);
        music.play();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        updateCameraPosition(); //update where camera is
        Gdx.gl.glClearColor(240 / 255f, 220 / 255f, 130 / 255f, 1); //set clear colour of screen (sandy)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // This cryptic line clears the screen.

        camera.update(); //update camera view based on position
        orthogonalTiledMapRenderer.setView(camera); //update view of renderers to camera
        orthogonalTiledMapRenderer.render();//draw tilemap before sprites to save correct z-index of sprites

        //batch is sprite renderer, other sprites go here
        player.update(delta); //player has a sprite so update then draw
        batch.setProjectionMatrix(camera.combined);//update view of renderers to camera
        batch.begin();// begin rendering sprites
        Enemy.update(batch);
        player.sprite.draw(batch);//after updateing position and the plauer settings, render sprite to screen
        player.activeWeapon.gunSprite.draw(batch); //draw weapon of player
        Bullet.update(batch);
        batch.end(); //no more sprites to render

        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        shapeRenderer.setColor(Color.CYAN);

        for (Rectangle collider: mapColliders) {
            shapeRenderer.setColor(Color.ORANGE);
            shapeRenderer.rect(collider.x,collider.y,collider.width,collider.height);
            shapeRenderer.setColor(Color.RED);
            for (int i = 0; i < Bullet.bullets.size(); i++) {
                shapeRenderer.rect(Bullet.bullets.get(i).collider.x, Bullet.bullets.get(i).collider.y, Bullet.bullets.get(i).collider.width, Bullet.bullets.get(i).collider.height);
                if(Bullet.bullets.get(i).collider.overlaps(collider)){
                    Bullet.bullets.get(i).active = false;
                }
            }
        }

        shapeRenderer.setColor(Color.SCARLET);
        for (BasicEnemy e:enemies) {
            shapeRenderer.rect(e.collider.x, e.collider.y, e.collider.width, e.collider.height);
        }

        shapeRenderer.setColor(Color.LIME);
        shapeRenderer.rect(player.playerCollider.x, player.playerCollider.y, player.playerCollider.width, player.playerCollider.height);

        try{
            if(player.intersector != null){
                shapeRenderer.setColor(Color.PINK);
                shapeRenderer.rect(player.intersector.x, player.intersector.y, player.intersector.width, player.intersector.height);
            }
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        shapeRenderer.end();
    }

    private void updateCameraPosition() {
        Vector2 mousePosition = new Vector2(ButchGame.mousePosition().x, ButchGame.mousePosition().y); //get mouse pos
        float newX = mousePosition.x + (player.getPosition().x - mousePosition.x) / distanceDivisor; //gets position  divirsor percentage) along vector instead of midpoint
        float newY = mousePosition.y + (player.getPosition().y - mousePosition.y) / distanceDivisor; //gets position  divirsor percentage) along vector instad of midpoint
        camera.position.set(new Vector3(newX, newY, camera.position.z));
    }

    @Override
    public void resize(int width, int height) { //if window is resized
        gameViewPort.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, camera.position.z);
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

    public ArrayList<Rectangle> getColliders() {
        return mapColliders;
    }
}