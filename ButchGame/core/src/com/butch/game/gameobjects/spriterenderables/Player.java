package com.butch.game.gameobjects.spriterenderables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
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
import com.butch.game.gameobjects.weapons.GunCreator;
import com.butch.game.gameobjects.weapons.Colt;


import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;

public class Player extends Renderable {
    public enum State { RUNNING, IDLE, DEAD, RELOADING, SHOOTING };
    private static float maxHealth = 100;
    public State currentState;
    public State previousState;
    float xAxis, yAxis, speed = 0;
    private Sprite sprite;

    private Animation<TextureRegion> butchWalking;
    private Animation<TextureRegion> butchIdle;
    private Animation<TextureRegion> butchDying;

    private boolean movingRight = false;
    private boolean movingLeft;
    private boolean movingUp;
    private boolean movingDown;
    private boolean butchDead;
    private boolean isButchIdle = true;

    private Iterator<Gun> gunInvIterator;

    public int rifleAmmo = 10;
    public int pistolAmmo = 10;
    public int shotgunAmmo = 10;
    public float health = 100;
    public int coin;

    public static ArrayList<Gun> gunInventory;
    public static ArrayList<ItemPickup> itemInventory;
    private static ArrayList<ItemPickup> itemCollection; //items in range if collection

    private Gun activeGun;
    private int gunInventoryIteration = 0;

    private ArrayList<Rectangle> mapColliders;
    private boolean canMove;
    private Vector2 velocity;

    private Vector2 leftHandIKoffset = new Vector2().setZero();

    private Vector2 rightHandIKoffset = new Vector2().setZero();

    private Rectangle intersector;

    public Sound walkingFX;
    public Sound hitEffect;

    private float stateTimer;

    public Player(Vector2 startPosition, ArrayList<Rectangle>mapStaticColliders){
        this.setPosition(startPosition);
        System.out.println("STARTING POS:" + startPosition);
        this.mapColliders = mapStaticColliders;
        this.TAG = "player";
        sprite = new Sprite(ButchGame.assets.get(ButchGame.assets.enemySprite, Texture.class));
        this.setSprite(sprite);
        this.getSprite().setScale(10);
        this.velocity = new Vector2().setZero();
        this.canMove = true;
        this.speed = 10;
        this.walkingFX = ButchGame.assets.get(ButchGame.assets.walkingFX, Sound.class);
        this.walkingFX.play();
        this.walkingFX.pause();
        this.gunInventory = new ArrayList<Gun>();
        this.itemInventory = new ArrayList<ItemPickup>();
        this.itemCollection = new  ArrayList<ItemPickup>();
        this.gunInventory.add(new GunCreator("Revolver"));
        this.gunInventory.add(new GunCreator("Shotgun"));
        this.gunInventory.add(new GunCreator("MachineGun"));
        this.gunInventory.add(new GunCreator("Musket"));
        this.activeGun = this.gunInventory.get(0);
        this.gunInvIterator = this.gunInventory.iterator();

        for (Gun gun:gunInventory) {
            if(gun != activeGun){
                gun.activeForRender = false;
            }
        }

        this.rightHandIKoffset = new Vector2(-50, -20); //how far from sprite center is the right hand
        this.leftHandIKoffset = new Vector2(50, -20); //how far away from sprite center is the left hand

        this.setCollider(new Rectangle(this.getPosition().x, this.getPosition().y, this.getSprite().getBoundingRectangle().width/2.5f, this.getSprite().getBoundingRectangle().height/1.5f));

        butchIdle = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.butchIdleAnim, TextureAtlas.class).getRegions());
        butchDying = new Animation<TextureRegion>(0.083f, ButchGame.assets.get(ButchGame.assets.butchDying, TextureAtlas.class).getRegions());
        butchWalking = new Animation<TextureRegion>(0.083f, ButchGame.assets.get(ButchGame.assets.butchWalking, TextureAtlas.class).getRegions());

        currentState = State.IDLE;
        previousState = State.IDLE;
        stateTimer = 0;
        butchDead = false;
        ////////////////////////////
       // health = 1000;
    }

    private void inputHandler() { // handle inputs
        if (!Gdx.input.isKeyPressed(Input.Keys.D)) {
            xAxis = 0;
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.W)) {
            yAxis = 0;
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.S)) {
            yAxis = 0;
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.A)) {
            xAxis = 0;
        }
        if (!Gdx.input.isKeyPressed(Input.Keys.A) || !Gdx.input.isKeyPressed(Input.Keys.S) || !Gdx.input.isKeyPressed(Input.Keys.W) || !Gdx.input.isKeyPressed(Input.Keys.D)) {
            isButchIdle = true;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            yAxis = 1;
            isButchIdle = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            yAxis = -1;
            isButchIdle = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            xAxis = -1;
            isButchIdle = false;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            xAxis = 1;
            isButchIdle = false;
        }
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            try{
                activeGun.Shoot();
            } catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            try {
                if (gunInvIterator.hasNext()) {
                    //System.out.println(gunInvIterator.next());
                    this.activeGun.activeForRender = false;
                    this.activeGun = gunInvIterator.next();
                    this.activeGun.activeForRender = true;
                } else {
                    this.activeGun.activeForRender = false;
                    this.activeGun = this.gunInventory.get(0);
                    this.activeGun.activeForRender = true;
                    gunInvIterator = gunInventory.iterator();
                }
            } catch (NoSuchElementException w){
                w.printStackTrace();
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.R)){
            try{
                this.activeGun.Reload();
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

            //interpolate this for clean movement
            this.setPosition(new Vector2(this.getPosition().x + this.velocity.x * speed, this.getPosition().y + this.velocity.y * speed));
        }
    }

