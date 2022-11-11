package com.eindopdracht.game.control;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eindopdracht.game.EindOpdracht;
import com.eindopdracht.game.gameobject.*;
import java.util.LinkedList;

//Handles *all* game objects
public class Handler {



    //Array of all game objects
    private LinkedList<GameObject> gameObjects = new LinkedList<>();

    public Handler() {

    }

    //Updates all game objects.
    public void update(SpriteBatch batch) {
        for (GameObject gameObject : gameObjects) {
            gameObject.update(batch);
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

    //TODO: figure out if this can be done the cool, smart way
    //Which probably wouldn't work that terrifically well, but it'd be cool
    public void addGameObject() {

    }


}
