package com.butch.game.gamemanagers;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import javax.xml.soap.Text;

public class AssetManagement extends com.badlogic.gdx.assets.AssetManager {
    /*
        CLASS : ASSETMANAGEMENT

        used to load in relevant external assets for other classes, including images, sounds
     */
    //TEXTURES
    public final String levelUpSprite = "core/assets/DButt.jpg"; //Placeholder LevelUpItem
    public final String gunSprite = "core/assets/weapon.png"; //Placeholder Gun
    public final String bulletSprite = "core/assets/bullet.png"; //Standard Bullet
    //Idle Gun Textures
    public final String coltSprite = "core/assets/Anim/Guns/revolverIdle.png";
    public final String machineGunSprite = "core/assets/Anim/Guns/machinegunIdle.png";
    public final String rifleSprite = "core/assets/Rifle.png";
    public final String shotgunSprite = "core/assets/Anim/Guns/shotgunIdle.png";
    public final String musketSprite = "core/assets/Anim/Guns/musketIdle.png";
    //Gun Silhouettes
    public final String machineGunSilhoutte = "core/assets/Anim/Guns/machineGunSilhouette.png";
    public final String musketSilhoutte = "core/assets/Anim/Guns/musketSilhouette.png";
    public final String revolverSilhouette = "core/assets/Anim/Guns/revolverSilhouette.png";
    public final String shotgunSilhouette = "core/assets/Anim/Guns/shotgunSilhouette.png";

