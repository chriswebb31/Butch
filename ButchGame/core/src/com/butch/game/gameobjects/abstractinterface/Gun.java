package com.butch.game.gameobjects.abstractinterface;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.Bullet;
import com.butch.game.gameobjects.Player;

public abstract class Gun {
    //VARS FROM CONSTRUCTOR
    public Player player;
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
    public Vector2 position;

    public Sound gunShotSound;
    public boolean oneHanded;

    private long lastShot = System.currentTimeMillis() - (long) (fireRate * 1000);
    private long lastReload = System.currentTimeMillis() - (long) (reloadSpeed * 1000);

//    public Sound reloadSound;

    public Gun(Player player){
        this.player = player;
    }

    public void Shoot(){
        long thisShot = System.currentTimeMillis();
        long thisReload = System.currentTimeMillis();

        if ((thisShot - lastShot) >= (long) (fireRate *1000)) {

            try {
                if ((clip > 0) && (!isReloading)) {
                    System.out.println((clip > 0) + " " + (!isReloading));
                    gunShotSound.play(0.8f);
                    Bullet newShot = new Bullet(this.position, player.getAimDirection(), true, gunType);
                    lastShot = thisShot;
                    clip -= 1;

                    System.out.println("BANG");
                    System.out.println("clip:"+clip);
                } else if (clip <= 0) {
                    isReloading = true;

                    if((thisReload - lastReload) >= (long) (reloadSpeed * 1000)){
                        Reload();
                        isReloading = false;
                        lastReload = thisReload;
                    }
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    public void Reload(){
        if(this.reserve >= clipSize){
            clip = clipSize;
            reserve -= clipSize;
        } else{
            clip = reserve;
            reserve = 0;
        }
        isReloading = false;
    }

    public void Update(){
        Vector2 targetDir = new Vector2(ButchGame.mousePosition().x, ButchGame.mousePosition().y);
        float angle = (float) Math.atan2(targetDir.y - gunSprite.getY(), targetDir.x - gunSprite.getX());
        angle = (float) Math.toDegrees(angle);
        position = player.weaponPosition();
        try{
            this.gunSprite.setRotation(angle);
            this.gunSprite.setPosition(position.x, position.y);
        } catch (NullPointerException e){
            System.out.println(e);
        }
    }
}
