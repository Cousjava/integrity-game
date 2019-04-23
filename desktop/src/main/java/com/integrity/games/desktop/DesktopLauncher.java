package com.integrity.games.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.integritygame.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
			config.title = "Tanks";
            config.height = 480;
            config.width = 600;
            config.resizable = false;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
