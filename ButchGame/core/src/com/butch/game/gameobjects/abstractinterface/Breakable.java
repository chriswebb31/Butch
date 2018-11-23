package com.butch.game.gameobjects.abstractinterface;

import com.butch.game.gamemanagers.RenderableManager;

import java.util.ArrayList;

public abstract class Breakable extends Renderable {
    ArrayList<Breakable> breakables;
    public float health;

    public Breakable(){
        if(breakables == null){
            breakables = new ArrayList<Breakable>();
        }

        breakables.add(this);
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
        System.out.println("Health:" + health + "  Damage: " + damage );
    }
}
