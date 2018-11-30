package com.butch.game.gameobjects.spriterenderables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.gamemanagers.RenderableManager;
import com.butch.game.gameobjects.abstractinterface.Renderable;

public class Bullet extends Renderable {
    private Vector2 velocity;
    private boolean freindly;
    private float damage;
    private float speed;

    private long startTime;

    public Bullet(Vector2 start, Vector2 velocity, float speed, float damage, boolean freindly) {
        this.setPosition(start);
        startTime = System.currentTimeMillis();
        this.velocity = velocity;
        this.speed = speed;
        this.damage = damage;
        this.freindly = freindly;
        this.setSprite(new Sprite(ButchGame.assets.get(ButchGame.assets.bulletSprite, Texture.class)));
        this.getSprite().setScale(10);
        this.setCollider(new Rectangle(this.getPosition().x, this.getPosition().y, this.getSprite().getWidth(), this.getSprite().getHeight()));
        this.activeForRender = true;
    }

    @Override
    public void update() {
        this.setPosition(new Vector2(this.getPosition().x + this.velocity.x * speed, this.getPosition().y + this.velocity.y * speed));
        this.getSprite().setPosition(this.getPosition().x, this.getPosition().y);
        this.getCollider().setPosition(this.getPosition());
        for (Renderable renderable: RenderableManager.renderableObjects) {
            if(renderable.getCollider().overlaps(this.getCollider())){
                if(renderable.TAG.equals("breakable") || renderable.TAG == "enemy" || (renderable.TAG=="player" && !this.freindly)){
                    renderable.takeHit(damage);
                    activeForRender = false;
                    System.out.println(renderable.TAG);
                }
            }
        }
        for(Rectangle collider : RenderableManager.mapColliders){
            if(collider.overlaps(this.getCollider())){
                activeForRender = false;
                destroy = true;
            }
        }
        if(System.currentTimeMillis() - startTime > 2000){
            activeForRender = false;
            destroy = true;
        }
    }

    @Override
    public void takeHit(float damage) {
        //Maybe you can shoot their rockets?
    }
}
