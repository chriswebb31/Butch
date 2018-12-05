package com.butch.game.gameobjects.spriterenderables;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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

    public ArrayList<Vector2> route;
    public Vector2 targetPos;
    int iteration;

    public ENEMYSTATE state;
    private long lastCheck;
    private int checkRate;


    public Vector2 newDirection;

    public Enemy(Vector2 position){
        this.TAG = "enemy";
        this.rightHandIKoffset = new Vector2(-50, 0); //how far from sprite center is the right hand
        this.leftHandIKoffset = new Vector2(50, 0);
        this.activateRange = new Circle(this.getPosition().x, this.getPosition().y, 400);
        this.weapon = new MachineGun();
        this.weapon.parent = this;
        this.weapon.activeForRender = true;
        this.setPosition(position);
        this.health = 100;
        this.setSprite(new Sprite(ButchGame.assets.get(ButchGame.assets.enemySprite, Texture.class)));
        this.getSprite().setScale(10);
        this.speed = 2.5F;
        this.activeForRender = true;
        this.activeCollision = true;
        this.setCollider(new Rectangle(this.getPosition().x, this.getPosition().y, this.getSprite().getBoundingRectangle().width/2.5f, this.getSprite().getBoundingRectangle().height/1.5f));
        this.state = ENEMYSTATE.IDLE;
        this.checkRate = 5;
        this.route = new ArrayList<Vector2>();
        this.newDirection = new Vector2().setZero();
        this.targetPos = new Vector2().setZero();
        this.iteration = 0;
    }

    @Override
    public void update(float delta ) {
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

                System.out.println("POS: " + getPosition() + " TARGETPOS: " + targetPos + " DISTANCE: " + (Math.hypot(posx - tarx, posy -tary)));
                System.out.println("ROUTE: " + route);
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
                System.out.println("New Direction : " +newDirection);
                break;
            case CHASE:
                if(!(this.target == null)){
                    newDirection = new Vector2(this.target.getPosition().x - this.getPosition().x, this.target.getPosition().y - this.getPosition().y).nor();
                }
                break;
            case PICKSPOT:
                break;
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
        this.getSprite().setPosition(this.getPosition().x, this.getPosition().y);
        this.getCollider().setPosition(this.getPosition());
        this.activateRange = new Circle(this.getPosition().x, this.getPosition().y, 400);
//
//        if (this.health <= 0) {
//            this.activeCollision = false;
//            this.activeForRender = false;
//            this.weapon.activeForRender = false;
//        }
    }

    @Override
    public void takeHit(float damage) {
        this.health -= damage;
    }

    public Vector2 aimDirection(){
        return direction;
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
        if((thisCheck - lastCheck) >= (long) (checkRate * 1000)){
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
            Sound fx = ButchGame.assets.get(ButchGame.assets.coinCollection, Sound.class);
            fx.play();
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
}
