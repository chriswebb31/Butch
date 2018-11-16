package com.butch.game.gameobjects.abstractinterface;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Renderable {
    private Sprite sprite;
    private Vector2 position;
    private Rectangle collider;

    public Renderable(){
        this.position = new Vector2();
        RenderableManager.renderableObjects.add(this);
        this.collider = new Rectangle();
    }

    public abstract void update();

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Rectangle getCollider() {
        return collider;
    }

    public void setCollider(Rectangle collider) {
        this.collider = collider;
    }
}
