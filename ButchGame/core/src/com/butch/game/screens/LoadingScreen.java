package com.butch.game.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;
import com.butch.game.gamemanagers.AssetManagement;
import com.butch.game.gamemanagers.GameStateManager;

public class LoadingScreen implements Screen {
    private ButchGame game;
    private FitViewport gameViewPort;
    public static final boolean DEBUG = true;
    public static final float TARGET_WIDTH = 1920;
    public static final float TARGET_HEIGHT = 1080;

    public static Engine ashleyEngine;
    public static AssetManagement assets;
    public static GameStateManager GSM;
    private static FPSLogger log;
    public MainMenuScreen game_screen;

    public LoadingScreen(ButchGame game, FitViewport gameViewPort){
        this.game = game;
        this.gameViewPort = gameViewPort;
        /** gameViewPort = new FitViewport(1920, 1080);
        /**assets = new AssetManagement();
        assets.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        assets.load(assets.tilemap1, TiledMap.class);
        log = new FPSLogger();
        ashleyEngine = new Engine();
        GSM = new GameStateManager();
         */
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //log.log();
       // GSM.update();
  if(game.assets.update()){
      if(ButchGame.assets.update()){

          game.setScreen(new GameScreen(game, gameViewPort));
      }
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
