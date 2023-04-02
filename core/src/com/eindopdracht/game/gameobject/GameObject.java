package com.eindopdracht.game.gameobject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.eindopdracht.game.control.Handler;
import com.eindopdracht.game.statuseffect.StatusEffect;
import com.eindopdracht.game.statuseffect.StatusEffectID;


public abstract class GameObject {

    protected float orientation, velX, velY, health, maxHealth = 100, width, height;
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

    // Draw the object
    public abstract void draw(SpriteBatch batch);
    // Update the object positionally etc.
    public abstract void update(float delta);
    // Create the box2D body
    public abstract void createBody(float x, float y);
    // The method to shoot with
    protected abstract void shoot(float angleRadians);

    public void dispose() {

        // TODO: When adding sprites and such, fill in this method with the required disposing
        if(body.getFixtureList().size == 0) return;
        body.destroyFixture(body.getFixtureList().get(0));
    }

    // Compensates for the size of the body
    // TODO: remove, shouldn't be necessary anymore, just provides inaccuracy
    protected Vector2 bodyCompensation(float angleRadians) {
        Vector2 returnVector = new Vector2(0, 0);

        float radius = (float) Math.sqrt(width * width + height * height);

        returnVector.set((float) (radius * Math.cos(angleRadians)), (float) (radius * Math.sin(angleRadians)));

        return returnVector;
    }

    // Getters and setters
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

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(float maxHealth) {
        this.maxHealth = maxHealth;
    }

    public Handler getHandler() {
        return handler;
    }

    // Handles status effects, like bleeding
    // TODO: the systems have been created, implement them
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
        health = MathUtils.clamp(health - damage, 0, maxHealth);
    }



}
