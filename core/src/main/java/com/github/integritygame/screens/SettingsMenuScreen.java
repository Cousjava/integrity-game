package com.github.integritygame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.github.integritygame.MyGdxGame;
import com.github.integritygame.util.AssetManager;
import com.github.integritygame.util.KeyBindingManager;

public class SettingsMenuScreen extends AbstractScreen {

    protected Stage stage;
    private  TextButton.TextButtonStyle buttonStyle;
    private TextButton.TextButtonStyle keyButtonStyle;
    BitmapFont font;
    BitmapFont titleFont;
    Skin skinButton;
    TextureAtlas buttonAtlas;

    TextButton menuButton;
    TextButton changeKeyButton1;

    TextButton changeKeyButton2;
    TextButton changeKeyButton3;
    TextButton changeKeyButton4;
    TextButton changeKeyButton5;
    TextButton changeKeyButton6;
    TextButton changeKeyButton7;
    TextButton changeKeyButton8;
    TextButton changeKeyButton9;

    Table mainTable;
    Table keyBindingsTable;

    //used to configure key mappings
    private static KeyBindingManager keyManager = MyGdxGame.keyManager;

    public SettingsMenuScreen() {
        stage = new Stage();

        //create button
        font = new BitmapFont();
        titleFont = new BitmapFont(Gdx.files.internal("fonts/defused.fnt"));
        skinButton = new Skin();
        buttonAtlas = new TextureAtlas("buttons/menuButton/simpleButton.txt");
        skinButton.addRegions(buttonAtlas);
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
        menuButton = new TextButton("Menu",AssetManager.preGameScreenButtons());
        changeKeyButton1 = AssetManager.settingsTextButton("Configure...");
        changeKeyButton2 = AssetManager.settingsTextButton("Configure...");
        changeKeyButton3 = AssetManager.settingsTextButton("Configure...");
        changeKeyButton4 = AssetManager.settingsTextButton("Configure...");
        changeKeyButton5 = AssetManager.settingsTextButton("Configure...");
        changeKeyButton6 = AssetManager.settingsTextButton("Configure...");
        changeKeyButton7 = AssetManager.settingsTextButton("Configure...");
        changeKeyButton8 = AssetManager.settingsTextButton("Configure...");
        changeKeyButton9 = AssetManager.settingsTextButton("Configure...");

        //add listeners to each button
        menuButton.addListener(new ClickListener(){
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

        keyBindingsTable = new Table();
        keyBindingsTable.row().height(70);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Define custom keyboard layouts."));

        //TODO - small button on each to change the value - input listener as seen on game
        //     - button will get input > check for duplicates > save value > reload settings menu to update table or show error msg

        keyBindingsTable.row().height(50);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Player 1 Controls")).align(Align.left);
        keyBindingsTable.row().height(30);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Move Left: ")).width(150);
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText(Input.Keys.toString(keyManager.getLeftLeftMoveKey())));
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(changeKeyButton1).width(100).height(20);
        keyBindingsTable.row().height(30);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Move Right: ")).width(150);
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText(Input.Keys.toString(keyManager.getLeftRightMoveKey())));
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(changeKeyButton2).width(100).height(20);
        keyBindingsTable.row().height(30);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Aim Up: ")).width(150);
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText(Input.Keys.toString(keyManager.getLeftAimUpKey())));
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(changeKeyButton3).width(100).height(20);
        keyBindingsTable.row().height(30);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Aim Down: ")).width(150);
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText(Input.Keys.toString(keyManager.getLeftAimDownKey())));
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(changeKeyButton4).width(100).height(20);

        keyBindingsTable.row().height(50);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Player 2 Controls")).align(Align.left);
        keyBindingsTable.row().height(30);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Move Left: ")).width(150);
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText(Input.Keys.toString(keyManager.getRightLeftMoveKey())));
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(changeKeyButton5).width(100).height(20);
        keyBindingsTable.row().height(30);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Move Right: ")).width(150);
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText(Input.Keys.toString(keyManager.getRightRightMoveKey())));
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(changeKeyButton6).width(100).height(20);
        keyBindingsTable.row().height(30);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Aim Up: ")).width(150);
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText(Input.Keys.toString(keyManager.getRightAimUpKey())));
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(changeKeyButton7).width(100).height(20);
        keyBindingsTable.row().height(30);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Aim Down: ")).width(150);
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText(Input.Keys.toString(keyManager.getRightAimDownKey())));
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(changeKeyButton8).width(100).height(20);

        keyBindingsTable.row().height(50);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Fire (both players):")).width(150).align(Align.left);
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText(Input.Keys.toString(keyManager.getFireKey())));
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(changeKeyButton9).width(100).height(20);


        mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top();

        mainTable.add(AssetManager.screenTitle(Color.FOREST, "Settings"));
        mainTable.row().height(Gdx.graphics.getHeight()/1.3f).width(Gdx.graphics.getWidth()-50);
        mainTable.add(keyBindingsTable);
        mainTable.row().height(30);
        mainTable.add(menuButton).width(200).height(100).align(Align.bottomLeft);
    }
}
