package com.eindopdracht.game.control.level;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.eindopdracht.game.control.GameObjectCreator;
import com.eindopdracht.game.gameobject.ID;

import java.util.HashMap;
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
    private ID[] enemyIDArray = {ID.basicEnemy};
    protected Random random;

    protected Vector2 tilePosition;


    public Room(GameObjectCreator gameObjectCreator, int x, int y, Vector2 tilePosition) {
        this.gameObjectCreator = gameObjectCreator;
        this.x = x;
        this.y = y;
        this.tilePosition = tilePosition;

        random = new Random();
    }

    public abstract void build();


    protected int createRandomEnemy(int difficulty) {

        int chosenEnemy = random.nextInt(enemyIDArray.length);

        switch(enemyIDArray[chosenEnemy]) {

            case basicEnemy:
                difficulty -= 1;
                try {
                    gameObjectCreator.createBasicEnemy(random.nextInt(150) - 75 + x, random.nextInt(150) - 75 + y);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            break;

        }



        return difficulty;

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