package com.butch.game.gameobjects.spriterenderables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.SpriteRenderable;
import com.butch.game.gameobjects.spriterenderables.abstracts.NewGun;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class NewPlayer extends SpriteRenderable {
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
        this.position = startPosition;
        this.mapColliders = mapStaticColliders;

        this.sprite = new Sprite(ButchGame.assets.get(ButchGame.assets.cowboySprite, Texture.class));
        this.sprite.setScale(10);

        this.velocity = new Vector2().setZero();
        this.canMove = true;
        this.speed = 10;

        this.gunInventory = new ArrayList<NewGun>();
        this.gunInventory.add(new CHOPPER(this));
        this.activeWeapon = this.gunInventory.get(0);
        this.gunInvIterator = this.gunInventory.iterator();

        this.rightHandIKoffset = new Vector2(-50, 0); //how far from sprite center is the right hand
        this.leftHandIKoffset = new Vector2(50, 0); //how far away from sprite center is the left hand

        this.collider = new Rectangle(position.x, position.y, this.sprite.getBoundingRectangle().width/2.5f, this.sprite.getBoundingRectangle().height/1.5f);
    }

    @Override
    public void update(float delta) {
        inputHandler();
        movementHandler();
        flipHandler();

        System.out.println("position: "+position);
        this.sprite.setPosition(this.position.x, this.position.y);
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
//        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
//            try{
//                activeWeapon.Shoot();
//            } catch (NullPointerException e){
//                e.printStackTrace();
//            }
//        }
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
            this.collider.setCenter(position.x + velocity.x * speed, position.y + velocity.y * speed);

            for (Rectangle staticCollider: mapColliders) {
                if(collider.overlaps(staticCollider)){
                    intersector = new Rectangle();
                    Intersector.intersectRectangles(collider, staticCollider, intersector);
                    if(intersector.x > collider.x){
                        //Intersects with right side
                        velocity.x = clamp(velocity.x, -1, 0.001f);
                    }
                    else if(intersector.x + intersector.width < collider.x + collider.width) {
                        //Intersects with left side
                        velocity.x = clamp(velocity.x, 0.001f, 1);
                    }

                    if(intersector.y > collider.y){
                        //Intersects with top side
                        velocity.y = clamp(velocity.y, -1, 0.001f);
                    }
                    else if(intersector.y + intersector.height < collider.y + collider.height){
                        //intersects with bottom side
                        velocity.y = clamp(velocity.y, 0.001f, 1);
                    }
                }
            }
            System.out.println("Velocity: " + velocity);

            //interpolate this for clean movement
            this.position = new Vector2(this.position.x + this.velocity.x * speed, this.position.y + this.velocity.y * speed);
        }
    }

    private void flipHandler() {
        if (ButchGame.mousePosition().x >= this.position.x) { // if direction is right
            this.sprite.setFlip(false, false);
            activeWeapon.sprite.setFlip(false, false);
        } else { //if direction is left or not right
            this.sprite.setFlip(true, false);
            activeWeapon.sprite.setFlip(false, true); //
        }
    }

    public Vector2 getAimDirection() {
        /*
            Determining a Vector Given Two Points

            PQ→=(xQ−xP,yQ−yP)

         */
        Vector2 aimDirection = new Vector2(ButchGame.mousePosition().x - this.position.x, ButchGame.mousePosition().y - this.position.y);
        return aimDirection;
    }
    public Vector2 getWeaponPosition(){
        Vector2 pos;
        if(ButchGame.mousePosition().x >= position.x){
            if(this.activeWeapon.oneHanded){
                pos = new Vector2(position.x + leftHandIKoffset.x, position.y + leftHandIKoffset.y);
            }
            else{
                pos = new Vector2(position);
            }
        }
        else{
            if(this.activeWeapon.oneHanded){
                pos = new Vector2(position.x + rightHandIKoffset.x, position.y + rightHandIKoffset.y);
            }
            else{
                pos = new Vector2(position);
            }
        }
        return pos;
    }

    public static float clamp(float val, float min, float max) { //simple clamp function
        return Math.max(min, Math.min(max, val));
    }

}
