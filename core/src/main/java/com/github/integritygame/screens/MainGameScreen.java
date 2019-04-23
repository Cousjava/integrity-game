package com.github.integritygame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.integritygame.objects.Tank;

public class MainGameScreen extends AbstractScreen {

    private Tank tankA;
    private Tank tankB;

    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;
    private Texture backgroundTexture;

    public MainGameScreen(){
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        loadTextures();
        tankA = new Tank(10, 120,80,40);
        tankB = new Tank(510,120,80,40);
    }

    private void loadTextures(){
        backgroundTexture = new Texture(Gdx.files.internal("backgrounds/Background1.jpg"));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        userInput();
        spriteBatch.begin();
            spriteBatch.draw(backgroundTexture, 0 , 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            tankA.renderSprite(spriteBatch);
            tankB.renderSprite(spriteBatch);
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
