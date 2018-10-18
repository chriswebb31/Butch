package com.tpgame.demo.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.tpgame.demo.butchobjects.InputManager;
import com.tpgame.demo.butchobjects.Player;

public class PlayScreen implements Screen {
    public OrthographicCamera camera;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private SpriteBatch sb = new SpriteBatch();
    public static InputManager inputManager;
    private static Player playerObject;

    @Override
    public void render(float delta){
        camera.position.set(this.playerObject.getPlayerPosition().x, this.playerObject.getPlayerPosition().y, this.camera.position.z);
        camera.update();
        inputManager.update();
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mapRenderer.setView(camera);
        mapRenderer.render();
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        playerObject.update(delta);
        playerObject.render(sb);
        sb.end();
    }

    @Override
    public void resize(int width, int height){
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    @Override
    public void show(){
        inputManager = new InputManager();
        playerObject = new Player(new Sprite(new Texture("tiles/generic-rpg-Slice.png")), new Vector2(50,50), inputManager);
        map = new TmxMapLoader().load("maps/untitled.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        camera.position.set(this.playerObject.getPlayerPosition().x, this.playerObject.getPlayerPosition().y, this.camera.position.z);
        camera.update();
    }

    @Override
    public  void hide(){
        dispose();
    }

    @Override
    public void pause(){

    }

    @Override
    public void resume(){

    }

    @Override
    public void dispose(){
        map.dispose();
    }



}
