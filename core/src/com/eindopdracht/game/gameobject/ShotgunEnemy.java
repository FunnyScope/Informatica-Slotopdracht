package com.eindopdracht.game.gameobject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.eindopdracht.game.control.Handler;

public class ShotgunEnemy extends GameObject {

    private Player player;


    public ShotgunEnemy(float x, float y, float orientation, float velX, float velY, ID id, Handler handler, float width, float height, Player player) {
        super(x, y, orientation, velX, velY, id, handler, width, height);
        this.player = player;
        ammoCount = 2;
        maxAmmo = 2;
        timeRemaining = 0.5f;
        reloadTime = 10;
    }

    private boolean canSeePlayer() {
        return true;
    }

    @Override
    public void draw(SpriteBatch batch) {

    }

    @Override
    public void update(float delta) {
        updateStatusEffects(delta);

        if (ammoCount == 0) {
            reloadTime -= delta;
        } else {
            timeRemaining -= delta;
        }
        if (reloadTime >= 0) {
            ammoCount = maxAmmo;
            reloadTime = 10;
        }



        if (!canSeePlayer()) {
            return;
        }


        float angleRadians = (float) Math.atan2(body.getPosition().y - player.getBody().getPosition().y, body.getPosition().x - player.getBody().getPosition().x) + (float) Math.PI;

        if (!(body.getPosition().dst(player.getBody().getPosition()) > 10.5f)) {


            body.setLinearVelocity(0, 0);

            shoot(angleRadians);

            return;
        }


        float speed = 35;
        body.setLinearVelocity((float) Math.cos(angleRadians) * speed, (float) Math.sin(angleRadians) * speed);

    }

    @Override
    public void createBody(float x, float y) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        body = handler.getWorldHandler().getWorld().createBody(bodyDef);

        PolygonShape hitBox = new PolygonShape();
        hitBox.setAsBox(width /  2f, height / 2f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = hitBox;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.7f;
        fixtureDef.restitution = 0.25f;

        body.createFixture(fixtureDef);

    }

    @Override
    protected void shoot(float angleRadians) {

        float inaccuracy = (float) (1/2 * Math.PI);

        if(ammoCount > 0 && timeRemaining <= 0) {
            ammoCount--;


            for(int i = 0; i < handler.random.nextInt(8) + 4; i++) {
                handler.hub.getGameObjectCreator().createBullet(body.getPosition().x + bodyCompensation(angleRadians).x,
                        body.getPosition().y + bodyCompensation(angleRadians).y,
                        angleRadians + handler.random.nextFloat() * inaccuracy - inaccuracy / 2,
                        BulletID.enemy, 3);
            }

            timeRemaining = 0.5f;
        }

    }


}
