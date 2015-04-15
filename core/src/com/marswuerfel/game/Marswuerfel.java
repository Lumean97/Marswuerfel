package com.marswuerfel.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.marswuerfel.game.objects.Dice;
import com.marswuerfel.game.objects.DiceCard;
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
		
	}
	
	

}
