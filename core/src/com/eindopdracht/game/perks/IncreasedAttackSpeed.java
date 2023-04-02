package com.eindopdracht.game.perks;

import com.badlogic.gdx.math.MathUtils;
import com.eindopdracht.game.gameobject.Player;

public class IncreasedAttackSpeed extends Perk {

    private boolean hasBeenUsed = false;

    public IncreasedAttackSpeed(Player player) {
        super(player);
    }

    @Override
    public void update(float delta) {
        if (!hasBeenUsed) {
            player.attackSpeed = MathUtils.clamp(player.attackSpeed - 0.125f, 0.01f, 10f);
            hasBeenUsed = true;
        }
    }
}
