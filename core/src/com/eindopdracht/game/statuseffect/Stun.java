package com.eindopdracht.game.statuseffect;

import com.eindopdracht.game.gameobject.GameObject;

public class Stun extends StatusEffect {
    public Stun(int tickAmount, StatusEffectID id) {
        super(tickAmount, id);
    }

    @Override
    public void update(GameObject gameObject, float delta) {
        accumulator += delta;
        while(accumulator >= 1) {
            accumulator -= 1;
            tickAmount -= 1;

        }
        if(tickAmount == 0)
            gameObject.removeStatusEffect(this);
    }
}
