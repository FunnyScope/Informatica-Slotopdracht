package com.eindopdracht.game.gameobject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.eindopdracht.game.control.Handler;
import com.eindopdracht.game.control.PlayerShotPosition;
import com.eindopdracht.game.input.*;

public class Player extends GameObject {
    private int level = 0;
    private InputHandler inputHandler;
    public int damage = 10, maxHealth = 100;
    public float attackSpeed = 1, speed = 50;


    public Player(float x, float y, float orientation, float velX, float velY, ID id, Handler handler, float width, float height) {
        super(x, y, orientation, velX, velY, id, handler, width, height);
        inputHandler = new InputHandler(handler);
        timeRemaining = attackSpeed;
    }


    @Override
    public void draw(SpriteBatch batch) {

    }



    @Override
    public void update(float delta) {

        timeRemaining -= delta;

        updateStatusEffects(delta);

        Vector2 velocity = new Vector2();

        if (inputHandler.getButtonPressed(Button.up)) {
            velocity.y += speed;
        }
        if (inputHandler.getButtonPressed(Button.down)) {
            velocity.y -= speed;
        }
        if (inputHandler.getButtonPressed(Button.left)) {
            velocity.x -= speed;
        }
        if (inputHandler.getButtonPressed(Button.right)) {
            velocity.x += speed;
        }
        if(inputHandler.getButtonPressed(Button.shoot)) {
            shoot(inputHandler.getJoystickPosition());
        }

        body.setLinearVelocity(velocity);
        body.setTransform(body.getPosition(), 0);
        body.setAngularVelocity(0);

    }

    @Override
    public void createBody(float x, float y) {
        // Create the definition of the body, which is apparently necessary for box2D
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
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

    @Override
    protected void shoot(float angleRadians) {
        float inaccuracy = (float) (1/18f * Math.PI);

        if(timeRemaining <= 0) {
            handler.hub.getGameObjectCreator().createBullet(
                    body.getPosition().x + bodyCompensation(angleRadians).x,
                    body.getPosition().y + bodyCompensation(angleRadians).y,
                    angleRadians + handler.random.nextFloat() * inaccuracy - inaccuracy / 2,
                    BulletID.player,
                    damage
            );
            timeRemaining = attackSpeed;
            handler.playerShotPositions.add(new PlayerShotPosition(body.getPosition()));
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
