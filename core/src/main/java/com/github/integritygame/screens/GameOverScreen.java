package com.github.integritygame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.github.integritygame.util.AssetManager;
import com.github.integritygame.util.VariableManager;

public class GameOverScreen extends AbstractScreen {

    private Stage stage;
    private VariableManager variableManager;

    private TextButton menuButton;
    private TextButton replayButton;
    private Table mainTable;
    private Table summaryTable;

    private TextField nameOne;
    private TextField nameTwo;

    private boolean explicit;
    private boolean name;

    public GameOverScreen() {
        stage = new Stage();
        variableManager = VariableManager.getInstance();
    }

    @Override
    public void show() {
        explicit = true;
        name = true;

        createAndConfigureButtons();
        createAndConfigureTableForMenu();

        Gdx.input.setInputProcessor(stage);
        stage.addActor(mainTable);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor( 0, 0, 0, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

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

    }

    @Override
    public void dispose() {

    }

    /**
     * All buttons can be created here and configured. Ie, add listeners
     */
    private void createAndConfigureButtons() {
        menuButton = new TextButton("Main Menu", AssetManager.preGameScreenButtons());
        replayButton = new TextButton("Play Again", AssetManager.preGameScreenButtons());

        //add listeners to each button
        menuButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().changeScreen(ScreenManager.Screens.MAIN_MENU);
            }
        });

        replayButton.addListener(new ClickListener(){
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

        mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.setDebug(false);

        summaryTable = new Table();
        summaryTable.setDebug(false);
        summaryTable.add(AssetManager.labelSimpleWhiteText("The winner was :")).width(150).height(40);
        summaryTable.row();
        summaryTable.add(AssetManager.labelSimpleWhiteText("Proceed to debriefing.")).width(150).height(40);

        Table buttonTable = new Table();
        buttonTable.setDebug(false);
        buttonTable.add(menuButton).width(200).height(100);
        buttonTable.add().width(100);
        buttonTable.add(replayButton).width(200).height(100);

        mainTable.add(AssetManager.screenTitle(Color.FOREST, "Game Over!")).align(Align.center);
        mainTable.row().height(Gdx.graphics.getHeight()/1.3f).width(Gdx.graphics.getWidth());
        mainTable.add(summaryTable).align(Align.center);
        mainTable.row().height(100);
        mainTable.add(buttonTable);
    }
}
