package com.eindopdracht.game.gameobject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
<<<<<<< Updated upstream
import com.eindopdracht.game.control.Handler;

public class Bullet extends GameObject {
    public Bullet(float x, float y, ID id, Handler handler, float orientation, float velX, float velY) {
        super(x, y, id, handler, orientation, velX, velY);
=======
import com.badlogic.gdx.utils.Pool;
import com.eindopdracht.game.control.Handler;

public class Bullet extends GameObject implements Pool.Poolable {
    public boolean alive;

    public Bullet(float x, float y, float orientation, float velX, float velY, ID id, Handler handler, int width, int height) {
        super(x, y, orientation, velX, velY, id, handler, width, height);
>>>>>>> Stashed changes
    }

    @Override
    public void draw(SpriteBatch batch) {

    }

    @Override
<<<<<<< Updated upstream
    public void update(SpriteBatch batch) {
        x += velX;
        y += velY;
    }
=======
    public void update(float delta) {

    }

    @Override
    public void createBody() {

    }

    @Override
    public void reset() {

    }

    public void init(float x, float y) {
        alive = true;
        this.x = x;
        this.y = y;
    }

>>>>>>> Stashed changes
}
