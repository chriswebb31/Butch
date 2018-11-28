package com.butch.game.gameobjects.abstractinterface;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.gameobjects.spriterenderables.Bullet;

import java.util.Random;

public abstract class Gun extends EquipableItem {
    public int id;
    public int gunType;

    private long lastShot;
    public float fireRate;
    public float speed;
    public Sound gunShotSound;
    public float damage;
    public float accuracy;
    public int clip;
    public int clipSize;
    private boolean isReloading = false;
    private long lastReload;
    private boolean hasCalledReload;
    public Sound reloadSoundEffect;
    public float reloadSpeed;
    public int reserve;

    public Gun() {

    }

    public void Shoot(){
        long thisShot = System.currentTimeMillis();
        if ((thisShot - lastShot) >= (long) (fireRate * 1000)) {
            try {
                switch (gunType){
                    case 0:
                        this.reserve = player.pistolAmmo;
                    case 1:
                        this.reserve = player.rifleAmmo;
                    case 2:
                        this.reserve = player.shotgunAmmo;
                }
                if((clip > 0) && (!isReloading)) {
                    gunShotSound.play();
                    Bullet shot = new Bullet(this.getPosition(), this.aimDirection().nor(), speed, damage);
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
            hasCalledReload = true;
        }

        long thisReload = System.currentTimeMillis();
        System.out.println(reloadSpeed * 1000);
        if ((thisReload - lastReload) >= (long) (reloadSpeed * 1000)) {
            reloadSoundEffect.play(1);
            if(this.reserve >= clipSize){
                clip = clipSize;
                reserve -= clipSize;
            } else{
                clip = reserve;
                reserve = 0;
            }
            switch (gunType){
                case 0:
                    player.pistolAmmo = this.reserve;
                case 1:
                    player.rifleAmmo = this.reserve;
                case 2:
                    player.shotgunAmmo = this.reserve;
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
