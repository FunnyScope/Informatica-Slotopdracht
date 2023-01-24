package com.eindopdracht.game.control.level;

import com.badlogic.gdx.utils.Array;
import com.eindopdracht.game.control.GameObjectCreator;

public class Level {

    private Array<Room> rooms;
    private GameObjectCreator gameObjectCreator;

    public Level(Array<Room> rooms, GameObjectCreator gameObjectCreator) {
        this.rooms = rooms;
        this.gameObjectCreator = gameObjectCreator;
    }

    public void buildLevel() {
        for (Room room: rooms) {
            room.build();
        }

        gameObjectCreator.createWall(-83.2f, 0, 3.2f, 163.2f);
        gameObjectCreator.createWall(83.2f, 0, 3.2f, 163.2f);
        gameObjectCreator.createWall(0, -83.2f, 163.2f, 3.2f);
        gameObjectCreator.createWall(0, 83.2f, 163.2f, 3.2f);

    }

    public Array<Room> getRooms() {
        return rooms;
    }
}
