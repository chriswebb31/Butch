package com.butch.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.butch.game.ButchGame;

import static com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;

public class MainMenuScreen implements Screen {
    ButchGame game;
    OrthographicCamera camera;
    SpriteBatch batch;
    Texture texture_back;
    Sprite sprite_back;
    //TextureRegion mainBackground;
    public MainMenuScreen(ButchGame game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 1920, 1080);
        batch = new SpriteBatch();
        texture_back = new Texture(Gdx.files.internal("Butch Title 2 Final.png"));
        //mainBackground = new TextureRegion(texture_back,0,0,1600,800);
        texture_back.setFilter(Linear,Linear);
        sprite_back = new Sprite(texture_back);
        sprite_back.setRegionWidth(Math.round(ButchGame.TARGET_WIDTH));
        sprite_back.setRegionHeight(Math.round(ButchGame.TARGET_HEIGHT));
        sprite_back.flip(false, true);
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
