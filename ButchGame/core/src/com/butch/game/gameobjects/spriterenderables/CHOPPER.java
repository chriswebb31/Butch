package com.butch.game.gameobjects.spriterenderables;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.spriterenderables.abstracts.NewGun;

public class CHOPPER extends NewGun {

    public CHOPPER(NewPlayer player) {
        super(player);
        this.accuracy = 100;
        this.clip = 25;
        this.clipSize = 25;
        this.damage = 25;
        this.oneHanded = false;
        this.reserve = 125;

        this.setSprite(new Sprite(ButchGame.assets.get(ButchGame.assets.machineGunSprite, Texture.class)));
        this.getSprite().setScale(10);
        this.gunShotSound = ButchGame.assets.get(ButchGame.assets.gunShot, Sound.class);
        this.reloadSoundEffect = ButchGame.assets.get(ButchGame.assets.otherReloadEffect, Sound.class);
    }


}
