package com.butch.game.screens.cutscenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class NewsPaperScene implements Disposable {
    private Stage stage;
    private Viewport viewport;
    Image news;
    Texture newspaper;
    public NewsPaperScene(SpriteBatch batch){
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport,batch);
        newspaper = new Texture("core/assets/CutScenes/newspaperCutscene.png");
        news = new Image(newspaper);
        news.setBounds(Gdx.graphics.getWidth()/2-news.getWidth()/2, 0-news.getHeight()/3-50,
                Gdx.graphics.getWidth()/1.5f,Gdx.graphics.getHeight()/0.664200f);
        stage.addActor(news);

    }
    public void draw(){
        stage.draw();
    }
    @Override
    public void dispose() {
        stage.dispose();
    }
}
