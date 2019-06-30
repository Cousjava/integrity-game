package com.github.integritygame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.integritygame.util.AssetManager;
import com.github.integritygame.util.GameManager;

public class MainGameScreen extends AbstractScreen {

    private Stage stage;
    private Stage upgrades;

    private GameManager gameManager;

    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;

    private int graphicsWidth;
    private int graphicsHeight;
    
    /**
     * Create a main game screen and initialise the GameManager to handle most the stuff
     */
    public MainGameScreen() {
        stage = new Stage();
        upgrades = new Stage();

        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        graphicsWidth = Gdx.graphics.getWidth();
        graphicsHeight = Gdx.graphics.getHeight();

        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        
        gameManager = new GameManager(graphicsWidth, graphicsHeight, spriteBatch, shapeRenderer, stage, upgrades);
    }

    //TODO - Add in other backgrounds again

    @Override
    public void show() {

    }


    /**
     * Render the screen into the window
     *
     * @param delta Delay between actions
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameManager.render(delta);

        stage.act();
        upgrades.act();
        stage.draw();
        upgrades.draw();
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
        stage.clear();
        upgrades.clear();
    }

    @Override
    public void dispose() {
        stage.dispose();
        upgrades.dispose();
        spriteBatch.dispose();

    }



}
