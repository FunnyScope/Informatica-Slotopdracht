package com.eindopdracht.game.perks;

import com.eindopdracht.game.gameobject.Player;
import com.eindopdracht.game.statuseffect.BulletImmunity;
import com.eindopdracht.game.statuseffect.StatusEffectID;

public class TempBulletImmunity extends Perk {
    private float timeRemaining = 3;
    private final int duration = 1;

    public TempBulletImmunity(Player player) {
        super(player);
    }

    @Override
    public void update(float delta) {
        timeRemaining -= delta;

        if (timeRemaining <= 0) {
            timeRemaining += 4;
            player.addStatusEffect(new BulletImmunity(duration, StatusEffectID.BulletImmunity));
        }
    }
}
