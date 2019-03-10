package com.butch.game.gameobjects.Items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Item;

public class MusketAmmo extends Item {
    public MusketAmmo(Vector2 position) {
        super(position);
        this.id = 3;
        this.setSprite(new Sprite(ButchGame.assets.get(ButchGame.assets.pistolAmmo, Texture.class)));
        this.quantity = 5;
        this.type = 2;
        this.activeForRender = true;
        this.itemAnim = new Animation<TextureRegion>(0.083f, ButchGame.assets.get(ButchGame.assets.coinSpin, TextureAtlas.class).getRegions());
    }
}