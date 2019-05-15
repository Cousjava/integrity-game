package com.github.integritygame.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;

/**
 * A class to hold information on a bullet
 * @author jonathan coustick
 */
public class Bullet {
    
    private Vector2 location;
    private Vector2 heading;
    private Body body;
    
    private boolean impacted = false;
    
    private static Texture texture = new Texture(Gdx.files.internal("projectiles/ProjectileBlack.png"));

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
        return body.getPosition().x;
    }

    public float getY() {
        return body.getPosition().y;
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
    
    public BodyDef getBodyDef() {
        BodyDef bulletDef = new BodyDef();
        bulletDef.bullet = true;
        bulletDef.linearVelocity.x = heading.x * 5;
        bulletDef.linearVelocity.y = heading.y * 5;
        bulletDef.gravityScale = 1;
        bulletDef.type = BodyDef.BodyType.DynamicBody;
        bulletDef.position.x = location.x;
        bulletDef.position.y = location.y;
        
        return bulletDef;
    }

    void setBody(Body bulletBody) {
        body = bulletBody;
        body.setUserData(this);

    }

    /**
     * Whether the bullet has hit another object
     * @return 
     */
    public boolean isImpacted() {
        return impacted;
    }

    /**
     * Sets whether the bullet has hit something
     * @param impacted 
     */
    public void setImpacted(boolean impacted) {
        this.impacted = impacted;
    }
    
    
    
}
