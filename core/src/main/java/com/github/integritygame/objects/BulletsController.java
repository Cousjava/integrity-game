package com.github.integritygame.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.integrity.games.world.GameWorld;

import java.util.ArrayList;
import java.util.List;

public class BulletsController {

    private List<Bullet> outside = new ArrayList<>();
    private List<Bullet> bullets = new ArrayList<>();

    private int graphicsWidth;
    private int graphicsHeight;
    private GameWorld gameWorld;

    public BulletsController(int graphicsWidth, int graphicsHeight, GameWorld gameWorld) {
        this.graphicsWidth = graphicsWidth;
        this.graphicsHeight = graphicsHeight;
        this.gameWorld = gameWorld;
    }

    public void render(SpriteBatch spriteBatch) {
        for (Bullet bullet : bullets) {
            if (bullet.isImpacted() || bullet.getY() < 0 || bullet.getY() > graphicsHeight) {
                outside.add(bullet);
            } else {
                spriteBatch.draw(bullet.getTexture(), bullet.getX() - (bullet.getTextureWidth() / 2), bullet.getY() - (bullet.getTextureHeight() / 2), bullet.getTextureWidth(), bullet.getTextureHeight());
            }

        }
    }

    /**
     * Removes all bullets that are outside the playing area
     */
    public void cleanOutsideBullets() {
        for (Bullet toRemove : outside) {
            bullets.remove(toRemove);
            toRemove.sleep();
        }
    }

    public boolean isOnScreen() {
        return bullets.size() >= 1;
    }

    /**
     * Creates a new bullet and adds it to the screen
     *
     * @param positon Starting location of bullet
     * @param vector  The direction in which the bullet is fired
     * @return the created bullet
     */
    public Bullet addBullet(Vector2 positon, Vector2 vector, Tank firingTank, BulletData bulletData) {
        Bullet bullet;
        bullet = new Bullet(positon, vector, firingTank, bulletData);
        bullets.add(bullet);
        Body bulletBody = gameWorld.addBullet(bullet.getBodyDef());
        bullet.setBody(bulletBody);
        return bullet;
    }

}
