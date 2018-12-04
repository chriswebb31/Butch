package com.butch.game.gameobjects.Items;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Item;

public class CoinItem extends Item {
    public CoinItem(Vector2 position) {
        super(position);
        this.id = 3;
        this.setSprite(new Sprite(ButchGame.assets.get(ButchGame.assets.coinItemSprite, Texture.class)));
        this.quantity = 1;
        autoPickup = true;
        this.activeForRender = true;
        this.collectionFX = ButchGame.assets.get(ButchGame.assets.coinCollection, Sound.class);
    }

}
