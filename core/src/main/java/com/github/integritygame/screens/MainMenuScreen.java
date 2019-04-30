package com.github.integritygame.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainMenuScreen extends AbstractScreen {

    protected Stage stage;
    private  TextButton.TextButtonStyle buttonStyle;
    BitmapFont font;
    Skin skinButton;
    TextureAtlas buttonAtlas;

    public MainMenuScreen() {
        stage = new Stage();

        //create button
        font = new BitmapFont();
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

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top();

        TextButton playButton = new TextButton("Play", buttonStyle);
        TextButton exitButton = new TextButton("Exit", buttonStyle);

        //add listeners to each button
        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenManager.getInstance().changeScreen(ScreenManager.Screens.MAIN_GAME);
            }
        });

        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        //add buttons to table
        int tableWidthScalar = 4;
        int tableHeightScalar = 3;

        mainTable.add(playButton).width(Gdx.graphics.getWidth()/tableWidthScalar).height(Gdx.graphics.getHeight()/tableHeightScalar);
        mainTable.row();
        mainTable.add(exitButton).width(Gdx.graphics.getWidth()/tableWidthScalar).height(Gdx.graphics.getHeight()/tableHeightScalar);

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

    }

    @Override
    public void hide() {
        skinButton.dispose();
        buttonAtlas.dispose();
        stage.dispose();
    }

    @Override
    public void dispose() {
        skinButton.dispose();
        buttonAtlas.dispose();
        stage.dispose();
    }

}
