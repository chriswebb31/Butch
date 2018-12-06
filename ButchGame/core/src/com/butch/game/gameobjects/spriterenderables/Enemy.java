package com.butch.game.gameobjects.spriterenderables;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.gamemanagers.RenderableManager;
import com.butch.game.gameobjects.abstractinterface.Gun;
import com.butch.game.gameobjects.abstractinterface.Renderable;
import com.butch.game.gameobjects.weapons.MachineGun;
import com.butch.game.gameobjects.weapons.Shotgun;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Enemy extends Renderable {
    public enum State {IDLE, WALKING}
    private Sound fx = ButchGame.assets.get(ButchGame.assets.coinCollection, Sound.class);
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
    private Sprite sprite = new Sprite(ButchGame.assets.get(ButchGame.assets.enemySprite, Texture.class));
    private boolean movingRight = false;

    private TextureAtlas enemy1IdleAtlas = new TextureAtlas(ButchGame.assets.enemy1Idle);
    private TextureAtlas enemy1WalkingAtlas = new TextureAtlas(ButchGame.assets.enemy1Walking);
    private TextureAtlas enemy2IdleAtlas = new TextureAtlas(ButchGame.assets.enemy2Idle);
    private TextureAtlas enemy2WalkingAtlas = new TextureAtlas(ButchGame.assets.enemy2Walking);
    private TextureAtlas enemy3IdleAtlas = new TextureAtlas(ButchGame.assets.enemy3Idle);
    private TextureAtlas enemy3WalkingAtlas = new TextureAtlas(ButchGame.assets.enemy3Walking);
    private TextureAtlas enemy4IdleAtlas = new TextureAtlas(ButchGame.assets.enemy4Idle);
    private TextureAtlas enemy4WalkingAtlas = new TextureAtlas(ButchGame.assets.enemy4Walking);

    private Animation<TextureRegion> enemy1Idle;
    private Animation<TextureRegion> enemy1Walking;
    private Animation<TextureRegion> enemy2Idle;
    private Animation<TextureRegion> enemy2Walking;
    private Animation<TextureRegion> enemy3Idle;
    private Animation<TextureRegion> enemy3Walking;
    private Animation<TextureRegion> enemy4Idle;
    private Animation<TextureRegion> enemy4Walking;

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

    public Enemy(Vector2 position, int enemyType){
        this.TAG = "enemy";
        this.enemyType = enemyType;
        this.rightHandIKoffset = new Vector2(-50, 0); //how far from sprite center is the right hand
        this.leftHandIKoffset = new Vector2(50, 0);
        this.activateRange = new Circle(this.getPosition().x, this.getPosition().y, 1600);
        this.weapon = new MachineGun();
        this.weapon.parent = this;
        this.weapon.activeForRender = true;
        this.setPosition(position);
        this.health = 100;
        this.speed = 2.5F;
        this.activeForRender = true;
        this.activeCollision = true;
        this.setSprite(sprite);
        this.setCollider(new Rectangle(this.getPosition().x, this.getPosition().y, this.getSprite().getBoundingRectangle().width/2.5f, this.getSprite().getBoundingRectangle().height/1.5f));
        this.state = ENEMYSTATE.IDLE;
        this.checkRate = 5;
        this.shootCheckRate = 3;
        this.route = new ArrayList<Vector2>();
        this.direction = new Vector2().setZero();
        this.newDirection = new Vector2().setZero();
        this.targetPos = new Vector2().setZero();
        this.localPos = new Vector2().setZero();
        this.iteration = 0;
        enemy1Idle = new Animation<TextureRegion>(0.3f, enemy1IdleAtlas.getRegions());
        enemy2Idle = new Animation<TextureRegion>(0.3f, enemy2IdleAtlas.getRegions());
        enemy3Idle = new Animation<TextureRegion>(0.3f, enemy3IdleAtlas.getRegions());
        enemy4Idle = new Animation<TextureRegion>(0.3f, enemy4IdleAtlas.getRegions());
        enemy1Walking = new Animation<TextureRegion>(0.1f, enemy1WalkingAtlas.getRegions());
        enemy2Walking = new Animation<TextureRegion>(0.1f, enemy2WalkingAtlas.getRegions());
        enemy3Walking = new Animation<TextureRegion>(0.1f, enemy3WalkingAtlas.getRegions());
        enemy4Walking = new Animation<TextureRegion>(0.1f, enemy4WalkingAtlas.getRegions());
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
            if(shouldShoot()){
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
        this.setSprite(sprite);

        this.getSprite().setScale(8);
        this.getSprite().setPosition(this.getPosition().x, this.getPosition().y);
        this.getCollider().setPosition(this.getPosition());
        this.activateRange = new Circle(this.getPosition().x, this.getPosition().y, 1600);
//
        if (this.health <= 0) {
            this.activeCollision = false;
            this.activeForRender = false;
            this.weapon.activeForRender = false;
        }
    }

    @Override
    public void takeHit(float damage) {
        this.health -= damage;
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
        return newRand > 0.98f;
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
        System.out.println("thisCheck:"+thisCheck+" lastCheck:"+lastCheck+" checkrate:"+checkRate);
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
            System.out.println("RANDOM STATEs: "+stateChoice);
            Random rand = new Random();
            ENEMYSTATE newState = stateChoice.get(rand.nextInt(stateChoice.size()));
            lastCheck = thisCheck;
            System.out.println("STATE: " + newState);
            return newState;
        }
        else{
            System.out.println("STATE: " + state);
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
                region = enemy1Walking.getKeyFrame(stateTimer, true);
                break;
            case IDLE:
                region = enemy1Idle.getKeyFrame(stateTimer, true);
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
}
