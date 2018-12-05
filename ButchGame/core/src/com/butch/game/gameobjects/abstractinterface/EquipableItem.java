package com.butch.game.gameobjects.abstractinterface;

import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.spriterenderables.Enemy;
import com.butch.game.gameobjects.spriterenderables.Player;

public abstract class EquipableItem extends Renderable {
    public boolean oneHanded;
    public Renderable parent;
    boolean friendly;
    public Enemy enemy;
    public Player player;


    public EquipableItem(){

    }

    @Override
    public void update(float delta) {
        if(parent!=null){
            if (parent.TAG == "player"){
                player = (Player) parent;
            }else{
                enemy = (Enemy) parent;
            }

            if(parent.TAG == "player"){
                Vector2 targetDir = new Vector2(ButchGame.mousePosition().x, ButchGame.mousePosition().y);
                float angle = (float) Math.atan2(targetDir.y - this.getSprite().getY(), targetDir.x - this.getSprite().getX());
                angle = (float) Math.toDegrees(angle);
                try{
                    if(this.oneHanded)
                        this.setPosition(player.getWeaponPosition());
                    else{
                        this.setPosition(new Vector2(player.getPosition().x, player.getPosition().y - 60));
                    }
                } catch (NullPointerException e){
                    e.printStackTrace();
                }
                try{
                    this.getSprite().setRotation(angle);
                    this.getSprite().setPosition(this.getPosition().x, this.getPosition().y);
                } catch (NullPointerException e){
                    System.out.println(e);
                }
            } else if(parent.TAG == "enemy"){
                if(this.oneHanded){
                    this.setPosition(enemy.getWeaponPosition());
                }else{
                    this.setPosition(enemy.getPosition());
                }
                if(enemy.target != null) {
                    Vector2 targetDir = new Vector2(enemy.target.getPosition().x, enemy.target.getPosition().y);
                    float angle = (float) Math.atan2(targetDir.y - this.getSprite().getY(), targetDir.x - this.getSprite().getX());
                    angle = (float) Math.toDegrees(angle);
                    this.getSprite().setRotation(angle);
                    }
                try{
                    this.getSprite().setPosition(this.getPosition().x, this.getPosition().y);
                } catch (NullPointerException e){
                    System.out.println(e);
                }
            }
        }
    }
//    public boolean oneHanded;
//    private Player player;
//
//    public EquipableItem(Player player){
//        this.player = player;
//    }
//
//    @Override
//    public void update(float delta) {
//        Vector2 targetDir = new Vector2(ButchGame.mousePosition().x, ButchGame.mousePosition().y);
//        float angle = (float) Math.atan2(targetDir.y - this.sprite.getY(), targetDir.x - this.sprite.getX());
//        angle = (float) Math.toDegrees(angle);
//        if(this.oneHanded)
//            position = player.getWeaponPosition();
//        else{
//            position = player.position;
//        }
//        try{
//            this.sprite.setRotation(angle);
//            this.sprite.setPosition(position.x, position.y);
//        } catch (NullPointerException e){
//            System.out.println(e);
//        }
//    }
}

