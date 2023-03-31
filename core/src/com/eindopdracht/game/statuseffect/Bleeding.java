package com.eindopdracht.game.statuseffect;

import com.eindopdracht.game.gameobject.GameObject;

public class Bleeding extends StatusEffect {
    public Bleeding(int tickAmount, float damage, StatusEffectID id) {
        super(tickAmount, damage, id);
    }

    @Override
    public void update(GameObject gameObject, float delta) {
        accumulator += delta;
        while (accumulator >= 1) {
            accumulator -= 1;
            tickAmount -= 1;

            gameObject.dealDamage(damage);
        }
        if(tickAmount == 0)
            gameObject.removeStatusEffect(this);
    }
}
