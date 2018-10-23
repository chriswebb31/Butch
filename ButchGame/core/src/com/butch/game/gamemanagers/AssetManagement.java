package com.butch.game.gamemanagers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Music;

public class AssetManagement extends com.badlogic.gdx.assets.AssetManager {
    //TEXTURES
    public final String cowboySprite = "Anim/IdleDown/Idle_1_32x32.png";
    public final String gunSprite = "weapon.png";
    public final String bulletSprite = "bullet.png";
    //SOUNDS
    public final String mainTheme = "music/TitleScreen.mp3";
    public final String townTheme = "music/Town1.mp3";
    //PARTICLES

    //FONTS

    public AssetManagement(){
        includeAssets();
    }

    public void includeAssets(){
        load(cowboySprite, Texture.class);
        load(gunSprite, Texture.class);
        load(mainTheme, Music.class);
        load(townTheme, Music.class);
    }
}
