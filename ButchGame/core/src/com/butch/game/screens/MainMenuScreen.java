package com.butch.game.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
///import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.maps.tiled.TiledMap;
//import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;
import com.butch.game.gamemanagers.AssetManagement;
import com.butch.game.gamemanagers.GameStateManager;

import static com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;

public class MainMenuScreen implements Screen {
    ButchGame game;
    Sound sound;
    OrthographicCamera camera;
    SpriteBatch batch;
    Texture texture_back;
    Sprite sprite_back;
    Texture playButtonActive;
    Texture aboutButton;
    Texture needHelpButton;
    Texture settingsButton;
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
    int settingsButtonX = 0; //location where the settings button will start drawing in x axis
    int  settingsButtonY = 0; //location where the settings button will start drawing in y axis reversed!
    int settingsButtonWidth = 70; // width of settings Button
    int settingsButtonHeight = 70; // height of settings button

    public MainMenuScreen(ButchGame game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 1920, 1080);
        batch = new SpriteBatch();
        texture_back = new Texture(Gdx.files.internal("Butch Title 2 Final.png")); // locating the background
        texture_back.setFilter(Linear,Linear);
        sprite_back = new Sprite(texture_back);
        sprite_back.setRegionWidth(1920);
        sprite_back.setRegionHeight(1080);
        sprite_back.flip(false, true); // flipping y because in LibGDX y axis is reversed.
        sound = Gdx.audio.newSound(Gdx.files.internal("SoundFX/clickingSound.mp3"));

        playButtonActive = new Texture ("Buttons/playButton.png"); // locating the play button
        aboutButton = new Texture("Buttons/aboutButton.png"); // locating the about button
        needHelpButton = new Texture("Buttons/needHelpButton.png"); //locating the need help button
        settingsButton = new Texture("Buttons/settingsButton.png"); // locating the setting button
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
                game.doit();
                game.create2();

                game.render2();

            }
            //if above conditions are met then once play button is clicked the game will load
        }

        else{
            batch.draw(playButtonActive, 839, 619, 215,71);
        }
        if(Gdx.input.getX()>=  aboutButtonX && Gdx.input.getX() <= aboutButtonWidth + aboutButtonX && Gdx.input.getY()
                >= aboutButtonY  && Gdx.input.getY() < aboutButtonY + aboutButtonHeight ) {

            batch.draw(aboutButton, aboutButtonX, aboutButtonY , aboutButtonWidth,aboutButtonHeight);

            if (Gdx.input.isTouched()) {
                sound.play();
                this.dispose();
                game.setScreen(new AboutScreen(game));


            }
        }

        else{
            batch.draw(aboutButton, 839, 780, 215,71);
        }
        if(Gdx.input.getX()>=  needHelpButtonX && Gdx.input.getX() <= needHelpButtonWidth + needHelpButtonX &&
                Gdx.input.getY() >= needHelpButtonY  && Gdx.input.getY() < needHelpButtonY + needHelpButtonHeight ) {

            batch.draw(needHelpButton, needHelpButtonX, needHelpButtonY , needHelpButtonWidth,needHelpButtonHeight);
            if (Gdx.input.isTouched()) {
                sound.play();
                this.dispose();
                game.setScreen(new NeedHelpScreen(game));
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
                game.setScreen(new SettingsScreen(game));
            }
        }

        else {
            batch.draw(settingsButton, 0, 0, 60, 60);
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

    }
}
