package com.butch.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;
import com.butch.game.gamemanagers.AssetManagement;
import com.butch.game.screens.MenuScreens.MainMenuScreen;

public class LoadingScreen implements Screen {
    private ButchGame game;
    private FitViewport gameViewPort;
    public static AssetManagement assets;
    public Sprite loadingImageBack;
    public Sprite loadingImageFront;
    private SpriteBatch batch;
    float assetsNum;

    public LoadingScreen(ButchGame game, FitViewport gameViewPort){
        this.game = game;
        game.assets.includeMainMenuScreenAssets();
        this.gameViewPort = gameViewPort;
        batch = new SpriteBatch();
        assetsNum = 1000;
        loadingImageFront= new Sprite(new Texture(game.assets.playerHBFG));
        loadingImageBack = new Sprite(new Texture(game.assets.loadingBarBack));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        /**setting the background white, however this doesn't matter as there will be a background anyway.*/
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            if (game.assets.update()) {
                    game.setScreen(new MainMenuScreen(game, gameViewPort));
        }
            else{
                batch.begin();
                batch.draw(loadingImageBack, Gdx.graphics.getWidth()/2 - loadingImageBack.getWidth()/2,
                        Gdx.graphics.getHeight()/2 - loadingImageBack.getHeight()/2,assetsNum,50);
                batch.draw(loadingImageFront, Gdx.graphics.getWidth()/2 - loadingImageBack.getWidth()/2+2,
                        Gdx.graphics.getHeight()/2 - loadingImageBack.getHeight()/2+1,game.assets.getProgress()
                                *1000, 50-2);
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
    loadingImageBack.getTexture().dispose();
    loadingImageFront.getTexture().dispose();
    }
}
