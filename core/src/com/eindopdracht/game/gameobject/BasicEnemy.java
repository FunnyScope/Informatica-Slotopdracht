package com.eindopdracht.game.gameobject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import com.eindopdracht.game.control.Handler;
import com.eindopdracht.game.gameobject.ai.AI;
import com.eindopdracht.game.gameobject.ai.Connection;
import com.eindopdracht.game.gameobject.ai.Node;
import com.eindopdracht.game.gameobject.ai.NormalAI;

public class BasicEnemy extends GameObject implements Enemy {

    private final Player player;
    private final AI ai;


    public BasicEnemy(float x, float y, float orientation, float velX, float velY,
                      ID id, Handler handler,
                      float width, float height,
                      Player player, Array<Connection> patrol, Node spawnNode) {
        super(x, y, orientation, velX, velY, id, handler, width, height);
        this.player = player;
        maxAmmo = 10;
        ammoCount = 10;
        ai = new NormalAI(patrol, spawnNode, this);
    }

    // TODO: graphics
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
            reloadTime = 5;
        }

        if (!ai.followPlayer(player)) {
            Vector2 destination = ai.nextNode().position();
            float angleRadians = (float) Math.atan2(body.getPosition().y - destination.y, body.getPosition().x - destination.x) + (float) Math.PI;
            float speed = 25;
            body.setLinearVelocity((float) Math.cos(angleRadians) * speed, (float) Math.sin(angleRadians) * speed);
        } else {
            float angleRadians = (float) Math.atan2(body.getPosition().y - player.getBody().getPosition().y, body.getPosition().x - player.getBody().getPosition().x) + (float) Math.PI;

            if (ai.shouldShoot(player)) {
                shoot(angleRadians);
            }


            float speed = 25;
            body.setLinearVelocity((float) Math.cos(angleRadians) * speed, (float) Math.sin(angleRadians) * speed);

        }



        body.setTransform(body.getPosition(), 0);
        body.setAngularVelocity(0);
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

        float inaccuracy = (float) (15 / 180f * Math.PI);

        if(ammoCount > 0 && timeRemaining <= 0) {
            ammoCount--;
            handler.hub.getGameObjectCreator().createBullet(body.getPosition().x + bodyCompensation(angleRadians).x,
                    body.getPosition().y + bodyCompensation(angleRadians).y,
                    angleRadians + handler.random.nextFloat() * inaccuracy - inaccuracy / 2,
                    BulletID.enemy);
            timeRemaining = 2;
        }

    }

    @Override
    public int shootingDistance() {
        return 25;
    }

    @Override
    public int followDistance() {
        return 80;
    }

}
