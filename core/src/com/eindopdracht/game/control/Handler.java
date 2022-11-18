package com.eindopdracht.game.control;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======
import com.badlogic.gdx.utils.Array;
>>>>>>> Stashed changes
import com.eindopdracht.game.EindOpdracht;
=======
import com.badlogic.gdx.utils.Array;
>>>>>>> Stashed changes
import com.eindopdracht.game.gameobject.*;


//Handles *all* game objects
public class Handler {

<<<<<<< Updated upstream
<<<<<<< Updated upstream

=======
    private WorldHandler worldHandler;
>>>>>>> Stashed changes
=======
    private WorldHandler worldHandler;
>>>>>>> Stashed changes

    //Array of all game objects
    private Array<GameObject> gameObjects = new Array<>();

<<<<<<< Updated upstream
    public Handler() {
        worldHandler = new WorldHandler();
    }

    //Updates all game objects.
    public void update(SpriteBatch batch) {
        for (GameObject gameObject : gameObjects) {
            gameObject.update(batch);
=======

    public Handler() {
        worldHandler = new WorldHandler(this);
    }

    //Updates all game objects.
    public void update(float delta) {
        for (GameObject gameObject : gameObjects) {
            gameObject.update(delta);
>>>>>>> Stashed changes
        }
    }

    //Draws all game objects
    public void draw(SpriteBatch batch) {
        for (GameObject gameObject : gameObjects) {
            gameObject.draw(batch);
        }
    }

    //Gets an object from the given index
    public GameObject getObjectByIndex(int index) {
        return gameObjects.get(index);
    }

    //Adds a game object to the list
    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public WorldHandler getWorldHandler() {
        return worldHandler;
    }
}
