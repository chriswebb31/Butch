package com.butch.game.gameobjects.weapons.Ammo;

import com.badlogic.gdx.math.Vector2;
import com.butch.game.gameobjects.abstracts.Bullet;

public class RifleBullet extends Bullet {

    public RifleBullet(Vector2 start, Vector2 direction, float speed) {
        super(start, direction, speed);
        System.out.println("bullet");
    }
}
