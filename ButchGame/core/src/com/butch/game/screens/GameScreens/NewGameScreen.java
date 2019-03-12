package com.butch.game.screens.GameScreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.butch.game.ButchGame;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.butch.game.dialouge.DialogueBox;

public class NewGameScreen extends ModelGameScreen {
   public static TiledMap map = ButchGame.assets.get(ButchGame.assets.tilemap1);
   private int uiScale=2;
   private Stage uiStage;
   private Table root;
   private DialogueBox dialogueBox;
    boolean cutsceneStart = true;
    Vector2 movingpos;
    float currentPos, statetime;

    public NewGameScreen(int levelNumber, ButchGame game, FitViewport gameViewPort, TiledMap map){

    super(levelNumber,game,gameViewPort, map, 1);
        //tiledMap = ButchGame.assets.get(ButchGame.assets.route1);
        currentPos = player.getPosition().x;
        statetime = 0;
       // dialogueBox = new DialogueBox(new Skin(Gdx.files.internal("Data/uiskin.json")));
        initUI();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta){
        if(player.getCollider().overlaps(endPoint)){

//            Actions.run(new Runnable(){
//                @Override
//                public void run(){
//                    game.setScreen(new Level2(2,game, gameViewPort, player.getGunInventory(), Level2.map, player.getPlayerLevel()));
//                }
//                        }
//            )));
            game.setScreen(new Level2(2,game, gameViewPort, player.getGunInventory(), Level2.map, player.getPlayerLevel()));
       //  game.setScreen(new PrisonLevel(4, game, gameViewPort, player.getGunInventory(), player.getPlayerLevel()));
        }

        super.render(delta);
//        uiStage.act(delta);
//        uiStage.draw();


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

        if(player.getPosition().x <= currentPos + 2000&& cutsceneStart ==true){

            movingpos = new Vector2(player.getPosition().x+20.0f, player.getPosition().y);
//            player.getFrame(delta).setRegion(player.butchWalking.getKeyFrame(statetime,true));
            player.setPosition(movingpos);

        }
        else{
            cutsceneStart=false;
//            player.getFrame(delta).setRegion(player.butchWalking.getKeyFrame(statetime,false));
            // player.getFrame(statetime);
            //player.currentState = player.getState();
        }
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

//     uiStage =new Stage(new ScreenViewport());
//     uiStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),true);
//
//     root = new Table();
//     root.setFillParent(true);
//     uiStage.addActor(root);
//     Skin skin = new Skin(Gdx.files.internal("Data/uiskin.json"));
//     dialogueBox = new DialogueBox(skin);
//     dialogueBox.animateText(" test case examp this shouls be a rolling test example and some shit \n i like dogs and want more play time ");
//     //dialogueBox.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
//
//     root.add(dialogueBox)
//             .expand()
//             .align(Align.bottom)
//             .pad(8f);
//
    }
}