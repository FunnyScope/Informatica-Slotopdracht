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

    private final EindOpdracht game;
    private final ExtendViewport viewport;
    private final OrthographicCamera camera;
    private final Box2DDebugRenderer debugRenderer;
    private final Handler handler;
    private final GameObjectCreator gameObjectCreator;
    private float accumulator = 0;
    private Player player;
    private final LevelCreator levelCreator;
    int difficulty = 0;
    private boolean playerAlive = false;

     enum GameState {
         Running,
         Paused,
         GameOver,
         LevelUp
     }

     GameState gameState;



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
        gameState = GameState.Running;
    }
    // TODO: Create the below
    private void gameOverSequence() {

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
                playerAlive = true;
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
        if (!handler.isPlayerAlive() && playerAlive) {
            playerAlive = false;
            gameOverSequence();
            // TODO: Figure the rest of this out. Screen should be set to main menu
        }
        handler.removeDeadEnemies();
        handler.cleanUpPlayerShotPositions();


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        gameState = GameState.Paused;
    }

    @Override
    public void resume() {
        gameState = GameState.Running;
    }

    @Override
    public void hide() {
        gameState = GameState.Paused;
    }

    @Override
    public void dispose() {
        handler.dispose();
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
