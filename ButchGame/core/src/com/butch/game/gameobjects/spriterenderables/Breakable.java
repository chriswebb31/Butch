package com.butch.game.gameobjects.spriterenderables;

import com.butch.game.gamemanagers.RenderableManager;
import com.butch.game.gameobjects.abstractinterface.Renderable;

public abstract class Breakable extends Renderable {
    public float health;

    public Breakable(){

    }

    @Override
    public void update() {
        if(this.health <= 0){
            System.out.println("broke");
            RenderableManager.renderableObjectsToRemove.add(this);
        }
        this.getSprite().setPosition(this.getPosition().x, this.getPosition().y);
    }

    @Override
    public void takeHit(float damage) {
        this.health -= damage;
    }
}
