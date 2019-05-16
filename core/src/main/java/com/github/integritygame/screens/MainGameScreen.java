package com.github.integritygame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.integritygame.util.AssetManager;
import com.github.integritygame.util.GameManager;
import com.github.integritygame.util.VariableManager;

public class MainGameScreen extends AbstractScreen {

    private GameManager gameManager;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;
    private Texture backgroundTexture;

    private int graphicsWidth;
    private int graphicsHeight;

    /**
     * Create a main game screen and initialise gamemanager to handle most the stuff
     */
    public MainGameScreen() {
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        graphicsWidth = Gdx.graphics.getWidth();
        graphicsHeight = Gdx.graphics.getHeight();

        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        gameManager = new GameManager(graphicsWidth, graphicsHeight, spriteBatch, shapeRenderer);

        configBackground();
    }

    private void configBackground() {
        //backgroundTexture = new Texture(Gdx.files.internal("backgrounds/Background1.jpg"));

        backgroundTexture = AssetManager.background(VariableManager.getInstance().getString("Background").equals("Grass") ? AssetManager.Background.GRASS : AssetManager.Background.DESERT);
    }

    @Override
    public void show() {

    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Render the background
        spriteBatch.begin();
        spriteBatch.draw(backgroundTexture, 0, 0, graphicsWidth, graphicsHeight);
        spriteBatch.end();

        //Also run the render method in game manager
        gameManager.render(delta);
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
