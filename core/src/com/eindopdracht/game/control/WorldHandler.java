package com.eindopdracht.game.control;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

//Does the box2D things, handles them
public class WorldHandler implements ContactListener {

    private World world;
    private Handler handler;

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

    //The below governs the interaction between
    @Override
    public void beginContact(Contact contact) {

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
