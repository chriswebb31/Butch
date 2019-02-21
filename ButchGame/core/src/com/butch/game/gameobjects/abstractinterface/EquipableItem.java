package com.butch.game.gameobjects.abstractinterface;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.spriterenderables.Enemy;
import com.butch.game.gameobjects.spriterenderables.Player;

public abstract class EquipableItem extends Renderable {
    public enum State {MOVING, IDLE, SHOOTING, RELOADING}
    public boolean oneHanded;
    public Renderable parent;
    boolean friendly;
    public Enemy enemy;
    public Player player;
    private State currentState, previousState;
    private float stateTimer = 0;
    private Sprite sprite = new Sprite(ButchGame.assets.get(ButchGame.assets.machineGunSprite, Texture.class));


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
                sprite.setRegion(getFrame(delta));
                this.setSprite(sprite);
                this.getSprite().setScale(10);
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
                    if (ButchGame.mousePosition().x >= this.getPosition().x) { // if direction is right
                        //this.getSprite().setFlip(false, false);
                        this.getSprite().setFlip(false, false);
                    } else if (ButchGame.mousePosition().x < this.getPosition().x){ //if direction is left or not right
                        //this.getSprite().setFlip(true, false);
                        this.getSprite().setFlip(false, true); //
                    }
                } catch (NullPointerException e){
                    System.out.println(e);
                }
            } else if(parent.TAG == "enemy"){
                sprite.setRegion(getFrame(delta));
                this.setSprite(sprite);
                this.getSprite().setScale(10);
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


//        this.getSprite().setPosition(this.getPosition().x, this.getPosition().y);
    }

    public TextureRegion getFrame(float dt) {
        TextureRegion region = null;
        currentState = GetState();

//        switch(currentState) {
//            case MOVING:
//                region = player.getActiveWeapon().gunWalking.getKeyFrame(stateTimer, true);
//                break;
//        }
        if(parent.TAG == "player")
            switch(currentState) {
                case RELOADING:
                    region = player.getActiveWeapon().gunReloading.getKeyFrame(stateTimer, false);
                    break;
                case SHOOTING:
                    region = player.getActiveWeapon().gunShooting.getKeyFrame(stateTimer, true);
                    break;
                case MOVING:
                    region = player.getActiveWeapon().gunWalking.getKeyFrame(stateTimer, true);
                    break;
                case IDLE:
                    region = player.getActiveWeapon().gunWalking.getKeyFrame(stateTimer, false);
            }

        else if(parent.TAG == "enemy")
            region = enemy.getActiveWeapon().gunWalking.getKeyFrame(stateTimer, true);
        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;

        return region;
    }

    public State GetState() {
        if(parent.TAG == "player") {
            if (player.getActiveWeapon().getState() == Gun.State.RELOADING)
                return State.RELOADING;
            else if (Gdx.input.isButtonPressed(Input.Buttons.LEFT))
                return State.SHOOTING;
            else if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.D))
                return State.MOVING;
            else
                return State.IDLE;
        } else
            return State.IDLE;
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

