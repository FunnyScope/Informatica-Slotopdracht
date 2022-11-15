package com.eindopdracht.game.gameobject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eindopdracht.game.control.Handler;

public class Player extends GameObject {
    private int level = 0, health = 100;

    public Player(float x, float y, ID id, Handler handler, float orientation) {
        super(x, y, id, handler, orientation, 0, 0);
    }

    @Override
    public void draw(SpriteBatch batch) {

    }

    @Override
    public void update(SpriteBatch batch) {
        x += velX;
        y += velY;


    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
