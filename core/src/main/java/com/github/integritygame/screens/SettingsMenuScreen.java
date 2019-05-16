package com.github.integritygame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.github.integritygame.MyGdxGame;
import com.github.integritygame.util.AssetManager;
import com.github.integritygame.util.KeyBindingManager;

/**
 * Setting menu screen to configure keyboard layout and other options
 */
public class SettingsMenuScreen extends AbstractScreen {

    private Stage stage;
    private TextButton.TextButtonStyle buttonStyle;
    private TextButton.TextButtonStyle keyButtonStyle;

    private TextButton menuButton;
    private TextButton changeKeyButtonLeftLeft;

    private TextButton changeKeyButtonLeftRight;
    private TextButton changeKeyButtonLeftUp;
    private TextButton changeKeyButtonLeftDown;
    private TextButton changeKeyButtonRightLeft;
    private TextButton changeKeyButtonRightRight;
    private TextButton changeKeyButtonRightUp;
    private TextButton changeKeyButtonRightDown;
    private TextButton changeKeyButtonFire;
    private TextButton resetControls;
    private TextButton changeKeyToggleBullet;

    private Table mainTable;

    //used to configure key mappings
    private static KeyBindingManager keyManager = MyGdxGame.keyManager;

    /**
     * Initialises the Settings Menu screen
     */
    public SettingsMenuScreen() {
        stage = new Stage();

        //create button
        BitmapFont font = new BitmapFont();
        BitmapFont titleFont = new BitmapFont(Gdx.files.internal("fonts/defused.fnt"));
        Skin skinButton = new Skin();
        TextureAtlas buttonAtlas = new TextureAtlas("buttons/simpleButton.txt");
        skinButton.addRegions(buttonAtlas);
    }

