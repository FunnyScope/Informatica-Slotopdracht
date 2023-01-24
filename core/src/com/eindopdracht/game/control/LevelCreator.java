package com.eindopdracht.game.control;

import com.badlogic.gdx.utils.Array;
import com.eindopdracht.game.gameobject.GameObject;
import com.eindopdracht.game.gameobject.ID;
import com.eindopdracht.game.control.level.*;
import com.eindopdracht.game.gameobject.Player;

public class LevelCreator {

    private Handler handler;
    private GameObjectCreator gameObjectCreator;
    private Level currentLevel;

    private Player player;


    public LevelCreator(Handler handler, GameObjectCreator gameObjectCreator) {
        this.handler = handler;
        this.gameObjectCreator = gameObjectCreator;

    }

    public void clearLevel() {
        Array<GameObject> removalList = new Array<>();
        for(GameObject gameObject : handler.getGameObjects()) {
            if (gameObject.getId() != ID.player) {
                removalList.add(gameObject);
            }
        }
        for(GameObject gameObject : removalList) {
            gameObject.dispose();
            handler.getGameObjects().removeValue(gameObject, true);
        }

        currentLevel = null;
    }

    public void createLevel(int difficulty) {

        if (difficulty == 1) {
            gameObjectCreator.createPlayer(0, 0);
            player = (Player) handler.getObjectByIndex(0);
        } else {
            player.setX(0);
            player.setY(0);
        }

        Array<Room> rooms = new Array<>();

        rooms.add(new SpawnRoom(gameObjectCreator, 0, 0));


        currentLevel = new Level(rooms, gameObjectCreator);
        currentLevel.buildLevel();


    }

}
