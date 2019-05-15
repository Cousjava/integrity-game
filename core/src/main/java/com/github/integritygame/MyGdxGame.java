package com.github.integritygame;

import com.badlogic.gdx.Game;
import com.github.integritygame.screens.ScreenManager;
import com.github.integritygame.util.KeyBindingManager;

import javax.net.ssl.KeyStoreBuilderParameters;

public class MyGdxGame extends Game {
	public static KeyBindingManager keyManager;

	@Override
	public void create () {
		keyManager = new KeyBindingManager();
		ScreenManager.getInstance().init(this);
		ScreenManager.getInstance().changeScreen(ScreenManager.Screens.MAIN_MENU);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}
}
