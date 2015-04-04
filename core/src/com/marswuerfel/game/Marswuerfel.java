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
import com.marswuerfel.game.utils.Constants;
import com.marswuerfel.game.utils.DiceCard;

public class Marswuerfel extends ApplicationAdapter {
	SpriteBatch batch;

	Texture background;
	Texture middle;
	Texture tankDisplay;
	Texture alienDisplay;
	
	BitmapFont font;
	
	DiceCard cow;
	DiceCard human;
	DiceCard alien;
	DiceCard chicken;
	DiceCard tank;
	
	Sound clickDown;
	Sound clickUp;
	
	Sound cowSound;
	public static final String TAG = "[Mauswürfel]";

	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Gdx.input.setInputProcessor(Constants.IN);
		middle = new Texture("gfx/middleteil.png");
		background = new Texture("gfx/background.jpg");
		batch = new SpriteBatch();
		tankDisplay = new Texture("gfx/TankDisplay.png");
		alienDisplay = new Texture("gfx/AlienDisplay.png");
		tank = new DiceCard("gfx/Tank.png", "gfx/Tank.png", 0, 0);
		cow = new DiceCard("gfx/CowButtonUp.png", "gfx/CowButtonDown.png", 660,
				175);
		cow.setOrigin(0, 0);
		human = new DiceCard("gfx/HumanButtonUp.png",
				"gfx/HumanButtonDown.png", 735, 250);
		human.setOrigin(0, 0);
		alien = new DiceCard("gfx/AlienButtonUp.png",
				"gfx/AlienButtonDown.png", 735, 175);
		alien.setOrigin(0, 0);
		chicken = new DiceCard("gfx/ChickenButtonUp.png",
				"gfx/ChickenButtonDown.png", 660, 250);
		chicken.setOrigin(0, 0);
		
		clickDown = Gdx.audio.newSound(Gdx.files.internal("sounds/clickDown.mp3"));
		clickUp = Gdx.audio.newSound(Gdx.files.internal("sounds/clickUp.mp3"));
		cowSound = Gdx.audio.newSound(Gdx.files.internal("sounds/kuhmuh.mp3"));
		
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		font.setScale(2f);

	}

	public void update() {
		if (alien.isPressed() && !alien.isPressedState()) {
			alien.setPressed(true);
			alien.setDownTexture();
			alien.setScale(1.0f, 0.9f);
			clickDown.play();
			alien.setCount(alien.getCount()+1);
		} 
			
		
		if (!alien.isPressed() && alien.isPressedState()){
			alien.setPressed(false);
			alien.setUpTexture();
			alien.setScale(1.0f);
			clickUp.play();
		}

		chicken.updateAsButton(clickDown);
		cow.updateAsButton(cowSound);
		human.updateAsButton(clickDown);
	}

	@Override
	public void render() {
		update();
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(background, 0, 0);
		batch.draw(middle, 37, 58);
		batch.draw(tankDisplay, 660, 350);
		batch.draw(alienDisplay, 735, 350);
		batch.draw(cow, cow.getX(), cow.getY(), cow.getOriginX(),
				cow.getOriginY(), cow.getWidth(), cow.getHeight(),
				cow.getScaleX(), cow.getScaleY(), cow.getRotation());
		batch.draw(human, human.getX(), human.getY(), human.getOriginX(),
				human.getOriginY(), human.getWidth(), human.getHeight(),
				human.getScaleX(), human.getScaleY(), human.getRotation());
		batch.draw(alien, alien.getX(), alien.getY(), alien.getOriginX(),
				alien.getOriginY(), alien.getWidth(), alien.getHeight(),
				alien.getScaleX(), alien.getScaleY(), alien.getRotation());
		batch.draw(chicken, chicken.getX(), chicken.getY(),
				chicken.getOriginX(), chicken.getOriginY(), chicken.getWidth(),
				chicken.getHeight(), chicken.getScaleX(), chicken.getScaleY(),
				chicken.getRotation());

		font.draw(batch, "" + tank.getCount(), 667, 400);
		font.draw(batch, "" + alien.getCount(), 742, 400);
		batch.end();
	}
	
	@Override
	public void dispose(){
		batch.dispose();
		background.dispose();
		middle.dispose();
		tankDisplay.dispose();
		alienDisplay.dispose();
		font.dispose();
		cow.dispose();
		human.dispose();
		alien.dispose();
		chicken.dispose();
		tank.dispose();
		clickDown.dispose();
		clickUp.dispose();
		cowSound.dispose();
	}
	
}
