package com.github.integritygame.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.github.integritygame.util.AssetManager;

/**
 * A class to hold information on a bullet
 *
 * @author jonathan coustick
 */
public class Bullet {

    private Vector2 location;
    private Vector2 heading;
    private Body body;

    private boolean impacted = false;
    private int damage = 5;

    private Tank firingTank;

    private BulletData.BulletName bulletData;

    Sound tankFireSound = Gdx.audio.newSound(Gdx.files.internal("sounds/tank-fire.wav"));
    Sound airRaidSirenSound = Gdx.audio.newSound(Gdx.files.internal("sounds/air-raid-siren2.wav"));
    long tankFireId = 1;
    long sirenId = 2;

    /**
     * This will create a bullet
     *
     * @param position
     * @param vector
     */
    public Bullet(Vector2 position, Vector2 vector, Tank firingTank, BulletData.BulletName bulletData) {
        location = position;
        this.heading = vector;
        this.firingTank = firingTank;
        this.bulletData = bulletData;

        //play fire sound for new bullet
        if (bulletData.equals(BulletData.BulletName.NUKE)) {
            airRaidSirenSound.setLooping(sirenId, false);
            airRaidSirenSound.play();
        } else {
            tankFireSound.setLooping(tankFireId, false);
            tankFireSound.play();
        }
    }

    /**
     * Returns the X coordinate of the current bullet
     *
     * @return Bullet's X coordinate
     */
    public float getX() {
        return body.getPosition().x;
    }

    /**
     * Returns the Y coordinate of the current bullet
     *
     * @return Bullet's Y coordinate
     */
    public float getY() {
        return body.getPosition().y;
    }

    /**
     * Returns the bullet texture
     *
     * @return Bullet's texture
     */
    public Texture getTexture() {
        return AssetManager.getInstance().getBullets(bulletData);
    }

    /**
     * Returns the texture's width
     *
     * @return Bullet texture's width
     */
    public int getTextureWidth() {
        return 4;
    }

    /**
     * Returns the texture's height
     *
     * @return Bullet texture's height
     */
    public int getTextureHeight() {
        return 4;
    }

    /**
     * Returns the Bullets characteristics.
     *
     * @return Bullet characteristic definition
     */
    public BodyDef getBodyDef() {
        BodyDef bulletDef = new BodyDef();
        bulletDef.bullet = true;
        bulletDef.linearVelocity.x = heading.x * 5;
        bulletDef.linearVelocity.y = heading.y * 5;
        bulletDef.gravityScale = 2;
        bulletDef.type = BodyDef.BodyType.DynamicBody;
        bulletDef.position.x = location.x;
        bulletDef.position.y = location.y;

        return bulletDef;
    }

    /**
     * Sets the bullets body
     *
     * @param bulletBody
     */
    void setBody(Body bulletBody) {
        body = bulletBody;
        body.setUserData(this);

    }

    /**
     * Returns the amount of damage that is dealt to a
     * tank on impact
     */
    public int getDamage() {
        return impacted ? 0 : damage;
    }

    /**
     * Sets the amount of damage that is dealt to a
     * tank on impact
     *
     * @param damage Damage dealt
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Whether the bullet has hit another object
     *
     * @return True if the bullet has hit another object, False otherwise
     */
    public boolean isImpacted() {
        return impacted;
    }

    /**
     * Sets whether the bullet has hit something
     *
     * @param impacted
     */
    public void setImpacted(boolean impacted) {
        this.impacted = impacted;
    }

    /**
     * Forces the bullet to "deactivate"
     */
    void sleep() {
        body.setActive(false);
    }

    /**
     * Returns the tank who fired this bullet
     *
     * @return Origin tank
     */
    public Tank getFiringTank() {
        return firingTank;
    }

    /**
     * Returns the current bullets data
     *
     * @return Bullet data
     */
    public BulletData.BulletName getBulletData() {
        return bulletData;
    }

}
