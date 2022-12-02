package com.eindopdracht.game.statuseffect;

import com.eindopdracht.game.gameobject.GameObject;

public abstract class StatusEffect {

    protected float accumulator;
    protected float damage;
    protected float tickAmount;
    public final StatusEffectID id;

    public StatusEffect(int tickAmount, StatusEffectID id) {
        this.tickAmount = tickAmount;
        this.id = id;
    }

    public StatusEffect(int tickAmount, float damage, StatusEffectID id) {
        this.tickAmount = tickAmount;
        this.damage = damage;
        this.id = id;
    }

    public abstract void update(GameObject gameObject, float delta);

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getTickAmount() {
        return tickAmount;
    }

    public void setTickAmount(int tickAmount) {
        this.tickAmount = tickAmount;
    }

}
