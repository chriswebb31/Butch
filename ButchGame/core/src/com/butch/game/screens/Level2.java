package com.butch.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;
import com.butch.game.gamemanagers.ItemManager;
import com.butch.game.gamemanagers.RenderableManager;
import com.butch.game.gameobjects.HUDObjects.HealthBar;
import com.butch.game.gameobjects.HUDObjects.Hud;
import com.butch.game.gameobjects.Items.*;
import com.butch.game.gameobjects.abstractinterface.ItemPickup;
import com.butch.game.gameobjects.abstractinterface.Renderable;
import com.butch.game.gameobjects.spriterenderables.Enemy;
import com.butch.game.gameobjects.spriterenderables.NPC;
import com.butch.game.gameobjects.spriterenderables.Player;

import java.util.ArrayList;

public class Level2 implements Screen {
    public int levelNumber;
    private SpriteBatch batch;
    private Sprite cursor;
    public ArrayList<ItemPickup> itemPickups;
    public ArrayList<Enemy> enemies;
    public ArrayList<NPC> NPCs;
    public ButchGame game;

    private FitViewport gameViewPort; //viewports define how the camera will render to the screen. FIT | STRETCH | FILL
    private OrthographicCamera camera; //camera for height position of render
    private float distanceDivisor = 1.2f;

    public Vector2 spawnPoint;
    public Rectangle endPoint;

    public Player player;

    public TiledMap tiledMap;

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

    public Level2(ButchGame game, FitViewport gameViewPort){
        this.game = game;
        this.gameViewPort = gameViewPort;
        this.batch = new SpriteBatch();
        this.cursor = new Sprite(ButchGame.assets.get(ButchGame.assets.cursor, Texture.class));
        this.cursor.setScale(10);
        this.shapeRenderer = new ShapeRenderer();
        this.camera = new OrthographicCamera();
        this.camera.zoom = 2.5f;
        tiledMap = ButchGame.assets.get(ButchGame.assets.route1); //get tiled map for this screen
        orthogonalTiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 10); //render tilemap with scalar of ten
        this.itemPickups = new ArrayList<ItemPickup>();
        this.enemies = new ArrayList<Enemy>();
        this.NPCs = new ArrayList<NPC>();
        this.mapColliders = new ArrayList<Rectangle>();
        this.spawnPoint = new Vector2().setZero();

        ButchGame.renderableManager.reset();
        RenderableManager.mapColliders = mapColliders;
        ButchGame.itemManager = new ItemManager();

        setupLevel();

        player = new Player(spawnPoint, mapColliders);
        player.activeForRender = true;

