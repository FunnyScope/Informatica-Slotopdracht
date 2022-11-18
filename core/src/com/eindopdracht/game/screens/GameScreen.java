package com.eindopdracht.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
<<<<<<< Updated upstream
=======
import com.badlogic.gdx.math.MathUtils;
>>>>>>> Stashed changes
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.eindopdracht.game.EindOpdracht;
import com.eindopdracht.game.control.*;

public class GameScreen implements Screen {

    private EindOpdracht game;

    private ExtendViewport viewport;
    private OrthographicCamera camera;

<<<<<<< Updated upstream
    private World world;
    private Box2DDebugRenderer debugRenderer;

<<<<<<< Updated upstream
=======
    private Handler handler;
    private GameObjectCreator gameObjectCreator;

>>>>>>> Stashed changes
=======
    private Box2DDebugRenderer debugRenderer;

    private Handler handler;
    private GameObjectCreator gameObjectCreator;

    private float accumulator = 0;

>>>>>>> Stashed changes
    public GameScreen(EindOpdracht game) {
        this.game = game;
        camera.setToOrtho(false, 320, 180);
        viewport = new ExtendViewport(80, 45, camera);

        //TODO: box2d initialisation
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
<<<<<<< Updated upstream

        game.batch.begin();

<<<<<<< Updated upstream
=======


>>>>>>> Stashed changes
        //All commands that draw things on the screen go between this
        game.batch.end();
=======

        game.batch.begin();

        handler.draw(game.batch);

        //All commands that draw things on the screen go between this
        game.batch.end();
        handler.update(delta);
        physicsStep(delta);
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
        world.dispose();
=======
        handler.getWorldHandler().dispose();
>>>>>>> Stashed changes
        debugRenderer.dispose();
    }
}
