package com.marswuerfel.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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
	public static SpriteBatch batch;
	private int lastIndex = 0;
	public static int playerIndex = 0;
	private int maxPlayers = 5;
	Player[] players;
	@Override
	public void create() {
	 players = new Player[maxPlayers];
	 for(int i = 0; i<players.length; i++){
		 players[i] = new Player();
	 }
	 batch = new SpriteBatch();
	 players[0].start();
	}



	public void checkWinner(){
		for(Player p : players){
			if(p.getPoints()>=25){
				System.out.println("Winner!");
			}
		}
	}
	
	@Override
	public void render() {
	if(playerIndex>lastIndex){
		if(playerIndex>maxPlayers-1){
			playerIndex = 0;
			checkWinner();
		}
		lastIndex = playerIndex;
		checkLoose(0);
		players[playerIndex].start();
		
	}
	players[playerIndex].render();
	}

	@Override
	public void dispose() {
		batch.dispose();
		
	}
	
	public void checkLoose(int trys){
		if(trys>maxPlayers)gameOver();
		if(players[playerIndex].isLost()){
			playerIndex++;
			checkLoose(trys++);
		}
	}
	
	public void gameOver(){
		
	}

}
