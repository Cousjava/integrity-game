package com.github.integritygame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.github.integritygame.util.AssetManager;
import com.github.integritygame.util.VariableManager;

public class GameOverScreen extends AbstractScreen {

    private Stage stage;
    private AssetManager assetManager;

    private TextButton menuButton;
    private TextButton replayButton;

    /**
     * Initialises the Game Over screen with a Stage and an instance of the VariableManager
     */
    public GameOverScreen() {
        stage = new Stage();
        assetManager = AssetManager.getInstance();
    }

    /**
     * Shows the game over screen
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        createAndConfigureButtons();
        createAndConfigureTableForMenu();
    }

    /**
     * Renders the screen with 100% opacity
     *
     * @param delta Timings between actions
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        stage.act();
        stage.draw();
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
    }

    @Override
    public void dispose() {
        stage.clear();
    }

    /**
     * All buttons can be created here and configured. Ie, add listeners
     */
    private void createAndConfigureButtons() {
        menuButton = new TextButton("Main Menu", assetManager.getCustomTextButton());
        replayButton = new TextButton("Play Again", assetManager.getCustomTextButton());

        //add listeners to each button
        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().changeScreen(ScreenManager.Screens.MAIN_MENU);
            }
        });

        replayButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().changeScreen(ScreenManager.Screens.PRE_GAME);
            }
        });
    }

    /**
     * Method to create and configure the main table used for the menu buttons.
     */
    private void createAndConfigureTableForMenu() {

        String victor;

        if (VariableManager.getInstance().getString(VariableManager.VICTOR_KEY).equals(VariableManager.PLAYER_ONE)) {
            victor = VariableManager.getInstance().getString("PlayerOneName");
        } else {
            victor = VariableManager.getInstance().getString("PlayerTwoName");
        }

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.setDebug(false);

        Table summaryTable = new Table();
        summaryTable.setDebug(false);
        summaryTable.row();
        summaryTable.add(assetManager.getText(Color.WHITE, "Proceed to debriefing.", false)).width(150).height(40).align(Align.center);
        summaryTable.row();
        summaryTable.add(assetManager.getText(Color.WHITE, "The game was won by "+victor, false)).width(150).height(40).align(Align.center);
        summaryTable.row();
        summaryTable.add(assetManager.getText(Color.WHITE, "The game was won by method "+VariableManager.getInstance().getVictoryType().toString(), false))
                .width(150).height(40).align(Align.center);
        Table buttonTable = new Table();
        buttonTable.setDebug(false);
        buttonTable.add(menuButton).width(200).height(100);
        buttonTable.add().width(100);
        buttonTable.add(replayButton).width(200).height(100);

        mainTable.add(assetManager.getText(Color.FOREST, "Game Over!", true)).align(Align.center);
        mainTable.row().height(Gdx.graphics.getHeight() / 1.3f).width(Gdx.graphics.getWidth());
        mainTable.add(summaryTable).align(Align.center);
        mainTable.row().height(100);
        mainTable.add(buttonTable);
        stage.addActor(mainTable);
    }
}
