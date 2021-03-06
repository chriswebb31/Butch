package com.butch.game.gameobjects.abstractinterface;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.spriterenderables.Bullet;
import com.butch.game.gameobjects.spriterenderables.Shell;

import java.util.Random;

public abstract class Gun extends EquipableItem {
    /*

    CLASS : GUN

    Used to create weapon classes which shoot

    gun types
    3: musket
    2: shotgun
    1: rifle
    0 : pistol

     */
    public enum State {SHOOTING, MOVING, IDLE, RELOADING}
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
    private boolean isShooting = false;
    public Animation<TextureRegion> gunWalking;
    public Animation<TextureRegion> gunShooting;
    public Animation<TextureRegion> gunReloading;
    public TextureRegion spriteImg;
    public Texture ammoBar;
    private boolean isFist = false;

    public Gun() {

    }

    public boolean Shoot(){
        Shell shell; //drop shells when shooting
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
        if ((thisShot - lastShot) >= (long) (fireRate * 1000)) { //fire rate calculations
            try {
                if(friendly){
                    switch (gunType){
                        case 0:
                            this.reserve = player.pistolAmmo;
                            break;
                        case 1:
                            this.reserve = player.rifleAmmo;
                            break;
                        case 2:
                            this.reserve = player.shotgunAmmo;
                            break;
                        case 3:
                            this.reserve = player.musketAmmo;
                            break;
                        case 4:
                            this.reserve = player.meleeAmmo;
                            break;
                    }
                }else{
                    this.reserve = 100;
                }
                if((clip > 0) && (!isReloading) && this.reserve!=0) {
                    gunShotSound.play();
                    Bullet shot;
                    if(this.parent.TAG == "player") {
                        if(this.player.getActiveWeapon().id == 14)
                            isFist = true;
                        else
                            isFist = false;
                        if (this.player.getAimDirection().x > 0)
                            shot = new Bullet(new Vector2(this.getPosition().x + (2 * this.getSprite().getRegionWidth()), this.getPosition().y), this.aimDirection().nor(), speed + ((player.getPlayerLevel() - 1) * 3), damage + ((player.getPlayerLevel() - 1) * 5), friendly, new Sprite(ButchGame.assets.get(ButchGame.assets.friendlyBullet, Texture.class)), isFist);
                        else
                            shot = new Bullet(new Vector2(this.getPosition().x - (6 * this.getSprite().getRegionWidth()), this.getPosition().y), this.aimDirection().nor(), speed + ((player.getPlayerLevel()-1) * 3), damage + ((player.getPlayerLevel()-1) * 5), friendly, new Sprite(ButchGame.assets.get(ButchGame.assets.friendlyBullet, Texture.class)), isFist);
                    } else {
                        shot = new Bullet(new Vector2(this.getPosition().x + (2 * this.getSprite().getRegionWidth()), this.getPosition().y), this.aimDirection().nor(), speed, damage, friendly, new Sprite(ButchGame.assets.get(ButchGame.assets.enemyBullet, Texture.class)), false);
                    }
                    if(!isFist)
                        shell = new Shell(this.getPosition());
                    lastShot = thisShot;
                    clip -= 1;
                    return true;
                }
                else if (clip <= 0  && this.reserve!=0) {
                    if (!isReloading)
                        lastReload = System.currentTimeMillis();

                    isReloading = true;
                    Reload();
                    return false;
                }
                else if((clip < 0) && isReloading  && this.reserve!=0){
                    lastReload = System.currentTimeMillis();
                    Reload();
                    return false;
                }
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }
        return false;
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
            } else if (gunType == 3) {
                reserve = player.musketAmmo;
            } else if (gunType == 4) {
                reserve = player.meleeAmmo;
            }

            System.out.println("Player RELOAD!");

            long thisReload = System.currentTimeMillis();
            if ((thisReload - lastReload) >= (long) (reloadSpeed * 1000) && reserve > 0) {
                reloadSoundEffect.play(1);
                if (this.reserve > clipSize) {
                    if (gunType == 0) {
                        player.pistolAmmo += clip;
                    } else if (gunType == 1) {
                        player.rifleAmmo += clip;
                    } else if (gunType == 2) {
                        player.shotgunAmmo += clip;
                    } else if (gunType == 3) {
                        player.musketAmmo += clip;
                    } else if (gunType == 4) {
                        player.meleeAmmo += clip;
                    }

                    clip = clipSize;

                    if (gunType == 0) {
                        player.pistolAmmo -= clipSize;
                    } else if (gunType == 1) {
                        player.rifleAmmo -= clipSize;
                    } else if (gunType == 2) {
                        player.shotgunAmmo -= clipSize;
                    } else if (gunType == 3) {
                        player.musketAmmo -= clipSize;
                    } else if (gunType == 4) {
                        player.meleeAmmo -= clipSize;
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
                    } else if (gunType == 3) {
                        player.musketAmmo = 0;
                    } else if (gunType == 4) {
                        player.meleeAmmo = 0;
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
            } else if (gunType == 3) {
                reserve = enemy.musketAmmo;
            }
            System.out.println("Enemy RELOAD!");

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
                    } else if (gunType == 3) {
                        enemy.musketAmmo -= clipSize;
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
                    } else if (gunType == 3) {
                        enemy.musketAmmo = 0;
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
            Random random = new Random();
            float min = -accuracy;
            float max = accuracy;
            float x = min + random.nextFloat() * (max - min);
            float y = min + random.nextFloat() * (max - min);
            aimDir = new Vector2(aimDir.x + x, aimDir.y + y);
            return aimDir;
        } else if(parent.TAG == "enemy"){
            Vector2 aimDir = enemy.aimDirection();
            Random random = new Random();
            float min = -100;
            float max = 100;
            float x = min + random.nextFloat() * (max - min);
            float y = min + random.nextFloat() * (max - min);
            Vector2 randomNums = new Vector2(x, y);
            aimDir = new Vector2(aimDir.x + randomNums.x, aimDir.y + randomNums.y);
            return aimDir;
        }else {
            return null;
        }
    }

    public State getState() {
        if (isShooting)
            return State.SHOOTING;
        else if (isReloading)
            return  State.RELOADING;
        else
            return State.MOVING;
    }
}
