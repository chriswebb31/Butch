package com.butch.game.gameobjects.weapons;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.Player;
import com.butch.game.gameobjects.abstractinterface.Gun;
import com.butch.game.screens.GameScreen;

public class Rifle extends Gun {
    public Rifle(Player player, GameScreen game) {
        super(player, game);
        this.clipSize = 1;
        this.gunType = 0;
        this.reserve = 50;
        this.fireRate = 0.1f;
        this.reloadSpeed = 0.85f;
        this.accuracy = 50;
        this.isShooting=false;
        this.isReloading = false;
        this.oneHanded = false;
        this.gunSprite = new Sprite(ButchGame.assets.get(ButchGame.assets.rifleSprite, Texture.class));
        this.gunShotSound = ButchGame.assets.get(ButchGame.assets.gunShot, Sound.class);
        this.reloadSoundEffect = ButchGame.assets.get(ButchGame.assets.otherReloadEffect, Sound.class);
        this.gunSprite.setScale(10);
    }
}