    public final String backgroundTexture = "core/assets/Anim/doorAtlas/mainDoors-0.png";
    public final String playButtonActiveSprite = "core/assets/Buttons/playButtonActive.png";
    public final String playButtonInactiveSprite = "core/assets/Buttons/playButtonInactive.png";
    public final String aboutButtonActiveSprite = "core/assets/Buttons/aboutButtonActive.png";
    public final String aboutButtonInactiveSprite = "core/assets/Buttons/aboutButtonInactive.png";
    public final String needHelpButtonActiveSprite = "core/assets/Buttons/helpButtonActive.png";
    public final String needHelpButtonInactiveSprite = "core/assets/Buttons/helpButtonInactive.png";
    public final String settingsButtonActiveSprite = "core/assets/Buttons/settingsButton.png";
    public final String exitButtonActive = "core/assets/Buttons/exitButtonActive.png";
    public final String exitButtonInactive = "core/assets/Buttons/exitButtonInactive.png";
    public final String homeButtonInactive = "core/assets/Buttons/homeButtonInactive.png";
    public final String homeButtonActive = "core/assets/Buttons/homeButtonActive.png";
    public final String continueButtonActive = "core/assets/Buttons/continueActive.png";
    public final String continueButtonInactive = "core/assets/Buttons/continueInactive.png";
    public final String barrelSprite = "core/assets/Barrel.png";
    public final String sliderBack = "core/assets/Buttons/sliderBack.png";
    public final String sliderKnob = "core/assets/Buttons/sliderKnob.png";
    public final String pistolAmmo = "core/assets/pistolAmmo.png";
    public final String rifleAmmo = "core/assets/rifleAmmo.png";
    public final String shotgunAmmoIdle = "core/assets/shotgunAmmo.png";
    public final String coinItemSprite = "core/assets/coin.png";
    public final String shellSprite = "core/assets/shell.png";
    public final String bloodSprite = "core/assets/blood.png";
    public final String pickupSprite = "core/assets/pickupIcon.png";
    public final String cursor = "core/assets/cursor.png";
    public final String healthPotion = "core/assets/healthPotion.png";
    public final String whiskyBottle = "core/assets/whisky.png";
    public final String aboutPage = "core/assets/Pages/aboutPage.png";
    public final String helpPage = "core/assets/Pages/needHelpPage.png";
    //Bullets
    public final String friendlyBullet = "core/assets/friendlyBullet.png";
    public final String enemyBullet = "core/assets/enemyBullet.png";
    //HUD
    //Character Screen
    public final String characterScreen = "core/assets/HUD/characterScreen.png";
    //Skin
    public final String uiskin = "core/assets/Data/uiskin.json";
    //Health Bars
    public final String enemyHBBG = "core/assets/HUD/bars/enemyHealthBarBG.png";
    public final String enemyHBFG = "core/assets/HUD/bars/enemyHealthBarFG.png";
    public final String playerHBBG = "core/assets/HUD/bars/playerHealthBarBG.png";
    public final String playerHBFG = "core/assets/HUD/bars/playerHealthBarFG.png";
    public final String loadingBarBack = "core/assets/HUD/bars/loadingBarBack.png";
    //Revolver Ammo Bar
    public final String revolverAmmoBar6 = "core/assets/HUD/RevolverAmmo/revolverAmmoBar-0.png";
    public final String revolverAmmoBar5 = "core/assets/HUD/RevolverAmmo/revolverAmmoBar-1.png";
    public final String revolverAmmoBar4 = "core/assets/HUD/RevolverAmmo/revolverAmmoBar-2.png";
    public final String revolverAmmoBar3 = "core/assets/HUD/RevolverAmmo/revolverAmmoBar-3.png";
    public final String revolverAmmoBar2 = "core/assets/HUD/RevolverAmmo/revolverAmmoBar-4.png";
    public final String revolverAmmoBar1 = "core/assets/HUD/RevolverAmmo/revolverAmmoBar-5.png";
    public final String revolverAmmoBar0 = "core/assets/HUD/RevolverAmmo/revolverAmmoBar-6.png";
    //Machine Gun Ammo Bar
    public final String machineGunAmmoBar24 = "core/assets/HUD/machineGunAmmoBar/machineGunAmmoBar_00.png";
    public final String machineGunAmmoBar23 = "core/assets/HUD/machineGunAmmoBar/machineGunAmmoBar_01.png";
    public final String machineGunAmmoBar22 = "core/assets/HUD/machineGunAmmoBar/machineGunAmmoBar_02.png";
    public final String machineGunAmmoBar21 = "core/assets/HUD/machineGunAmmoBar/machineGunAmmoBar_03.png";
    public final String machineGunAmmoBar20 = "core/assets/HUD/machineGunAmmoBar/machineGunAmmoBar_04.png";
    public final String machineGunAmmoBar19 = "core/assets/HUD/machineGunAmmoBar/machineGunAmmoBar_05.png";
    public final String machineGunAmmoBar18 = "core/assets/HUD/machineGunAmmoBar/machineGunAmmoBar_06.png";
    public final String machineGunAmmoBar17 = "core/assets/HUD/machineGunAmmoBar/machineGunAmmoBar_07.png";
    public final String machineGunAmmoBar16 = "core/assets/HUD/machineGunAmmoBar/machineGunAmmoBar_08.png";
    public final String machineGunAmmoBar15 = "core/assets/HUD/machineGunAmmoBar/machineGunAmmoBar_09.png";
    public final String machineGunAmmoBar14 = "core/assets/HUD/machineGunAmmoBar/machineGunAmmoBar_10.png";
    public final String machineGunAmmoBar13 = "core/assets/HUD/machineGunAmmoBar/machineGunAmmoBar_11.png";
    public final String machineGunAmmoBar12 = "core/assets/HUD/machineGunAmmoBar/machineGunAmmoBar_12.png";
    public final String machineGunAmmoBar11 = "core/assets/HUD/machineGunAmmoBar/machineGunAmmoBar_13.png";
    public final String machineGunAmmoBar10 = "core/assets/HUD/machineGunAmmoBar/machineGunAmmoBar_14.png";
    public final String machineGunAmmoBar9 = "core/assets/HUD/machineGunAmmoBar/machineGunAmmoBar_15.png";
    public final String machineGunAmmoBar8 = "core/assets/HUD/machineGunAmmoBar/machineGunAmmoBar_16.png";
    public final String machineGunAmmoBar7 = "core/assets/HUD/machineGunAmmoBar/machineGunAmmoBar_17.png";
    public final String machineGunAmmoBar6 = "core/assets/HUD/machineGunAmmoBar/machineGunAmmoBar_18.png";
    public final String machineGunAmmoBar5 = "core/assets/HUD/machineGunAmmoBar/machineGunAmmoBar_19.png";
    public final String machineGunAmmoBar4 = "core/assets/HUD/machineGunAmmoBar/machineGunAmmoBar_20.png";
    public final String machineGunAmmoBar3 = "core/assets/HUD/machineGunAmmoBar/machineGunAmmoBar_21.png";
    public final String machineGunAmmoBar2 = "core/assets/HUD/machineGunAmmoBar/machineGunAmmoBar_22.png";
    public final String machineGunAmmoBar1 = "core/assets/HUD/machineGunAmmoBar/machineGunAmmoBar_23.png";
    public final String machineGunAmmoBar0 = "core/assets/HUD/machineGunAmmoBar/machineGunAmmoBar_24.png";
    //Shotgun Ammo Bar
    public final String shotgunAmmoBar2 = "core/assets/HUD/shotgunAmmoBar/shotgunAmmoBar-0.png";
    public final String shotgunAmmoBar1 = "core/assets/HUD/shotgunAmmoBar/shotgunAmmoBar-1.png";
    public final String shotgunAmmoBar0 = "core/assets/HUD/shotgunAmmoBar/shotgunAmmoBar-2.png";
    //Musket Ammo Bar
    public final String musketAmmoBar1 = "core/assets/HUD/musketAmmoBar/musketAmmoBar_0.png";
    public final String musketAmmoBar0 = "core/assets/HUD/musketAmmoBar/musketAmmoBar_1.png";
    //Melee Ammo Bar
    public final String meleeAmmoBar = "core/assets/HUD/meleeAmmoBar.png";
    //Coin Counter
    public final String coinCounter0 = "core/assets/HUD/CoinCount/coinCount-9.png";
    public final String coinCounter1 = "core/assets/HUD/CoinCount/coinCount-0.png";
    public final String coinCounter2 = "core/assets/HUD/CoinCount/coinCount-1.png";
    public final String coinCounter3 = "core/assets/HUD/CoinCount/coinCount-2.png";
    public final String coinCounter4 = "core/assets/HUD/CoinCount/coinCount-3.png";
    public final String coinCounter5 = "core/assets/HUD/CoinCount/coinCount-4.png";
    public final String coinCounter6 = "core/assets/HUD/CoinCount/coinCount-5.png";
    public final String coinCounter7 = "core/assets/HUD/CoinCount/coinCount-6.png";
    public final String coinCounter8 = "core/assets/HUD/CoinCount/coinCount-7.png";
    public final String coinCounter9 = "core/assets/HUD/CoinCount/coinCount-8.png";
    //HamburgerOptionButton
    public final String hamburger = "core/assets/HUD/hamburger.png";
    //HUD Gun Labels
    public final String machineGunLabel = "core/assets/HUD/machineGunLabel.png";
    public final String musketLabel = "core/assets/HUD/musketLabel.png";
    public final String shotgunLabel = "core/assets/HUD/shotgunLabel.png";
    public final String revolverLabel = "core/assets/HUD/revolverLabel.png";

