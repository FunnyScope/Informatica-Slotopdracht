package com.eindopdracht.game.control;


import com.badlogic.gdx.utils.Array;
import com.eindopdracht.game.EindOpdracht;
import com.eindopdracht.game.gameobject.*;
import com.eindopdracht.game.gameobject.ai.Connection;
import com.eindopdracht.game.gameobject.ai.Node;
import com.eindopdracht.game.gameobject.obstacle.Wall;

//Creates game objects
public class GameObjectCreator {

    private EindOpdracht game;
    private Handler handler;

    private BulletStorage bulletPool;

    public GameObjectCreator(EindOpdracht game, Handler handler) {
        this.game = game;
        this.handler = handler;
        bulletPool = new BulletStorage(handler);
    }

    /**
     * @param x horizontal position of the player
     * @param y vertical position of the player
     */
    public void createPlayer(float x, float y) {
        handler.addGameObject(new Player(x, y, 0, 0, 0, ID.player, handler, 3.2f, 3.2f));
    }

    /**
     * @param x horizontal position of the bullet
     * @param y vertical position of the bullet
     * @param orientation orientation of the bullet, in radians
     * @param bulletID the ID of the bullet, either player or enemy
     */
    public void createBullet(float x, float y, float orientation, BulletID bulletID) {

        Bullet bullet = bulletPool.obtain();
        bullet.init(x, y, orientation, bulletID);
        handler.addGameObject(bullet);
    }

    /**
     * @param x horizontal position of the bullet
     * @param y vertical position of the bullet
     * @param orientation orientation of the bullet, in radians
     * @param bulletID the ID of the bullet, either player or enemy
     * @param damage damage dealt by the bullet
     */
    public void createBullet(float x, float y, float orientation, BulletID bulletID, float damage) {

        Bullet bullet = bulletPool.obtain();
        bullet.init(x, y, orientation, bulletID, damage);
        handler.addGameObject(bullet);
    }


    /**
     * @param x horizontal position of the object
     * @param y vertical position of the object
     * @param patrol patrol the object follows
     * @param spawnNode node at which the object spawns
     * @throws RuntimeException thrown upon not being able to find the player in the handler
     */
    public void createBasicEnemy(float x, float y, Array<Connection> patrol, Node spawnNode) throws Exception {
        Player player = null;
        for (GameObject gameObject : handler.getGameObjects()) {
            if(gameObject.getId() == ID.player) {
                player = (Player) gameObject;
            }
        }

        if(player == null) {
            throw new RuntimeException("No player found when creating an enemy.");
        }

        handler.addGameObject(new BasicEnemy(x, y, 0, 0, 0, ID.shotgunEnemy, handler,
                3.2f, 3.2f, player,
                patrol, spawnNode
        ));
    }


    /**
     * @param x horizontal position of the wall
     * @param y vertical position of the wall
     * @param width width of the wall
     * @param height height of the wall
     */
    public void createWall(float x, float y, float width, float height) {
        handler.addGameObject(new Wall(x, y, 0, 0, 0, ID.wall, handler, width, height));
    }

    /**
     * @param x horizontal position of the object
     * @param y vertical position of the object
     * @param patrol patrol the object follows
     * @param spawnNode node at which the object spawns
     * @throws RuntimeException thrown upon not being able to find the player in the handler
     */
    public void createShotgunEnemy(float x, float y, Array<Connection> patrol, Node spawnNode) throws Exception {
        Player player = null;

        for (GameObject gameObject : handler.getGameObjects()) {
            if (gameObject.getId() == ID.player) {
                player = (Player) gameObject;
            }
        }
        if (player == null) {
            throw new RuntimeException("No player found when creating an enemy.");
        }

        handler.addGameObject(new ShotgunEnemy(x, y, 0, 0, 0, ID.shotgunEnemy, handler,
                3.2f, 3.2f, player,
                patrol, spawnNode
        ));
    }


    //TODO: add more game objects :D


    /**
     * Frees the bullets that have collided, as outlined in the WorldHandler class
     */
    public void freeBullets() {
        Array<Bullet> bulletQueue = handler.getWorldHandler().bulletQueue;
        // Cleans up any stragglers that might not have been caught by the worldHandler as insurance.
        // Probably superfluous, however, I enjoy the safety that redundancy grants me.
        for(GameObject gameObject : handler.getGameObjects()) {
            if(gameObject.getId() == ID.bullet && gameObject.getBody().getLinearVelocity().len() == 0) {
                bulletQueue.add((Bullet) gameObject);
            }
        }

        // We go back to front to avoid any iteration errors. Yeah, I hate it too.
        for(int i = bulletQueue.size - 1; i >= 0; i--) {
            bulletPool.free(bulletQueue.get(i));
            bulletQueue.removeIndex(i);
        }
    }


}
