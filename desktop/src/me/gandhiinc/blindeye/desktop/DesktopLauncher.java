package me.gandhiinc.blindeye.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import me.gandhiinc.blindeye.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 869;
		config.backgroundFPS = 0;
		config.foregroundFPS = 0;
		config.vSyncEnabled = true;
		config.resizable = false;
		config.title = "Blind Eye";
		Game game = new Game();
		new LwjglApplication(game, config);
	}
}
