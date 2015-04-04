package com.marswuerfel.game.utils;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class DiceCard extends Button{

	Texture upTexture;
	Texture downTexture;
	
	private int count = 0;
	
	
	public DiceCard(String upTexture, String downTexture, float x, float y) {
		super(upTexture, x, y);
		this.upTexture = new Texture(upTexture);
		this.downTexture = new Texture(downTexture);
	}
	
	public DiceCard(String upTexture, String downTexture, float x, float y, float width, float height) {
		super(upTexture, x, y, width, height);
		this.upTexture = new Texture(upTexture);
		this.downTexture = new Texture(downTexture);
	}
	
	
	public void setDownTexture(){
		this.setTexture(downTexture);
	}
	public void setUpTexture(){
		this.setTexture(upTexture);
	}
	
	
	public void checkPressed() {
		int screenX = in.getTouchX();
		int screenY = in.getTouchY();
		if (screenX>=getX() && screenX <= getX() + getWidth() && screenY >= getY() && screenY <= getY() + getHeight()
				&& in.isTouched()) {
			setPressed(true);
		} 
	}
	
	public void updateAsButton(Sound sound) {
		if(isPressedState())return;
		checkPressed();
		if (isPressedState()) {
			setDownTexture();
			setScale(1.0f, 0.9f);
			sound.play();
		}
	}
	
	public int getCount(){
		return count;
	}
	public void setCount(int c){
		count = c;
		if(count> 999) count = 999;
		if(count<0) count = 0;
	}
	
	public void dispose(){
		super.dispose();
		upTexture.dispose();
		downTexture.dispose();
	}
	
}
