package com.marswuerfel.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.marswuerfel.game.objects.Dice;
import com.marswuerfel.game.objects.DiceCard;
import com.marswuerfel.game.utils.Constants;

public class LifeCycle extends Game{

	Player player;
	Texture background;
	Texture middle;
	Texture tankDisplay;
	Texture alienDisplay;
	Texture chickenDisplay;
	Texture cowDisplay;
	Texture humanDisplay;

	BitmapFont font;

	DiceCard cow;
	DiceCard human;
	DiceCard alien;
	DiceCard chicken;
	DiceCard tank;
	DiceCard startButton;

	Sound clickDown;
	Sound clickUp;

	Sound cowSound;
	Dice[] dices;

	private String[] cardTextures;
	
	public LifeCycle(Player player){
		this.player = player;
		create();
	}
	
	@Override
	public void create() {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Gdx.input.setInputProcessor(Constants.IN);
		middle = new Texture("gfx/middleteil.png");
		background = new Texture("gfx/background.jpg");
		tankDisplay = new Texture("gfx/TankDisplay.png");
		alienDisplay = new Texture("gfx/AlienDisplay.png");
		chickenDisplay = new Texture("gfx/ChickenDisplay.png");
		cowDisplay = new Texture("gfx/CowDisplay.png");
		humanDisplay = new Texture("gfx/HumanDisplay.png");
		tank = new DiceCard("gfx/Tank.png", "gfx/Tank.png", 0, 0, 4);
		cow = new DiceCard("gfx/CowButtonUp.png", "gfx/CowButtonDown.png", 660,
				175, 3);
		cow.setOrigin(0, 0);
		human = new DiceCard("gfx/HumanButtonUp.png",
				"gfx/HumanButtonDown.png", 735, 250, 2);
		human.setOrigin(0, 0);
		alien = new DiceCard("gfx/AlienButtonUp.png",
				"gfx/AlienButtonDown.png", 735, 175, 5);
		alien.setOrigin(0, 0);
		chicken = new DiceCard("gfx/ChickenButtonUp.png",
				"gfx/ChickenButtonDown.png", 660, 250, 1);
		chicken.setOrigin(0, 0);
		startButton = new DiceCard("gfx/StartUp.png", "gfx/StartDown.png", 662, 40, -1);
		startButton.setOrigin(0,  0);
		
		clickDown = Gdx.audio.newSound(Gdx.files
				.internal("sounds/clickDown.mp3"));
		clickUp = Gdx.audio.newSound(Gdx.files.internal("sounds/clickUp.mp3"));
		cowSound = Gdx.audio.newSound(Gdx.files.internal("sounds/kuhmuh.mp3"));

		font = new BitmapFont();
		font.setColor(Color.BLACK);
		font.setScale(2f);
		cardTextures = new String[] { "gfx/questionCard.png",
				"gfx/ChickenCard.png", "gfx/HumanCard.png", "gfx/CowCard.png",
				"gfx/TankCard.png", "gfx/AlienCard.png", "gfx/AlienCard.png" };
		dices = new Dice[13];
		for (int i = 0; i < 4; i++) {
			dices[i] = new Dice(cardTextures, (37 + 32 + (64 * i * 2.5f)),
					(58 + (middle.getHeight() - 96)), 64, 64);
		}
		for (int i = 4; i < 9; i++) {
			dices[i] = new Dice(cardTextures, (37 + (64 * (i - 4) * 2.15f)),
					(58 + (middle.getHeight() / 2 - 32)), 64, 64);
		}
		for (int i = 9; i < 13; i++) {
			dices[i] = new Dice(cardTextures,
					(37 + 32 + (64 * (i - 9) * 2.5f)),
					(58 + (middle.getHeight() / 3 - 96)), 64, 64);
		}
	}
	
