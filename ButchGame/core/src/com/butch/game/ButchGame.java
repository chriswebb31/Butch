package com.butch.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.gamemanagers.AssetManagement;
import com.butch.game.gamemanagers.GameStateManager;
import com.butch.game.screens.LoadingScreen;
import com.butch.game.screens.MainMenuScreen;

public class ButchGame extends Game {
    /*
        CLASS : BUTCHGAME

        This is the main LLibGDX class, it extends game, and could be described as the 'active window' enwhich the scene changes
        all game constants go here. Such as managers and viewports

     */
	public static final boolean DEBUG = true;
	public static final float TARGET_WIDTH = 1920;
	public static final float TARGET_HEIGHT = 1080;
	private static FitViewport gameViewPort;
	public static Engine ashleyEngine;
	public static AssetManagement assets;
	public static GameStateManager GSM;
	private static FPSLogger log;
    public MainMenuScreen game_screen;
	public ButchGame() {

     //doit();
  }
	public void doit(){
		gameViewPort = new FitViewport(1920, 1080);
		assets = new AssetManagement();
		assets.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		assets.load(assets.tilemap1, TiledMap.class);
		log = new FPSLogger();
		ashleyEngine = new Engine();
		GSM = new GameStateManager();
	}

	@Override
	public void create () {
		game_screen = new MainMenuScreen(this);
		setScreen(game_screen);
		//this.setScreen(new LoadingScreen(this, gameViewPort));
	}

	/**@Override
	public void render () {
		log.log();
		GSM.update();
		super.render();
	}
	*/

	public static Vector3 mousePosition(){
		return gameViewPort.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
	}
	/**@Override
	public void dispose() {
		super.dispose();
		assets.dispose();
	}
	*/
}