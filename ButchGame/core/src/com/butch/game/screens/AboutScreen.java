package com.butch.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.butch.game.ButchGame;

import static com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;

public class AboutScreen implements Screen {
    private SpriteBatch batch;
    private ButchGame game;
    //String aboutText;
    private OrthographicCamera camera;
    private Texture exitButton;
    private int exitButtonX = 100; //location where the exit button will start drawing in x axis
    private int  exitButtonY = 970; //location where the exit button will start drawing in y axis reversed!
    private int exitButtonWidth = 300; // width of exit Button
    private int exitButtonHeight = 100; // height of exit button
    private Sound sound;
    Texture back;
    Sprite backS;
    public AboutScreen(ButchGame game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 1920, 1080);
        // import picture to sprite and then using sprite to render background image with canvas dimensions
        batch = new SpriteBatch();
        back = new Texture(Gdx.files.internal("aboutPage.png"));
        back.setFilter(Linear, Linear);
        backS = new Sprite(back);
        backS.setRegionWidth(1920);
        backS.setRegionHeight(1080);
        backS.flip(false,true);
        //aboutText = new String("bye world");
        exitButton = new Texture("Buttons/exitButton.png");
        sound = Gdx.audio.newSound(Gdx.files.internal("SoundFX/clickingSound.mp3"));

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
      batch.begin();
       batch.draw(backS, 0, 0 );
        if(Gdx.input.getX()>=  exitButtonX && Gdx.input.getX() <= exitButtonWidth + exitButtonX && Gdx.input.getY()
                >= exitButtonY && Gdx.input.getY() < exitButtonY + exitButtonHeight ) {

            batch.draw(exitButton, exitButtonX, exitButtonY , exitButtonWidth,exitButtonHeight);
            if (Gdx.input.isTouched()) {
                sound.play(); // clicking sound will be played;
                this.dispose();
                game.setScreen(new MainMenuScreen(game));


            }
        }

        else{
            batch.draw(exitButton, 100, 1080- 100, 215,71);
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
