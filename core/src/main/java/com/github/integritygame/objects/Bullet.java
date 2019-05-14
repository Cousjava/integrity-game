package com.github.integritygame.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

/**
 * A class to hold information on a bullet
 * @author jonathan coustick
 */
public class Bullet {
    
    private Vector2 location;
    private Vector2 heading;
    
    private static Texture texture = new Texture(Gdx.files.internal("projectiles/ProjectileBlack.png"));
    private static BodyDef BULLET_DEF = null;

    /**
     * This will create a bullet
     * @param position
     * @param vector
     */
    public Bullet(Vector2 position, Vector2 vector) {
        location = position;
        this.heading = vector;
    }

    public float getX() {
        return location.x;
    }

    public float getY() {
        return location.y;
    }
    
    public void update() {
        location.add(heading);
    }
    
    public Texture getTexture() {
        return texture;
    }

    public int getTextureWidth(){
        return 4;
    }

    public int getTextureHeight(){
        return  4;
    }
    
    public static BodyDef getBodyDef() {
        if (BULLET_DEF == null) {
            BULLET_DEF = new BodyDef();
            BULLET_DEF.bullet = true;
        }
        return BULLET_DEF;
    }
    
}
