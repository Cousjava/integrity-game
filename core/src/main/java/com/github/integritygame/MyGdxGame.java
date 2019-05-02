package com.github.integritygame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.GdxNativesLoader;
import com.badlogic.gdx.utils.SharedLibraryLoader;
import com.github.integritygame.screens.ScreenManager;

public class MyGdxGame extends Game {

	@Override
	public void create () {
            SharedLibraryLoader libraryLoader = new SharedLibraryLoader();
            libraryLoader.load("gdx-box2d");
                GdxNativesLoader.load();
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
