package com.eindopdracht.game.control.level;

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

    private HashMap<ID, Integer> difficultyMap;
    private ID[] enemyIDArray = {ID.basicEnemy};
    protected Random random;


    public Room(GameObjectCreator gameObjectCreator, int x, int y) {
        this.gameObjectCreator = gameObjectCreator;
        this.x = x;
        this.y = y;

        difficultyMap = new HashMap<>();
        difficultyMap.put(ID.basicEnemy, 1);
        random = new Random();
    }

    public abstract void build();


    protected int createRandomEnemy(int difficulty) {

        int chosenEnemy = random.nextInt(enemyIDArray.length);

        switch(enemyIDArray[chosenEnemy]) {

            case basicEnemy -> {
                difficulty -= 1;
                // TODO: finish this method
            }
            default -> {

            }

        }



        return difficulty;

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
}
