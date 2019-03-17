package com.butch.game.screens.GameScreens;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Gun;
import com.butch.game.gameobjects.spriterenderables.Player;

import java.util.ArrayList;

public class StartTavern extends ModelGameScreen {
    public static TiledMap map = ButchGame.assets.get(ButchGame.assets.startTavern);
    Music tavernMusic,playSound;
    boolean phase2,phase3, phase4;
    float currentPosx, currentPosy, statetime;
    Vector2 movingpos;
    public static boolean cutSceneStart = true;
    public StartTavern(ButchGame game, FitViewport gameViewPort, TiledMap map, int spawnLocation) {
        super(game, gameViewPort, map, spawnLocation);
        tavernMusic = ButchGame.assets.get(ButchGame.assets.saloonBackNoise1, Music.class);
        tavernMusic.setVolume(1.0f);
        music.pause();
        tavernMusic.play();
        tavernMusic.setLooping(true);
        playSound = ButchGame.assets.get(ButchGame.assets.playSound, Music.class);
        //cutSceneStart = true;
        currentPosx = player.getPosition().x;
        currentPosy = player.getPosition().y;
        statetime = 0;
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
                    player.isAllowedToMove = false;
                    player.xAxis = 0;
                    player.yAxis = 0;
                    player.getFrame(delta, Player.State.IDLE);
                    playSound.play();
                    playSound.setOnCompletionListener(new Music.OnCompletionListener() {
                        @Override
                        public void onCompletion(Music music) {

                            music.play();
                            tavernMusic.dispose();
                            playSound.dispose();
                            player.isAllowedToMove = true;
                            updateSave(1);
                            game.setScreen( new NewGameScreen(game, gameViewPort, NewGameScreen.map, 0));
                        }
                    });

                }
            }
        }

            if(cutSceneStart){
                if(player.getPosition().y <= currentPosy + 1050 ){
                    //currentPosy = player.getPosition().y;
                    player.allowedtoPress = false;
                    player.getPosition().y+=10.f;

                }
                else{
                    phase2=true;
                }
                if(phase2==true){
                    if(player.getPosition().x <= currentPosx + 810 ){
                        player.getPosition().x+=10.f;

                    }
                    else{
                        phase2=false;
                       phase3=true;
                    }
                }
                if(phase3==true){
                    if(player.getPosition().y <= currentPosy + 1050+ 1363 ){
                        player.getPosition().y+=10.f;

                    }
                    else{
                        phase3=false;
                        phase4 = true;
                    }
                }
                if(phase4==true){
                    if(player.getPosition().x <= currentPosx + 810 + 710 ){
                        player.getPosition().x+=10.f;

                    }
                    else{
                        phase4=false;
                        cutSceneStart=false;
                        player.allowedtoPress = true;
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
