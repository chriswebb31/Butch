package com.butch.game.screens.GameScreens;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.math.Rectangle;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.HUDObjects.Hud;
import com.butch.game.gameobjects.abstractinterface.Gun;
import com.butch.game.gameobjects.spriterenderables.Player;

import java.util.ArrayList;

public class BigTown extends ModelGameScreen {
    public static TiledMap map = ButchGame.assets.get(ButchGame.assets.bigBoyTown);


    public BigTown(ButchGame game, FitViewport gameViewPort, TiledMap map, int spawnLocation) {
        super(game, gameViewPort, map, spawnLocation);
        music.pause();
        bigCityMusic.play();
        bigCityMusic.setVolume(1.0f);
        bigCityMusic.setLooping(true);
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
                    updateSave(10);
                    bigCityMusic.stop();
                    music.play();
                    player.isAllowedToMove = false;
                    player.xAxis = 0;
                    player.yAxis = 0;
                    player.getFrame(delta, Player.State.IDLE);
                    Hud.stage.addAction(Actions.sequence(Actions.fadeOut(1),Actions.run(new Runnable(){
                        @Override
                        public void run() {
                            Hud.stage.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(1)));
                            game.setScreen(new Warzone(game, gameViewPort, Warzone.map, 0));
                        }
                    })));
                    playSound.setVolume(0);
                    playSound.play();
                    playSound.setOnCompletionListener(new Music.OnCompletionListener() {
                        @Override
                        public void onCompletion(Music music) {
                            music.play();
                            playSound.dispose();
                            player.isAllowedToMove = true;



                        }
                    });

                } else if (endPoints.indexOf(endPointLoc) == 0) {
                    updateSave(8);
                    player.isAllowedToMove = false;
                    player.xAxis = 0;
                    player.yAxis = 0;
                    player.getFrame(delta, Player.State.IDLE);
                    Hud.stage.addAction(Actions.sequence(Actions.fadeOut(1),Actions.run(new Runnable(){
                        @Override
                        public void run() {
                            Hud.stage.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(1)));
                            game.setScreen(new SnowyMountain(game, gameViewPort, SnowyMountain.map, 0));
                        }
                    })));
                    playSound.setVolume(0);
                    playSound.play();
                    playSound.setOnCompletionListener(new Music.OnCompletionListener() {
                        @Override
                        public void onCompletion(Music music) {
                            music.play();
                            playSound.dispose();
                            player.isAllowedToMove = true;



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
