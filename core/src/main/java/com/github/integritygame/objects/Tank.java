package com.github.integritygame.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Tank {

    private Vector2 position;
    private float rotation = 90;
    private int width;
    private int height;
    private Texture texture;
    private float toleranceMove = 0.5f;
    private float toleranceRotation = 5;

    private int graphicsWidth;

    public Tank(float x, float y, int width, int height){
        this.position = new Vector2(x, y);
        this.width = width;
        this.height = height;
        this.texture = new Texture(Gdx.files.internal("tanks/DesertColourTank.png"));
        this.graphicsWidth = Gdx.graphics.getWidth();
    }

    public void updateX(boolean positive){
        if(positive){
            position.x = Math.min(Math.max(position.x + toleranceMove, 0), graphicsWidth - width);
        }else{
            position.x = Math.min(Math.max(position.x - toleranceMove, 0), graphicsWidth - width);
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
        Vector2 localCenter = new Vector2(position.x + (width / 2), position.y + (height / 2));
        float radians = (float)Math.toRadians(rotation);
        float x = (float)(Math.cos(radians) * 20) + localCenter.x;
        float y = (float)(Math.sin(radians) * 20) + localCenter.y;

        shapeRenderer.setColor(Color.RED);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.line(localCenter.x, localCenter.y, x, y);
        shapeRenderer.end();
    }

}