    //dialogue stuff
    public final String dialougueBox= "core/assets/dialogueBox.png";

    //TEXTUREATLAS
    //MAIN MENU
    public final String doorsMain = "core/assets/Anim/doorAtlas/mainDoors.atlas";
    //ANIMALS
    public final String chickenIdle = "core/assets/Anim/Animals/chickenIdle.atlas";
    public final String cowIdle = "core/assets/Anim/Animals/cowIdle.atlas";
    public final String goatIdle = "core/assets/Anim/Animals/goatIdle.atlas";
    public final String horseWalking = "core/assets/Anim/Animals/horseWalking.atlas";
    public final String pigIdle = "core/assets/Anim/Animals/pigIdle.atlas";
    public final String racoonIdle = "core/assets/Anim/Animals/racoonIdle.atlas";
    public final String turkeyIdle = "core/assets/Anim/Animals/turkeyIdle.atlas";
    //BUTCH
    public final String butchIdleAnim = "core/assets/Anim/Butch/butchIdle.atlas";
    public final String butchDying = "core/assets/Anim/Butch/butchDying.atlas";
    public final String butchWalking = "core/assets/Anim/Butch/butchWalking.atlas";
    public final String butchGhost = "core/assets/Anim/Butch/butchGhost.atlas";
    public final String butchDoorTransition = "core/assets/Anim/Butch/butchDoorTransition.atlas";
    public final String butchHandsWalking = "core/assets/Anim/Butch/handsWalking.atlas";
    public final String butchHorseRiding = "core/assets/Anim/Butch/butchHorseRiding.atlas";
    public final String butchHorseIdle = "core/assets/Anim/Butch/butchHorseIdle.atlas";
    public final String butchHandsIdle = "core/assets/Anim/Butch/butchHandsIdle.png";
    public final String butchIdleCutscene = "core/assets/Anim/Butch/butchIdleCutscene.atlas";
    //ENEMIES
    public final String enemy1Idle = "core/assets/Anim/Enemies/enemy1Idle.atlas";
    public final String enemy1Walking = "core/assets/Anim/Enemies/enemy1Walking.atlas";
    public final String enemy2Idle = "core/assets/Anim/Enemies/enemy2Idle.atlas";
    public final String enemy2Walking = "core/assets/Anim/Enemies/enemy2Walking.atlas";
    public final String enemy3Idle = "core/assets/Anim/Enemies/enemy3Idle.atlas";
    public final String enemy3Walking = "core/assets/Anim/Enemies/enemy3Walking.atlas";
    public final String enemy4Idle = "core/assets/Anim/Enemies/enemy4Idle.atlas";
    public final String enemy4Walking = "core/assets/Anim/Enemies/enemy4Walking.atlas";
    //GUNS
    public final String machineGunFiring = "core/assets/Anim/Guns/machineGunFiring.atlas";
    public final String machineGunReload = "core/assets/Anim/Guns/machineGunReload.atlas";
    public final String machineGunWalking = "core/assets/Anim/Guns/machineGunWalking.atlas";
    public final String machineGunPickup = "core/assets/Anim/Guns/machineGunPickup.atlas";
    public final String musketFiring = "core/assets/Anim/Guns/musketFiring.atlas";
    public final String musketReload = "core/assets/Anim/Guns/musketReload.atlas";
    public final String musketWalking = "core/assets/Anim/Guns/musketWalking.atlas";
    public final String musketPickup = "core/assets/Anim/Guns/musketPickup.atlas";
    public final String revolverFiring = "core/assets/Anim/Guns/revolverFiring.atlas";
    public final String revolverReload = "core/assets/Anim/Guns/revolverReload.atlas";
    public final String revolverWalking = "core/assets/Anim/Guns/revolverWalking.atlas";
    public final String revolverPickup = "core/assets/Anim/Guns/revolverPickup.atlas";
    public final String shotgunFiring = "core/assets/Anim/Guns/shotgunFiring.atlas";
    public final String shotgunReload = "core/assets/Anim/Guns/shotgunReload.atlas";
    public final String shotgunWalking = "core/assets/Anim/Guns/shotgunWalking.atlas";
    public final String shotgunPickup = "core/assets/Anim/Guns/shotgunPickup.atlas";
    public final String meleeIdle = "core/assets/Anim/Guns/meleeIdle.atlas";
    public final String meleeWalking = "core/assets/Anim/Guns/meleeWalking.atlas";
    public final String meleeFiring = "core/assets/Anim/Guns/meleeFiring.atlas";
    public final String meleeSilhoutte = "core/assets/Anim/Guns/meleeSilhouette.png";
    //NPC
    public final String npc1Idle = "core/assets/Anim/NPC/npc1Idle.atlas";
    public final String npc2Idle = "core/assets/Anim/NPC/npc2Idle.atlas";
    public final String npc3Idle = "core/assets/Anim/NPC/npc3Idle.atlas";
    public final String npc4Idle = "core/assets/Anim/NPC/npc4Idle.atlas";
    public final String npc5Idle = "core/assets/Anim/NPC/npc5Idle.atlas";
    public final String npc6Walking = "core/assets/Anim/NPC/npc6Walking.atlas";
    public final String npc7Walking = "core/assets/Anim/NPC/npc7Walking.atlas";
    public final String npc8Walking = "core/assets/Anim/NPC/npc8Walking.atlas";
    public final String npc9Idle = "core/assets/Anim/NPC/npc9Idle.atlas";
    public final String npc10Idle = "core/assets/Anim/NPC/npc10Idle.atlas";
    public final String npc11Idle = "core/assets/Anim/NPC/npc11Idle.atlas";
    public final String npc12Idle = "core/assets/Anim/NPC/npc12Idle.atlas";
    public final String npc13Idle = "core/assets/Anim/NPC/npc13Idle.atlas";

