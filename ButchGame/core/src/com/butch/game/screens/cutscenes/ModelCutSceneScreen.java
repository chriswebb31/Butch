package com.butch.game.screens.cutscenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;
import com.butch.game.screens.TransitionScreen;

public abstract class ModelCutSceneScreen implements Screen {
    Stage stage;
    ButchGame game;
    static TransitionScreen transitionScreen;
    public OrthographicCamera camera;
    FitViewport gameViewPort;
    SpriteBatch batch;
    public ModelCutSceneScreen(ButchGame game){
        this.game = game;
        gameViewPort = new FitViewport(game.TARGET_WIDTH,game.TARGET_HEIGHT);
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 1920, 1080);
        batch = new SpriteBatch();
        stage = new Stage(gameViewPort);
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        transitionScreen.transitionIn(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        stage.act(delta);
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            Gdx.app.exit();

        }
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