        camera.position.set(new Vector3(player.getPosition().x, player.getPosition().y, 40));
        orthogonalTiledMapRenderer.setView(camera); //render using camera perspective

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

    }

    @Override
    public void render(float delta){
        updateCameraPosition();

        if(player.getCollider().overlaps(endPoint)){
            ButchGame.GSM.playerObject = player;
        }

        Gdx.gl.glClearColor(205 / 255f, 105 / 255f, 105 / 255f, 1); //set clear colour of screen (sandy)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // This cryptic line clears the screen.


        camera.update();
        ButchGame.renderableManager.update(delta);

        orthogonalTiledMapRenderer.setView(camera); //update view of renderers to camera
        orthogonalTiledMapRenderer.render();//draw t

        batch.setProjectionMatrix(camera.combined);//update view of renderers to camera
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        cursor.setPosition(ButchGame.mousePosition().x, ButchGame.mousePosition().y);
        batch.begin();
        ButchGame.renderableManager.render(batch); //render all objects on screen
        cursor.draw(batch);
        batch.end();

//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        shapeRenderer.setColor(Color.FIREBRICK);
//
//        for (Renderable renderable: RenderableManager.renderableObjects) {
//            try{
//                if((renderable.TAG == "item" || renderable.TAG == "enemy") && renderable.activeForRender){
//                    if((renderable.TAG == "item")) {
//                        ItemPickup item = (ItemPickup) renderable;
//                        shapeRenderer.circle(item.collectionRange.x, item.collectionRange.y, item.collectionRange.radius);
//                    } else if(renderable.TAG == "enemy" && renderable.activeCollision){
//                        Enemy enemy = (Enemy) renderable;
//                        shapeRenderer.circle(enemy.activateRange.x, enemy.activateRange.y, enemy.activateRange.radius);
//                    }
//                }
//            } catch (NullPointerException e){
//                e.printStackTrace();
//            }
//            try{
//                if(renderable.activeCollision){
//                    shapeRenderer.rect(renderable.getCollider().x, renderable.getCollider().y, renderable.getCollider().width, renderable.getCollider().height);
//                } else if(renderable.TAG == "item" || renderable.TAG == "player"){
//                    shapeRenderer.rect(renderable.getCollider().x, renderable.getCollider().y, renderable.getCollider().width, renderable.getCollider().height);
//                }
//            } catch (NullPointerException e){
//                e.printStackTrace();
//            }
//        }
//
//        shapeRenderer.end();

        hud.coinLabel.setText(String.format("Coins: " + player.coin ));
        int thisReserve;
        if(player.getActiveWeapon().gunType == 0){
            thisReserve = player.pistolAmmo;
        } else if(player.getActiveWeapon().gunType == 1){
            thisReserve = player.rifleAmmo;
        }else{
            thisReserve = player.shotgunAmmo;
        }
        hud.weaponLabel.setText(String.format(hud.player.getActiveWeapon().gunName + " " + player.getActiveWeapon().clip+"/"+thisReserve));

        if(player.getHealth() <0 && outOfBullets == false){
            Label healthLabel = new Label(String.format("Ag... I don't feel so good"), new Label.LabelStyle(new BitmapFont(), Color.RED));
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
    }

    private void setupLevel() {
        MapObjects collisionLayer = tiledMap.getLayers().get(3).getObjects();
        MapObjects pointsLayer = tiledMap.getLayers().get(4).getObjects();
        MapObjects itemLayer = tiledMap.getLayers().get(5).getObjects();
        MapObjects enemyLayer = tiledMap.getLayers().get(6).getObjects();
        MapObjects npcLayer = tiledMap.getLayers().get(7).getObjects();

        for(RectangleMapObject colliderRectangle : collisionLayer.getByType(RectangleMapObject.class)){
            float newX = colliderRectangle.getRectangle().x * 10;
            float newY = colliderRectangle.getRectangle().y * 10;
            float newWidth = colliderRectangle.getRectangle().width * 10;
            float newHeight = colliderRectangle.getRectangle().height * 10;
            Rectangle collider = new Rectangle(newX, newY, newWidth, newHeight);
            mapColliders.add(collider);
        }
        // ADD ALL COLLIDERS TO GAME

        for(RectangleMapObject point : pointsLayer.getByType(RectangleMapObject.class)){
            int pointID = Integer.parseInt(point.getName());
            if(pointID == 0){
                spawnPoint = new Vector2(point.getRectangle().x * 10, point.getRectangle().getY() * 10);
                player = new Player(spawnPoint, mapColliders);
            }else{
                endPoint = new Rectangle(point.getRectangle().x * 10, point.getRectangle().y * 10, point.getRectangle().width * 10, point.getRectangle().height * 10);
            }
        }
        //SET SPAWN AND ENDS OF LEVELS

        for(RectangleMapObject item : itemLayer.getByType(RectangleMapObject.class)){
            int itemId = Integer.parseInt(item.getName());
            switch (itemId){
                case 0:
                    itemPickups.add(new PistolAmmo(new Vector2(item.getRectangle().x * 10, item.getRectangle().y * 10)));
                    break;
                case 1:
                    itemPickups.add(new RifleAmmo(new Vector2(item.getRectangle().x * 10, item.getRectangle().y * 10)));
                    break;
                case 2:
                    itemPickups.add(new ShotgunAmmo(new Vector2(item.getRectangle().x * 10, item.getRectangle().y * 10)));
                    break;
                case 3:
                    itemPickups.add(new CoinItem(new Vector2(item.getRectangle().x * 10, item.getRectangle().y * 10)));
                    break;
                case 4:
                    itemPickups.add(new WhiskyItem(new Vector2(item.getRectangle().x * 10, item.getRectangle().y * 10)));
                    break;
                case 10:
                    itemPickups.add(new ColtItem(new Vector2(item.getRectangle().x * 10, item.getRectangle().y * 10)));
                    break;
                case 11:
                    itemPickups.add(new MachineGunItem(new Vector2(item.getRectangle().x * 10, item.getRectangle().y * 10)));
                    break;
            }
        }
        //SET ITEMS AND POSITIONING ITEMS

        for(PolygonMapObject enemy : enemyLayer.getByType(PolygonMapObject.class)){
            Enemy newEnemy = new Enemy(new Vector2(enemy.getPolygon().getTransformedVertices()[2] * 10, enemy.getPolygon().getTransformedVertices()[3] * 10), 1);
            for (int i = 2; i < enemy.getPolygon().getTransformedVertices().length; i+=2){
                Vector2 newRoutePosition = new Vector2(enemy.getPolygon().getTransformedVertices()[i] * 10, enemy.getPolygon().getTransformedVertices()[i+1] * 10);
                newEnemy.route.add(newRoutePosition);
                newEnemy.targetPos = newEnemy.route.get(0);
                System.out.println("NEW ROUTE POS: " + newRoutePosition);

            }
            enemies.add(newEnemy);
        }

        //SET ENEMIES AND POSITIONS

        for(RectangleMapObject npc : npcLayer.getByType(RectangleMapObject.class)){
            NPCs.add(new NPC(new Vector2(npc.getRectangle().x * 10, npc.getRectangle().y * 10), new TextureAtlas(ButchGame.assets.npc2Idle)));
        }
        //SET NPCS AND POSITIONS
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
}
