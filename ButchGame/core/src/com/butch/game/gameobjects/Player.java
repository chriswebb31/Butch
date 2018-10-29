package com.butch.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Bullet;
import com.butch.game.gameobjects.abstractinterface.Weapon;
import com.butch.game.gameobjects.weapons.Revolver;
import com.butch.game.screens.GameScreen;

import java.util.ArrayList;


public class Player {
    /*
    CLASS : PLAYER

    this class holds all the information about the player object

    */
    public ArrayList < Bullet > playerBullets; //need so that we can loop through and update them

    //POSITION VARS
    private Vector2 position; //player position used to update sprite pos
    private Vector2 velocity; //velocity is direction of input times speed scalar
    private float xAxis = 0; //x input  =  A | D
    private float yAxis = 0; //y input = W | S

    //ART VARS
    public Sprite sprite;

    //CHARACTER VARS
    private boolean canMove = true; //Possible reason to block input receiving
    private float speed;
    private ArrayList < Weapon > weaponInventory; //current weapons, will create weapon cycle function
    public Weapon activeWeapon;
    private Vector2 leftHandIKoffset = new Vector2().setZero();
    private Vector2 rightHandIKoffset = new Vector2().setZero();

    //MANAGERS
    private static GameScreen gameScreen;

    public Player(GameScreen gameScreen) {
        Player.gameScreen = gameScreen;
        this.position = Vector2.Zero; //PLAYER COORDINATES set to 0,0
        this.velocity = Vector2.Zero; // PLAYER DIRECTION AND SPEED set to 0,0
        this.speed = 5.0f; //PLAYER SPEED MODIFIER
        this.sprite = new Sprite(ButchGame.assets.get(ButchGame.assets.cowboySprite, Texture.class)); //GET ASSETS FROM ASSET MANAGER
        this.sprite.setScale(10); //mulitply sprite size by 10 as larger numbers are easier to deal with / could move camera down and scale movemmen?


        this.weaponInventory = new ArrayList < Weapon > (); //clear player weapons
        this.weaponInventory.add(new Revolver(this)); //give player a new gun


        this.activeWeapon = weaponInventory.get(0); //revolver as nothing else in array
        this.rightHandIKoffset = new Vector2(-50, 0); //how far from sprite center is the right hand
        this.leftHandIKoffset = new Vector2(50, 0); //how far away from sprite center is the left hand
        this.playerBullets = new ArrayList < Bullet > (); // set bullet list to empty
    }

    public void update() {
        inputHandler(); //GET NEW INPUT EVENTS RELATED TO THIS CLASS
        movementHandler(); //UPDATE PLAYER VELOCITY AND POSITION
        flipHandler(); //USE VELOCITY TO DECIDE ON DIRECTION

        sprite.setPosition(position.x, position.y); // after updating local position, apply to sprite
        activeWeapon.updatePosition(new Vector2(ButchGame.mousePosition().x, ButchGame.mousePosition().y)); //move hand
        activeWeapon.updateRotation(new Vector2(ButchGame.mousePosition().x, ButchGame.mousePosition().y)); //rotate weapon sprite
        for (Bullet bullet: this.playerBullets) { //for all fired bullets update them
            bullet.update();
        }
    }

    private void flipHandler() {
        if (ButchGame.mousePosition().x >= this.position.x) { // if direction is right
            sprite.setFlip(false, false);
            activeWeapon.sprite.setFlip(false, false);
        } else { //if direction is left or not right
            sprite.setFlip(true, false);
            activeWeapon.sprite.setFlip(false, true); //
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

            this.position = new Vector2(this.position.x + velocity.x * speed, this.position.y + velocity.y * speed); //velocity add to current position, to simulate movement
        }
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
            activeWeapon.Attack();
            System.out.print("CLIP: " + this.activeWeapon.clip + " ");
            System.out.print("RESERVE: " + this.activeWeapon.reserve);
        }
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 leftHandIK() { //vector 2 method returns vector 2 value on where left hand currently is for weapon
        float x = this.position.x;
        float y = this.position.y;
        return new Vector2(x - leftHandIKoffset.x, y - leftHandIKoffset.y);
    }

    public Vector2 rightHandIK() { //vector 2 method returns vector 2 value on where right hand currently is for weapon
        float x = this.position.x;
        float y = this.position.y;
        return new Vector2(x - rightHandIKoffset.x, y - rightHandIKoffset.y);
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