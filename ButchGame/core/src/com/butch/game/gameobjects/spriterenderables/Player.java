package com.butch.game.gameobjects.spriterenderables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.gamemanagers.RenderableManager;
import com.butch.game.gameobjects.Items.PistolAmmo;
import com.butch.game.gameobjects.Items.RifleAmmo;
import com.butch.game.gameobjects.Items.ShotgunAmmo;
import com.butch.game.gameobjects.abstractinterface.Gun;
import com.butch.game.gameobjects.abstractinterface.Item;
import com.butch.game.gameobjects.abstractinterface.ItemPickup;
import com.butch.game.gameobjects.abstractinterface.Renderable;
import com.butch.game.gameobjects.weapons.MachineGun;
import com.butch.game.screens.GameScreen;

import java.util.ArrayList;

public class Player extends Renderable {
    public enum State { UP, DOWN, LEFT, RIGHT, IDLE, DEAD };
    public State currentState;
    public State previousState;
    float xAxis, yAxis, speed = 0;
    private Sprite sprite;

    private Animation<TextureRegion> butchWalkingLeft;
    private Animation<TextureRegion> butchWalkingRight;
    private Animation<TextureRegion> butchWalkingUp;
    private Animation<TextureRegion> butchWalkingDown;
    private Animation<TextureRegion> butchIdle;
    private Animation<TextureRegion> butchDying;

    private TextureAtlas butchLeftAtlas;
    private TextureAtlas butchRightAtlas;
    private TextureAtlas butchUpAtlas;
    private TextureAtlas butchDownAtlas;
    private TextureAtlas butchIdleAtlas;
    private TextureAtlas butchDyingAtlas;

    private boolean movingRight;
    private boolean movingLeft;
    private boolean movingUp;
    private boolean movingDown;
    private boolean butchDead;
    private boolean isButchIdle = true;

    public int rifleAmmo = 10;
    public int pistolAmmo = 10;
    public int shotgunAmmo = 10;
    public float health;
    public int coin;

    private static ArrayList<Gun> gunInventory;
    private static ArrayList<ItemPickup> itemInventory;
    private static ArrayList<ItemPickup> itemCollection; //items in range if collection

    private Gun activeGun;
    private int gunInventoryIteration = 0;

    private ArrayList<Rectangle> mapColliders;
    private boolean canMove;
    private Vector2 velocity;

    private Vector2 leftHandIKoffset = new Vector2().setZero();
    private Vector2 rightHandIKoffset = new Vector2().setZero();

    private Rectangle intersector;

    private float stateTimer;

