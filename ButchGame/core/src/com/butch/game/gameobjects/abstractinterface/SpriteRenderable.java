package com.butch.game.gameobjects.abstractinterface;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public abstract class SpriteRenderable {
    public static ArrayList<SpriteRenderable> spriteRenderables; //want to sort by y-index for correct stacking
    public Sprite sprite;
    public Rectangle collider;
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

    public static void Render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer, float delta){
        //sort then render
        spriteBatch.begin();
        for (SpriteRenderable spriteRenderable : spriteRenderables) {
            spriteRenderable.update(delta);
            System.out.println("delta: "+delta);
            spriteRenderable.sprite.draw(spriteBatch);
        }
        spriteBatch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        for (SpriteRenderable spriteRenderable : spriteRenderables) {
            shapeRenderer.rect(spriteRenderable.collider.x, spriteRenderable.collider.y, spriteRenderable.collider.width, spriteRenderable.collider.height);
        }
        shapeRenderer.end();
    }

}
