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

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.butch.game.ButchGame;
import com.butch.game.desktop.DesktopLauncher;
import com.butch.game.gameobjects.spriterenderables.Player;
import com.butch.game.screens.GameScreens.ModelGameScreen;
import com.butch.game.screens.GameScreens.SnowyMountain;
import org.junit.Test;
import org.mockito.internal.matchers.Null;

public class UnitTestExample {
	private static DesktopLauncher desktopLauncher = new DesktopLauncher();
	private static ButchGame butchGame = desktopLauncher.getButchGame();
	private static Player player;
	@Test
	public void loadNewButchGame() {

		assertTrue("player exists!", setupPlayer());
	}

	private boolean setupPlayer(){
        ModelGameScreen gameScreen = null;
        try{
            gameScreen = new ModelGameScreen(butchGame, new FitViewport(0,0), null, 0) {
                @Override
                public void show() {
                    super.show();
                }
            };
            player = gameScreen.player;
        } catch (NullPointerException e){
            e.printStackTrace();
        }

        if(player!=null){
            return true;
        }
        return false;
    }

	private boolean setupGame(){
		if(desktopLauncher != null){
		    if(butchGame != null){
		        return true;
            }
            else{
                return false;
            }
		}
		else{
			return false;
		}
	}

}
