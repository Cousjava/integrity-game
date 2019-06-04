package com.github.integritygame;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.SharedLibraryLoader;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.mockito.Mockito;

public class GameTest {

    private static Application application;
    
    protected static World gameWorld;

    @BeforeClass
    public static void init() {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Testing in progress... Please Wait...";
        config.height = 720;
        config.width = 1280;
        config.resizable = false;

        application = new LwjglApplication(new ApplicationListener() {
            @Override
            public void create() {
                SharedLibraryLoader libraryLoader = new SharedLibraryLoader();
                libraryLoader.load("gdx-box2d");
            }

            @Override
            public void resize(int width, int height) {
            }

            @Override
            public void render() {
            }

            @Override
            public void pause() {
            }

            @Override
            public void resume() {
            }

            @Override
            public void dispose() {
            }
        }, config);

        Gdx.gl20 = Mockito.mock(GL20.class);
        Gdx.gl = Gdx.gl20;
        
        gameWorld = new World(new Vector2(), false);gameWorld.step(0, 0, 0);
        gameWorld.setContinuousPhysics(true);
        gameWorld.setWarmStarting(true);
    }

    @AfterClass
    public static void cleanUp() {
        Gdx.app.exit();
    }
}