package com.eindopdracht.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.eindopdracht.game.EindOpdracht;
import com.eindopdracht.game.control.*;

public class GameScreen implements Screen {

    private EindOpdracht game;
    private ExtendViewport viewport;
    private OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;
    private Handler handler;
    private GameObjectCreator gameObjectCreator;
    private float accumulator = 0;

    public GameScreen(EindOpdracht game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 320, 180);
        viewport = new ExtendViewport(80, 45, camera);

        //TODO: box2d initialisation

        handler = new Handler();
        debugRenderer = new Box2DDebugRenderer();
    }

    @Override
    public void show() {

    }

    public void physicsStep(float deltaTime) {
        float stepTime = MathUtils.clamp(deltaTime, 1/240f, 1/60f);
        accumulator += stepTime;
        while(accumulator >= 1/60f) {
            handler.getWorldHandler().getWorld().step(1/60f, 6, 2);
            accumulator -= 1/60f;
        }
    }

    @Override
    public void render(float delta) {
        //Clearing the screen, updating the camera
        ScreenUtils.clear(0, 0, 0, 0);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);




        game.batch.begin();

        handler.draw(game.batch);

        //All commands that draw things on the screen go between this
        game.batch.end();
        handler.update(delta);
        physicsStep(delta);

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
        handler.getWorldHandler().dispose();
        debugRenderer.dispose();
    }
}
