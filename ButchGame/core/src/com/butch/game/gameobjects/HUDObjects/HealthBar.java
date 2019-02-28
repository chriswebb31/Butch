package com.butch.game.gameobjects.HUDObjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.butch.game.ButchGame;
import com.butch.game.gamemanagers.Utils;
import com.badlogic.gdx.graphics.Color;

public class HealthBar {
private Sprite bg, fg;
private float x, y, width, height;

    public HealthBar(boolean enemy, float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        if (enemy == true) {
        bg = new Sprite (ButchGame.assets.get(ButchGame.assets.enemyHBBG, Texture.class));
        fg = new Sprite (ButchGame.assets.get(ButchGame.assets.enemyHBFG, Texture.class));

        } else {
        bg = new Sprite (ButchGame.assets.get(ButchGame.assets.playerHBBG, Texture.class));
        fg = new Sprite (ButchGame.assets.get(ButchGame.assets.playerHBFG, Texture.class));
        }
    }

    public void render (SpriteBatch batch){
        update(x,y,width,height);
        batch.draw(bg,x,y, width,height);
        batch.draw(fg,x,y, width,height);
    }
    public void update (float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }
}









//public class HealthBar extends ProgressBar{

//    public HealthBar(int width, int height, float x, float y) {
//        super(0f, 1f, 0.01f, false, new ProgressBarStyle());
//
//        getStyle().background = Utils.getColoredDrawable(width, height, Color.RED);
//        getStyle().knob = Utils.getColoredDrawable(0, height, Color.GREEN);
//        getStyle().knobBefore = Utils.getColoredDrawable(width, height, Color.GREEN);
//
//        setWidth(width);
//        setHeight(height);
//        setPosition(x,y);
//        setAnimateDuration(0.0f);
//        setValue(1f);
//
//        setAnimateDuration(0.25f);
//    }
//}
