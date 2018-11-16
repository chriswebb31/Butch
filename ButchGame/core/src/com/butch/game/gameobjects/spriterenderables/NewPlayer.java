package com.butch.game.gameobjects.spriterenderables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Renderable;
import com.butch.game.gameobjects.spriterenderables.abstracts.NewGun;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class NewPlayer extends Renderable {
    float xAxis, yAxis, speed = 0;

    private ArrayList<NewGun> gunInventory;
    private NewGun activeWeapon;
    private Iterator<NewGun> gunInvIterator;

    private ArrayList<Rectangle> mapColliders;
    private boolean canMove;
    private Vector2 velocity;

    private Vector2 leftHandIKoffset = new Vector2().setZero();
    private Vector2 rightHandIKoffset = new Vector2().setZero();

    private Rectangle intersector;

    public NewPlayer(Vector2 startPosition, ArrayList<Rectangle>mapStaticColliders){
        this.setPosition(startPosition);
        this.mapColliders = mapStaticColliders;

        this.setSprite(new Sprite(ButchGame.assets.get(ButchGame.assets.cowboySprite, Texture.class)));
        this.getSprite().setScale(10);

        this.velocity = new Vector2().setZero();
        this.canMove = true;
        this.speed = 10;

        this.gunInventory = new ArrayList<NewGun>();
        this.gunInventory.add(new CHOPPER(this));
        this.activeWeapon = this.gunInventory.get(0);
        this.gunInvIterator = this.gunInventory.iterator();

        this.rightHandIKoffset = new Vector2(-50, 0); //how far from sprite center is the right hand
        this.leftHandIKoffset = new Vector2(50, 0); //how far away from sprite center is the left hand

        this.setCollider(new Rectangle(this.getPosition().x, this.getPosition().y, this.getSprite().getBoundingRectangle().width/2.5f, this.getSprite().getBoundingRectangle().height/1.5f));
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
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            yAxis = 1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            yAxis = -1;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            xAxis = -1;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            xAxis = 1;
        }
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            try{
                activeWeapon.Shoot();
            } catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            try {
                if (gunInvIterator.hasNext()) {
                    activeWeapon = gunInvIterator.next();
                } else {
                    gunInvIterator = gunInventory.iterator();
                    activeWeapon = gunInventory.get(0);
                }
            } catch (NoSuchElementException w){
                w.printStackTrace();
            }
        }
    }

    private void movementHandler() {
        if (canMove) { //if player isnt blocked
            if (yAxis > 0) {
                velocity.y = 1;
            } else if (yAxis < 0) {
                velocity.y = -1;
            } else {
                velocity.y = 0;
            }
            if (xAxis > 0) {
                velocity.x = 1;
            } else if (xAxis < 0) {
                velocity.x = -1;
            } else {
                velocity.x = 0;
            }
            this.getCollider().setCenter(this.getPosition().x + velocity.x * speed, this.getPosition().y + velocity.y * speed);

            for (Rectangle staticCollider: mapColliders) {
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
            System.out.println("Velocity: " + velocity);

            //interpolate this for clean movement
            this.setPosition(new Vector2(this.getPosition().x + this.velocity.x * speed, this.getPosition().y + this.velocity.y * speed));
        }
    }

    private void flipHandler() {
        if (ButchGame.mousePosition().x >= this.getPosition().x) { // if direction is right
            this.getSprite().setFlip(false, false);
            activeWeapon.getSprite().setFlip(false, false);
        } else { //if direction is left or not right
            this.getSprite().setFlip(true, false);
            activeWeapon.getSprite().setFlip(false, true); //
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
            if(this.activeWeapon.oneHanded){
                pos = new Vector2(getPosition().x + leftHandIKoffset.x, getPosition().y + leftHandIKoffset.y);
            }
            else{
                pos = new Vector2(getPosition());
            }
        }
        else{
            if(this.activeWeapon.oneHanded){
                pos = new Vector2(getPosition().x + rightHandIKoffset.x, getPosition().y + rightHandIKoffset.y);
            }
            else{
                pos = new Vector2(getPosition());
            }
        }
        return pos;
    }

    public static float clamp(float val, float min, float max) { //simple clamp function
        return Math.max(min, Math.min(max, val));
    }

    @Override
    public void update() {
        inputHandler();
        movementHandler();
        flipHandler();

        System.out.println("position: " + this.getPosition());
        this.getSprite().setPosition(this.getPosition().x, this.getPosition().y);
    }
}
