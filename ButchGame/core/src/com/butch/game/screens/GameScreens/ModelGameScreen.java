package com.butch.game.screens.GameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
import com.butch.game.gameobjects.HUDObjects.Hud;
import com.butch.game.gameobjects.Items.*;
import com.butch.game.gameobjects.abstractinterface.Gun;
import com.butch.game.gameobjects.abstractinterface.ItemPickup;
import com.butch.game.gameobjects.spriterenderables.Animal;
import com.butch.game.gameobjects.spriterenderables.Enemy;
import com.butch.game.gameobjects.spriterenderables.NPC;
import com.butch.game.gameobjects.spriterenderables.Player;
import com.butch.game.gameobjects.weapons.GunCreator;
import com.butch.game.screens.TransitionScreen;

import java.util.ArrayList;

public abstract class ModelGameScreen implements Screen {
    public int levelNumber;
    private int playerLevel;
     SpriteBatch batch;
    Pixmap cursor;
    public ArrayList<ItemPickup> itemPickups;
    public ArrayList<Enemy> enemies;
    public ArrayList<NPC> NPCs;
    ArrayList<Animal> animals;
    public ButchGame game;
    ArrayList<Gun> weaponCache;
    FitViewport gameViewPort; //viewports define how the camera will render to the screen. FIT | STRETCH | FILL
    OrthographicCamera camera; //camera for height position of render
    float distanceDivisor = 1.2f;
    public Vector2 spawnPoint;
    public Rectangle endPoint;
    public Player player;
    public TiledMap tiledMap;
    OrthogonalTiledMapRenderer orthogonalTiledMapRenderer; //tiled map renderer
    public ArrayList<Rectangle> mapColliders;
    ShapeRenderer shapeRenderer;
    Music music;
    // private Stage stage;
    /////////initializing hud vars////////////////////
    Hud hud;
    boolean outOfBullets;
    Stage stage;
    float enemyX = 8000, enemyY = 7500;
    float npcX = 6000, npcY = 8000;
    Sprite healthBarBG;
    Sprite healthBarFG;
    final short buffer = 120;
    static TransitionScreen transitionScreen;
    public ModelGameScreen(int levelNumber, ButchGame game, FitViewport gameViewPort,TiledMap tiledMap, int playerLevel){
        this.levelNumber = levelNumber;
        this.game = game;
        this.gameViewPort = gameViewPort;
        this.batch = new SpriteBatch();
//        this.cursor = new Sprite(ButchGame.assets.get(ButchGame.assets.cursor, Texture.class));
//        this.cursor.setScale(10);
        this.shapeRenderer = new ShapeRenderer();
        this.camera = new OrthographicCamera();
        this.camera.zoom = 2.5f;
        this.tiledMap = tiledMap;
        //tiledMap = ButchGame.assets.get(ButchGame.assets.tilemap1); //get tiled map for this screen
        orthogonalTiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 10); //render tilemap with scalar of ten
        this.itemPickups = new ArrayList<ItemPickup>();
        this.enemies = new ArrayList<Enemy>();
        this.NPCs = new ArrayList<NPC>();
        this.animals = new ArrayList<Animal>();
        this.mapColliders = new ArrayList<Rectangle>();
        this.spawnPoint = new Vector2().setZero();
        this.playerLevel = playerLevel;

        ButchGame.renderableManager.reset();
        RenderableManager.mapColliders = mapColliders;
        ButchGame.itemManager = new ItemManager();

        this.weaponCache = new ArrayList<Gun>();
        this.weaponCache.add(new GunCreator("Revolver"));
        this.weaponCache.add(new GunCreator("MachineGun"));
        this.weaponCache.add(new GunCreator("Musket"));
        this.weaponCache.add(new GunCreator("Shotgun"));
        setupLevel();

//        player = new Player(spawnPoint, mapColliders, weaponCache);
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

        this.cursor = ButchGame.assets.get(ButchGame.assets.cursor, Pixmap.class);
        Gdx.graphics.setCursor(Gdx.graphics.newCursor(this.cursor, 0, 0));
        //(int)enemies.get(0).getHealth()
        // enemyHb = new HealthBar(500,20);
        stage= new Stage();
//

