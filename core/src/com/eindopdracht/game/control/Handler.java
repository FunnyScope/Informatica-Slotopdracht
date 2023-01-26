package com.eindopdracht.game.control;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.eindopdracht.game.gameobject.*;
import com.eindopdracht.game.screens.GameScreen;

import java.util.Random;


//Handles *all* game objects
public class Handler {

    public final GameScreen hub;
    private WorldHandler worldHandler;
    public final Random random;

    //Array of all game objects
    private Array<GameObject> gameObjects = new Array<>();

    public Handler(GameScreen hub) {
        this.hub = hub;
        worldHandler = new WorldHandler(this);
        random = new Random();
    }


    //Updates all game objects.
    public void update(float delta) {
        for (GameObject gameObject : gameObjects) {
            gameObject.update(delta);
        }
    }

    public void draw(SpriteBatch batch) {
        for (GameObject gameObject : gameObjects) {
            gameObject.draw(batch);
        }
    }



    //Gets an object from the given index
    public GameObject getObjectByIndex(int index) {
        return gameObjects.get(index);
    }

    public Array<GameObject> getGameObjects() {
        return gameObjects;
    }

    //Adds a game object to the list
    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public WorldHandler getWorldHandler() {
        return worldHandler;
    }

    public boolean noEnemiesLeft() {
        for(GameObject gameObject : gameObjects) {
            if (gameObject.getId() != ID.player || gameObject.getId() != ID.bullet || gameObject.getId() != ID.wall) {
                return false;
            }
        }
        return true;
    }
}
