package com.butch.game.gameobjects.Items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Item;

public class PistolAmmo extends Item {
    public PistolAmmo(Vector2 position) {
        super(position);
        this.id = 0;
        this.setSprite(new Sprite(ButchGame.assets.get(ButchGame.assets.pistolAmmo, Texture.class)));
        this.quantity = 50;
        this.type = 2;
        this.activeForRender = true;
    }
}
