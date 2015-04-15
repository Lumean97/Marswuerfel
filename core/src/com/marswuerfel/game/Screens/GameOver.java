package com.marswuerfel.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.marswuerfel.game.utils.Button;
import com.marswuerfel.game.utils.Constants;

public class GameOver implements Screen{
	private String wonPlayers = "";
	private String noPlayer = "No Player won! :(";
	private Button playAgain;
	@Override
	public void show() {
		playAgain = new Button("gfx/PlayAgain.png", Constants.GAME_WIDTH/2-64, 50);
		for(int i = 0; i<Constants.GAME.getPlayers().length; i++){
			if(Constants.GAME.getPlayers()[i].hadWon())wonPlayers+="" + i + ", ";
		}
		String s = "";
		for(int i = 0; i<wonPlayers.length()-2; i++){
			s+=wonPlayers.charAt(i);
		}
	}
	
	private void update(){
		if(playAgain.isPressed()){
			Gdx.app.getApplicationListener().create();
		}
	}
	
	@Override
	public void render(float delta) {
		update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Constants.GAME.font.setColor(1, 1, 1, 1);
		Constants.GAME.font.setScale(1.3f);
		Constants.GAME.batch.begin();
		Constants.GAME.batch.draw(playAgain, playAgain.getX(), playAgain.getY());
		Constants.GAME.font.draw(Constants.GAME.batch, "Game Over!", 150, 300);
		if(wonPlayers.equals("")){
		Constants.GAME.font.draw(Constants.GAME.batch, noPlayer, 50, 200);
		}else{
			Constants.GAME.font.setScale(0.5f);
			Constants.GAME.font.drawWrapped(Constants.GAME.batch, "Player(s)" + wonPlayers + " won!", 10, 200, 750);

		}
		Constants.GAME.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
