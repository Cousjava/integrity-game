package com.github.integritygame;

import com.badlogic.gdx.Game;
import com.github.integritygame.screens.ScreenManager;

public class MyGdxGame extends Game {

	@Override
	public void create () {
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
