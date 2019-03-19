package com.butch.game.gameobjects.spriterenderables;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.butch.game.ButchGame;
import com.butch.game.gamemanagers.RenderableManager;
import com.butch.game.gameobjects.HUDObjects.HealthBar;
import com.butch.game.gameobjects.abstractinterface.Gun;
import com.butch.game.gameobjects.abstractinterface.Renderable;

//import com.butch.game.gameobjects.weapons.MachineGun;
//import com.butch.game.gameobjects.weapons.Shotgun;
//import com.butch.game.screens.NewGameScreen;

import com.butch.game.gameobjects.weapons.GunCreator;


import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Enemy extends Renderable  {
    public enum State {IDLE, WALKING}
//    private Sound fx = ButchGame.assets.get(ButchGame.assets.coinCollection, Sound.class);
    private Sound fx = null;
    public float health;
    public Gun weapon;
    public Player target;
    public Circle activateRange;
    public boolean combatActive = false;
    private Vector2 direction;
    private float speed;
    private Vector2 leftHandIKoffset;
    private Vector2 rightHandIKoffset;
    public int rifleAmmo = 100000;
    public int pistolAmmo = 100000;
    public int shotgunAmmo = 100000;
    public int musketAmmo = 100000;
    //Comment this line out when testing
    private Sprite sprite = new Sprite(ButchGame.assets.get(ButchGame.assets.enemySprite, Texture.class));
    //Uncomment this line when testing
//    private Sprite sprite = new Sprite();
    private boolean movingRight = false;

    private Animation<TextureRegion> enemy1Idle;
    private Animation<TextureRegion> enemy1Walking;
    private Animation<TextureRegion> enemy2Idle;
    private Animation<TextureRegion> enemy2Walking;
    private Animation<TextureRegion> enemy3Idle;
    private Animation<TextureRegion> enemy3Walking;
    private Animation<TextureRegion> enemy4Idle;
    private Animation<TextureRegion> enemy4Walking;

    private Animation<TextureRegion> enemyIdle;
    private Animation<TextureRegion> enemyWalking;

    private int enemyType;
    public ArrayList<Vector2> route;
    public Vector2 targetPos;
    public Vector2 localPos;
    int iteration;

    public ENEMYSTATE state;
    private long lastCheck;
    private int checkRate;

    private long lastShootCheck;
    private int shootCheckRate;
    private boolean currentShootMode;

    private State currentState = State.IDLE;
    private State previousState = State.IDLE;

    public Vector2 newDirection;
    private boolean swappedCombat = false;
    private float stateTimer = 0;
    /// testing
   // private Hb healthB;
    private Batch batch;
    private Sound hitEffect;
    ///
    private Rectangle colliderRect;

    public Enemy(Vector2 position, int enemyType){
        this.TAG = "enemy";
        this.enemyType = enemyType;
        this.rightHandIKoffset = new Vector2(-50, 0); //how far from sprite center is the right hand
        this.leftHandIKoffset = new Vector2(50, 0);
        this.activateRange = new Circle(this.getPosition().x, this.getPosition().y, 1600);
//        switch(this.enemyType) {
//            case 0 :
//                this.weapon = new GunCreator("MachineGun");
//                break;
//            case 1 :
//                this.weapon = new GunCreator("MachineGun");
//                break;
//            case 2 :
//                this.weapon = new GunCreator("Shotgun");
//                break;
//            case 3 :
//                this.weapon = new GunCreator("Musket");
//                break;
//        }

        this.setPosition(position);
        this.health = 100;
        this.speed = 2.5F;
        this.activeForRender = true;
        this.activeCollision = true;
        this.setSprite(sprite);
        colliderRect = new Rectangle(this.getPosition().x - (this.getSprite().getWidth()), this.getPosition().x - (this.getSprite().getHeight()), this.getSprite().getBoundingRectangle().width/2.5f * 10, this.getSprite().getBoundingRectangle().height/1.5f * 10);
        this.setCollider(colliderRect);
        this.state = ENEMYSTATE.IDLE;
        this.checkRate = 5;
        this.shootCheckRate = 3;
        this.route = new ArrayList<Vector2>();
        this.direction = new Vector2().setZero();
        this.newDirection = new Vector2().setZero();
        this.targetPos = new Vector2().setZero();
        this.localPos = new Vector2().setZero();
        this.iteration = 0;

        switch(this.enemyType) {
            case 0 :
                this.enemyIdle = new Animation<TextureRegion>(0.75f, ButchGame.assets.get(ButchGame.assets.enemy1Idle, TextureAtlas.class).getRegions());
                this.enemyWalking = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.enemy1Walking, TextureAtlas.class).getRegions());
                this.weapon = new GunCreator("MachineGun");
                break;
            case 1 :
                this.enemyIdle = new Animation<TextureRegion>(0.75f, ButchGame.assets.get(ButchGame.assets.enemy2Idle, TextureAtlas.class).getRegions());
                this.enemyWalking = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.enemy2Walking, TextureAtlas.class).getRegions());
                this.weapon = new GunCreator("MachineGun");
                break;
            case 2 :
                this.enemyIdle = new Animation<TextureRegion>(0.75f, ButchGame.assets.get(ButchGame.assets.enemy3Idle, TextureAtlas.class).getRegions());
                this.enemyWalking = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.enemy3Walking, TextureAtlas.class).getRegions());
                this.weapon = new GunCreator("Shotgun");
                break;
            case 3 :
                this.enemyIdle = new Animation<TextureRegion>(0.75f, ButchGame.assets.get(ButchGame.assets.enemy4Idle, TextureAtlas.class).getRegions());
                this.enemyWalking = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.enemy4Walking, TextureAtlas.class).getRegions());
                this.weapon = new GunCreator("Musket");
                break;
            case 10 :
                this.enemyIdle = new Animation<TextureRegion>(0.75f, ButchGame.assets.get(ButchGame.assets.sheriff1Idle, TextureAtlas.class).getRegions());
                this.enemyWalking = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.sheriff1Walking, TextureAtlas.class).getRegions());
                this.weapon = new GunCreator("Musket");
                break;
            case 11 :
                this.enemyIdle = new Animation<TextureRegion>(0.75f, ButchGame.assets.get(ButchGame.assets.sheriff2Idle, TextureAtlas.class).getRegions());
                this.enemyWalking = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.sheriff2Walking, TextureAtlas.class).getRegions());
                this.weapon = new GunCreator("MachineGun");
                break;
            case 12 :
                this.enemyIdle = new Animation<TextureRegion>(0.75f, ButchGame.assets.get(ButchGame.assets.sheriff3Idle, TextureAtlas.class).getRegions());
                this.enemyWalking = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.sheriff3Walking, TextureAtlas.class).getRegions());
                this.weapon = new GunCreator("Shotgun");
                break;
            case 20 :
                this.enemyIdle = new Animation<TextureRegion>(0.75f, ButchGame.assets.get(ButchGame.assets.soldier3Idle, TextureAtlas.class).getRegions());
                this.enemyWalking = new Animation<TextureRegion>(0.75f, ButchGame.assets.get(ButchGame.assets.soldier3Idle, TextureAtlas.class).getRegions());
                this.weapon = new GunCreator("MachineGun");
            case 21 :
                this.enemyIdle = new Animation<TextureRegion>(0.75f, ButchGame.assets.get(ButchGame.assets.soldier4Idle, TextureAtlas.class).getRegions());
                this.enemyWalking = new Animation<TextureRegion>(0.75f, ButchGame.assets.get(ButchGame.assets.soldier4Idle, TextureAtlas.class).getRegions());
                this.weapon = new GunCreator("Shotgun");
            case 100 :
                this.enemyIdle = new Animation<TextureRegion>(0.75f, ButchGame.assets.get(ButchGame.assets.lincolnIdle, TextureAtlas.class).getRegions());
                this.enemyWalking = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.lincolnWalking, TextureAtlas.class).getRegions());
                this.weapon = new GunCreator("Shotgun");
            case 99 :
                this.enemyIdle = new Animation<TextureRegion>(1, new TextureRegion());
                this.enemyWalking = new Animation<TextureRegion>(1, new TextureRegion());
                this.weapon = new GunCreator("TestWeapon");
        }
        this.weapon.parent = this;
        this.weapon.activeForRender = true;
