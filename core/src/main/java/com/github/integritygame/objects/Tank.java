package com.github.integritygame.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * This create a tank that can be moved around the screen
 */
public class Tank {

    private Vector2 position;
    private Texture texture;
    private Texture turret;
    private int width;
    private int height;
    private int graphicsWidth;

    //Specify the default rotation
    private float rotation = 0;

    //Specify the tolerance for how fast the tank moves and the rotation interval
    private float toleranceMove = 0.5f;
    private float toleranceRotation = 1;

    private boolean side;


    /**
     * This creates a tank with a default texture and the following parameters
     * @param x X position where the tank should be rendered on screen (bottom left corner of the tank)
     * @param y Y position where the tank should be rendered on screen (bottom left corner of the tank)
     * @param width Width the tank should be
     * @param height Height thee tank should be
     */
    public Tank(float x, float y, int width, int height, boolean side){
        this.side = side;
        if(side){
            rotation = 180;
        }
        this.position = new Vector2(x, y);
        this.width = width;
        this.height = height;
        this.texture = new Texture(Gdx.files.internal("tanks/BlueTankLeftBody.png"));
        this.turret = new Texture(Gdx.files.internal("tanks/BlueTankLeftTurret.png"));
        //Sets the width of the screen so the tank cant go past this
        this.graphicsWidth = Gdx.graphics.getWidth();
    }

    /**
     * This allows the tank to move at the rate specified in the tolerance
     * @param positive If true move right on the X else move left
     */
    public void updateX(boolean positive){
        if(positive){
            position.x = Math.min(Math.max(position.x + toleranceMove, 0), graphicsWidth - width);
        }else{
            position.x = Math.min(Math.max(position.x - toleranceMove, 0), graphicsWidth - width);
        }
    }

    /**
     * This will render the tank
     * @param batch This will be the SpriteBatch object that will render the object
     */
    public void renderSprite (SpriteBatch batch) {
        Vector2 localCenter = new Vector2((position.x + (width / 2) + (side ? -18 : +18)), (position.y + (height / 2)) + 7);
        batch.draw(new TextureRegion(turret, 0, 0, 24, 2), localCenter.x, localCenter.y,0,2.5f, 30, 5,1,1,rotation);
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
     * Gets the current position of the tank (This is the center of the tank)
     * @return Vector2 The position at the center of the tank
     */
    public Vector2 getCurrentPosition() {
        return new Vector2((position.x + (width / 2) + (side ? -18 : +18)), (position.y + (height / 2)) + 7);
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
     * @param  The location of the texture in the assets folder
     */
    public void setTexture(String body, String turret){
        this.texture = new Texture(Gdx.files.internal(body));
        this.turret = new Texture(Gdx.files.internal(turret));
    }

}
