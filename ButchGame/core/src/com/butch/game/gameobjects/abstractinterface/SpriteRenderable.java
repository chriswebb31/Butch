package com.butch.game.gameobjects.abstractinterface;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public abstract class SpriteRenderable {
    public static ArrayList<SpriteRenderable> spriteRenderables; //want to sort by y-index for correct stacking
    public Sprite sprite;
    public Vector2 position;

    public SpriteRenderable(){
        try{
            if(spriteRenderables == null)
                spriteRenderables = new ArrayList<SpriteRenderable>();
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        spriteRenderables.add(this);
    }

    public abstract void update(float delta);

    public static void Render(SpriteBatch spriteBatch, float delta){
        //sort then render

        for (SpriteRenderable spriteRenderable : spriteRenderables) {
            spriteRenderable.update(delta);
            System.out.println("delta: "+delta);
            spriteRenderable.sprite.draw(spriteBatch);
        }
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
