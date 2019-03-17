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

    public Level3(ButchGame game, FitViewport gameViewPort, TiledMap map, int spawnLocation){
        super(game, gameViewPort, map, spawnLocation);
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
                    game.setScreen( new Level2(game, gameViewPort, Level2.map, 1));
                    updateSave(2);
                } else if (endPoints.indexOf(endPointLoc) == 1) {
                    game.setScreen((new Route3(game, gameViewPort, Route3.map, 0)));
                    updateSave(4);
                } else if (endPoints.indexOf(endPointLoc) == 2) {
                    game.setScreen(new Cave(game, gameViewPort, Cave.map, 0));
                    updateSave(5);
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
