package com.butch.game.gameobjects.weapons;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Gun;

public class Colt extends Gun {
    private TextureAtlas revolverWalkingAtlas = new TextureAtlas(ButchGame.assets.revolverWalking);
    private TextureAtlas revolverShootingAtlas = new TextureAtlas(ButchGame.assets.revolverFiring);
    private TextureAtlas revolverReloadingAtlas = new TextureAtlas(ButchGame.assets.revolverReload);

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
        this.gunWalking = new Animation<TextureRegion>(0.1f, revolverWalkingAtlas.getRegions());
        this.gunReloading = new Animation<TextureRegion>(0.1f, revolverReloadingAtlas.getRegions());
        this.gunShooting = new Animation<TextureRegion>(0.1f, revolverShootingAtlas.getRegions());
        this.gunShotSound = ButchGame.assets.get(ButchGame.assets.gunShot, Sound.class);
        this.reloadSoundEffect = ButchGame.assets.get(ButchGame.assets.otherReloadEffect, Sound.class);
    }

    @Override
    public void takeHit(float damage) {

    }
}
