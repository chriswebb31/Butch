package com.butch.game.gameobjects.weapons;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Gun;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GunCreator extends Gun {
    public GunCreator(String gunName) {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("Gun/" + gunName + ".properties");

            // load a properties file
            prop.load(input);

            this.id = Integer.parseInt(prop.getProperty("id"));
            this.gunName = prop.getProperty("gunName");
            System.out.print(this.gunName);
            this.gunType = Integer.parseInt(prop.getProperty("gunType"));
            this.accuracy = Integer.parseInt(prop.getProperty("accuracy"));
            this.clip = Integer.parseInt(prop.getProperty("clip"));
            this.clipSize = Integer.parseInt(prop.getProperty("clipSize"));
            this.damage = Integer.parseInt(prop.getProperty("damage"));
            this.oneHanded = Boolean.parseBoolean(prop.getProperty("oneHanded"));
            this.fireRate = Float.parseFloat(prop.getProperty("fireRate"));
            this.reloadSpeed = Float.parseFloat(prop.getProperty("reloadSpeed"));
            this.speed = Integer.parseInt(prop.getProperty("speed"));
            switch(this.id) {
                case 10 :
                    this.gunWalking = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.revolverWalking, TextureAtlas.class).getRegions());
                    this.gunReloading = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.revolverReload, TextureAtlas.class).getRegions());
                    this.gunShooting = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.revolverFiring, TextureAtlas.class).getRegions());
                    this.spriteImg = new TextureRegion(ButchGame.assets.get(ButchGame.assets.coltSprite, Texture.class), 0, 0,28, 12);
                    this.gunShotSound = ButchGame.assets.get(ButchGame.assets.gunShot, Sound.class);
                    this.reloadSoundEffect = ButchGame.assets.get(ButchGame.assets.otherReloadEffect, Sound.class);
                    this.ammoBar = ButchGame.assets.get(ButchGame.assets.revolverAmmoBar6, Texture.class);
                    break;
                case 11 :
                    this.gunWalking = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.machineGunWalking, TextureAtlas.class).getRegions());
                    this.gunReloading = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.machineGunReload, TextureAtlas.class).getRegions());
                    this.gunShooting = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.machineGunFiring, TextureAtlas.class).getRegions());
                    this.spriteImg = new TextureRegion(ButchGame.assets.get(ButchGame.assets.machineGunSprite, Texture.class), 0, 0,27, 7);
                    this.gunShotSound = ButchGame.assets.get(ButchGame.assets.gunShot, Sound.class);
                    this.reloadSoundEffect = ButchGame.assets.get(ButchGame.assets.otherReloadEffect, Sound.class);
                    this.ammoBar = ButchGame.assets.get(ButchGame.assets.machineGunAmmoBar24, Texture.class);
                    break;
                case 12:
                    this.gunWalking = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.shotgunWalking, TextureAtlas.class).getRegions());
                    this.gunReloading = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.shotgunReload, TextureAtlas.class).getRegions());
                    this.gunShooting = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.shotgunFiring, TextureAtlas.class).getRegions());
                    this.spriteImg = new TextureRegion(ButchGame.assets.get(ButchGame.assets.shotgunSprite, Texture.class), 0, 0,27, 16);
                    this.gunShotSound = ButchGame.assets.get(ButchGame.assets.gunShot, Sound.class);
                    this.reloadSoundEffect = ButchGame.assets.get(ButchGame.assets.otherReloadEffect, Sound.class);
                    this.ammoBar = ButchGame.assets.get(ButchGame.assets.shotgunAmmoBar2, Texture.class);
                    break;
                case 13:
                    this.gunWalking = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.musketWalking, TextureAtlas.class).getRegions());
                    this.gunReloading = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.musketReload, TextureAtlas.class).getRegions());
                    this.gunShooting = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.musketFiring, TextureAtlas.class).getRegions());
                    this.spriteImg = new TextureRegion(ButchGame.assets.get(ButchGame.assets.musketSprite, Texture.class), 0, 0,44, 16);
                    this.gunShotSound = ButchGame.assets.get(ButchGame.assets.gunShot, Sound.class);
                    this.reloadSoundEffect = ButchGame.assets.get(ButchGame.assets.otherReloadEffect, Sound.class);
                    this.ammoBar = ButchGame.assets.get(ButchGame.assets.musketAmmoBar1, Texture.class);
                    break;
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public void takeHit(float takeDamage) {

    }
}
