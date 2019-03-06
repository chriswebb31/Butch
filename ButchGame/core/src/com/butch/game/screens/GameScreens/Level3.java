package com.butch.game.screens.GameScreens;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Gun;

import java.util.ArrayList;

public class Level3 extends ModelGameScreen {
    public static TiledMap map = ButchGame.assets.get(ButchGame.assets.caveTransition);


    public Level3(int level, ButchGame game, FitViewport gameViewPort, ArrayList<Gun> weapons){
    super(level, game, gameViewPort,map);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta){
        updateCameraPosition();

        if (player.getCollider().overlaps(endPoint)) {
            game.setScreen(new PrisonLevel(4, game, gameViewPort, player.getGunInventory()));
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
