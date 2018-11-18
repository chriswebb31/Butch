package com.butch.game.gameobjects.spriterenderables.abstracts;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.gameobjects.spriterenderables.Bullet;
import com.butch.game.gameobjects.abstractinterface.EquipableItem;
import com.butch.game.gameobjects.spriterenderables.Player;

import java.util.Random;

public abstract class Gun extends EquipableItem {
    private long lastShot;
    public float fireRate;
    public float speed;
    public Sound gunShotSound;
    public float damage;
    public float accuracy;
    public int clip;
    public int clipSize;
    private Player player;
    private boolean isReloading = false;
    private long lastReload;
    private boolean hasCalledReload;
    public Sound reloadSoundEffect;
    public float reloadSpeed;
    public int reserve;

    public Gun(Player player) {
        super(player);
        this.player = player;
    }

    public void Shoot(){
        long thisShot = System.currentTimeMillis();
        if ((thisShot - lastShot) >= (long) (fireRate * 1000)) {
            try {
                if((clip > 0) && (!isReloading)) {
                    gunShotSound.play();
                    Bullet shot = new Bullet(this.getPosition(), this.aimDirection().nor(), speed);
                    lastShot = thisShot;
                    clip -= 1;
                }
                else if (clip <= 0) {
                    if (!isReloading)
                        lastReload = System.currentTimeMillis();

                    isReloading = true;
                    Reload();
                }
                else if((clip < 0) && isReloading){
                    lastReload = System.currentTimeMillis();
                    Reload();
                }
            }catch (NullPointerException e){
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
}
