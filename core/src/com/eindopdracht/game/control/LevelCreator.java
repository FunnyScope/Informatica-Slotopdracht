package com.eindopdracht.game.control;

import com.badlogic.gdx.utils.Array;
import com.eindopdracht.game.gameobject.GameObject;
import com.eindopdracht.game.gameobject.ID;

import java.util.HashMap;
import java.util.Random;

public class LevelCreator {

    private Handler handler;
    private GameObjectCreator gameObjectCreator;

    private Random random;
    private HashMap<ID, Integer> difficultyMap;
    private ID[] enemyIDArray = {ID.basicEnemy};

    public final int levelWidth = 1200, levelHeight = 900;


    public LevelCreator(Handler handler, GameObjectCreator gameObjectCreator) {
        this.handler = handler;
        this.gameObjectCreator = gameObjectCreator;

        random = new Random();
        difficultyMap = new HashMap<>();
        difficultyMap.put(ID.basicEnemy, 1);
    }

    public void clearLevel() {
        Array<GameObject> removalList = new Array<>();
        for(GameObject gameObject : handler.getGameObjects()) {
            if (gameObject.getId() != ID.player) {
                removalList.add(gameObject);
            }
        }
        for(GameObject gameObject : removalList) {
            handler.getGameObjects().removeValue(gameObject, true);
        }
    }

    public void createLevel(int difficulty) {

        while (difficulty > 0) {

            int selectedID = random.nextInt(enemyIDArray.length);

            difficulty = createSelectedObject(difficulty, selectedID);

        }

        //TODO: do something with the walls.



    }

    private int createSelectedObject(int difficulty, int selectID) {

        try {
            switch(enemyIDArray[selectID]) {
                case basicEnemy:
                    gameObjectCreator.createBasicEnemy(random.nextInt(levelWidth), random.nextInt(levelHeight));
                    difficulty -= 1;
                    break;


            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return difficulty;
    }
}