//        enemy1Idle = new Animation<TextureRegion>(0.75f, ButchGame.assets.get(ButchGame.assets.enemy1Idle, TextureAtlas.class).getRegions());
//        enemy2Idle = new Animation<TextureRegion>(0.75f, ButchGame.assets.get(ButchGame.assets.enemy2Idle, TextureAtlas.class).getRegions());
//        enemy3Idle = new Animation<TextureRegion>(0.75f, ButchGame.assets.get(ButchGame.assets.enemy3Idle, TextureAtlas.class).getRegions());
//        enemy4Idle = new Animation<TextureRegion>(0.75f, ButchGame.assets.get(ButchGame.assets.enemy4Idle, TextureAtlas.class).getRegions());
//        enemy1Walking = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.enemy1Walking, TextureAtlas.class).getRegions());
//        enemy2Walking = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.enemy2Walking, TextureAtlas.class).getRegions());
//        enemy3Walking = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.enemy3Walking, TextureAtlas.class).getRegions());
//        enemy4Walking = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.enemy4Walking, TextureAtlas.class).getRegions());


     //
        //healthB = new Hb(this);
        //healthBar = new HealthBar((int)this.health, 10, this.getPosition().x,this.getPosition().y + this.sprite.getHeight() +20 );
        batch = new SpriteBatch();
        this.hitEffect = ButchGame.assets.get(ButchGame.assets.oof, Sound.class);
