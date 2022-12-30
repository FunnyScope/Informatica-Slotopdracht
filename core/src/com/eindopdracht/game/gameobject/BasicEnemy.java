package com.eindopdracht.game.gameobject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.eindopdracht.game.control.Handler;

public class BasicEnemy extends GameObject {

    private final float speed = 10;
    private Player player;

    public BasicEnemy(float x, float y, float orientation, float velX, float velY, ID id, Handler handler, int width, int height, Player player) {
        super(x, y, orientation, velX, velY, id, handler, width, height);
        this.player = player;

    }

    @Override
    public void draw(SpriteBatch batch) {

    }

    private boolean canSeePlayer() {
        // TODO: Create walls and other obstacles which impede line of sight and bullets
        return true;
    }

    @Override
    public void update(float delta) {
        // TODO: AI things
        // For now, we merely make this guy move to the player
        // It only requires some basic vector math, so it'll suffice for testing

        if(!canSeePlayer()) {
            return;
        }


        Vector2 posDiff = new Vector2(player.getBody().getPosition().x - body.getPosition().x, player.getBody().getPosition().y - body.getPosition().y);
        float angleRadians =  (float) Math.atan2(body.getPosition().y - player.getBody().getPosition().y, body.getPosition().x - player.getBody().getPosition().x);
        if(posDiff.x < 0) {
            if (posDiff.y < 0) {
                angleRadians += Math.PI;
            } else {
                angleRadians += 0.5f * Math.PI;
            }
        } else if (posDiff.y < 0) {
            angleRadians += 1.5f * Math.PI;
        }

        if (!(body.getPosition().dst(player.getBody().getPosition()) > 200)) {
            //TODO: Create system to fire bullets. Might've been useful to do this beforehand.
            body.setLinearVelocity(0, 0);
            return;
        }



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
}
