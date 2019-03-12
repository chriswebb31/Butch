package com.butch.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.butch.game.ButchGame;

public class TransitionScreen implements Screen {
    public TransitionScreen(){
    }
    public static void transitionOut(final Screen newScreen, Stage stage, final ButchGame game){
        stage.addAction(Actions.sequence(Actions.fadeOut(0.5f),
                Actions.run(new Runnable(){
                    @Override
                    public void run(){
                        game.setScreen(newScreen);
                    }
                })));
    }
    public static void transitionIn(Stage stage){
        stage.addAction(Actions.sequence(Actions.alpha(0),Actions.fadeIn(0.5f)));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
