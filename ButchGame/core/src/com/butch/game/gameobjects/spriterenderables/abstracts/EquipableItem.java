package com.butch.game.gameobjects.spriterenderables.abstracts;

import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.SpriteRenderable;
import com.butch.game.gameobjects.spriterenderables.NewPlayer;

public class EquipableItem extends SpriteRenderable {
    public boolean oneHanded;
    private NewPlayer player;

    public EquipableItem(NewPlayer player){
        this.player = player;
    }

    @Override
    public void update(float delta) {
        Vector2 targetDir = new Vector2(ButchGame.mousePosition().x, ButchGame.mousePosition().y);
        float angle = (float) Math.atan2(targetDir.y - this.sprite.getY(), targetDir.x - this.sprite.getX());
        angle = (float) Math.toDegrees(angle);
        if(this.oneHanded)
            position = player.getWeaponPosition();
        else{
            position = player.position;
        }
        try{
            this.sprite.setRotation(angle);
            this.sprite.setPosition(position.x, position.y);
        } catch (NullPointerException e){
            System.out.println(e);
        }
    }
}

