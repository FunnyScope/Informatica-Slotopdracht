package com.eindopdracht.game.gameobject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Pool;
import com.eindopdracht.game.control.Handler;
public class Bullet extends GameObject implements Pool.Poolable {
    public boolean alive;

    public Bullet(float x, float y, float orientation, float velX, float velY, ID id, Handler handler, int width, int height) {
        super(x, y, orientation, velX, velY, id, handler, width, height);
    }

    @Override
    public void draw(SpriteBatch batch) {

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void createBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        bodyDef.angle = orientation;

        body = handler.getWorldHandler().getWorld().createBody(bodyDef);

        PolygonShape hitBox = new PolygonShape();
        hitBox.setAsBox(width, height);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = hitBox;
        fixtureDef.restitution = 0.2f;
        fixtureDef.density = 0.7f;
        fixtureDef.friction = 0.3f;
        fixtureDef.isSensor = true;

        body.createFixture(fixtureDef);
    }

    @Override
    public void reset() {
        alive = false;
    }

    public void init(float x, float y, float orientation) {
        alive = true;
        this.x = x;
        this.y = y;
        this.orientation = orientation;
        createBody();
    }

}
