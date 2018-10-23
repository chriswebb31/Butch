package com.butch.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.Player;

public class GameScreen implements Screen {
    public ButchGame game;
    private SpriteBatch batch;
    private ShapeRenderer colliderRenderer;
    private FitViewport gameViewPort;
    private OrthographicCamera camera;
    private float distanceDivisor =  1.5f;
    private Player player;

    public GameScreen(ButchGame game, FitViewport gameViewPort){
        this.game = game;
        this.gameViewPort = gameViewPort;
        this.player = new Player(this);
        player.setPosition(new Vector2(0,0));
        this.batch = new SpriteBatch();
        this.colliderRenderer = new ShapeRenderer();
        //Setup camera and viewport
        this.camera = new OrthographicCamera();
        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2, 40);
        camera.zoom = 1.5f;

        gameViewPort.setCamera(camera);
        gameViewPort.apply();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        updateCameraPosition();
        camera.update();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // This cryptic line clears the screen.
        //batch is sprite renderer, other sprites go here
        player.update(); //player has a sprite so update then draw
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        player.sprite.draw(batch);
        batch.end(); //no more sprites to render
//        colliderRenderer.setProjectionMatrix(camera.combined);
//        colliderRenderer.begin(ShapeRenderer.ShapeType.Filled); //collider rendering for debug
//        colliderRenderer.setColor(255,0,0,0.5f); //MAKE DAT RED
//        colliderRenderer.rect(player.TCollider.getBoundingRectangle().x,player.TCollider.getBoundingRectangle().y, player.TCollider.getBoundingRectangle().width, player.TCollider.getBoundingRectangle().height);
//        colliderRenderer.rect(player.BCollider.getBoundingRectangle().x,player.BCollider.getBoundingRectangle().y, player.BCollider.getBoundingRectangle().width, player.BCollider.getBoundingRectangle().height);
//        colliderRenderer.rect(player.LCollider.getBoundingRectangle().x,player.LCollider.getBoundingRectangle().y, player.LCollider.getBoundingRectangle().width, player.LCollider.getBoundingRectangle().height);
//        colliderRenderer.rect(player.RCollider.getBoundingRectangle().x,player.RCollider.getBoundingRectangle().y, player.RCollider.getBoundingRectangle().width, player.RCollider.getBoundingRectangle().height);
//
//        colliderRenderer.end();
//        System.out.print("PLAYER : " + player.getPosition());
//        System.out.print("CAMERA : " + camera.position);
    }

    private void updateCameraPosition(){
        Vector2 mousePosition = new Vector2(ButchGame.mousePosition().x, ButchGame.mousePosition().y);
        float newX = mousePosition.x + (player.getPosition().x - mousePosition.x) / distanceDivisor;
        float newY = mousePosition.y + (player.getPosition().y - mousePosition.y) / distanceDivisor;
        camera.position.set(new Vector3(newX, newY, camera.position.z));
    }

    @Override
    public void resize(int width, int height) {
        gameViewPort.update(width,height);
        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,camera.position.z);
        camera.update();
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
