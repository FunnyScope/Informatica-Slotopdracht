package com.eindopdracht.game.control;

import com.eindopdracht.game.EindOpdracht;
import com.eindopdracht.game.gameobject.*;

//Creates game objects (except for maybe the bullets)
public class GameObjectCreator {

    private EindOpdracht game;
    private Handler handler;

    public GameObjectCreator(EindOpdracht game, Handler handler) {
        this.game = game;
        this.handler = handler;
    }

    public void createPlayer(float x, float y) {
        handler.addGameObject(new Player(x, y, ID.player, handler, 0));
    }


}
