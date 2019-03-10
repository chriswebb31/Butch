package com.butch.game.screens.GameScreens;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;

public class NewGameScreen extends ModelGameScreen {
   public static TiledMap map = ButchGame.assets.get(ButchGame.assets.tilemap1);
   boolean cutsceneStart = true;
   Vector2 movingpos;
   float currentPos, statetime;
    public NewGameScreen(int levelNumber, ButchGame game, FitViewport gameViewPort, TiledMap map){

    super(levelNumber,game,gameViewPort, map, 1);
        //tiledMap = ButchGame.assets.get(ButchGame.assets.route1);
        currentPos = player.getPosition().x;
        statetime = 0;
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
        statetime = statetime + delta;
        if(player.getPosition().x <= currentPos + 2000&& cutsceneStart ==true){
            movingpos = new Vector2(player.getPosition().x+10.0f, player.getPosition().y);
           // player.getFrame(delta).setRegion(player.butchWalking.getKeyFrame(statetime,true));
            player.setPosition(movingpos);

        }
        else{
            cutsceneStart=false;
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
}