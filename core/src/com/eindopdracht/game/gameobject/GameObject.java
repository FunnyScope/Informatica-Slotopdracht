package com.eindopdracht.game.gameobject;

import com.eindopdracht.game.control.Handler;

public abstract class GameObject {

    private float x, y;
    private ID id;
    private Handler handler;

    public GameObject(float x, float y, ID id, Handler handler) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.handler = handler;
    }

    public abstract void draw();
    public abstract void update();

}
