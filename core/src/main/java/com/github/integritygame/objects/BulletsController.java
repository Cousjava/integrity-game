package com.github.integritygame.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class BulletsController {

    private List<Bullet> outside = new ArrayList<>();
    private List<Bullet> bullets = new ArrayList<>();

    private int graphicsWidth;
    private int graphicsHeight;

    public BulletsController(int graphicsWidth, int graphicsHeight){
        this.graphicsWidth = graphicsWidth;
        this.graphicsHeight = graphicsHeight;
    }

    public void render(SpriteBatch spriteBatch){
        for (Bullet bullet: bullets) {
            if (bullet.getX() < 0 || bullet.getX() > graphicsWidth || bullet.getY() < 0 || bullet.getY() > graphicsHeight) {
                outside.add(bullet);
            } else {
                bullet.update();
                spriteBatch.draw(bullet.getTexture(), bullet.getX()-(bullet.getTextureWidth()/2), bullet.getY()-(bullet.getTextureHeight()/2), bullet.getTextureWidth(), bullet.getTextureHeight());
            }

        }
    }

    public void addBullet(Vector2 positon, Vector2 vector){
        bullets.add(new Bullet(positon, vector));
    }

}
