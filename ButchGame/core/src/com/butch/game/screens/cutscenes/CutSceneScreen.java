package com.butch.game.screens.cutscenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
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
    private Label welcomeText, briefText;
    private Label continueText;
    private Image introBack, bubbleSpeech;
    boolean skip = false;
    public CutSceneScreen(ButchGame game, FitViewport gameViewPort){
        this.game = game;
        this.gameViewPort = gameViewPort;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 1920, 1080);
        npcAnim = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.npc1Idle, TextureAtlas.class).getRegions());
        batch = new SpriteBatch();
        stage = new Stage(gameViewPort);
        introBack = new Image(ButchGame.assets.get(ButchGame.assets.introBack, Texture.class));
        introBack.setSize(game.TARGET_WIDTH, game.TARGET_HEIGHT);
        bubbleSpeech = new Image(ButchGame.assets.get(ButchGame.assets.bubbleSpeech, Texture.class));
        bubbleSpeech.setBounds(game.TARGET_WIDTH/1.855072464f,game.TARGET_HEIGHT/2.07293666f,game.TARGET_WIDTH/2.391033624f,game.TARGET_HEIGHT/2.720403023f);
        welcomeText = new Label (String.format("Welcome to Butch"), ButchGame.assets.get(ButchGame.assets.uiskin, Skin.class));
        welcomeText.setColor(Color.BLACK);
        welcomeText.setPosition(game.TARGET_WIDTH*0.4f, game.TARGET_HEIGHT*0.8f);
        welcomeText.setFontScale(5.0f);
        welcomeText.setPosition(game.TARGET_WIDTH/1.75663312f,game.TARGET_HEIGHT/1.341614907f);
        briefText = new Label(String.format("This is an Adventure Game which will\n blow your mind"), ButchGame.assets.get(ButchGame.assets.uiskin, Skin.class));
        briefText.setColor(Color.BLACK);
        briefText.setAlignment(Align.center);
        briefText.setFontScale(2.5f);
        briefText.setPosition(game.TARGET_WIDTH/1.4803312f,game.TARGET_HEIGHT/1.610140845f);
        //briefText.setFontScale(2.5f);
        continueText = new Label(String.format("Click To Continue!"), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        continueText.setPosition(game.TARGET_WIDTH/1.5f, game.TARGET_HEIGHT/14);
        continueText.setFontScale(2.5f);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1f,1f,1f,1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isTouched()&& skip == true){
            game.setScreen(new NewGameScreen(1,game, gameViewPort,NewGameScreen.map));
        }
        else {
            stage.addActor(introBack);
            stage.addAction(Actions.sequence(
                    Actions.run(new Runnable() {
                                    @Override
                                    public void run() {
                                        //stage.addActor(introBack);
                                        stage.addActor(bubbleSpeech);
                                        stage.draw();
                                    }
                                }
                    ),
                    Actions.run(new Runnable() {
                                    @Override
                                    public void run() {
                                        stage.addActor(welcomeText);
                                        stage.draw();
                                    }
                                }
                    ),
                    Actions.delay(1),
                    Actions.run(new Runnable() {
                                    @Override
                                    public void run() {
                                        stage.addActor(briefText);
                                        stage.draw();

                                    }
                                }
                    ),Actions.delay(3),
                    Actions.run(new Runnable() {
                                    @Override
                                    public void run() {
                                        stage.addActor(continueText);
                                        stage.draw();
                                        skip = true;
                                    }
                                }
                    )

            ));
            transitionScreen.transitionIn(stage);
            camera.update();
            update(delta);
            stateTime += delta;
            batch.begin();
            batch.draw(npcAnim.getKeyFrame(stateTime, true), game.TARGET_WIDTH / 10, game.TARGET_HEIGHT / 10, game.TARGET_WIDTH/3.84f, game.TARGET_HEIGHT/1.8f);
            batch.end();

            //System.out.println("game.targetwidth is"+game.TARGET_WIDTH + game.TARGET_HEIGHT);
            //stage.draw();
        }
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
