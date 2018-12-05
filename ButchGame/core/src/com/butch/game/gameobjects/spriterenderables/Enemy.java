package com.butch.game.gameobjects.spriterenderables;

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
    Iterator itr;

    public ENEMYSTATE state;
    private long lastCheck;
    private int checkRate;


    public Enemy(Vector2 position){
        this.TAG = "enemy";
        this.rightHandIKoffset = new Vector2(-50, 0); //how far from sprite center is the right hand
        this.leftHandIKoffset = new Vector2(50, 0);
        this.activateRange = new Circle(this.getPosition().x, this.getPosition().y, 400);
        this.weapon = new Shotgun();
        this.weapon.parent = this;
        this.weapon.activeForRender = true;
        this.setPosition(position);
        this.health = 100;
        this.setSprite(new Sprite(ButchGame.assets.get(ButchGame.assets.enemySprite, Texture.class)));
        this.getSprite().setScale(10);
        this.speed = 5;
        this.activeForRender = true;
        this.activeCollision = true;
        this.setCollider(new Rectangle(this.getPosition().x, this.getPosition().y, this.getSprite().getBoundingRectangle().width/2.5f, this.getSprite().getBoundingRectangle().height/1.5f));
        this.state = ENEMYSTATE.IDLE;
        this.checkRate = 5;
        this.route = new ArrayList<Vector2>();
        itr = route.iterator();
    }

    @Override
    public void update(float delta ) {
        switch (getState()){
            case IDLE:
                break;
            case ROUTE:
                if(this.getPosition() == this.targetPos){
                    if(itr.hasNext()){
                        targetPos = (Vector2) itr.next();
                    }
                    else{
                        itr = route.iterator();
                        targetPos = (Vector2) itr.next();
                    }
                }
                break;
            case CHASE:
                break;
            case PICKSPOT:

        }


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
}
