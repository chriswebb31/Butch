package com.butch.game.screens.GameScreens;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.HUDObjects.Hud;
import com.butch.game.gameobjects.spriterenderables.Player;

public class MazeMap extends ModelGameScreen{
    public static TiledMap map = ButchGame.assets.get(ButchGame.assets.mazeMap);
    private Music playSound;
    private int coinCounter;
    Music mazeMusic;

    public MazeMap(ButchGame game, FitViewport gameViewPort, TiledMap map, int spawnLocation) {
        super( game, gameViewPort, map, spawnLocation);
<<<<<<< Updated upstream
        mazeMusic = ButchGame.assets.get(ButchGame.assets.endMazeMapTheme, Music.class);
        mazeMusic.setVolume(1.0f);
        music.pause();
        mazeMusic.play();
        mazeMusic.setLooping(true);
=======
        playSound = ButchGame.assets.get(ButchGame.assets.playSound, Music.class);
>>>>>>> Stashed changes
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
                    updateSave(1);
<<<<<<< Updated upstream
                    mazeMusic.stop();
                    music.play();
                    game.setScreen(new NewGameScreen(game, gameViewPort, NewGameScreen.map, 0));
=======
                    player.isAllowedToMove = false;
                    player.xAxis = 0;
                    player.yAxis = 0;
                    player.getFrame(delta, Player.State.IDLE);
                    Hud.stage.addAction(Actions.sequence(Actions.fadeOut(1),Actions.run(new Runnable(){
                        @Override
                        public void run() {
                            Hud.stage.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(1)));
                            game.setScreen(new NewGameScreen(game, gameViewPort, NewGameScreen.map, 0));
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

>>>>>>> Stashed changes
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