//    private void flipHandler() {
//        try{
//            if (ButchGame.mousePosition().x >= this.getPosition().x) { // if direction is right
//                //this.getSprite().setFlip(false, false);
//                this.activeGun.getSprite().setFlip(false, false);
//            } else if (ButchGame.mousePosition().x < this.getPosition().x){ //if direction is left or not right
//                //this.getSprite().setFlip(true, false);
//                this.activeGun.getSprite().setFlip(false, true); //
//            }
//        } catch (NullPointerException e){
//            e.printStackTrace();
//        }
//    }

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

        if(!this.butchDead) {
            this.activeGun.player = this;
            this.activeGun.parent = this;
            this.activeGun.activeForRender = true;

            if(!butchDead){
                inputHandler();
                movementHandler();
                if(velocity.x > 0 || velocity.x < 0 || velocity.y > 0 || velocity.y < 0){
                    walkingFX.resume();
                }else{
                    walkingFX.pause();
                }
            }
        }

        for (Renderable renderable:RenderableManager.renderableObjects) {
            if(renderable.TAG == "item" && renderable.activeForRender){
                ItemPickup itemPickupOG = (ItemPickup) renderable;
                if(itemPickupOG.id == 3){
                    Item itemPickup = (Item) renderable;
                    intersector = new Rectangle();
                    if(Intersector.overlaps(itemPickup.collectionRange, this.getCollider()) && itemPickup.autoPickup){
                        addItem(itemPickup);
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


//        flipHandler();
        if(this.health <= 0){
            this.activeCollision = false;
//            this.activeForRender= false;
            this.butchDead = true;
//            this.destroy = true;
           this.activeGun.activeForRender = false;

        }
        else{
            //
        }
//        this.gunInvIterator = this.gunInventory.iterator();


    }

    @Override
    public void takeHit(float damage) {
        health -= damage;
    }

    public void addItem(ItemPickup item){
        System.out.println(item);
        if(item.getCollider().overlaps(this.getCollider())){
            System.out.println(item.type);
            if(item.type == 0) {
                gunInventory.add(ButchGame.itemManager.getGun(item.id));
                gunInvIterator = gunInventory.iterator();

            } else if(item.type == 1){
                itemInventory.add(ButchGame.itemManager.getItem(item.id));
                System.out.println("DING");
            }
            else if(item.type == 2) {
                System.out.println("DONG");
                Item itemObj = (Item) item;
                if(itemObj.id == 0){
                    this.pistolAmmo += itemObj.quantity;
                } else if(itemObj.id == 1){
                    this.rifleAmmo += itemObj.quantity;
                }
                else if(itemObj.id == 2){
                    this.shotgunAmmo += itemObj.quantity;
                }
                else if(itemObj.id == 3){
                    this.coin += itemObj.quantity;
                }
                else if(itemObj.id == 4){
                    this.health += itemObj.quantity;
                    if(this.health > maxHealth)
                        this.health = maxHealth;
                }
                item.activeForRender = false;
                item.collected();
            }
        }
        else{
            item.getPosition().lerp(this.getPosition(), 0.2f);
            if(item.type == 0) {
                gunInventory.add(ButchGame.itemManager.getGun(item.id));
                gunInvIterator = gunInventory.iterator();

            } else if(item.type == 1){
                itemInventory.add(ButchGame.itemManager.getItem(item.id));
                System.out.println("DING");
            }
            else if(item.type == 2) {
                System.out.println("DONG");
                Item itemObj = (Item) item;
                if(itemObj.id == 0){
                    this.pistolAmmo += itemObj.quantity;
                } else if(itemObj.id == 1){
                    this.rifleAmmo += itemObj.quantity;
                }
                else if(itemObj.id == 2){
                    this.shotgunAmmo += itemObj.quantity;
                }
                else if(itemObj.id == 3){
                    this.coin += itemObj.quantity;
                }
                else if(itemObj.id == 4){
                    this.health += itemObj.quantity;
                    if(this.health > maxHealth)
                        this.health = maxHealth;
                }
                item.activeForRender = false;
                item.collected();
            }
        }
    }

    public TextureRegion getFrame(float dt){
        TextureRegion region = null;
        currentState = getState();

        switch(currentState){
            case DEAD:
                if(previousState != currentState) {
                    stateTimer = 0;
                }
                region = butchDying.getKeyFrame(stateTimer, false);
                break;
            case RUNNING:
                region = butchWalking.getKeyFrame(stateTimer, true);
                break;
            case IDLE:
                region = butchIdle.getKeyFrame(stateTimer, true);
                break;
        }

        if(((ButchGame.mousePosition().x < getPosition().x) || !movingRight) && !region.isFlipX()){
            region.flip(true, false);
            movingRight = false;
        }

        if(((ButchGame.mousePosition().x >= getPosition().x) || movingRight) && region.isFlipX()) {
            region.flip(true, false);
            movingRight = true;
        }


        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;

    }

    public State getState(){
        if(health <= 0) {
            return State.DEAD;
        }
        else if(xAxis > 0){
            return State.RUNNING;
        }
        else if(xAxis < 0){
            return State.RUNNING;
        }
        else if(yAxis > 0){
            return State.RUNNING;
        }
        else if(yAxis < 0){
            return State.RUNNING;
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
    public Gun getActiveWeapon() {
        return this.activeGun;
    }
}

