package com.marswuerfel.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.marswuerfel.game.Screens.GameOver;
import com.marswuerfel.game.Screens.LifeCycle;
import com.marswuerfel.game.Screens.StartScreen;
import com.marswuerfel.game.utils.Constants;

public class GameHandler extends Game{
	public BitmapFont font;
	public SpriteBatch batch;
	public int maxPlayers = 2;
	public int lastIndex = 0;
	public int playerIndex = 0;
	public boolean gameOver = false;
	private boolean finalRound = false;
	Player[] players;
	public GameHandler(){
		
	}
	
	@Override
	public void create() {
		font = new BitmapFont(Gdx.files.internal("kavoon.fnt"));
		font.setColor(Color.BLACK);
		font.setScale(0.5f);
		
		batch = new SpriteBatch();
		
		 
		 setScreen(new StartScreen());
	}
	
	public void restart(){
		setScreen(new StartScreen());
	}
	
	@Override
	public void render() {
		
		if(playerIndex>lastIndex && !gameOver){
			System.out.println(playerIndex);
			System.out.println("checked");
			if(playerIndex>maxPlayers-1){
				if(finalRound)gameOver();
				System.out.println("checked, again");
				playerIndex = 0;
				int leftPlayers = 0;
				for(Player p : players){
					if(!p.lost)leftPlayers++;
				}
				if(leftPlayers<=1){
					for(Player p : players){
						if(!p.lost)p.setWon(true);
					}
					gameOver();
				}
			}
			System.out.println(playerIndex);
			checkWinner();
			lastIndex = playerIndex;
			checkLoose(0);
			if(gameOver)return;
			players[playerIndex].start();
			System.out.println("was ist!");
		}
		screen.render(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose(){
	}
	
	public void checkLoose(int trys){
		if(playerIndex==0 && !players[0].lost)return;
		System.out.println("try:" + trys + " max:" + maxPlayers);
		if(trys>=maxPlayers){gameOver();
		return;}
		
		
		if(players[playerIndex].isLost()){
			playerIndex++;
			trys++;
			if(playerIndex>=maxPlayers)playerIndex=0;
			checkLoose(trys);
		}
	}
	
	public void gameOver(){
		LifeCycle.disposeAll();
		getScreen().dispose();
		setScreen(new GameOver());
		gameOver=true;
	}
	public void checkWinner(){
		for(Player p : players){
			if(p.getPoints()>=25){
				p.setWon(true);
				finalRound = true;
			}
		}
	}
	
	public Player[] getPlayers(){
		return players;
	}
	
	public void startGame(){
		players = new Player[Constants.GAME.maxPlayers];
		 System.out.println(players.length);
		 for(int i = 0; i<players.length; i++){
			 players[i] = new Player();
		 }
		players[playerIndex].start();
	}
}
