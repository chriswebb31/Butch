package com.butch.game.screens.GameScreens;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.math.Rectangle;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Gun;

import java.util.ArrayList;


public class PrisonLevel extends ModelGameScreen {
    public static TiledMap map = ButchGame.assets.get(ButchGame.assets.prison);
    private int coinCounter;
    Music prisonMusic;
    public PrisonLevel(ButchGame game, FitViewport gameViewPort, TiledMap map, int spawnLocation) {
        super(game, gameViewPort, map, spawnLocation);
        prisonMusic = ButchGame.assets.get(ButchGame.assets.prisonMusic1, Music.class);
        prisonMusic.setVolume(0.3f);
        music.pause();
        prisonMusic.play();
        prisonMusic.setLooping(true);
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
                    prisonMusic.pause();
                    music.play();
                    game.setScreen( new SnowyMountain(game, gameViewPort, SnowyMountain.map, 0));
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
    prisonMusic.pause();
        this.dispose();

    }
}
