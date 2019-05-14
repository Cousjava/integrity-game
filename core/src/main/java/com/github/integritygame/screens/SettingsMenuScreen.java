package com.github.integritygame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.github.integritygame.MyGdxGame;
import com.github.integritygame.util.AssetManager;
import com.github.integritygame.util.KeyBindingManager;

public class SettingsMenuScreen extends AbstractScreen {

    protected Stage stage;
    private  TextButton.TextButtonStyle buttonStyle;
    BitmapFont font;
    BitmapFont titleFont;
    Skin skinButton;
    TextureAtlas buttonAtlas;

    TextButton menuButton;

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
        buttonAtlas = new TextureAtlas("buttons/simpleButton.txt");
        skinButton.addRegions(buttonAtlas);
        buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;

        buttonStyle.up = skinButton.getDrawable("rounded_rectangle_button");
        buttonStyle.down = skinButton.getDrawable("rounded_rectangle_button");
        buttonStyle.checked = skinButton.getDrawable("rounded_rectangle_button");
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
        menuButton = new TextButton("Main Menu", buttonStyle);

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

        keyBindingsTable.row().height(50);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Player 1 Controls")).align(Align.left);
        keyBindingsTable.row().height(30);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Move Left: ")).width(150);
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText(Input.Keys.toString(keyManager.getLeftLeftMoveKey())));
        keyBindingsTable.row().height(30);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Move Right: ")).width(150);
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText(Input.Keys.toString(keyManager.getLeftRightMoveKey())));
        keyBindingsTable.row().height(30);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Aim Up: ")).width(150);
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText(Input.Keys.toString(keyManager.getLeftAimUpKey())));
        keyBindingsTable.row().height(30);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Aim Down: ")).width(150);
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText(Input.Keys.toString(keyManager.getLeftAimDownKey())));

        keyBindingsTable.row().height(50);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Player 2 Controls")).align(Align.left);
        keyBindingsTable.row().height(30);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Move Left: ")).width(150);
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText(Input.Keys.toString(keyManager.getRightLeftMoveKey())));
        keyBindingsTable.row().height(30);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Move Right: ")).width(150);
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText(Input.Keys.toString(keyManager.getRightRightMoveKey())));
        keyBindingsTable.row().height(30);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Aim Up: ")).width(150);
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText(Input.Keys.toString(keyManager.getRightAimUpKey())));
        keyBindingsTable.row().height(30);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Aim Down: ")).width(150);
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText(Input.Keys.toString(keyManager.getRightAimDownKey())));

        keyBindingsTable.row().height(50);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Fire (both players):")).width(150).align(Align.left);
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText(Input.Keys.toString(keyManager.getFireKey())));


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
