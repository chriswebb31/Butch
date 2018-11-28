package com.butch.game.gameobjects.Items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Item;

public class ShotgunAmmo extends Item {
    public ShotgunAmmo(Vector2 position) {
        super(position);
        this.id = 2;
        this.setSprite(new Sprite(ButchGame.assets.get(ButchGame.assets.shotgunAmmo, Texture.class)));
    }
}
