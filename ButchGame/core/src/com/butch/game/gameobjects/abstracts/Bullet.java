package com.butch.game.gameobjects.abstracts;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public abstract class Bullet {
    private static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    private Texture texture;
    private Vector2 directon;
    private  Vector2 velocity;
    private float speed;
    private float width;
    private float height;
    private Vector2 position;

    public Bullet(Vector2 start, Vector2 direction, float speed){
        bullets.add(this);
        this.position = start;
        this.directon = direction;
        this.speed = speed;
        this.velocity = new Vector2(direction.x * speed, direction.y * speed);
    }

    private void createCollision(){

    }

    private static void update(){
        for (Bullet bullet:bullets) {
            bullet.position = new Vector2(bullet.position.x + bullet.velocity.x, bullet.position.y + bullet.velocity.y);
        }
    }
}
