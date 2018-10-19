package com.butch.game.gameobjects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;


public class Player {
    private Vector2 position;
    private Vector2 velocity;

    private Sprite playerSprite;

    private float speed;

    public Player(){
        position = Vector2.Zero;
        velocity = Vector2.Zero;
        speed = 0.0f;
    }

    public void render(){

    }

}
