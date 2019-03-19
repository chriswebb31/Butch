package com.butch.game.screens.cutscenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;
import com.butch.game.screens.GameScreens.PrisonLevel;
import com.butch.game.screens.GameScreens.StartTavern;
import com.butch.game.screens.TransitionScreen;

public class CaveScene extends ModelCutSceneScreen{
    private Animation<TextureRegion> butchAnim, abAnim;
    private Image introBack;
    float statetime = 0;
    float ymove = 0;
    Texture narration;
    boolean moveup = true;
    boolean cutScenestart = true;
    Music playSound;
    public CaveScene(ButchGame game){
        super(game);
        butchAnim = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.butchIdleCutscene, TextureAtlas.class).getRegions());
        abAnim = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.lincolnIdleNPC, TextureAtlas.class).getRegions());
        introBack = new Image(new Texture(ButchGame.assets.caveBack));
        introBack.setSize(game.TARGET_WIDTH, game.TARGET_HEIGHT);
        narration = ButchGame.assets.get(ButchGame.assets.caveAbDialogue,Texture.class);
//        abAnim.getKeyFrame(statetime+1).flip(true,false);
        playSound = ButchGame.assets.get(ButchGame.assets.shotgunFire2, Music.class);
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        transitionScreen.transitionIn(stage);
    }
    @Override
    public  void render(float delta){
        super.render(delta);
        stage.addActor(introBack);
        stage.draw();
        statetime += delta;
//    abAnim.getKeyFrame(statetime, true).flip(true, false);
        batch.begin();
        batch.draw(butchAnim.getKeyFrame(statetime,true), game.TARGET_WIDTH / 19.0f, game.TARGET_HEIGHT / 10, game.TARGET_WIDTH/3.84f, game.TARGET_HEIGHT/1.8f);
        batch.draw(abAnim.getKeyFrame(statetime,true), game.TARGET_WIDTH / 1.4f, game.TARGET_HEIGHT / 10, game.TARGET_WIDTH/3.84f, game.TARGET_HEIGHT/1.8f);
        batch.draw(narration, game.TARGET_WIDTH / 1.8f - narration.getHeight() / 2, game.TARGET_HEIGHT/2.0f, game.TARGET_WIDTH / 3.84f, game.TARGET_HEIGHT/2.734f);
        batch.end();
        if(cutScenestart == true){
            stage.addAction(Actions.sequence(
                    Actions.delay(8),
                    Actions.run(new Runnable() {
                        @Override
                        public void run() {
                            playSound.play();
                            playSound.setOnCompletionListener(new Music.OnCompletionListener() {
                                @Override
                                public void onCompletion(Music music) {
                                    game.setScreen(new PrisonLevel(game, game.gameViewPort,PrisonLevel.map,0));
                                }
                            });

                        }
                    })
            ));

        }
//        if(ymove <= game.TARGET_HEIGHT + narration.getHeight()/2){
//            moveup = true;
//            ymove +=2;
//        }
//        else{
//            moveup = false;
//            //game.setScreen(new StartTavern(game, game.gameViewPort, StartTavern.map, 0));
//        }


    }
    public TextureRegion getFrame(float dt){
        return null;
    }
}