package com.butch.game.components;

import com.badlogic.gdx.math.Rectangle;

public class Collider {
    private int width;
    private int height;
    private float x;
    private float y;
    private Rectangle boundingRectangle;

    public Collider(int width, int height, float x, float y){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;

        boundingRectangle = new Rectangle(this.x, this.y, this.width, this.height);
    }

    public Rectangle getBoundingRectangle() {
        return boundingRectangle;
    }
}
