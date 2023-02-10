package com.eindopdracht.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eindopdracht.game.control.Assets;
import com.eindopdracht.game.screens.GameScreen;
import com.eindopdracht.game.screens.LoadingScreen;
import com.eindopdracht.game.screens.MainMenu;

public class EindOpdracht extends Game {
	//Spritebatch is the thing that sends what gets drawn to the screen to openGL
	public SpriteBatch batch;
	//The screens
	Screen loadingScreen;
	Screen gameScreen;
	Screen mainMenu;
	//Assets
	public Assets assets;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		loadingScreen = new LoadingScreen(this);
		gameScreen = new GameScreen(this);
		mainMenu = new MainMenu(this);

		assets = new Assets(this);
		assets.queueMainMenu();

		//setScreen(loadingScreen);
		setScreen(gameScreen);
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
		batch.dispose();
		loadingScreen.dispose();
		gameScreen.dispose();
		mainMenu.dispose();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}
}
