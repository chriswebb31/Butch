package com.butch.game.screens.GameScreens;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.math.Rectangle;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Gun;

import java.util.ArrayList;

public class Level3 extends ModelGameScreen {
    public static TiledMap map = ButchGame.assets.get(ButchGame.assets.caveTransition);
    private int coinCounter;

    public Level3(int levelNumber, ButchGame game, FitViewport gameViewPort, TiledMap map, ArrayList<Gun> weaponCache, int playerLevel, int spawnLocation){
        super(levelNumber, game, gameViewPort, map, weaponCache, playerLevel, spawnLocation);
        coinCounter = levelNumber;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta){
        updateCameraPosition();

        for(Rectangle endPointLoc : endPoints) {
            if(player.getCollider().overlaps(endPointLoc)) {
                if(endPoints.indexOf(endPointLoc) == 0) {
                    game.setScreen( new Level2(player.coin, game, gameViewPort, Level2.map, player.getGunInventory(), player.getPlayerLevel(), 1));
                } else if (endPoints.indexOf(endPointLoc) == 1) {
                    game.setScreen((new Route3(player.coin, game, gameViewPort, Route3.map ,player.getGunInventory(), player.getPlayerLevel(), 0)));
                } else if (endPoints.indexOf(endPointLoc) == 2) {
                    game.setScreen(new Cave(player.coin, game, gameViewPort, Cave.map, player.getGunInventory(), player.getPlayerLevel(), 0));
                }
            }
        }
        super.render(delta);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
