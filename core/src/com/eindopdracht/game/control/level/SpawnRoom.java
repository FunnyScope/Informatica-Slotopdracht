package com.eindopdracht.game.control.level;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.eindopdracht.game.control.GameObjectCreator;
import com.eindopdracht.game.gameobject.ai.Connection;

public class SpawnRoom extends Room {

    public SpawnRoom(GameObjectCreator gameObjectCreator, int x, int y, Vector2 tilePosition) {
        super(gameObjectCreator, x, y, tilePosition);
        difficulty = 0;
    }

    @Override
    public void build() {

    }

    @Override
    public Array<Connection> roomPatrol() {
        return null;
    }

    @Override
    public void createWalls() {

    }
}
