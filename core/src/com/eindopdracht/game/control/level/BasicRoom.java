package com.eindopdracht.game.control.level;

import com.eindopdracht.game.control.GameObjectCreator;

public class BasicRoom extends Room {
    public BasicRoom(GameObjectCreator gameObjectCreator, int x, int y) {
        super(gameObjectCreator, x, y);
        difficulty = 5;
    }

    @Override
    public void build() {
        int enemyDifficulty = difficulty;

        while (enemyDifficulty > 0) {

        }

    }
}
