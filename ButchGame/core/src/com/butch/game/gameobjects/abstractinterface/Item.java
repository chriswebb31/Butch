package com.butch.game.gameobjects.abstractinterface;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;

public abstract class Item extends ItemPickup {
    /*
        CLASS : ITEM

        Used to create items which can be picked up
     */
    public int quantity;
    public boolean autoPickup = false;

    public Item(Vector2 position) {
        super(position);
        this.type = 2;
    }



}