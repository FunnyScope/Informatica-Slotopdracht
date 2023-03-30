package com.eindopdracht.game.control;

import com.badlogic.gdx.math.Vector2;

public class PlayerShotPosition {

    public final Vector2 position;
    private int framesRemaining = 2;

    public PlayerShotPosition(Vector2 position) {
        this.position = position;
    }

    public boolean shouldBeRemoved() {
        framesRemaining--;
        return framesRemaining <= 0;
    }
}
