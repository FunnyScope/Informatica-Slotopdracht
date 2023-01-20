package com.eindopdracht.game.control;


import com.badlogic.gdx.utils.Array;
import com.eindopdracht.game.gameobject.Bullet;
import com.eindopdracht.game.gameobject.ID;

// Because of the fact that the bug this was meant to fix wasn't caused by the pool thing, means that
// this is sort of useless. I will, nevertheless, keep it here, because it's not doing any harm. I think.
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
        Bullet bullet = new Bullet(0, 0, 0, 0, 0, ID.bullet, handler, 1.6f, 0.5f);
        bullet.reset();
        return bullet;
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
