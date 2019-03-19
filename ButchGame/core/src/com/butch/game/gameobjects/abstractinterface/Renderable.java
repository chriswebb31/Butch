package com.butch.game.gameobjects.abstractinterface;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.gamemanagers.RenderableManager;

public abstract class Renderable {
    /*
    CLASS : RENDERABLE

    Used to create anything which is to be rendered on screen
     */
    public boolean activeForRender = false;
    public boolean destroy = false;
    public boolean activeCollision = false;
    public String TAG = this.getClass().getName();
    private Sprite sprite;
    private Vector2 position;
    private Rectangle collider;

    public Renderable(){
        this.position = new Vector2();
        this.collider = new Rectangle();
        RenderableManager.renderableObjects.add(this);
    }

    public abstract void update(float delta);

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
        this.sprite.setSize(this.sprite.getWidth(), this.sprite.getHeight());
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

    public abstract void takeHit(float damage);

}
