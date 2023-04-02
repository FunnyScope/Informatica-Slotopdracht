package com.eindopdracht.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.eindopdracht.game.EindOpdracht;
import com.eindopdracht.game.input.Button;
import com.eindopdracht.game.input.InputHandler;
import java.util.stream.Stream;

public class MainMenu implements Screen {

    private final EindOpdracht game;
    private final ExtendViewport viewport;
    private final OrthographicCamera camera;
    private final InputHandler inputHandler;

    public MainMenu(EindOpdracht game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 192, 108);
        viewport = new ExtendViewport(320, 180, camera);

        inputHandler = new InputHandler();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Clearing the screen, updating the camera
        ScreenUtils.clear(0, 0, 0, 0);
        camera.position.set(0, 0, 0);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        //All commands that draw things on the screen go between this
        game.fonts.get("title").draw(game.batch, "Untitled Gun Game", -145, 75);
        game.fonts.get("description").draw(game.batch, "Press any key to continue", -150, -30);

        game.batch.end();

        if(Stream.of(Button.down, Button.up, Button.left, Button.right, Button.shoot).anyMatch(inputHandler::getButtonPressed)) {
            game.setScreen(game.gameScreen);
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