    //Prisoners
    public final String prisoner1Idle = "core/assets/Anim/Prisoners/prisoner1Idle.atlas";
    public final String prisoner2Idle = "core/assets/Anim/Prisoners/prisoner2Idle.atlas";
    public final String prisoner3Idle = "core/assets/Anim/Prisoners/prisoner3Idle.atlas";
    public final String prisoner4Idle = "core/assets/Anim/Prisoners/prisoner4Idle.atlas";
    public final String prisoner1Walking = "core/assets/Anim/Prisoners/prisoner1Walking.atlas";
    public final String prisoner2Walking = "core/assets/Anim/Prisoners/prisoner2Walking.atlas";
    public final String prisoner3Walking = "core/assets/Anim/Prisoners/prisoner3Walking.atlas";
    public final String prisoner4Walking = "core/assets/Anim/Prisoners/prisoner4Walking.atlas";

    //Sheriffs
    public final String sheriff1Idle = "core/assets/Anim/Sheriffs/sheriff1Idle.atlas";
    public final String sheriff2Idle = "core/assets/Anim/Sheriffs/sheriff1Idle.atlas";
    public final String sheriff3Idle = "core/assets/Anim/Sheriffs/sheriff1Idle.atlas";
    public final String sheriff1Walking = "core/assets/Anim/Sheriffs/sheriff1Walking.atlas";
    public final String sheriff2Walking = "core/assets/Anim/Sheriffs/sheriff2Walking.atlas";
    public final String sheriff3Walking = "core/assets/Anim/Sheriffs/sheriff3Walking.atlas";

    //Soldiers
    public final String soldier1Idle = "core/assets/Anim/Soldiers/soldier1Idle.atlas";
    public final String soldier2Idle = "core/assets/Anim/Soldiers/soldier2Idle.atlas";
    public final String soldier3Idle = "core/assets/Anim/Soldiers/soldier3Idle.atlas";
    public final String soldier4Idle = "core/assets/Anim/Soldiers/soldier4Idle.atlas";
    public final String woundedSoldier1Idle = "core/assets/Anim/Soldiers/woundedSoldier1Idle.atlas";
    public final String woundedSoldier2Idle = "core/assets/Anim/Soldiers/woundedSoldier2Idle.atlas";
    public final String deadSoldier1 = "core/assets/Anim/Soldiers/deadSoldier1.atlas";
    public final String deadSoldier2 = "core/assets/Anim/Soldiers/deadSoldier2.atlas";

    //Lincoln
    public final String lincolnIdle = "core/assets/Anim/Lincoln/lincolnIdle.atlas";
    public final String lincolnWalking = "core/assets/Anim/Lincoln/lincolnWalking.atlas";
    public final String lincolnIdleNPC = "core/assets/Anim/Lincoln/lincolnIdleNPC.atlas";
    //Coin
    public final String coinSpin = "core/assets/Anim/Coin/coin.atlas";
    //Whisky
    public final String whisky = "core/assets/Anim/Items/whisky.atlas";
    public final String whiskySpin = "core/assets/Anim/Items/whiskySpin.atlas";
    //Level Up Item
    public final String lvlItem = "core/assets/Anim/Items/lvlItem.atlas";
    //AMMO
    //Machine Gun Ammo
    public final String machineGunAmmo = "core/assets/Anim/Items/machineGunAmmo.atlas";
    //Revolver Ammo
    public final String revolverAmmo = "core/assets/Anim/Items/revolverAmmo.atlas";
    //Shotgun Ammo
    public final String shotgunAmmo = "core/assets/Anim/Items/shotgunAmmo.atlas";
    //Musket Ammo
    public final String musketAmmo = "core/assets/Anim/Items/musketAmmo.atlas";

    //Cannon
    public final String cannonFiring = "core/assets/Anim/Cannon/cannonFiring.atlas";
    public final String cannonIdle = "core/assets/Anim/Cannon/cannonIdle.png";

    public final String dialogueTrigger = "core/assets/Anim/dialogueTrigger.atlas";

    //SOUNDS
    public final String gunShot = "core/assets/SoundFX/gunShot1.mp3";
    public final String menuClick = "core/assets/SoundFX/clickingSound.mp3";

    public final String otherReloadEffect = "core/assets/SoundFX/OtherReload.mp3";
    public final String hitEffect = "core/assets/SoundFX/hit.mp3";
    public final String revolverReloadEeffect = "core/assets/SoundFX/revolverReload.mp3";

    public final String shellBounceEffect = "core/assets/SoundFX/shellBounce.mp3";

    public final String playSound = "core/assets/SoundFX/playSound.mp3";
    public final String coinCollection = "core/assets/SoundFX/coinCollect.mp3";
    public final String walkingFX = "core/assets/SoundFX/walking.mp3";
    public final String ammoCollection = "core/assets/SoundFX/ammoCollect.mp3";
    public final String potionCollection = "core/assets/SoundFX/potionFX.mp3";

    //Gun Sounds
    //public final String cannonFire = "SoundFX/GunSounds/cannonFire.mp3";
    //public final String gatlingFire = "SoundFX/GunSounds/gatlingFire.mp3";
    //public final String rifleFire = "SoundFX/GunSounds/rifleFire.mp3";
    public final String machinegunFire = "core/assets/SoundFX/GunSounds/machinegunFire.mp3";
    public final String musketFire = "core/assets/SoundFX/GunSounds/musketFire.mp3";
    public final String revolverFire = "core/assets/SoundFX/GunSounds/revolverFire.mp3";
    public final String shotgunFire = "core/assets/SoundFX/GunSounds/shotgunFire.mp3";
    public final String shotgunFire2 = "core/assets/CutScenes/Audio/shotgunFire.mp3";

