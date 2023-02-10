package com.eindopdracht.game.gameobject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.eindopdracht.game.control.Handler;
import com.eindopdracht.game.statuseffect.StatusEffect;
import com.eindopdracht.game.statuseffect.StatusEffectID;


public abstract class GameObject {

    protected float orientation, velX, velY, health, width, height;
    protected ID id;
    protected Handler handler;
    // We use the body's native position vector to track the position of the game object.
    protected Body body;
    protected int maxAmmo = 30, ammoCount = 30;
    protected float timeRemaining = 2;
    protected float reloadTime = 5;

    private Array<StatusEffect> statusEffects;

    public GameObject(float x, float y, float orientation, float velX, float velY, ID id, Handler handler, float width, float height) {

        this.orientation = orientation;
        this.velX = velX;
        this.velY = velY;
        this.id = id;
        this.handler = handler;
        this.width = width;
        this.height = height;
        health = 100;
        statusEffects = new Array<>();
        createBody(x, y);
        body.setUserData(this);
    }

    public abstract void draw(SpriteBatch batch);
    public abstract void update(float delta);
    public abstract void createBody(float x, float y);
    protected abstract void shoot(float angleRadians);

    public void dispose() {
        // TODO: When adding sprites and such, fill in this method with the required disposing
        body.destroyFixture(body.getFixtureList().get(0));
    }


    public Body getBody() {
        return body;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getX() {
        return body.getPosition().x;
    }

    public void setX(float x) {
        body.getPosition().x = x;
    }

    public float getY() {
        return body.getPosition().y;
    }

    public void setY(float y) {
        body.getPosition().y = y;
    }

    public float getOrientation() {
        return orientation;
    }

    public void setOrientation(float orientation) {
        this.orientation = orientation;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public int getMaxAmmo() {
        return maxAmmo;
    }

    public void setMaxAmmo(int maxAmmo) {
        this.maxAmmo = maxAmmo;
    }

    public int getAmmoCount() {
        return ammoCount;
    }

    public void setAmmoCount(int ammoCount) {
        this.ammoCount = ammoCount;
    }

    public void addStatusEffect(StatusEffect statusEffect) {
        statusEffects.add(statusEffect);
    }
    public void removeStatusEffect(int index) {
        statusEffects.removeIndex(index);
    }
    public void removeStatusEffect(StatusEffect statusEffect) {
        statusEffects.removeValue(statusEffect, true);
    }
    public boolean hasStatusEffect(StatusEffectID id) {
        for (StatusEffect statusEffect:
             statusEffects) {
            if(statusEffect.id == id)
                return true;
        }
        return false;
    }
    public void removeAllStatusEffects() {
        statusEffects.clear();
    }
    protected void updateStatusEffects(float delta) {
        for (StatusEffect statusEffect:
             statusEffects) {
            statusEffect.update(this, delta);
        }
    }

    //TODO: create armour mechanics and such
    public void dealDamage(float damage) {
        if(hasStatusEffect(StatusEffectID.BulletImmunity))
            return;
        health -= damage;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }
}
