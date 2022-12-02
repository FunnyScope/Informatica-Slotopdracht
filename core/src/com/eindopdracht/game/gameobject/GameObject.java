package com.eindopdracht.game.gameobject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;
import com.eindopdracht.game.control.Handler;
import com.eindopdracht.game.statuseffect.StatusEffect;
import com.eindopdracht.game.statuseffect.StatusEffectID;

public abstract class GameObject {

    protected float x, y, orientation, velX, velY, health;
    protected ID id;
    protected Handler handler;
    protected Body body;
    protected int width, height;

    private Array<StatusEffect> statusEffects;

    public GameObject(float x, float y, float orientation, float velX, float velY, ID id, Handler handler, int width, int height) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
        this.velX = velX;
        this.velY = velY;
        this.id = id;
        this.handler = handler;
        this.width = width;
        this.height = height;
        statusEffects = new Array<>();
        createBody();
    }

    public abstract void draw(SpriteBatch batch);
    public abstract void update(float delta);
    public abstract void createBody();


    public Body getBody() {
        return body;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
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
        health -= damage;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }
}
