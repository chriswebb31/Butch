package com.butch.game.gameobjects.weapons;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Gun;

public class MachineGun extends Gun {
    private TextureAtlas machineGunWalkingAtlas = new TextureAtlas(ButchGame.assets.machineGunWalking);
    private TextureAtlas machineGunShootingAtlas = new TextureAtlas(ButchGame.assets.machineGunFiring);
    private TextureAtlas machineGunReloadingAtlas = new TextureAtlas(ButchGame.assets.machineGunReload);

    public MachineGun() {
        this.id = 11;
        this.gunName = "Machine Gun";
        this.gunType = 1;
        this.accuracy = 100;
        this.clip = 25;
        this.clipSize = 25;
        this.damage = 25;
        this.oneHanded = false;
        this.fireRate = 0.25f;
        this.reloadSpeed = 0.5f;
        this.speed = 15;
        this.gunWalking = new Animation<TextureRegion>(0.1f, machineGunWalkingAtlas.getRegions());
        this.gunReloading = new Animation<TextureRegion>(0.1f, machineGunReloadingAtlas.getRegions());
        this.gunShooting = new Animation<TextureRegion>(0.1f, machineGunShootingAtlas.getRegions());
        this.spriteImg = new TextureRegion(ButchGame.assets.get(ButchGame.assets.machineGunSprite, Texture.class), 0, 0,27, 7);
        this.gunShotSound = ButchGame.assets.get(ButchGame.assets.gunShot, Sound.class);
        this.reloadSoundEffect = ButchGame.assets.get(ButchGame.assets.otherReloadEffect, Sound.class);
    }


    @Override
    public void takeHit(float damage) {

    }
}
