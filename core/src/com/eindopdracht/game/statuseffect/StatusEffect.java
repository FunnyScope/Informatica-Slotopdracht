package com.eindopdracht.game.statuseffect;

import com.eindopdracht.game.gameobject.GameObject;

public abstract class StatusEffect {

    private float damage;
    private int tickAmount;

    public StatusEffect(int tickAmount) {
        this.tickAmount = tickAmount;
    }

    public StatusEffect(int tickAmount, float damage) {
        this.tickAmount = tickAmount;
        this.damage = damage;
    }

    public abstract void update(GameObject gameObject);

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public int getTickAmount() {
        return tickAmount;
    }

    public void setTickAmount(int tickAmount) {
        this.tickAmount = tickAmount;
    }
}
