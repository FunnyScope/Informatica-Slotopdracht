package com.eindopdracht.game.gameobject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.eindopdracht.game.control.Handler;

public class Player extends GameObject {
    private int level = 0;


    public Player(float x, float y, float orientation, float velX, float velY, ID id, Handler handler, int width, int height) {
        super(x, y, orientation, velX, velY, id, handler, width, height);
    }


    @Override
    public void draw(SpriteBatch batch) {

    }



    @Override
    public void update(float delta) {

        updateStatusEffects(delta);

        body.setLinearVelocity(velX, velY);

    }

    @Override
    public void createBody(float x, float y) {
        // Create the definition of the body, which is apparently necessary for box2D
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(x, y);

        // Create the body
        body = handler.getWorldHandler().getWorld().createBody(bodyDef);

        // Create the shape
        PolygonShape hitBox = new PolygonShape();
        hitBox.setAsBox(width /  2f, height / 2f);

        // Create the fixtureDef, and below that the body's fixture.
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = hitBox;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.7f;
        fixtureDef.restitution = 0.25f;

        body.createFixture(fixtureDef);


    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
