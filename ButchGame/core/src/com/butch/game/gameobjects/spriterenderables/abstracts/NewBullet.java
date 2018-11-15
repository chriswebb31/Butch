package com.butch.game.gameobjects.spriterenderables.abstracts;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.SpriteRenderable;

public class NewBullet extends SpriteRenderable {
    private Vector2 velocity;
    private boolean freindly;
    private float damage;

    public NewBullet(Vector2 start, Vector2 velocity, boolean freindly, float damage){
        this.velocity = velocity;
        this.position = start;
        this.freindly = freindly;
        this.damage = damage;
        this.sprite = new Sprite(ButchGame.assets.get(ButchGame.assets.bulletSprite, Texture.class));
        this.collider =  new Rectangle(this.position.x, this.position.y, this.sprite.getWidth(), this.sprite.getHeight());
    }

    @Override
    public void update(float delta) {
        this.position = new Vector2(this.position.x+ this.velocity.x, this.position.y + this.velocity.y);
    }
}
