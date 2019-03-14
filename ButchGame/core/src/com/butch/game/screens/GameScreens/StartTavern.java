package com.butch.game.screens.GameScreens;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Gun;

import java.util.ArrayList;

public class StartTavern extends ModelGameScreen {
    public static TiledMap map = ButchGame.assets.get(ButchGame.assets.startTavern);
    Music tavernMusic,playSound;
    public StartTavern(int levelNumber, ButchGame game, FitViewport gameViewPort, TiledMap map, int playerLevel, int spawnLocation) {
        super(levelNumber, game, gameViewPort, map, playerLevel, spawnLocation);
        tavernMusic = ButchGame.assets.get(ButchGame.assets.saloonBackNoise1, Music.class);
        tavernMusic.setVolume(1.0f);
        music.pause();
        tavernMusic.play();
        tavernMusic.setLooping(true);
        playSound = ButchGame.assets.get(ButchGame.assets.playSound, Music.class);
    }
    public StartTavern(int levelNumber, ButchGame game, FitViewport gameViewPort, TiledMap map, ArrayList<Gun> weaponCache, int playerLevel, int spawnLocation) {
        super(levelNumber, game, gameViewPort, map, weaponCache, playerLevel, spawnLocation);
        tavernMusic = ButchGame.assets.get(ButchGame.assets.saloonBackNoise1, Music.class);
        tavernMusic.setVolume(1.0f);
        music.pause();
        tavernMusic.play();
        tavernMusic.setLooping(true);
        playSound = ButchGame.assets.get(ButchGame.assets.playSound, Music.class);
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

                    playSound.play();
                    playSound.setOnCompletionListener(new Music.OnCompletionListener() {
                        @Override
                        public void onCompletion(Music music) {

                            music.play();
                            tavernMusic.dispose();
                            playSound.dispose();
                            game.setScreen( new NewGameScreen(1, game, gameViewPort, NewGameScreen.map, player.getGunInventory(),  player.getPlayerLevel(), 0));
                        }
                    });

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
