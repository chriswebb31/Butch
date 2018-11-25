package com.butch.game.gameobjects.abstractinterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.gamemanagers.RenderableManager;
import com.butch.game.gameobjects.spriterenderables.Player;

public abstract class ItemPickup extends Renderable{
    public Sprite icon;
    public Circle collectionRange;
    public boolean collectable = false;
    public int id;

    public ItemPickup(int id, Vector2 position){
        this.TAG = "item";
        this.id = id;
        this.setPosition(position);
    }

    @Override
    public void update() {
        for (Renderable renderableObject: RenderableManager.renderableObjects) {
            if(renderableObject.TAG == "player"){
                collectable = true;
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
