package com.eindopdracht.game.control;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.eindopdracht.game.EindOpdracht;

import java.util.HashMap;

//Manages assets
public class Assets {

    private AssetManager assetManager;
    private final FileHandleResolver resolver = new InternalFileHandleResolver();

    private final EindOpdracht game;

    public Assets(EindOpdracht game) {
        this.game = game;

        assetManager = new AssetManager();


    }

    //Queues the assets for the main menu
    public void queueMainMenu() {

    }

    //Queues the assets for the game screen
    public void queueGameScreen() {


    }

    public HashMap<String, BitmapFont> loadFonts() {
        HashMap<String, BitmapFont> fonts = new HashMap<>();

        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
        Color fontColor = new Color();
        fontColor.set(1, 1, 1, 1);

        FreetypeFontLoader.FreeTypeFontLoaderParameter gameOverFont = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        gameOverFont.fontFileName = "indiansteeds.ttf";
        gameOverFont.fontParameters.size = 30;
        gameOverFont.fontParameters.color = fontColor;
        assetManager.load("indiansteeds.ttf", BitmapFont.class, gameOverFont);

        FreetypeFontLoader.FreeTypeFontLoaderParameter titleFont = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        titleFont.fontFileName = "SALARYMA.ttf";
        titleFont.fontParameters.size = 20;
        titleFont.fontParameters.color = fontColor;
        assetManager.load("SALARYMA.ttf", BitmapFont.class, titleFont);

        FreetypeFontLoader.FreeTypeFontLoaderParameter descriptionFont = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        descriptionFont.fontFileName = "SALARYMA.ttf";
        descriptionFont.fontParameters.size = 10;
        descriptionFont.fontParameters.color = fontColor;
        assetManager.load("SALARYMA_description.ttf", BitmapFont.class, descriptionFont);

        assetManager.finishLoading();

        fonts.put("title", assetManager.get("SALARYMA.ttf", BitmapFont.class));
        fonts.put("gameOver", assetManager.get("indiansteeds.ttf", BitmapFont.class));
        fonts.put("description", assetManager.get("SALARYMA_description.ttf", BitmapFont.class));

        return fonts;
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
