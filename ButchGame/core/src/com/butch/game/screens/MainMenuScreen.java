package com.butch.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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

//import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
///import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.maps.tiled.TiledMap;
//import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class MainMenuScreen implements Screen {
    //Change to use actual buttons maybe? Scene2D is used for menus etc

    static ButchGame game;
    Sound sound;

    OrthographicCamera camera;
    SpriteBatch batch;
    Texture texture_back;
    private Sprite sprite_back, playButtonActive,playButtonInactive,aboutButtonActive,aboutButtonInactive,
            needHelpButtonActive, needHelpButtonInactive, settingsButtonActive, exitButtonActive, exitButtonInactive;
    Stage stage;
    private FitViewport gameViewPort;
    private static Music music, playSound;
    public ImageButton exitButton, playButton, aboutButton, settingsButton, needHelpButton;


    public MainMenuScreen(ButchGame game, FitViewport gameViewport){
        this.game = game;

        this.gameViewPort = gameViewport;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, game.TARGET_WIDTH,game.TARGET_HEIGHT);
        batch = new SpriteBatch();
        texture_back = ButchGame.assets.get(ButchGame.assets.backgroundTexture, Texture.class); // locating the background
        texture_back.setFilter(Linear,Linear);
        sprite_back = new Sprite(texture_back);
        sprite_back.setRegionWidth((int)camera.viewportWidth);
        sprite_back.setRegionHeight((int)camera.viewportHeight);
        sprite_back.flip(false, true); // flipping y because in LibGDX y axis is reversed.
        sound = ButchGame.assets.get(ButchGame.assets.menuClick, Sound.class);
        stage = new Stage();
        music = ButchGame.assets.get(ButchGame.assets.mainTheme, Music.class);
        music.setVolume(game.getVolume());
        music.setLooping(true);
        music.play();
        playSound = ButchGame.assets.get(ButchGame.assets.playSound, Music.class);
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        createButtons();
    }

    public void setVolume(float volume) {
        music.setVolume(volume);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f); // setting the background white, however this doesn't//
        //matter as there will be a background anyway.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(sprite_back,0,0);
        batch.end();
        update(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }
    public void update(float delta){
        stage.act(delta);
    }
    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
    public void createButtons(){
        /** creating the Active and Inactive Sprites
            creating the Buttons as Image Buttons
            setting position and size with .setBounds method. */
        settingsButtonActive = new Sprite(ButchGame.assets.get(ButchGame.assets.settingsButtonActiveSprite, Texture.class));
        settingsButton = new ImageButton(new SpriteDrawable(settingsButtonActive));
        settingsButton.setBounds(0,game.TARGET_HEIGHT-60,60,60);
        playButtonActive = new Sprite (ButchGame.assets.get(ButchGame.assets.playButtonActiveSprite, Texture.class));
        playButtonInactive = new Sprite (ButchGame.assets.get(ButchGame.assets.playButtonInactiveSprite, Texture.class));
        playButton = new ImageButton(new SpriteDrawable(playButtonInactive),new SpriteDrawable(playButtonActive));
        playButton.setBounds(80,game.TARGET_HEIGHT -447,321,137);
        aboutButtonActive = new Sprite(ButchGame.assets.get(ButchGame.assets.aboutButtonActiveSprite, Texture.class));
        aboutButtonInactive = new Sprite(ButchGame.assets.get(ButchGame.assets.aboutButtonInactiveSprite, Texture.class));
        aboutButton = new ImageButton(new SpriteDrawable(aboutButtonInactive),new SpriteDrawable(aboutButtonActive));
        aboutButton.setBounds(80, game.TARGET_HEIGHT - 634, 321,137);
        needHelpButtonActive = new Sprite(ButchGame.assets.get(ButchGame.assets.needHelpButtonActiveSprite, Texture.class));
        needHelpButtonInactive = new Sprite(ButchGame.assets.get(ButchGame.assets.needHelpButtonInactiveSprite, Texture.class));
        needHelpButton = new ImageButton(new SpriteDrawable( needHelpButtonInactive), new SpriteDrawable(needHelpButtonActive));
        needHelpButton.setBounds(80, game.TARGET_HEIGHT - 821, 321, 137);
        exitButtonInactive = new Sprite (ButchGame.assets.get(ButchGame.assets.exitButtonInactive, Texture.class));
        exitButtonActive = new Sprite (ButchGame.assets.get(ButchGame.assets.exitButtonActive, Texture.class));
        exitButton = new ImageButton(new SpriteDrawable(exitButtonInactive), new SpriteDrawable(exitButtonActive));
        exitButton.setBounds(10,10,251,71);
        /** adding actions of when hovering over a button and clicking */
        playButton.addListener(new ClickListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                playButton.setBounds(75,game.TARGET_HEIGHT-452,331,147);

            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
                playButton.setBounds(80,game.TARGET_HEIGHT -447,321,137);

            }
            public void clicked(InputEvent event, float x, float y){
                music.stop();
                playSound.play();
               removeButtons();
                playSound.setOnCompletionListener(new Music.OnCompletionListener() {
                    @Override
                    public void onCompletion(Music music) {

                        game.setScreen(new NewGameScreen(game, gameViewPort));
                    }
                });
            }
        });
        aboutButton.addListener(new ClickListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                aboutButton.setBounds(75,game.TARGET_HEIGHT-639,331,147);

            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
                aboutButton.setBounds(80, game.TARGET_HEIGHT - 634, 321,137);

            }
            public void clicked(InputEvent event, float x, float y){
                sound.play();
               removeButtons();
                game.setScreen(new AboutScreen(game, gameViewPort));
            }

        });
        needHelpButton.addListener(new ClickListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                needHelpButton.setBounds(75,game.TARGET_HEIGHT-826,331,147);

            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
                needHelpButton.setBounds(80,game.TARGET_HEIGHT -821,321,137);

            }
            public void clicked(InputEvent event, float x, float y){
                sound.play();
                removeButtons();
                game.setScreen(new NeedHelpScreen(game, gameViewPort));
            }

        });
        settingsButton.addListener(new ClickListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                settingsButton.setBounds(0,game.TARGET_HEIGHT-70,70,70);

            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
                settingsButton.setBounds(0,game.TARGET_HEIGHT-60,60,60);

            }
            public void clicked(InputEvent event, float x, float y){
                sound.play();
                //game.setScreen(new SettingsScreen(game, gameViewPort, this));
                changeS();
            }

        });

        exitButton.addListener(new ClickListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                exitButton.setSize(261,81);

            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
                exitButton.setSize(251,71);

            }
            public void clicked(InputEvent event, float x, float y){
                sound.play();
              Gdx.app.exit();

            }

        });
        stage.addActor(exitButton);
        stage.addActor(playButton);
        stage.addActor(aboutButton);
        stage.addActor(needHelpButton);
        stage.addActor(settingsButton);
    }
    void removeButtons(){
        playButton.remove();
        aboutButton.remove();
        needHelpButton.remove();
        exitButton.remove();
        settingsButton.remove();
    }
    public void changeS(){
        removeButtons();
        game.setScreen(new SettingsScreen(game, gameViewPort, this));
    }
    @Override
    public void dispose() {

       playButtonActive.getTexture().dispose();
       playButtonInactive.getTexture().dispose();
       exitButtonActive.getTexture().dispose();
       exitButtonInactive.getTexture().dispose();
        music.dispose();
    }

}
