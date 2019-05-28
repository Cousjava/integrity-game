package com.github.integritygame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.github.integritygame.util.AssetManager;
import com.github.integritygame.util.VariableManager;

import java.util.regex.Pattern;

public class PreGameScreen extends AbstractScreen {

    private Stage stage;
    private VariableManager variableManager;

    private TextButton menuButton;
    private TextButton playButtonGrass;
    private TextButton playButtonDesert;
    private Table mainTable;
    private Table playerTable;

    private TextField nameOne;
    private TextField nameTwo;

    private boolean explicit;
    private boolean name;

    /**
     * Initializes the pre-game screen
     */
    public PreGameScreen() {
        stage = new Stage();
        variableManager = VariableManager.getInstance();
    }

    /**
     * Creates and configures buttons and tables as well as populating the
     * name labels with the usernames given previously
     */
    @Override
    public void show() {
        explicit = true;
        name = true;
        createAndConfigureButtons();
        createAndConfigureTableForMenu();
        nameOne.setText(variableManager.getString("PlayerOneName"));
        nameTwo.setText(variableManager.getString("PlayerTwoName"));
        Gdx.input.setInputProcessor(stage);
        stage.addActor(mainTable);
    }

    /**
     * Renders page elements onto the screen medium
     * @param delta Delay between actions
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

    }

    /**
     * All buttons can be created here and configured. Ie, add listeners
     */
    private void createAndConfigureButtons() {
        menuButton = new TextButton("Main Menu", AssetManager.preGameScreenButtons());
        playButtonGrass = new TextButton("Play Grass", AssetManager.preGameScreenButtons());
        playButtonDesert = new TextButton("Play Desert", AssetManager.preGameScreenButtons());

        //add listeners to each button
        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                variableManager.setString("PlayerOneName", nameOne.getText());
                variableManager.setString("PlayerTwoName", nameTwo.getText());
                ScreenManager.getInstance().changeScreen(ScreenManager.Screens.MAIN_MENU);
            }
        });

        playButtonGrass.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                variableManager.setString("Background", "Grass");
                init();
            }
        });

        playButtonDesert.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                variableManager.setString("Background", "Desert");
                init();
            }
        });
    }

    /**
     * Initialises the service to record the username and verify it
     */
    public void init() {
        Pattern pattern = Pattern.compile("(?i)fuck|shit|wanker|twat");
        if (pattern.matcher(nameOne.getText()).find() || pattern.matcher(nameTwo.getText()).find() || nameOne.getText().length() < 3 || nameOne.getText().length() >= 20 || nameTwo.getText().length() < 3 || nameTwo.getText().length() >= 20) {
            if (pattern.matcher(nameOne.getText()).find() || pattern.matcher(nameTwo.getText()).find()) {
                if (explicit) {
                    playerTable.row().height(30);
                    playerTable.add(AssetManager.labelSimpleWhiteText("Explicit Names Not Allowed")).colspan(3);
                }
                explicit = false;
            }
            if (nameOne.getText().length() < 3 || nameOne.getText().length() >= 20 || nameTwo.getText().length() < 3 || nameTwo.getText().length() >= 20) {
                if (name) {
                    playerTable.row().height(30);
                    playerTable.add(AssetManager.labelSimpleWhiteText("Name must be between 3 - 20 characters")).colspan(3);
                }
                name = false;
            }
            return;
        }
        variableManager.setString("PlayerOneName", nameOne.getText());
        variableManager.setString("PlayerTwoName", nameTwo.getText());
        ScreenManager.getInstance().changeScreen(ScreenManager.Screens.MAIN_GAME);
    }

    /**
     * Method to create and configure the main table used for the menu buttons.
     */
    private void createAndConfigureTableForMenu() {
        nameOne = AssetManager.skinnedTextField();
        nameTwo = AssetManager.skinnedTextField();

        mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.setDebug(false);

        playerTable = new Table();
        //playerTable.setFillParent(true);
        playerTable.setDebug(false);
        playerTable.add(AssetManager.labelSimpleWhiteText("Player 1 Name: ")).width(150).height(40);
        playerTable.add().width(10);
        playerTable.add(nameOne).width(150);
        playerTable.row();
        playerTable.add(AssetManager.labelSimpleWhiteText("Player 2 Name: ")).width(150).height(40);
        playerTable.add().width(10);
        playerTable.add(nameTwo).width(150);

        Table buttonTable = new Table();
        buttonTable.setDebug(false);
        buttonTable.add(menuButton).width(200).height(100);
        buttonTable.add().width(100);
        buttonTable.add(playButtonGrass).width(200).height(100);
        buttonTable.add().width(100);
        buttonTable.add(playButtonDesert).width(200).height(100);

        mainTable.add(AssetManager.screenTitle(Color.FOREST, "Operation Briefing...")).align(Align.center);
        mainTable.row().height(Gdx.graphics.getHeight() / 1.3f).width(Gdx.graphics.getWidth());
        mainTable.add(playerTable).align(Align.center);
        mainTable.row().height(100);
        mainTable.add(buttonTable);
    }
}
