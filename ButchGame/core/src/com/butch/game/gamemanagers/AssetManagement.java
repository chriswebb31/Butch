package com.butch.game.gamemanagers;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetManagement extends com.badlogic.gdx.assets.AssetManager {
    //TEXTURES
    public final String cowboySprite = "Anim/IdleDown/Idle_1_32x32.png";
    public final String gunSprite = "weapon.png";
    public final String bulletSprite = "bullet.png";
    public final String coltSprite = "Anim/Guns/revolverIdle.png";
    public final String machineGunSprite = "Anim/Guns/machinegunIdle.png";
    public final String rifleSprite = "Rifle.png";
    public final String shotgunSprite = "Anim/Guns/shotgunIdle.png";
    public final String musketSprite = "Anim/Guns/musketIdle.png";
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
    public final String sliderBack = "Buttons/sliderBack.png";
    public final String sliderKnob = "Buttons/sliderKnob.png";
    public final String pistolAmmo = "pistolAmmo.png";
    public final String rifleAmmo = "rifleAmmo.png";
    public final String shotgunAmmo = "shotgunAmmo.png";
    public final String coinItemSprite = "coin.png";
    public final String shellSprite = "shell.png";
    public final String pickupSprite = "pickupIcon.png";
    public final String cursor = "cursor.png";
    public final String healthPotion = "healthPotion.png";

    //TEXTUREATLAS
    //BUTCH
    public final String butchIdleAnim = "Anim/Butch/butchIdle.atlas";
    public final String butchDying = "Anim/Butch/butchDying.atlas";
    public final String butchWalking = "Anim/Butch/butchWalking.atlas";
    public final String butchGhost = "Anim/Butch/butchGhost.atlas";
    //ENEMIES
    public final String enemy1Idle = "Anim/Enemies/enemy1Idle.atlas";
    public final String enemy1Walking = "Anim/Enemies/enemy1Walking.atlas";
    public final String enemy2Idle = "Anim/Enemies/enemy2Idle.atlas";
    public final String enemy2Walking = "Anim/Enemies/enemy2Walking.atlas";
    public final String enemy3Idle = "Anim/Enemies/enemy3Idle.atlas";
    public final String enemy3Walking = "Anim/Enemies/enemy3Walking.atlas";
    public final String enemy4Idle = "Anim/Enemies/enemy4Idle.atlas";
    public final String enemy4Walking = "Anim/Enemies/enemy4Walking.atlas";
    //GUNS
    public final String machineGunFiring = "Anim/Guns/machineGunFiring.atlas";
    public final String machineGunReload = "Anim/Guns/machineGunReload.atlas";
    public final String machineGunWalking = "Anim/Guns/machineGunWalking.atlas";
    public final String musketFiring = "Anim/Guns/musketFiring.atlas";
    public final String musketReload = "Anim/Guns/musketReload.atlas";
    public final String musketWalking = "Anim/Guns/musketWalking.atlas";
    public final String revolverFiring = "Anim/Guns/revolverFiring.atlas";
    public final String revolverReload = "Anim/Guns/revolverReload.atlas";
    public final String revolverWalking = "Anim/Guns/revolverWalking.atlas";
    public final String shotgunFiring = "Anim/Guns/shotgunFiring.atlas";
    public final String shotgunReload = "Anim/Guns/shotgunReload.atlas";
    public final String shotgunWalking = "Anim/Guns/shotgunWalking.atlas";
    //HORSE
    public final String horseWalking = "Anim/Horse/horseWalking.atlas";
    //NPC
    public final String npc2Idle = "Anim/NPC/npc2Idle.atlas";
    public final String npc3Idle = "Anim/NPC/npc3Idle.atlas";
    public final String npc6Walking = "Anim/NPC/npc6Walking.atlas";
    public final String npc7Walking = "Anim/NPC/npc7Walking.atlas";
    public final String npc8Walking = "Anim/NPC/npc8Walking.atlas";

    //SOUNDS
    public final String gunShot = "SoundFX/gunShot1.mp3";
    public final String menuClick = "SoundFX/clickingSound.mp3";

    public final String otherReloadEffect = "SoundFX/OtherReload.mp3";
    public final String hitEffect = "SoundFX/hit.mp3";
    public final String revolverReloadEeffect = "SoundFX/revolverReload.mp3";

    public final String shellBounceEffect = "SoundFX/shellBounce.mp3";

    public final String playSound = "SoundFX/playSound.mp3";
    public final String coinCollection = "SoundFX/coinCollect.mp3";
    public final String walkingFX = "SoundFX/walking.mp3";
    public final String ammoCollection = "SoundFX/ammoCollect.mp3";
    public final String potionCollection = "SoundFX/potionFX.mp3";
    //MUSIC
    public final String townTheme = "Music/Town1.mp3";
    public final String mainTheme = "Music/TitleScreen.mp3";

    //PARTICLES

    //FONTS

    //TileMapData
    public final String tilemap1 = "TiledFiles/REALTOWNMAPCLEAN.tmx";
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
        load(sliderKnob, Texture.class);
        load(sliderBack, Texture.class);
        load(cowboySprite, Texture.class);
        load(gunSprite, Texture.class);
        load(coltSprite, Texture.class);
        load(machineGunSprite, Texture.class);
        load(rifleSprite, Texture.class);
        load(bulletSprite, Texture.class);
        load(musketSprite, Texture.class);
        load(tilemap1Tilseset, Texture.class);
        load(enemySprite, Texture.class);
        load(exitButtonActive,Texture.class);
        load(exitButtonInactive,Texture.class);
        load(barrelSprite, Texture.class);
        load(pistolAmmo, Texture.class);
        load(rifleAmmo, Texture.class);
        load(shotgunAmmo, Texture.class);
        load(coinItemSprite, Texture.class);
        load(shotgunSprite, Texture.class);
        load(healthPotion, Texture.class);
        load(butchIdleAnim, TextureAtlas.class);
        load(butchDying, TextureAtlas.class);

        load(shellSprite, Texture.class);
        load(pickupSprite, Texture.class);
        load(cursor, Texture.class);

        load(mainTheme, Music.class);
        load(townTheme, Music.class);
        load(gunShot, Sound.class);
        load(menuClick, Sound.class);
        load(hitEffect, Sound.class);
        load(revolverReloadEeffect, Sound.class);
        load(otherReloadEffect, Sound.class);
        load(shellBounceEffect, Sound.class);
        load(coinCollection, Sound.class);
        load(ammoCollection, Sound.class);
        load(walkingFX, Sound.class);
        load(potionCollection, Sound.class);
    }
}