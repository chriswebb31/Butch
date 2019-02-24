package com.butch.game.gameobjects.abstractinterface;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.butch.game.ButchGame;

public abstract class Item extends ItemPickup {
    public enum State {ACTIVE}
    public State currentState = State.ACTIVE;
    public State previousState = State.ACTIVE;
    public Animation<TextureRegion> itemAnim;
    public int quantity;
    public boolean autoPickup = false;
    private float stateTimer = 0;
    private Sprite sprite = new Sprite(ButchGame.assets.get(ButchGame.assets.coinItemSprite, Texture.class));
    public String messageCheck;

    public Item(Vector2 position) {
        super(position);
        this.type = 2;
    }

    public void update(float delta) {
        sprite.setRegion(getFrame(delta));
        this.setSprite(sprite);
    }

    public TextureRegion getFrame(float dt) {
        TextureRegion region = null;
        currentState = GetState();
//        System.out.println(this.id);
//        System.out.println(this.itemAnim.getKeyFrame(stateTimer, true));
        region = itemAnim.getKeyFrame(stateTimer, true);
        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;

        return region;
    }

    private State GetState() {
        return State.ACTIVE;
    }
}