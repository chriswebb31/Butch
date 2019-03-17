package com.butch.game.gameobjects.HUDObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.spriterenderables.Player;
import com.badlogic.gdx.graphics.Texture;
//import org.junit.Test;

import java.util.ArrayList;

public class CharacterScreen implements Disposable {
    private Stage stage;
    private Viewport viewport;
    private Player player;
    private Table table = new Table();
    private Label butchLevel;
    private Label butchHealth;
    private Label butchDamage;
    private Label butchShotSpeed;
    private Label butchAccuracy;
    private Label gunAmmoLabel;
    private ArrayList<Label> ammoInventory = new ArrayList<Label>();
//    private Label revolverAmmo;
//    private Label machineGunAmmo;
//    private Label shotgunAmmo;
//    private Label musketAmmo;
    private Image revolverImage;
    private Image machineGunImage;
    private Image shotgunImage;
    private Image musketImage;
    private Image characterScreenImage;

    public CharacterScreen(SpriteBatch spriteBatch, Player player) {
        this.player = player;
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport,spriteBatch);
        characterScreenImage = new Image(ButchGame.assets.get(ButchGame.assets.characterScreen, Texture.class));
        characterScreenImage.setSize(characterScreenImage.getWidth() * 2.5f, characterScreenImage.getHeight() * 2.5f);
        characterScreenImage.setPosition(Gdx.graphics.getWidth() / 5f, Gdx.graphics.getHeight() / 5f);
        revolverImage = new Image(ButchGame.assets.get(ButchGame.assets.revolverSilhouette, Texture.class));
        revolverImage.setSize(revolverImage.getWidth() * 6, revolverImage.getHeight() * 6);
        revolverImage.setPosition(Gdx.graphics.getWidth()/3.5f, Gdx.graphics.getHeight()/4.5f);
        butchLevel = new Label(String.format("LEVEL: " + player.getPlayerLevel()), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        butchLevel.setPosition(Gdx.graphics.getWidth()/3f, Gdx.graphics.getHeight()/1.34f);
        butchLevel.setFontScale(2);
        butchHealth = new Label(String.format("HEALTH: " + (int)player.getHealth() + "/" + (int)player.getMaxHealth()), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        butchHealth.setPosition(Gdx.graphics.getWidth()/1.8f, Gdx.graphics.getHeight()/1.39f);
        butchHealth.setFontScale(2);
        butchDamage = new Label(String.format(player.getActiveWeapon().gunName.toUpperCase() + " DAMAGE: " + (player.getActiveWeapon().damage + ((player.getPlayerLevel() - 1) * 5))), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        butchDamage.setPosition(Gdx.graphics.getWidth()/1.8f, Gdx.graphics.getHeight()/1.65f);
        butchDamage.setFontScale(2);
        butchShotSpeed = new Label(String.format("SHOT SPEED: " + (player.getActiveWeapon().speed + ((player.getPlayerLevel() - 1) * 3))), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        butchShotSpeed.setPosition(Gdx.graphics.getWidth()/1.8f, Gdx.graphics.getHeight()/2.0f);
        butchShotSpeed.setFontScale(2);
        butchAccuracy = new Label(String.format(Float.toString(player.getActiveWeapon().accuracy)), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        butchAccuracy.setPosition(Gdx.graphics.getWidth()/1.8f, Gdx.graphics.getHeight()/2.5f);
        butchAccuracy.setFontScale(2);

        gunAmmoLabel = new Label("", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        gunAmmoLabel.setFontScale(2);
        gunAmmoLabel.setPosition(Gdx.graphics.getWidth()/1.8f, Gdx.graphics.getHeight()/3.5f);

        switch(player.getActiveWeapon().id) {
            case(10) :
                gunAmmoLabel.setText(String.format("REVOLVER AMMO: " + player.getActiveWeapon().clip + "/" + player.pistolAmmo));
                break;
            case(11) :
                gunAmmoLabel.setText(String.format("MACHINE GUN AMMO: " + player.getActiveWeapon().clip + "/" + player.rifleAmmo));
                break;
            case(12) :
                gunAmmoLabel.setText(String.format("SHOTGUN AMMO: " + player.getActiveWeapon().clip + "/" + player.shotgunAmmo));
                break;
            case(13) :
                gunAmmoLabel.setText(String.format("MUSKET AMMO: " + player.getActiveWeapon().clip + "/" + player.musketAmmo));
                break;
        }


//        table.add(ammoInventory.get(1)).expandX().center().right();
//        table.row();

        table.top().left();
        stage.addActor(characterScreenImage);
        stage.addActor(butchLevel);
        stage.addActor(butchHealth);
        stage.addActor(butchDamage);
        stage.addActor(butchShotSpeed);
        stage.addActor(butchAccuracy);
        stage.addActor(revolverImage);
        stage.addActor(gunAmmoLabel);
    }
    @Override
    public void dispose() {
        stage.dispose();
    }

    public void render(float delta) {

    }

    public void drawStage() {
        stage.draw();
    }
}
