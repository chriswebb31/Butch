package com.butch.game.gameobjects.weapons;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.Player;
import com.butch.game.gameobjects.abstractinterface.Gun;

public class MachineGun extends Gun {

    public MachineGun(Player player) {
        super(player);
        this.clipSize = 25;
        this.gunType = 0;
        this.reserve = 100;
        this.clip = 25;
        this.fireRate = 0.2f;
        this.reloadSpeed = 2;
        this.isReloading = false;
        this.accuracy = 100;
        this.isShooting = false;
        this.oneHanded = false;
        this.gunSprite = new Sprite(ButchGame.assets.get(ButchGame.assets.machineGunSprite, Texture.class));
        this.gunShotSound = ButchGame.assets.get(ButchGame.assets.gunShot, Sound.class);
        this.gunSprite.setScale(10);
    }
}
