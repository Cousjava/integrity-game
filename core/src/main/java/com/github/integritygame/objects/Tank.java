package com.github.integritygame.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;

/**
 * This create a tank that can be moved around the screen
 */
public class Tank {
        
    public static final int MASS = 100;

    private Vector2 position;
    private final int width;
    private final int height;
    private Texture texture;
    
    private BodyDef tankBodyDef;
    private FixtureDef tankFixture;
    private Body tankBody;
    private int graphicsWidth;

    /**Specify the default rotation */
    private float rotation = 90;

    /** Specify the tolerance for how fast the tank moves and the rotation interval */
    private float toleranceMove = 0.5f;
    private float toleranceRotation = 1;


    /**
     * This creates a tank with a default texture and the following parameters
     * @param x X position where the tank should be rendered on screen (bottom left corner of the tank)
     * @param y Y position where the tank should be rendered on screen (bottom left corner of the tank)
     * @param width Width the tank should be
     * @param height Height thee tank should be
     */
    public Tank(float x, float y, int width, int height){
        this.position = new Vector2(x, y);
        this.width = width;
        this.height = height;
        this.texture = new Texture(Gdx.files.internal("tanks/DesertColourTankRight.png"));
        
        tankFixture = new FixtureDef();
        tankFixture.shape = setTankShape(width, height);
        tankFixture.density = 20;
        tankBodyDef = new BodyDef();
        tankBodyDef.fixedRotation = true;
        tankBodyDef.type = BodyDef.BodyType.DynamicBody;
        tankBodyDef.position.set(x, y);

        //Sets the width of the screen so the tank cant go past this
        this.graphicsWidth = Gdx.graphics.getWidth();
    }

    /**
     * This allows the tank to move at the rate specified in the tolerance
     * @param positive If true move right on the X else move left
     */
    public void updateX(boolean positive){
        tankBody.applyForceToCenter(position, true);
        if(positive){
            //position.x = Math.min(Math.max(position.x + toleranceMove, 0), graphicsWidth - width);
            //tankBody.applyForceToCenter(new Vector2(1000000, 5000), true);
            tankBody.setLinearVelocity(2000, 0);
        }else{
            //position.x = Math.min(Math.max(position.x - toleranceMove, 0), graphicsWidth - width);
            tankBody.applyForceToCenter(new Vector2(-1000000, 0), true);
            tankBody.applyLinearImpulse(-5000, 0, position.x, position.y, true);
        }
    }

    /**
     * This will render the tank
     * @param batch This will be the SpriteBatch object that will render the object
     */
    public void renderSprite (SpriteBatch batch) {
        batch.draw(texture, position.x, position.y, width, height);
    }

    /**
     * This will allow the angle of the tanks aim to be set with speed of the tolerance
     * @param clockwise This will specify if the tank aim should move clockwise or anti clockwise
     */
    public void rotate(boolean clockwise){
        if(clockwise) {
            rotation = Math.min(Math.max(rotation - toleranceRotation, 0), 180);
        }else{
            rotation = Math.min(Math.max(rotation + toleranceRotation, 0), 180);
        }
    }

    /**
     * This will render an aim for the tank
     * @param shapeRenderer This will be the ShapeRenderer object that will render the object
     */
    public void renderShape(ShapeRenderer shapeRenderer) {
        position = tankBody.getPosition();
        
        //This ensures that its drew from the center of the tank instead of the bottom left corner
        Vector2 localCenter = new Vector2(position.x + (width / 2), position.y + (height / 2));

        //Calculate the other point based on the angle
        float radians = (float)Math.toRadians(rotation);
        float x = (float)(Math.cos(radians) * 25) + localCenter.x;
        float y = (float)(Math.sin(radians) * 25) + localCenter.y;

        //Render the line
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.line(localCenter.x, localCenter.y, x, y);
        shapeRenderer.end();
    }

    /**
     * Gets the current position of the tank (This is the center of the tank)
     * @return Vector2 The position at the center of the tank
     */
    public Vector2 getCurrentPosition() {
        return new Vector2(position.x + (width / 2), position.y + (height / 2));
    }

    /**
     * Gets the rotation of the turret of the tank
     * @return float The rotation in degrees
     */
    public float getRotation() {
        return rotation;
    }

    /**
     * This sets the texture of the tank to something other than the default
     * @param location The location of the texture in the assets folder
     */
    public void setTexture(String location){
        texture = new Texture(Gdx.files.internal(location));
    }
    
    public FixtureDef getTankFixtureDef() {
        return tankFixture;
    }
    
    public BodyDef getTankBodyDef() {
        return tankBodyDef;
    }
    
    private Shape setTankShape(float width, float height) {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);
        return shape;
    }
    
    public void setTankBody(Body tankBody) {
        this.tankBody = tankBody;
    }

}
