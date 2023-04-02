package com.eindopdracht.game.control.level;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.eindopdracht.game.control.GameObjectCreator;
import com.eindopdracht.game.gameobject.ID;
import com.eindopdracht.game.gameobject.ai.Connection;
import com.eindopdracht.game.gameobject.ai.Node;

import java.util.Random;

import static com.badlogic.gdx.math.MathUtils.random;

public abstract class Room {



    // To create the game objects
    protected GameObjectCreator gameObjectCreator;
    // These coordinates point to the centre of the room
    protected int x, y;
    // Size being both width and height.
    public final int size = 160;
    // Each different enemy has its own difficulty, just as rooms have their difficulty.
    protected int difficulty;
    private final ID[] enemyIDArray = {ID.basicEnemy, ID.shotgunEnemy};
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
                roomDistributionTiles.add(new Vector2(i, j));
            }

        }
    }


    protected int createRandomEnemy(int difficulty, Array<Connection> patrol) {

        int chosenEnemy = random.nextInt(enemyIDArray.length);
        try {
            Node spawnNode = patrol.get(random.nextInt(patrol.size - 1)).firstNode();
            switch (enemyIDArray[chosenEnemy]) {
                case basicEnemy -> {
                    difficulty -= 1;
                    theDefault(patrol, spawnNode);
                }
                case shotgunEnemy -> {
                    if (difficulty < 2) {
                        theDefault(patrol, spawnNode);
                        difficulty -= 1;
                        break;
                    }
                    difficulty -= 2;
                    gameObjectCreator.createShotgunEnemy(spawnNode.position().x, spawnNode.position().y, patrol, spawnNode);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return difficulty;
    }

    // Creates a basic enemy
    private void theDefault(Array<Connection> patrol, Node spawnNode) throws Exception {
        gameObjectCreator.createBasicEnemy(spawnNode.position().x, spawnNode.position().y, patrol, spawnNode);

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
