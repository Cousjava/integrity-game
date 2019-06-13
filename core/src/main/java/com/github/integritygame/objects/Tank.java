package com.github.integritygame.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.github.integritygame.util.AssetManager;
import com.github.integritygame.util.InputManager;
import com.integrity.games.util.PolarVector;

/**
 * This create a tank that can be moved around the screen
 */
public class Tank {

    private Vector2 position;
    private final int width;
    private final int height;

    private AssetManager assetManager;
    private AssetManager.TankStyles tankStyles;

    private BodyDef tankBodyDef;
    private FixtureDef tankFixture;
    private Body tankBody;

    private int barrelLength = 30;
    private float toleranceRotation = 1;
    private int damage = 100;
    private int money = 250;
    private int fuel = 100;

    private boolean rightSide;
    private float rotation;

    /**
     * This creates a tank with a default texture and the following parameters
     *
     * @param x      X position where the tank should be rendered on screen (bottom left corner of the tank)
     * @param y      Y position where the tank should be rendered on screen (bottom left corner of the tank)
     * @param width  Width the tank should be
     * @param height Height thee tank should be
     */
    public Tank(float x, float y) {
        this.width = 80;
        this.height = 35;
        this.position = new Vector2(x, y);
        assetManager = AssetManager.getInstance();
        tankStyles = AssetManager.TankStyles.GREEN_TANK;

        tankFixture = new FixtureDef();
        tankFixture.shape = setTankShape(width, height);
        tankFixture.density = 2f;
        tankFixture.filter.categoryBits = 2;
        tankBodyDef = new BodyDef();
        tankBodyDef.fixedRotation = true;
        tankBodyDef.type = BodyDef.BodyType.DynamicBody;
        tankBodyDef.position.set(x, y);
    }

    public void registerTurn(UserTurn userTurn) {
        this.rightSide = userTurn.getControl().equals(InputManager.Control.RIGHT);
        this.rotation = rightSide ? 180 : 0;
    }

    /**
     * This allows the tank to move at the rate specified in the tolerance
     *
     * @param positive If true move right on the X else move left
     */
    public void updateX(boolean positive) {
        tankBody.applyForceToCenter(position, true);
        if (positive) {
            tankBody.applyForceToCenter(new Vector2(500000000, 5000), true);
        } else {
            tankBody.applyForceToCenter(new Vector2(-500000000, 5000), true);
        }
    }

    /**
     * This will render the tank
     *
     * @param batch This will be the SpriteBatch object that will render the object
     */
    public void renderSprite(SpriteBatch batch) {
        position = tankBody.getPosition().cpy();
        position.x -= width / 2;
        position.y -= height / 2;
        Vector2 localCenter = getLocalCenter();
        TextureRegion barrel = new TextureRegion(assetManager.getTankTexture(tankStyles)[1], 0, 0, 24, 2);
        barrel.flip(false, rightSide);
        batch.draw(barrel, localCenter.x, localCenter.y, 0, 2.5f, barrelLength, 5, 1, 1, rotation);
        batch.draw(assetManager.getTankTexture(tankStyles)[0], position.x, position.y, width, height, 0, 0, 58, 24, rightSide, false);
    }

    /**
     * This will allow the angle of the tanks aim to be set with speed of the tolerance
     *
     * @param clockwise This will specify if the tank aim should move clockwise or anti clockwise
     */
    public void rotate(boolean clockwise) {
        if (clockwise) {
            rotation = Math.min(Math.max(rotation - toleranceRotation, rightSide ? 135 : 0), rightSide ? 180 : 45);
        } else {
            rotation = Math.min(Math.max(rotation + toleranceRotation, rightSide ? 135 : 0), rightSide ? 180 : 45);
        }
    }

    public void stopTank() {
        tankBody.setLinearVelocity(0, 0);
    }

    /**
     * Gets the current position of the tank (This is the center of the tank)
     *
     * @return Vector2 The position at the center of the tank
     */
    public Vector2 getCurrentPosition() {
        position = tankBody.getPosition().cpy();
        return new Vector2((position.x + (width / 2) + (rightSide ? -18 : +18)), (position.y + (height / 2)) + 7);
    }

    /**
     * Gets the end of the barrel of the tank's gun
     */
    public Vector2 getBarrelEnd() {
        Vector2 localCenter = getLocalCenter();
        localCenter.y += 2.5f;
        Vector2 barrel = new PolarVector(barrelLength * barrelLength, (float) (rotation * Math.PI / 180));
        return localCenter.add(barrel);
    }

    /**
     * Gets the center of the tank
     */
    private Vector2 getLocalCenter() {
        return new Vector2((position.x + (width / 2) + (rightSide ? -18 : +18)), (position.y + (height / 2)) + 7);
    }

    /**
     * Gets the rotation of the turret of the tank
     *
     * @return float The rotation in degrees
     */
    public float getRotation() {
        return rotation;
    }

    /**
     * This sets the texture of the tank to something other than the default
     */
    public void setTexture(AssetManager.TankStyles texture) {
        this.tankStyles = texture;
    }

    /**
     * Returns the tank fixture definition
     *
     * @return Tank fixture definition
     */
    public FixtureDef getTankFixtureDef() {
        return tankFixture;
    }

    /**
     * Returns the tank body definition
     *
     * @return Tank body definition
     */
    public BodyDef getTankBodyDef() {
        return tankBodyDef;
    }

    /**
     * Sets the tank shape given a height and width
     *
     * @param width  Tank width
     * @param height Tank height
     * @return New Tank Shape as a Polygon
     */
    private Shape setTankShape(float width, float height) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);
        return shape;
    }

    /**
     * Sets the tank body with the given Body
     *
     * @param tankBody New tank body
     */
    public void setTankBody(Body tankBody) {
        tankBody.setUserData(this);
        this.tankBody = tankBody;
    }


    /**
     * Alters the damage by a given value
     *
     * @param increase True increments the damage, False reduces the damage
     * @param value    Amount to alter by
     */
    public void toggleByValue(boolean increase, int value) {
        damage = increase
                ? Math.min(Math.max(damage + value, 0), 100)
                : Math.min(Math.max(damage - value, 0), 100);
    }

    public void toggelFuel(boolean increase, int value) {
        fuel = increase
                ? Math.min(Math.max(fuel + value, 0), 100)
                : Math.min(Math.max(fuel - value, 0), 100);
    }

    /**
     * Alerts the money by a given value
     *
     * @param increase True increments the money, False reduces the money
     * @param value    Amount to alter by
     */
    public void changeMoney(boolean increase, int value) {
        money = increase
                ? money + value
                : money - value;
    }

    /**
     * Returns if the health of the tank is less than or equal to 0
     *
     * @return True if the tank is dead
     */
    public boolean isDead() {
        return damage <= 0;
    }

    /**
     * Returns if the user has ran out of money
     *
     * @return True if the wallet is empty
     */
    public boolean isBankrupt() {
        return money <= 0;
    }

    /**
     * Returns how damaged the tank is
     *
     * @return Damage the tank can take before being destroyed
     */
    int getDamage() {
        return damage;
    }

    /**
     * Returns how much money the user has in their wallet
     *
     * @return Wallet contents
     */
    int getMoney() {
        return money;
    }

    public int getFuel() {
        return fuel;
    }
}
