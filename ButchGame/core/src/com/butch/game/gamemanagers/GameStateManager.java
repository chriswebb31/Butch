package com.butch.game.gamemanagers;

import com.badlogic.gdx.Gdx;

public class GameStateManager {
    private static int GAME_STATE = 0;

    public  GameStateManager(){

    }

    public static int getGameState() {
        return GAME_STATE;
    }

    public static void setGameState(int gameState) {
        GAME_STATE = gameState;
    }

    public void update(){
        if (GAME_STATE > 0){
            Gdx.app.exit();
            System.exit(0);
        }
    }
}
