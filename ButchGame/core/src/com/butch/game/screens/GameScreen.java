package com.butch.game.screens;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;
import com.butch.game.gamemanagers.ItemManager;
import com.butch.game.gamemanagers.RenderableManager;
import com.butch.game.gameobjects.Breakables.Barrel;
import com.butch.game.gameobjects.HUDObjects.HealthBar;
import com.butch.game.gameobjects.HUDObjects.Hud;
import com.butch.game.gameobjects.Items.CoinItem;
import com.butch.game.gameobjects.Items.ColtItem;
import com.butch.game.gameobjects.Items.RifleAmmo;
import com.butch.game.gameobjects.abstractinterface.ItemPickup;
import com.butch.game.gameobjects.abstractinterface.Renderable;
import com.butch.game.gameobjects.spriterenderables.Enemy;
import com.butch.game.gameobjects.spriterenderables.Player;
import com.butch.game.gameobjects.spriterenderables.NPC;

import java.util.ArrayList;
public class GameScreen implements Screen {
    /*
        CLASS : GAMESCREEN

        as stated in higher classes in the hierarchy, ButchGame is much the window
        this game screen is a 'scene' within the window. Used for creating levels.

     */
    public ArrayList<ItemPickup> itemPickups;
    public ArrayList<Enemy> enemies;
    public ArrayList<NPC> npcs;
    public ButchGame game; //reference to libgdx main game class

    public SpriteBatch batch; //sprite renderer

    private FitViewport gameViewPort; //viewports define how the camera will render to the screen. FIT | STRETCH | FILL
    private OrthographicCamera camera; //camera for height position of render
    private float distanceDivisor = 1.2f;

    private Player player;
    public Vector2 spawnPoint;


    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer; //tiled map renderer
    public ArrayList<Rectangle> mapColliders;

    private ShapeRenderer shapeRenderer;
    private Music music;
    // private Stage stage;
    /////////initializing hud vars////////////////////
    private Hud hud;
    private boolean outOfBullets;
    HealthBar enemyHb;
    Label healthLabel;
    Stage stage;
    float enemyX = 8000, enemyY = 7500;
    float npcX = 6000, npcY = 8000;
    /////////////////////////////////////////////////////
    public GameScreen(ButchGame game, FitViewport gameViewPort) {
        this.game = game;
        this.gameViewPort = gameViewPort;
        this.batch = new SpriteBatch(); //create new sprite renderer

        //Setup camera and viewport
        camera = new OrthographicCamera(); //create new camera
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 40); //set to middle of screen default pos
        camera.zoom = 2.5f; //set camera height


        tiledMap = ButchGame.assets.get(ButchGame.assets.tilemap1); //get tiled map for this screen
        orthogonalTiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 10); //render tilemap with scalar of ten
        orthogonalTiledMapRenderer.setView(camera); //render using camera perspective
//        stage = new Stage();
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
//
            ButchGame.GSM.playerObject = this.player;
        }

        ButchGame.renderableManager.reset();
        RenderableManager.mapColliders = mapColliders;
        ButchGame.itemManager = new ItemManager();
        // barrel = new Barrel(6960,8630);
        this.spawnPoint = new Vector2(6960, 8630);
        shapeRenderer = new ShapeRenderer();
<<<<<<< HEAD
        player = new Player(spawnPoint, mapColliders); //create new player for screen
=======
        player = new Player(new Vector2(7500.0f,4340.0f), mapColliders); //create new player for screen
