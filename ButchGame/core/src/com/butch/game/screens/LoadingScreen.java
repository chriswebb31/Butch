package com.butch.game.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;
import com.butch.game.gamemanagers.AssetManagement;
import com.butch.game.gamemanagers.GameStateManager;
import com.butch.game.gamemanagers.GifDecoder;

public class LoadingScreen implements Screen {
    private ButchGame game;
    private FitViewport gameViewPort;
    public static final boolean DEBUG = true;
    public static Engine ashleyEngine;
    public static AssetManagement assets;
    public Sprite loadingImageBack;
    public Sprite loadingImageFront;
    //////////////////Temporary gif loader///////////////////////
    private SpriteBatch batch;
    public MainMenuScreen game_screen;
    float elapsed,assetsNum;

    public LoadingScreen(ButchGame game, FitViewport gameViewPort){
        this.game = game;
        game.assets.includeMainMenuScreenAssets();
        this.gameViewPort = gameViewPort;
        batch = new SpriteBatch();
        assetsNum = 1000;
        loadingImageBack = new Sprite(new Texture("HUD/healthBarBack.png"));
//        batch.begin();
//
//        batch.draw(loadingImageBack, Gdx.graphics.getWidth()/2 - loadingImageBack.getWidth()/2,
//                Gdx.graphics.getHeight()/2 - loadingImageBack.getHeight()/2,100, 50 );
//        batch.end();
        loadingImageFront= new Sprite(new Texture("HUD/playerHealthBarFG.png"));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f); // setting the background white, however this doesn't//
        //matter as there will be a background anyway.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            if (game.assets.update()) {
                if (ButchGame.assets.update()) {
                    game.setScreen(new MainMenuScreen(game, gameViewPort));
            }

        }
            else{
                batch.begin();

                batch.draw(loadingImageBack, Gdx.graphics.getWidth()/2 - loadingImageBack.getWidth()/2,
                        Gdx.graphics.getHeight()/2 - loadingImageBack.getHeight()/2,assetsNum,50);
                System.out.println("assetsNum = " + assetsNum + "renderAssetsNum = " + game.assets.getQueuedAssets() + "loaded Assets = " + game.assets.getLoadedAssets()
                + "progress =" + (int)game.assets.getProgress());
                batch.draw(loadingImageFront, Gdx.graphics.getWidth()/2 - loadingImageBack.getWidth()/2+2,
                        Gdx.graphics.getHeight()/2 - loadingImageBack.getHeight()/2+1,game.assets.getProgress()*1000, 50-2);

                batch.end();
            }

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
