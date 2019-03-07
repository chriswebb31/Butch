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

import static com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;

public class AboutScreen implements Screen {
    private SpriteBatch batch;
    private ButchGame game;
    private OrthographicCamera camera;
    private Sound sound;
    Texture back;
    Sprite backS, homeButtonActive,homeButtonInactive;
    Stage stage;
    public ImageButton homeButton;
    FitViewport gameViewPort;

    public AboutScreen(ButchGame game, FitViewport gameViewPort){
        this.game = game;
        this.gameViewPort = gameViewPort;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 1920, 1080);
        // import picture to sprite and then using sprite to render background image with canvas dimensions
        batch = new SpriteBatch();
        back = ButchGame.assets.get(ButchGame.assets.aboutPage);
        back.setFilter(Linear, Linear);
        backS = new Sprite(back);
        backS.setRegionWidth(1920);
        backS.setRegionHeight(1080);
        backS.flip(false,true);
        stage = new Stage(gameViewPort);
        sound = ButchGame.assets.get(ButchGame.assets.menuClick, Sound.class);
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        createButtons();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f); // setting the background white, however this doesn't...
        // ...matter as there will be a background anyway.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(backS, 0, 0 );
        batch.end();
        camera.update();
        update(delta);
        stage.draw();
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
    public void update(float delta){
        stage.act(delta);
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
    stage.dispose();
    back.dispose();
    }
}
