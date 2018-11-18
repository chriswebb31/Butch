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

    ButchGame game;
    Sound sound;
    OrthographicCamera camera;
    SpriteBatch batch;
    Texture texture_back;
    Sprite sprite_back, playButtonActive,aboutButtonActive, needHelpButton, settingsButton, exitButtonActive, exitButtonInactive;
    int playButtonX = 809, playButtonY = 598, playButtonWidth = 276, playButtonHeight = 124;
    int aboutButtonX = 809, aboutButtonY = 750, aboutButtonWidth = 276, aboutButtonHeight = 124;
    int needHelpButtonX = 809, needHelpButtonY = 900, needHelpButtonWidth = 276 ,needHelpButtonHeight = 124;
    Stage stage;
    int settingsButtonX = 0; //location where the settings button will start drawing in x axis
    int  settingsButtonY = 0; //location where the settings button will start drawing in y axis reversed!
    int settingsButtonWidth = 70; // width of settings Button
    int settingsButtonHeight = 70; // height of settings button
    private FitViewport gameViewPort;
    private Music music;
    public ImageButton exitButton;


    public MainMenuScreen(ButchGame game, FitViewport gameViewport){
        this.game = game;
        this.gameViewPort = gameViewport;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 1920, 1080);
        batch = new SpriteBatch();
        texture_back = ButchGame.assets.get(ButchGame.assets.backgroundTexture, Texture.class); // locating the background
        texture_back.setFilter(Linear,Linear);
        sprite_back = new Sprite(texture_back);
        sprite_back.setRegionWidth(1920);
        sprite_back.setRegionHeight(1080);
        sprite_back.flip(false, true); // flipping y because in LibGDX y axis is reversed.
        sound = ButchGame.assets.get(ButchGame.assets.menuClick, Sound.class);
         stage = new Stage();
        playButtonActive = new Sprite (ButchGame.assets.get(ButchGame.assets.playButtonActiveSprite, Texture.class)); // locating the play button
        playButtonActive.flip(false, true);
        aboutButtonActive = new Sprite(ButchGame.assets.get(ButchGame.assets.aboutButtonActiveSprite, Texture.class)); // locating the about button
        aboutButtonActive.flip(false,true);

        needHelpButton = new Sprite(ButchGame.assets.get(ButchGame.assets.needHelpButtonActiveSprite, Texture.class)); //locating the need help button
        needHelpButton.flip(false, true);
        settingsButton = new Sprite(ButchGame.assets.get(ButchGame.assets.settingsButtonActiveSprite, Texture.class)); // locating the setting button
        music = ButchGame.assets.get(ButchGame.assets.mainTheme, Music.class);
        music.setVolume(0.3f);
        music.setLooping(true);
        music.play();
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

        batch.begin(); //starting rendering
        //render code

        batch.draw(sprite_back,0,0);
        float x =  1600/2 - playButtonWidth/2;
        //float y = ButchGame.TARGET_HEIGHT/2 - playButtonActive.getWidth();

        if(Gdx.input.getX()>=  playButtonX && Gdx.input.getX() <= playButtonWidth + playButtonX && Gdx.input.getY() >=
                playButtonY  && Gdx.input.getY() < playButtonY + playButtonHeight ) {
// there is math involved here, in a few words it's checking if the pointer is hovering over the button if yes the image
// will become larger.
            batch.draw(playButtonActive, playButtonX, playButtonY , playButtonWidth,playButtonHeight);
            //if yes the image will become larger.
            if (Gdx.input.isTouched()) {
                sound.play();

                this.dispose();

                game.setScreen(new GameScreen(game, gameViewPort));

            }
            //if above conditions are met then once play button is clicked the game will load
        }

        else{
            batch.draw(playButtonActive, 839, 619, 215,71);
        }
        if(Gdx.input.getX()>=  aboutButtonX && Gdx.input.getX() <= aboutButtonWidth + aboutButtonX && Gdx.input.getY()
                >= aboutButtonY  && Gdx.input.getY() < aboutButtonY + aboutButtonHeight ) {

            batch.draw(aboutButtonActive, aboutButtonX, aboutButtonY , aboutButtonWidth,aboutButtonHeight);

            if (Gdx.input.isTouched()) {
                sound.play();
                this.dispose();
                game.setScreen(new AboutScreen(game, gameViewPort));
            }
        }

        else{
            batch.draw(aboutButtonActive, 839, 780, 215,71);
        }
        if(Gdx.input.getX()>=  needHelpButtonX && Gdx.input.getX() <= needHelpButtonWidth + needHelpButtonX &&
                Gdx.input.getY() >= needHelpButtonY  && Gdx.input.getY() < needHelpButtonY + needHelpButtonHeight ) {

            batch.draw(needHelpButton, needHelpButtonX, needHelpButtonY , needHelpButtonWidth,needHelpButtonHeight);
            if (Gdx.input.isTouched()) {
                sound.play();
                this.dispose();
                game.setScreen(new NeedHelpScreen(game, gameViewPort));
            }
        }

        else{
            batch.draw(needHelpButton, 839, 940, 215,71);
        }

        if(Gdx.input.getX()>=  settingsButtonX && Gdx.input.getX() <= settingsButtonWidth + settingsButtonX &&
                Gdx.input.getY() >= settingsButtonY  && Gdx.input.getY() < settingsButtonY + settingsButtonHeight ) {

            batch.draw(settingsButton, settingsButtonX, settingsButtonY , settingsButtonWidth,settingsButtonHeight);
            if (Gdx.input.isTouched()) {
                sound.play();
                this.dispose();
                game.setScreen(new SettingsScreen(game, gameViewPort));
            }
        }

        else {
            batch.draw(settingsButton, 0, 0, 60, 60);
        }

        batch.end(); // end rendering
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
        exitButtonInactive = new Sprite (ButchGame.assets.get(ButchGame.assets.exitButtonInactive, Texture.class));
        exitButtonActive = new Sprite (ButchGame.assets.get(ButchGame.assets.exitButtonActive, Texture.class));
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
               Gdx.app.exit();
            }

        });
        stage.addActor(exitButton);
    }
    @Override
    public void dispose() {

        music.dispose();
    }
}
