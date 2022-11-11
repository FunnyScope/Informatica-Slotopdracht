package com.eindopdracht.game.control;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.eindopdracht.game.EindOpdracht;

//Manages assets
public class Assets {

    private AssetManager assetManager;
    public final AssetDescriptor<Skin> skin;

    private EindOpdracht game;

    public Assets(EindOpdracht game) {
        this.game = game;

        assetManager = new AssetManager();
        //TODO: make skin(s) and such
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

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

}
