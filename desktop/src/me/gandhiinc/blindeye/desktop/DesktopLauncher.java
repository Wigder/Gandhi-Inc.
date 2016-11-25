package me.gandhiinc.blindeye.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import me.gandhiinc.blindeye.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//config.fullscreen = true;
		config.width = 1680;
		config.height = 1050;
		config.backgroundFPS = 0;
		config.foregroundFPS = 0;
		config.vSyncEnabled = false;
		config.title = "Blind Eye";
		Game game = new Game();
		LwjglApplication app = new LwjglApplication(game, config);
	}
}
