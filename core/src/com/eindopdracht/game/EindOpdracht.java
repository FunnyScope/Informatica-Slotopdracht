package com.eindopdracht.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.eindopdracht.game.control.Assets;
import com.eindopdracht.game.screens.GameScreen;
import com.eindopdracht.game.screens.LoadingScreen;
import com.eindopdracht.game.screens.MainMenu;

import java.util.HashMap;

public class EindOpdracht extends Game {
	//Spritebatch is the thing that sends what gets drawn to the screen to openGL
	public SpriteBatch batch;
	//The screens
	public Screen loadingScreen;
	public Screen gameScreen;
	public Screen mainMenu;
	//Assets
	public Assets assets;
	public final HashMap<String, BitmapFont> fonts = new HashMap<>();
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		loadingScreen = new LoadingScreen(this);
		gameScreen = new GameScreen(this);
		mainMenu = new MainMenu(this);

		assets = new Assets(this);
		fonts.putAll(assets.loadFonts());
		assets.queueMainMenu();

		setScreen(mainMenu);
	}

	//Render method
	@Override
	public void render () {
		super.render();
	}

	//Everything that's disposable should be disposed of here. Triggers when the program is closed.
	//It prevents memory leaks.
	@Override
	public void dispose () {
		for(HashMap.Entry<String, BitmapFont> font : fonts.entrySet()) {
			fonts.get(font.getKey()).dispose();
		}
		batch.dispose();
		loadingScreen.dispose();
		gameScreen.dispose();
		mainMenu.dispose();
	}
}
