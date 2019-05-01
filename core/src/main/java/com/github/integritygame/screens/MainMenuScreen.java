package com.github.integritygame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

public class MainMenuScreen extends AbstractScreen {

    protected Stage stage;
    private  TextButton.TextButtonStyle buttonStyle;
    BitmapFont buttonFont;
    BitmapFont titleFont;
    Skin skinButton;
    TextureAtlas buttonAtlas;

    Label titleLabel;
    Label.LabelStyle labelStyle;

    TextButton playButton;
    TextButton exitButton;
    TextButton helpButton;
    TextButton settingsButton;

    Table mainTable;

    public MainMenuScreen() {
        stage = new Stage();

        buttonFont = new BitmapFont();
        titleFont = new BitmapFont(Gdx.files.internal("fonts/defused.fnt"));
        skinButton = new Skin();
        buttonAtlas = new TextureAtlas("buttons/simpleButton.txt");
        skinButton.addRegions(buttonAtlas);
        buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = buttonFont;

        buttonStyle.up = skinButton.getDrawable("rounded_rectangle_button");
        buttonStyle.down = skinButton.getDrawable("rounded_rectangle_button");
        buttonStyle.checked = skinButton.getDrawable("rounded_rectangle_button");
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        createAndConfigureButtons();
        defineLabelStyle();
        createAndConfigureTableForMenu();

        //add table to stage
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
        skinButton.dispose();
        buttonAtlas.dispose();
        stage.dispose();
    }

    /**
     * All buttons can be created here and configured. Ie, add listeners
     */
    private void createAndConfigureButtons() {
        playButton = new TextButton("Play", buttonStyle);
        exitButton = new TextButton("Exit", buttonStyle);
        helpButton = new TextButton("Help", buttonStyle);
        settingsButton = new TextButton("Settings", buttonStyle);

        //add listeners to each button
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().changeScreen(ScreenManager.Screens.MAIN_GAME);
            }
        });

        helpButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().changeScreen(ScreenManager.Screens.HELP_MENU);
            }
        });

        settingsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().changeScreen(ScreenManager.Screens.SETTINGS_MENU);
            }
        });

        exitButton.addListener(new ClickListener(){
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

        mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top();

        mainTable.add(titleLabel);
        mainTable.row();
        mainTable.add(playButton).width(Gdx.graphics.getWidth()/tableWidthScalar).height(Gdx.graphics.getHeight()/tableHeightScalar);
        mainTable.row();
        mainTable.add(settingsButton).width(Gdx.graphics.getWidth()/tableWidthScalar).height(Gdx.graphics.getHeight()/tableHeightScalar);
        mainTable.row();
        mainTable.add(helpButton).width(Gdx.graphics.getWidth()/tableWidthScalar).height(Gdx.graphics.getHeight()/tableHeightScalar);
        mainTable.row();
        mainTable.add(exitButton).width(Gdx.graphics.getWidth()/tableWidthScalar).height(Gdx.graphics.getHeight()/tableHeightScalar);
    }

    /**
     * Method to define look and feel of labels
     */
    private void defineLabelStyle() {
        int rowHeight = Gdx.graphics.getWidth()/2;
        labelStyle = new Label.LabelStyle();
        labelStyle.font = titleFont;
        labelStyle.fontColor = Color.FOREST;

        titleLabel = new Label("TANKS", labelStyle);
        titleLabel.setSize(Gdx.graphics.getWidth(), rowHeight);
        titleLabel.setPosition(0, Gdx.graphics.getHeight()-rowHeight*40);
        titleLabel.setAlignment(Align.center);
        stage.addActor(titleLabel);
    }
}
