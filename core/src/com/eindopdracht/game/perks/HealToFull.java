package com.eindopdracht.game.perks;

import com.eindopdracht.game.gameobject.Player;

public class HealToFull extends Perk {
    private boolean hasBeenUsed = false;

    public HealToFull(Player player) {
        super(player);
    }

    @Override
    public void update(float delta) {
        if (!hasBeenUsed) {
            player.setHealth(player.getMaxHealth());
            hasBeenUsed = true;
        }
    }
}
