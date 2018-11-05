package com.butch.game.gameobjects.abstractinterface;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Random;

public abstract class Enemy {
    public static ArrayList<Enemy> enemies;
    public Sprite sprite;
    public Vector2 position;
    public Vector2 playerTarget;
    public Vector2 moveTarget;
    public float moveRadius;
    public float speed;
    public float health;
    public float stamina;

    public Enemy(){
        if(enemies == null)
            enemies = new ArrayList<Enemy>();

        enemies.add(this);
    }

    public abstract void attack();

    public  abstract void createMove();

    public abstract void render(SpriteBatch spriteBatch);

    public static void update(SpriteBatch spriteBatch){
        for (Enemy enemy: enemies) {
            enemy.render(spriteBatch);
        }
    }

    public static Vector2 randomV2(float radius){
        float min = -radius;
        float max = radius;
        float x = (float) (Math.random()*((max-min)+1))+min;
        float y = (float) (Math.random()*((max-min)+1))+min;
        return new Vector2(x, y);
    }
}
