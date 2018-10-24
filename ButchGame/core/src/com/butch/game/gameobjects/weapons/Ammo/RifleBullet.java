package com.butch.game.gameobjects.weapons.Ammo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Bullet;

public class RifleBullet implements Bullet {
    private Vector2 position = new Vector2();
    private Vector2 direction;
    private Vector2 velocity = new Vector2();
    private float speed = 3;
    private Sprite sprite;

    @Override
    public void update() {
        this.position = new Vector2(this.position.x + this.velocity.x, this.position.y + this.velocity.y);
    }

    @Override
    public void init(Vector2 start, Vector2 dir) {
        this.sprite = new Sprite(ButchGame.assets.get(ButchGame.assets.bulletSprite, Texture.class));
        this.sprite.setScale(10);
        position = start;
        direction = dir;
        velocity = new Vector2(this.direction.x * speed, this.direction.y * speed);
        Bullet.bullets.add(this);
    }
}
