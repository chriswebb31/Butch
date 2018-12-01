package com.butch.game.gameobjects.HUDObjects;

import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.butch.game.gamemanagers.Utils;
import com.badlogic.gdx.graphics.Color;

public class HealthBar extends ProgressBar{
    private int health;
    public HealthBar(int width, int height) {
        super(0f, 1f, 0.01f, false, new ProgressBarStyle());

        getStyle().background = Utils.getColoredDrawable(width, height, Color.RED);
        getStyle().knob = Utils.getColoredDrawable(0, height, Color.GREEN);
        getStyle().knobBefore = Utils.getColoredDrawable(width, height, Color.GREEN);

        setWidth(width);
        setHeight(height);
        ///////////
        ProgressBarStyle progressBarStyle = new ProgressBarStyle();
       // progressBarStyle.background = drawable;
        //////////////////////////////
        setAnimateDuration(0.0f);
        setValue(1f);

        setAnimateDuration(0.25f);
    }
    public int getHealth(){
        return health;
    }
}
