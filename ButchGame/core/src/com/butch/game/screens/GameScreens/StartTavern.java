package com.butch.game.screens.GameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.HUDObjects.CharacterScreen;
import com.butch.game.gameobjects.HUDObjects.Hud;
import com.butch.game.gameobjects.abstractinterface.Gun;
import com.butch.game.gameobjects.spriterenderables.Player;
import com.butch.game.screens.cutscenes.NewsPaperScene;

import java.util.ArrayList;

public class StartTavern extends ModelGameScreen {
    public static TiledMap map = ButchGame.assets.get(ButchGame.assets.startTavern);

    boolean phase2,phase3, phase4, phase5;
    float currentPosx, currentPosy, statetime;
//    Vector2 movingpos;
//    Texture newspaper;
//    Image news;
private boolean showHud = true;
    private NewsPaperScene inventory;
    public static boolean cutSceneStart = true;
    public StartTavern(ButchGame game, FitViewport gameViewPort, TiledMap map, int spawnLocation) {
        super(game, gameViewPort, map, spawnLocation);

        tavernMusic.setVolume(1.0f);
        music.pause();
        tavernMusic.play();
        tavernMusic.setLooping(true);

        //cutSceneStart = true;
        currentPosx = player.getPosition().x;
        currentPosy = player.getPosition().y;
        statetime = 0;
//        newspaper = new Texture("CutScenes/newspaperCutscene.png");
//        news = new Image(newspaper);
//        news.setBounds(game.TARGET_WIDTH/2, game.TARGET_HEIGHT/2, 500,500);
        inventory = new NewsPaperScene(game.batch);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Hud.stage.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(1)));
        updateCameraPosition();
        for (Rectangle endPointLoc : endPoints) {
            if (player.getCollider().overlaps(endPointLoc)) {
                if (endPoints.indexOf(endPointLoc) == 0) {
                    player.isAllowedToMove = false;
                    player.xAxis = 0;
                    player.yAxis = 0;
                    player.getFrame(delta, Player.State.IDLE);
                    Hud.stage.addAction(Actions.sequence(Actions.fadeOut(1), Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            Hud.stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1)));
                            game.setScreen(new NewGameScreen(game, gameViewPort, NewGameScreen.map, 0));
                        }
                    })));
                    playSound.play();
                    playSound.setOnCompletionListener(new Music.OnCompletionListener() {
                        @Override
                        public void onCompletion(Music music) {

                            music.play();
                            tavernMusic.stop();
                            playSound.stop();
                            player.isAllowedToMove = true;
                            updateSave(1);
                        }
                    });

                }
            }
        }
        super.render(delta);
        if (cutSceneStart) {
            if (player.getPosition().y <= currentPosy + 1050) {
                //currentPosy = player.getPosition().y;
                player.allowedtoPress = false;
                player.getPosition().y += 10.f;

            } else {
                phase2 = true;
            }
            if (phase2 == true) {
                if (player.getPosition().x <= currentPosx + 810) {
                    player.getPosition().x += 10.f;

                } else {
                    phase2 = false;
                    phase3 = true;
                }
            }
            if (phase3 == true) {
                if (player.getPosition().y <= currentPosy + 1050 + 1363) {
                    player.getPosition().y += 10.f;

                } else {
                    phase3 = false;
                    phase4 = true;
                }
            }
            if (phase4 == true) {
                if (player.getPosition().x <= currentPosx + 810 + 710) {
                    player.getPosition().x += 10.f;

                } else {
                    phase4 = false;
                    phase5 = true;
                    player.allowedtoPress = true;

                    cutSceneStart = false;

                }
            }


        }
        if (cutSceneStart == false && Gdx.input.isKeyPressed(Input.Keys.X)) {
            inventory.draw();
        }
    }
//void readNewsPaper(){
////        batch.begin();
////        batch.draw(newspaper, game.TARGET_WIDTH/2, game.TARGET_HEIGHT/2, 500,500);
////        batch.end();
////    stage.addActor(news);
////    stage.draw();
//}
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
    public void fadeOut(){
        stage.addAction(Actions.sequence(Actions.fadeOut(1),Actions.run(new Runnable() {
            @Override
            public void run() {
                game.setScreen( new NewGameScreen(game, gameViewPort, NewGameScreen.map, 0));
            }
        })));

    }
}
