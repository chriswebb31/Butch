package com.butch.game.gameobjects.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.Player;
import com.butch.game.gameobjects.abstractinterface.Weapon;
import com.butch.game.gameobjects.weapons.Ammo.RifleBullet;

public class Rifle extends Weapon {

     public Rifle(Player player){
         this.type = 2;
         this.clipSize = 5;
         this.reserve = 45;
         this.bullet = new RifleBullet();
         this.player = player;
         this.reloadSpeed = 5;
         this.fireRate = 1;
         this.isShootingActive = false;
         this.sprite = new Sprite(ButchGame.assets.get(ButchGame.assets.gunSprite, Texture.class));
         this.sprite.setScale(10);
     }

    @Override
    public void MeleeAttack() {

    }

    @Override
    public void Shoot() {
        if((this.clip > 0) && (!this.isShootingActive)){
            System.out.println("Bang!");
            isShootingActive = true;
            bullet = new RifleBullet();
            bullet.init(player.getPosition(), player.getAimDirection());
            try {
                Thread.sleep(this.fireRate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isShootingActive = false;
        }
        else if(this.clip > 0){
            System.out.println("RELOAD!");
            this.Reload();
        }
    }
}
