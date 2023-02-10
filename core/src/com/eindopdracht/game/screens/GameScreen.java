package com.eindopdracht.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.eindopdracht.game.EindOpdracht;
import com.eindopdracht.game.control.*;
import com.eindopdracht.game.gameobject.Player;

public class GameScreen implements Screen {

    private EindOpdracht game;
    private ExtendViewport viewport;
    private OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;
    private Handler handler;
    private GameObjectCreator gameObjectCreator;
    private float accumulator = 0;
    private Player player;
    private LevelCreator levelCreator;
    int difficulty = 0;


    public GameScreen(EindOpdracht game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 192, 108);
        viewport = new ExtendViewport(320, 180, camera);

        handler = new Handler(this);
        debugRenderer = new Box2DDebugRenderer();
        gameObjectCreator = new GameObjectCreator(game, handler);
        levelCreator = new LevelCreator(handler, gameObjectCreator);
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

        if (handler.noEnemiesLeft()) {
            difficulty += 1;
            levelCreator.clearLevel();
            levelCreator.createLevel(difficulty);
            if(difficulty == 1) {
                player = (Player) handler.getObjectByIndex(0);
            }
        }

        // Clearing the screen, updating the camera
        ScreenUtils.clear(0, 0, 0, 0);
        camera.position.set(player.getBody().getPosition(), 0);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        debugRenderer.render(handler.getWorldHandler().getWorld(), camera.combined);







        game.batch.begin();
        // Everything that draws on the screen goes here, between begin() and end().
        handler.draw(game.batch);


        game.batch.end();
        handler.update(delta);
        physicsStep(delta);
        gameObjectCreator.freeBullets();


    }

    @Override
    public void resize(int width, int height) {
        viewport.setScreenWidth(width);
        viewport.setScreenHeight(height);
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

    public GameObjectCreator getGameObjectCreator() {
        return gameObjectCreator;
    }

    public ExtendViewport getViewport() {
        return viewport;
    }
}
