package com.eindopdracht.game.gameobject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.eindopdracht.game.control.Handler;

public abstract class GameObject {

<<<<<<< Updated upstream
    private float x, y, orientation;
    private ID id;
    private Handler handler;
=======
    protected float x, y, orientation, velX, velY;
    protected ID id;
    protected Handler handler;


    public GameObject(float x, float y, ID id, Handler handler, float orientation, float velX, float velY) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.handler = handler;
        this.orientation = orientation;
        this.velX = velX;
        this.velY = velY;
    }
>>>>>>> Stashed changes


    public GameObject(float x, float y, ID id, Handler handler, float orientation) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.handler = handler;
        this.orientation = orientation;
    }

    public GameObject(float x, float y, ID id, Handler handler) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.handler = handler;
        orientation = 0;
<<<<<<< Updated upstream
=======
        velX = 0;
        velY = 0;
>>>>>>> Stashed changes
    }

    public abstract void draw(SpriteBatch batch);
    public abstract void update(SpriteBatch batch);
<<<<<<< Updated upstream

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

=======

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

>>>>>>> Stashed changes
    public float getOrientation() {
        return orientation;
    }

    public void setOrientation(float orientation) {
        this.orientation = orientation;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
<<<<<<< Updated upstream
=======

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }
>>>>>>> Stashed changes
}
