package com.butch.game.screens.GameScreens;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.abstractinterface.Gun;
import com.butch.game.gameobjects.spriterenderables.Player;

import java.util.ArrayList;

public class NewGameScreen extends ModelGameScreen {
    public static TiledMap map = ButchGame.assets.get(ButchGame.assets.tilemap1);
//    private int uiScale=2;
//    private Stage uiStage;
//    private Table root;
//    private DialogueBox dialogueBox;
//    boolean cutsceneStart = true;
//    Vector2 movingpos;
//    float currentPos, statetime;
    Music playSound;
    public NewGameScreen(int levelNumber, ButchGame game, FitViewport gameViewPort, TiledMap map, ArrayList<Gun> weaponCache, int playerLevel, int spawnLocation){

        super(levelNumber, game, gameViewPort, map, weaponCache, playerLevel, spawnLocation);
//        System.out.println();
        //tiledMap = ButchGame.assets.get(ButchGame.assets.route1);
//        currentPos = player.getPosition().x;
//        statetime = 0;
       // dialogueBox = new DialogueBox(new Skin(Gdx.files.internal("Data/uiskin.json")));
//        initUI();
        playSound = ButchGame.assets.get(ButchGame.assets.playSound, Music.class);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta){

        for(Rectangle endPointLoc : endPoints) {
            if(player.getCollider().overlaps(endPointLoc)) {
                if(endPoints.indexOf(endPointLoc) == 2) {
                    game.setScreen( new Level2(1, game, gameViewPort, Level2.map, player.getGunInventory(), player.getPlayerLevel(), 0));
                } else if (endPoints.indexOf(endPointLoc) == 1) {
                    game.setScreen((new Warzone(2, game, gameViewPort, Warzone.map, player.getGunInventory(), player.getPlayerLevel(), 0)));
                } else if (endPoints.indexOf(endPointLoc) == 0) {
                    player.isAllowedToMove = false;
                    player.getFrame(delta, Player.State.IDLE);
                    playSound.play();
                    playSound.setOnCompletionListener(new Music.OnCompletionListener() {
                        @Override
                        public void onCompletion(Music music) {
                            music.play();
                            playSound.dispose();
                            player.isAllowedToMove = true;
                            game.setScreen((new StartTavern(2, game, gameViewPort, StartTavern.map, player.getGunInventory(), player.getPlayerLevel(), 0)));
                        }
                    });

                }
            }
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

//        if(player.getPosition().x <= currentPos + 2000&& cutsceneStart ==true){

           // movingpos = new Vector2(player.getPosition().x+10.0f, player.getPosition().y);
//            player.getFrame(delta).setRegion(player.butchWalking.getKeyFrame(statetime,true));
           // player.setPosition(movingpos);

//        }
//        else{
//            cutsceneStart=false;
//            player.getFrame(delta).setRegion(player.butchWalking.getKeyFrame(statetime,false));
            // player.getFrame(statetime);
            //player.currentState = player.getState();
//        }
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