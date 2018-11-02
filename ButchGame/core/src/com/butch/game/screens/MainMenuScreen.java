package com.butch.game.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;
import com.butch.game.gamemanagers.AssetManagement;
import com.butch.game.gamemanagers.GameStateManager;

import static com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;

public class MainMenuScreen implements Screen {
    ButchGame game;
    OrthographicCamera camera;
    SpriteBatch batch;
    Texture texture_back;
    Sprite sprite_back;
    Texture playButtonActive;
    private static FitViewport gameViewPort;
    //TextureRegion mainBackground;
    public static AssetManagement assets;
    private static FPSLogger log;
    public static Engine ashleyEngine;
    public static GameStateManager GSM;
    public MainMenuScreen(ButchGame game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 1600, 800);
        batch = new SpriteBatch();
        texture_back = new Texture(Gdx.files.internal("Butch Title 2 Final.png"));
        texture_back.setFilter(Linear,Linear);
        sprite_back = new Sprite(texture_back);
        sprite_back.setRegionWidth(1600);
        sprite_back.setRegionHeight(800);
        sprite_back.flip(false, true);
        playButtonActive = new Texture ("playButton.png");
       /**
        gameViewPort = new FitViewport(1920, 1080);
        assets = new AssetManagement();
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
Gdx.gl.glClearColor(1f,1f,1f,1f);
Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        //render code

        batch.draw(sprite_back,0,0);
        batch.draw(playButtonActive, 700, 461, 180,50);
        if(Gdx.input.isTouched()){
            this.dispose();
            game.doit();
            game.create2();

            game.render2();

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
