package com.eindopdracht.game.gameobject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.eindopdracht.game.control.Handler;
public class Bullet extends GameObject {
    public boolean alive;
    public float damage = 5;
    private BulletID bulletID;

    public Bullet(float x, float y, float orientation, float velX, float velY, ID id, Handler handler, float width, float height, BulletID bulletID) {
        super(x, y, orientation, velX, velY, id, handler, width, height);
        this.bulletID = bulletID;
    }

    @Override
    public void draw(SpriteBatch batch) {

    }

    @Override
    public void update(float delta) {
        body.setTransform(body.getPosition(), orientation);
        float speed = 50000;
        body.setLinearVelocity((float) (Math.cos(orientation) * speed), (float) (Math.sin(orientation) * speed));
    }

    @Override
    public void createBody(float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);
        bodyDef.angle = orientation;

        body = handler.getWorldHandler().getWorld().createBody(bodyDef);

        PolygonShape hitBox = new PolygonShape();
        hitBox.setAsBox(width / 2f, height / 2f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = hitBox;
        fixtureDef.restitution = 0.25f;
        fixtureDef.density = 0.7f;
        fixtureDef.friction = 0.3f;
        fixtureDef.isSensor = true;

        body.createFixture(fixtureDef);

        //Necessary for the WorldHandler
        body.isBullet();


    }


    @Override
    protected void shoot(float angleRadians) {
        //Bullet shoots bullet? Hell yeah
    }


    public void reset() {
        alive = false;
        damage = 5;
        bulletID = BulletID.player;
        body.setLinearVelocity(0, 0);
        body.setAngularVelocity(0);
        if(body.getFixtureList().isEmpty()) return;
        body.destroyFixture(body.getFixtureList().get(0));
    }

    public void init(float x, float y, float orientation, BulletID bulletID) {
        alive = true;
        this.orientation = orientation;
        this.bulletID = bulletID;
        damage = 5;
        createBody(x, y);
        body.setUserData(this);
    }
    public void init(float x, float y, float orientation, BulletID bulletID, float damage) {
        alive = true;
        this.orientation = orientation;
        this.bulletID = bulletID;
        this.damage = damage;
        createBody(x, y);
        body.setUserData(this);
    }

    public BulletID getBulletID() {
        return bulletID;
    }
}
