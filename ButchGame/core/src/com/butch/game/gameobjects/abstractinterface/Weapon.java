package com.butch.game.gameobjects.abstractinterface;

import com.badlogic.gdx.graphics.g2d.Sprite;
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
    public Bullet bullet;
    public long fireRate;
    public long reloadSpeed;
    public Sprite sprite;

    public Weapon(){

    }

    public void Attack(){
        switch (type){
            case 1:
                //melee
                MeleeAttack();
            case 2:
                //firearm
                Shoot();
        }
    }

    public void Reload() {
        System.out.println("RELOADING!");
        if(reserve > clipSize){
            clip = clipSize;
            reserve -= clipSize;
            try {
                Thread.sleep(reloadSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            clip = reserve;
            reserve = 0;
        }
    }

    public abstract void MeleeAttack();

    public abstract void Shoot();
}
