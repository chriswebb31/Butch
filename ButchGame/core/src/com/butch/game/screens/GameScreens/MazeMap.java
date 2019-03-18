package com.butch.game.screens.GameScreens;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;

public class MazeMap extends ModelGameScreen{
    public static TiledMap map = ButchGame.assets.get(ButchGame.assets.mazeMap);
    private int coinCounter;
    public MazeMap(ButchGame game, FitViewport gameViewPort, TiledMap map, int spawnLocation) {
        super( game, gameViewPort, map, spawnLocation);
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
                    updateSave(1);
                    game.setScreen(new NewGameScreen(game, gameViewPort, NewGameScreen.map, 0));
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
