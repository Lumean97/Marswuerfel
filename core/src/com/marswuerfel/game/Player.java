package com.marswuerfel.game;

public class Player {
	private LifeCycle game;
	private int points = 0;
	boolean lost = false;
	public Player(){
		
	}
	public void start(){
		game = new LifeCycle(this);
	}
	public void render(){
		game.render();
	}
	
	public LifeCycle getCurrentLifeCycle(){
		return game;
	}
	
	public void stop(int p){
		points+=p;
		Marswuerfel.playerIndex++;
		System.out.println(points);
		game.dispose();
	}
	
	public int getPoints(){
		return points;
	}
	
	public void loose(){
		lost = true;
		System.out.println("lost");
	}
	
	public boolean isLost(){
		
		return lost;
	}
}
