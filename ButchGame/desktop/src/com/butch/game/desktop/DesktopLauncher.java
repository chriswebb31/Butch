package com.butch.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.butch.game.ButchGame;
import com.butch.game.screens.MainMenuScreen;

public class DesktopLauncher {
	/*
    CLASS : DESKTOP LAUNCHER

    the launchers are different main classes which run depending on the system running the game. It also supplies the BUTCHGAME class with
    the window configs. such as a size and title
 	*/
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Butch";
		config.useGL30 = false;
		config.width = Math.round(ButchGame.TARGET_WIDTH);
		config.height = Math.round(ButchGame.TARGET_HEIGHT);

		new LwjglApplication(new ButchGame(), config);
	}
}