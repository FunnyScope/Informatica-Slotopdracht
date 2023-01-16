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

    public final int levelWidth = 1920, levelHeight = 1080;


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

        // So the best idea for this is to probably just do it with rooms that get tiled? Or areas.
        // Something like that
        // And just use the difficulty for that

        if(difficulty == 1) {
            gameObjectCreator.createPlayer(0, 32);
        }

        while (difficulty > 0) {

            int selectedID = random.nextInt(enemyIDArray.length);

            difficulty = createSelectedObject(difficulty, selectedID);

        }

        //TODO: Create obstacles and such, aside from level boundaries.


        gameObjectCreator.createWall(levelWidth * -0.5f, levelHeight / 2f, 32, levelHeight);
        gameObjectCreator.createWall(levelWidth * 0.5f, levelHeight / 2f, 32, levelHeight);
        gameObjectCreator.createWall(0, levelHeight, levelWidth, 32);
        gameObjectCreator.createWall(0, 0, levelWidth, 32);

    }

    private int createSelectedObject(int difficulty, int selectID) {

        try {
            switch(enemyIDArray[selectID]) {
                case basicEnemy:
                    gameObjectCreator.createBasicEnemy(random.nextInt(levelWidth) - levelWidth * 0.5f, random.nextInt(levelHeight) - levelHeight * 0.5f);
                    difficulty -= 1;
                    break;


            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        return difficulty;
    }
}
