package com.github.integritygame.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.github.integritygame.objects.*;
import com.github.integritygame.screens.ScreenManager;
import com.integrity.games.world.GameWorld;
import static java.lang.Math.round;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GameManager {

    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;

    private UserTurn userA;
    private UserTurn userB;

    private BulletsController bullets;
    private TurnManager<UserTurn> turnManager;
    private Hud hud;

    //TODO: Be able to calculate this from background
    private GameWorld game;
    private static final int START_HEIGHT = 500;

    private int graphicsWidth;
    private int graphicsHeight;
    
    private Pixmap transparentLayer;
    private TextureData data;
    private Texture imageMap;

    /**
     * Create a game manager
     *
     * @param graphicsWidth  Width of the screen
     * @param graphicsHeight Height of the screen
     * @param spriteBatch    SpriteBatch so we can render them instead of creating a new one
     * @param shapeRenderer  ShapeRenderer so we can render them instead of creating a new one
     */
    public GameManager(int graphicsWidth, int graphicsHeight, SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        this.spriteBatch = spriteBatch;
        this.shapeRenderer = shapeRenderer;
        this.graphicsWidth = graphicsWidth;
        this.graphicsHeight = graphicsHeight;

        configureTanksAndUserTurn();
        configureHud();
        configureGameWorld();

    }

    private void configureTanksAndUserTurn() {
        userA = new UserTurn(new Tank(30, 180), InputManager.Control.LEFT);
        userB = new UserTurn(new Tank(graphicsWidth - 110, 180), InputManager.Control.RIGHT);
        userB.getTank().setTexture(AssetManager.TankStyles.LIGHT_GREEN_TANK);

        turnManager = new TurnManager<>(new LinkedList<>(Arrays.asList(userA, userB)));
        userA.setInputManager(turnManager);
        userB.setInputManager(turnManager);

    }

    private void configureHud() {
        hud = new Hud(graphicsWidth, graphicsHeight);
        List<PlayerHud> players = hud.getPlayerHuds();
        players.get(0).setTank(userA.getTank());
        players.get(1).setTank(userB.getTank());
    }

    private void configureGameWorld() {
        EdgeShape terrain = new EdgeShape();
        terrain.set(0, START_HEIGHT, graphicsWidth, START_HEIGHT);
        game = new GameWorld(terrain);
        transparentLayer = setTransparentGround(game.getTerrain());
        data = TextureData.Factory.loadFromFile(Gdx.files.internal("backgrounds/green-ground.jpeg"), true);
        data.prepare();
        Pixmap producedimage = data.consumePixmap();
        producedimage.drawPixmap(transparentLayer, 0, 0);
        imageMap = new Texture(producedimage);
        userA.getTank().setTankBody(game.addTank(30, START_HEIGHT + 1, userA.getTank()));
        userB.getTank().setTankBody(game.addTank(graphicsWidth - 110, START_HEIGHT + 1, userB.getTank()));
        bullets = new BulletsController(graphicsWidth, graphicsHeight, game);
    }
    
    public Pixmap setTransparentGround(Vector2[] points) {
        float maxheight = 0;
        for (Vector2 point: points) {
            if ((point.y) > maxheight) {
                maxheight = point.y;
            }
        }
        Pixmap transparentPixels = new Pixmap(graphicsWidth, graphicsHeight, Pixmap.Format.Alpha);
        transparentPixels.setColor(0, 0, 0, 1);
        
        Vector2 previous = points[0];
        
        //Deliberatly starting from 1, not zero
        for (int i = 1; i < points.length; i++) {
            Vector2 current = points[i];
            
            int rectY = Math.round(Math.max(previous.y, current.y));
            transparentPixels.fillRectangle(round(previous.x), graphicsHeight, round(current.x - previous.x), graphicsHeight - rectY);
            
            //third point of a triangle to fill
            Vector2 third = getThirdPoint(previous, current);
            //round comes from Math.round
            transparentPixels.fillTriangle(round(previous.x), round(previous.y), round(current.x), round(current.y), round(third.x), round(third.y));
            
            previous = current;
        }
        return transparentPixels;
    }
    
    /**
     * Gets the third point of a triangle with the highest y-coord of the pair
     * and the x-point of the other
     * @param first
     * @param second
     * @return 
     */
    private Vector2 getThirdPoint(Vector2 first, Vector2 second) {
        if (first.y > second.y) {
            return new Vector2(second.x, first.y);
        } else {
            return new Vector2(first.x, second.y);
        }
    }
    
    

    /**
     * This will render everything on screen along with executing code that should be done with every frame
     */
    public void render(float delta) {
        game.update(delta);

        //Do logic when its a users turn else wait for next turn
        if (!bullets.isOnScreen()) {
            turnManager.getTurnId().onTurn(bullets);
        } else {
            userA.getTank().stopTank();
            userB.getTank().stopTank();
        }

        //Always do this logic even if its not the users turn
        turnManager.getTurnId().always();
        
        //imageMap.drawPixmap(transparentLayer, START_HEIGHT, START_HEIGHT);

        //Render the users tanks and bullets and background
        spriteBatch.begin();
        spriteBatch.draw(AssetManager.getInstance().getBackgrounds(VariableManager.getInstance().getBackground()), 0, 0, graphicsWidth, graphicsHeight);
        spriteBatch.draw(imageMap, 0, 0);
        userA.getTank().renderSprite(spriteBatch);
        userB.getTank().renderSprite(spriteBatch);
        bullets.render(spriteBatch);
        spriteBatch.end();
        
        hud.render(shapeRenderer, spriteBatch);

        if (!bullets.isOnScreen() && (userA.getTank().isBankrupt() || userB.getTank().isBankrupt())) {
            ScreenManager.getInstance().changeScreen(ScreenManager.Screens.GAME_OVER);
        }

        //Cleanup unseen bullets
        bullets.cleanOutsideBullets();
    }

}
