package com.butch.game.gameobjects.HUDObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.spriterenderables.Player;
/**
 * Created by rahmanel on 03/12/2018.
 */
public class Hud implements Disposable{
    public Stage stage;
    private Viewport viewport;
    public Label timeLabel;
    public Label levelLabel;
    public Label weaponLabel;
    public Label coinLabel;
    public HealthBar hb;
    public  Table table = new Table();
    public Player player;

    public Hud(SpriteBatch spriteBatch, Player player){
        this.player = player;
        viewport = new FitViewport(ButchGame.TARGET_WIDTH, ButchGame.TARGET_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport,spriteBatch);
        // health = new Integer(1000);
        levelLabel = new Label(String.format("Level 1: A Land far far away"), new Label.LabelStyle(new BitmapFont(), Color.DARK_GRAY));
        levelLabel.setFontScale(2.0f);
        coinLabel = new Label(String.format("0"), new Label.LabelStyle(new BitmapFont(), Color.DARK_GRAY));
        coinLabel.setFontScale(2.0f);
        weaponLabel = new Label(String.format(player.getActiveWeapon().gunName), new Label.LabelStyle(new BitmapFont(), Color.BLUE));
        weaponLabel.setFontScale(2.0f);
        table.top().left();
        hb = new HealthBar((int)player.getHealth(),20);
        table.setFillParent(true);
        table.row();
        table.add(hb).expandX().left().pad(5);
        table.add(levelLabel).expandX().right().pad(5);
        table.row();
        table.add(coinLabel).expand().bottom().left().pad(5);
        table.add(weaponLabel).expand().bottom().right().pad(5);

        stage.addActor(table);
    }

    @Override
    public void dispose() {
        //stage.dispose();
    }
}
