package com.butch.game.gamemanagers;

import com.badlogic.gdx.Gdx;

public class GameStateManager {
    //CLASS USED TO MANAGE PLAYER STATE AND GAME STATE
    private static int GAME_STATE = 0; //0:RUN
    private static int PLAYER_STATE = 0; //0:ALIVE 1:DEAD

    public  GameStateManager(){

    }

    public static int getGameState() {
        return GAME_STATE;
    }

    public static void setGameState(int gameState) {
        GAME_STATE = gameState;
    }

    public static int getPlayerState() {
        return PLAYER_STATE;
    }

    public static void setPlayerState(int playerState) {
        PLAYER_STATE = playerState;
    }

    public void update(){
        if (GAME_STATE > 0){
            Gdx.app.exit();
            System.exit(0);
        }
    }
}
