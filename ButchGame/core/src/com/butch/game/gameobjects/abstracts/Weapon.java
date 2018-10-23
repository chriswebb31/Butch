package com.butch.game.gameobjects.abstracts;

public abstract class Weapon {
    public int type;
    public Bullet bullet;
    private float range;
    private float damage;
    private float accuracy;
    private float clip;
    public float clipSize;
    public float reserve;
    private boolean isShootingActive;

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

    public void Reload(){
        System.out.println("RELOADING!");
        if(reserve > clipSize){
            clip = clipSize;
            reserve -= clipSize;
        } else {
            clip = reserve;
            reserve = 0;
        }
    }

    private void MeleeAttack(){

    }

    private void Shoot(){
        if(clip > 0 && !isShootingActive){
            isShootingActive = true;

            clip -= 1;
            System.out.println("RESERVE : " + reserve + " CLIP : " + clip);
            System.out.println("BANG");
            isShootingActive = false;
        } else{
            isShootingActive = false;
            Reload();
        }
    }
}
