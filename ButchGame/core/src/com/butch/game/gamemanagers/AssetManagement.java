package com.butch.game.gamemanagers;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Gdx2DPixmap;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetManagement extends com.badlogic.gdx.assets.AssetManager {
    //TEXTURES
    public final String gunSprite = "weapon.png"; //Placeholder Gun
    public final String bulletSprite = "bullet.png"; //Standard Bullet
    //Idle Gun Textures
    public final String coltSprite = "Anim/Guns/revolverIdle.png";
    public final String machineGunSprite = "Anim/Guns/machinegunIdle.png";
    public final String rifleSprite = "Rifle.png";
    public final String shotgunSprite = "Anim/Guns/shotgunIdle.png";
    public final String musketSprite = "Anim/Guns/musketIdle.png";
    //Gun Silhouettes
    public final String machineGunSilhoutte = "Anim/Guns/machineGunSilhouette.png";
    public final String musketSilhoutte = "Anim/Guns/musketSilhouette.png";
    public final String revolverSilhouette = "Anim/Guns/revolverSilhouette.png";
    public final String shotgunSilhouette = "Anim/Guns/shotgunSilhouette.png";

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
    public final String whiskyBottle = "whisky.png";

    //TEXTUREATLAS
    //MAIN MENU
    public final String doorsMain = "Anim/doorAtlas/mainDoors.atlas";
    //ANIMALS
    public final String chickenIdle = "Anim/Animals/chickenIdle.atlas";
    public final String cowIdle = "Anim/Animals/cowIdle.atlas";
    public final String goatIdle = "Anim/Animals/goatIdle.atlas";
    public final String horseWalking = "Anim/Animals/horseWalking.atlas";
    public final String pigIdle = "Anim/Animals/pigIdle.atlas";
    public final String racoonIdle = "Anim/Animals/racoonIdle";
    //BUTCH
    public final String butchIdleAnim = "Anim/Butch/butchIdle.atlas";
    public final String butchDying = "Anim/Butch/butchDying.atlas";
    public final String butchWalking = "Anim/Butch/butchWalking.atlas";
    public final String butchGhost = "Anim/Butch/butchGhost.atlas";
    public final String butchDoorTransition = "Anim/Butch/butchDoorTransition.atlas";
    public final String butchHandsWalking = "Anim/Butch/handsWalking.atlas";
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
    //NPC
    public final String npc1Idle = "Anim/NPC/npc1Idle.atlas";
    public final String npc2Idle = "Anim/NPC/npc2Idle.atlas";
    public final String npc3Idle = "Anim/NPC/npc3Idle.atlas";
    public final String npc4Idle = "Anim/NPC/npc4Idle.atlas";
    public final String npc5Idle = "Anim/NPC/npc5Idle.atlas";
    public final String npc6Walking = "Anim/NPC/npc6Walking.atlas";
    public final String npc7Walking = "Anim/NPC/npc7Walking.atlas";
    public final String npc8Walking = "Anim/NPC/npc8Walking.atlas";
    public final String npc9Idle = "Anim/NPC/npc9Idle.atlas";
    public final String npc10Idle = "Anim/NPC/npc10Idle.atlas";
    public final String npc11Idle = "Anim/NPC/npc11Idle.atlas";
    public final String npc12Idle = "Anim/NPC/npc12Idle.atlas";
    public final String npc13Idle = "Anim/NPC/npc13Idle.atlas";

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
    public final String route1 = "TiledFiles/Route1.tmx";
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
        load(gunSprite, Texture.class);
        load(coltSprite, Texture.class);

        load(rifleSprite, Texture.class);
        load(bulletSprite, Texture.class);

        load(tilemap1Tilseset, Texture.class);
        load(enemySprite, Texture.class);
        load(exitButtonActive,Texture.class);
        load(exitButtonInactive,Texture.class);
        load(barrelSprite, Texture.class);
        load(pistolAmmo, Texture.class);
        load(rifleAmmo, Texture.class);
        load(shotgunAmmo, Texture.class);
        load(coinItemSprite, Texture.class);

        load(healthPotion, Texture.class);
        load(butchIdleAnim, TextureAtlas.class);//FrameDuration = 0.25f
        load(butchDying, TextureAtlas.class);//FrameDuration = 0.083f

        load(shellSprite, Texture.class);
        load(pickupSprite, Texture.class);

        //CURSOR
        load(cursor, Pixmap.class);

        //BUTCH ATLAS
        load(butchDying, TextureAtlas.class);//FrameDuration = 0.083f
        load(butchIdleAnim, TextureAtlas.class);//FrameDuration = 0.25f
        load(butchDoorTransition, TextureAtlas.class);//FrameDuration = 0.083f
        load(butchWalking, TextureAtlas.class);//FrameDuration = 0.083f
        load(butchHandsWalking, TextureAtlas.class);//FrameDuration = 0.083f

        //ENEMY ATLAS
        load(enemy1Idle, TextureAtlas.class);
        load(enemy1Walking, TextureAtlas.class);
        load(enemy2Idle, TextureAtlas.class);
        load(enemy2Walking, TextureAtlas.class);
        load(enemy3Idle, TextureAtlas.class);
        load(enemy3Walking, TextureAtlas.class);
        load(enemy4Idle, TextureAtlas.class);
        load(enemy4Walking, TextureAtlas.class);

        //NPC ATLAS
        load(npc1Idle, TextureAtlas.class);//FrameDuration = 0.25f
        load(npc2Idle, TextureAtlas.class);//FrameDuration = 0.25f
        load(npc3Idle, TextureAtlas.class);//FrameDuration = 0.25f
        load(npc4Idle, TextureAtlas.class);//FrameDuration = 0.25f
        load(npc5Idle, TextureAtlas.class);//FrameDuration = 0.25f
        load(npc6Walking, TextureAtlas.class);//FrameDuration = 0.083f
        load(npc7Walking, TextureAtlas.class);//FrameDuration = 0.083f
        load(npc8Walking, TextureAtlas.class);//FrameDuration = 0.083f
        load(npc9Idle, TextureAtlas.class);//FrameDuration = 0.25f
        load(npc10Idle, TextureAtlas.class);//FrameDuration = 0.25f
        load(npc11Idle, TextureAtlas.class);//FrameDuration = 0.25f
        load(npc12Idle, TextureAtlas.class);//FrameDuration = 0.25f
        load(npc13Idle, TextureAtlas.class);//FrameDuration = 0.25f

        //GUN ATLAS
        //Machine Gun
        load(machineGunFiring, TextureAtlas.class);//FrameDuration = 0.083f
        load(machineGunReload, TextureAtlas.class);//FrameDuration = 0.083f
        load(machineGunWalking, TextureAtlas.class);//FrameDuration = 0.083f
        load(machineGunSprite, Texture.class);
        load(machineGunSilhoutte, Texture.class);
        //Colt
        load(revolverReload, TextureAtlas.class);//FrameDuration = 0.083f
        load(revolverFiring, TextureAtlas.class);//FrameDuration = 0.083f
        load(revolverWalking, TextureAtlas.class);//FrameDuration = 0.083f
        load(coltSprite, Texture.class);
        load(revolverSilhouette, Texture.class);
        //Shotgun
        load(shotgunFiring, TextureAtlas.class);//FrameDuration = 0.083f
        load(shotgunReload, TextureAtlas.class);//FrameDuration = 0.083f
        load(shotgunWalking, TextureAtlas.class);//FrameDuration = 0.083f
        load(shotgunSprite, Texture.class);
        load(shotgunSilhouette, Texture.class);
        //Musket
        load(musketFiring, TextureAtlas.class);//FrameDuration = 0.083f
        load(musketReload, TextureAtlas.class);//FrameDuration = 0.083f
        load(musketWalking, TextureAtlas.class);//FrameDuration = 0.083f
        load(musketSprite, Texture.class);
        load(musketSilhoutte, Texture.class);

        //SOUNDS
        //Background Music
        load(mainTheme, Music.class);
        load(townTheme, Music.class);
        //SFX
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