    public final String machineGunReloadNoise = "core/assets/SoundFX/GunSounds/rifleReload.mp3";
    //public final String musketReloadNoise = "SoundFX/GunSounds/musketReload.mp3";
    public final String revolverReloadNoise = "core/assets/SoundFX/GunSounds/revolverReload.mp3";
    public final String shotgunReloadNoise = "core/assets/SoundFX/GunSounds/shotgunReload.mp3";

    //Animal sounds
    public final String maniMountNoise = "core/assets/SoundFX/ManiHorse/maniMount.mp3";
    public final String maniDismountNoise = "core/assets/SoundFX/ManiHorse/maniDismount.mp3";
    public final String maniMovingNoise = "core/assets/SoundFX/ManiHorse/maniMoving.mp3";
    public final String maniNeighingNoise = "core/assets/SoundFX/ManiHorse/maniNeighing.mp3";
    public final String maniSnortNoise = "core/assets/SoundFX/ManiHorse/maniSnortNoise.mp3";

    public final String chickenNoise = "core/assets/SoundFX/chickenNoise.mp3";
    public final String cowNoise = "core/assets/SoundFX/cowNoise.mp3";
    public final String goatNoise = "core/assets/SoundFX/goatNoise.mp3";
    public final String turkeyNoise = "core/assets/SoundFX/turkeyNoise.mp3";
    public final String raccoonNoise = "core/assets/SoundFX/raccoonNoise.mp3";
    public final String pigNoise = "core/assets/SoundFX/pigNoise.mp3";

    //Character sounds
    public final String consumeFoodNoise = "core/assets/SoundFX/CharacterNoises/consumeFoodNoise.mp3";
    public final String drinkItemNoise = "core/assets/SoundFX/CharacterNoises/drinkItemNoise.mp3";
    public final String oof = "core/assets/SoundFX/CharacterNoises/oof.mp3";

    //MUSIC
    public final String townTheme = "core/assets/Music/Town1.mp3";
    public final String mainTheme = "core/assets/Music/TitleScreen.mp3";
    public final String prisonTheme = "core/assets/Music/prisonMusic.mp3";
    public final String endMazeMapTheme = "core/assets/Music/mazeMap.mp3";
    public final String bigCityTheme =  "core/assets/Music/BigCity.mp3";
    public final String snowTheme =  "core/assets/Music/Snow.mp3";
    public final String caveTheme = "core/assets/Music/Cave.mp3";
    public final String warzoneTheme = "core/assets/Music/Warzone.mp3";
    public final String routeTheme = "core/assets/Music/Routes.mp3";

    //PARTICLES


    //FONTS


    //TileMapData
    public final String startTavern = "core/assets/TiledFiles/TavernInterior.tmx";
    public final String tilemap1 = "core/assets/TiledFiles/REALTOWNMAPCLEAN.tmx";
    public final String route1 = "core/assets/TiledFiles/Route1.tmx";
    public final String caveTransition = "core/assets/TiledFiles/CaveTransition.tmx";
    public final String mazeMap = "core/assets/TiledFiles/endMaze.tmx";
    public final String prison = "core/assets/TiledFiles/Prison.tmx";
    public final String cave = "core/assets/TiledFiles/Cave.tmx";
    public final String warzone = "core/assets/TiledFiles/Warzone.tmx";
    public final String route3 = "core/assets/TiledFiles/Route3.tmx";
    public final String route4 = "core/assets/TiledFiles/Route4.tmx";
    public final String snowyMountain = "core/assets/TiledFiles/SnowyPasture.tmx";
    public final String bigBoyTown = "core/assets/TiledFiles/BigCity.tmx";
    public final String houseInterior = "core/assets/TiledFiles/HouseInterior.tmx";
//    public final String tavernInteriorTwo = "TiledFiles/TavernInteriroTwo.tmx";
    public final String gunStoreInterior = "core/assets/TiledFiles/GunStoreInterior.tmx";
    public final String enemySprite = "core/assets/enemy.png";
    public final String punchSound = "core/assets/SoundFX/Punch.mp3";

    //CutScenes Assets
    public final String bubbleSpeech = "core/assets/CutScenes/speechBubble.png";
    public final String introBack = "core/assets/CutScenes/introBack.jpg";
    public final String newspaper = "core/assets/CutScenes/newspaperCutscene.png";

    public final String introBackMusic1 = "core/assets/CutScenes/Audio/introBackMusic.mp3";
    public final String saloonBackNoise1 = "core/assets/CutScenes/Audio/saloonBackNoise1.mp3";
    public final String saloonBackNoise2 = "core/assets/CutScenes/Audio/saloonBackNoise2.mp3";
    public final String prisonMusic1 = "core/assets/CutScenes/Audio/prisonMusic.mp3";

    public final String narration = "core/assets/CutScenes/narration.png";
    public final String caveBack = "core/assets/CutScenes/caveBack.png";
    public final String caveAbDialogue = "core/assets/CutScenes/caveAbDialogue.png";

    public AssetManagement() {
        includeAssets();
        includeGunAssets();
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
         load(doorsMain,TextureAtlas.class);
         load(playSound, Music.class);
         load(exitButtonActive,Texture.class);
         load(exitButtonInactive,Texture.class);
         load(continueButtonActive, Texture.class);
         load(continueButtonInactive, Texture.class);
     }

