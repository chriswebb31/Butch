package com.butch.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.components.Collider;
import com.butch.game.screens.GameScreen;


public class Player {
    //COLLISION DETECTION
    public Collider TCollider;
    public Collider BCollider;
    public Collider LCollider;
    public Collider RCollider;
    private Vector2 topOffset = new Vector2().setZero();
    private Vector2 bottomOffset = new Vector2().setZero();
    private Vector2 leftOffset = new Vector2().setZero();
    private Vector2 rightOffset = new Vector2().setZero();

    //POSITION VARS
    private Vector2 position;
    private Vector2 velocity;
    private float xAxis = 0;
    private float yAxis = 0;

    //ART VARS
    public Sprite sprite;

    //CHARACTER VARS
    private boolean canMove = true;
    private float speed;

    //MANAGERS
    private static GameScreen gameScreen;

    public Player(GameScreen gameScreen){
        Player.gameScreen = gameScreen;
        this.position = Vector2.Zero; //PLAYER COORDINATES
        this.velocity = Vector2.Zero; // PLAYER DIRECTION AND SPEED
        this.speed = 5.0f; //PLAYER SPEED MODIFIER
        this.sprite = new Sprite(ButchGame.assets.get(ButchGame.assets.cowboySprite, Texture.class)); //GET ASSETS FROM ASSET MANAGER
        this.sprite.setScale(10);
        this.TCollider = new Collider(100,50, this.position.x + topOffset.x, this.position.y + topOffset.y);
        this.BCollider = new Collider(100,50, this.position.x + bottomOffset.x, this.position.y + bottomOffset.y);
        this.LCollider = new Collider(50,100, this.position.x + leftOffset.x, this.position.y + leftOffset.y);
        this.RCollider = new Collider(50,100, this.position.x + rightOffset.x, this.position.y + rightOffset.y);
        this.topOffset = new Vector2(-35, 100); //MOVE COLLIDER TO SUITABLE LOCATION
        this.bottomOffset = new Vector2(-35, -100);
        this.leftOffset = new Vector2(-50, -20);
        this.rightOffset = new Vector2(35, -20);

        ButchGame.CM.addCollider(TCollider); //FOR DISABLING POSITIVE Y AXIS
        ButchGame.CM.addCollider(BCollider); //FOR DISABLING NEGATIVE Y AXIS
        ButchGame.CM.addCollider(LCollider); //FOR DISABLING POSITIVE X AXIS
        ButchGame.CM.addCollider(RCollider); //FOR DISABLING NEGATIVE X AXIS
    }

    public void update(){
        inputHandler();
        movementHandler();
        sprite.setPosition(position.x, position.y);
    }
    private void movementHandler(){
        if(canMove){
            if(yAxis > 0){
                velocity.y = 1;
            }
            else if(yAxis < 0){
                velocity.y = -1;
            } else{
                velocity.y = 0;
            }
            if(xAxis > 0){
                velocity.x = 1;
            }
            else if(xAxis < 0){
                velocity.x = -1;
            } else{
                velocity.x = 0;
            }

            //Collision management
            if(TCollider.isColliding()){
                velocity.y = clamp(velocity.y, -100, 0);
            }
            if(BCollider.isColliding()){
                velocity.y = clamp(velocity.y, 0, 100);
            }
            if(LCollider.isColliding()){
                velocity.x = clamp(velocity.x, 0, 100);
            }
            if(RCollider.isColliding()){
                velocity.x = clamp(velocity.x, -100, 0);
            }

            this.position = new Vector2(this.position.x + velocity.x * speed, this.position.y + velocity.y * speed);
            TCollider.getBoundingRectangle().x = this.position.x + topOffset.x;
            TCollider.getBoundingRectangle().y = this.position.y + topOffset.y;

            BCollider.getBoundingRectangle().x = this.position.x + bottomOffset.x;
            BCollider.getBoundingRectangle().y = this.position.y + bottomOffset.y;

            LCollider.getBoundingRectangle().x = this.position.x + leftOffset.x;
            LCollider.getBoundingRectangle().y = this.position.y + leftOffset.y;

            RCollider.getBoundingRectangle().x = this.position.x + rightOffset.x;
            RCollider.getBoundingRectangle().y = this.position.y + rightOffset.y;
        }
    }

    private float clamp(float val, float min, float max){
        return Math.max(min, Math.min(max, val));
    }
    private void inputHandler(){
        if(!Gdx.input.isKeyPressed(Input.Keys.D)){
            xAxis = 0;
        }
        if(!Gdx.input.isKeyPressed(Input.Keys.W)){
            yAxis = 0;
        }
        if(!Gdx.input.isKeyPressed(Input.Keys.S)){
            yAxis = 0;
        }
        if(!Gdx.input.isKeyPressed(Input.Keys.A)){
            xAxis = 0;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            yAxis = 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            yAxis = -1;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            xAxis = -1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            xAxis = 1;
        }


        System.out.println(xAxis + " " + yAxis);
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
