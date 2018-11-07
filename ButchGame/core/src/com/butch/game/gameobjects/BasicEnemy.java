package com.butch.game.gameobjects;

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

    public BasicEnemy(){
        sprite = new Sprite(ButchGame.assets.get(ButchGame.assets.enemySprite, Texture.class));
        sprite.setScale(10);
        position = new Vector2(0,0);
        collider = new Rectangle(this.position.x, this.position.y, sprite.getWidth() * 5, sprite.getHeight() * 5);
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
        handleMovement();
        if(health > 0){
            sprite.draw(spriteBatch);
        }
        else{
            this.active = false;
        }
    }

    @Override
    public void takeDamage(int ammoType){
        switch (ammoType){
            case 0:
                health -= 15;
            case 1:
                health -= 40;
            case 2:
                health -= 100;
        }
        System.out.println("Health:" + health);
    }

    private void move() {
        Vector2 velocity = new Vector2(moveTarget.x - this.position.x, moveTarget.y - this.position.y).nor();
        this.position = new Vector2(this.position.x + (velocity.x * speed), this.position.y + (velocity.y * speed));
        sprite.setPosition(this.position.x, this.position.y);
        collider.setPosition(this.position.x, this.position.y);
    }
}
