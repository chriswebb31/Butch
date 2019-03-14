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
    public PrisonLevel(int levelNumber, ButchGame game, FitViewport gameViewPort, TiledMap map, ArrayList<Gun> weaponCache, int playerLevel, int spawnLocation) {
        super(levelNumber, game, gameViewPort, map, weaponCache, playerLevel, spawnLocation);
        coinCounter = levelNumber;
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
                    game.setScreen( new SnowyMountain(player.coin, game, gameViewPort, SnowyMountain.map, player.getGunInventory(),  player.getPlayerLevel(), 0));

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
