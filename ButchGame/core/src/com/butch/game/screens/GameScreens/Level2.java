package com.butch.game.screens.GameScreens;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Gun;

import java.util.ArrayList;


public class Level2 extends ModelGameScreen {
    public static TiledMap map = ButchGame.assets.get(ButchGame.assets.route1);
    public Level2(int level, ButchGame game, FitViewport gameViewPort, ArrayList<Gun> weapons, TiledMap map, int playerLevel, int spawnLocation){
        super(level,game,gameViewPort, map ,playerLevel, spawnLocation);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        updateCameraPosition();

        if (player.getCollider().overlaps(endPoint)) {
            game.setScreen(new Level3(3,game, gameViewPort, player.getGunInventory(), player.getPlayerLevel(), 0));
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
