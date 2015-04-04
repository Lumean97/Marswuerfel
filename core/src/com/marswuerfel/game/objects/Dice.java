package com.marswuerfel.game.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class Dice extends Sprite{
	public static final String TAG = "[Dice]";
	Texture[] textures;
	public static boolean running = false;
	private long lastRandom;
	private static Texture questionCard = new Texture("gfx/questionCard.png");
	public Dice(String[] textures, float x, float y, float width, float height){
		super(new Texture(textures[0]));
		this.textures = new Texture[textures.length];
		for(int i = 0; i<textures.length; i++){
			this.textures[i] = new Texture(textures[i]);
		}
		setBounds(x, y, width, height);
	}
	
	private void randomizeTexture(){
		this.setTexture(textures[MathUtils.random(textures.length-1)]);
		lastRandom = TimeUtils.millis();
	}
	
	public long getLastRandom(){
		return lastRandom;
	}
	
	public void randomize(int switchesPerMinute){
		int range = (int) (1000f/switchesPerMinute);
		if(range <= 0)range = 1;
		if(TimeUtils.millis()-range>=lastRandom)randomizeTexture();
	}
	public static void swtichRunning() {
		if (running) {
			running = false;
			return;
		}
		if (!running) {
			running = true;
			return;
		}
	}
	public void returnToBegin(){
		setTexture(questionCard);
	}
}
