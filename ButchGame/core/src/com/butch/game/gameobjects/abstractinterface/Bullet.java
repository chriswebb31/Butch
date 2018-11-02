package com.butch.game.gameobjects.abstractinterface;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;

import java.util.ArrayList;

public class Bullet {
    public static ArrayList<Bullet> bullets;
    public Vector2 position;
    public Vector2 direction;
    public Vector2 velocity;
    public float pistolSpeed = 20;
    public float rifleSpeed = 30;
    public float shotgunSpeed = 20;
    public boolean freindly;
    public Sprite pistolBulletSprite = new Sprite(ButchGame.assets.get(ButchGame.assets.bulletSprite, Texture.class));
    public Sprite rifleBulletSprite = new Sprite(ButchGame.assets.get(ButchGame.assets.bulletSprite, Texture.class));; // 1
    public Sprite shotgunBulletSprite = new Sprite(ButchGame.assets.get(ButchGame.assets.bulletSprite, Texture.class));; // 2
    public Sprite activeSprite;
    public int ammoType;

    public Bullet(Vector2 start, Vector2 direction, boolean freindly, int ammoType){
        if(bullets == null)
            initArray();
        
        this.position = start;
        this.direction = direction.nor();
        this.ammoType = ammoType;
        switch(ammoType){
            case 0:
                activeSprite = pistolBulletSprite;
                this.velocity = new Vector2(this.direction.x * pistolSpeed, this.direction.y * pistolSpeed);
            case 1:
                activeSprite = rifleBulletSprite;
                this.velocity = new Vector2(this.direction.x * rifleSpeed, this.direction.y * rifleSpeed);
            case 2:
                activeSprite = shotgunBulletSprite;
                this.velocity = new Vector2(this.direction.x * shotgunSpeed, this.direction.y * shotgunSpeed);
        }
        activeSprite.setScale(5);
        addBullet(this);
    }

    private static void initArray() {
        bullets = new ArrayList<Bullet>();
    }

    public static void update(SpriteBatch spriteBatch){
        if(bullets == null)
            initArray();
        try{
            for (Bullet bullet: bullets) {
                bullet.position = new Vector2(bullet.position.x + bullet.velocity.x, bullet.position.y + bullet.velocity.y);
                bullet.activeSprite.setPosition(bullet.position.x, bullet.position.y);
                bullet.activeSprite.draw(spriteBatch);
            }
        } catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    public static void addBullet(Bullet bullet){
        bullets.add(bullet);
    }
}
