package com.butch.game.gameobjects.weapons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.Player;
import com.butch.game.gameobjects.abstractinterface.Gun;

public class Colt extends Gun {

    public Colt(Player player) {
        super(player);
        this.clipSize = 6;
        this.reserve = 80;
        this.fireRate = 0.25f;
        this.reloadSpeed = 1.5f;
        this.clip = 6;
        this.isReloading = false;
        this.isShooting = false;
        this.oneHanded = true;
        this.gunSprite = new Sprite(ButchGame.assets.get(ButchGame.assets.coltSprite, Texture.class));
        this.gunSprite.scale(5);
    }
}