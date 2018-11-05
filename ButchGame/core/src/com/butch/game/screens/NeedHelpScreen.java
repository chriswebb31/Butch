package com.butch.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.butch.game.ButchGame;

public class NeedHelpScreen implements Screen {
    private SpriteBatch batch;
    private ButchGame game;
    private OrthographicCamera camera;
    private Texture exitButton;
    private Texture exitButtonInactive;
    private int exitButtonX = 100; //location where the exit button will start drawing in x axis
    private int  exitButtonY = 50; //location where the exit button will start drawing in y axis reversed!
    private int exitButtonWidth = 300; // width of exit Button
    private int exitButtonHeight = 100; // height of exit button
    private Sound sound;
    public NeedHelpScreen(ButchGame game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 1920, 1080);
        batch = new SpriteBatch();
        exitButton = new Texture("Buttons/exitButtonActive.png");
        exitButtonInactive = new Texture("Buttons/exitButtonInactive.png");
        sound = Gdx.audio.newSound(Gdx.files.internal("SoundFX/clickingSound.mp3"));

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(128f,0f,128f, 0.7f); // setting the background white, however this doesn't...
        // ...matter as there will be a background anyway.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.begin();
        if(Gdx.input.getX()>=  exitButtonX && Gdx.input.getX() <= exitButtonWidth + exitButtonX && Gdx.input.getY()
                <1080 - exitButtonY && Gdx.input.getY() > 1080 - exitButtonY - exitButtonHeight ) {

            batch.draw(exitButton, exitButtonX, exitButtonY , exitButtonWidth,exitButtonHeight);
            if (Gdx.input.isTouched()) {
                sound.play(); // clicking sound will be played;
                this.dispose();
                game.setScreen(new MainMenuScreen(game));


            }
        }

        else{
            batch.draw(exitButtonInactive, 100, 100, 215,71);
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
