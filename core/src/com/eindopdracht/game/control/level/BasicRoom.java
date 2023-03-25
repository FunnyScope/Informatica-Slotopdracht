package com.eindopdracht.game.control.level;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.eindopdracht.game.control.GameObjectCreator;
import com.eindopdracht.game.gameobject.ai.Connection;

public class BasicRoom extends Room {
    public BasicRoom(GameObjectCreator gameObjectCreator, int x, int y, Vector2 tilePosition) {
        super(gameObjectCreator, x, y, tilePosition);
        difficulty = 5;
    }

    @Override
    public void build() {
        createWalls();


        int enemyDifficulty = difficulty;

        while (enemyDifficulty > 0) {
            enemyDifficulty = createRandomEnemy(enemyDifficulty);
        }



    }

    @Override
    public Array<Connection> roomPatrol() {
        return null;
    }

    @Override
    public void createWalls() {
        int wallTracker = random.nextInt(10) + 5;
        for (Vector2 internalTile : roomDistributionTiles) {
            if (internalTile.x != (1 + tilePosition.x * 10) &&
                    internalTile.x != (16 + tilePosition.x * 10)  &&
                    internalTile.y != (1 + tilePosition.y * 10) &&
                    internalTile.y != (16 + tilePosition.y * 10)) {
                if (random.nextInt(110) <= 10 && wallTracker > 0) {
                    wallTracker--;
                    gameObjectCreator.createWall((internalTile.x - 1) * 10 + 5, (internalTile.y - 1) * 10 + 5, 10, 10);
                    System.out.println(new Vector2(internalTile.x, internalTile.y));
                }
            }
        }
    }
}
