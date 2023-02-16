package com.eindopdracht.game.gameobject.ai;

import com.badlogic.gdx.math.Vector2;

import java.util.Objects;

public record Node(Vector2 position) {

    public Node {
        Objects.requireNonNull(position);
    }

    public Node(float positionX, float positionY) {
        this(new Vector2(positionX, positionY));
    }

}
