package com.butch.game.gameobjects.HUDObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.butch.game.ButchGame;
import com.butch.game.dialouge.DialogueBox;
import com.butch.game.gameobjects.abstractinterface.Gun;
import com.butch.game.gameobjects.spriterenderables.Player;
import com.badlogic.gdx.graphics.Texture;

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
    private ArrayList<Label> ammoInventory = new ArrayList<Label>();
//    private Label revolverAmmo;
//    private Label machineGunAmmo;
//    private Label shotgunAmmo;
//    private Label musketAmmo;
    private Texture revolverImage;
    private Texture machineGunImage;
    private Texture shotgunImage;
    private Texture musketImage;
    private Texture characterScreen;
    private Image characterScreenImage;

    public CharacterScreen(SpriteBatch spriteBatch, Player player) {
        this.player = player;
        viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new OrthographicCamera());
        stage = new Stage(viewport,spriteBatch);
        characterScreen = ButchGame.assets.get(ButchGame.assets.characterScreen, Texture.class);
        characterScreenImage = new Image(characterScreen);
        characterScreenImage.setSize(characterScreen.getWidth() * 2.5f, characterScreen.getHeight() * 2.5f);
        characterScreenImage.setPosition(Gdx.graphics.getWidth() / 5f, Gdx.graphics.getHeight() / 5f);
        butchLevel = new Label(String.format(Integer.toString(player.getPlayerLevel())), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        butchHealth = new Label(String.format(player.getHealth() + "/" + player.getMaxHealth()), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        butchDamage = new Label(String.format(Float.toString(player.getActiveWeapon().damage + ((player.getPlayerLevel() - 1) * 5))), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        butchShotSpeed = new Label(String.format(Float.toString(player.getActiveWeapon().speed + ((player.getPlayerLevel() - 1) * 3))), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        butchAccuracy = new Label(String.format(Float.toString(player.getActiveWeapon().accuracy)), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        for(Gun gun : player.getGunInventory()) {
            Label gunLabel = new Label("Hi", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
            switch(gun.id) {
                case 10:
                    gunLabel.setText(String.format("Revolver Ammo: " + gun.clip + "/" + player.pistolAmmo));
                    gunLabel.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/1.9f);
                    ammoInventory.add(gunLabel);
                    break;
                case 11:
                    gunLabel.setText(String.format("Machine Gun Ammo: " + gun.clip + "/" + player.rifleAmmo));
                    gunLabel.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2.0f);
                    ammoInventory.add(gunLabel);
                    break;
                case 12:
                    gunLabel.setText(String.format("Shotgun Ammo" + gun.clip + "/" + player.shotgunAmmo));
                    gunLabel.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2.1f);
                    ammoInventory.add(gunLabel);
                    break;
                case 13:
                    gunLabel.setText(String.format("Musket Ammo" + gun.clip + "/" + player.musketAmmo));
                    gunLabel.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2.2f);
                    ammoInventory.add(gunLabel);
                    break;
            }
        }

        table.add(ammoInventory.get(0)).expandX().right();
        table.row();
//        table.add(ammoInventory.get(1)).expandX().center().right();
//        table.row();

        table.top().left();
        stage.addActor(characterScreenImage);
        stage.addActor(table);

        for(Label label : ammoInventory) {
            stage.addActor(label);
        }
    }
    @Override
    public void dispose() {
        //stage.dispose();
    }

    public void render(float delta) {

    }

    public void drawStage() {
        stage.draw();
    }
}
