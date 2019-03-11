package com.butch.game.gameobjects.Items;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Item;

public class LevelUpItem extends Item {
    public LevelUpItem(Vector2 position) {
        super(position);
        this.id = 7;
        this.setSprite(new Sprite(ButchGame.assets.get(ButchGame.assets.levelUpSprite, Texture.class)));
        this.quantity = 1;
        autoPickup = true;
        this.activeForRender = true;
        this.collectionFX = ButchGame.assets.get(ButchGame.assets.coinCollection, Sound.class);
        this.itemAnim = new Animation<TextureRegion>(0.5f, ButchGame.assets.get(ButchGame.assets.lvlItem, TextureAtlas.class).getRegions());
    }
}
