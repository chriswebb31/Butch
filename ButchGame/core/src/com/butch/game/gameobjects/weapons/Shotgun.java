package com.butch.game.gameobjects.weapons;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Gun;

public class Shotgun extends Gun {
    private TextureAtlas shotgunWalkingAtlas = new TextureAtlas(ButchGame.assets.shotgunWalking);
    private TextureAtlas shotgunShootingAtlas = new TextureAtlas(ButchGame.assets.shotgunFiring);
    private TextureAtlas shotgunReloadingAtlas = new TextureAtlas(ButchGame.assets.shotgunReload);

    public Shotgun() {
        this.id = 12;
        this.gunName = "Shotgun";
        this.gunType = 1;
        this.clipSize = 2;
        this.accuracy = 20;
        this.clip = 2;
        this.oneHanded = false;
        this.damage = 60;
        this.fireRate = 1;
        this.reloadSpeed = 2;
        this.speed = 30;
        this.gunWalking = new Animation<TextureRegion>(0.1f, shotgunWalkingAtlas.getRegions());
        this.gunReloading = new Animation<TextureRegion>(0.1f, shotgunReloadingAtlas.getRegions());
        this.gunShooting = new Animation<TextureRegion>(0.1f, shotgunShootingAtlas.getRegions());
        this.gunShotSound = ButchGame.assets.get(ButchGame.assets.gunShot, Sound.class);
        this.reloadSoundEffect = ButchGame.assets.get(ButchGame.assets.otherReloadEffect, Sound.class);
    }

    @Override
    public void takeHit(float damage) {

    }
}

