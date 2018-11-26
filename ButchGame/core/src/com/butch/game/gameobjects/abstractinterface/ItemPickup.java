package com.butch.game.gameobjects.abstractinterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.gamemanagers.RenderableManager;
import com.butch.game.gameobjects.spriterenderables.Player;

public abstract class ItemPickup extends Renderable{
    public int id;
    public Sprite icon;
    public Circle collectionRange;
    public boolean collectable = false;
    public int type;
    public Class itemClass;

    private Rectangle intersector;

    public ItemPickup(Vector2 position){
        this.TAG = "item";
        this.setPosition(position);
        this.collectionRange = new Circle(this.getPosition().x, this.getPosition().y, 5);
    }

    @Override
    public void update() {
        for (Renderable renderableObject: RenderableManager.renderableObjects) {
            if(renderableObject.TAG == "player"){
                intersector = new Rectangle();
                if(Intersector.overlaps(this.collectionRange, renderableObject.getCollider())){
                    collectable = true;
                } else{
                    collectable = false;
                }

            }
        }
        if(collectable && Gdx.input.isKeyPressed(Input.Keys.E)){
            Player.addItem(this);
        }
    }

    @Override
    public void takeHit(float damage) {

    }
}
