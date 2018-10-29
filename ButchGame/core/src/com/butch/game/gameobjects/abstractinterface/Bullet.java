package com.butch.game.gameobjects.abstractinterface;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.gameobjects.Player;

public abstract class Bullet {
    public Vector2 position = new Vector2();
    public Vector2 direction;
    public Vector2 velocity = new Vector2();
    public float speed = 100;
    public Sprite sprite;

    public abstract void update();

    public abstract void init(Vector2 start, Vector2 dir, Player player);


}