    /**
     * Creates and configures the buttons and tables
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        createAndConfigureButtons();
        createAndConfigureTableForMenu();

        //add table to stage
        stage.addActor(mainTable);
    }

    /**
     * Renders the page elements to the screen medium
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
        menuButton = new TextButton("Menu", AssetManager.preGameScreenButtons());
        changeKeyButtonLeftLeft = AssetManager.settingsTextButton("Configure...");
        changeKeyButtonLeftRight = AssetManager.settingsTextButton("Configure...");
        changeKeyButtonLeftUp = AssetManager.settingsTextButton("Configure...");
        changeKeyButtonLeftDown = AssetManager.settingsTextButton("Configure...");
        changeKeyButtonRightLeft = AssetManager.settingsTextButton("Configure...");
        changeKeyButtonRightRight = AssetManager.settingsTextButton("Configure...");
        changeKeyButtonRightUp = AssetManager.settingsTextButton("Configure...");
        changeKeyButtonRightDown = AssetManager.settingsTextButton("Configure...");
        changeKeyButtonFire = AssetManager.settingsTextButton("Configure...");
        changeKeyToggleBullet = AssetManager.settingsTextButton("Configure...");
        resetControls = AssetManager.settingsTextButton("Restore Defaults");


        //add listeners to each button
        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().changeScreen(ScreenManager.Screens.MAIN_MENU);
            }
        });

        changeKeyButtonLeftLeft.addListener(new Listener(KeyBindingManager.ConfigurableKeys.LEFT_LEFT_MOVE).resolve());
        changeKeyButtonLeftRight.addListener(new Listener(KeyBindingManager.ConfigurableKeys.LEFT_RIGHT_MOVE).resolve());
        changeKeyButtonLeftUp.addListener(new Listener(KeyBindingManager.ConfigurableKeys.LEFT_AIM_UP).resolve());
        changeKeyButtonLeftDown.addListener(new Listener(KeyBindingManager.ConfigurableKeys.LEFT_AIM_DOWN).resolve());
        changeKeyButtonRightLeft.addListener(new Listener(KeyBindingManager.ConfigurableKeys.RIGHT_LEFT_MOVE).resolve());
        changeKeyButtonRightRight.addListener(new Listener(KeyBindingManager.ConfigurableKeys.RIGHT_RIGHT_MOVE).resolve());
        changeKeyButtonRightUp.addListener(new Listener(KeyBindingManager.ConfigurableKeys.RIGHT_AIM_UP).resolve());
        changeKeyButtonRightDown.addListener(new Listener(KeyBindingManager.ConfigurableKeys.RIGHT_AIM_DOWN).resolve());
        changeKeyButtonFire.addListener(new Listener(KeyBindingManager.ConfigurableKeys.FIRE).resolve());
        changeKeyToggleBullet.addListener(new Listener(KeyBindingManager.ConfigurableKeys.BULLET_TOGGLE).resolve());

        resetControls.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                KeyBindingManager.resetDefaultKeys();
                ScreenManager.getInstance().changeScreen(ScreenManager.Screens.SETTINGS_MENU);
            }
        });
    }

    /**
     * Method to create and configure the main table used for the menu buttons.
     */
    private void createAndConfigureTableForMenu() {

        Table keyBindingsTable = new Table();
        keyBindingsTable.row().height(70);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Define custom keyboard layouts."));

        keyBindingsTable.row().height(50);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Player 1 Controls")).align(Align.left);
        keyBindingsTable.row().height(30);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Move Left: ")).width(150);
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText(Input.Keys.toString(keyManager.keyMap.get(KeyBindingManager.ConfigurableKeys.LEFT_LEFT_MOVE))));
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(changeKeyButtonLeftLeft).width(100).height(20);
        keyBindingsTable.row().height(30);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Move Right: ")).width(150);
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText(Input.Keys.toString(keyManager.keyMap.get(KeyBindingManager.ConfigurableKeys.LEFT_RIGHT_MOVE))));
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(changeKeyButtonLeftRight).width(100).height(20);
        keyBindingsTable.row().height(30);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Aim Up: ")).width(150);
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText(Input.Keys.toString(keyManager.keyMap.get(KeyBindingManager.ConfigurableKeys.LEFT_AIM_UP))));
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(changeKeyButtonLeftUp).width(100).height(20);
        keyBindingsTable.row().height(30);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Aim Down: ")).width(150);
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText(Input.Keys.toString(KeyBindingManager.keyMap.get(KeyBindingManager.ConfigurableKeys.LEFT_AIM_DOWN))));
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(changeKeyButtonLeftDown).width(100).height(20);

        keyBindingsTable.row().height(50);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Player 2 Controls")).align(Align.left);
        keyBindingsTable.row().height(30);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Move Left: ")).width(150);
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText(Input.Keys.toString(KeyBindingManager.keyMap.get(KeyBindingManager.ConfigurableKeys.RIGHT_LEFT_MOVE))));
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(changeKeyButtonRightLeft).width(100).height(20);
        keyBindingsTable.row().height(30);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Move Right: ")).width(150);
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText(Input.Keys.toString(KeyBindingManager.keyMap.get(KeyBindingManager.ConfigurableKeys.RIGHT_RIGHT_MOVE))));
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(changeKeyButtonRightRight).width(100).height(20);
        keyBindingsTable.row().height(30);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Aim Up: ")).width(150);
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText(Input.Keys.toString(KeyBindingManager.keyMap.get(KeyBindingManager.ConfigurableKeys.RIGHT_AIM_UP))));
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(changeKeyButtonRightUp).width(100).height(20);
        keyBindingsTable.row().height(30);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Aim Down: ")).width(150);
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText(Input.Keys.toString(KeyBindingManager.keyMap.get(KeyBindingManager.ConfigurableKeys.RIGHT_AIM_DOWN))));
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(changeKeyButtonRightDown).width(100).height(20);

        keyBindingsTable.row().height(50);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Shared Controls")).align(Align.left);
        keyBindingsTable.row().height(30);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Fire:")).width(150).align(Align.left);
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText(Input.Keys.toString(KeyBindingManager.keyMap.get(KeyBindingManager.ConfigurableKeys.FIRE))));
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(changeKeyButtonFire).width(100).height(20);
        keyBindingsTable.row().height(30);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText("Toggle Bullet Type:")).width(150).align(Align.left);
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(AssetManager.labelSimpleWhiteText(Input.Keys.toString(KeyBindingManager.keyMap.get(KeyBindingManager.ConfigurableKeys.BULLET_TOGGLE))));
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(changeKeyToggleBullet).width(100).height(20);

        keyBindingsTable.row().height(50);
        keyBindingsTable.add(resetControls).width(130).height(20).align(Align.left);


        mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top();

        mainTable.add(AssetManager.screenTitle(Color.FOREST, "Settings"));
        mainTable.row().height(Gdx.graphics.getHeight() / 1.3f).width(Gdx.graphics.getWidth() - 50);
        mainTable.add(keyBindingsTable);
        mainTable.row().height(30);
        mainTable.add(menuButton).width(200).height(100).align(Align.bottomLeft);
    }

    /**
     *
     */
    private class Listener {

        private final ClickListener listener;

        Listener(final KeyBindingManager.ConfigurableKeys key) {
            listener = new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Gdx.input.setInputProcessor(new InputAdapter() {
                        @Override
                        public boolean keyDown(int keycode) {
                            KeyBindingManager.setKey(key, keycode);
                            ScreenManager.getInstance().changeScreen(ScreenManager.Screens.SETTINGS_MENU);
                            return false;
                        }
                    });
                }
            };
        }

        public ClickListener resolve() {
            return listener;
        }
    }
}
