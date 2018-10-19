package com.butch.game;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;
import com.butch.game.gamemanagers.AssetManagement;
import com.butch.game.gamemanagers.GameStateManager;
import com.butch.game.input.InputHandler;
import com.butch.game.screens.LoadingScreen;

public class ButchGame extends Game {
	public static final boolean DEBUG = true;
	public static final float TARGET_WIDTH = 1920;
	public static final float TARGET_HEIGHT = 1080;
	public static final Engine ashleyEngine = new Engine();
	public static AssetManagement assets;
	public static GameStateManager GSM;
	public static InputHandler IH;
	private FPSLogger log;

	public ButchGame() {

	}

	@Override
	public void create () {
		this.assets = new AssetManagement();
		this.log = new FPSLogger();
		this.GSM = new GameStateManager();
		this.IH = new InputHandler();

		this.setScreen(new LoadingScreen(this));
	}

	@Override
	public void render () {
		log.log();
		GSM.update();
		IH.update();
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
		assets.dispose();
	}
}