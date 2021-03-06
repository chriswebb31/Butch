package com.butch.game.gameobjects.spriterenderables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Renderable;

import java.util.Random;

public class Blood extends Renderable {
    public Random random;
    public Vector2 offsetLanding;
    public int newRotation;

    public Blood(Vector2 spawn){
        this.setSprite(new Sprite(ButchGame.assets.get(ButchGame.assets.bloodSprite, Texture.class)));
        this.setPosition(spawn);
        this.activeCollision = false;
        this.activeForRender = true;
        int max = 100;
        int min = 0;
        int minAngle = 0;
        int maxAngle = 360;

        random = new Random();
        int x = min + random.nextInt(max);
        int y = min + random.nextInt(max);
        newRotation = minAngle + random.nextInt(maxAngle);
        offsetLanding = new Vector2(this.getPosition().x + x, this.getPosition().y + y);


    }

    @Override
    public void update(float delta) {
        if(this.getPosition() != offsetLanding){
            this.setPosition(this.getPosition().lerp(offsetLanding, 0.1f));
        }
        if(this.getSprite().getRotation() != newRotation){
            Vector2 angleHolder = new Vector2(0, this.getSprite().getRotation());
            angleHolder.lerp(new Vector2(0, this.newRotation), 0.1f);
            this.getSprite().setRotation(angleHolder.y);
        }

        this.getSprite().setPosition(this.getPosition().x, this.getPosition().y);
    }

    @Override
    public void takeHit(float damage) {

    }
}
