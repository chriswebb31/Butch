package com.butch.game.gameobjects.Items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.ItemPickup;

public class MusketItem extends ItemPickup {
    public MusketItem(Vector2 position) {
        super(position);
        this.setSprite(new Sprite(ButchGame.assets.get(ButchGame.assets.musketSprite, Texture.class)));
        this.type = 0;
        this.id = 13;
        this.activeForRender = true;
        this.itemAnim = new Animation<TextureRegion>(0.5f, ButchGame.assets.get(ButchGame.assets.musketPickup, TextureAtlas.class).getRegions());
    }
}
