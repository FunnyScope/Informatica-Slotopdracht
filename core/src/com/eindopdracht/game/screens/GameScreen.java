package com.eindopdracht.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.eindopdracht.game.EindOpdracht;
import com.eindopdracht.game.control.*;
import com.eindopdracht.game.gameobject.Player;
import com.eindopdracht.game.input.Button;
import com.eindopdracht.game.perks.*;

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
    private float gameOverScreenTimer = 5;
    private final PerkID[] perks = {PerkID.HealToFull, PerkID.IncreasedAttackSpeed, PerkID.IncreasedDamage, PerkID.IncreasedMaxHealth, PerkID.TemporaryBulletImmunity};
    private final Array<PerkID> perkSelection = new Array<>();
    private int perkSelectionIndex = 0;
    private float perkLockInTimer = 1;

     enum GameState {
         Running,
         Paused,
         GameOver,
         LevelUp
     }

     GameState gameState;
     private ShapeRenderer shapeRenderer;
     Vector2 healthBarPosition = new Vector2();



    public GameScreen(EindOpdracht game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 192, 108);
        viewport = new ExtendViewport(320, 180, camera);

        handler = new Handler(this);
        debugRenderer = new Box2DDebugRenderer();
        gameObjectCreator = new GameObjectCreator(game, handler);
        levelCreator = new LevelCreator(handler, gameObjectCreator);
        shapeRenderer = new ShapeRenderer();
        healthBarPosition.x = viewport.getScreenWidth();
    }

    @Override
    public void show() {
        gameState = GameState.Running;
    }
    // TODO: Create the below
    private void gameOverSequence() {

    }

    // Adds the perk
    private void addPerk(PerkID addedPerk) {
        switch (addedPerk) {
            case TemporaryBulletImmunity -> player.addPerk(new TempBulletImmunity(player));
            case IncreasedAttackSpeed -> player.addPerk(new IncreasedAttackSpeed(player));
            case IncreasedDamage -> player.addPerk(new IncreasedDamage(player));
            case IncreasedMaxHealth -> player.addPerk(new IncreasedMaxHealth(player));
            case HealToFull -> player.addPerk(new HealToFull(player));
        }
    }

    // Does the time thing for physics
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

        // Clearing the screen
        ScreenUtils.clear(0, 0, 0, 0);

        switch(gameState) {
            case Running -> {

                // The main running of the game
                if (handler.noEnemiesLeft()) {
                    difficulty += 1;
                    levelCreator.clearLevel();
                    levelCreator.createLevel(difficulty);
                    player.getBody().getPosition().set(0, 0);
                    if(difficulty == 1) {
                        playerAlive = true;
                        player = (Player) handler.getObjectByIndex(0);
                    } else {
                        gameState = GameState.LevelUp;
                    }

                }

                camera.position.set(player.getBody().getPosition(), 0);
                camera.update();
                game.batch.setProjectionMatrix(camera.combined);
                debugRenderer.render(handler.getWorldHandler().getWorld(), camera.combined);


                game.batch.begin();
                // Everything that draws on the screen goes here, between begin() and end().
                handler.draw(game.batch);

                game.batch.end();

                // Health overlay
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(1 - player.getHealth() / player.getMaxHealth(), player.getHealth() / player.getMaxHealth(), 0.1f, 1);
                shapeRenderer.rect(5, 5, player.getHealth() / player.getMaxHealth() * 100, 20);
                shapeRenderer.end();
                shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
                shapeRenderer.setColor(0.5f, 0.5f, 0.5f, 1);
                shapeRenderer.rect(5, 5, 100, 20);
                shapeRenderer.end();

                // Update all the positions and such
                handler.update(delta);
                physicsStep(delta);
                gameObjectCreator.freeBullets();
                if(!handler.isPlayerAlive()) {
                    gameState = GameState.GameOver;
                }
                handler.removeDeadEnemies();
                handler.cleanUpPlayerShotPositions();
            }
            case Paused -> {
                // Probably nothing that needs to be done for this,
                // but I'll leave it here in case I change my mind.
            }
            case LevelUp -> {
                // Lets the player choose a level-up perk
                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                if (perkSelection.size == 0) {
                    player.setHealth(MathUtils.clamp(player.getHealth() + 25, 0, player.getMaxHealth()));
                    Array<PerkID> availablePerks = new Array<>(perks);
                    for(int i = 0; i < 3; i++) {
                        PerkID chosenPerk = availablePerks.get(MathUtils.random.nextInt(availablePerks.size));
                        availablePerks.removeValue(chosenPerk, true);
                        perkSelection.add(chosenPerk);
                    }
                }

                if (player.getInputHandler().getButtonPressed(Button.left)) {
                    if (perkSelectionIndex == 0) {
                        perkSelectionIndex = 2;
                    } else {
                        perkSelectionIndex--;
                    }
                }
                if (player.getInputHandler().getButtonPressed(Button.right)) {
                    if (perkSelectionIndex == 2) {
                        perkSelectionIndex = 0;
                    } else {
                        perkSelectionIndex++;
                    }
                }
                game.batch.begin();

                switch(perkSelection.get(perkSelectionIndex)) {
                    case HealToFull -> game.fonts.get("description").draw(game.batch,
                            "Heal to max HP.",
                            camera.position.x - 120,
                            camera.position.y);
                    case IncreasedDamage -> game.fonts.get("description").draw(game.batch,
                            "Deal more damage.",
                            camera.position.x - 70,
                            camera.position.y);
                    case IncreasedMaxHealth -> game.fonts.get("description").draw(game.batch,
                            "Increase your max HP.",
                            camera.position.x - 100,
                            camera.position.y);
                    case IncreasedAttackSpeed -> game.fonts.get("description").draw(game.batch,
                            "Increase your attack speed.",
                            camera.position.x - 120,
                            camera.position.y);
                    case TemporaryBulletImmunity -> game.fonts.get("description").draw(game.batch,
                            "Every 3 seconds, gain 1 second of damage immunity.", // Should honestly get a better font
                            camera.position.x - 160,
                            camera.position.y);
                }

                game.batch.end();

                if (player.getInputHandler().getButtonPressed(Button.shoot)) {
                    perkLockInTimer -= delta;
                    if (perkLockInTimer <= 0) {
                        perkLockInTimer = 1;
                        addPerk(perkSelection.get(perkSelectionIndex));
                        for(int i = perkSelection.size - 1; i == 0; i--) {
                            perkSelection.removeValue(perkSelection.get(i), true);
                        }
                        gameState = GameState.Running;
                    }
                }


            }
            case GameOver -> {

                // Puts the player back to the main menu, after 5 seconds have passed

                gameOverScreenTimer -= delta;
                if(gameOverScreenTimer <= 0) {
                    difficulty = 0;
                    handler.cleanOutGameObjects();
                    while(handler.playerShotPositions.size > 0) {
                        handler.cleanUpPlayerShotPositions();
                    }

                    gameObjectCreator.freeBullets();

                    System.out.println(handler.getGameObjects().size);
                    game.setScreen(game.mainMenu);
                }

                camera.update();
                game.batch.setProjectionMatrix(camera.combined);

                game.batch.begin();

                game.fonts.get("gameOver").draw(game.batch, "Game Over", camera.position.x - 95, camera.position.y);

                game.batch.end();
            }
        }




    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
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
