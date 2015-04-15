package com.marswuerfel.game;

import com.marswuerfel.game.Screens.LifeCycle;
import com.marswuerfel.game.utils.Constants;

public class Player {
	private LifeCycle game;
	private int points = 0;
	boolean lost = false;
	public static final String TAG = "[Player]";
	private boolean won = false;

	public Player() {

	}

	public void start() {
		game = new LifeCycle(this);
		Constants.GAME.setScreen(game);
	}

	public void render(float delta) {
		game.render(delta);
	}

	public LifeCycle getCurrentLifeCycle() {
		return game;
	}

	public void stop(int p) {
		points += p;
		Constants.GAME.playerIndex++;
		game.dispose();
	}

	public int getPoints() {
		return points;
	}

	public void loose() {
		lost = true;
	}

	public boolean isLost() {

		return lost;
	}

	public void setWon(boolean won) {
		this.won = won;
	}

	public boolean hadWon() {
		return won;
	}
	
	public void dispose(){
		
	}
}
