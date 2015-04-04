package com.marswuerfel.game.utils;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Button extends Sprite {
	
	protected InputHandler in;
	private boolean pressed;
	@SuppressWarnings("unused")
	private static final String TAG = "[Button] ";
	/**
	 * 
	 * @param texturePath - Path to the Image that represents the Button
	 * @param x - x-coordianate on which the Button should be placed.
	 * @param y - y-coordianate on which the button should be placed ( [0,0] is on the bottom left cornor ).
	 * @param width - width of the Button.
	 * @param height - height of the Button.
	 * @return A Button Object based on the {@link Texture} which is created by the texturePath.
	 */
	public Button(String texturePath, float x, float y, float width, float height) {
		super(new Texture(texturePath));
		this.in = Constants.IN;
		setBounds(x, y, width, height);
		setOrigin(getWidth()/2, getHeight()/2);
		
	}
	
	public Button(String texturePath, float x, float y){
		super(new Texture(texturePath));
		this.in = Constants.IN;
		setBounds(x, y, getWidth(), getHeight());
		setOrigin(getWidth()/2, getHeight()/2);
	}

	public boolean isPressed() {
		int screenX = in.getTouchX();
		int screenY = in.getTouchY();
		if (screenX>=getX() && screenX <= getX() + getWidth() && screenY >= getY() && screenY <= getY() + getHeight()
				&& in.isTouched()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isOver() {
		int screenX = in.getMousePosX();
		int screenY = in.getMousePosY();
		if (screenX>=getX() && screenX <= getX() + getWidth() && screenY >= getY() && screenY <= getY() + getHeight()){
			return true;
		} else {
			return false;
		}
	}

	
	
	
	
	public boolean isPressedState(){
		return pressed;
	}
	public void setPressed(boolean pressed){
		this.pressed = pressed;
	}
	
	public void dispose(){
		this.getTexture().dispose();
	}
	
}
