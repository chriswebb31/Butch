package com.butch.game.gameobjects.weapons;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.spriterenderables.Player;
import com.butch.game.gameobjects.spriterenderables.abstracts.Gun;

public class CHOPPER extends Gun {

    public CHOPPER(Player player) {
        super(player);
        this.accuracy = 100;
        this.clip = 25;
        this.clipSize = 25;
        this.damage = 25;
        this.oneHanded = false;
        this.reserve = 125;
        this.fireRate = 0.25f;
        this.reloadSpeed = 0.5f;
        this.speed = 15;
        this.setSprite(new Sprite(ButchGame.assets.get(ButchGame.assets.machineGunSprite, Texture.class)));
        this.getSprite().setScale(10);
        this.gunShotSound = ButchGame.assets.get(ButchGame.assets.gunShot, Sound.class);
        this.reloadSoundEffect = ButchGame.assets.get(ButchGame.assets.otherReloadEffect, Sound.class);
    }


    @Override
    public void takeHit(float damage) {

    }
}
