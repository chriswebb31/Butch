package com.tpgame.demo.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.tpgame.demo.ButchGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
//		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//		new LwjglApplication(new ButchGame(), config);

		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "butch-game";
		cfg.useGL30 = false;
		cfg.width = 480;
		cfg.height = 320;

		new LwjglApplication(new ButchGame(), cfg);

	}
}
