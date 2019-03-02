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

public class Animal extends Renderable {
    private enum State {IDLE}
    private int animalType;
    private Circle activateRange;
    private Sound animalNoise;
    private boolean interactActive;
    private Animation<TextureRegion> animalAnim;
    private float stateTimer = 0;
    private State currentState, previousState;
    private Sprite sprite = new Sprite(ButchGame.assets.get(ButchGame.assets.enemySprite, Texture.class));

    public Animal(Vector2 position, int animalType) {
        this.TAG = "NPC";
        this.animalType = animalType;
        this.activateRange = new Circle(this.getPosition().x, this.getPosition().y, 400);
        this.setPosition(position);
        this.setSprite(new Sprite(ButchGame.assets.get(ButchGame.assets.enemySprite, Texture.class)));
        this.getSprite().setScale(10);
        this.interactActive = false;
        this.setCollider(new Rectangle(this.getPosition().x, this.getPosition().y, this.getSprite().getBoundingRectangle().width / 2.5f, this.getSprite().getBoundingRectangle().height / 1.5f));
        this.activeForRender = true;
        this.activeCollision = true;
        this.currentState = State.IDLE;
        this.previousState = State.IDLE;
        switch(this.animalType) {
            case 0 : //Chicken
                this.animalNoise = ButchGame.assets.get(ButchGame.assets.revolverReloadEeffect, Sound.class);
                this.animalAnim = new Animation<TextureRegion>(0.3f, ButchGame.assets.get(ButchGame.assets.chickenIdle, TextureAtlas.class).getRegions());
                break;
            case 1 : //Cow
                this.animalNoise = ButchGame.assets.get(ButchGame.assets.revolverReloadEeffect, Sound.class);
                this.animalAnim = new Animation<TextureRegion>(0.3f, ButchGame.assets.get(ButchGame.assets.cowIdle, TextureAtlas.class).getRegions());
                break;
            case 2 : //Goat
                this.animalNoise = ButchGame.assets.get(ButchGame.assets.revolverReloadEeffect, Sound.class);
                this.animalAnim = new Animation<TextureRegion>(0.3f, ButchGame.assets.get(ButchGame.assets.goatIdle, TextureAtlas.class).getRegions());
                break;
            case 3 : //Pig
                this.animalNoise = ButchGame.assets.get(ButchGame.assets.revolverReloadEeffect, Sound.class);
                this.animalAnim = new Animation<TextureRegion>(0.3f, ButchGame.assets.get(ButchGame.assets.pigIdle, TextureAtlas.class).getRegions());
                break;
            case 4 : //Racoon
                this.animalNoise = ButchGame.assets.get(ButchGame.assets.revolverReloadEeffect, Sound.class);
                this.animalAnim = new Animation<TextureRegion>(0.3f, ButchGame.assets.get(ButchGame.assets.racoonIdle, TextureAtlas.class).getRegions());
                break;
            case 5 : //Turkey
                this.animalNoise = ButchGame.assets.get(ButchGame.assets.revolverReloadEeffect, Sound.class);
                this.animalAnim = new Animation<TextureRegion>(0.3f, ButchGame.assets.get(ButchGame.assets.turkeyIdle, TextureAtlas.class).getRegions());
                break;
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
            }catch (NullPointerException e) {
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

    @Override
    public void takeHit(float damage) {

    }

    private TextureRegion getFrame (float dt) {
        TextureRegion region = null;
        currentState = getState();

        region = animalAnim.getKeyFrame(stateTimer, true);

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }
    private State getState() {
        return State.IDLE;
    }
}
