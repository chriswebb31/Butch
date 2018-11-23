package com.butch.game.screens;

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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;

public class SettingsScreen implements Screen {
    private SpriteBatch batch;
    private ButchGame game;
    private OrthographicCamera camera;
    private Sprite exitButtonActive,exitButtonInactive, sliderBack, sliderKnob;
    Stage stage;
    public ImageButton exitButton;
    private Sound sound;
    FitViewport gameViewPort;
    Slider.SliderStyle sliderStyle;
    Slider volumeSlider;
    MainMenuScreen menuScreen;

    public SettingsScreen(final ButchGame game, FitViewport gameViewPort, final MainMenuScreen menuScreen){
        this.gameViewPort = gameViewPort;
        this.game = game;
        this.menuScreen = menuScreen;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 1920, 1080);
        stage = new Stage(gameViewPort);
        batch = new SpriteBatch();
       // volumeLabel = new Label("Volume",new Label.LabelStyle(""));
        sound = ButchGame.assets.get(ButchGame.assets.menuClick, Sound.class);
        sliderBack = new Sprite (ButchGame.assets.get(ButchGame.assets.sliderBack, Texture.class));
        sliderKnob = new Sprite(ButchGame.assets.get(ButchGame.assets.sliderKnob,Texture.class));
        sliderStyle = new Slider.SliderStyle( new SpriteDrawable(sliderBack),new SpriteDrawable(sliderKnob));
        volumeSlider = new Slider(0f,1f,0.1f,false,sliderStyle);
        volumeSlider.setPosition(500,500);
        volumeSlider.setSize(1000,100);
        volumeSlider.setValue(game.getVolume());
        volumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
             game.setVolume(volumeSlider.getValue());
             menuScreen.setVolume(volumeSlider.getValue());
             System.out.println(volumeSlider.getValue());


            }
        });
        stage.addActor(volumeSlider);
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
        exitButtonActive = new Sprite(ButchGame.assets.get(ButchGame.assets.exitButtonActive, Texture.class));
        exitButtonInactive = new Sprite (ButchGame.assets.get(ButchGame.assets.exitButtonInactive, Texture.class));
        exitButton = new ImageButton(new SpriteDrawable(exitButtonInactive), new SpriteDrawable(exitButtonActive));
        exitButton.setBounds(10,10,251,71);

        exitButton.addListener(new ClickListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                exitButton.setBounds(10,10,251,81);

            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
                exitButton.setBounds(10,10,251,71);

            }
            public void clicked(InputEvent event, float x, float y){
                sound.play();

              game.setScreen(new MainMenuScreen(game, gameViewPort));
            }

        });
        stage.addActor(exitButton);
    }
    @Override
    public void dispose() {
        this.dispose();
        exitButtonActive.getTexture().dispose();
        exitButtonInactive.getTexture().dispose();
    }
}
