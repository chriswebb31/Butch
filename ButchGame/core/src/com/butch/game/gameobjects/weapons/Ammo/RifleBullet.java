package com.butch.game.gameobjects.weapons.Ammo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.Player;
import com.butch.game.gameobjects.abstractinterface.Bullet;

public class RifleBullet extends Bullet {
    private float spawnTime;
    private float speed = 5;

    public RifleBullet(){

    }

    @Override
    public void update() {
        this.position = new Vector2(this.position.x + this.velocity.x * speed, this.position.y + this.velocity.y * speed);
        this.sprite.setPosition(this.position.x, this.position.y);
        Vector2 targetDirection = new Vector2(ButchGame.mousePosition().x, ButchGame.mousePosition().y);
        float angle = (float) Math.atan2(targetDirection.y - sprite.getY(), targetDirection.x - sprite.getX());
        this.sprite.setRotation(angle);
    }

    @Override
    public void init(Vector2 start, Vector2 dir, Player player) {
        this.sprite = new Sprite(ButchGame.assets.get(ButchGame.assets.bulletSprite, Texture.class));
        this.sprite.setScale(10);
        position = start;
        direction = dir.nor();
        velocity = new Vector2(this.direction.x * speed, this.direction.y * speed);
        Vector2 targetDirection = new Vector2(ButchGame.mousePosition().x, ButchGame.mousePosition().y);
        float angle = (float) Math.atan2(targetDirection.y - sprite.getY(), targetDirection.x - sprite.getX());
        this.sprite.setRotation(angle);
        player.playerBullets.add(this);
    }
}
