package com.eindopdracht.game.control.level;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.eindopdracht.game.control.GameObjectCreator;
import com.eindopdracht.game.gameobject.ai.Connection;
import com.eindopdracht.game.gameobject.ai.Node;

import static com.badlogic.gdx.math.MathUtils.random;

public class BasicRoom extends Room {
    public BasicRoom(GameObjectCreator gameObjectCreator, int x, int y, Vector2 tilePosition) {
        super(gameObjectCreator, x, y, tilePosition);
        difficulty = 5;
    }

    // Builds the room
    @Override
    public void build() {
        createWalls();

        Array<Connection> patrol = roomPatrol();

        int enemyDifficulty = difficulty;

        while (enemyDifficulty > 0) {
            enemyDifficulty = createRandomEnemy(enemyDifficulty, patrol);
        }



    }

    // TODO: Add room patrols to game objects, as well as the AI that will handle it.
    @Override
    public Array<Connection> roomPatrol() {

        Array<Node> nodes = new Array<>();

        nodes.add(
                new Node((tilePosition.x - 0.5f) * 160 + 5, (tilePosition.y - 0.5f) * 160 + 5),
                new Node((tilePosition.x - 0.5f) * 160 + 5, (tilePosition.y - 0.5f) * 160 + 155),
                new Node((tilePosition.x - 0.5f) * 160 + 155, (tilePosition.y - 0.5f) * 160 + 155),
                new Node((tilePosition.x - 0.5f) * 160 + 155, (tilePosition.y - 0.5f) * 160 + 5)
        );

        Array<Connection> connections = new Array<>();

        for(int i = 0; i < 4; i++) {
            connections.add(new Connection(nodes.get(i), nodes.get((i == 3) ? (0) : (i + 1))));
        }


        return connections;
    }

    // Creates walls, and removes them from the tile list.
    @Override
    public void createWalls() {
        Array<Vector2> tileRemovalArray = new Array();
        int wallTracker = random.nextInt(10) + 5;
        for (Vector2 internalTile : roomDistributionTiles) {
            if (internalTile.x != 1 && internalTile.x != 16 && internalTile.y != 1 && internalTile.y != 16) {
                if (random.nextInt(110) <= 10 && wallTracker > 0) {
                    wallTracker--;
                    gameObjectCreator.createWall((internalTile.x - 1) * 10 + 5 + (tilePosition.x - 0.5f) * 160,
                            (internalTile.y - 1) * 10 + 5 + (tilePosition.y - 0.5f) * 160,
                            10, 10);
                    tileRemovalArray.add(internalTile);
                }
            }
        }

        for(Vector2 tileScheduledForRemoval : tileRemovalArray) {
            roomDistributionTiles.removeValue(tileScheduledForRemoval, true);
        }

    }
}
