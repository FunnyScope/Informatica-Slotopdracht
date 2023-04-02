package com.eindopdracht.game.control;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.eindopdracht.game.gameobject.Bullet;
import com.eindopdracht.game.gameobject.BulletID;
import com.eindopdracht.game.gameobject.GameObject;
import com.eindopdracht.game.gameobject.ID;

//Does the box2D things, handles them
public class WorldHandler implements ContactListener {

    private World world;
    private Handler handler;
    public Array<Bullet> bulletQueue = new Array<>();

    public WorldHandler(Handler handler) {
        world = new World(new Vector2(0, 0), false);
        this.handler = handler;
        world.setContactListener(this);
    }


    public World getWorld() {
        return world;
    }


    public void dispose() {
        world.dispose();
    }

    //The below governs the interaction between two physics bodies
    @Override
    public void beginContact(Contact contact) {

        GameObject objectA = (GameObject) contact.getFixtureA().getBody().getUserData();
        GameObject objectB = (GameObject) contact.getFixtureB().getBody().getUserData();

        Bullet bullet = null;


        if(objectA.getId() == ID.bullet && objectB.getId() != ID.bullet) {
            bullet = (Bullet) objectA;
            if (bullet.getBulletID() == BulletID.enemy && objectB.getId() == ID.player ||
                    bullet.getBulletID() == BulletID.player && objectB.getId() != ID.player) {
                objectB.dealDamage(bullet.damage);
            } else if (bullet.getBulletID() == BulletID.enemy && (objectB.getId() != ID.player
            && objectB.getId() != ID.wall) ||
            bullet.getBulletID() == BulletID.player) {
                bullet = null;
            }
        } else if (objectB.getId() == ID.bullet && objectA.getId() != ID.bullet) {
            bullet = (Bullet) objectB;
            if (bullet.getBulletID() == BulletID.enemy && objectA.getId() == ID.player ||
                    bullet.getBulletID() == BulletID.player && objectA.getId() != ID.player) {
                objectA.dealDamage(bullet.damage);
            } else if (bullet.getBulletID() == BulletID.enemy && (objectA.getId() != ID.player
                    && objectA.getId() != ID.wall) ||
                    bullet.getBulletID() == BulletID.player) {
                bullet = null;
            }
        }

        if(bullet != null) {
            handler.getGameObjects().removeValue(bullet, true);
            bulletQueue.add(bullet);
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

}
