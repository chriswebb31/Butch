package com.butch.game.components;

import com.badlogic.gdx.math.Rectangle;
import com.butch.game.ButchGame;

public class Collider {
    private Rectangle boundingRectangle;

    public Collider(int width, int height, float x, float y){
        //CREATE COLLIDER WITH BOUND DIMENSIONS
        boundingRectangle = new Rectangle(x, y, width, height);

    }

    public Rectangle getBoundingRectangle() {
        //COLLIDER BOUNDS
        return boundingRectangle;
    }

    public boolean isColliding(){
        // COULD AND SHOULD BE IMPROVED VERY INEFFICIENT
        boolean isCol = false;
        for(int i = 0; i < ButchGame.CM.getColliders().size(); i++){
            if(!(this.boundingRectangle == ButchGame.CM.getColliders().get(i).boundingRectangle) && this.boundingRectangle.overlaps(ButchGame.CM.getColliders().get(i).boundingRectangle)) //IF OVERLAPS WITH ANY EXCEPT ITSELF
                isCol = true;
        }
        System.out.println(isCol);
        return isCol;
    }
}
