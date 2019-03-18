package com.butch.game.gameobjects.spriterenderables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.butch.game.ButchGame;
import com.butch.game.gamemanagers.RenderableManager;
import com.butch.game.gamemanagers.Rumble;
import com.butch.game.gameobjects.abstractinterface.Gun;
import com.butch.game.gameobjects.abstractinterface.Item;
import com.butch.game.gameobjects.abstractinterface.ItemPickup;
import com.butch.game.gameobjects.abstractinterface.Renderable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Player extends Renderable {
    private int playerLevel;
    private boolean followCamera = true;
    public Vector3 shake;
    public Vector3 reverseShake;
    private float shakeY;
    private float shakeX;

    public OrthographicCamera getCam() {
        return cam;
    }

    public void setCam(OrthographicCamera cam) {
        this.cam = cam;
    }

    public enum State { RUNNING, IDLE, DEAD, RELOADING, SHOOTING, RIDING, RIDINGIDLE };
    private float maxHealth;
    private static float baseHealth = 100;
    public State currentState;
    public State previousState;
    public float xAxis, yAxis, speed = 0;
    private Sprite sprite;
    public float shakeAmount = 25;
    public Animation<TextureRegion> butchWalking;
    public Animation<TextureRegion> butchIdle;
    private Animation<TextureRegion> butchDying;
    private Animation<TextureRegion> butchHorseRiding;
    private Animation<TextureRegion> butchHorseIdle;

    private boolean isRiding = false;

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
    public int musketAmmo = 10;
    public float health = 100;
    public int coin;

    private static ArrayList<Gun> gunInventory;
    public static ArrayList<ItemPickup> itemInventory;
    private static ArrayList<ItemPickup> itemCollection; //items in range if collection

    private Gun activeGun;
    private int gunInventoryIteration = 0;

    public ArrayList<Rectangle> mapColliders;
    private boolean canMove;
    private Vector2 velocity;

    private Vector2 leftHandIKoffset = new Vector2().setZero();

    private Vector2 rightHandIKoffset = new Vector2().setZero();

    private Rectangle intersector;
    public boolean loaded;

    public Sound walkingFX;
    public Sound hitEffect;

    private OrthographicCamera cam;
    Rumble rumble;
    private float stateTimer;
    private int activeWeaponNumber = 0;
    public boolean isAllowedToMove = true;
    public boolean allowedtoPress = true;

    public Player(Vector2 startPosition, ArrayList<Rectangle>mapStaticColliders){
        this.loaded = false;
        this.setPosition(startPosition);
        this.maxHealth = baseHealth + ((playerLevel-1) * 10);
        this.health = maxHealth;
        this.shake = new Vector3();
        rumble = new Rumble();
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
//        this.gunInventory.addAll(weaponCache);

        this.itemInventory = new ArrayList<ItemPickup>();
        this.itemCollection = new  ArrayList<ItemPickup>();
//        this.gunInventory.add(new GunCreator("Revolver"));
//        this.gunInventory.add(new GunCreator("Shotgun"));
//        this.gunInventory.add(new GunCreator("MachineGun"));
//        this.gunInventory.add(new GunCreator("Musket"));

        this.rightHandIKoffset = new Vector2(-50, -20); //how far from sprite center is the right hand
        this.leftHandIKoffset = new Vector2(50, -20); //how far away from sprite center is the left hand

        this.setCollider(new Rectangle(this.getPosition().x, this.getPosition().y, this.getSprite().getBoundingRectangle().width/2.5f, this.getSprite().getBoundingRectangle().height/1.5f));

        butchIdle = new Animation<TextureRegion>(0.75f, ButchGame.assets.get(ButchGame.assets.butchIdleAnim, TextureAtlas.class).getRegions());
        butchDying = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.butchDying, TextureAtlas.class).getRegions());
        butchWalking = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.butchWalking, TextureAtlas.class).getRegions());
        butchHorseRiding = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.butchHorseRiding, TextureAtlas.class).getRegions());
        butchHorseIdle = new Animation<TextureRegion>(0.75f, ButchGame.assets.get(ButchGame.assets.butchHorseIdle, TextureAtlas.class).getRegions());

        currentState = State.IDLE;
        previousState = State.IDLE;
        stateTimer = 0;
        butchDead = false;
        ////////////////////////////
       // health = 1000;
    }

    public void loadedPing(){
        if(gunInventory.size() > 0){
            this.activeGun = this.gunInventory.get(0);
            System.out.println(this.gunInventory.get(0).gunName);
            this.gunInvIterator = this.gunInventory.iterator();

            for (Gun gun:gunInventory) {
                if(gun != activeGun){
                    gun.activeForRender = false;
                }
            }
        }
        loaded = true;
        System.out.println("Loaded");
    }

    private void inputHandler() { // handle inputs
        if(isAllowedToMove&& allowedtoPress) {
            if (!Gdx.input.isKeyPressed(Input.Keys.D) || !Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                xAxis = 0;
            }
            if (!Gdx.input.isKeyPressed(Input.Keys.W) || !Gdx.input.isKeyPressed(Input.Keys.UP)) {
                yAxis = 0;
            }
            if (!Gdx.input.isKeyPressed(Input.Keys.S) || !Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                yAxis = 0;
            }
            if (!Gdx.input.isKeyPressed(Input.Keys.A) || !Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                xAxis = 0;
            }
            if (!Gdx.input.isKeyPressed(Input.Keys.A) || !Gdx.input.isKeyPressed(Input.Keys.S) || !Gdx.input.isKeyPressed(Input.Keys.W) || !Gdx.input.isKeyPressed(Input.Keys.D) || !Gdx.input.isKeyPressed(Input.Keys.LEFT) || !Gdx.input.isKeyPressed(Input.Keys.RIGHT) || !Gdx.input.isKeyPressed(Input.Keys.UP) || !Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                isButchIdle = true;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
                yAxis = 1;
                isButchIdle = false;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                yAxis = -1;
                isButchIdle = false;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                xAxis = -1;
                isButchIdle = false;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                xAxis = 1;
                isButchIdle = false;
            }
            if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                try {
                    if (!isRiding)
                        if (activeGun.Shoot()) {
                            rumble = new Rumble();
                            rumble.rumble(activeGun.damage, activeGun.fireRate);
                        }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
                try {
                    if (activeWeaponNumber < (gunInventory.size() - 1)) {
                        activeWeaponNumber++;
                    } else {
                        activeWeaponNumber = 0;
                    }
                    this.activeGun.activeForRender = false;
                    this.activeGun = this.gunInventory.get(activeWeaponNumber);
                    this.activeGun.activeForRender = true;
                } catch (NoSuchElementException w) {
                    w.printStackTrace();
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
                if (isRiding) {
                    isRiding = false;
                } else {
                    isRiding = true;
                }
            }
//
//        if(Gdx.input.isKeyPressed(Input.Keys.R)){
//            try{
//                this.activeGun.Reload();
//            } catch (NullPointerException e){
//                e.printStackTrace();
//            }
//        }

            if (Gdx.input.isKeyPressed(Input.Keys.E)) {
                for (Renderable renderable : RenderableManager.renderableObjects) {
                    if (renderable.TAG == "item" && renderable.activeForRender) {
                        ItemPickup itemPickup = (ItemPickup) renderable;
                        intersector = new Rectangle();
                        if (Intersector.overlaps(itemPickup.collectionRange, this.getCollider())) {
                            itemCollection.add(itemPickup);
                            itemPickup.activeForRender = false;
                        }
                    }
                }
            }
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

    public Vector2 getRecoilDirection() {
        Vector2 recoilDirection = new Vector2(this.getPosition().x - ButchGame.mousePosition().x, this.getPosition().y - ButchGame.mousePosition().y);
        return recoilDirection;
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

        if(cam != null){
            Vector2 mousePosition = new Vector2(ButchGame.mousePosition().x, ButchGame.mousePosition().y); //get mouse pos
            float newX = mousePosition.x + (this.getPosition().x - mousePosition.x) / 1.2f; //gets position  divirsor percentage) along vector instead of midpoint
            float newY = mousePosition.y + (this.getPosition().y - mousePosition.y) / 1.2f; //gets position  divirsor percentage) along vector instad of midpoint
            Vector3 camTarget = new Vector3(newX, newY,  cam.position.z);

            cam.position.slerp(camTarget, 0.1f);
            if (Rumble.getRumbleTimeLeft() > 0) {
                Rumble.tick(Gdx.graphics.getDeltaTime());
                cam.translate(rumble.getPos());
            }
        }


        for (Renderable renderable:RenderableManager.renderableObjects) {
            if(renderable.TAG == "item" && renderable.activeForRender){
                ItemPickup itemPickupOG = (ItemPickup) renderable;
                if(itemPickupOG.id == 5){
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
        sprite.setSize(getFrame(delta).getRegionWidth(), getFrame(delta).getRegionHeight());
        this.setSprite(sprite);

        this.getSprite().setScale(10);
        this.getSprite().setPosition(this.getPosition().x, this.getPosition().y);
        if(isRiding) {
            this.speed = 50;
            this.activeGun.activeForRender = false;
        }
        else {
            this.speed = 10;
            this.activeGun.activeForRender = true;
        }


//        flipHandler();
        if(this.health <= 0){
            this.activeCollision = false;
//            this.activeForRender= false;
            this.butchDead = true;
//            this.destroy = true;
           this.activeGun.activeForRender = false;
//            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
//                Gdx.app.exit();
//            }
        }
        else{
            //
        }
//        this.gunInvIterator = this.gunInventory.iterator();
    }

    @Override
    public void takeHit(float damage) {
        health -= damage;
        if(health < 0)
            health = 0;
        rumble = new Rumble();
        rumble.rumble(damage, 0.2f);
    }

    public void addItem(ItemPickup item){
        if(item.getCollider().overlaps(this.getCollider())){
            if(item.type == 0) {
                gunInventory.add(ButchGame.itemManager.getGun(item.id));
                gunInvIterator = gunInventory.iterator();

            } else if(item.type == 1){
                itemInventory.add(ButchGame.itemManager.getItem(item.id));
            }
            else if(item.type == 2) {
                Item itemObj = (Item) item;
                if(itemObj.id == 0){
                    this.pistolAmmo += itemObj.quantity;
                } else if(itemObj.id == 1){
                    this.rifleAmmo += itemObj.quantity;
                }
                else if(itemObj.id == 2){
                    this.shotgunAmmo += itemObj.quantity;
                }
                else if(itemObj.id == 3) {
                    this.musketAmmo += itemObj.quantity;
                }
                else if(itemObj.id == 4){
                    this.health += itemObj.quantity;
                    if(this.health > maxHealth)
                        this.health = maxHealth;
                }
                else if(itemObj.id == 5){
                    this.coin += itemObj.quantity;
                }
                else if(itemObj.id == 7){
                    this.playerLevel += 1;
                    this.maxHealth = this.baseHealth + ((playerLevel-1) * 10);
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
            }
            else if(item.type == 2) {
                Item itemObj = (Item) item;
                if(itemObj.id == 0){
                    this.pistolAmmo += itemObj.quantity;
                } else if(itemObj.id == 1){
                    this.rifleAmmo += itemObj.quantity;
                }
                else if(itemObj.id == 2){
                    this.shotgunAmmo += itemObj.quantity;
                }
                else if(itemObj.id == 3) {
                    this.musketAmmo += itemObj.quantity;
                }
                else if(itemObj.id == 4){
                    this.health += itemObj.quantity;
                    if(this.health > maxHealth)
                        this.health = maxHealth;
                }
                else if(itemObj.id == 5){
                    this.coin += itemObj.quantity;
                }
                else if(itemObj.id == 7){
                    this.playerLevel += 1;
                    this.maxHealth = this.baseHealth + ((playerLevel-1) * 10);
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
            case RIDING :
                region = butchHorseRiding.getKeyFrame(stateTimer, true);
                break;
            case RIDINGIDLE :
                region = butchHorseIdle.getKeyFrame(stateTimer, true);
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

    public TextureRegion getFrame(float dt, State state){
        TextureRegion region = null;
        currentState = state;

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
            case RIDING :
                region = butchHorseRiding.getKeyFrame(stateTimer, true);
                break;
            case RIDINGIDLE :
                region = butchHorseIdle.getKeyFrame(stateTimer, true);
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
        else if(isRiding && (xAxis > 0 || xAxis < 0 || yAxis > 0 || yAxis< 0)) {
            return State.RIDING;
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
        else if(isRiding) {
            return State.RIDINGIDLE;
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

    public void setActiveWeapon(Gun weapon) { this.activeGun = weapon; }

    public Iterator<Gun> getGunInvIterator() { return this.gunInvIterator; }

    public void setGunInvIterator(Iterator<Gun> gunIterator) { this.gunInvIterator = gunIterator; }

    public ArrayList<Gun> getGunInventory() { return this.gunInventory; }

    public void setGunInventory(ArrayList<Gun> gunList) { this.gunInventory = gunList; }

    public int getPlayerLevel() { return this.playerLevel; }

    public void setPlayerLevel(int playerLevel) { this.playerLevel = playerLevel; }

    public float getPlayerHealthPercent() { return (this.health / this.maxHealth * 100); }

    public boolean getButchDead() { return butchDead; }

    public float getMaxHealth() { return maxHealth; }
}

