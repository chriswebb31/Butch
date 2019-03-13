package com.butch.game.screens.GameScreens;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;
import com.butch.game.dialouge.DialogueBox;
import com.butch.game.gameobjects.abstractinterface.Gun;
import com.butch.game.gameobjects.weapons.GunCreator;

import java.util.ArrayList;
import java.util.Collection;

public class StartTavern extends ModelGameScreen {
    public static TiledMap map = ButchGame.assets.get(ButchGame.assets.startTavern);

    public StartTavern(int levelNumber, ButchGame game, FitViewport gameViewPort, TiledMap map, int playerLevel, int spawnLocation) {
        super(levelNumber, game, gameViewPort, map, playerLevel, spawnLocation);
    }
    public StartTavern(int levelNumber, ButchGame game, FitViewport gameViewPort, TiledMap map, ArrayList<Gun> weaponCache, int playerLevel, int spawnLocation) {
        super(levelNumber, game, gameViewPort, map, weaponCache, playerLevel, spawnLocation);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta){
        updateCameraPosition();
        for(Rectangle endPointLoc : endPoints) {
            if(player.getCollider().overlaps(endPointLoc)) {
                if(endPoints.indexOf(endPointLoc) == 0) {
                    game.setScreen( new NewGameScreen(1, game, gameViewPort, NewGameScreen.map, player.getGunInventory(),  player.getPlayerLevel(), 0));
                }
            }
        }


        super.render(delta);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
