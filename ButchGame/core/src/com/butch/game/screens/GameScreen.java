package com.butch.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.butch.game.ButchGame;
import com.butch.game.gamemanagers.AssetManagement;
import com.butch.game.gamemanagers.GameStateManager;
import com.butch.game.gameobjects.Player;

public class GameScreen implements Screen {
    public ButchGame game;
    private SpriteBatch batch;
    private ShapeRenderer colliderRenderer;

    private Player player;

    public GameScreen(ButchGame game){
        this.game = game;
        this.player = new Player(this);
        this.batch = new SpriteBatch();
        this.colliderRenderer = new ShapeRenderer();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // This cryptic line clears the screen.
        batch.begin();
        // Drawing goes here!
        player.update();
        player.sprite.draw(batch);
        batch.end();
        colliderRenderer.begin(ShapeRenderer.ShapeType.Filled);
        colliderRenderer.setColor(255,0,0,0.5f);
        colliderRenderer.rect(player.TCollider.getBoundingRectangle().x,player.TCollider.getBoundingRectangle().y, player.TCollider.getBoundingRectangle().width, player.TCollider.getBoundingRectangle().height);
        colliderRenderer.rect(player.BCollider.getBoundingRectangle().x,player.BCollider.getBoundingRectangle().y, player.BCollider.getBoundingRectangle().width, player.BCollider.getBoundingRectangle().height);
        colliderRenderer.rect(player.LCollider.getBoundingRectangle().x,player.LCollider.getBoundingRectangle().y, player.LCollider.getBoundingRectangle().width, player.LCollider.getBoundingRectangle().height);
        colliderRenderer.rect(player.RCollider.getBoundingRectangle().x,player.RCollider.getBoundingRectangle().y, player.RCollider.getBoundingRectangle().width, player.RCollider.getBoundingRectangle().height);

        colliderRenderer.end();
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

//    public AssetManagement getAssets() {
//        return assets;
//    }
}
