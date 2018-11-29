package com.butch.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;
;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;

public class NeedHelpScreen implements Screen {

    private ButchGame game;
    private OrthographicCamera camera;
    private Sprite exitButtonActive,exitButtonInactive;
    public ImageButton exitButton;
    Stage stage;
    private Sound sound;
    FitViewport gameViewPort;


    public NeedHelpScreen(ButchGame game, FitViewport gameViewPort){
        this.gameViewPort = gameViewPort;
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 1920, 1080);
        stage = new Stage(gameViewPort);

        exitButtonActive = new Sprite(ButchGame.assets.get(ButchGame.assets.exitButtonActive, Texture.class));
        sound = ButchGame.assets.get(ButchGame.assets.menuClick, Sound.class);



    }
//    public static void main(String[] args) throws MalformedURLException {
//
//        URL url = new URL("gifs.gif");
//        Icon icon = new ImageIcon(url);
//        JLabel label = new JLabel(icon);
//
//        JFrame f = new JFrame("Animation");
//        f.getContentPane().add(label);
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.pack();
//        f.setLocation(100,100);
//        f.setVisible(true);
//    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        createButtons();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(255f,215f,0f,1f); // setting the background white, however this doesn't...
        //...matter as there will be a background anyway.
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

        update(delta);




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
    public void createButtons(){
        exitButtonInactive = new Sprite(ButchGame.assets.get(ButchGame.assets.exitButtonInactive, Texture.class));
        exitButton = new ImageButton(new SpriteDrawable(exitButtonInactive), new SpriteDrawable(exitButtonActive));
        exitButton.setBounds(10,10,251,71);

        exitButton.addListener(new ClickListener(){
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor){
                exitButton.setBounds(10,10,251,81);

            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor){
                exitButton.setBounds(10,10,251,71);

            }
            public void clicked(InputEvent event, float x, float y){
                sound.play();
                game.setScreen(new MainMenuScreen(game, gameViewPort));
            }

        });
        stage.addActor(exitButton);
    }

    @Override
    public void dispose() {
        this.dispose();
        exitButtonActive.getTexture().dispose();
        exitButtonInactive.getTexture().dispose();
    }
}
