package com.butch.game.gameobjects.HUDObjects;

import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.butch.game.gamemanagers.Utils;
import com.badlogic.gdx.graphics.Color;

public class HealthBar extends ProgressBar{

    public HealthBar(int width, int height, float x, float y) {
        super(0f, 1f, 0.01f, false, new ProgressBarStyle());

        getStyle().background = Utils.getColoredDrawable(width, height, Color.RED);
        getStyle().knob = Utils.getColoredDrawable(0, height, Color.GREEN);
        getStyle().knobBefore = Utils.getColoredDrawable(width, height, Color.GREEN);

        setWidth(width);
        setHeight(height);
        setPosition(x,y);
        setAnimateDuration(0.0f);
        setValue(1f);

        setAnimateDuration(0.25f);
    }
}
