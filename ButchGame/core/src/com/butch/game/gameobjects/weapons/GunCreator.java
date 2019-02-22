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
                case 11 :
                    this.gunWalking = new Animation<TextureRegion>(0.1f, ButchGame.assets.get(ButchGame.assets.machineGunWalking, TextureAtlas.class).getRegions());
                    this.gunReloading = new Animation<TextureRegion>(0.1f, ButchGame.assets.get(ButchGame.assets.machineGunReload, TextureAtlas.class).getRegions());
                    this.gunShooting = new Animation<TextureRegion>(0.1f, ButchGame.assets.get(ButchGame.assets.machineGunFiring, TextureAtlas.class).getRegions());
                    this.spriteImg = new TextureRegion(ButchGame.assets.get(ButchGame.assets.machineGunSprite, Texture.class), 0, 0,27, 7);
                    this.gunShotSound = ButchGame.assets.get(ButchGame.assets.gunShot, Sound.class);
                    this.reloadSoundEffect = ButchGame.assets.get(ButchGame.assets.otherReloadEffect, Sound.class);
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
