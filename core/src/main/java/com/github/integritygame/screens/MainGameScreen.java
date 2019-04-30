package com.github.integritygame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.integritygame.util.GameManager;

public class MainGameScreen extends AbstractScreen {

    private GameManager gameManager;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;
    private Texture backgroundTexture;

    private int graphicsWidth;
    private int graphicsHeight;

    /**
     * Create a main game screen with the main game logic
     */
    public MainGameScreen(){
        graphicsWidth = Gdx.graphics.getWidth();
        graphicsHeight = Gdx.graphics.getHeight();

        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        gameManager = new GameManager(graphicsWidth, graphicsHeight, spriteBatch, shapeRenderer);

        configBackground();
    }

    private void configBackground(){
        backgroundTexture = new Texture(Gdx.files.internal("backgrounds/Background1.jpg"));
    }

    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
            spriteBatch.draw(backgroundTexture, 0 , 0, graphicsWidth, graphicsHeight);
        spriteBatch.end();

        gameManager.render();
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
