package com.butch.game.components;

import com.badlogic.gdx.math.Rectangle;
import com.butch.game.gamemanagers.ColliderManager;

public class Collider {
    private int width;
    private int height;
    private float x;
    private float y;
    private Rectangle boundingRectangle;
    private static ColliderManager CM;

    public Collider(int width, int height, float x, float y, ColliderManager CM){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.CM = CM;

        boundingRectangle = new Rectangle(this.x, this.y, this.width, this.height);

    }

    public Rectangle getBoundingRectangle() {
        return boundingRectangle;
    }

    public boolean isColliding(){
        boolean isCol = false;
        for(int i=0; i < CM.getColliders().size(); i++){
            if(this.boundingRectangle.overlaps(CM.getColliders().get(i).boundingRectangle)){
                isCol = true;
            }
            else{
                isCol = false;
            }
        }
        return isCol;
    }
}