//
    }
    public float getHealth(){
        return this.health;
    }

    @Override
    public void update(float delta ) {
        this.weapon.accuracy *= 3;
        for (Renderable renderable : RenderableManager.renderableObjects) {
            if (renderable.TAG == "player") {
                if (Intersector.overlaps(this.activateRange, renderable.getCollider())) {
                    this.combatActive = true;
                    this.target = (Player) renderable;
                }
                else{
                    this.combatActive = false;
                }
            }
        }

        this.setPosition(new Vector2(this.getPosition().x + this.newDirection.x * speed,this.getPosition().y + this.newDirection.y * speed ));
        this.getCollider().setPosition(new Vector2(this.getPosition().x - (this.getSprite().getWidth() * 100), this.getPosition().y - (this.getSprite().getHeight() * 10)));
//        this.getCollider().setPosition(this.getPosition());
        state = getState();

        switch (state){
            case IDLE:
                this.newDirection.setZero();
                break;
            case ROUTE:
                double posx = (double) this.getPosition().x;
                double posy = (double) this.getPosition().y;
                double tarx = (double) this.targetPos.x;
                double tary = (double) this.targetPos.y;
                if(Vector2.dst2(this.getPosition().x,this.getPosition().y,targetPos.x, targetPos.y) < 10){
                    if(iteration < route.size()-1){
                        iteration++;
                        targetPos = route.get(iteration);
                    }
                    else{
                        iteration = 0;
                        targetPos = route.get(iteration);
                    }
                }
                newDirection = new Vector2(this.targetPos.x - this.getPosition().x, this.targetPos.y - this.getPosition().y).nor();
                break;
            case CHASE:
                if(!(this.target == null && target.health > 0)){
                    newDirection = new Vector2(this.target.getPosition().x - this.getPosition().x, this.target.getPosition().y - this.getPosition().y).nor();
                    this.getCollider().setPosition(this.getPosition().x + newDirection.x * speed, this.getPosition().y + newDirection.y * speed);
                    for (Rectangle renderable:RenderableManager.mapColliders) {
                        if(this.getCollider().overlaps(renderable)){
                            newDirection = Vector2.Zero;
                            this.getCollider().setPosition(this.getPosition());
                        }
                    }
                }
                break;
            case PICKSPOT:
                if(this.getPosition().dst2(localPos) < 10 || localPos == Vector2.Zero){
                    Random random = new Random();
                    double r = 100;
                    double x = r * Math.sin(random.nextFloat());
                    double y = r * Math.sin(random.nextFloat());
                    float newX = (float) x;
                    float newY = (float) y;
                    localPos = new Vector2(target.getPosition().x + newX, target.getPosition().y + newY);
                    this.getCollider().setPosition(localPos);
                    for(Rectangle renderable: RenderableManager.mapColliders){
                        if(this.getCollider().overlaps(renderable)){
                            x = r * Math.sin(random.nextFloat());
                            y = r * Math.sin(random.nextFloat());
                            newX = (float) x;
                            newY = (float) y;
                            localPos = new Vector2(target.getPosition().x + newX, target.getPosition().y + newY);
                        }
                    }
                }

                direction = new Vector2(this.localPos.x - this.getPosition().x, this.localPos.y - this.getPosition().y);
                break;
        }
        if(combatActive && target.health > 0){
            direction = new Vector2(this.target.getPosition().x - this.getPosition().x, this.target.getPosition().y - this.getPosition().y);
            System.out.println("TRY SHOOT");
            if(shouldShoot()){
                System.out.println("shooting!:"+direction);
                this.weapon.Shoot();
            }
        }
        this.setPosition(new Vector2(this.getPosition().x + this.newDirection.x * speed,this.getPosition().y + this.newDirection.y * speed ));

//        System.out.println("Active for render? " + this.activeForRender);
//        if (!this.combatActive) {
//            try {
//                for (Renderable renderable : RenderableManager.renderableObjects) {
//                    if (renderable.TAG == "player") {
//                        if (Intersector.overlaps(this.activateRange, renderable.getCollider())) {
//                            this.combatActive = true;
//                            this.target = (Player) renderable;
//                        }
//                    }
//                }
//            } catch (NullPointerException e) {
//                e.printStackTrace();
//            }
//        }//CHECK IF SHOULD FIGHT
//        else {
//            this.combatActive = false;
//            try {
//                direction = new Vector2(this.target.getPosition().x - this.getPosition().x, this.target.getPosition().y - this.getPosition().y).nor();
//                this.setPosition(new Vector2(this.getPosition().x + this.direction.x * speed, this.getPosition().y + this.direction.y * speed));
//                if (this.weapon.clip > 0) {
//                    this.weapon.Shoot();
//                } else {
//                    this.weapon.Reload();
//                }
//            } catch (NullPointerException e) {
//                e.printStackTrace();
//            }
//
//        }

        sprite.setRegion(getFrame(delta));
        sprite.setSize(getFrame(delta).getRegionWidth(), getFrame(delta).getRegionHeight());
        this.setSprite(sprite);

        this.getSprite().setScale(10);
        this.getSprite().setPosition(this.getPosition().x, this.getPosition().y);
        this.getCollider().setPosition(new Vector2(this.getPosition().x - this.getSprite().getWidth() * 3, this.getPosition().y - this.getSprite().getHeight() * 3.5f));
//        this.getCollider().setPosition(this.getPosition());
        this.activateRange = new Circle(this.getPosition().x, this.getPosition().y, 1600);
//
        if (this.health <= 0) {
            this.activeCollision = false;
            this.activeForRender = false;
            this.weapon.activeForRender = false;
        }

//        healthB.update(delta);
//    healthBar.draw();
        ///

        //healthB.update();

    }

    public void render(){
//        healthB.render(batch);
    }

    @Override
    public void takeHit(float damage) {
        this.health -= damage;
        this.hitEffect.play();
    }

    public Vector2 aimDirection(){
        return direction;
    }

    public boolean shouldShoot(){
//        long thisShootCheck = System.currentTimeMillis();
//        if((thisShootCheck - lastShootCheck) >= (long) (shootCheckRate * 1000)){
//            Random random = new Random();
//            boolean newShootMode = random.nextBoolean();
//            this.currentShootMode = newShootMode;
//            return newShootMode;
//        }else{
//            return currentShootMode;
//        }

        Random r = new Random();
        float newRand = r.nextFloat();
        return newRand > 0.88f;
    }

    public Vector2 getWeaponPosition(){
        Vector2 pos;
        if(target.getPosition().x>= this.getPosition().x){
            if(this.weapon.oneHanded){
                pos = new Vector2(this.getPosition().x + leftHandIKoffset.x, this.getPosition().y + leftHandIKoffset.y);
            }
            else{
                pos = this.getPosition();
            }
        }
        else {
            if(this.weapon.oneHanded){
                pos = new Vector2(getPosition().x + rightHandIKoffset.x, getPosition().y + rightHandIKoffset.y);
            }
            else{
                pos = this.getPosition();
            }
        }
        return pos;
    }

    public ENEMYSTATE getState(){
        long thisCheck = System.currentTimeMillis();
        if(((thisCheck - lastCheck) >= (long) (checkRate * 1000)) || swappedCombat){
            ArrayList<ENEMYSTATE> stateChoice = new ArrayList<ENEMYSTATE>();
            if(combatActive){
                stateChoice.add(ENEMYSTATE.IDLE);
                stateChoice.add(ENEMYSTATE.CHASE);
                stateChoice.add(ENEMYSTATE.PICKSPOT);
            }
            else{
                stateChoice.add(ENEMYSTATE.IDLE);
                stateChoice.add(ENEMYSTATE.ROUTE);
                stateChoice.add(ENEMYSTATE.ROUTE);
                stateChoice.add(ENEMYSTATE.ROUTE);
            }
            Random rand = new Random();
            ENEMYSTATE newState = stateChoice.get(rand.nextInt(stateChoice.size()));
            lastCheck = thisCheck;
            return newState;
        }
        else{
            return state;
        }
    }

    public enum ENEMYSTATE{
        IDLE, ROUTE, CHASE, PICKSPOT
    }
    public Gun getActiveWeapon() {
        return this.weapon;
    }
    private TextureRegion getFrame(float dt) {
        TextureRegion region = null;
        currentState = getAnimState();

        switch(currentState) {
            case WALKING:
                region = enemyWalking.getKeyFrame(stateTimer, true);
                break;
            case IDLE:
                region = enemyIdle.getKeyFrame(stateTimer, true);
                break;
        }

        if(((direction.x < 0 || !movingRight) && !region.isFlipX())){
            region.flip(true, false);
            movingRight = false;
        }

        if(((direction.x >= 0) || movingRight) && region.isFlipX()) {
            region.flip(true, false);
            movingRight = true;
        }

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }

    private State getAnimState() {
        if(direction != null){
            if (direction.x < 0)
                return State.WALKING;
            else if (direction.x > 0)
                return State.WALKING;
            else if (direction.y < 0)
                return State.WALKING;
            else if (direction.y > 0)
                return State.WALKING;
            else
                return State.IDLE;
        }else{
            return State.IDLE;
        }
    }
    //E.L. Territory!!
