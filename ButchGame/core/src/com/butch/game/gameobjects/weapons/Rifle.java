package com.butch.game.gameobjects.weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.Player;
import com.butch.game.gameobjects.abstractinterface.Bullet;
import com.butch.game.gameobjects.abstractinterface.Weapon;
import com.butch.game.gameobjects.weapons.Ammo.RifleBullet;
import com.badlogic.gdx.audio.Sound;

public class Rifle extends Weapon {

     public Rifle(Player player){
         this.type = 2;
         this.clipSize = 5;
         this.reserve = 45;
         this.player = player;
         this.reloadSpeed = 5;
         this.fireRate = 1;
         this.isShootingActive = false;
         this.sprite = new Sprite(ButchGame.assets.get(ButchGame.assets.gunSprite, Texture.class));
         this.sprite.setScale(10);
         this.position = player.rightHandIK();
         this.targetPos = 0;
         this.gunShot = ButchGame.assets.get(ButchGame.assets.gunShot, Sound.class);
     }

    @Override
    public void MeleeAttack() {

    }

    @Override
    public void Shoot() {
         System.out.println(!this.isShootingActive);
        if((this.clip > 0) && (!this.isShootingActive)){
            System.out.println("Bang!");
            gunShot.play(0.8f);
            isShootingActive = true;
            Bullet bullet = new RifleBullet();
            bullet.init(new Vector2(player.activeWeapon.sprite.getX(), player.activeWeapon.sprite.getY()), player.getAimDirection(), player);
            float bulletAngle = (float) Math.atan2(player.getAimDirection().y - sprite.getY(), player.getAimDirection().x - sprite.getX());
            bullet.sprite.setRotation(bulletAngle);
            try {
                Thread.sleep(this.fireRate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isShootingActive = false;
        }
        else if(this.clip <= 0){
            System.out.println("RELOAD!");
            this.Reload();
        }
    }

    @Override
    public void updatePosition(Vector2 targetDirection){
        if(targetDirection.x >= player.getPosition().x){
            this.position.set(player.rightHandIK());
        } else{
            this.position.set(player.leftHandIK());
        }
        this.sprite.setPosition(this.position.x, this.position.y);
    }

    @Override
    public void updateRotation(Vector2 targetDirection){
         float angle = 0;
         sprite.setOriginCenter();
         if(this.sprite.isFlipX()){
             angle = (float) Math.atan2(targetDirection.y - sprite.getY(), targetDirection.x - sprite.getX());
         } else{
             angle = (float) Math.atan2(targetDirection.y - sprite.getY(), targetDirection.x - sprite.getX());
         }

        angle = (float) Math.toDegrees(angle);
        this.sprite.setRotation(angle);
    }
}
