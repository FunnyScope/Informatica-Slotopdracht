package com.eindopdracht.game.control;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

//Manages assets
public class Assets {

    private AssetManager assetManager;
    public final AssetDescriptor<Skin> skin;

    public Assets() {
        assetManager = new AssetManager();
        skin = null;
    }

    //Queues the assets for the main menu
    public void queueMainMenu() {

    }

    //Queues the assets for the game screen
    public void queueGameScreen() {

    }

    public void dispose() {
	assetManager.dispose();
    }

}
