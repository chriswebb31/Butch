package com.butch.game.gameobjects.abstractinterface;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.Player;

public abstract class Gun {
    //VARS FROM CONSTRUCTOR
    public Player player;
    public Sprite gunSprite;

    public int clipSize = 0;
    public int reserve = 0;
    public float fireRate = 0;
    public float reloadSpeed = 0;
    public int gunType = 1;

    //VARS FOR METHODS
    public int clip = 0;
    public boolean isShooting = false;
    public boolean isReloading = false;
    public Vector2 position;

    public Sound gunShotSound;
    public boolean oneHanded;
//    public Sound reloadSound;

    public Gun(Player player){
        this.player = player;
    }

    public void Shoot(){
        try{
            if((clip > 0) && (!isShooting) && (!isReloading)){
                System.out.println((clip > 0) + " " + (!isShooting) + " " + (!isReloading));
//                gunShotSound.play(0.8f);
                isShooting = true;
                switch (gunType){
                    case 0:
                        break;
                    case 1:
                        Bullet newShot = new Bullet(this.position,player.getAimDirection(),true, 0);
                        System.out.println("BANG");
                }
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        clip -= 1;
                        isShooting = false;
                    }
                }, fireRate);
            }
            else if(clip <= 0){
                isReloading = true;
//            reloadSound.play(0.8f);
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        Reload();
                    }
                }, reloadSpeed);
            }
        } catch (NullPointerException e){
            System.out.println(e.getCause());
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
        isShooting = false;
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
            System.out.println("here m8");
            System.out.println(e);
        }
    }
}
