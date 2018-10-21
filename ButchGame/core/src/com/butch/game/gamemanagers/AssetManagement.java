package com.butch.game.gamemanagers;

import com.badlogic.gdx.graphics.Texture;

public class AssetManagement extends com.badlogic.gdx.assets.AssetManager {
    //TEXTURES
    public final String cowboySprite = "Anim/IdleDown/Idle_1_32x32.png";
    //SOUNDS

    //PARTICLES

    //FONTS

    public AssetManagement(){
        includeAssets();
    }

    public void includeAssets(){
        load(cowboySprite, Texture.class);
    }
}
