package com.marswuerfel.game.objects;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.marswuerfel.game.utils.Button;

public class DiceCard extends Button {

	Texture upTexture;
	Texture downTexture;

	

	private int count = 0;

	public DiceCard(String upTexture, String downTexture, float x, float y) {
		super(upTexture, x, y);
		this.upTexture = new Texture(upTexture);
		this.downTexture = new Texture(downTexture);
	}

	public DiceCard(String upTexture, String downTexture, float x, float y,
			float width, float height) {
		super(upTexture, x, y, width, height);
		this.upTexture = new Texture(upTexture);
		this.downTexture = new Texture(downTexture);
	}

	public void setDownTexture() {
		this.setTexture(downTexture);
	}

	public void setUpTexture() {
		this.setTexture(upTexture);
	}

	public void checkPressed() {
		int screenX = in.getTouchX();
		int screenY = in.getTouchY();
		if (screenX >= getX() && screenX <= getX() + getWidth()
				&& screenY >= getY() && screenY <= getY() + getHeight()
				&& in.isTouched()) {
			setPressed(true);
		}
	}

	public void updateAsButton(Sound sound, Dice[] dices) {
		if (isPressedState() || Dice.running)
			return;
		checkPressed();
		if (isPressedState() && !Dice.running) {
			setDownTexture();
			setScale(1.0f, 0.9f);
			sound.play();
			
			for(Dice dice : dices){
				if(dice.getIndexID()==Dice.CHICKEN){
					dice.setPressed(true);
					dice.setSpinable(false);
					dice.setFinalTexture(dice.getTexture());
					setCount(count+1);
					
				}
			}
			Dice.running = true;
			
		}
	}

	public int getCount() {
		return count;
	}

	public void setCount(int c) {
		count = c;
		if (count > 999)
			count = 999;
		if (count < 0)
			count = 0;
	}

	public void dispose() {
		super.dispose();
		upTexture.dispose();
		downTexture.dispose();
	}

	

}
