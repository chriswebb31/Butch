package com.butch.game.gameobjects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Enemy;

public class BasicEnemy extends Enemy {
    public boolean hasMoveTarget = false;
    public boolean completedMove = false;
    public boolean takingHit = false;
    public Sound hitSound = ButchGame.assets.get(ButchGame.assets.hitEffect, Sound.class);
    long now = System.currentTimeMillis();
    long expectedTime = 200;
    long lastHit = System.currentTimeMillis();

    public BasicEnemy(){
        sprite = new Sprite(ButchGame.assets.get(ButchGame.assets.enemySprite, Texture.class));
        sprite.setScale(10);
        position = new Vector2(0,0);
        sprite.setOriginCenter();
        collider = new Rectangle(this.sprite.getX(), this.sprite.getOriginY(), (sprite.getBoundingRectangle().width / 3) + 10, (sprite.getBoundingRectangle().height / 1.5f) + 10);
        speed = 4;
        moveRadius = 400;
        health = 100;
    }

    @Override
    public void attack() {

    }

    @Override
    public void createMove() {
        Vector2 randomMoveAmount = randomV2(moveRadius);
        moveTarget = new Vector2(this.position.x + randomMoveAmount.x, this.position.y + randomMoveAmount.y);
        hasMoveTarget = true;
        completedMove = false;
    }

    @Override
    public void handleMovement() {
        if(completedMove || !hasMoveTarget){
            createMove();
        }
        if(hasMoveTarget && !completedMove){
            float distance = (float) Math.hypot(this.position.x-moveTarget.x, this.position.y-moveTarget.y);
            if(distance <= 5){
                completedMove = true;
            }else{
                move();
            }
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        if(health > 0){
            handleMovement();
            sprite.draw(spriteBatch);
        }
        else{
            this.active = false;
        }
    }

    @Override
    public void takeDamage(int ammoType) throws InterruptedException {
        long thisHit = System.currentTimeMillis();
        if((thisHit - lastHit) >= 200) {
            takingHit = true;
            health -= 10;
            takingHit = false;
            hitSound.play(0.7f);
            System.out.println("Health:" + health);
            lastHit = thisHit;
        }
    }

    private void move() {
        Vector2 velocity = new Vector2(moveTarget.x - this.position.x, moveTarget.y - this.position.y).nor();
        this.position = new Vector2(this.position.x + (velocity.x * speed), this.position.y + (velocity.y * speed));
        sprite.setPosition(this.position.x, this.position.y);
        collider.setPosition(this.sprite.getX() - sprite.getWidth(), this.sprite.getY() - sprite.getHeight() * 3);
    }
}
