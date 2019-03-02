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
    private boolean interactActive = false;
    private Sound speak;
    private boolean hasSpoken = false;
    private Animation<TextureRegion> npcAnim;
    private float stateTimer = 0;
    private State currentState, previousState;
    private Sprite sprite = new Sprite(ButchGame.assets.get(ButchGame.assets.enemySprite, Texture.class));

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
            this.activateRange = new Circle(this.getPosition().x, this.getPosition().y, 400);
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
                    this.npcAnim = new Animation<TextureRegion>(0.25f, ButchGame.assets.get(ButchGame.assets.npc1Idle, TextureAtlas.class).getRegions());
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
        if(!this.interactActive) {
            try{
                for(Renderable renderable: RenderableManager.renderableObjects){
                    if (renderable.TAG == "player") {
                        if (Intersector.overlaps(this.activateRange, renderable.getCollider())) {
                            this.interactActive = true;
                        }
                    }
                }
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }//CHECK IF SHOULD SPEAK
        else{
            try {
                if(!hasSpoken) {
                    speak.play();
                    hasSpoken = true;
                }

            }catch (NullPointerException e){
                e.printStackTrace();
            }

        }

        sprite.setRegion(getFrame(delta));
        this.setSprite(sprite);

        this.getSprite().setScale(8);
        this.getSprite().setPosition(this.getPosition().x, this.getPosition().y);
        this.getCollider().setPosition(this.getPosition());
        this.activateRange = new Circle(this.getPosition().x, this.getPosition().y, 400);


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
