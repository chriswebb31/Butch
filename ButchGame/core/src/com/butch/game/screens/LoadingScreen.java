package com.butch.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;

public class LoadingScreen implements Screen {
    private ButchGame game;
    private FitViewport gameViewPort;


    public LoadingScreen(ButchGame game, FitViewport gameViewPort){
        this.game = game;
        this.gameViewPort = gameViewPort;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(game.assets.update()){
            game.setScreen(new GameScreen(game, gameViewPort));
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
