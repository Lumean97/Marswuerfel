package com.marswuerfel.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class InputHandler implements InputProcessor {
	private int touchX = 0;
	private int touchY = 0;
	private boolean touched;

	private int mousePosX;
	private int mousePosY;

	public static final String TAG = "[InputHandler]";

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		touchX = screenX * Constants.GAME_WIDTH / Gdx.graphics.getWidth();
		touchY = (Gdx.graphics.getHeight() - screenY) * Constants.GAME_HEIGHT
				/ Gdx.graphics.getHeight();
		touched = true;
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		touched = false;
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {

		mousePosX = screenX * Constants.GAME_WIDTH / Gdx.graphics.getWidth();
		mousePosY = (Gdx.graphics.getHeight() - screenY)
				* Constants.GAME_HEIGHT / Gdx.graphics.getHeight();
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

	public int getTouchX() {
		return touchX;
	}

	public int getTouchY() {
		return touchY;
	}

	public boolean isTouched() {
		return touched;
	}

	public int getMousePosX() {
		return mousePosX;
	}

	public int getMousePosY() {
		return mousePosY;
	}

}
