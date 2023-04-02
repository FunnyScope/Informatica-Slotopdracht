package com.eindopdracht.game.perks;

import com.badlogic.gdx.math.MathUtils;
import com.eindopdracht.game.gameobject.Player;

public class IncreasedDamage extends Perk {

    private boolean hasBeenUsed = false;

    public IncreasedDamage(Player player) {
        super(player);
    }

    @Override
    public void update(float delta) {
        if (!hasBeenUsed) {
            player.damage += 10;
            hasBeenUsed = true;
        }
    }
}