package com.butch.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.butch.game.ButchGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Adventures Of Butch";
		config.width = Math.round(ButchGame.TARGET_WIDTH);
		config.height = Math.round(ButchGame.TARGET_HEIGHT);
		new LwjglApplication(new ButchGame(), config);
	}
}