package com.butch.game.gameobjects.spriterenderables.abstracts;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Renderable;

public class NewBullet extends Renderable {
    private Vector2 velocity;
    private boolean freindly;
    private float damage;
    private float speed;

    public NewBullet(Vector2 start, Vector2 velocity, float speed) {
        this.setPosition(start);
        this.velocity = velocity;
        this.speed = speed;
        this.setSprite(new Sprite(ButchGame.assets.get(ButchGame.assets.bulletSprite, Texture.class)));
        this.getSprite().setScale(10);
    }

    @Override
    public void update() {
        this.setPosition(new Vector2(this.getPosition().x + this.velocity.x * speed, this.getPosition().y + this.velocity.y * speed));
        this.getSprite().setPosition(this.getPosition().x, this.getPosition().y);
    }
//    public NewBullet(Vector2 start, Vector2 velocity, boolean freindly, float damage){
//        if(newBullets == null){
//            newBullets = new ArrayList<NewBullet>();
//        }
//
//        System.out.println("POW!");
//        this.velocity = velocity;
//        this.position = start;
//        this.freindly = freindly;
//        this.damage = damage;
//        this.sprite = new Sprite(ButchGame.assets.get(ButchGame.assets.bulletSprite, Texture.class));
//        this.collider =  new Rectangle(this.position.x, this.position.y, this.sprite.getWidth(), this.sprite.getHeight());
//        newBullets.add(this);
//    }

//    public void update(float delta) {
//        System.out.println("POW!");
//        this.position = new Vector2(this.position.x+ this.velocity.x, this.position.y + this.velocity.y);
//    }
}
