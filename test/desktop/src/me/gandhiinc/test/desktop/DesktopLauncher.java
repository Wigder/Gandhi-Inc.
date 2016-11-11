package me.gandhiinc.test.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import me.gandhiinc.test.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//config.fullscreen = true;
		config.width = 1280;
		config.height = 720;
		config.backgroundFPS = 0;
		config.foregroundFPS = 0;
		config.vSyncEnabled = true;
		config.title = "Blind Eye";
		Game game = new Game();
		LwjglApplication app = new LwjglApplication(game, config);
	}
}
