package com.butch.game.gameobjects.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.Player;
import com.butch.game.gameobjects.abstractinterface.Bullet;
import com.butch.game.gameobjects.abstractinterface.Weapon;
import com.butch.game.gameobjects.weapons.Ammo.RifleBullet;
import com.badlogic.gdx.audio.Sound;

public class Revolver extends Weapon {

    public Revolver(Player player) {
        this.clip = 6;
        this.type = 2;
        this.clipSize = 6;
        this.reserve = 120;
        this.player = player;
        this.reloadSpeed = 3;
        this.fireRate = 0.4f;
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
        if ((this.clip > 0) && (!this.isShootingActive) && (!this.isReloading)) { //if ammo in clip and isnt currently shooting already this frame or reloading
            gunShot.play(0.8f); //then shoot!
            isShootingActive = true; //say we are shooting to stop repeat calls in following frames
            Bullet bullet = new RifleBullet(); //instantiate new bullet
            bullet.init(new Vector2(player.activeWeapon.sprite.getX(), player.activeWeapon.sprite.getY()), player.getAimDirection(), player); //set bullet position and direction
            Timer.schedule(new Timer.Task() { //fire rate delay
                @Override
                public void run() {
                    clip -= 1; //shot a bullet so ammo -1
                    isShootingActive = false; //delay over so we can shoot again
                }
            }, fireRate);

        } else if (this.clip <= 0) { //if no ammo
            isReloading = true; //set to reloading to stop repeat calls
            Timer.schedule(new Timer.Task() { //reload delay
                @Override
                public void run() {
                    Reload(); //call reload
                }
            }, reloadSpeed);
        }
    }

    public void Reload() {
        if (reserve >= clipSize) { //if more ammo in reserve than size of clip
            clip = clipSize; //fill clip
            reserve -= clipSize; //reserve minus new filled clip
        } else {
            clip = reserve; //if less ammo in reserve than size of clip
            reserve = 0; //empty reserve into clip
        }
        isReloading = false; //reload over
        isShootingActive = false; //can shoot
    }

    @Override
    public void updatePosition(Vector2 targetDirection) {
        if (targetDirection.x >= player.getPosition().x) { //if aiming right of player
            this.position.set(player.rightHandIK()); //set weapon pos to right hand
        } else {//if aiming left of player
            this.position.set(player.leftHandIK()); //set weapon pos to left hand
        }
        this.sprite.setPosition(this.position.x, this.position.y); //update sprite position
    }

    @Override
    public void updateRotation(Vector2 targetDirection) {
        float angle = 0; //local variable
        if (this.sprite.isFlipX()) {
            angle = (float) Math.atan2(targetDirection.y - sprite.getY(), targetDirection.x - sprite.getX());
        } else {
            angle = (float) Math.atan2(targetDirection.y - sprite.getY(), targetDirection.x - sprite.getX());
        }

        angle = (float) Math.toDegrees(angle);
        this.sprite.setRotation(angle);
    }
}