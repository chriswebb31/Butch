package com.butch.game.gamemanagers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Music;

public class AssetManagement extends com.badlogic.gdx.assets.AssetManager {
    //TEXTURES
    public final String cowboySprite = "Anim/IdleDown/Idle_1_32x32.png";
    //SOUNDS
    public final Music mainTheme = Gdx.audio.newMusic(Gdx.files.internal("music/Town1.mp3"));
    //PARTICLES

    //FONTS

    public AssetManagement(){
        includeAssets();
    }

    public void includeAssets(){
        load(cowboySprite, Texture.class);
    }
}
