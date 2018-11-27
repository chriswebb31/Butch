package com.butch.game.gameobjects.Items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.ItemPickup;

public class ColtItem extends ItemPickup {
    public ColtItem(Vector2 position) {
        super(position);
        this.icon = new Sprite(ButchGame.assets.get(ButchGame.assets.coltSprite, Texture.class));
        this.type = 0;
        this.id = 0;
        System.out.println("created at: "+position);
    }
}
