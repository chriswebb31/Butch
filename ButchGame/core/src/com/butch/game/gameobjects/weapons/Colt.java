package com.butch.game.gameobjects.weapons;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Gun;

public class Colt extends Gun {

    public Colt() {
        this.id = 10;
        this.gunName = "Colt";
        this.gunType = 0;
        this.clipSize = 8;
        this.accuracy = 50;
        this.clip = 8;
        this.oneHanded = true;
        this.damage = 50;
        this.fireRate = 0.2f;
        this.reloadSpeed = 1;
        this.speed = 15;
        this.setSprite(new Sprite(ButchGame.assets.get(ButchGame.assets.coltSprite, Texture.class)));
        this.getSprite().setScale(6);
        this.gunShotSound = ButchGame.assets.get(ButchGame.assets.gunShot, Sound.class);
        this.reloadSoundEffect = ButchGame.assets.get(ButchGame.assets.otherReloadEffect, Sound.class);
    }

    @Override
    public void takeHit(float damage) {

    }
}
