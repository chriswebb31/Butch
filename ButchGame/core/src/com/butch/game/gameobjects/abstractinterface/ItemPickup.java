package com.butch.game.gameobjects.abstractinterface;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class ItemPickup extends Renderable{
    public int id;
    public Sprite icon;
    public Circle collectionRange;
    public int type;

    private Rectangle intersector;

    public ItemPickup(Vector2 position){
        this.TAG = "item";
        this.setPosition(position);
        this.collectionRange = new Circle(this.getPosition().x, this.getPosition().y, 100);
    }

    @Override
    public void update() {
        this.setSprite(icon);
        this.getSprite().setScale(10);
        this.getSprite().setPosition(this.getPosition().x, this.getPosition().y);
    }

    @Override
    public void takeHit(float damage) {

    }
}
