package com.butch.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.spriterenderables.Breakable;

public class Barrel extends Breakable {
    public Barrel(float x, float y) {
        health = 100;
        this.setPosition(new Vector2(x, y));
        this.setSprite(new Sprite(ButchGame.assets.get(ButchGame.assets.barrelSprite, Texture.class)));
        this.getSprite().setScale(5);
        this.setCollider(new Rectangle(x,y,this.getSprite().getBoundingRectangle().width/2, this.getSprite().getBoundingRectangle().height));
    }

    @Override
    public void takeHit(float damage){
        System.out.println("ded");
    }
}
