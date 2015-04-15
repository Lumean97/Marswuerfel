package com.marswuerfel.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.marswuerfel.game.objects.DiceCard;
import com.marswuerfel.game.utils.Button;
import com.marswuerfel.game.utils.Constants;

public class StartScreen implements Screen {
	private Texture logo;
	private Button playButton;
	private DiceCard add;
	private DiceCard remove;
	static Sound clickDown;
	static Sound clickUp;

	@Override
	public void show() {
		logo = new Texture("gfx/StartLogo.png");
		playButton = new Button("gfx/playButton.png",
				Constants.GAME_WIDTH / 2 - 62, 350);
		add = new DiceCard("gfx/addUp.png", "gfx/addDown.png", 100, 100, -1);
		remove = new DiceCard("gfx/removeUp.png", "gfx/removeDown.png", 300,
				300, -1);
		clickDown = Gdx.audio.newSound(Gdx.files
				.internal("sounds/clickDown.mp3"));
		clickUp = Gdx.audio.newSound(Gdx.files.internal("sounds/clickUp.mp3"));
	}

	@Override
	public void render(float delta) {
		add.setBounds(700, 400, 64, 64);
		remove.setBounds(600, 400, 64, 64);
		if (add.isPressed() && !add.isPressedState()) {
			add.setPressed(true);
			add.setDownTexture();

			clickDown.play();
			Constants.GAME.maxPlayers++;
			if (Constants.GAME.maxPlayers > 25)
				Constants.GAME.maxPlayers = 25;
		}

		if (!add.isPressed() && add.isPressedState()) {
			add.setPressed(false);
			add.setUpTexture();
			clickUp.play();
		}
		if (remove.isPressed() && !remove.isPressedState()) {
			remove.setPressed(true);
			remove.setDownTexture();
			Constants.GAME.maxPlayers--;
			if (Constants.GAME.maxPlayers < 2)
				Constants.GAME.maxPlayers = 2;
			clickDown.play();
		}

		if (!remove.isPressed() && remove.isPressedState()) {
			remove.setPressed(false);
			remove.setUpTexture();
			clickUp.play();
		}

		if (playButton.isPressed()) {
			dispose();
			clickDown.play();
		}
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Constants.GAME.batch.begin();
		Constants.GAME.batch.draw(logo, 280, 380);
		Constants.GAME.batch.draw(playButton, playButton.getX(),
				playButton.getY());
		Constants.GAME.batch.draw(add, add.getX(), add.getY());
		Constants.GAME.batch.draw(remove, remove.getX(), remove.getY());
		Constants.GAME.font.setColor(1, 1, 1, 1);
		Constants.GAME.font.draw(Constants.GAME.batch, "Players: "
				+ Constants.GAME.maxPlayers, 610, 390);
		Constants.GAME.font.setColor(0, 0, 0, 1);
		Constants.GAME.batch.end();
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

	@Override
	public void dispose() {
		Constants.GAME.startGame();
		logo.dispose();
		playButton.setPressed(false);
		playButton.dispose();
		add.dispose();
		remove.dispose();
		clickDown.dispose();
		clickUp.dispose();

	}

}
