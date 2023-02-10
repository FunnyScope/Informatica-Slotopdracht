package com.eindopdracht.game.gameobject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.eindopdracht.game.control.Handler;
import com.eindopdracht.game.input.*;

public class Player extends GameObject {
    private int level = 0;
    private InputHandler inputHandler;


    public Player(float x, float y, float orientation, float velX, float velY, ID id, Handler handler, float width, float height) {
        super(x, y, orientation, velX, velY, id, handler, width, height);
        inputHandler = new InputHandler(handler);
    }


    @Override
    public void draw(SpriteBatch batch) {

    }



    @Override
    public void update(float delta) {

        timeRemaining -= delta;


        if (ammoCount > maxAmmo) {
            reloadTime -= delta;
        }

        if (reloadTime >= 0) {
            ammoCount = maxAmmo;
            reloadTime = 5;
        }

        updateStatusEffects(delta);

        Vector2 velocity = new Vector2();



        if (inputHandler.getButtonPressed(Button.up)) {
            velocity.y += 50;
        }
        if (inputHandler.getButtonPressed(Button.down)) {
            velocity.y -= 50;
        }
        if (inputHandler.getButtonPressed(Button.left)) {
            velocity.x -= 50;
        }
        if (inputHandler.getButtonPressed(Button.right)) {
            velocity.x += 50;
        }

        body.setLinearVelocity(velocity);
        body.setTransform(body.getPosition(), 0);
        body.setAngularVelocity(0);


        if (inputHandler.getButtonPressed(Button.shoot)) {
            shoot(inputHandler.getJoystickPosition());
        }

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
        float inaccuracy = 1/18f;

        if(ammoCount > 0 && timeRemaining <= 0) {
            ammoCount--;
            handler.hub.getGameObjectCreator().createBullet(body.getPosition().x + bodyCompensation(angleRadians).x,
                    body.getPosition().y + bodyCompensation(angleRadians).y,
                    angleRadians + handler.random.nextFloat() * inaccuracy - inaccuracy / 2);
            timeRemaining = 2;
        }
    }

    private Vector2 bodyCompensation(float angleRadians) {
        Vector2 returnVector = new Vector2(0, 0);

        float radius = (float) Math.sqrt(width * width + height * height);

        returnVector.set((float) (radius * Math.cos(angleRadians)), (float) (radius * Math.sin(angleRadians)));

        return returnVector;


    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
