package com.butch.game.gameobjects.abstractinterface;

import com.badlogic.gdx.math.Vector2;

public abstract class Item extends ItemPickup{
    public int quantity;

    public Item(Vector2 position) {
        super(position);
    }
}
