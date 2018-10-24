package com.butch.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.gamemanagers.AssetManagement;
import com.butch.game.gamemanagers.ColliderManager;
import com.butch.game.gamemanagers.GameStateManager;
import com.butch.game.screens.LoadingScreen;

public class ButchGame extends Game {
	public static final boolean DEBUG = true;
	public static final float TARGET_WIDTH = 1920;
	public static final float TARGET_HEIGHT = 1080;
	private static FitViewport gameViewPort;
	public static Engine ashleyEngine;
	public static AssetManagement assets;
	public static GameStateManager GSM;
	public static ColliderManager CM;
	private static FPSLogger log;

	public ButchGame() {
		gameViewPort = new FitViewport(1920, 1080);
		assets = new AssetManagement();
		log = new FPSLogger();
		ashleyEngine = new Engine();
		GSM = new GameStateManager();
		CM = new ColliderManager();
	}

	@Override
	public void create () {
		this.setScreen(new LoadingScreen(this, gameViewPort));
	}

	@Override
	public void render () {
//		log.log();
		GSM.update();
		super.render();
	}

	public static Vector3 mousePosition(){
		return gameViewPort.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
	}
	@Override
	public void dispose() {
		super.dispose();
		assets.dispose();
	}
}