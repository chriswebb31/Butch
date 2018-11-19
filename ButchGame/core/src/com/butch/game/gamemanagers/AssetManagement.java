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
    public final String rifleSprite = "Rifle.png";
    public final String backgroundTexture = "saloonDoors.jpg";
    public final String playButtonActiveSprite = "Buttons/playButtonActive.png";
    public final String playButtonInactiveSprite = "Buttons/playButtonInactive.png";
    public final String aboutButtonActiveSprite = "Buttons/aboutButtonActive.png";
    public final String aboutButtonInactiveSprite = "Buttons/aboutButtonInactive.png";
    public final String needHelpButtonActiveSprite = "Buttons/helpButtonActive.png";
    public final String needHelpButtonInactiveSprite = "Buttons/helpButtonInactive.png";
    public final String settingsButtonActiveSprite = "Buttons/settingsButton.png";
    public final String exitButtonActive = "Buttons/exitButtonActive.png";
    public final String exitButtonInactive = "Buttons/exitButtonInactive.png";
    public final String barrelSprite = "Barrel.png";
    //SOUNDS
    public final String gunShot = "SoundFX/gunShot1.mp3";
    public final String menuClick = "SoundFX/clickingSound.mp3";
    public final String hitEffect = "SoundFX/hit.mp3";
    public final String revolverReloadEeffect = "SoundFX/revolverReload.mp3";
    public final String otherReloadEffect = "SoundFX/OtherReload.mp3";
    public final String shellBounceEffect = "SoundFX/shellBounce.mp3";
    public final String playSound = "SoundFX/playSound.mp3";

    //MUSIC
    public final String townTheme = "Music/Town1.mp3";
    public final String mainTheme = "Music/TitleScreen.mp3";

    //PARTICLES

    //FONTS

    //TileMapData
    public final String tilemap1 = "TiledFiles/desertdoodles.tmx";
    public final String tilemap1Tilseset = "TiledFiles/desertdoodles.png";
    public final String enemySprite = "enemy.png";

    public AssetManagement() {

        includeAssets();
    }
     public void includeMainMenuScreenAssets(){
         load(backgroundTexture, Texture.class);
         load(playButtonActiveSprite, Texture.class);
         load(playButtonInactiveSprite, Texture.class);
         load(aboutButtonActiveSprite, Texture.class);
         load(aboutButtonInactiveSprite, Texture.class);
         load(needHelpButtonActiveSprite, Texture.class);
         load(needHelpButtonInactiveSprite, Texture.class);
         load(settingsButtonActiveSprite, Texture.class);
         load(playSound, Music.class);
     }
    public void includeAssets() {
        load(cowboySprite, Texture.class);
        load(gunSprite, Texture.class);
        load(coltSprite, Texture.class);
        load(machineGunSprite, Texture.class);
        load(rifleSprite, Texture.class);
        load(bulletSprite, Texture.class);
        load(tilemap1Tilseset, Texture.class);
        load(enemySprite, Texture.class);
        load(exitButtonActive,Texture.class);
        load(exitButtonInactive,Texture.class);
        load(barrelSprite, Texture.class);
        load(mainTheme, Music.class);
        load(townTheme, Music.class);
        load(gunShot, Sound.class);
        load(menuClick, Sound.class);
        load(hitEffect, Sound.class);
        load(revolverReloadEeffect, Sound.class);
        load(otherReloadEffect, Sound.class);
        load(shellBounceEffect, Sound.class);

    }
}