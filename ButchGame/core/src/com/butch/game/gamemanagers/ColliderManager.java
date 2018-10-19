package com.butch.game.gamemanagers;

import com.butch.game.components.Collider;

import java.util.List;

public class ColliderManager {
    private static List<Collider> colliders;

    public ColliderManager(){

    }

    public void addCollider(Collider collider){
        if(!colliders.contains(collider)){
            colliders.add(collider);
        }
    }

    public List<Collider> getColliders(){
        return colliders;
    }
}
