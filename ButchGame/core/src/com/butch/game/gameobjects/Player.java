package com.butch.game.gameobjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.components.Collider;
import com.butch.game.input.InputHandler;


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
    private Sprite playerSprite;

    //CHARACTER VARS
    private float speed;

    //MANAGERS
    private InputHandler IH;

    public Player(){
        position = Vector2.Zero;
        velocity = Vector2.Zero;
        speed = 0.0f;
    }

    public void render(){

    }

    public void setIH(InputHandler IH) {
        this.IH = IH;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Sprite getPlayerSprite() {
        return playerSprite;
    }

    public void setPlayerSprite(Sprite playerSprite) {
        this.playerSprite = playerSprite;
    }
}
