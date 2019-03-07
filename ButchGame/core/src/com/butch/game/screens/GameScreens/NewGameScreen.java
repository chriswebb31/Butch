package com.butch.game.screens.GameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.butch.game.ButchGame;
import com.butch.game.screens.GameScreens.Level2;
import com.butch.game.screens.GameScreens.ModelGameScreen;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.butch.game.dialouge.DialougeBox;

public class NewGameScreen extends ModelGameScreen {
   public static TiledMap map = ButchGame.assets.get(ButchGame.assets.tilemap1);
   private int uiScale=2;
   private Stage uiStage;
   private Table root;
   private DialougeBox dialougeBox;
    public NewGameScreen(int levelNumber, ButchGame game, FitViewport gameViewPort, TiledMap map){

    super(levelNumber,game,gameViewPort, map, 1);
        //tiledMap = ButchGame.assets.get(ButchGame.assets.route1);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta){
        if(player.getCollider().overlaps(endPoint)){
            game.setScreen(new Level2(2,game, gameViewPort, player.getGunInventory(), Level2.map, player.getPlayerLevel()));
        }
        super.render(delta);
        initUI();

//
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        shapeRenderer.setColor(Color.FIREBRICK);
//
//        for (Renderable renderable: RenderableManager.renderableObjects) {
//            try{
//                if((renderable.TAG == "item" || renderable.TAG == "enemy") && renderable.activeForRender){
//                    if((renderable.TAG == "item")) {
//                        ItemPickup item = (ItemPickup) renderable;
//                        shapeRenderer.circle(item.collectionRange.x, item.collectionRange.y, item.collectionRange.radius);
//                    } else if(renderable.TAG == "enemy" && renderable.activeCollision){
//                        Enemy enemy = (Enemy) renderable;
//                        shapeRenderer.circle(enemy.activateRange.x, enemy.activateRange.y, enemy.activateRange.radius);
//                    }
//                }
//            } catch (NullPointerException e){
//                e.printStackTrace();
//            }
//            try{
//                if(renderable.activeCollision){
//                    shapeRenderer.rect(renderable.getCollider().x, renderable.getCollider().y, renderable.getCollider().width, renderable.getCollider().height);
//                } else if(renderable.TAG == "item" || renderable.TAG == "player"){
//                    shapeRenderer.rect(renderable.getCollider().x, renderable.getCollider().y, renderable.getCollider().width, renderable.getCollider().height);
//                }
//            } catch (NullPointerException e){
//                e.printStackTrace();
//            }
//        }
//
//        shapeRenderer.end();

//            renderEnemyHB();
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
    private void initUI(){
     uiStage =new Stage(new ScreenViewport(new OrthographicCamera()));
     uiStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),true);
     root = new Table();
     root.setFillParent(true);

     dialougeBox =new DialougeBox();
     dialougeBox.animateText("test case example");
     dialougeBox.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
     root.add(dialougeBox); //.expand().align(Align.bottom).pad(8f);
        uiStage.addActor(root);
    }
}