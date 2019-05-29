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
    private AssetManager assetManager;

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

    //used to configure key mappings
    private static KeyBindingManager keyManager = MyGdxGame.keyManager;

    /**
     * Initialises the Settings Menu screen
     */
    public SettingsMenuScreen() {
        stage = new Stage();
        assetManager = AssetManager.getInstance();
    }

    /**
     * Creates and configures the buttons and tables
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        createAndConfigureButtons();
        createAndConfigureTableForMenu();
    }

    /**
     * Renders the page elements to the screen medium
     * @param delta Delay between actions
     */
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
        menuButton = new TextButton("Menu", assetManager.getCustomTextButton());
        changeKeyButtonLeftLeft = assetManager.getTextButton("Configure...");
        changeKeyButtonLeftRight = assetManager.getTextButton("Configure...");
        changeKeyButtonLeftUp = assetManager.getTextButton("Configure...");
        changeKeyButtonLeftDown = assetManager.getTextButton("Configure...");
        changeKeyButtonRightLeft = assetManager.getTextButton("Configure...");
        changeKeyButtonRightRight = assetManager.getTextButton("Configure...");
        changeKeyButtonRightUp = assetManager.getTextButton("Configure...");
        changeKeyButtonRightDown = assetManager.getTextButton("Configure...");
        changeKeyButtonFire = assetManager.getTextButton("Configure...");
        changeKeyToggleBullet = assetManager.getTextButton("Configure...");
        resetControls = assetManager.getTextButton("Restore Defaults");


        //add listeners to each button
        menuButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().changeScreen(ScreenManager.Screens.MAIN_MENU);
            }
        });

        changeKeyButtonLeftLeft.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(new InputAdapter() {
                    @Override
                    public boolean keyDown(int keycode) {
                        keyManager.setKey(KeyBindingManager.ConfigurableKeys.LEFT_LEFT_MOVE, keycode);
                        ScreenManager.getInstance().changeScreen(ScreenManager.Screens.SETTINGS_MENU);
                        return false;
                    }
                });
            }
        });

        changeKeyButtonLeftRight.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(new InputAdapter() {
                    @Override
                    public boolean keyDown(int keycode) {
                        keyManager.setKey(KeyBindingManager.ConfigurableKeys.LEFT_RIGHT_MOVE, keycode);
                        ScreenManager.getInstance().changeScreen(ScreenManager.Screens.SETTINGS_MENU);
                        return false;
                    }
                });
            }
        });

        changeKeyButtonLeftUp.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(new InputAdapter() {
                    @Override
                    public boolean keyDown(int keycode) {
                        keyManager.setKey(KeyBindingManager.ConfigurableKeys.LEFT_AIM_UP, keycode);
                        ScreenManager.getInstance().changeScreen(ScreenManager.Screens.SETTINGS_MENU);
                        return false;
                    }
                });
            }
        });

        changeKeyButtonLeftDown.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(new InputAdapter() {
                    @Override
                    public boolean keyDown(int keycode) {
                        keyManager.setKey(KeyBindingManager.ConfigurableKeys.LEFT_AIM_DOWN, keycode);
                        ScreenManager.getInstance().changeScreen(ScreenManager.Screens.SETTINGS_MENU);
                        return false;
                    }
                });
            }
        });

        changeKeyButtonRightLeft.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(new InputAdapter() {
                    @Override
                    public boolean keyDown(int keycode) {
                        keyManager.setKey(KeyBindingManager.ConfigurableKeys.RIGHT_LEFT_MOVE, keycode);
                        ScreenManager.getInstance().changeScreen(ScreenManager.Screens.SETTINGS_MENU);
                        return false;
                    }
                });
            }
        });

        changeKeyButtonRightRight.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(new InputAdapter() {
                    @Override
                    public boolean keyDown(int keycode) {
                        keyManager.setKey(KeyBindingManager.ConfigurableKeys.RIGHT_RIGHT_MOVE, keycode);
                        ScreenManager.getInstance().changeScreen(ScreenManager.Screens.SETTINGS_MENU);
                        return false;
                    }
                });
            }
        });

        changeKeyButtonRightUp.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(new InputAdapter() {
                    @Override
                    public boolean keyDown(int keycode) {
                        keyManager.setKey(KeyBindingManager.ConfigurableKeys.RIGHT_AIM_UP, keycode);
                        ScreenManager.getInstance().changeScreen(ScreenManager.Screens.SETTINGS_MENU);
                        return false;
                    }
                });
            }
        });

        changeKeyButtonRightDown.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(new InputAdapter() {
                    @Override
                    public boolean keyDown(int keycode) {
                        keyManager.setKey(KeyBindingManager.ConfigurableKeys.RIGHT_AIM_DOWN, keycode);
                        ScreenManager.getInstance().changeScreen(ScreenManager.Screens.SETTINGS_MENU);
                        return false;
                    }
                });
            }
        });

        changeKeyButtonFire.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(new InputAdapter() {
                    @Override
                    public boolean keyDown(int keycode) {
                        keyManager.setKey(KeyBindingManager.ConfigurableKeys.FIRE, keycode);
                        ScreenManager.getInstance().changeScreen(ScreenManager.Screens.SETTINGS_MENU);
                        return false;
                    }
                });
            }
        });

        changeKeyToggleBullet.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.input.setInputProcessor(new InputAdapter() {
                    @Override
                    public boolean keyDown(int keycode) {
                        keyManager.setKey(KeyBindingManager.ConfigurableKeys.BULLET_TOGGLE, keycode);
                        ScreenManager.getInstance().changeScreen(ScreenManager.Screens.SETTINGS_MENU);
                        return false;
                    }
                });
            }
        });

        resetControls.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                keyManager.resetDefaultKeys();
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
        keyBindingsTable.add(assetManager.getTextAsWhiteNonTitle("Define custom keyboard layouts."));
        keyBindingsTable.row().height(50);
        keyBindingsTable.add(assetManager.getTextAsWhiteNonTitle("Player 1 Controls")).align(Align.left);
        keyBindingsTable.row().height(30);


        controlItem(keyBindingsTable, "Move Left: ", KeyBindingManager.ConfigurableKeys.LEFT_LEFT_MOVE, changeKeyButtonLeftLeft, 30);
        controlItem(keyBindingsTable, "Move Right: ", KeyBindingManager.ConfigurableKeys.LEFT_RIGHT_MOVE, changeKeyButtonLeftRight, 30);
        controlItem(keyBindingsTable, "Aim Up: ", KeyBindingManager.ConfigurableKeys.LEFT_AIM_UP, changeKeyButtonLeftUp, 30);
        controlItem(keyBindingsTable, "Aim Down: ", KeyBindingManager.ConfigurableKeys.LEFT_AIM_DOWN, changeKeyButtonLeftDown, 30);

        keyBindingsTable.row().height(20);
        keyBindingsTable.add(assetManager.getTextAsWhiteNonTitle("Player 2 Controls")).align(Align.left);
        keyBindingsTable.row().height(30);

        controlItem(keyBindingsTable, "Move Left: ", KeyBindingManager.ConfigurableKeys.RIGHT_LEFT_MOVE, changeKeyButtonRightLeft, 30);
        controlItem(keyBindingsTable, "Move Right: ", KeyBindingManager.ConfigurableKeys.RIGHT_RIGHT_MOVE, changeKeyButtonRightRight, 30);
        controlItem(keyBindingsTable, "Aim Up: ", KeyBindingManager.ConfigurableKeys.RIGHT_AIM_UP, changeKeyButtonRightUp, 30);
        controlItem(keyBindingsTable, "Aim Down: ", KeyBindingManager.ConfigurableKeys.RIGHT_AIM_DOWN, changeKeyButtonRightDown, 50);


        keyBindingsTable.add(assetManager.getTextAsWhiteNonTitle("Shared Controls")).align(Align.left);
        keyBindingsTable.row().height(30);
        keyBindingsTable.add(assetManager.getTextAsWhiteNonTitle("Fire:")).width(150).align(Align.left);
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(assetManager.getTextAsWhiteNonTitle(Input.Keys.toString(keyManager.keyMap.get(KeyBindingManager.ConfigurableKeys.FIRE))));
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(changeKeyButtonFire).width(100).height(20);
        keyBindingsTable.row().height(30);
        keyBindingsTable.add(assetManager.getTextAsWhiteNonTitle("Toggle Bullet Type:")).width(150).align(Align.left);
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(assetManager.getTextAsWhiteNonTitle(Input.Keys.toString(keyManager.keyMap.get(KeyBindingManager.ConfigurableKeys.BULLET_TOGGLE))));
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(changeKeyToggleBullet).width(100).height(20);
        keyBindingsTable.row().height(50);
        keyBindingsTable.add(resetControls).width(130).height(20).align(Align.left);


        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top();

        mainTable.add(assetManager.getText(Color.FOREST, "Settings", true));
        mainTable.row().height(Gdx.graphics.getHeight()/1.3f).width(Gdx.graphics.getWidth()-50);
        mainTable.add(keyBindingsTable);
        mainTable.row().height(30);
        mainTable.add(menuButton).width(200).height(100).align(Align.bottomLeft);
        stage.addActor(mainTable);
    }

    private void controlItem(Table keyBindingsTable, String s, KeyBindingManager.ConfigurableKeys leftLeftMove, TextButton changeKeyButtonLeftLeft, int i) {
        keyBindingsTable.add(assetManager.getTextAsWhiteNonTitle(s)).width(150);
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(assetManager.getTextAsWhiteNonTitle(Input.Keys.toString(keyManager.keyMap.get(leftLeftMove))));
        keyBindingsTable.add().width(10);
        keyBindingsTable.add(changeKeyButtonLeftLeft).width(100).height(20);
        keyBindingsTable.row().height(i);
    }


}
