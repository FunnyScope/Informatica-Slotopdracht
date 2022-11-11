package com.eindopdracht.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.eindopdracht.game.EindOpdracht;

public class GameScreen implements Screen {

    private EindOpdracht game;

    private ExtendViewport viewport;
    private OrthographicCamera camera;

    private World world;
    private Box2DDebugRenderer debugRenderer;

    public GameScreen(EindOpdracht game) {
        this.game = game;
        camera.setToOrtho(false, 320, 180);
        viewport = new ExtendViewport(80, 45, camera);

        //TODO: box2d initialisation
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Clearing the screen, updating the camera
        ScreenUtils.clear(0, 0, 0, 0);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        //All commands that draw things on the screen go between this
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
        world.dispose();
        debugRenderer.dispose();
    }
}