//    private class Hb{
//        private Sprite healthBarBG;
//        private Sprite healthBarFG;
//        private Enemy owner;
//        //private Batch batch;
//        private final short buffer = 20;
//        public Hb(Enemy owner){
//            this.owner = owner;
//            healthBarBG = new Sprite(new Texture("HUD Stuff/healthBarBG.png"));
//            healthBarFG = new Sprite (new Texture("HUD Stuff/healthBarFG.png"));
//            healthBarBG.setX(owner.getPosition().x);
//            healthBarBG.setY(owner.getPosition().y + owner.sprite.getHeight() + buffer);
//            healthBarFG.setX(healthBarBG.getX());
//            healthBarFG.setY(healthBarBG.getY());
//            healthBarBG.setSize(75,10);
//            healthBarFG.setSize(75,10);
//        }
//
//        public void update(){
////            healthBarBG = new Sprite(new Texture("HUD Stuff/healthBarBG.png"));
////            healthBarFG = new Sprite (new Texture("HUD Stuff/healthBarFG.png"));
//            healthBarBG.setX(owner.getPosition().x);
//            System.out.println("owner x =" + owner.getPosition().x + "back pos is" + healthBarFG.getX() + "" + healthBarFG.getY());
//            System.out.println("owner height = " + owner.sprite.getHeight() );
//            healthBarBG.setY(owner.getPosition().y + owner.sprite.getHeight() + buffer);
////            healthBarBG.setX(0);
////            healthBarBG.setY(0);
//            healthBarFG.setX(healthBarBG.getX());
//            healthBarFG.setY(healthBarFG.getY());
//
////           batch.begin();
//
////            batch.end();
//        }
//        public void render(Batch batch){
//            batch.begin();
//            batch.draw(healthBarBG, healthBarBG.getX(), healthBarBG.getY());
//            //System.out.println("is printing? ");
//            batch.draw(healthBarFG, healthBarFG.getX(), healthBarFG.getY());
//            batch.end();
//        }
//    }
}
