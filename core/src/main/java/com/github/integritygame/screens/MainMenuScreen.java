package com.github.integritygame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.integritygame.util.AssetManager;

public class MainMenuScreen extends AbstractScreen {

    private Stage stage;
    private SpriteBatch spriteBatch;
    private AssetManager assetManager;

    private TextButton playButton;
    private TextButton exitButton;
    private TextButton helpButton;
    private TextButton settingsButton;
    
    private final Texture homeTexture;

    public MainMenuScreen() {
        stage = new Stage();
        spriteBatch = new SpriteBatch();
        assetManager = AssetManager.getInstance();
        homeTexture = new Texture(assetManager.getBackgrounds(AssetManager.Background.HOME));
    }

    /**
     * Creates and configures button and tables for rendering
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        createAndConfigureButtons();
        createAndConfigureTableForMenu();
    }

    /**
     * Renders page elements onto the screen medium
     *
     * @param delta Delay between actions
     */
    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);


        spriteBatch.begin();
        spriteBatch.draw(homeTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    /**
     * Called when switch back to this screen
     */
    @Override
    public void resume() {
        stage.act();
        stage.draw();
    }

    /**
     * Called when screens switch
     */
    @Override
    public void hide() {
        stage.clear();
    }

    /**
     * Called on exit
     */
    @Override
    public void dispose() {
        stage.dispose();
    }

    /**
     * All buttons can be created here and configured. Ie, add listeners
     */
    private void createAndConfigureButtons() {
        playButton = new TextButton("Play", assetManager.getCustomTextButton());
        exitButton = new TextButton("Exit", assetManager.getCustomTextButton());
        helpButton = new TextButton("Help", assetManager.getCustomTextButton());
        settingsButton = new TextButton("Settings", assetManager.getCustomTextButton());

        //add listeners to each button
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().changeScreen(ScreenManager.Screens.PRE_GAME);
            }
        });

        helpButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().changeScreen(ScreenManager.Screens.HELP_MENU);
            }
        });

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().changeScreen(ScreenManager.Screens.SETTINGS_MENU);
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
    }

    /**
     * Method to create and configure the main table used for the menu buttons.
     */
    private void createAndConfigureTableForMenu() {
        int tableWidthScalar = 8;
        int tableHeightScalar = 10;
        int width = Gdx.graphics.getWidth() / tableWidthScalar;
        int height = Gdx.graphics.getHeight() / tableHeightScalar;

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.setDebug(false);
        mainTable.top();
        mainTable.add(assetManager.getText(Color.BLACK, "Tanks", true));
        mainTable.row();
        mainTable.add(playButton).width(width).height(height);
        mainTable.row();
        mainTable.add(settingsButton).width(width).height(height);
        mainTable.row();
        mainTable.add(helpButton).width(width).height(height);
        mainTable.row();
        mainTable.add(exitButton).width(width).height(height);
        stage.addActor(mainTable);
    }
}
