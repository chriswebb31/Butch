package com.butch.game.gamemanagers;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class AssetManagement extends com.badlogic.gdx.assets.AssetManager {
    //TEXTURES
    public final String cowboySprite = "Anim/IdleDown/Idle_1_32x32.png";
    public final String gunSprite = "weapon.png";
    public final String bulletSprite = "bullet.png";
    public final String coltSprite = "colt.png";
    public final String machineGunSprite = "machineGun.png";
    //SOUNDS
    public final String townTheme = "Music/Town1.mp3";
    public final String mainTheme = "Music/TitleScreen.mp3";
    public final String gunShot = "SoundFX/gunShot1.mp3";
    //PARTICLES

    //FONTS

    //TileMapData
    public final String tilemap1 = "TiledFiles/desertdoodles.tmx";
    public final String tilemap1Tilseset = "TiledFiles/desertdoodles.png";
    public final String enemySprite = "enemy.png";

    public AssetManagement() {

        includeAssets();
    }

    public void includeAssets() {
        load(cowboySprite, Texture.class);
        load(gunSprite, Texture.class);
        load(coltSprite, Texture.class);
        load(machineGunSprite, Texture.class);
        load(bulletSprite, Texture.class);
        load(tilemap1Tilseset, Texture.class);
        load(enemySprite, Texture.class);
        load(mainTheme, Music.class);
        load(townTheme, Music.class);
        load(gunShot, Sound.class);
    }
}