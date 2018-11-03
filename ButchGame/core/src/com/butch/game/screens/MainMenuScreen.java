package com.butch.game.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
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
    OrthographicCamera camera;
    SpriteBatch batch;
    Texture texture_back;
    Sprite sprite_back;
    Texture playButtonActive;
    Texture aboutButton;
    Texture needHelpButton;
    int  playButtonY = 441; //location where the play button will start drawing in y axis reversed!
    int playButtonX = 695; //location where the play button will start drawing in x axis
    int playButtonWidth = 200; // width of play Button
    int playButtonHeight = 70; // height of play button
    int  aboutButtonY = 541; //location where the play button will start drawing in y axis reversed!
    int aboutButtonX = 690; //location where the play button will start drawing in x axis
    int aboutButtonWidth = 200; // width of play Button
    int aboutButtonHeight = 70; // height of play button
    int  needHelpButtonY = 641; //location where the play button will start drawing in y axis reversed!
    int needHelpButtonX = 690; //location where the play button will start drawing in x axis
    int needHelpButtonWidth = 200; // width of play Button
    int needHelpButtonHeight = 70; // height of play button
    public MainMenuScreen(ButchGame game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 1600, 800);
        batch = new SpriteBatch();
        texture_back = new Texture(Gdx.files.internal("Butch Title 2 Final.png"));
        texture_back.setFilter(Linear,Linear);
        sprite_back = new Sprite(texture_back);
        sprite_back.setRegionWidth(1600);
        sprite_back.setRegionHeight(800);
        sprite_back.flip(false, true);


        playButtonActive = new Texture ("playButton.png");
        aboutButton = new Texture("aboutButton.png");
        needHelpButton = new Texture("needHelpButton.png");
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        //render code

        batch.draw(sprite_back,0,0);
       float x =  1600/2 - playButtonWidth/2;
        //float y = ButchGame.TARGET_HEIGHT/2 - playButtonActive.getWidth();

        if(Gdx.input.getX()>=  playButtonX && Gdx.input.getX() <= playButtonWidth + playButtonX && Gdx.input.getY() >= playButtonY  && Gdx.input.getY() < playButtonY + playButtonHeight ) {

            batch.draw(playButtonActive, playButtonX, playButtonY , playButtonWidth,playButtonHeight);
            if (Gdx.input.isTouched()) {
                this.dispose();
                game.doit();
                game.create2();

                game.render2();

            }
        }
        //695, 441, 200,70)
        //700, 461, 180, 50
        else{
            batch.draw(playButtonActive, 700, 461, 180,60);
        }
        if(Gdx.input.getX()>=  aboutButtonX && Gdx.input.getX() <= aboutButtonWidth + aboutButtonX && Gdx.input.getY() >= aboutButtonY  && Gdx.input.getY() < aboutButtonY + aboutButtonHeight ) {

            batch.draw(aboutButton, aboutButtonX, aboutButtonY , aboutButtonWidth,aboutButtonHeight);

        }
        //695, 441, 200,70)
        //700, 461, 180, 50
        else{
            batch.draw(aboutButton, 700, 541, 180,60);
        }
        if(Gdx.input.getX()>=  needHelpButtonX && Gdx.input.getX() <= needHelpButtonWidth + needHelpButtonX && Gdx.input.getY() >= needHelpButtonY  && Gdx.input.getY() < needHelpButtonY + needHelpButtonHeight ) {

            batch.draw(needHelpButton, needHelpButtonX, needHelpButtonY , needHelpButtonWidth,needHelpButtonHeight);

        }
        //695, 441, 200,70)
        //700, 461, 180, 50
        else{
            batch.draw(needHelpButton, 700, 641, 180,60);
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
