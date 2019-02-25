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

public class NPC extends Renderable {
    public enum State {IDLE, MOVING}
    public float health;
    public Circle activateRange;
    private boolean interactActive;
    private Sound speak;
    private boolean hasSpoken = false;
    private Animation<TextureRegion> npcMove;
    private Animation<TextureRegion> npcIdle;
    private float stateTimer = 0;
    private State currentState, previousState;
    private Sprite sprite = new Sprite(ButchGame.assets.get(ButchGame.assets.enemySprite, Texture.class));

    public NPC(Vector2 position, TextureAtlas idleAtlas) {
        this.TAG = "npc";
        this.activateRange = new Circle(this.getPosition().x, this.getPosition().y, 400);
        this.setPosition(position);
        this.health = 100;
        this.setSprite(new Sprite(ButchGame.assets.get(ButchGame.assets.enemySprite, Texture.class)));
        this.getSprite().setScale(10);
        this.setCollider(new Rectangle(this.getPosition().x, this.getPosition().y, this.getSprite().getBoundingRectangle().width/2.5f, this.getSprite().getBoundingRectangle().height/1.5f));
        this.activeForRender = true;
        this.activeCollision = true;
        this.speak = ButchGame.assets.get(ButchGame.assets.revolverReloadEeffect, Sound.class);
        //npcMove = new Animation<TextureRegion>(0.1f, moveAtlas.getRegions());
        npcIdle = new Animation<TextureRegion>(0.3f, idleAtlas.getRegions());
        currentState = State.IDLE;
        previousState = State.IDLE;
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
        }//CHECK IF SHOULD FIGHT
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

        if(this.health <= 0) {
            this.activeCollision = false;
            this.activeForRender = false;
        }
    }

    private TextureRegion getFrame (float dt) {
        TextureRegion region = null;
        currentState = getState();

        region = npcIdle.getKeyFrame(stateTimer, true);

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }

    @Override
    public void takeHit(float damage) {
        this.health -= damage;
    }

    private State getState() {
        return State.IDLE;
    }

}
