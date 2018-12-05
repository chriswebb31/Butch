package com.butch.game.gameobjects.spriterenderables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.audio.Sound;
import com.butch.game.ButchGame;
import com.butch.game.gamemanagers.RenderableManager;
import com.butch.game.gameobjects.abstractinterface.Renderable;

public class NPC extends Renderable {
    public float health;
    public Circle activateRange;
    private boolean interactActive;
    private Sound speak;
    private boolean hasSpoken = false;

    public NPC(Vector2 position) {
        this.TAG = "npc";
        this.activateRange = new Circle(this.getPosition().x, this.getPosition().y, 400);
        this.setPosition(position);
        this.health = 100;
        this.setSprite(new Sprite(ButchGame.assets.get(ButchGame.assets.cowboySprite, Texture.class)));
        this.getSprite().setScale(10);
        this.setCollider(new Rectangle(this.getPosition().x, this.getPosition().y, this.getSprite().getBoundingRectangle().width/2.5f, this.getSprite().getBoundingRectangle().height/1.5f));
        this.activeForRender = true;
        this.activeCollision = true;
        this.speak = ButchGame.assets.get(ButchGame.assets.revolverReloadEeffect, Sound.class);
    }

    @Override
    public void update(float delta) {
        if(!this.interactActive) {
            try{
                for(Renderable renderable: RenderableManager.renderableObjects){
                    if (renderable.TAG == "player") {
                        if (Intersector.overlaps(this.activateRange, renderable.getCollider())) {
                            this.interactActive = true;
                        }
                    }
                }
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }//CHECK IF SHOULD FIGHT
        else{
            try {
                if(!hasSpoken) {
                    speak.play();
                    hasSpoken = true;
                }
            }catch (NullPointerException e){
                e.printStackTrace();
            }

        }

        this.getSprite().setPosition(this.getPosition().x, this.getPosition().y);
        this.getCollider().setPosition(this.getPosition());
        this.activateRange = new Circle(this.getPosition().x, this.getPosition().y, 400);

        if(this.health <= 0) {
            this.activeCollision = false;
            this.activeForRender = false;
        }
    }

    @Override
    public void takeHit(float damage) {
        this.health -= damage;
    }

}
