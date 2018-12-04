package com.butch.game.gameobjects.HUDObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.butch.game.ButchGame;
import com.butch.game.gameobjects.HUDObjects.HealthBar;

/**
 * Created by rahmanel on 03/12/2018.
 */
public class Hud {
    public Stage stage;
    private Viewport viewport;
    private Integer health;
    public Label timeLabel;
    public Label levelLabel;
    public Label weaponLabel;
    public HealthBar hb;
   public  Table table = new Table();
    public Hud(SpriteBatch spriteBatch){

        viewport = new FitViewport(ButchGame.TARGET_WIDTH, ButchGame.TARGET_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport,spriteBatch);
        health = new Integer(30);

        table.top().left();
        hb = new HealthBar(1000,20);
        table.setFillParent(true);
        levelLabel = new Label(String.format("Level 1, Introduction"), new Label.LabelStyle(new BitmapFont(), Color.RED));
        table.add(levelLabel).expandX().pad(5);
        table.add(hb).expandX().pad(5);
        table.left();
        // table.row();
        //table.bottom();
        stage.addActor(table);
    }
}
