package com.butch.game.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;
import com.butch.game.gamemanagers.AssetManagement;
import com.butch.game.gamemanagers.GameStateManager;
import com.butch.game.gamemanagers.GifDecoder;


public class LoadingScreen implements Screen {
    private ButchGame game;
    private SpriteBatch batch;
    private FitViewport gameViewPort;
  //  public static final boolean DEBUG = true;
  float elapsed;
   // public static Engine ashleyEngine;
    public static AssetManagement assets;
    Animation <TextureRegion> animation;
//    public static GameStateManager GSM;
//    private static FPSLogger log;
//    public MainMenuScreen game_screen;

    public LoadingScreen(ButchGame game, FitViewport gameViewPort){
        this.game = game;
        game.assets.includeMainMenuScreenAssets();
        this.gameViewPort = gameViewPort;
        batch = new SpriteBatch();
        animation = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP, Gdx.files.internal("Gif/loading.gif").read());

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
//        log.log();
//        GSM.update();
        elapsed += Gdx.graphics.getDeltaTime();
        batch.begin();
        if (elapsed < 3.0f){
            batch.draw(animation.getKeyFrame(elapsed), 0,0,game.TARGET_WIDTH,game.TARGET_HEIGHT);

        }
        else {
            if (game.assets.update()) {
                if (ButchGame.assets.update()) {
                    game.setScreen(new MainMenuScreen(game, gameViewPort));
                }
            }
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
