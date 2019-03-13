package com.butch.game.screens.GameScreens;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.math.Rectangle;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Gun;

import java.util.ArrayList;

public class Cave extends ModelGameScreen {
    public static TiledMap map = ButchGame.assets.get(ButchGame.assets.cave);
    public Cave(int level, ButchGame game, FitViewport gameViewPort, ArrayList<Gun> weapons, int playerLevel, int spawnLocation) {
        super(level, game, gameViewPort, map, playerLevel, spawnLocation);
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
                    game.setScreen( new PrisonLevel(1, game, gameViewPort, player.getGunInventory(),  player.getPlayerLevel(), 0));
                } else if (endPoints.indexOf(endPointLoc) == 2) {
                    game.setScreen((new Level3(2, game, gameViewPort, player.getGunInventory(), player.getPlayerLevel(), 2)));
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
