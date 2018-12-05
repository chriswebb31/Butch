package com.butch.game.gamemanagers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.butch.game.gameobjects.abstractinterface.Renderable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RenderableManager {
    public static ArrayList<Rectangle> mapColliders;
    public static ArrayList<Renderable> renderableObjects;
    public static ArrayList<Renderable> renderableObjectsToRemove;

    public RenderableManager() {
        this.renderableObjects = new ArrayList<Renderable>();
        this.renderableObjectsToRemove = new ArrayList<Renderable>();
        this.mapColliders = new ArrayList<Rectangle>();
    }

    public void update(float delta) {

        Collections.sort(renderableObjects, new Comparator<Renderable>() { //lamda not in this version :(
            @Override
            public int compare(Renderable o1, Renderable o2) {
                return Float.compare(o2.getPosition().y, o1.getPosition().y) ;
            }
        });

        renderableObjects.removeAll(renderableObjectsToRemove);
        renderableObjectsToRemove.clear();

        for (int i=0; i<renderableObjects.size();i++) {
            if(renderableObjects.get(i).activeForRender){
                renderableObjects.get(i).update(delta);
            } else if(renderableObjects.get(i).destroy){
                renderableObjectsToRemove.add(renderableObjects.get(i));
            }
        }
    }

    public void render(SpriteBatch spriteBatch) {
        for (int i=0; i<renderableObjects.size();i++) {
            if(renderableObjects.get(i).activeForRender){
                System.out.println(renderableObjects.get(i).getClass());
                renderableObjects.get(i).getSprite().draw(spriteBatch);
            }
        }
    }

    public void reset(){
        this.renderableObjects.clear();
        this.renderableObjectsToRemove.clear();
        this.mapColliders.clear();
    }
}
