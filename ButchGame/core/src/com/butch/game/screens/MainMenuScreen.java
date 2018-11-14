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
    Sprite sprite_back;
    Sprite playButtonActive;
    Sprite aboutButtonActive;
   Sprite needHelpButton;
    Sprite settingsButton;
    int playButtonX = 809; //location where the play button will start drawing in x axis
    int  playButtonY = 598; //location where the play button will start drawing in y axis reversed!
    int playButtonWidth = 276; // width of play Button
    int playButtonHeight = 124; // height of play button
    int aboutButtonX = 809; //location where the about button will start drawing in x axis
    int  aboutButtonY = 750; //location where the about button will start drawing in y axis reversed!
    int aboutButtonWidth = 276; // width of about Button
    int aboutButtonHeight = 124; // height of about button
    int needHelpButtonX = 809; //location where the needHelp button will start drawing in x axis
    int  needHelpButtonY = 900; //location where the needHelp button will start drawing in y axis reversed!
    int needHelpButtonWidth = 276; // width of needHelp Button
    int needHelpButtonHeight = 124; // height of needHelp button
    private Sprite exitButtonActive;
    private Sprite exitButtonInactive;
    private int exitButtonX = 100; //location where the exit button will start drawing in x axis
    private int  exitButtonY = 50; //location where the exit button will start drawing in y axis reversed!
    private int exitButtonWidth = 300; // width of exit Button
    private int exitButtonHeight = 100; // height of exit button
    int settingsButtonX = 0; //location where the settings button will start drawing in x axis
    int  settingsButtonY = 0; //location where the settings button will start drawing in y axis reversed!
    int settingsButtonWidth = 70; // width of settings Button
    int settingsButtonHeight = 70; // height of settings button
    private FitViewport gameViewPort;
    private Music music;


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

        playButtonActive = new Sprite (ButchGame.assets.get(ButchGame.assets.playButtonActiveSprite, Texture.class)); // locating the play button
        playButtonActive.flip(false, true);
        aboutButtonActive = new Sprite(ButchGame.assets.get(ButchGame.assets.aboutButtonActiveSprite, Texture.class)); // locating the about button
        aboutButtonActive.flip(false,true);

        needHelpButton = new Sprite(ButchGame.assets.get(ButchGame.assets.needHelpButtonActiveSprite, Texture.class)); //locating the need help button
        needHelpButton.flip(false, true);
        settingsButton = new Sprite(ButchGame.assets.get(ButchGame.assets.settingsButtonActiveSprite, Texture.class)); // locating the setting button
        exitButtonInactive = new Sprite(ButchGame.assets.get(ButchGame.assets.exitButtonInactive, Texture.class));
        exitButtonInactive.flip(false, true);
        exitButtonActive = new Sprite(ButchGame.assets.get(ButchGame.assets.exitButtonActive, Texture.class));
        exitButtonActive.flip(false,true);

        music = ButchGame.assets.get(ButchGame.assets.mainTheme, Music.class);
        music.setVolume(0.3f);
        music.setLooping(true);
        music.play();
    }
    @Override
    public void show() {

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
        if(Gdx.input.getX()>=  exitButtonX && Gdx.input.getX() <= exitButtonWidth + exitButtonX && Gdx.input.getY()
                <1080 - exitButtonY && Gdx.input.getY() > 1080 - exitButtonY - exitButtonHeight ) {

            batch.draw(exitButtonActive, exitButtonX, 1000-exitButtonY , exitButtonWidth,exitButtonHeight);
            if (Gdx.input.isTouched()) {
                sound.play(); // clicking sound will be played;
                this.dispose();
                Gdx.app.exit();

            }
        }

        else{
            batch.draw(exitButtonInactive, 100, 1080 - 100, 215,71);
        }

        batch.end(); // end rendering
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
        music.dispose();
    }
}
