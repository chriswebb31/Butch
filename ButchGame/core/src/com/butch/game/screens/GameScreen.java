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
import com.butch.game.gamemanagers.ItemManager;
import com.butch.game.gamemanagers.RenderableManager;
import com.butch.game.gameobjects.Breakables.Barrel;
import com.butch.game.gameobjects.Items.ColtItem;
import com.butch.game.gameobjects.abstractinterface.ItemPickup;
import com.butch.game.gameobjects.abstractinterface.Renderable;
import com.butch.game.gameobjects.spriterenderables.Player;

import java.util.ArrayList;

public class GameScreen implements Screen {
    /*
        CLASS : GAMESCREEN

        as stated in higher classes in the hierarchy, ButchGame is much the window
        this game screen is a 'scene' within the window. Used for creating levels.

     */
    public ArrayList<ItemPickup> itemPickups;
    public ButchGame game; //reference to libgdx main game class
    public SpriteBatch batch; //sprite renderer
    private FitViewport gameViewPort; //viewports define how the camera will render to the screen. FIT | STRETCH | FILL
    private OrthographicCamera camera; //camera for height position of render
    private float distanceDivisor = 1.5f;
    private Player player;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer; //tiled map renderer
    public ArrayList<Rectangle> mapColliders;
    public Barrel barrel;
    private ShapeRenderer shapeRenderer;
    private Music music;

    public GameScreen(ButchGame game, FitViewport gameViewPort) {
        this.game = game;
        this.gameViewPort = gameViewPort;
        this.batch = new SpriteBatch(); //create new sprite renderer

        //Setup camera and viewport
        camera = new OrthographicCamera(); //create new camera
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 40); //set to middle of screen default pos
        camera.zoom = 1.5f; //set camera height


        tiledMap = ButchGame.assets.get(ButchGame.assets.tilemap1); //get tiled map for this screen
        orthogonalTiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 10); //render tilemap with scalar of ten
        orthogonalTiledMapRenderer.setView(camera); //render using camera perspective

        //Set up static colliders
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

        ButchGame.renderableManager.reset();
        ButchGame.renderableManager.mapColliders = mapColliders;
        ButchGame.itemManager = new ItemManager();
//        barrel= new Barrel(6960,8630);

        shapeRenderer = new ShapeRenderer();

        player = new Player(new Vector2(6960.0f,8630.0f), mapColliders); //create new player for screen
        this.itemPickups = new ArrayList<ItemPickup>();
        this.itemPickups.add(new ColtItem(new Vector2(6960,8630)));
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
        ButchGame.renderableManager.update(delta);
        orthogonalTiledMapRenderer.setView(camera); //update view of renderers to camera
        orthogonalTiledMapRenderer.render();//draw tilemap before sprites to save correct z-index of sprites

        batch.setProjectionMatrix(camera.combined);//update view of renderers to camera
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        batch.begin();
        ButchGame.renderableManager.render(batch);
        batch.end();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.FIREBRICK);
        for (Renderable renderable: RenderableManager.renderableObjects) {
            try{
                shapeRenderer.rect(renderable.getCollider().x, renderable.getCollider().y, renderable.getCollider().width, renderable.getCollider().height);
            } catch (NullPointerException e){
                e.printStackTrace();
            }
        }
//        if (Gdx.input.isButtonPressed(Input.Keys.F2)== false){
// Gdx.app.exit();
//        }
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