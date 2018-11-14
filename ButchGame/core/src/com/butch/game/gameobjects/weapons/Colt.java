package com.butch.game.gameobjects.weapons;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Gun;
import com.butch.game.gameobjects.spriterenderables.NewPlayer;

public class Colt extends Gun {

    public Colt(NewPlayer player) {
        super(player);
        this.clipSize = 6;
        this.gunType = 0;
        this.reserve = 80;
        this.fireRate = 0.25f;
        this.reloadSpeed = 1.5f;
        this.clip = 6;
        this.isReloading = false;
        this.isShooting = false;
        this.oneHanded = true;
        this.gunSprite = new Sprite(ButchGame.assets.get(ButchGame.assets.coltSprite, Texture.class));
        this.gunShotSound = ButchGame.assets.get(ButchGame.assets.gunShot, Sound.class);
        this.reloadSoundEffect = ButchGame.assets.get(ButchGame.assets.revolverReloadEeffect, Sound.class);
        this.gunSprite.setScale(10);
    }
}