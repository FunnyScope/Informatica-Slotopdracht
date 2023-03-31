package com.eindopdracht.game.gameobject;

public interface Enemy {

    // Enemy range.
    public abstract int shootingDistance();
    // Distance within which, if they "hear" a gunshot, they'll chase the player until the player is
    // at a distance greater than this distance.
    public abstract int followDistance();

}
