package com.butch.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.gamemanagers.AssetManagement;
import com.butch.game.gamemanagers.GameStateManager;
import com.butch.game.gamemanagers.ItemManager;
import com.butch.game.gamemanagers.RenderableManager;
import com.butch.game.screens.LoadingScreen;

import java.awt.*;

public class ButchGame extends Game {
    /*
        CLASS : BUTCHGAME

        This is the main LLibGDX class, it extends game, and could be described as the 'activeForRender window' enwhich the scene changes
        all game constants go here. Such as managers and viewports

     */
	public static final boolean DEBUG = true;
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static final float TARGET_WIDTH = (float)screenSize.getWidth();
	public static final float TARGET_HEIGHT = (float)screenSize.getHeight();
	private static FitViewport gameViewPort;
	public static AssetManagement assets;
	public static GameStateManager GSM;
	public static RenderableManager renderableManager;
	public static ItemManager itemManager;
	private static FPSLogger log;
    //public MainMenuScreen game_screen;
    public float themeVolume;
	public SpriteBatch batch;


	public ButchGame() {
		gameViewPort = new FitViewport(TARGET_WIDTH, TARGET_HEIGHT);
		assets = new AssetManagement();
		assets.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		assets.load(assets.tilemap1, TiledMap.class);
		assets.load(assets.route1, TiledMap.class);
		assets.load(assets.caveTransition, TiledMap.class);
		assets.load(assets.prison, TiledMap.class);
		assets.load(assets.cave, TiledMap.class);
		assets.load(assets.warzone, TiledMap.class);
		renderableManager = new RenderableManager();
//        Gdx.input.setCursorCatched(true);
		log = new FPSLogger();
		GSM = new GameStateManager(gameViewPort, this);
		themeVolume = 0.3f;


  	}
  	public float getVolume(){
		return themeVolume;
	}
	public void setVolume(float volume){
		themeVolume = volume;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
        this.setScreen(new LoadingScreen(this, gameViewPort));
	}

	@Override
        public void render () {
		log.log();
		GSM.update();
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
		assets.dispose();
	}

    public static Vector3 mousePosition(){
        return gameViewPort.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
    }

}