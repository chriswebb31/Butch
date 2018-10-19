package com.butch.game.gamemanagers;

import com.badlogic.gdx.graphics.Texture;

public class AssetManagement extends com.badlogic.gdx.assets.AssetManager {
    //TEXTURES
    public final String sampleTexture = "badlogic.jpg";

    //SOUNDS

    //PARTICLES

    //FONTS

    public AssetManagement(){
        includeAssets();
    }

    public void includeAssets(){
        load(sampleTexture, Texture.class);
    }
}
