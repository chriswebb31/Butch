package com.butch.game.gameobjects.abstractinterface;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class ItemPickup extends Renderable{
    public Sprite icon;
    public int id;

    public ItemPickup(int id){
        this.TAG = "ID:"+id;
    }

    @Override
    public void update() {

    }

    @Override
    public void takeHit(float damage) {

    }
}
