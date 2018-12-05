package com.butch.game.gameobjects.weapons;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Gun;

public class GatlingGun extends Gun {
    public GatlingGun() {
        this.id = 12;
        this.gunType = 1;
        this.clipSize = 100;
        this.accuracy = 200;
        this.clip = 100;
        this.oneHanded = true;
        this.damage = 30;
        this.fireRate = 0.1f;
        this.reloadSpeed = 4;
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
