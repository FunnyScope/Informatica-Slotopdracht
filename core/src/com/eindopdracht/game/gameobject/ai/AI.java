package com.eindopdracht.game.gameobject.ai;

import com.badlogic.gdx.math.Vector2;
import com.eindopdracht.game.gameobject.GameObject;

public interface AI {

    // Provides the next goal node for the game object
    public abstract Node nextNode();
    // Tells the object whether it should shoot
    public abstract boolean shouldShoot(GameObject player);
    // Tells the object if it should follow the player
    public abstract boolean followPlayer(GameObject player);
}
