package com.butch.game.gameobjects.HUDObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.spriterenderables.Player;
import com.badlogic.gdx.graphics.Texture;

public class Hud implements Disposable{
    public Stage stage;
    private Viewport viewport;
    public Label levelLabel;
    public Label weaponLabel;
    public Label coinLabel;
    public HealthBar hb;
    public  Table table = new Table();
    public Player player;
    private Texture ammoCount;
    private Image ammoCountImage;
    private Image healthBarBG;
    private Image healthBarFG;

    public Hud(SpriteBatch spriteBatch, Player player){
        this.player = player;
        viewport = new FitViewport(ButchGame.TARGET_WIDTH, ButchGame.TARGET_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport,spriteBatch);
        // health = new Integer(1000);
        levelLabel = new Label(String.format("Level 1: A Land far far away"), new Label.LabelStyle(new BitmapFont(), Color.DARK_GRAY));
        levelLabel.setFontScale(2.0f);
        coinLabel = new Label(String.format("0"), new Label.LabelStyle(new BitmapFont(), Color.DARK_GRAY));
        coinLabel.setFontScale(2.0f);
        ammoCount = player.getActiveWeapon().ammoBar;
        ammoCountImage = new Image(ammoCount);
        ammoCountImage.setPosition(Gdx.graphics.getWidth()/1.1f-ammoCountImage.getWidth()*2,Gdx.graphics.getHeight()/14-ammoCountImage.getHeight()*2);
        ammoCountImage.setSize(ammoCount.getWidth() * 6, ammoCount.getHeight() * 6);
        weaponLabel = new Label(String.format(player.getActiveWeapon().gunName), new Label.LabelStyle(new BitmapFont(), Color.BLUE));
        weaponLabel.setFontScale(2.0f);
//      ammoCountImage.setDrawable(new TextureRegionDrawable(new TextureRegion(ammoCount)));
        healthBarBG = new Image(ButchGame.assets.get(ButchGame.assets.playerHBBG, Texture.class));
        healthBarFG = new Image(ButchGame.assets.get(ButchGame.assets.playerHBFG, Texture.class));
        healthBarBG.setPosition(Gdx.graphics.getWidth()/14-healthBarBG.getWidth()*2,Gdx.graphics.getHeight()/1.1f-healthBarBG.getHeight()*2);
        healthBarFG.setPosition(Gdx.graphics.getWidth()/14-healthBarBG.getWidth()*2 + 2,Gdx.graphics.getHeight()/1.1f-healthBarFG.getHeight()*2 -1);
        healthBarBG.setSize(player.getHealth(), healthBarBG.getHeight() * 6);
      //  hb = new HealthBar((int)player.getHealth(),20,0,0);
        table.top().left();

        table.setFillParent(true);
        table.row();
        //table.add(hb).expandX().left().pad(5);
        table.add(levelLabel).expandX().right().pad(5);
        table.row();
        table.add(coinLabel).expand().bottom().left().pad(5);

        stage.addActor(table);
        stage.addActor(ammoCountImage);
        stage.addActor(healthBarBG);
        stage.addActor(healthBarFG);
    }

    @Override
    public void dispose() {
        //stage.dispose();
    }

    public void setAmmoCount(Texture newAmmoCount) {
        ammoCountImage.setDrawable(new TextureRegionDrawable(new TextureRegion(newAmmoCount)));
        ammoCountImage.setWidth(newAmmoCount.getWidth());
        ammoCountImage.setHeight(newAmmoCount.getHeight());
        ammoCountImage.setScale(7);
    }
}
