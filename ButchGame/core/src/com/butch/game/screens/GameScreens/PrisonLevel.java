package com.butch.game.screens.GameScreens;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Gun;

import java.util.ArrayList;


public class PrisonLevel extends ModelGameScreen {
    public static TiledMap map = ButchGame.assets.get(ButchGame.assets.prison);

    public PrisonLevel(int level, ButchGame game, FitViewport gameViewPort, ArrayList<Gun> weapons, int playerLevel) {
        super(level, game, gameViewPort,map, playerLevel);

}

    @Override
    public void show() {

    }

    @Override
    public void render(float delta){
        updateCameraPosition();
        if(player.getCollider().overlaps(endPoint)){
            game.setScreen( new NewGameScreen(1,game, gameViewPort,NewGameScreen.map));
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
