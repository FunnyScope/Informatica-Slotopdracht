package com.eindopdracht.game.control.level;

import com.eindopdracht.game.control.GameObjectCreator;

public class SpawnRoom extends Room {

    public SpawnRoom(GameObjectCreator gameObjectCreator, int x, int y) {
        super(gameObjectCreator, x, y);
        difficulty = 0;
    }

    @Override
    public void build() {

    }
}
