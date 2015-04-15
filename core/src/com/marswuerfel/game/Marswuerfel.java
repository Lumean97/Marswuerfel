package com.marswuerfel.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.marswuerfel.game.utils.Constants;

public class Marswuerfel extends ApplicationAdapter {

	public static final String TAG = "[Marswuerfel]";

	@Override
	public void create() {

		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Gdx.input.setInputProcessor(Constants.IN);
		Constants.GAME.create();
	}

	@Override
	public void render() {
		Constants.GAME.render();
	}

	@Override
	public void dispose() {
		Constants.GAME.dispose();
	}

}
