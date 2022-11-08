package com.eindopdracht.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.eindopdracht.game.EindOpdracht;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setWindowedMode(320, 180);
		config.setForegroundFPS(60);
		config.setTitle("Informatica Eindopdracht");
		new Lwjgl3Application(new EindOpdracht(), config);
	}
}
