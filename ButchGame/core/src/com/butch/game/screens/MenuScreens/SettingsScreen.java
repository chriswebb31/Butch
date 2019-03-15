package com.butch.game.screens.MenuScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;
import com.butch.game.screens.TransitionScreen;

public class SettingsScreen implements Screen {
    private SpriteBatch batch;
    private ButchGame game;
    private OrthographicCamera camera;
    private Sprite homeButtonActive,homeButtonInactive, sliderBack, sliderKnob;
    Stage stage;
    public ImageButton homeButton;
    private Music clickSound;
    FitViewport gameViewPort;
    Slider.SliderStyle sliderStyle;
    Slider volumeSlider;
    MainMenuScreen menuScreen;
    Table table = new Table();
    Label volumeLabel;
    static TransitionScreen transitionScreen;
    public SettingsScreen(final ButchGame game, FitViewport gameViewPort, final MainMenuScreen menuScreen){
        this.gameViewPort = gameViewPort;
        this.game = game;
        this.menuScreen = menuScreen;

        camera = new OrthographicCamera();
        camera.setToOrtho(true, 1920, 1080);
        stage = new Stage(gameViewPort);
        batch = new SpriteBatch();
       // volumeLabel = new Label("Volume",new Label.LabelStyle(""));
        clickSound = ButchGame.assets.get(ButchGame.assets.menuClick, Music.class);
        sliderBack = new Sprite (ButchGame.assets.get(ButchGame.assets.sliderBack, Texture.class));
        sliderKnob = new Sprite(ButchGame.assets.get(ButchGame.assets.sliderKnob,Texture.class));
        sliderStyle = new Slider.SliderStyle( new SpriteDrawable(sliderBack),new SpriteDrawable(sliderKnob));
        volumeSlider = new Slider(0f,1f,0.1f,false,sliderStyle);
        volumeSlider.setPosition(game.TARGET_WIDTH/3.84f,game.TARGET_HEIGHT/2.16f);
        volumeSlider.setSize(game.TARGET_WIDTH/1.92f,game.TARGET_HEIGHT/10.8f);
        volumeSlider.setValue(game.getVolume());
        volumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
             game.setVolume(volumeSlider.getValue());
             menuScreen.setVolume(volumeSlider.getValue());
             System.out.println(volumeSlider.getValue());

            }
        });

//        table.setFillParent(true);
//        table.center();
//        table.add(volumeSlider).fillX();
//        table.row();
//        stage.addActor(table);
   stage.addActor(volumeSlider);
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        createButtons();
        transitionScreen.transitionIn(stage);
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
                clickSound.play();
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
