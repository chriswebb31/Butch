package com.butch.game.screens.GameScreens;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Gun;

import java.util.ArrayList;


public class Level2 extends ModelGameScreen {
    public static TiledMap map = ButchGame.assets.get(ButchGame.assets.route1);
    private int coinCounter;
    public Level2(ButchGame game, FitViewport gameViewPort, TiledMap map, int spawnLocation){
        super(game, gameViewPort, map, spawnLocation);
            }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        updateCameraPosition();

        for(Rectangle endPointLoc : endPoints) {
            if(player.getCollider().overlaps(endPointLoc)) {
                if(endPoints.indexOf(endPointLoc) == 0) {
                    game.setScreen( new NewGameScreen(game, gameViewPort, NewGameScreen.map, 2));
                    updateSave(1);
                } else if (endPoints.indexOf(endPointLoc) == 1) {
                    game.setScreen((new Level3(game, gameViewPort, Level3.map, 0)));
                    updateSave(3);
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
