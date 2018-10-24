package com.butch.game.gamemanagers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class AssetManagement extends com.badlogic.gdx.assets.AssetManager {
    //TEXTURES
    public final String cowboySprite = "Anim/IdleDown/Idle_1_32x32.png";
    public final String gunSprite = "weapon.png";
    public final String bulletSprite = "bullet.png";
    //SOUNDS

//    public final Music mainTheme = Gdx.audio.newMusic(Gdx.files.internal("music/Town1.mp3")); just need the location in a string, the asset manager will create instance later
    public final String townTheme = "Music/Town1.mp3";
    public final String mainTheme = "Music/TitleScreen.mp3";
    //PARTICLES

    //FONTS

    //TileMapData
    public final String tilemap1 = "TiledFiles/desertdoodles.tmx";
    public final String tilemap1Tilseset = "TiledFiles/desertdoodles.png";

    public AssetManagement(){

        includeAssets();
    }

    public void includeAssets(){
        load(cowboySprite, Texture.class);
        load(gunSprite, Texture.class);
        load(bulletSprite, Texture.class);
        load(tilemap1Tilseset, Texture.class);
        load(mainTheme, Music.class);
        load(townTheme, Music.class);

    }
}
