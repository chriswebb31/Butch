package com.butch.game.screens.cutscenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;
import com.butch.game.screens.GameScreens.NewGameScreen;
import com.butch.game.screens.TransitionScreen;


public class CutSceneScreen implements Screen {
    Stage stage;
    ButchGame game;
    static TransitionScreen transitionScreen;
    private OrthographicCamera camera;
    FitViewport gameViewPort;
    private Animation<TextureRegion> npcAnim;
    float stateTime;
    SpriteBatch batch;
    public CutSceneScreen(ButchGame game, FitViewport gameViewPort){
        this.game = game;
        this.gameViewPort = gameViewPort;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 1920, 1080);
        stage = new Stage(gameViewPort);
        npcAnim = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.npc1Idle, TextureAtlas.class).getRegions());
        batch = new SpriteBatch();
    }
    @Override
    public void show() {

       transitionScreen.transitionIn(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isTouched()){
            transitionScreen.transitionOut(new NewGameScreen(1,game, gameViewPort,NewGameScreen.map),stage,game);
        }
        camera.update();
        update(delta);
        stateTime += delta;
        batch.begin();
       batch.draw(npcAnim.getKeyFrame(stateTime,true), game.TARGET_WIDTH/10,game.TARGET_HEIGHT/10, 500,600);
       batch.end();
       //System.out.println("game.targetwidth is"+game.TARGET_WIDTH + game.TARGET_HEIGHT);
       stage.draw();
    }
    public void update(float delta){
        stage.act(delta);
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
        batch.dispose();
        stage.dispose();
    }
}
