package com.butch.game.gameobjects.abstractinterface;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.Bullet;
import com.butch.game.gameobjects.spriterenderables.NewPlayer;

import java.util.Random;

public abstract class Gun {
    //VARS FROM CONSTRUCTOR
    public NewPlayer player;
    public Sprite gunSprite;

    public int clipSize = 0;
    public int reserve = 0;
    public float fireRate = 0;
    public float reloadSpeed = 0;
    public int gunType = 0;


    //VARS FOR METHODS
    public int clip = 0;
    public boolean isShooting = false;
    public boolean isReloading = false;
    boolean hasCalledReload = false;
    public Vector2 position;

    public Sound gunShotSound;
    public Sound reloadSoundEffect;
    public boolean oneHanded;

    public float accuracy;

    private long lastShot = System.currentTimeMillis() - (long) (fireRate * 1000);
    private long lastReload = System.currentTimeMillis() - (long) (reloadSpeed * 1000);

//    public Sound reloadSound;

    public Gun(NewPlayer player){
        this.player = player;
    }

    public Vector2 aimDirection(){
        Vector2 aimDir = player.getAimDirection();
        System.out.println(aimDir);
        Random random = new Random();
        float min = -accuracy;
        float max = accuracy;
        float x = min + random.nextFloat() * (max - min);
        float y = min + random.nextFloat() * (max - min);

        aimDir = new Vector2(aimDir.x + x, aimDir.y + y);
        System.out.println(aimDir);
        return aimDir;
    }

    public void Shoot(){
        long thisShot = System.currentTimeMillis();

        if ((thisShot - lastShot) >= (long) (fireRate *1000)) {
            try {
                if ((clip > 0) && (!isReloading)) {
                    Random r = new Random();
                    double random = 0.6f + r.nextDouble() * (0.6 - 0.4f);
                    gunShotSound.play((float) random);
                    Bullet newShot = new Bullet(this.position, this.aimDirection(), true, gunType);
                    lastShot = thisShot;
                    clip -= 1;
                } else if (clip <= 0) {
                    if (!isReloading)
                        lastReload = System.currentTimeMillis();

                    isReloading = true;
                    Reload();
                } else if((clip < 0) && isReloading){
                    lastReload = System.currentTimeMillis();
                    Reload();
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    public void Reload(){
        if (!hasCalledReload){
            reloadSoundEffect.play(1);
            hasCalledReload = true;
        }
        long thisReload = System.currentTimeMillis();

        System.out.println(reloadSpeed * 1000);
        if ((thisReload - lastReload) >= (long) (reloadSpeed * 1000)) {
            if(this.reserve >= clipSize){
                clip = clipSize;
                reserve -= clipSize;
            } else{
                clip = reserve;
                reserve = 0;
            }
            isReloading = false;
            hasCalledReload = false;
            lastReload = thisReload;
        }
    }

    public void Update(){
        Vector2 targetDir = new Vector2(ButchGame.mousePosition().x, ButchGame.mousePosition().y);
        float angle = (float) Math.atan2(targetDir.y - gunSprite.getY(), targetDir.x - gunSprite.getX());
        angle = (float) Math.toDegrees(angle);
        if(this.oneHanded)
            position = player.getWeaponPosition();
        else{
            position = player.getPosition();
        }
        try{
            this.gunSprite.setRotation(angle);
            this.gunSprite.setPosition(position.x, position.y);
        } catch (NullPointerException e){
            System.out.println(e);
        }
    }
}
