package com.eindopdracht.game.control;


import com.badlogic.gdx.utils.Pool;
import com.eindopdracht.game.EindOpdracht;
import com.eindopdracht.game.gameobject.*;

//Creates game objects (except for maybe the bullets)
public class GameObjectCreator {

    private EindOpdracht game;
    private Handler handler;

    private Pool<Bullet> bulletPool = new Pool<Bullet>() {
        @Override
        protected Bullet newObject() {
            return new Bullet(0, 0, 0, 0, 0, ID.bullet, handler, 0, 0);
        }
    };

    public GameObjectCreator(EindOpdracht game, Handler handler) {
        this.game = game;
        this.handler = handler;
    }

    public void createPlayer(float x, float y) {
        handler.addGameObject(new Player(x, y, 0, 0, 0, ID.player, handler, 64, 64));
    }

    public void createBullet(float x, float y, float orientation) {

        Bullet bullet = bulletPool.obtain();
        bullet.init(x, y, orientation);
        handler.addGameObject(bullet);
    }

    //TODO: add more game objects :D


    public Pool<Bullet> getBulletPool() {
        return bulletPool;
    }
}
