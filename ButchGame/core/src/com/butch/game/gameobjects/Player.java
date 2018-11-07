package com.butch.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Gun;
import com.butch.game.gameobjects.weapons.MachineGun;
import com.butch.game.gameobjects.weapons.Colt;
import com.butch.game.screens.GameScreen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class Player {
    /*
    CLASS : PLAYER

    this class holds all the information about the player object

    */
    public ArrayList<Bullet> playerAmmoInventory;
    public ArrayList <Bullet> playerBulletsFired; //need so that we can loop through and update them

    //POSITION VARS
    public Vector2 position; //player position used to update sprite pos
    private Vector2 velocity; //velocity is direction of input times speed scalar
    private float xAxis = 0; //x input  =  A | D
    private float yAxis = 0; //y input = W | S

    //ART VARS
    public Sprite sprite;

    //CHARACTER VARS
    private boolean canMove = true; //Possible reason to block input receiving
    private float speed;
    private ArrayList <Gun> gunInventory; //current weapons, will create weapon cycle function
    private Iterator <Gun> gunInvIterator;

    public Gun activeWeapon;
    private Vector2 leftHandIKoffset = new Vector2().setZero();
    private Vector2 rightHandIKoffset = new Vector2().setZero();
    public Rectangle playerCollider;
    public Rectangle intersector;
    //MANAGERS
    private static GameScreen gameScreen;

    public Player(GameScreen gameScreen) {
        Player.gameScreen = gameScreen;
        this.position = Vector2.Zero; //PLAYER COORDINATES set to 0,0
        this.velocity = Vector2.Zero; // PLAYER DIRECTION AND SPEED set to 0,0
        this.speed = 5.0f; //PLAYER SPEED MODIFIER
        this.sprite = new Sprite(ButchGame.assets.get(ButchGame.assets.cowboySprite, Texture.class)); //GET ASSETS FROM ASSET MANAGER
        this.sprite.setScale(10); //mulitply sprite size by 10 as larger numbers are easier to deal with / could move camera down and scale movemmen?
        this.playerCollider = new Rectangle(this.sprite.getOriginX(), this.sprite.getOriginY(), this.sprite.getBoundingRectangle().width / 3, this.sprite.getBoundingRectangle().height / 1.5f);
        this.gunInventory = new ArrayList <Gun> (); //clear player weapons
        this.gunInventory.add(new MachineGun(this, gameScreen)); //give player a new machine gun
        this.gunInventory.add(new Colt(this, gameScreen));
        this.gunInventory.add(new MachineGun(this, gameScreen)); //give player a new colt


        this.activeWeapon = gunInventory.get(0); //revolver as nothing else in array
        this.gunInvIterator = gunInventory.iterator();
        this.rightHandIKoffset = new Vector2(-50, 0); //how far from sprite center is the right hand
        this.leftHandIKoffset = new Vector2(50, 0); //how far away from sprite center is the left hand
        this.playerBulletsFired = new ArrayList <Bullet> (); // set bullet list to empty
    }

    public void update(float delta) {
        inputHandler(); //GET NEW INPUT EVENTS RELATED TO THIS CLASS
        movementHandler(); //UPDATE PLAYER VELOCITY AND POSITION
        flipHandler(); //USE VELOCITY TO DECIDE ON DIRECTION


        sprite.setPosition(position.x, position.y); // after updating local position, apply to sprite
        this.playerCollider.setPosition(sprite.getBoundingRectangle().x + (sprite.getBoundingRectangle().width/3), sprite.getBoundingRectangle().y + (sprite.getBoundingRectangle().height/4.5f));
        activeWeapon.Update(); //move hand
        for (Bullet bullet: this.playerBulletsFired) { //for all fired bullets update them
            bullet.update(gameScreen.batch);
        }
    }

    private void flipHandler() {
        if (ButchGame.mousePosition().x >= this.position.x) { // if direction is right
            sprite.setFlip(false, false);
            activeWeapon.gunSprite.setFlip(false, false);
        } else { //if direction is left or not right
            sprite.setFlip(true, false);
            activeWeapon.gunSprite.setFlip(false, true); //
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

            for (Rectangle rectangle:gameScreen.getColliders()) {
                if (playerCollider.overlaps(rectangle)){
                    intersector = new Rectangle();
                    Intersector.intersectRectangles(playerCollider,rectangle,intersector);
                    if(intersector.x > playerCollider.x){
                        //Intersects with right side
                        velocity.x = clamp(velocity.x, -1, 0);
                    }
                    if(intersector.y > playerCollider.y){
                            //Intersects with top side
                        velocity.y = clamp(velocity.y, -1, 0);
                    }
                    if(intersector.x + intersector.width < playerCollider.x + playerCollider.width) {
                        //Intersects with left side
                        velocity.x = clamp(velocity.x, 0, 1);
                    }
                    if(intersector.y + intersector.height < playerCollider.y + playerCollider.height){
                        velocity.x = clamp(velocity.y, 0, 1);
                    }
                }
            }

            this.position = new Vector2(this.position.x + velocity.x * speed, this.position.y + velocity.y * speed); //velocity add to current position, to simulate movement

        }
    }

    public static float clamp(float val, float min, float max) {
        return Math.max(min, Math.min(max, val));
    }

    private void inputHandler() {

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
            activeWeapon.Shoot();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
            try {
                if (gunInvIterator.hasNext()) {
                    System.out.println(gunInvIterator.next());
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

    public Vector2 getPosition() {
        return position;
    }

   public Vector2 weaponPosition(){
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

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getAimDirection() {
        /*
            Determining a Vector Given Two Points

            PQ→=(xQ−xP,yQ−yP)

         */
        Vector2 aimDirection = new Vector2(ButchGame.mousePosition().x - this.position.x, ButchGame.mousePosition().y - this.position.y);
        return aimDirection;
    }
}