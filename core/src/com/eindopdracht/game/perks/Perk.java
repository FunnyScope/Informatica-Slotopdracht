package com.eindopdracht.game.perks;

import com.eindopdracht.game.gameobject.Player;

public abstract class Perk {

    protected Player player;
    public Perk(Player player) {
        this.player = player;
    }

    public abstract void update(float delta);

}
