package com.butch.game.gamemanagers;

import com.badlogic.gdx.math.Rectangle;
import com.butch.game.components.Collider;

import java.util.ArrayList;
import java.util.List;

public class ColliderManager {
    private static ArrayList<Collider> colliders;

    public ColliderManager(){
        colliders = new ArrayList<Collider>();
    }

    public void addCollider(Collider collider){
        try {
            if(!colliders.contains(collider)){
                colliders.add(collider);
                System.out.println("Added Collider : "  + collider.hashCode()); //debugging
            }
            else{
                System.out.println("Failed to add Collider : "  + collider.hashCode()); //debugging
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Collider> getColliders(){
        return colliders;
    }
}
