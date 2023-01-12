package com.eindopdracht.game.control;


import com.badlogic.gdx.utils.Array;
import com.eindopdracht.game.gameobject.Bullet;
import com.eindopdracht.game.gameobject.ID;

// Just to be clear, this is because there appears to be some issue with creating a body whilst creating the
// object in a pool, so I'm just using this as a crutch to avoid that issue
// It's not as good as the native pool class and its derivatives, but that's not important right now.
public class BulletStorage {

    private Array<Bullet> bulletArray;
    private Handler handler;
    private int maximum = 64;


    public BulletStorage(Handler handler) {
        this.handler = handler;
        bulletArray = new Array<>();
    }

    /**
     * @return returns a bullet from the array if available, otherwise creates a new bullet.
     */
    public Bullet obtain() {


        if(bulletArray.isEmpty()) {
            return newObject();
        }

        return bulletArray.pop();

    }

    private Bullet newObject() {
        return new Bullet(0, 0, 0, 0, 0, ID.bullet, handler, 20, 5);
    }

    /**
     * @param bullet a bullet game object, which is returned to the bullet array
     */
    public void free(Bullet bullet) {
        if(bulletArray.size >= maximum) {
            return;
        }

        if (bullet == null) {
            throw new IllegalArgumentException("Bullet can't be null");
        } else {
            bullet.reset();
            bulletArray.add(bullet);
        }
    }

}
