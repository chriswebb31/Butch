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
    /*
        CLASS : EQUIPABLEITEM

        Used to create objects that go in inventory space, such as weapons.
        works for Enemies and Players
     */
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
                sprite.setSize(getFrame(delta).getRegionWidth(), getFrame(delta).getRegionHeight());
                this.setSprite(sprite);
                this.getSprite().setScale(8);
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
                    if (ButchGame.mousePosition().x >= player.getPosition().x) { // if direction is right
                        this.getSprite().setFlip(false, false);
                        if(this.oneHanded)
                            this.getSprite().setPosition(player.getSprite().getX() + 20, player.getSprite().getY() - 100);
                        else
                            this.getSprite().setPosition(player.getSprite().getX() + 10, player.getSprite().getY() - 80);
                    } else if (ButchGame.mousePosition().x < player.getPosition().x){ //if direction is left or not right
                        //this.getSprite().setFlip(true, false);
                        if(this.oneHanded)
                            if(player.getState() == Player.State.IDLE)
                                this.getSprite().setPosition(player.getSprite().getX() - 120, player.getSprite().getY() - 80);
                            else
                                this.getSprite().setPosition(player.getSprite().getX() - 120, player.getSprite().getY() - 40);
                        else
                            if(player.getState() == Player.State.IDLE)
                                this.getSprite().setPosition(player.getSprite().getX() - 120, player.getSprite().getY() - 90);
                            else
                                this.getSprite().setPosition(player.getSprite().getX() - 120, player.getSprite().getY() - 50);
                        this.getSprite().setFlip(false, true); //

                    }
                } catch (NullPointerException e){
                    System.out.println(e);
                }
            } else if(parent.TAG == "enemy"){
                sprite.setRegion(getFrame(delta));
                sprite.setSize(getFrame(delta).getRegionWidth(), getFrame(delta).getRegionHeight());
                this.setSprite(sprite);
                this.getSprite().setScale(8);
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

                    try {
                        this.getSprite().setPosition(this.getPosition().x, this.getPosition().y);
                        if (targetDir.x >= this.getPosition().x) { // if direction is right
                            //this.getSprite().setFlip(false, false);
                            this.getSprite().setPosition(enemy.getSprite().getX() + 10, enemy.getSprite().getY() - 80);
                            this.getSprite().setFlip(false, false);
                        } else if (targetDir.x < this.getPosition().x) { //if direction is left or not right
                            //this.getSprite().setFlip(true, false);
                            this.getSprite().setFlip(false, true); //
                            this.getSprite().setPosition(enemy.getSprite().getX() - 120, enemy.getSprite().getY() - 50);
                        }

                    } catch (NullPointerException e) {
                        System.out.println(e);
                    }
                }
            }
        }


//        this.getSprite().setPosition(this.getPosition().x, this.getPosition().y);
    }

    public TextureRegion getFrame(float dt) {
        TextureRegion region = null;
        currentState = GetState();
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
                    //region = player.getActiveWeapon().gunWalking.getKeyFrame(stateTimer, false);
                    region = player.getActiveWeapon().spriteImg;
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
}

