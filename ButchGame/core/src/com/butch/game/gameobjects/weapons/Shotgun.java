package com.butch.game.gameobjects.weapons;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Gun;

public class Shotgun extends Gun {

    public Shotgun() {
        this.id = 12;
        this.gunType = 0;
        this.clipSize = 2;
        this.accuracy = 20;
        this.clip = 2;
        this.oneHanded = true;
        this.damage = 60;
        this.fireRate = 1;
        this.reloadSpeed = 2;
        this.speed = 30;
        this.setSprite(new Sprite(ButchGame.assets.get(ButchGame.assets.shotgunSprite, Texture.class)));
        this.getSprite().setScale(10);
        this.gunShotSound = ButchGame.assets.get(ButchGame.assets.gunShot, Sound.class);
        this.reloadSoundEffect = ButchGame.assets.get(ButchGame.assets.otherReloadEffect, Sound.class);
    }

    @Override
    public void takeHit(float damage) {

    }
}

