package com.butch.game.gameobjects.abstractinterface;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class ItemPickup extends Renderable{
    public int id;
    public Circle collectionRange;
    public int type;
    public Sound collectionFX;

    public ItemPickup(Vector2 position){
        this.TAG = "item";
        this.setPosition(position);
        this.collectionRange = new Circle(this.getPosition().x, this.getPosition().y, 100);
    }

    @Override
    public void update(float delta) {
        if(activeForRender && getSprite()!=null){
            this.getSprite().setScale(10);
            this.getSprite().setPosition(this.getPosition().x, this.getPosition().y);
            this.setCollider(new Rectangle(this.getPosition().x, this.getPosition().y,this.getSprite().getWidth()/3, this.getSprite().getHeight() / 2));
        }
    }

    @Override
    public void takeHit(float damage) {

    }

    public void collected(){
        if(this.collectionFX != null){
            collectionFX.play();
        }
    }
}
