package com.butch.game.screens.MenuScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;

import com.butch.game.screens.MenuScreens.MainMenuScreen;

import static com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;


public class NeedHelpScreen implements Screen {
    private SpriteBatch batch;
    private ButchGame game;
    private OrthographicCamera camera;
    private Sprite homeButtonActive,homeButtonInactive;
    public ImageButton homeButton;
    Stage stage;
    Texture back;
    Sprite backS;
    private Sound sound;
    FitViewport gameViewPort;


    public NeedHelpScreen(ButchGame game, FitViewport gameViewPort){
        this.gameViewPort = gameViewPort;
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 1920, 1080);
        stage = new Stage(gameViewPort);
        batch = new SpriteBatch();
        homeButtonActive = new Sprite(ButchGame.assets.get(ButchGame.assets.homeButtonActive, Texture.class));
        sound = ButchGame.assets.get(ButchGame.assets.menuClick, Sound.class);
        batch= new SpriteBatch();
        back = new Texture(Gdx.files.internal("needHelpPage.png"));
        back.setFilter(Linear, Linear);
        backS = new Sprite(back);
        backS.flip(false,true);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        createButtons();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255f,215f,0f,1f); // setting the background white, however this doesn't...
        //...matter as there will be a background anyway.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        camera.update();
        update(delta);
        batch.begin();
        batch.draw(backS, 0, 0 );
        batch.end();
        update(delta);
        stage.draw();
    }

    public void update(float delta){
        stage.act(delta);
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
    public void createButtons(){
        homeButtonActive = new Sprite(ButchGame.assets.get(ButchGame.assets.homeButtonActive, Texture.class));
        homeButtonInactive = new Sprite (ButchGame.assets.get(ButchGame.assets.homeButtonInactive, Texture.class));
        homeButton = new ImageButton(new SpriteDrawable(homeButtonInactive), new SpriteDrawable(homeButtonActive));
        homeButton.setBounds(10,10,251,71);

        homeButton.addListener(new ClickListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                homeButton.setBounds(10,10,251,81);

            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
                homeButton.setBounds(10,10,251,71);

            }
            public void clicked(InputEvent event, float x, float y){
                sound.play();
                game.setScreen(new MainMenuScreen(game, gameViewPort));
            }

        });
        stage.addActor(homeButton);
    }

    @Override
    public void dispose() {
        this.dispose();
        homeButtonActive.getTexture().dispose();
        homeButtonInactive.getTexture().dispose();
        stage.dispose();
    }
}
