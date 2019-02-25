package com.butch.game.gameobjects.abstractinterface;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;

public abstract class ItemPickup extends Renderable{
    public enum State {ACTIVE}
    public State currentState = State.ACTIVE;
    public State previousState = State.ACTIVE;
    public float stateTimer = 0;
    public int id;
    public Circle collectionRange;
    public boolean colleactable = false;
    public int type;
    public Sound collectionFX;
    public Animation<TextureRegion> itemAnim;
    public Sprite sprite = new Sprite(ButchGame.assets.get(ButchGame.assets.coinItemSprite, Texture.class));

    public ItemPickup(Vector2 position){
        this.TAG = "item";
        this.setPosition(position);
        this.collectionRange = new Circle(this.getPosition().x, this.getPosition().y, 200);
        this.collectionFX = ButchGame.assets.get(ButchGame.assets.ammoCollection, Sound.class);

    }

    @Override
    public void update(float delta) {
        if (activeForRender && getSprite() != null) {
            if(this.id == 3) {
                sprite.setRegion(getFrame(delta));
                this.setSprite(sprite);
                System.out.println(getFrame(delta));
                this.getSprite().setScale(4);
            } else {
                this.getSprite().setScale(10);
            }
            System.out.println(this.id);

            this.getSprite().setPosition(this.getPosition().x, this.getPosition().y);
            this.setCollider(new Rectangle(this.getPosition().x, this.getPosition().y, this.getSprite().getWidth() / 3, this.getSprite().getHeight() / 2));
        }
    }


    @Override
    public void takeHit(float damage) {

    }

    public void collected(){
        if(this.collectionFX != null){
            collectionFX.play();
        }
    }

    public TextureRegion getFrame(float dt) {
        TextureRegion region = null;
        currentState = GetState();
//        System.out.println(this.id);
//        System.out.println(this.itemAnim.getKeyFrame(stateTimer, true));
        switch(currentState) {
            case ACTIVE:
                region = this.itemAnim.getKeyFrame(stateTimer, true);
                break;
        }
        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        System.out.println(region);
        return region;
    }

    private State GetState() {
        return State.ACTIVE;
    }

}
