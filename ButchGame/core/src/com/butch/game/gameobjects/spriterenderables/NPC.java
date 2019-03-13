package com.butch.game.gameobjects.spriterenderables;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.audio.Sound;
import com.butch.game.ButchGame;
import com.butch.game.gamemanagers.RenderableManager;
import com.butch.game.gameobjects.abstractinterface.Renderable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class NPC extends Renderable {
    public enum State {IDLE, MOVING}
    private int npcType;
    private String npcName;
    private String npcText;
    private String npcTextFollowup;
    public Circle activateRange;
    public Circle deActivateRange;
    private boolean interactActive = false;
    private boolean interactDeactivate = false;
    private Sound speak;
    private boolean hasSpoken = false;
    private Animation<TextureRegion> npcAnim;
    private float stateTimer = 0;
    private State currentState, previousState;
    private Sprite sprite = new Sprite(ButchGame.assets.get(ButchGame.assets.enemySprite, Texture.class));
    public boolean isSpeaking = false;

    public NPC(Vector2 position, String npcName) {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("NPC/" + npcName + ".properties");

            // load a properties file
            prop.load(input);
            this.TAG = "npc";
            this.npcType = Integer.parseInt(prop.getProperty("type"));
            this.npcText = prop.getProperty("speech");
            this.npcTextFollowup = prop.getProperty("speechFollowup");
            this.npcName = npcName;
            this.activateRange = new Circle(this.getPosition().x, this.getPosition().y, 100);
            this.deActivateRange = new Circle(this.getPosition().x, this.getPosition().y, 110);
            this.setPosition(position);
            this.setSprite(new Sprite(ButchGame.assets.get(ButchGame.assets.enemySprite, Texture.class)));
            this.getSprite().setScale(10);
            this.setCollider(new Rectangle(this.getPosition().x, this.getPosition().y, this.getSprite().getBoundingRectangle().width / 2.5f, this.getSprite().getBoundingRectangle().height / 1.5f));
            this.activeForRender = true;
            this.activeCollision = true;
            this.speak = ButchGame.assets.get(ButchGame.assets.revolverReloadEeffect, Sound.class);
            //npcMove = new Animation<TextureRegion>(0.1f, moveAtlas.getRegions());
            switch(npcType) {
                case 0:
                    this.npcAnim = new Animation<TextureRegion>(0.225f, ButchGame.assets.get(ButchGame.assets.npc1Idle, TextureAtlas.class).getRegions());
                    break;
                case 1:
                    this.npcAnim = new Animation<TextureRegion>(0.275f, ButchGame.assets.get(ButchGame.assets.npc2Idle, TextureAtlas.class).getRegions());
                    break;
                case 2:
                    this.npcAnim = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.npc3Idle, TextureAtlas.class).getRegions());
                    break;
                case 3:
                    this.npcAnim = new Animation<TextureRegion>(0.3f, ButchGame.assets.get(ButchGame.assets.npc4Idle, TextureAtlas.class).getRegions());
                    break;
                case 4:
                    this.npcAnim = new Animation<TextureRegion>(0.24f, ButchGame.assets.get(ButchGame.assets.npc5Idle, TextureAtlas.class).getRegions());
                    break;
                case 5:
                    this.npcAnim = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.npc6Walking, TextureAtlas.class).getRegions());
                    break;
                case 6:
                    this.npcAnim = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.npc7Walking, TextureAtlas.class).getRegions());
                    break;
                case 7:
                    this.npcAnim = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.npc8Walking, TextureAtlas.class).getRegions());
                    break;
                case 8:
                    this.npcAnim = new Animation<TextureRegion>(0.26f, ButchGame.assets.get(ButchGame.assets.npc9Idle, TextureAtlas.class).getRegions());
                    break;
                case 9:
                    this.npcAnim = new Animation<TextureRegion>(0.275f, ButchGame.assets.get(ButchGame.assets.npc10Idle, TextureAtlas.class).getRegions());
                    break;
                case 10:
                    this.npcAnim = new Animation<TextureRegion>(0.245f, ButchGame.assets.get(ButchGame.assets.npc11Idle, TextureAtlas.class).getRegions());
                    break;
                case 11:
                    this.npcAnim = new Animation<TextureRegion>(0.235f, ButchGame.assets.get(ButchGame.assets.npc12Idle, TextureAtlas.class).getRegions());
                    break;
                case 12:
                    this.npcAnim = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.npc13Idle, TextureAtlas.class).getRegions());
                    break;
            }
            currentState = State.IDLE;
            previousState = State.IDLE;
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(float delta) {
//        if(!this.interactActive) {
            try{
                for(Renderable renderable: RenderableManager.renderableObjects){
                    if (renderable.TAG == "player") {
                        if (Intersector.overlaps(this.activateRange, renderable.getCollider())) {
                            this.interactActive = true;
                        } else {
                            this.interactActive = false;
                        }
                    }
                }
            }catch (NullPointerException e){
                e.printStackTrace();
            }
//        }//CHECK IF SHOULD SPEAK
//        else{
//            try {
//                if(!hasSpoken) {
//                    speak.play();
//                    hasSpoken = true;
//                }
//
//            }catch (NullPointerException e){
//                e.printStackTrace();
//            }
//
//        }
//        if(!this.interactDeactivate) {
            try{
                for(Renderable renderable: RenderableManager.renderableObjects){
                    if (renderable.TAG == "player") {
                        if (Intersector.overlaps(this.deActivateRange, renderable.getCollider())) {
                            this.interactDeactivate = true;
                        } else {
                            this.interactDeactivate = false;
                        }
                    }
                }
            }catch (NullPointerException e){
                e.printStackTrace();
            }
//        }

        sprite.setRegion(getFrame(delta));
        this.setSprite(sprite);

        this.getSprite().setScale(8);
        this.getSprite().setPosition(this.getPosition().x, this.getPosition().y);
        this.getCollider().setPosition(this.getPosition());
        this.activateRange = new Circle(this.getPosition().x, this.getPosition().y, 400);
        this.deActivateRange = new Circle(this.getPosition().x, this.getPosition().y, 500);


    }

    private TextureRegion getFrame (float dt) {
        TextureRegion region = null;
        currentState = getState();

        region = npcAnim.getKeyFrame(stateTimer, true);

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }

    @Override
    public void takeHit(float damage) {
    }

    private State getState() {
        return State.IDLE;
    }

    public boolean getInteractActive() {
        return interactActive;
    }

    public boolean getInteractDeactivate() {
        return interactDeactivate;
    }

    public String getNpcText() {
        return npcText;
    }

    public boolean getHasSpoken() {
        return hasSpoken;
    }

    public void setHasSpoken(boolean hasSpoken) {
        this.hasSpoken = true;
    }
}
