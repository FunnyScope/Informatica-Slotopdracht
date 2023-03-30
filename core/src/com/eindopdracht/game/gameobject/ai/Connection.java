package com.eindopdracht.game.gameobject.ai;

import java.util.Objects;

public record Connection(Node firstNode, Node secondNode, float distance) {

    public Connection {
        Objects.requireNonNull(firstNode);
        Objects.requireNonNull(secondNode);
    }

    public Connection(Node firstNode, Node secondNode) {
        this(firstNode, secondNode, firstNode.position().dst(secondNode.position()));
    }


}
