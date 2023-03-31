package com.eindopdracht.game.gameobject.obstacle;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.eindopdracht.game.control.Handler;
import com.eindopdracht.game.gameobject.GameObject;
import com.eindopdracht.game.gameobject.ID;

//This thing shouldn't do anything, ever.
public class Wall extends GameObject {

    public Wall(float x, float y, float orientation, float velX, float velY, ID id, Handler handler, float width, float height) {
        super(x, y, orientation, velX, velY, id, handler, width, height);
    }

    @Override
    public void draw(SpriteBatch batch) {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void createBody(float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(x, y);

        body = handler.getWorldHandler().getWorld().createBody(bodyDef);

        PolygonShape hitBox = new PolygonShape();
        hitBox.setAsBox(width / 2, height / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.restitution = 0;
        fixtureDef.density = 1;
        fixtureDef.friction = 0.75f;
        fixtureDef.shape = hitBox;
        body.createFixture(fixtureDef);

        body.setUserData(this);
    }

    @Override
    protected void shoot(float angleRadians) {

    }
}
