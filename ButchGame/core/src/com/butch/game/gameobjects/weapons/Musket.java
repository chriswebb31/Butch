package com.butch.game.gameobjects.weapons;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Gun;

public class Musket extends Gun {
    private TextureAtlas musketWalkingAtlas = new TextureAtlas(ButchGame.assets.musketWalking);
    private TextureAtlas musketShootingAtlas = new TextureAtlas(ButchGame.assets.musketFiring);
    private TextureAtlas musketReloadingAtlas = new TextureAtlas(ButchGame.assets.musketReload);

    public Musket() {
        this.id = 13;
        this.gunName = "Musket";
        this.gunType = 0;
        this.clipSize = 12;
        this.accuracy = 20;
        this.clip = 12;
        this.oneHanded = true;
        this.damage = 20;
        this.fireRate = 0.7f;
        this.reloadSpeed = 1.5f;
        this.speed = 30;
        this.gunWalking = new Animation<TextureRegion>(0.1f, musketWalkingAtlas.getRegions());
        this.gunReloading = new Animation<TextureRegion>(0.1f, musketReloadingAtlas.getRegions());
        this.gunShooting = new Animation<TextureRegion>(0.1f, musketShootingAtlas.getRegions());
        this.gunShotSound = ButchGame.assets.get(ButchGame.assets.gunShot, Sound.class);
        this.reloadSoundEffect = ButchGame.assets.get(ButchGame.assets.otherReloadEffect, Sound.class);
    }

    @Override
    public void takeHit(float damage) {

    }
}
