package com.eindopdracht.game.gameobject.ai;


import com.badlogic.gdx.utils.Array;

public class NormalAI implements AI {

    private Array<Connection> levelLayout;
    private Array<Connection> patrol;

    public NormalAI(Array<Connection> levelLayout, Array<Connection> patrol) {
        this.levelLayout = levelLayout;
        this.patrol = patrol;
    }



    @Override
    public Node nextNode() {
        return null;
    }

    @Override
    public boolean shouldShoot() {
        return false;
    }
}
