package com.eindopdracht.game.gameobject.ai;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.eindopdracht.game.control.PlayerShotPosition;
import com.eindopdracht.game.gameobject.Enemy;
import com.eindopdracht.game.gameobject.GameObject;

import java.util.Random;

import static com.badlogic.gdx.math.MathUtils.random;

public class NormalAI implements AI {

    private final Array<Connection> patrol;
    private Node currentGoal;
    private final GameObject gameObject;
    private final int distanceBias;
    private boolean isFollowingPlayer = false;


    public NormalAI(Array<Connection> patrol, Node spawnNode, GameObject gameObject) {
        this.patrol = patrol;
        currentGoal = spawnNode;
        this.gameObject = gameObject;
        distanceBias = random.nextInt(10) + 5;
    }

    @Override
    public Node nextNode() {
        if(gameObject.getBody().getPosition().dst(currentGoal.position()) < distanceBias) {

            for(Connection connection : patrol) {
                if (connection.firstNode() == currentGoal) {
                    currentGoal = connection.secondNode();
                    break;
                }
            }

        }

        return currentGoal;
    }

    @Override
    public boolean shouldShoot(GameObject player) {
        try {
            if (!isFollowingPlayer) return false;

            if (gameObject instanceof Enemy) {
                // Best statement ever. ;-;
                return ((Enemy) gameObject).shootingDistance() >= player.getBody().getPosition().dst(gameObject.getBody().getPosition());
            }
            throw new RuntimeException("Game object isn't an enemy, I think. How? I do not know");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean followPlayer(GameObject player) {
        try {
            if (gameObject instanceof Enemy) {
                if (player.getBody().getPosition().dst(gameObject.getBody().getPosition()) > ((Enemy) gameObject).followDistance()) {
                    return false;
                }
                if (isFollowingPlayer) return true;
            } else {
                throw new RuntimeException("Game object isn't an enemy, I think. How? I do not know");
            }
            // Practically infinity
            Vector2 closestShot = new Vector2(1147483647, 1147483647);
            for (PlayerShotPosition playerShotPosition : gameObject.getHandler().playerShotPositions) {
                if (player.getBody().getPosition().dst(playerShotPosition.position) < player.getBody().getPosition().dst(closestShot)) {
                    closestShot = playerShotPosition.position;
                }
            }

            isFollowingPlayer = gameObject.getBody().getPosition().dst(closestShot) < ((Enemy) gameObject).followDistance();
            return gameObject.getBody().getPosition().dst(closestShot) < ((Enemy) gameObject).followDistance();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
