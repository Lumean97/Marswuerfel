package com.marswuerfel.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.marswuerfel.game.Player;
import com.marswuerfel.game.objects.Dice;
import com.marswuerfel.game.objects.DiceCard;
import com.marswuerfel.game.utils.Constants;

public class LifeCycle implements Screen {

	static Player player;
	static Texture background;
	static Texture middle;
	static Texture tankDisplay;
	static Texture alienDisplay;
	static Texture chickenDisplay;
	static Texture cowDisplay;
	static Texture humanDisplay;
	static Texture rules;

	static DiceCard cow;
	static DiceCard human;
	static DiceCard alien;
	static DiceCard chicken;
	static DiceCard tank;
	static DiceCard startButton;
	static DiceCard help;

	static Sound clickDown;
	static Sound clickUp;

	Dice[] dices;

	private String[] cardTextures;
	private boolean stopped = false;
	private int givenPoints = 0;
	private boolean ruleOn = false;

	public LifeCycle(Player player) {
		LifeCycle.player = player;
		show();
	}

	@Override
	public void show() {
		rules = new Texture("gfx/rules.png");
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
		startButton = new DiceCard("gfx/StartUp.png", "gfx/StartDown.png", 662,
				40, -1);
		help = new DiceCard("gfx/helpUp.png", "gfx/helpDown.png", 590, 0, -1);
		startButton.setOrigin(0, 0);

		clickDown = Gdx.audio.newSound(Gdx.files
				.internal("sounds/clickDown.mp3"));
		clickUp = Gdx.audio.newSound(Gdx.files.internal("sounds/clickUp.mp3"));

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
		if (dices[0].getIndexID() != 0) {
			alien.updateAsButton(clickDown, dices);
			chicken.updateAsButton(clickDown, dices);
			cow.updateAsButton(clickDown, dices);
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

		if (!startButton.isPressed() && startButton.isPressedState()
				&& Dice.chosen) {
			startButton.setPressed(false);
			startButton.setUpTexture();
			clickUp.play();
		}

		if (help.isPressed() && !help.isPressedState()) {
			help.setPressed(true);
			help.setDownTexture();

			clickDown.play();
			if (!ruleOn)
				ruleOn = true;
			else
				ruleOn = false;

		}

		if (!help.isPressed() && help.isPressedState()) {
			help.setPressed(false);
			help.setUpTexture();
			clickUp.play();
		}

		for (Dice dice : dices) {
			if (Dice.running && dice.isSpinable()) {

				dice.randomize(5);
			} else if (!Dice.running) {
				if (dice.getIndexID() == Dice.TANK && dice.isSpinable()) {
					tank.setCount(tank.getCount() + 1);
					dice.setPressed(true);
					dice.setSpinable(false);
					dice.setFinalTexture(dice.getTexture());
				}
			} else {
				// dice.returnToBegin();
			}
		}
		int i = 0;
		for (Dice dice : dices) {

			if (!dice.isSpinable())
				i++;
		}
		if (i >= 13) {
			System.out.println("t");
			boolean chicken = false;
			boolean human = false;
			boolean cow = false;
			int tanks = 0;
			int aliens = 0;
			for (Dice d : dices) {
				switch (d.getIndexID()) {
				case Dice.CHICKEN:
					chicken = true;
					givenPoints++;
					break;
				case Dice.COW:
					cow = true;
					givenPoints++;
					break;
				case Dice.HUMAN:
					human = true;
					givenPoints++;
					break;
				case Dice.ALIEN:
					aliens++;
					break;
				case Dice.TANK:
					tanks++;
					break;
				case 6:
					aliens++;
					break;
				}
			}
			if (tanks > aliens)

				player.loose();
			if (chicken && human && cow)
				givenPoints += 3;
			stopped = true;
		}
		boolean cowAviable = true;
		boolean chickenAviable = true;
		boolean humanAviable = true;
		if (!Dice.running) {
			if (cow.isPressedState())
				cowAviable = false;
			if (chicken.isPressedState())
				chickenAviable = false;
			if (human.isPressedState())
				humanAviable = false;
			Dice.chosen = true;
			for (Dice d : dices) {
				if (d.isSpinable()) {
					if (d.getIndexID() == Dice.COW && cowAviable)
						Dice.chosen = false;
					if (d.getIndexID() == Dice.CHICKEN && chickenAviable)
						Dice.chosen = false;
					if (d.getIndexID() == Dice.HUMAN && humanAviable)
						Dice.chosen = false;
					if (d.getIndexID() == Dice.ALIEN || d.getIndexID() == 6)
						Dice.chosen = false;
				}
			}
		}
		if (tank.getCount() >= 7)
			player.loose();
	}

	@Override
	public void render(float delta) {
		update();
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Constants.GAME.batch.begin();
		Constants.GAME.batch.draw(background, 0, 0);
		Constants.GAME.batch.draw(middle, 37, 58);
		Constants.GAME.batch.draw(tankDisplay, 660, 350);
		Constants.GAME.batch.draw(alienDisplay, 735, 350);
		Constants.GAME.batch.draw(chickenDisplay, 60, 410);
		Constants.GAME.batch.draw(cowDisplay, 280, 410);
		Constants.GAME.batch.draw(humanDisplay, 500, 410);
		Constants.GAME.batch.draw(cow, cow.getX(), cow.getY(),
				cow.getOriginX(), cow.getOriginY(), cow.getWidth(),
				cow.getHeight(), cow.getScaleX(), cow.getScaleY(),
				cow.getRotation());
		Constants.GAME.batch.draw(human, human.getX(), human.getY(),
				human.getOriginX(), human.getOriginY(), human.getWidth(),
				human.getHeight(), human.getScaleX(), human.getScaleY(),
				human.getRotation());
		Constants.GAME.batch.draw(alien, alien.getX(), alien.getY(),
				alien.getOriginX(), alien.getOriginY(), alien.getWidth(),
				alien.getHeight(), alien.getScaleX(), alien.getScaleY(),
				alien.getRotation());
		Constants.GAME.batch.draw(chicken, chicken.getX(), chicken.getY(),
				chicken.getOriginX(), chicken.getOriginY(), chicken.getWidth(),
				chicken.getHeight(), chicken.getScaleX(), chicken.getScaleY(),
				chicken.getRotation());
		Constants.GAME.batch.draw(startButton, startButton.getX(),
				startButton.getY());
		Constants.GAME.batch.draw(help, help.getX(), help.getY());

		Constants.GAME.font.draw(Constants.GAME.batch, "" + chicken.getCount(),
				130, 455);
		Constants.GAME.font.draw(Constants.GAME.batch, "" + cow.getCount(),
				350, 455);
		Constants.GAME.font.draw(Constants.GAME.batch, "" + human.getCount(),
				570, 455);
		Constants.GAME.font.draw(Constants.GAME.batch, "" + tank.getCount(),
				667, 400);
		Constants.GAME.font.draw(Constants.GAME.batch, "" + alien.getCount(),
				742, 400);
		Constants.GAME.font.setColor(1, 1, 1, 1);
			Constants.GAME.font
					.draw(Constants.GAME.batch,
							"Player "
									+ (Constants.GAME.playerIndex + 1)
									+ ": "
									+ (Constants.GAME.getPlayers()[Constants.GAME.playerIndex]
											.getPoints()) + " Punkte", 10, 45);
		
		Constants.GAME.font.setColor(0, 0, 0, 1);
		for (Dice dice : dices) {
			Constants.GAME.batch.draw(dice, dice.getX(), dice.getY());
			if (!dice.isSpinable()) {
				Constants.GAME.batch.draw(dice.getFinalTexture(), dice.getX(),
						dice.getY());
				Constants.GAME.batch.draw(Dice.blackOverlay, dice.getX(),
						dice.getY());

			}
		}
		if (ruleOn) {
			Constants.GAME.batch.draw(rules, 65, 70);
		}
		Constants.GAME.batch.end();
		if (stopped)
			player.stop(givenPoints);
	}

	@Override
	public void dispose() {
		for(Dice d : dices){
			d.setSpinable(true);
		}
	}

	public static void disposeAll() {
		background.dispose();
		middle.dispose();
		tankDisplay.dispose();
		alienDisplay.dispose();
		cowDisplay.dispose();
		chickenDisplay.dispose();
		humanDisplay.dispose();
		rules.dispose();
		cow.dispose();
		human.dispose();
		alien.dispose();
		chicken.dispose();
		tank.dispose();
		clickDown.dispose();
		clickUp.dispose();
		startButton.dispose();
		help.dispose();
		

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

}
