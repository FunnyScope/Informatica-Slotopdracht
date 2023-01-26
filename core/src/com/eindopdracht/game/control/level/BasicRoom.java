package com.eindopdracht.game.control.level;

import com.badlogic.gdx.math.Vector2;
import com.eindopdracht.game.control.GameObjectCreator;

public class BasicRoom extends Room {
    public BasicRoom(GameObjectCreator gameObjectCreator, int x, int y, Vector2 tilePosition) {
        super(gameObjectCreator, x, y, tilePosition);
        difficulty = 5;
    }

    @Override
    public void build() {
        for (int i = 0; i < random.nextInt(5); i++) {
            gameObjectCreator.createWall(random.nextInt(134) - 67 + x,
                    random.nextInt(134) - 67 + y,
                    16,
                    16);
        }


        int enemyDifficulty = difficulty;

        while (enemyDifficulty > 0) {
            enemyDifficulty = createRandomEnemy(enemyDifficulty);
        }



    }
}
