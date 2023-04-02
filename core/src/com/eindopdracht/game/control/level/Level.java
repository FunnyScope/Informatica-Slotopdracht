package com.eindopdracht.game.control.level;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.eindopdracht.game.control.GameObjectCreator;

public class Level {

    private Array<Room> rooms;
    private GameObjectCreator gameObjectCreator;
    private Array<Vector2> adjacentTiles;

    public Level(Array<Room> rooms, GameObjectCreator gameObjectCreator, Array<Vector2> adjacentTiles) {
        this.rooms = rooms;
        this.gameObjectCreator = gameObjectCreator;
        this.adjacentTiles = adjacentTiles;
    }

    public void buildLevel() {

        for (Room room: rooms) {
            room.build();
            for (Vector2 tile : adjacentTiles) {
                if (room.getAdjacentTiles().contains(tile, false)) {
                    placeWall(tile, room);
                }
            }
        }



    }

    public Array<Room> getRooms() {
        return rooms;
    }

    private void placeWall(Vector2 tile, Room room) {

        Vector2 roomTile = room.getTilePosition();

        if (tile.x > roomTile.x) {
            gameObjectCreator.createWall(tile.x * room.size - 76.8f, tile.y * room.size, 3.2f, room.size + 3.2f);
            return;
        }
        if (tile.x < roomTile.x) {
            gameObjectCreator.createWall(tile.x * room.size + 76.8f, tile.y * room.size, 3.2f, room.size + 3.2f);
            return;
        }
        if (tile.y > roomTile.y) {
            gameObjectCreator.createWall(tile.x * room.size, tile.y * room.size - 76.8f, room.size + 3.2f, 3.2f);
            return;
        }
        if (tile.y < roomTile.y) {
            gameObjectCreator.createWall(tile.x * room.size, tile.y * room.size + 76.8f, room.size + 3.2f, 3.2f);
        }


    }
}