    public void includeAssets() {
        load(caveBack, Texture.class);
        load(caveAbDialogue, Texture.class);
        load(narration, Texture.class);
        load(shotgunFire2, Music.class);
        load(punchSound, Sound.class);
        //cutScenes loading
        load(prisonMusic1, Music.class );
        load(saloonBackNoise1, Music.class );
        load(saloonBackNoise2, Music.class );
        load(introBackMusic1, Music.class );
        load(bubbleSpeech, Texture.class);
        load(introBack, Texture.class);
        load(newspaper, Texture.class);
        //page Contents
        load(helpPage, Texture.class);
        load(aboutPage, Texture.class);
        load(levelUpSprite, Texture.class);
        //Animals
        load(chickenIdle, TextureAtlas.class);
        load(cowIdle, TextureAtlas.class);
        load(racoonIdle, TextureAtlas.class);
        load(pigIdle, TextureAtlas.class);
        load(goatIdle, TextureAtlas.class);
        load(turkeyIdle, TextureAtlas.class);

        load(sliderKnob, Texture.class);
        load(sliderBack, Texture.class);
        load(gunSprite, Texture.class);
        load(coltSprite, Texture.class);

        load(rifleSprite, Texture.class);
        load(bulletSprite, Texture.class);

        load(dialogueTrigger, TextureAtlas.class);

        load(enemySprite, Texture.class);
        load(homeButtonInactive,Texture.class);
        load(homeButtonActive,Texture.class);
        load(barrelSprite, Texture.class);
        load(pistolAmmo, Texture.class);
        load(rifleAmmo, Texture.class);
        load(shotgunAmmoIdle, Texture.class);
        load(coinItemSprite, Texture.class);

        //Lincoln
        load(lincolnIdle, TextureAtlas.class);
        load(lincolnWalking, TextureAtlas.class);
        load(lincolnIdleNPC, TextureAtlas.class);

        //Bullets
        load(friendlyBullet, Texture.class);
        load(enemyBullet, Texture.class);
        //
        load(butchHandsIdle, Texture.class);

        load(healthPotion, Texture.class);
        load(butchIdleAnim, TextureAtlas.class);//FrameDuration = 0.25f
        load(butchDying, TextureAtlas.class);//FrameDuration = 0.083f

        load(shellSprite, Texture.class);
        load(bloodSprite, Texture.class);
        load(pickupSprite, Texture.class);

        //CURSOR
        load(cursor, Pixmap.class);

        //BUTCH ATLAS
        load(butchDying, TextureAtlas.class);//FrameDuration = 0.083f
        load(butchIdleAnim, TextureAtlas.class);//FrameDuration = 0.25f
        load(butchDoorTransition, TextureAtlas.class);//FrameDuration = 0.15f
        load(butchWalking, TextureAtlas.class);//FrameDuration = 0.083f
        load(butchHandsWalking, TextureAtlas.class);//FrameDuration = 0.083f
        load(butchHorseRiding, TextureAtlas.class);
        load(butchHorseIdle, TextureAtlas.class);
        load(butchIdleCutscene, TextureAtlas.class);
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

        //Prisoners
        load(prisoner1Idle, TextureAtlas.class);
        load(prisoner2Idle, TextureAtlas.class);
        load(prisoner3Idle, TextureAtlas.class);
        load(prisoner4Idle, TextureAtlas.class);
        load(prisoner1Walking, TextureAtlas.class);
        load(prisoner2Walking, TextureAtlas.class);
        load(prisoner3Walking, TextureAtlas.class);
        load(prisoner4Walking, TextureAtlas.class);

        //Sheriffs
        load(sheriff1Idle, TextureAtlas.class);
        load(sheriff2Idle, TextureAtlas.class);
        load(sheriff3Idle, TextureAtlas.class);
        load(sheriff1Walking, TextureAtlas.class);
        load(sheriff2Walking, TextureAtlas.class);
        load(sheriff3Walking, TextureAtlas.class);

        //Soldiers
        load(soldier1Idle, TextureAtlas.class);
        load(soldier2Idle, TextureAtlas.class);
        load(soldier3Idle, TextureAtlas.class);
        load(soldier4Idle, TextureAtlas.class);
        load(woundedSoldier1Idle, TextureAtlas.class);
        load(woundedSoldier2Idle, TextureAtlas.class);
        load(deadSoldier1, TextureAtlas.class);
        load(deadSoldier2, TextureAtlas.class);

//        //GUN ATLAS
//        //Machine Gun
//        load(machineGunFiring, TextureAtlas.class);//FrameDuration = 0.083f
//        load(machineGunReload, TextureAtlas.class);//FrameDuration = 0.083f
//        load(machineGunWalking, TextureAtlas.class);//FrameDuration = 0.083f
//        load(machineGunPickup, TextureAtlas.class);
//        load(machineGunSprite, Texture.class);
//        load(machineGunSilhoutte, Texture.class);
//        //Colt
//        load(revolverReload, TextureAtlas.class);//FrameDuration = 0.083f
//        load(revolverFiring, TextureAtlas.class);//FrameDuration = 0.083f
//        load(revolverWalking, TextureAtlas.class);//FrameDuration = 0.083f
//        load(revolverPickup, TextureAtlas.class);
//        load(coltSprite, Texture.class);
//        load(revolverSilhouette, Texture.class);
//        //Shotgun
//        load(shotgunFiring, TextureAtlas.class);//FrameDuration = 0.083f
//        load(shotgunReload, TextureAtlas.class);//FrameDuration = 0.083f
//        load(shotgunWalking, TextureAtlas.class);//FrameDuration = 0.083f
//        load(shotgunPickup, TextureAtlas.class);
//        load(shotgunSprite, Texture.class);
//        load(shotgunSilhouette, Texture.class);
//        //Musket
//        load(musketFiring, TextureAtlas.class);//FrameDuration = 0.083f
//        load(musketReload, TextureAtlas.class);//FrameDuration = 0.083f
//        load(musketWalking, TextureAtlas.class);//FrameDuration = 0.083f
//        load(musketPickup, TextureAtlas.class);
//        load(musketSprite, Texture.class);
//        load(musketSilhoutte, Texture.class);
//        //Melee
//        load(meleeFiring, TextureAtlas.class);
//        load(meleeIdle, TextureAtlas.class);
//        load(meleeWalking, TextureAtlas.class);
//        load(meleeSilhoutte, Texture.class);
//        load(meleeAmmoBar, Texture.class);
        //HUD
        //Character Screen
        load(characterScreen, Texture.class);
        //Health Bars
        load(uiskin, Skin.class);
        load(enemyHBBG, Texture.class);
        load(enemyHBFG, Texture.class);
        load(playerHBBG, Texture.class);
        load(playerHBFG, Texture.class);
        // dialogue
        load(dialougueBox, Texture.class);
//        //Revolver Ammo Bar
//        load(revolverAmmoBar0, Texture.class);
//        load(revolverAmmoBar1, Texture.class);
//        load(revolverAmmoBar2, Texture.class);
//        load(revolverAmmoBar3, Texture.class);
//        load(revolverAmmoBar4, Texture.class);
//        load(revolverAmmoBar5, Texture.class);
//        load(revolverAmmoBar6, Texture.class);
//        //Machine Gun Ammo Bar
//        load(machineGunAmmoBar0, Texture.class);
//        load(machineGunAmmoBar1, Texture.class);
//        load(machineGunAmmoBar2, Texture.class);
//        load(machineGunAmmoBar3, Texture.class);
//        load(machineGunAmmoBar4, Texture.class);
//        load(machineGunAmmoBar5, Texture.class);
//        load(machineGunAmmoBar6, Texture.class);
//        load(machineGunAmmoBar7, Texture.class);
//        load(machineGunAmmoBar8, Texture.class);
//        load(machineGunAmmoBar9, Texture.class);
//        load(machineGunAmmoBar10, Texture.class);
//        load(machineGunAmmoBar11, Texture.class);
//        load(machineGunAmmoBar12, Texture.class);
//        load(machineGunAmmoBar13, Texture.class);
//        load(machineGunAmmoBar14, Texture.class);
//        load(machineGunAmmoBar15, Texture.class);
//        load(machineGunAmmoBar16, Texture.class);
//        load(machineGunAmmoBar17, Texture.class);
//        load(machineGunAmmoBar18, Texture.class);
//        load(machineGunAmmoBar19, Texture.class);
//        load(machineGunAmmoBar20, Texture.class);
//        load(machineGunAmmoBar21, Texture.class);
//        load(machineGunAmmoBar22, Texture.class);
//        load(machineGunAmmoBar23, Texture.class);
//        load(machineGunAmmoBar24, Texture.class);
//        //Shotgun Ammo Bar
//        load(shotgunAmmoBar0, Texture.class);
//        load(shotgunAmmoBar1, Texture.class);
//        load(shotgunAmmoBar2, Texture.class);
//        //Musket Ammo Bar
//        load(musketAmmoBar0, Texture.class);
//        load(musketAmmoBar1, Texture.class);
        //Coin Counter
        load(coinCounter0, Texture.class);
        load(coinCounter1, Texture.class);
        load(coinCounter2, Texture.class);
        load(coinCounter3, Texture.class);
        load(coinCounter4, Texture.class);
        load(coinCounter5, Texture.class);
        load(coinCounter6, Texture.class);
        load(coinCounter7, Texture.class);
        load(coinCounter8, Texture.class);
        load(coinCounter9, Texture.class);

        //Coin
        load(coinSpin, TextureAtlas.class);
        //Whisky
        load(whisky, TextureAtlas.class);
        load(whiskySpin, TextureAtlas.class);
        //Level Up Item
        load(lvlItem, TextureAtlas.class);
        //AMMO
        //MachineGun
        load(machineGunAmmo, TextureAtlas.class);
        //Revolver
        load(revolverAmmo, TextureAtlas.class);
        //Shotgun
        load(shotgunAmmo, TextureAtlas.class);
        //Musket
        load(musketAmmo, TextureAtlas.class);

        //Cannon
        load(cannonFiring, TextureAtlas.class);
        load(cannonIdle, Texture.class);

        //SOUNDS
        //Background Music
        load(mainTheme, Music.class);
        load(townTheme, Music.class);
        load(prisonTheme, Music.class);
        load(endMazeMapTheme, Music.class);
        load(bigCityTheme, Music.class);
        load(snowTheme, Music.class);
        load(caveTheme, Music.class);
        load(warzoneTheme, Music.class);
        load(routeTheme, Music.class);

        //SFX
        load(gunShot, Sound.class);
        load(menuClick, Music.class);
        load(hitEffect, Sound.class);
        load(revolverReloadEeffect, Sound.class);
        load(otherReloadEffect, Sound.class);
        load(shellBounceEffect, Sound.class);
        load(coinCollection, Sound.class);
        load(ammoCollection, Sound.class);
        load(walkingFX, Sound.class);
        load(potionCollection, Sound.class);

        //Animals sound
        load(maniMountNoise, Sound.class);
        load(maniDismountNoise, Sound.class);
        load(maniMovingNoise, Sound.class);
        load(maniNeighingNoise, Sound.class);
        load(maniSnortNoise, Sound.class);

        load(chickenNoise, Sound.class);
        load(cowNoise, Sound.class);
        load(raccoonNoise, Sound.class);
        load(pigNoise, Sound.class);
        load(goatNoise, Sound.class);
        load(turkeyNoise, Sound.class);


        //Gun Shot SOUNDS
        load(revolverFire, Sound.class);
        load(musketFire, Sound.class);
        load(shotgunFire, Sound.class);
        load(machinegunFire, Sound.class);
        //load(cannonFire, Sound.class);
        //load(rifleFire, Sound.class);
        //load(gatlingFire, Sound.class);


        //Gun reload SOUNDS
        load(revolverReloadNoise, Sound.class);
      //  load(musketReloadNoise, Sound.class);
        load(shotgunReloadNoise, Sound.class);
        load(machineGunReloadNoise, Sound.class);


        //character noises
        load(consumeFoodNoise, Sound.class);
        load(drinkItemNoise, Sound.class);
        load(oof, Sound.class);
        //  load(arghNoise, Sound.class);
        // load(gruntNoise, Sound.class)
        //load(playerDies, Sound.class);



    }