>>>>>>> master
        player.activeForRender = true;
        this.itemPickups = new ArrayList<ItemPickup>();
        this.itemPickups.add(new ColtItem(new Vector2(6960,8630)));
        this.itemPickups.add(new RifleAmmo(new Vector2(7070, 8650)));
        this.itemPickups.add(new CoinItem(new Vector2(6000, 8000)));

        this.enemies = new ArrayList<Enemy>();

        this.enemies.add(new Enemy(new Vector2(enemyX, enemyY)));

        this.npcs = new ArrayList<NPC>();

        this.npcs.add(new NPC(new Vector2(npcX, npcY)));

        gameViewPort.setCamera(camera); //set main camera
        gameViewPort.apply(); //apply changes to vp settings
        music = ButchGame.assets.get(ButchGame.assets.townTheme, Music.class);
        music.setVolume(0.3f);
        music.setLooping(true);
        music.play();
        //////////////////////hud ////////////////////
        hud = new Hud(game.batch, player);
        outOfBullets = false;
        //(int)enemies.get(0).getHealth()
        enemyHb = new HealthBar(500,20);
        stage= new Stage();
    }

    @Override
    public void show() {
//        Gdx.input.setInputProcessor(stage);
//        createHUD();
    }

    @Override
    public void render(float delta) {
        updateCameraPosition(); //update where camera is
        Gdx.gl.glClearColor(240 / 255f, 220 / 255f, 130 / 255f, 1); //set clear colour of screen (sandy)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // This cryptic line clears the screen.

        camera.update(); //update camera view based on position
        ButchGame.renderableManager.update(delta);//update all objects on screen
        orthogonalTiledMapRenderer.setView(camera); //update view of renderers to camera
        orthogonalTiledMapRenderer.render();//draw tilemap before sprites to save correct z-index of sprites

        batch.setProjectionMatrix(camera.combined);//update view of renderers to camera
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        batch.begin();
        ButchGame.renderableManager.render(batch); //render all objects on screen
        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.FIREBRICK);
        for (Renderable renderable: RenderableManager.renderableObjects) {
            try{
                if((renderable.TAG == "item" || renderable.TAG == "enemy") && renderable.activeForRender){
                    if((renderable.TAG == "item")) {
                        ItemPickup item = (ItemPickup) renderable;
                        shapeRenderer.circle(item.collectionRange.x, item.collectionRange.y, item.collectionRange.radius);
                    } else if(renderable.TAG == "enemy" && renderable.activeCollision){
                        Enemy enemy = (Enemy) renderable;
                        shapeRenderer.circle(enemy.activateRange.x, enemy.activateRange.y, enemy.activateRange.radius);
                    }
                }
            } catch (NullPointerException e){
                e.printStackTrace();
            }
            try{
                if(renderable.activeCollision){
                    shapeRenderer.rect(renderable.getCollider().x, renderable.getCollider().y, renderable.getCollider().width, renderable.getCollider().height);
                } else if(renderable.TAG == "item" || renderable.TAG == "player"){
                    shapeRenderer.rect(renderable.getCollider().x, renderable.getCollider().y, renderable.getCollider().width, renderable.getCollider().height);
                }
            } catch (NullPointerException e){
                e.printStackTrace();
            }
        }

        shapeRenderer.end();
//////////////////////hud drawing and actions////////////////////////////////////
        hud.coinLabel.setText(String.format("Coins: " + player.coin ));
        if(player.getHealth() <0 && outOfBullets == false){
            Label healthLabel = new Label(String.format("You are Dead"), new Label.LabelStyle(new BitmapFont(), Color.RED));
            healthLabel.setFontScale(3.0f);
            hud.table.removeActor(hud.hb);
            hud.table.removeActor(hud.levelLabel);
            hud.table.reset();
            hud.table.center();
            hud.table.add(healthLabel).expandY().expandX().center();

            outOfBullets = true;
        }
        else{
            hud.hb.setWidth(player.getHealth());
        }

        hud.stage.draw();
        ///////////////////////////////////////////////////////////
        //stage.act(delta);
        //enemyHb.setPosition((int)enemies.get(0).getPosition().x,(int)enemies.get(0).getPosition().y);
        //stage.addActor(enemyHb);
        //stage.draw();
    }

    private void updateCameraPosition() {
        Vector2 mousePosition = new Vector2(ButchGame.mousePosition().x, ButchGame.mousePosition().y); //get mouse pos
        float newX = mousePosition.x + (player.getPosition().x - mousePosition.x) / distanceDivisor; //gets position  divirsor percentage) along vector instead of midpoint
        float newY = mousePosition.y + (player.getPosition().y - mousePosition.y) / distanceDivisor; //gets position  divirsor percentage) along vector instad of midpoint
        camera.position.slerp(new Vector3(newX, newY, camera.position.z), 0.1f);
    }

    @Override
    public void resize(int width, int height) { //if window is resized
        gameViewPort.update(width, height);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, camera.position.z);
        camera.update();
    }
    //    public void update(float delta){
//        stage.act(delta);
//    }
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
//    @Override
//    public void create(){
//
//    }

    public ArrayList<Rectangle> getColliders() {
        return mapColliders;
    }

    private void createHUD(){

    }
}