	public void update() {
		if(dices[0].getIndexID()!=0){
		alien.updateAsButton(clickDown, dices);
		chicken.updateAsButton(clickDown, dices);
		cow.updateAsButton(cowSound, dices);
		human.updateAsButton(clickDown, dices);
		if (!alien.isPressed() && alien.isPressedState()) {
			alien.setPressed(false);
			alien.setUpTexture();
			alien.setScale(1.0f);
			clickUp.play();
		}
		}
		if (startButton.isPressed() && !startButton.isPressedState()) {
			startButton.setPressed(true);
			startButton.setDownTexture();
			
			clickDown.play();
			Dice.swtichRunning();
		}

		if (!startButton.isPressed() && startButton.isPressedState()) {
			startButton.setPressed(false);
			startButton.setUpTexture();
			clickUp.play();
		}
			
		
		for (Dice dice : dices) {
			if (Dice.running && dice.isSpinable()) {

				dice.randomize(5);
			} else if (!Dice.running) {
				if(dice.getIndexID()==Dice.TANK && dice.isSpinable()){
					tank.setCount(tank.getCount() +1);
					dice.setPressed(true);
					dice.setSpinable(false);
					dice.setFinalTexture(dice.getTexture());
				}
			}  else {
				// dice.returnToBegin();
			}
		}
		int i = 0;
		for(Dice dice : dices){
			
			if(!dice.isSpinable())i++;
		}
		if(i>=13){
			boolean ch = false;
			boolean h = false;
			boolean cow = false;
			int tanks = 0;
			int aliens = 0;
			int givenPoints = 0;
			for(Dice d : dices){
				switch(d.getIndexID()){
				case Dice.CHICKEN:
					ch = true;
					givenPoints++;
					break;
				case Dice.COW:
					cow = true;
					givenPoints++;
					break;
				case Dice.HUMAN:
					h = true;
					givenPoints++;
					break;
				case Dice.ALIEN:
					aliens++;
					break;
				case Dice.TANK:
					tanks++;
					break;
				}
			}
			if(tanks>aliens)player.loose();
			if(ch&&h&&cow)givenPoints+=3;
			player.stop(givenPoints);
		}
		
	}
	
	@Override
	public void render(){
		update();
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Marswuerfel.batch.begin();
		Marswuerfel.batch.draw(background, 0, 0);
		Marswuerfel.batch.draw(middle, 37, 58);
		Marswuerfel.batch.draw(tankDisplay, 660, 350);
		Marswuerfel.batch.draw(alienDisplay, 735, 350);
		Marswuerfel.batch.draw(chickenDisplay, 60, 410);
		Marswuerfel.batch.draw(cowDisplay, 280, 410);
		Marswuerfel.batch.draw(humanDisplay, 500, 410);
		Marswuerfel.batch.draw(cow, cow.getX(), cow.getY(), cow.getOriginX(),
				cow.getOriginY(), cow.getWidth(), cow.getHeight(),
				cow.getScaleX(), cow.getScaleY(), cow.getRotation());
		Marswuerfel.batch.draw(human, human.getX(), human.getY(), human.getOriginX(),
				human.getOriginY(), human.getWidth(), human.getHeight(),
				human.getScaleX(), human.getScaleY(), human.getRotation());
		Marswuerfel.batch.draw(alien, alien.getX(), alien.getY(), alien.getOriginX(),
				alien.getOriginY(), alien.getWidth(), alien.getHeight(),
				alien.getScaleX(), alien.getScaleY(), alien.getRotation());
		Marswuerfel.batch.draw(chicken, chicken.getX(), chicken.getY(),
				chicken.getOriginX(), chicken.getOriginY(), chicken.getWidth(),
				chicken.getHeight(), chicken.getScaleX(), chicken.getScaleY(),
				chicken.getRotation());
		Marswuerfel.batch.draw(startButton, startButton.getX(), startButton.getY());
		font.draw(Marswuerfel.batch, "" + chicken.getCount(), 130, 455);
		font.draw(Marswuerfel.batch, "" + cow.getCount(), 350, 455);
		font.draw(Marswuerfel.batch, "" + human.getCount(), 570, 455);
		font.draw(Marswuerfel.batch, "" + tank.getCount(), 667, 400);
		font.draw(Marswuerfel.batch, "" + alien.getCount(), 742, 400);
		for (Dice dice : dices) {
			Marswuerfel.batch.draw(dice, dice.getX(), dice.getY());
			if (!dice.isSpinable()) {
				Marswuerfel.batch.draw(dice.getFinalTexture(), dice.getX(), dice.getY());
				Marswuerfel.batch.draw(Dice.blackOverlay, dice.getX(), dice.getY());

			}
		}
		Marswuerfel.batch.end();
	}
	
	@Override
	public void dispose(){
		background.dispose();
		middle.dispose();
		tankDisplay.dispose();
		alienDisplay.dispose();
		cowDisplay.dispose();
		chickenDisplay.dispose();
		humanDisplay.dispose();
		font.dispose();
		cow.dispose();
		human.dispose();
		alien.dispose();
		chicken.dispose();
		tank.dispose();
		clickDown.dispose();
		clickUp.dispose();
		cowSound.dispose();
		for (Dice dice : dices) {
			dice.getTexture().dispose();
		}
	}
	

}
