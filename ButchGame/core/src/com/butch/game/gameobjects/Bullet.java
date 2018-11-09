package com.butch.game.gameobjects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Enemy;
import com.butch.game.screens.GameScreen;

import java.util.ArrayList;
import java.util.Random;

public class Bullet {
    public static GameScreen game;
    public static ArrayList<Bullet> bullets;
    public Rectangle collider;
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
    public int ammoType; //0:pistol 1:rifle 2:shotgun
    public boolean active = true;

    public Bullet(GameScreen game, Vector2 start, Vector2 direction, boolean freindly, int ammoType){
        Random r = new Random();
        double random = 0.6f + r.nextDouble() * (1.2 - 0.7f);
        boolean shellPing = r.nextBoolean();
        if(shellPing){
            Sound shellBounce = ButchGame.assets.get(ButchGame.assets.shellBounceEffect);
            shellBounce.play((float) random);
        }
        if(bullets == null)
            initArray();

        this.game = game;
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
        activeSprite.setScale(10);
        this.collider = new Rectangle(this.position.x, this.position.y, this.activeSprite.getWidth(), this.activeSprite.getHeight());

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
                if(bullet.active){
                    bullet.position = new Vector2(bullet.position.x + bullet.velocity.x, bullet.position.y + bullet.velocity.y);
                    bullet.activeSprite.setPosition(bullet.position.x, bullet.position.y);
                    bullet.collider.setPosition(bullet.position.x, bullet.position.y);
                    bullet.activeSprite.draw(spriteBatch);
                    for (Enemy enemy: Enemy.enemies) {
                        if(bullet.collider.overlaps(enemy.collider) && enemy.active){
                            enemy.takeDamage(bullet.ammoType);
                            bullet.active = false;
                        }
                    }
                }
            }
        } catch (NullPointerException e){
            e.printStackTrace();
            System.out.println("ping");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void addBullet(Bullet bullet){
        bullets.add(bullet);
    }
}
