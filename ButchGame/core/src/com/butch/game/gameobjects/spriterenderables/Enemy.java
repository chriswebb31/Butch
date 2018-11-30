package com.butch.game.gameobjects.spriterenderables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.gamemanagers.RenderableManager;
import com.butch.game.gameobjects.abstractinterface.Gun;
import com.butch.game.gameobjects.abstractinterface.Renderable;
import com.butch.game.gameobjects.weapons.MachineGun;

public class Enemy extends Renderable {
    public float health;
    public Gun weapon;
    public Player target;
    public Circle activateRange;
    public boolean combatActive = false;
    private Vector2 direction;
    private float speed;

    public Enemy(Vector2 position){
        this.TAG = "enemy";
        this.activateRange = new Circle(this.getPosition().x, this.getPosition().y, 400);
        this.weapon = new MachineGun();
        this.weapon.parent = this;
        this.weapon.activeForRender = true;
        this.setPosition(position);
        this.health = 100;
        this.setSprite(new Sprite(ButchGame.assets.get(ButchGame.assets.enemySprite, Texture.class)));
        this.getSprite().setScale(10);
        this.speed = 5;
        this.activeForRender = true;
        this.activeCollision = true;
        this.setCollider(new Rectangle(this.getPosition().x, this.getPosition().y, this.getSprite().getBoundingRectangle().width/2.5f, this.getSprite().getBoundingRectangle().height/1.5f));
    }

    @Override
    public void update() {
        System.out.println("Active for render? " + this.activeForRender);
        if(!this.combatActive) {
            try{
                for(Renderable renderable:RenderableManager.renderableObjects){
                    if (renderable.TAG == "player") {
                        if (Intersector.overlaps(this.activateRange, renderable.getCollider())) {
                            this.combatActive = true;
                            this.target = (Player) renderable;
                        }
                    }
                }
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }//CHECK IF SHOULD FIGHT
        else{
            try {
                direction = new Vector2(this.target.getPosition().x - this.getPosition().x, this.target.getPosition().y - this.getPosition().y).nor();
                this.setPosition(new Vector2(this.getPosition().x + this.direction.x * speed, this.getPosition().y + this.direction.y * speed));
            }catch (NullPointerException e){
                e.printStackTrace();
            }

        }
        this.getSprite().setPosition(this.getPosition().x, this.getPosition().y);
        this.getCollider().setPosition(this.getPosition());
        this.activateRange = new Circle(this.getPosition().x, this.getPosition().y, 400);

        if(this.health <= 0){
            this.activeForRender= false;
            this.activeCollision = false;
        }
    }

    @Override
    public void takeHit(float damage) {
        this.health -= damage;
    }

    public Vector2 aimDirection(){
        return direction;
    }
}
