package com.github.ncdhz.fish.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.ncdhz.fish.RunFishingGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = RunFishingGame.DY_HEIGHT;
		config.width = RunFishingGame.DY_WIDTH;
		config.title = RunFishingGame.DY_TITLE;
		config.resizable = false;

		new LwjglApplication(new RunFishingGame(), config);
	}
}
