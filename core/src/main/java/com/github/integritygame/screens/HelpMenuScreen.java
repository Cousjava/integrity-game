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

public class HelpMenuScreen extends AbstractScreen {

    protected Stage stage;
    private TextButton.TextButtonStyle buttonStyle;
    BitmapFont font;
    BitmapFont titleFont;
    Skin skinButton;
    TextureAtlas buttonAtlas;

    Label titleLabel;
    Label helpTextLabel;

    TextButton menuButton;

    Table mainTable;

    String helpText;

    /**
     * Initialises the Help Menu screen, loads all of the helper text, and the font files.
     */
    public HelpMenuScreen() {
        stage = new Stage();

        //create button
        font = new BitmapFont();
        titleFont = new BitmapFont(Gdx.files.internal("fonts/defused.fnt"));
        skinButton = new Skin();
        buttonAtlas = new TextureAtlas("buttons/simpleButton.txt");
        skinButton.addRegions(buttonAtlas);
        buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;

        buttonStyle.up = skinButton.getDrawable("rounded_rectangle_button");
        buttonStyle.down = skinButton.getDrawable("rounded_rectangle_button");
        buttonStyle.checked = skinButton.getDrawable("rounded_rectangle_button");

        helpText = Gdx.files.internal("text/helpText.txt").readString();
    }

    /**
     * Creates buttons, defines label styles, and shows the Help screen
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        createAndConfigureButtons();
        defineLabelStyle();
        createAndConfigureTableForMenu();

        //add table to stage
        stage.addActor(mainTable);
    }

    /**
     * Renders the screen with an opacity of 100%
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
        skinButton.dispose();
        buttonAtlas.dispose();
        stage.dispose();
    }

    /**
     * All buttons can be created here and configured. Ie, add listeners
     */
    private void createAndConfigureButtons() {
        menuButton = new TextButton("Main Menu", buttonStyle);

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

        mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top();

        mainTable.add(titleLabel);
        mainTable.row();
        mainTable.add(helpTextLabel).width(Gdx.graphics.getWidth() - 200).height(Gdx.graphics.getHeight() - ((Gdx.graphics.getHeight() / tableHeightScalar) * 2));
        mainTable.row();
        mainTable.add(menuButton).width(Gdx.graphics.getWidth() / tableWidthScalar).height(Gdx.graphics.getHeight() / tableHeightScalar).align(Align.bottomLeft);
    }

    /**
     * Method to define look and feel of labels
     */
    private void defineLabelStyle() {
        int rowHeight = Gdx.graphics.getWidth() / 2;
        Label.LabelStyle titleLabelStyle = new Label.LabelStyle();
        titleLabelStyle.font = titleFont;
        titleLabelStyle.fontColor = Color.FOREST;

        Label.LabelStyle textLableStyle = new Label.LabelStyle();
        textLableStyle.fontColor = Color.WHITE;
        textLableStyle.font = font;

        titleLabel = new Label("HELP", titleLabelStyle);
        titleLabel.setSize(Gdx.graphics.getWidth(), rowHeight);
        titleLabel.setPosition(0, Gdx.graphics.getHeight() - rowHeight * 40);
        titleLabel.setAlignment(Align.center);
        stage.addActor(titleLabel);

        helpTextLabel = new Label(helpText, textLableStyle);
        helpTextLabel.setAlignment(Align.topLeft);
        helpTextLabel.setWrap(true);
        stage.addActor(helpTextLabel);
    }
}
