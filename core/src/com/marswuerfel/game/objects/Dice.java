package com.marswuerfel.game.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.marswuerfel.game.utils.Button;

public class Dice extends Button {
	// Index for "textures"
	public static final int QUESTION = 0;
	public static final int CHICKEN = 1;
	public static final int HUMAN = 2;
	public static final int COW = 3;
	public static final int TANK = 4;
	public static final int ALIEN = 5;

	private int indexID = 0;

	private Texture finalTexture;
	public static final String TAG = "[Dice]";
	Texture[] textures;
	public static Texture blackOverlay = new Texture("gfx/blackOverlay.png");
	public static boolean running = false;
	private long lastRandom;
	private static Texture questionCard = new Texture("gfx/questionCard.png");
	private boolean spinable = true;
	public static boolean chosen = true;

	public Dice(String[] textures, float x, float y, float width, float height) {
		super(new Texture(textures[0]));
		this.textures = new Texture[textures.length];
		for (int i = 0; i < textures.length; i++) {
			this.textures[i] = new Texture(textures[i]);
		}
		finalTexture = this.textures[0];
		setBounds(x, y, width, height);
	}

	private void randomizeTexture() {
		int r = MathUtils.random(textures.length - 2) + 1;
		indexID = r;
		this.setTexture(textures[r]);
		lastRandom = TimeUtils.millis();
	}

	public long getLastRandom() {
		return lastRandom;
	}

	public void randomize(int switchesPerMinute) {
		int range = (int) (1000f / switchesPerMinute);
		if (range <= 0)
			range = 1;
		if (TimeUtils.millis() - range >= lastRandom)
			randomizeTexture();
	}

	public static void swtichRunning() {
		if (running) {
			chosen = false;
			running = false;
			return;
		}
		if (!running) {
			running = true;
			return;
		}
	}

	public void returnToBegin() {
		setTexture(questionCard);
	}

	public boolean isSpinable() {
		return spinable;
	}

	public void setSpinable(boolean s) {
		spinable = s;
	}

	public void setFinalTexture(int textureIndex) {
		finalTexture = textures[textureIndex];
		spinable = false;

	}

	public Texture getFinalTexture() {
		return finalTexture;
	}

	public int getIndexID() {
		return indexID;
	}

	public void setFinalTexture(Texture texture) {
		finalTexture = texture;
		spinable = false;

	}

	public void dispose() {
		super.dispose();
		finalTexture.dispose();
		for (Texture t : textures) {
			t.dispose();
		}
		blackOverlay.dispose();
		questionCard.dispose();
	}
}
