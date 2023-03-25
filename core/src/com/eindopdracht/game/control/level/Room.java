package com.eindopdracht.game.control.level;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.eindopdracht.game.control.GameObjectCreator;
import com.eindopdracht.game.gameobject.ID;
import com.eindopdracht.game.gameobject.ai.Connection;

import java.util.Random;

public abstract class Room {



    // To create the game objects
    protected GameObjectCreator gameObjectCreator;
    // These coordinates point to the centre of the room
    protected int x, y;
    // Size being both width and height.
    public final int size = 160;
    // Each different enemy has its own difficulty, just as rooms have their difficulty.
    protected int difficulty;
    private ID[] enemyIDArray = {ID.basicEnemy, ID.shotgunEnemy};
    protected Random random;
    protected Array<Vector2> roomDistributionTiles = new Array<>();

    protected Vector2 tilePosition;


    public Room(GameObjectCreator gameObjectCreator, int x, int y, Vector2 tilePosition) {
        this.gameObjectCreator = gameObjectCreator;
        this.x = x;
        this.y = y;
        this.tilePosition = tilePosition;

        fillInRoomDistributionTiles();

        random = new Random();
    }

    public abstract void build();
    // Building internal walls needs to happen in this method too
    public abstract Array<Connection> roomPatrol();
    public abstract void createWalls();

    private void fillInRoomDistributionTiles() {
        for (int i = 1; i <= 16; i++) {
            for (int j = 1; j <= 16; j++) {
                roomDistributionTiles.add(new Vector2(
                        tilePosition.x * 16 + i,
                        tilePosition.y * 16 + j
                ));
            }

        }
    }


    protected int createRandomEnemy(int difficulty) {

        int chosenEnemy = random.nextInt(enemyIDArray.length);
        try {
            switch (enemyIDArray[chosenEnemy]) {
                case basicEnemy -> {
                    difficulty -= 1;
                    theDefault();
                }
                case shotgunEnemy -> {
                    if (difficulty < 2) {
                        theDefault();
                        difficulty -= 1;
                        break;
                    }
                    difficulty -= 2;
                    gameObjectCreator.createShotgunEnemy(random.nextInt(150) - 75 + x, random.nextInt(150) - 75 + y);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return difficulty;
    }

    // Creates a basic enemy
    private void theDefault() throws Exception {
        gameObjectCreator.createBasicEnemy(random.nextInt(150) - 75 + x, random.nextInt(150) - 75 + y);

    }

    // Returns the room tiles adjacent to this one
    public Array<Vector2> getAdjacentTiles() {
        Array<Vector2> adjacentTiles = new Array<>();

        adjacentTiles.add(
                new Vector2(tilePosition.x, tilePosition.y - 1),
                new Vector2(tilePosition.x, tilePosition.y + 1),
                new Vector2(tilePosition.x - 1, tilePosition.y),
                new Vector2(tilePosition.x + 1, tilePosition.y)
        );

        return adjacentTiles;
    }


    public int getDifficulty() {
        return difficulty;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Vector2 getTilePosition() {
        return tilePosition;
    }
}