        healthBarBG = new Sprite(ButchGame.assets.get(ButchGame.assets.enemyHBBG, Texture.class));
        healthBarFG = new Sprite (ButchGame.assets.get(ButchGame.assets.enemyHBFG, Texture.class));
    }

    private void setupLevel() {
        MapObjects collisionLayer = tiledMap.getLayers().get(4).getObjects();
        MapObjects pointsLayer = tiledMap.getLayers().get(5).getObjects();
        MapObjects itemLayer = tiledMap.getLayers().get(6).getObjects();
        MapObjects enemyLayer = tiledMap.getLayers().get(7).getObjects();
        MapObjects npcLayer = tiledMap.getLayers().get(8).getObjects();
        MapObjects animalLayer = tiledMap.getLayers().get(9).getObjects();

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
                player = new Player(spawnPoint, mapColliders, weaponCache, playerLevel);
                player.setCam(camera);
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
                case 7:
                    itemPickups.add(new LevelUpItem(new Vector2(item.getRectangle().x * 10, item.getRectangle().y * 10)));
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
            Enemy newEnemy = new Enemy(new Vector2(enemy.getPolygon().getTransformedVertices()[2] * 10, enemy.getPolygon().getTransformedVertices()[3] * 10), Integer.parseInt(enemy.getName()));
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
            NPCs.add(new NPC(new Vector2(npc.getRectangle().x * 10, npc.getRectangle().y * 10), "Dan"));
        }
        //SET NPCS AND POSITIONS
        for(RectangleMapObject animal : animalLayer.getByType(RectangleMapObject.class)) {
            int animalID = Integer.parseInt(animal.getName());
            animals.add(new Animal(new Vector2(animal.getRectangle().x * 10, animal.getRectangle().y * 10), animalID));
        }
    }

    @Override
    public void show() {
       // Gdx.input.setInputProcessor(stage);
      //  transitionScreen.transitionIn(stage);
    }

    @Override
    public void render(float delta) {
//        updateCameraPosition();


        Gdx.gl.glClearColor(205 / 255f, 105 / 255f, 105 / 255f, 1); //set clear colour of screen (sandy)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // This cryptic line clears the screen.


        camera.update();
        ButchGame.renderableManager.update(delta);

        orthogonalTiledMapRenderer.setView(camera); //update view of renderers to camera
        orthogonalTiledMapRenderer.render();//draw t

        batch.setProjectionMatrix(camera.combined);//update view of renderers to camera
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        //cursor.setPosition(ButchGame.mousePosition().x, ButchGame.mousePosition().y);
        batch.begin();
        ButchGame.renderableManager.render(batch); //render all objects on screen

        //cursor.draw(batch);
        batch.end();
        camera.update();
        caseBreak();
        renderHUD();
        renderEnemyHB();
    }

    public void renderEnemyHB(){
        for(int i = 0; i<=enemies.size()-1;i++){
            healthBarBG.setX(enemies.get(i).getPosition().x);
            healthBarBG.setY(enemies.get(i).getPosition().y + enemies.get(i).getSprite().getHeight() + buffer);
            healthBarFG.setX(healthBarBG.getX());
            healthBarFG.setY(healthBarBG.getY());
            if(enemies.get(i).getHealth() <= 0) {

            }
            else{
                batch.begin();
                batch.draw(healthBarBG, healthBarBG.getX(), healthBarBG.getY(),100,20);
                batch.draw(healthBarFG, healthBarFG.getX(), healthBarFG.getY(),enemies.get(i).getHealth(),20);
                batch.end();
            }
        }
    }

    void updateCameraPosition() {
        Vector2 mousePosition = new Vector2(ButchGame.mousePosition().x, ButchGame.mousePosition().y); //get mouse pos
        float newX = mousePosition.x + (player.getPosition().x - mousePosition.x) / distanceDivisor; //gets position  divirsor percentage) along vector instead of midpoint
        float newY = mousePosition.y + (player.getPosition().y - mousePosition.y) / distanceDivisor; //gets position  divirsor percentage) along vector instad of midpoint
        camera.position.slerp(new Vector3(newX, newY, camera.position.z), 0.1f);
    }

    void renderHUD(){
        int thisReserve;
        if(player.getActiveWeapon().gunType == 0){
            thisReserve = player.pistolAmmo;
        } else if(player.getActiveWeapon().gunType == 1){
            thisReserve = player.rifleAmmo;
        }else{
            thisReserve = player.shotgunAmmo;
        }
        hud.weaponLabel.setText(String.format(hud.player.getActiveWeapon().gunName + " " + player.getActiveWeapon().clip+"/"+thisReserve));

        for(NPC npc : NPCs) {
            if(npc.getInteractActive()) {
                hud.setNpcTextVisibility(true);
                hud.setNpcText(String.format(npc.getNpcText()));
//                hud.getNpcText().setPosition(npc.getPosition().x, npc.getPosition().y);

                System.out.println("Oh Hi Mark");
            } else if (!npc.getInteractActive() && npc.getInteractDeactivate()) {
                hud.setNpcTextVisibility(false);
//                hud.setNpcText(String.format(""));
                System.out.println("Oh Bye Mark");
            }
        }

        if(player.getHealth() <=0 && outOfBullets == false){
            Label healthLabel = new Label(String.format("Ag... I don't feel so good"), new Label.LabelStyle(new BitmapFont(), Color.RED));
            healthLabel.setFontScale(3.0f);
            //hud.table.removeActor(hud.hb);
//            hud.table.removeActor(hud.levelLabel);
            hud.table.reset();
            hud.table.center();
            hud.table.add(healthLabel).expandY().expandX().center();

            outOfBullets = true;
        }
        else{
            //hud.hb.setWidth(player.getHealth());
        }
        hud.render(player.getPlayerHealthPercent());
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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

    public void caseBreak(){

        switch(player.coin / 10) {
            case 0:
                hud.setCoinCounterTen(ButchGame.assets.get(ButchGame.assets.coinCounter0, Texture.class));
                break;
            case 1:
                hud.setCoinCounterTen(ButchGame.assets.get(ButchGame.assets.coinCounter1, Texture.class));
                break;
            case 2:
                hud.setCoinCounterTen(ButchGame.assets.get(ButchGame.assets.coinCounter2, Texture.class));
                break;
            case 3:
                hud.setCoinCounterTen(ButchGame.assets.get(ButchGame.assets.coinCounter3, Texture.class));
                break;
            case 4:
                hud.setCoinCounterTen(ButchGame.assets.get(ButchGame.assets.coinCounter4, Texture.class));
                break;
            case 5:
                hud.setCoinCounterTen(ButchGame.assets.get(ButchGame.assets.coinCounter5, Texture.class));
                break;
            case 6:
                hud.setCoinCounterTen(ButchGame.assets.get(ButchGame.assets.coinCounter6, Texture.class));
                break;
            case 7:
                hud.setCoinCounterTen(ButchGame.assets.get(ButchGame.assets.coinCounter7, Texture.class));
                break;
            case 8:
                hud.setCoinCounterTen(ButchGame.assets.get(ButchGame.assets.coinCounter8, Texture.class));
                break;
            case 9:
                hud.setCoinCounterTen(ButchGame.assets.get(ButchGame.assets.coinCounter9, Texture.class));
                break;
        }

        switch(player.coin % 10) {
            case 0:
                hud.setCoinCounterOne(ButchGame.assets.get(ButchGame.assets.coinCounter0, Texture.class));
                break;
            case 1:
                hud.setCoinCounterOne(ButchGame.assets.get(ButchGame.assets.coinCounter1, Texture.class));
                break;
            case 2:
                hud.setCoinCounterOne(ButchGame.assets.get(ButchGame.assets.coinCounter2, Texture.class));
                break;
            case 3:
                hud.setCoinCounterOne(ButchGame.assets.get(ButchGame.assets.coinCounter3, Texture.class));
                break;
            case 4:
                hud.setCoinCounterOne(ButchGame.assets.get(ButchGame.assets.coinCounter4, Texture.class));
                break;
            case 5:
                hud.setCoinCounterOne(ButchGame.assets.get(ButchGame.assets.coinCounter5, Texture.class));
                break;
            case 6:
                hud.setCoinCounterOne(ButchGame.assets.get(ButchGame.assets.coinCounter6, Texture.class));
                break;
            case 7:
                hud.setCoinCounterOne(ButchGame.assets.get(ButchGame.assets.coinCounter7, Texture.class));
                break;
            case 8:
                hud.setCoinCounterOne(ButchGame.assets.get(ButchGame.assets.coinCounter8, Texture.class));
                break;
            case 9:
                hud.setCoinCounterOne(ButchGame.assets.get(ButchGame.assets.coinCounter9, Texture.class));
                break;
        }

        switch(player.getActiveWeapon().id) {
            case 10:
                switch(player.getActiveWeapon().clip) {
                    case 6:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.revolverAmmoBar6, Texture.class));
                        break;
                    case 5:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.revolverAmmoBar5, Texture.class));
                        break;
                    case 4:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.revolverAmmoBar4, Texture.class));
                        break;
                    case 3:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.revolverAmmoBar3, Texture.class));
                        break;
                    case 2:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.revolverAmmoBar2, Texture.class));
                        break;
                    case 1:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.revolverAmmoBar1, Texture.class));
                        break;
                    case 0:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.revolverAmmoBar0, Texture.class));
                        break;
                }
                break;
            case 11:
                switch(player.getActiveWeapon().clip) {
                    case 24:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.machineGunAmmoBar24, Texture.class));
                        break;
                    case 23:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.machineGunAmmoBar23, Texture.class));
                        break;
                    case 22:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.machineGunAmmoBar22, Texture.class));
                        break;
                    case 21:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.machineGunAmmoBar21, Texture.class));
                        break;
                    case 20:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.machineGunAmmoBar20, Texture.class));
                        break;
                    case 19:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.machineGunAmmoBar19, Texture.class));
                        break;
                    case 18:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.machineGunAmmoBar18, Texture.class));
                        break;
                    case 17:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.machineGunAmmoBar17, Texture.class));
                        break;
                    case 16:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.machineGunAmmoBar16, Texture.class));
                        break;
                    case 15:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.machineGunAmmoBar15, Texture.class));
                        break;
                    case 14:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.machineGunAmmoBar14, Texture.class));
                        break;
                    case 13:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.machineGunAmmoBar13, Texture.class));
                        break;
                    case 12:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.machineGunAmmoBar12, Texture.class));
                        break;
                    case 11:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.machineGunAmmoBar11, Texture.class));
                        break;
                    case 10:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.machineGunAmmoBar10, Texture.class));
                        break;
                    case 9:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.machineGunAmmoBar9, Texture.class));
                        break;
                    case 8:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.machineGunAmmoBar8, Texture.class));
                        break;
                    case 7:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.machineGunAmmoBar7, Texture.class));
                        break;
                    case 6:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.machineGunAmmoBar6, Texture.class));
                        break;
                    case 5:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.machineGunAmmoBar5, Texture.class));
                        break;
                    case 4:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.machineGunAmmoBar4, Texture.class));
                        break;
                    case 3:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.machineGunAmmoBar3, Texture.class));
                        break;
                    case 2:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.machineGunAmmoBar2, Texture.class));
                        break;
                    case 1:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.machineGunAmmoBar1, Texture.class));
                        break;
                    case 0:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.machineGunAmmoBar0, Texture.class));
                        break;
                }
                break;
            case 12:
                switch(player.getActiveWeapon().clip) {
                    case 2:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.shotgunAmmoBar2, Texture.class));
                        break;
                    case 1:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.shotgunAmmoBar1, Texture.class));
                        break;
                    case 0:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.shotgunAmmoBar0, Texture.class));
                        break;
                }
                break;
            case 13:
                switch(player.getActiveWeapon().clip) {
                    case 1:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.musketAmmoBar1, Texture.class));
                        break;
                    case 0:
                        hud.setAmmoCount(ButchGame.assets.get(ButchGame.assets.musketAmmoBar0, Texture.class));
                        break;
                }
                break;
        }

    }

    @Override
    public void dispose() {

    }

}
