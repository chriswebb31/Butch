package com.butch.game.gameobjects.weapons;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Gun;

public class Musket extends Gun {

    public Musket() {
        this.id = 13;
        this.gunType = 0;
        this.clipSize = 12;
        this.accuracy = 20;
        this.clip = 12;
        this.oneHanded = true;
        this.damage = 20;
        this.fireRate = 0.7f;
        this.reloadSpeed = 1.5f;
        this.speed = 30;
        this.setSprite(new Sprite(ButchGame.assets.get(ButchGame.assets.musketSprite, Texture.class)));
        this.getSprite().setScale(10);
        this.gunShotSound = ButchGame.assets.get(ButchGame.assets.gunShot, Sound.class);
        this.reloadSoundEffect = ButchGame.assets.get(ButchGame.assets.otherReloadEffect, Sound.class);
    }

    @Override
    public void takeHit(float damage) {

    }
}
