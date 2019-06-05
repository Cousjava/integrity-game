package com.github.integritygame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.github.integritygame.util.AssetManager;

public class HelpMenuScreen extends AbstractScreen {

    private Stage stage;
    private AssetManager assetManager;

    private TextButton menuButton;

    /**
     * Initialises the Help Menu screen, loads all of the helper text, and the font files.
     */
    public HelpMenuScreen() {
        stage = new Stage();
        assetManager = AssetManager.getInstance();
    }

    /**
     * Creates buttons, defines label styles, and shows the Help screen
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        createAndConfigureButtons();
        createAndConfigureTableForMenu();
    }

    /**
     * Renders the screen with an opacity of 100%
     *
     * @param delta Time between actions
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
        stage.act();
        stage.draw();
    }

    @Override
    public void hide() {
        stage.clear();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    /**
     * All buttons can be created here and configured. Ie, add listeners
     */
    private void createAndConfigureButtons() {
        menuButton = new TextButton("Main Menu", assetManager.getCustomTextButton());

        //add listeners to each button
        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().changeScreen(ScreenManager.Screens.MAIN_MENU);
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

        Label help = assetManager.getText(Color.WHITE, assetManager.getHelpText(), false);
        help.setWrap(true);

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top();

        mainTable.add(assetManager.getText(Color.FOREST, "Help", true));
        mainTable.row();
        mainTable.add(help).width(Gdx.graphics.getWidth() - 200).height(Gdx.graphics.getHeight() - ((Gdx.graphics.getHeight() / tableHeightScalar) * 2));
        mainTable.row();
        mainTable.add(menuButton).width(Gdx.graphics.getWidth() / tableWidthScalar).height(Gdx.graphics.getHeight() / tableHeightScalar).align(Align.bottomLeft);
        stage.addActor(mainTable);
    }

}