    public Player(Vector2 startPosition, ArrayList<Rectangle>mapStaticColliders){
        this.setPosition(startPosition);
        this.mapColliders = mapStaticColliders;
        this.TAG = "player";
        sprite = new Sprite(ButchGame.assets.get(ButchGame.assets.cowboySprite, Texture.class));
        this.setSprite(sprite);
        this.getSprite().setScale(10);
        this.velocity = new Vector2().setZero();
        this.canMove = true;
        this.speed = 10;

        this.gunInventory = new ArrayList<Gun>();
        this.itemInventory = new ArrayList<ItemPickup>();
        this.itemCollection = new ArrayList<ItemPickup>();
        this.gunInventory.add(new MachineGun());
//        this.gunInventory.add(new Colt());
        this.activeGun = this.gunInventory.get(0);

        for (Gun gun:gunInventory) {
            if(gun != activeGun){
                gun.activeForRender = false;
            }
        }

        this.rightHandIKoffset = new Vector2(-50, -20); //how far from sprite center is the right hand
        this.leftHandIKoffset = new Vector2(50, -20); //how far away from sprite center is the left hand

        this.setCollider(new Rectangle(this.getPosition().x, this.getPosition().y, this.getSprite().getBoundingRectangle().width/2.5f, this.getSprite().getBoundingRectangle().height/1.5f));

        butchIdleAtlas = new TextureAtlas(ButchGame.assets.butchIdleAnim);
        butchUpAtlas = new TextureAtlas(ButchGame.assets.butchWalkingBack);
        butchDyingAtlas = new TextureAtlas(ButchGame.assets.butchDying);
        butchLeftAtlas = new TextureAtlas(ButchGame.assets.butchWalkingLeft);
        butchRightAtlas = new TextureAtlas(ButchGame.assets.butchWalkingRight);

        butchIdle = new Animation<TextureRegion>(0.3f, butchIdleAtlas.getRegions());
        butchDying = new Animation<TextureRegion>(0.3f, butchDyingAtlas.getRegions());
        butchWalkingUp = new Animation<TextureRegion>(0.1f, butchUpAtlas.getRegions());
        butchWalkingLeft = new Animation<TextureRegion>(0.1f, butchLeftAtlas.getRegions());
        butchWalkingRight = new Animation<TextureRegion>(0.1f, butchRightAtlas.getRegions());

        currentState = State.IDLE;
        previousState = State.IDLE;
        stateTimer = 0;
        butchDead = false;
        ////////////////////////////
        health = 100;
    }
    private void inputHandler() { // handle inputs
        if (!Gdx.input.isKeyPressed(Input.Keys.D)) {
            xAxis = 0;
            movingRight = false;
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.W)) {
            yAxis = 0;
            movingUp = false;
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.S)) {
            yAxis = 0;
            movingDown = false;
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.A)) {
            xAxis = 0;
            movingLeft = false;
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.A) || !Gdx.input.isKeyPressed(Input.Keys.S) || !Gdx.input.isKeyPressed(Input.Keys.W) || !Gdx.input.isKeyPressed(Input.Keys.D)) {
            isButchIdle = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            yAxis = 1;
            isButchIdle = false;
            movingUp = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            yAxis = -1;
            isButchIdle = false;
            movingDown = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            xAxis = -1;
            isButchIdle = false;
            movingLeft = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            xAxis = 1;
            isButchIdle = false;
            movingRight = true;
        }
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            try{
                activeGun.Shoot();
            } catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            try{
                if(this.gunInventory.size() -1 > this.gunInventoryIteration){
                    this.activeGun.activeForRender = false;
                    this.gunInventoryIteration++;
                    this.activeGun = gunInventory.get(this.gunInventoryIteration);
                } else{
                    this.activeGun.activeForRender = false;
                    this.gunInventoryIteration = 0;
                    this.activeGun = gunInventory.get(this.gunInventoryIteration);
                }
            } catch (NullPointerException e){
                e.printStackTrace();
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.E)){
            for (Renderable renderable:RenderableManager.renderableObjects) {
                if(renderable.TAG == "item" && renderable.activeForRender){
                    ItemPickup itemPickup = (ItemPickup) renderable;
                    intersector = new Rectangle();
                    if(Intersector.overlaps(itemPickup.collectionRange, this.getCollider())){
                        itemCollection.add(itemPickup);
                        itemPickup.activeForRender = false;
                    }
                }
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
    }

    private void movementHandler() {
        if (canMove) { //if player isnt blocked
            Vector2 velocityNew = new Vector2();
            if (yAxis > 0) {
                velocityNew.y = 1;
            } else if (yAxis < 0) {
                velocityNew.y = -1;
            } else {
                velocityNew.y = 0;
            }
            if (xAxis > 0) {
                velocityNew.x = 1;
            } else if (xAxis < 0) {
                velocityNew.x = -1;
            } else {
                velocityNew.x = 0;
            }
            velocity.lerp(velocityNew,0.85f);
            this.getCollider().setCenter(this.getPosition().x + velocity.x * speed, this.getPosition().y + velocity.y * speed);

            for (Rectangle staticCollider: RenderableManager.mapColliders) {
                if(this.getCollider().overlaps(staticCollider)){
                    intersector = new Rectangle();
                    Intersector.intersectRectangles(this.getCollider(), staticCollider, intersector);
                    if(intersector.x > this.getCollider().x){
                        //Intersects with right side
                        velocity.x = clamp(velocity.x, -1, 0.001f);
                    }
                    else if(intersector.x + intersector.width < this.getCollider().x + this.getCollider().width) {
                        //Intersects with left side
                        velocity.x = clamp(velocity.x, 0.001f, 1);
                    }

                    if(intersector.y > this.getCollider().y){
                        //Intersects with top side
                        velocity.y = clamp(velocity.y, -1, 0.001f);
                    }
                    else if(intersector.y + intersector.height < this.getCollider().y + this.getCollider().height){
                        //intersects with bottom side
                        velocity.y = clamp(velocity.y, 0.001f, 1);
                    }
                }
            }

            for (Renderable renderable: RenderableManager.renderableObjects) {
                if(renderable.getCollider().overlaps(this.getCollider())){
                    if(renderable.TAG.equals("breakable")){
                        intersector = new Rectangle();
                        Intersector.intersectRectangles(this.getCollider(), renderable.getCollider(), intersector);
                        if(intersector.x > this.getCollider().x){
                            //Intersects with right side
                            velocity.x = clamp(velocity.x, -1, 0.001f);
                        }
                        else if(intersector.x + intersector.width < this.getCollider().x + this.getCollider().width) {
                            //Intersects with left side
                            velocity.x = clamp(velocity.x, 0.001f, 1);
                        }

                        if(intersector.y > this.getCollider().y){
                            //Intersects with top side
                            velocity.y = clamp(velocity.y, -1, 0.001f);
                        }
                        else if(intersector.y + intersector.height < this.getCollider().y + this.getCollider().height){
                            //intersects with bottom side
                            velocity.y = clamp(velocity.y, 0.001f, 1);
                        }
                    }
                }
            } //Loop activeForRender breakables
            System.out.println("Velocity: " + velocity);

            //interpolate this for clean movement
            this.setPosition(new Vector2(this.getPosition().x + this.velocity.x * speed, this.getPosition().y + this.velocity.y * speed));
        }
    }

    private void flipHandler() {
        try{
            if (ButchGame.mousePosition().x >= this.getPosition().x) { // if direction is right
                this.getSprite().setFlip(false, false);
                activeGun.getSprite().setFlip(false, false);
            } else { //if direction is left or not right
                this.getSprite().setFlip(true, false);
                activeGun.getSprite().setFlip(false, true); //
            }
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public Vector2 getAimDirection() {
        /*
            Determining a Vector Given Two Points

            PQ→=(xQ−xP,yQ−yP)

         */
        Vector2 aimDirection = new Vector2(ButchGame.mousePosition().x - this.getPosition().x, ButchGame.mousePosition().y - this.getPosition().y);
        return aimDirection;
    }

    public Vector2 getWeaponPosition(){
        Vector2 pos;
        if(ButchGame.mousePosition().x >= getPosition().x){
            if(this.activeGun.oneHanded){
                pos = new Vector2(getPosition().x + leftHandIKoffset.x, getPosition().y + leftHandIKoffset.y);
            }
            else{
                pos = new Vector2(getPosition().x, getPosition().y);
            }
        }
        else{
            if(this.activeGun.oneHanded){
                pos = new Vector2(getPosition().x + rightHandIKoffset.x, getPosition().y + rightHandIKoffset.y);
            }
            else{
                pos = new Vector2(getPosition());
            }
        }
        pos.y -= 40;
        return pos;
    }

    public static float clamp(float val, float min, float max) { //simple clamp function
        return Math.max(min, Math.min(max, val));
    }

    public void update(float delta) {
        this.activeGun.player = this;
        this.activeGun.parent = this;
        this.activeGun.activeForRender = true;

        inputHandler();
        movementHandler();
        flipHandler();

        System.out.println("COINSSSS:"+coin);
        for (Renderable renderable:RenderableManager.renderableObjects) {
            if(renderable.TAG == "item" && renderable.activeForRender){
                ItemPickup itemPickupOG = (ItemPickup) renderable;
                if(itemPickupOG.id == 3){
                    Item itemPickup = (Item) renderable;
                    intersector = new Rectangle();
                    if(Intersector.overlaps(itemPickup.collectionRange, this.getCollider()) && itemPickup.autoPickup){
                        this.coin += itemPickup.quantity;
                        itemPickup.activeForRender = false;
                    }
                }
            }
        }

        if(itemCollection.size() > 0){
            for(ItemPickup item:itemCollection){
                addItem(item);
            }
            itemCollection.clear();
        }
        sprite.setRegion(getFrame(delta));
        this.setSprite(sprite);

        this.getSprite().setScale(8);
        this.getSprite().setPosition(this.getPosition().x, this.getPosition().y);
        this.activeGun.activeForRender = true;
    }

    @Override
    public void takeHit(float damage) {

            health -= damage/5;

    }

    public void addItem(ItemPickup item){
        if(item.type== 0) {
            gunInventory.add(ButchGame.itemManager.getGun(item.id));
        } else if(item.type == 1){
            itemInventory.add(ButchGame.itemManager.getItem(item.id));
        }
        else if(item.type == 2) {
                System.out.println(item);
                System.out.println("type:"+ item.type);
                Item itemObj = (Item) item;
                System.out.println("AmmoCount:" +itemObj.quantity);
              if(itemObj.id == 0){
                  PistolAmmo newPistolAmmo = (PistolAmmo) item;
                  this.pistolAmmo += newPistolAmmo.quantity;
              } else if(itemObj.id == 1){
                  RifleAmmo newRifleAmmo = (RifleAmmo) item;
                  this.rifleAmmo += newRifleAmmo.quantity;
              }
              else if(itemObj.id == 2){
                  ShotgunAmmo newShotgunAmmo = (ShotgunAmmo) item;
                  this.shotgunAmmo += newShotgunAmmo.quantity;
              }
               item.activeForRender = false;
            }
        }

    public TextureRegion getFrame(float dt){
        TextureRegion region = null;


        switch(getState()){
            case UP:
                region = butchWalkingUp.getKeyFrame(stateTimer, true);
                break;
            case DOWN:
                region = butchIdle.getKeyFrame(stateTimer, true);
                break;
            case LEFT:
                region = butchWalkingLeft.getKeyFrame(stateTimer, true);
                break;
            case RIGHT:
                region = butchWalkingRight.getKeyFrame(stateTimer, true);
                break;
            case IDLE:
                region = butchIdle.getKeyFrame(stateTimer, true);
        }

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;

    }

    public State getState(){
        if(xAxis > 0){
            return State.RIGHT;
        }
        else if(xAxis < 0){
            return State.LEFT;
        }
        else if(yAxis > 0){
            return State.UP;
        }
        else if(yAxis < 0){
            return State.DOWN;
        }
        else{
            return State.IDLE;
        }

//        if(butchDead)
//            return State.DEAD;
//        else if(movingLeft)
//            return State.LEFT;
//        else if(movingRight)
//            return State.RIGHT;
//        else if(movingUp)
//            return State.UP;
//        else if(movingDown)
//            return State.DOWN;
//        else
//            return State.IDLE;
    }
    public float getHealth(){
        return health;
    }
}

