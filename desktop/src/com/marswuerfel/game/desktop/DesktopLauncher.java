package com.marswuerfel.game.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.marswuerfel.game.Marswuerfel;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 480;
		config.addIcon("gfx/desktop_icon.png", FileType.Internal);
		config.title = "Marswürfel";
		new LwjglApplication(new Marswuerfel(), config);
	}
}
