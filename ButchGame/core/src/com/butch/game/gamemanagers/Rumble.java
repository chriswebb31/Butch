package com.butch.game.gamemanagers;

import com.badlogic.gdx.math.Vector3;

import java.util.Random;
/*
    CLASS : RUMBLE

    Used to add random units for a rumble effect for the camera
    Credit: https://carelesslabs.wordpress.com/2017/08/14/making-a-libgdx-roguelike-survival-game-part-9-screen-shake-inventory-gamedev/
 */
public class Rumble {
    private static float time = 0;
    private static float currentTime = 0;
    private static float power = 0;
    private static float currentPower = 0;
    private static Random random;
    private static Vector3 pos = new Vector3();

    public static void rumble(float rumblePower, float rumbleLength) {
        random = new Random();
        power = rumblePower;
        time = rumbleLength;
        currentTime = 0;
    }

    public static Vector3 tick(float delta) {
        if (currentTime <= time) {
            currentPower = power * ((time - currentTime) / time);

            pos.x = (random.nextFloat() - 0.5f) * 2 * currentPower;
            pos.y = (random.nextFloat() - 0.5f) * 2 * currentPower;

            currentTime += delta;
        } else {
            time = 0;
        }
        return pos;
    }

    public static float getRumbleTimeLeft() {
        return time;
    }

    public static Vector3 getPos() {
        return pos;
    }
}