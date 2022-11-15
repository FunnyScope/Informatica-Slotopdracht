package com.eindopdracht.game.gameobject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eindopdracht.game.control.Handler;

public class Bullet extends GameObject {
    public Bullet(float x, float y, ID id, Handler handler, float orientation, float velX, float velY) {
        super(x, y, id, handler, orientation, velX, velY);
    }

    @Override
    public void draw(SpriteBatch batch) {

    }

    @Override
    public void update(SpriteBatch batch) {
        x += velX;
        y += velY;
    }
}
