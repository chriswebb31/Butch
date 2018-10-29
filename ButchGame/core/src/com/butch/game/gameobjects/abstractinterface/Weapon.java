package com.butch.game.gameobjects.abstractinterface;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.audio.Sound;
import com.butch.game.gameobjects.Player;

public abstract class Weapon {
    public Player player;
    public int type;
    private float range;
    private float damage;
    private float accuracy;
    public float clip;
    public float clipSize;
    public float reserve;
    public boolean isShootingActive = false;
    public boolean isReloading = false;
    public Class bulletType;
    public float fireRate;
    public long reloadSpeed;
    public Sprite sprite;
    public Vector2 position;
    public int targetPos;
    public Sound gunShot;

    public Weapon() {

    }

    public void Attack() {
        switch (type) {
            case 1:
                //melee
                MeleeAttack();
            case 2:
                //firearm
                if (!isReloading)
                    Shoot();
        }
    }

    public abstract void Reload();

    public abstract void updatePosition(Vector2 targetDirection);

    public abstract void updateRotation(Vector2 targetDirection);

    public abstract void MeleeAttack();

    public abstract void Shoot();
}