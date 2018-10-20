package com.butch.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.components.Collider;
import com.butch.game.screens.GameScreen;


public class Player {
    //COLLISION DETECTION
    private Collider TCollider;
    private Collider BCollider;
    private Collider LCollider;
    private Collider RCollider;

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
        this.gameScreen = gameScreen;
        this.position = Vector2.Zero; //PLAYER COORDINATES
        this.velocity = Vector2.Zero; // PLAYER DIRECTION AND SPEED
        this.speed = 5.0f; //PLAYER SPEED MODIFIER
        this.sprite = new Sprite(gameScreen.game.assets.get(gameScreen.game.assets.cowboySprite, Texture.class));
        this.sprite.setScale(10);

//        gameScreen.game.CM.addCollider(TCollider); //FOR DISABLING POSITIVE Y AXIS
//        gameScreen.game.CM.addCollider(BCollider); //FOR DISABLING NEGATIVE Y AXIS
//        gameScreen.game.CM.addCollider(LCollider); //FOR DISABLING POSITIVE X AXIS
//        gameScreen.game.CM.addCollider(RCollider); //FOR DISABLING NEGATIVE X AXIS
    }

    public void update(){
        inputHandler();
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

            this.position = new Vector2(this.position.x + velocity.x * speed, this.position.y + velocity.y * speed);
        }
        sprite.setPosition(position.x, position.y);
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