    public void includeGunAssets() {
        //GUN ATLAS
        //Machine Gun
        load(machineGunFiring, TextureAtlas.class);//FrameDuration = 0.083f
        load(machineGunReload, TextureAtlas.class);//FrameDuration = 0.083f
        load(machineGunWalking, TextureAtlas.class);//FrameDuration = 0.083f
        load(machineGunPickup, TextureAtlas.class);
        load(machineGunSprite, Texture.class);
        load(machineGunSilhoutte, Texture.class);
        //Colt
        load(revolverReload, TextureAtlas.class);//FrameDuration = 0.083f
        load(revolverFiring, TextureAtlas.class);//FrameDuration = 0.083f
        load(revolverWalking, TextureAtlas.class);//FrameDuration = 0.083f
        load(revolverPickup, TextureAtlas.class);
        load(coltSprite, Texture.class);
        load(revolverSilhouette, Texture.class);
        //Shotgun
        load(shotgunFiring, TextureAtlas.class);//FrameDuration = 0.083f
        load(shotgunReload, TextureAtlas.class);//FrameDuration = 0.083f
        load(shotgunWalking, TextureAtlas.class);//FrameDuration = 0.083f
        load(shotgunPickup, TextureAtlas.class);
        load(shotgunSprite, Texture.class);
        load(shotgunSilhouette, Texture.class);
        //Musket
        load(musketFiring, TextureAtlas.class);//FrameDuration = 0.083f
        load(musketReload, TextureAtlas.class);//FrameDuration = 0.083f
        load(musketWalking, TextureAtlas.class);//FrameDuration = 0.083f
        load(musketPickup, TextureAtlas.class);
        load(musketSprite, Texture.class);
        load(musketSilhoutte, Texture.class);
        //Melee
        load(meleeFiring, TextureAtlas.class);
        load(meleeIdle, TextureAtlas.class);
        load(meleeWalking, TextureAtlas.class);
        load(meleeSilhoutte, Texture.class);
        load(meleeAmmoBar, Texture.class);

        //Revolver Ammo Bar
        load(revolverAmmoBar0, Texture.class);
        load(revolverAmmoBar1, Texture.class);
        load(revolverAmmoBar2, Texture.class);
        load(revolverAmmoBar3, Texture.class);
        load(revolverAmmoBar4, Texture.class);
        load(revolverAmmoBar5, Texture.class);
        load(revolverAmmoBar6, Texture.class);
        //Machine Gun Ammo Bar
        load(machineGunAmmoBar0, Texture.class);
        load(machineGunAmmoBar1, Texture.class);
        load(machineGunAmmoBar2, Texture.class);
        load(machineGunAmmoBar3, Texture.class);
        load(machineGunAmmoBar4, Texture.class);
        load(machineGunAmmoBar5, Texture.class);
        load(machineGunAmmoBar6, Texture.class);
        load(machineGunAmmoBar7, Texture.class);
        load(machineGunAmmoBar8, Texture.class);
        load(machineGunAmmoBar9, Texture.class);
        load(machineGunAmmoBar10, Texture.class);
        load(machineGunAmmoBar11, Texture.class);
        load(machineGunAmmoBar12, Texture.class);
        load(machineGunAmmoBar13, Texture.class);
        load(machineGunAmmoBar14, Texture.class);
        load(machineGunAmmoBar15, Texture.class);
        load(machineGunAmmoBar16, Texture.class);
        load(machineGunAmmoBar17, Texture.class);
        load(machineGunAmmoBar18, Texture.class);
        load(machineGunAmmoBar19, Texture.class);
        load(machineGunAmmoBar20, Texture.class);
        load(machineGunAmmoBar21, Texture.class);
        load(machineGunAmmoBar22, Texture.class);
        load(machineGunAmmoBar23, Texture.class);
        load(machineGunAmmoBar24, Texture.class);
        //Shotgun Ammo Bar
        load(shotgunAmmoBar0, Texture.class);
        load(shotgunAmmoBar1, Texture.class);
        load(shotgunAmmoBar2, Texture.class);
        //Musket Ammo Bar
        load(musketAmmoBar0, Texture.class);
        load(musketAmmoBar1, Texture.class);
    }
}