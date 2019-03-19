/*******************************************************************************
 * Copyright 2015 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package de.tomgrill.gdxtesting.examples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Color;
import com.butch.game.gameobjects.spriterenderables.Enemy;
import com.butch.game.gamemanagers.AssetManagement;
import com.butch.game.gameobjects.spriterenderables.Player;
import com.butch.game.gameobjects.weapons.GunCreator;
import com.butch.game.screens.GameScreens.ModelGameScreen;
import com.butch.game.screens.GameScreens.SnowyMountain;
import org.junit.Test;
import org.mockito.internal.matchers.Null;
import com.butch.game.gameobjects.abstractinterface.Gun;

public class UnitTestExample {
	private static ButchGame butchGame = new ButchGame();
	private static Player player;
	private AssetManagement assets = new AssetManagement();
//	private SpriteBatch batch = new SpriteBatch();
    private Enemy enemy;
    private Gun gun = new GunCreator("TestWeapon");
//	@Test
//	public void loadNewButchGame() {
//
//		assertTrue("player exists!", setupPlayer());
//	}
//
//	private boolean setupPlayer(){
//        ModelGameScreen gameScreen = null;
//        assets.includeAssets();
//        assets.includeMainMenuScreenAssets();
//        try{
//            gameScreen = new ModelGameScreen(butchGame, new FitViewport(0,0), null, 0) {
//                @Override
//                public void show() {
//                    super.show();
//                }
//            };
//            player = gameScreen.player;
//        } catch (NullPointerException e){
//            e.printStackTrace();
//        }
//
//        if(player!=null){
//            return true;
//        }
//        return false;
//    }
//    private void enemyLoader() {
////        batch.begin();
//
//        enemy = new Enemy(new Vector2(0,0), 99);
////        batch.draw(enemy.getSprite().getTexture(), 1, 1);
////        batch.end();
//        enemy.setSprite(new Sprite(new Texture(getPixmapRectangle(1, 1, Color.BLACK)) ) );
//    }

//    @Test
//    public void testEnemyHealth() {
//        enemyLoader();
//
//        assertEquals(100, enemy.getHealth(), 0);
//    }

    @Test
    public void testGunId() {
	    assertEquals(20, gun.id);
    }

    @Test
    public void testGunDamage() {
        assertEquals(10f, gun.damage, 0);
    }

    @Test
    public void testGunAccuracy() {
        assertEquals(15f, gun.accuracy, 0);
    }

    @Test
    public void testGunFireRate() {
        assertEquals(0.5f, gun.fireRate, 0);
    }

    @Test
    public void testGunShotSpeed() {
        assertEquals(15, gun.speed, 0);
    }

    @Test
    public void testGunStartClip() {
        assertEquals(9999, gun.clip);
    }

    @Test
    public void testGunClipSize() {
        assertEquals(9999, gun.clipSize);
    }

    @Test
    public void testLevelDamage() {
        int playerLevel = 2;
        assertEquals(15, gun.damage + (5 * (playerLevel-1)), 0);
    }

    @Test
    public void testReloadSpeed() {
        assertEquals(3f, gun.reloadSpeed, 0);
    }

    @Test
    public void testLevelSpeed() {
        int playerLevel = 3;
        assertEquals(21, gun.speed + (3 * (playerLevel - 1)), 0);
    }

	private boolean setupGame(){
//		if(desktopLauncher != null){
//		    if(butchGame != null){
//		        return true;
//            }
//            else{
//                return false;
//            }
//		}
//		else{
//			return false;
//		}
        return true;
	}

    private static Pixmap getPixmapRectangle(int width, int height, Color color){
        Pixmap pixmap=new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(0,0, pixmap.getWidth(), pixmap.getHeight());
        return pixmap;
    }
}
