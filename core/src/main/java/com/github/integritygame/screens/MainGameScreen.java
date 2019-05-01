package com.github.integritygame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.github.integritygame.objects.Bullet;
import com.github.integritygame.objects.Tank;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainGameScreen extends AbstractScreen {

    private Tank tankA;
    private Tank tankB;

    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;
    private Texture backgroundTexture;
    
    private List<Bullet> bullets = new ArrayList<>();

    private int graphicsWidth;
    private int graphicsHeight;

    /**
     * Create a main game screen with the main game logic
     */
    public MainGameScreen(){
        //Create the renderer's for the main game
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        loadTextures();

        //Set the size of the screen on load
        graphicsWidth = Gdx.graphics.getWidth();
        graphicsHeight = Gdx.graphics.getHeight();

        //Create new tanks and set the positions and textures
        tankA = new Tank(30, 180,80,35);
        tankB = new Tank(graphicsWidth - 110,180,80,35);
        tankB.setTexture("tanks/DesertColourTankLeft.png");
    }

    /**
     * Loads the textures
     */
    private void loadTextures(){
        backgroundTexture = new Texture(Gdx.files.internal("backgrounds/gameBackground.jpg"));
    }

    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        userInput();
        spriteBatch.begin();
            spriteBatch.draw(backgroundTexture, 0 , 0, graphicsWidth, graphicsHeight);
            tankA.renderSprite(spriteBatch);
            tankB.renderSprite(spriteBatch);
            List<Bullet> outside = new ArrayList<>();//for bullets that are outside the game
            
            for (Bullet bullet: bullets) {
                if (bullet.getX() < 0 || bullet.getX() > graphicsWidth || bullet.getY() < 0 || bullet.getY() > graphicsHeight) {
                    outside.add(bullet);
                } else {
                    bullet.update();
                    spriteBatch.draw(bullet.getTexture(), bullet.getX(), bullet.getY());
                }
                
            }
            
            
        spriteBatch.end();
        tankA.renderShape(shapeRenderer);
        tankB.renderShape(shapeRenderer);

    }

    private void userInput(){
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            tankA.updateX(false);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            tankA.updateX(true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            tankB.updateX(false);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            tankB.updateX(true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            tankA.rotate(true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            tankA.rotate(false);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            tankB.rotate(true);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            tankB.rotate(false);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
            Vector2 bullet = new Vector2(1, 1);
            bullet.setAngle(tankA.getRotation());
            bullet.setLength2(2f);
            bullets.add(new Bullet(new Vector2(tankA.getCurrentPosition()), bullet));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_RIGHT)) {
            Vector2 bullet = new Vector2(1, 1);
            bullet.setAngle(tankB.getRotation());
            bullet.setLength2(2f);
            bullets.add(new Bullet(new Vector2(tankB.getCurrentPosition()), bullet));
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
