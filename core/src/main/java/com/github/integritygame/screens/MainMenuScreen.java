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

    private TextButton playButton;
    private TextButton exitButton;
    private TextButton helpButton;
    private TextButton settingsButton;
    private Table mainTable;

    private Texture texture;


    public MainMenuScreen() {
        stage = new Stage();
        spriteBatch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("backgrounds/tank-main-menu-background.jpeg"));
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        createAndConfigureButtons();
        createAndConfigureTableForMenu();

        //add table to stage
        stage.addActor(mainTable);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);


        spriteBatch.begin();
        spriteBatch.draw(texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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

    //called when switch back to this screen
    @Override
    public void resume() {
        stage.act();
        stage.draw();
    }

    //called when screens switch
    @Override
    public void hide() {
        stage.clear();
    }

    //called on exit
    @Override
    public void dispose() {
        stage.dispose();
    }

    /**
     * All buttons can be created here and configured. Ie, add listeners
     */
    private void createAndConfigureButtons() {
        playButton = new TextButton("Play", AssetManager.preGameScreenButtons());
        exitButton = new TextButton("Exit", AssetManager.preGameScreenButtons());
        helpButton = new TextButton("Help", AssetManager.preGameScreenButtons());
        settingsButton = new TextButton("Settings", AssetManager.preGameScreenButtons());

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
        //add buttons to table
        int tableWidthScalar = 8;
        int tableHeightScalar = 10;

        int width = Gdx.graphics.getWidth() / tableWidthScalar;
        int height = Gdx.graphics.getHeight() / tableHeightScalar;

        mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.setDebug(false);
        mainTable.top();

        mainTable.add(AssetManager.screenTitle(Color.BLACK, "Tanks"));
        mainTable.row();
        mainTable.add(playButton).width(width).height(height);
        mainTable.row();
        mainTable.add(settingsButton).width(width).height(height);
        mainTable.row();
        mainTable.add(helpButton).width(width).height(height);
        mainTable.row();
        mainTable.add(exitButton).width(width).height(height);
    }
}
