package com.eindopdracht.game.control;

import com.eindopdracht.game.gameobject.*;
import java.util.LinkedList;

//Handles *all* game objects
public class Handler {

    //Array of all game objects
    private LinkedList<GameObject> gameObjects = new LinkedList<>();

    public Handler() {

    }

    //Updates all game objects.
    public void update() {
        for (GameObject gameObject : gameObjects) {
            gameObject.update();
        }
    }

    //Draws all game objects
    public void draw() {
        for (GameObject gameObject : gameObjects) {
            gameObject.draw();
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
