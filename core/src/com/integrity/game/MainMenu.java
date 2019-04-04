package com.integrity.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import com.github.integritygame.TanksGame;

/**
 * The main menu of the game
 * 
 * This is what users will first see when the game starts
 * @author jonathan coustick
 */
public class MainMenu implements Screen {
    
    private TanksGame game;
    
    private Texture button;
    
    private Camera camera;
    
    public MainMenu(TanksGame game) {
        this.game = game;
        
        TextureData.Factory.loadFromFile(Gdx.files.internal("tempimage.png"), true);
        button = new Texture("tempimage.png");
        camera = new PerspectiveCamera();
        
    }

    @Override
    public void show() {
        SpriteBatch sprites = game.getSpriteBatch();
        Rectangle startButton = new Rectangle();
        camera.update();
        sprites.begin();
        sprites.draw(button, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        Gdx.app.getGraphics();
        sprites.end();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(float delta) {
       // does nothing, menu is static so nothing new is rendered
       
    }

    @Override
    public void resize(int width, int height) {
        SpriteBatch sprites = game.getSpriteBatch();
        Rectangle startButton = new Rectangle();
        camera.update();
        /*sprites.begin();
        sprites.draw(button, width / 2, height / 2);
        sprites.end();*/
    }

    @Override
    public void pause() {
        // do nothing, as you can't pause the menu
    }

    @Override
    public void resume() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void hide() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dispose() {
        //TODO
    }
    
}
