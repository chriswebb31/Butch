package com.butch.game.screens.GameScreens;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.math.Rectangle;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Gun;

import java.util.ArrayList;

public class BigTown extends ModelGameScreen {
    public static TiledMap map = ButchGame.assets.get(ButchGame.assets.bigBoyTown);
    public BigTown(ButchGame game, FitViewport gameViewPort, TiledMap map, int spawnLocation) {
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
                if(endPoints.indexOf(endPointLoc) == 1) {
                    game.setScreen( new Warzone(game, gameViewPort, Warzone.map, 0));
                    updateSave(10);
                } else if (endPoints.indexOf(endPointLoc) == 0) {
                    game.setScreen((new SnowyMountain(game, gameViewPort, SnowyMountain.map, 1)));
                    updateSave(8);
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
