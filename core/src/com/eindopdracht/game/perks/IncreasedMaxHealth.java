package com.eindopdracht.game.perks;

import com.eindopdracht.game.gameobject.Player;

public class IncreasedMaxHealth extends Perk {
    private boolean hasBeenUsed = false;

    public IncreasedMaxHealth(Player player) {
        super(player);
    }

    @Override
    public void update(float delta) {

        if (!hasBeenUsed) {
            player.setMaxHealth(player.getMaxHealth() + 50);
            player.setHealth(player.getHealth() + 50);
            hasBeenUsed = true;
        }

    }
}
