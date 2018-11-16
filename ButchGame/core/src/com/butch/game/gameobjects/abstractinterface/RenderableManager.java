package com.butch.game.gameobjects.abstractinterface;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class RenderableManager {
    public static ArrayList<Renderable> renderableObjects;

    public RenderableManager() {
        this.renderableObjects = new ArrayList<Renderable>();
    }

    public void update(float delta) {
        for (int i=0; i<renderableObjects.size();i++) {
            renderableObjects.get(i).update();
        }
    }

    public void render(SpriteBatch spriteBatch) {
        for (int i=0; i<renderableObjects.size();i++) {
            renderableObjects.get(i).getSprite().draw(spriteBatch);
        }
    }
}
