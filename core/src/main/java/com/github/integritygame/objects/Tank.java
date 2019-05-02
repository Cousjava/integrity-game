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

public class Tank {
        
    public static final int MASS = 100;

    private Vector2 position;
    private float rotation = 90;
    private final int width;
    private final int height;
    private Texture texture;
    private final float toleranceMove = 0.5f;
    private final float toleranceRotation = 5;
    
    private BodyDef tankBodyDef;
    private FixtureDef tankFixture;
    private Body tankBody;

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
    }

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

    public void renderSprite (SpriteBatch batch) {
        batch.draw(texture, position.x, position.y, width, height);
    }

    public void rotate(boolean clockwise){
        if(clockwise) {
            rotation = Math.min(Math.max(rotation - toleranceRotation, 0), 180);
        }else{
            rotation = Math.min(Math.max(rotation + toleranceRotation, 0), 180);
        }
    }

    public void renderShape(ShapeRenderer shapeRenderer) {
        position = tankBody.getPosition();
        
        Vector2 localCenter = new Vector2(position.x + (width / 2), position.y + (height / 2));
        float radians = (float)Math.toRadians(rotation);
        float x = (float)(Math.cos(radians) * 25) + localCenter.x;
        float y = (float)(Math.sin(radians) * 25) + localCenter.y;

        shapeRenderer.setColor(Color.RED);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.line(localCenter.x, localCenter.y, x, y);
        shapeRenderer.end();
    }
    
    public Vector2 getCurrentPosition() {
        return new Vector2(position.x + (width / 2), position.y + (height / 2));
    }
    
    public float getRotation() {
        return rotation;
    }

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
