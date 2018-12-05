package com.butch.game.gameobjects.abstractinterface;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.gameobjects.spriterenderables.Bullet;
import com.butch.game.gameobjects.spriterenderables.Enemy;
import com.butch.game.gameobjects.spriterenderables.Shell;

import java.util.Random;

public abstract class Gun extends EquipableItem {
    public int id;
    public int gunType;
    public String gunName;
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
        if(this.parent.TAG == "player"){
            friendly = true;
        }
        else if(this.parent.TAG == "enemy"){
            friendly = false;
        }
        else if(this.parent.TAG == "npc") {
            friendly = false;
        }

        long thisShot = System.currentTimeMillis();
        if ((thisShot - lastShot) >= (long) (fireRate * 1000)) {

            try {
                if(friendly){
                    switch (gunType){
                        case 0:
                            this.reserve = player.pistolAmmo;
                        case 1:
                            this.reserve = player.rifleAmmo;
                        case 2:
                            this.reserve = player.shotgunAmmo;
                    }
                }else{
                    this.reserve = 100;
                }
                if((clip > 0) && (!isReloading) && this.reserve!=0) {
                    gunShotSound.play();
                    Bullet shot = new Bullet(this.getPosition(), this.aimDirection().nor(), speed, damage, friendly);
                    Shell shell = new Shell(this.getPosition());
                    lastShot = thisShot;
                    clip -= 1;
                }
                else if (clip <= 0  && this.reserve!=0) {
                    if (!isReloading)
                        lastReload = System.currentTimeMillis();

                    isReloading = true;
                    Reload();
                }
                else if((clip < 0) && isReloading  && this.reserve!=0){
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
        if(parent.TAG == "player") {
            if (gunType == 0) {
                reserve = player.pistolAmmo;
            } else if (gunType == 1) {
                reserve = player.rifleAmmo;
            } else if (gunType == 2) {
                reserve = player.shotgunAmmo;
            }
            System.out.println("Player RELOAD!");
            System.out.println("Player RESERVE AMMO:" + reserve);
            long thisReload = System.currentTimeMillis();
            if ((thisReload - lastReload) >= (long) (reloadSpeed * 1000)) {
                reloadSoundEffect.play(1);
                if (this.reserve >= clipSize) {
                    clip = clipSize;
                    if (gunType == 0) {
                        player.pistolAmmo -= clipSize;
                    } else if (gunType == 1) {
                        player.rifleAmmo -= clipSize;
                    } else if (gunType == 2) {
                        player.shotgunAmmo -= clipSize;
                    }
                } else {
                    clip = reserve;
                    reserve = 0;
                    if (gunType == 0) {
                        player.pistolAmmo = 0;
                    } else if (gunType == 1) {
                        player.rifleAmmo = 0;
                    } else if (gunType == 2) {
                        player.shotgunAmmo = 0;
                    }
                }

                isReloading = false;
                hasCalledReload = false;
                lastReload = thisReload;
            }
        } else if (parent.TAG == "enemy") {
            if (gunType == 0) {
                reserve = enemy.pistolAmmo;
            } else if (gunType == 1) {
                reserve = enemy.rifleAmmo;
            } else if (gunType == 2) {
                reserve = enemy.shotgunAmmo;
            }
            System.out.println("Enemy RELOAD!");
            System.out.println("Enemy RESERVE AMMO:" + reserve);
            long thisReload = System.currentTimeMillis();
            if ((thisReload - lastReload) >= (long) (reloadSpeed * 1000)) {
                reloadSoundEffect.play(1);
                if (this.reserve >= clipSize) {
                    clip = clipSize;
                    if (gunType == 0) {
                        enemy.pistolAmmo -= clipSize;
                    } else if (gunType == 1) {
                        enemy.rifleAmmo -= clipSize;
                    } else if (gunType == 2) {
                        enemy.shotgunAmmo -= clipSize;
                    }
                } else {
                    clip = reserve;
                    reserve = 0;
                    if (gunType == 0) {
                        enemy.pistolAmmo = 0;
                    } else if (gunType == 1) {
                        enemy.rifleAmmo = 0;
                    } else if (gunType == 2) {
                        enemy.shotgunAmmo = 0;
                    }
                }

                isReloading = false;
                hasCalledReload = false;
                lastReload = thisReload;
            }
        }
    }

    public Vector2 aimDirection(){

        if(parent.TAG == "player"){
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
        } else if(parent.TAG == "enemy"){
            Enemy enemy = (Enemy) parent;
            return enemy.aimDirection();
        }else {
            return null;
        }
    }
}
