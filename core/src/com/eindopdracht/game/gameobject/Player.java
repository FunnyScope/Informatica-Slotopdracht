package com.eindopdracht.game.gameobject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
<<<<<<< Updated upstream
=======
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
>>>>>>> Stashed changes
import com.eindopdracht.game.control.Handler;

public class Player extends GameObject {
    private int level = 0, health = 100;

<<<<<<< Updated upstream
    public Player(float x, float y, ID id, Handler handler, float orientation) {
        super(x, y, id, handler, orientation, 0, 0);
    }

=======
    public Player(float x, float y, float orientation, float velX, float velY, ID id, Handler handler, int width, int height) {
        super(x, y, orientation, velX, velY, id, handler, width, height);
    }


>>>>>>> Stashed changes
    @Override
    public void draw(SpriteBatch batch) {

    }

    @Override
<<<<<<< Updated upstream
    public void update(SpriteBatch batch) {
        x += velX;
        y += velY;


=======
    public void update(float delta) {



    }

    @Override
    public void createBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        body = handler.getWorldHandler().getWorld().createBody(bodyDef);

        PolygonShape hitBox = new PolygonShape();
        hitBox.setAsBox(width, height);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = hitBox;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.7f;
        fixtureDef.restitution = 0.25f;

        body.createFixture(fixtureDef);
>>>>>>> Stashed changes
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
