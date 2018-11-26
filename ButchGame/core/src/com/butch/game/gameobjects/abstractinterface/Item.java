package com.butch.game.gameobjects.abstractinterface;

import com.badlogic.gdx.math.Vector2;

public abstract class Item extends ItemPickup{

    public Item(int id, Vector2 position) {
        super(id, position);
        this.id = id;
    }
}
