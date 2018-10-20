package com.butch.game.gameobjects;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.components.Collider;
import com.butch.game.gamemanagers.ColliderManager;
import com.butch.game.input.InputHandler;
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

    //ART VARS
    public Sprite sprite;

    //CHARACTER VARS
    private float speed;

    //MANAGERS
    private static InputHandler IH;
    private static ColliderManager CM;

    public Player(GameScreen gameScreen){
        this.position = Vector2.Zero; //PLAYER COORDINATES
        this.velocity = Vector2.Zero; // PLAYER DIRECTION AND SPEED
        this.speed = 0.0f; //PLAYER SPEED MODIFIER
        this.sprite = new Sprite(gameScreen.game.assets.get(gameScreen.game.assets.cowboySprite, Texture.class));
        this.sprite.setScale(4);
//        this.IH = IH; //INPUT HANDLER FOR CONTROLS
//        this.CM = CM; //COLLIDER MANAGER FOR INHERITANCE

//        CM.addCollider(TCollider); //FOR DISABLING POSITIVE Y AXIS
//        CM.addCollider(BCollider); //FOR DISABLING NEGATIVE Y AXIS
//        CM.addCollider(LCollider); //FOR DISABLING POSITIVE X AXIS
//        CM.addCollider(RCollider); //FOR DISABLING NEGATIVE X AXIS
    }

    public static void setIH(InputHandler IH) {
        Player.IH = IH;
    }

    public static void setCM(ColliderManager CM) {
        Player.CM = CM;
    }

    public void render(){

    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
}
