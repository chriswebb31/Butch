package com.butch.game.gameobjects.weapons;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Gun;

public class Colt extends Gun {

    public Colt() {
        this.id = 10;
        this.gunName = "Colt";
        this.gunType = 0;
        this.clipSize = 6;
        this.accuracy = 50;
        this.clip = 6;
        this.oneHanded = true;
        this.damage = 50;
        this.fireRate = 0.2f;
        this.reloadSpeed = 1;
        this.speed = 15;
        this.gunWalking = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.revolverWalking, TextureAtlas.class).getRegions());
        this.gunReloading = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.revolverReload, TextureAtlas.class).getRegions());
        this.gunShooting = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.revolverFiring, TextureAtlas.class).getRegions());
        this.spriteImg = new TextureRegion(ButchGame.assets.get(ButchGame.assets.coltSprite, Texture.class), 0, 0,28, 12);
        this.gunShotSound = ButchGame.assets.get(ButchGame.assets.gunShot, Sound.class);
        this.reloadSoundEffect = ButchGame.assets.get(ButchGame.assets.otherReloadEffect, Sound.class);
    }

    @Override
    public void takeHit(float damage) {

    }
}
