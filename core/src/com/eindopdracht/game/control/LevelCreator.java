package com.eindopdracht.game.control;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.eindopdracht.game.gameobject.GameObject;
import com.eindopdracht.game.gameobject.ID;
import com.eindopdracht.game.control.level.*;
import com.eindopdracht.game.gameobject.Player;
import java.util.Random;

public class LevelCreator {

    private Handler handler;
    private GameObjectCreator gameObjectCreator;
    private Level currentLevel;
    private Random random;

    private Player player;
    private final int roomListSize = 1;
    private Array<Vector2> availableTiles;


    public LevelCreator(Handler handler, GameObjectCreator gameObjectCreator) {
        this.handler = handler;
        this.gameObjectCreator = gameObjectCreator;

        availableTiles = new Array<>();

        random = new Random();
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

        Array<Vector2> tileRemoval = new Array<>();

        for (Vector2 tile: availableTiles) {
            tileRemoval.add(tile);
        }
        for (Vector2 tile: tileRemoval) {
            availableTiles.removeValue(tile, true);
        }
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

        rooms.add(new SpawnRoom(gameObjectCreator, 0, 0, new Vector2(0, 0)));
        availableTiles.addAll(rooms.get(0).getAdjacentTiles());


        int roomDifficulty = difficulty * 5;
        while (roomDifficulty > 0) {
            Room newRoom;
            Vector2 selectedTile = availableTiles.get(random.nextInt(availableTiles.size));
            switch(roomListSize) {
                case 1:
                    newRoom = new BasicRoom(gameObjectCreator,
                            (int) selectedTile.x * 160,
                            (int) selectedTile.y * 160,
                            selectedTile);
                    availableTiles.removeValue(selectedTile, false);
                    rooms.add(newRoom);
                    addNewAdjacentTiles(newRoom.getAdjacentTiles(), rooms);
                    roomDifficulty -= newRoom.getDifficulty();
                    break;
            }
        }


        currentLevel = new Level(rooms, gameObjectCreator, availableTiles);
        currentLevel.buildLevel();


    }

    private void addNewAdjacentTiles(Array<Vector2> tiles, Array<Room> rooms) {

        for (Vector2 tile : tiles) {
            if (!availableTiles.contains(tile, true) && !tileAlreadyOccupied(tile, rooms)) {
                availableTiles.add(tile);
            }
        }
    }

    private boolean tileAlreadyOccupied(Vector2 tile, Array<Room> rooms) {
        for (Room room : rooms) {
            if (room.getTilePosition().x == tile.x && room.getTilePosition().y == tile.y) {
                return true;
            }
        }
        return false;
    }

    public Level getCurrentLevel() {
        return currentLevel;
    }
}
