package com.butch.game.gameobjects.Breakables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Breakable;

public class Barrel extends Breakable {
    public Barrel(float x, float y) {
        health = 100;
        this.setPosition(new Vector2(x, y));
        this.setSprite(new Sprite(ButchGame.assets.get(ButchGame.assets.barrelSprite, Texture.class)));
        this.getSprite().setScale(5);
        this.setCollider(new Rectangle(x,y,this.getSprite().getBoundingRectangle().width/2, this.getSprite().getBoundingRectangle().height));
    }
}
