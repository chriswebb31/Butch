package com.butch.game.gamemanagers;

import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Gun;
import com.butch.game.gameobjects.abstractinterface.ItemPickup;
import com.butch.game.gameobjects.spriterenderables.Player;
import com.butch.game.screens.NewGameScreen;

import java.util.ArrayList;

public class GameStateManager {
    public Player playerObject;
    private int lives;
    //CLASS USED TO MANAGE PLAYER STATE AND GAME STATE
    public int coins;

    public ArrayList<Gun> weaponInventory;
    public ArrayList<ItemPickup> itemInventory;

    public int level;
    private FitViewport viewport;
    private ButchGame game;

    public GameStateManager(FitViewport viewPort,ButchGame game) {
        this.viewport = viewPort;
        this.game = game;
    }

    public void update(){
        if(playerObject != null){
            try{
                if(playerObject.health <= 0){
                    death(playerObject.coin, Player.gunInventory, Player.itemInventory);
                }
            } catch (NullPointerException e){
                e.printStackTrace();
            }
        }
    }

    public void death(int coins, ArrayList<Gun> weaponInventory, ArrayList<ItemPickup> itemInventory){
        if(lives > 0) {
//            playerObject.setPosition(level.spawnPoint);
            playerObject.health = 100;
            lives -= 1;
        }
        else{
            game.setScreen(new NewGameScreen(game, viewport));
        }
    }

    public void setLevel(int level_id){
        this.level = level_id;
    }
}