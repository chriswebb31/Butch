package com.tpgame.demo.butchobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputManager {
    public float horizontalAxis = 0;
    public float verticalAxis = 0;

    public InputManager(){

    }

    public void update(){
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            horizontalAxis = -1;
        }
        else if(Gdx.input.isKeyPressed((Input.Keys.D))){
            horizontalAxis = 1;
        }
        else{
            horizontalAxis = 0;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            verticalAxis = 1;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.S)){
            verticalAxis = -1;
        }
        else{
            verticalAxis = 0;
        }

    }
}
