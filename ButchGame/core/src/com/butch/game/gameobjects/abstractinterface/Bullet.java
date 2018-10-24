package com.butch.game.gameobjects.abstractinterface;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public interface Bullet {
    public static ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    public void update();

    public void init(Vector2 start, Vector2 dir);

}
