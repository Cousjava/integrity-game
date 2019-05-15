package com.github.integritygame.util;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.github.integritygame.objects.BulletsController;
import com.github.integritygame.objects.Hud;
import com.github.integritygame.objects.PlayerHud;
import com.github.integritygame.objects.Tank;
import com.github.integritygame.objects.UserTurn;
import com.integrity.games.world.GameWorld;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GameManager {

    private TurnManager<UserTurn> turnManager;

    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;

    private UserTurn userA;
    private UserTurn userB;

    private BulletsController bullets;
    
    private GameWorld game;

    //TODO: Be able to calculate this from background
    private static final int START_HEIGHT = 100;

    private Hud hud;

    /**
     * Create a game manager
     * @param graphicsWidth Width of the screen
     * @param graphicsHeight Height of the screen
     * @param spriteBatch SpriteBatch so we can render them instead of creating a new one
     * @param shapeRenderer ShapeRenderer so we can render them instead of creating a new one
     */
    public GameManager(int graphicsWidth, int graphicsHeight, SpriteBatch spriteBatch, ShapeRenderer shapeRenderer){
        this.spriteBatch = spriteBatch;
        this.shapeRenderer = shapeRenderer;

        Tank userATank = new Tank(30, 180,80,35, false);
        Tank userBTank = new Tank(graphicsWidth - 110,180,80,35, true);
        userA = new UserTurn(userATank);
        userB = new UserTurn(userBTank);
        setTankTextures();

        //This will register the different objects that will need to tanke turns
        turnManager = new TurnManager<>(new LinkedList<>(Arrays.asList(userA, userB)));

        //This will create an input manager for each user
        userA.setInputManager(InputManager.CONTROL.LEFT, turnManager);
        userB.setInputManager(InputManager.CONTROL.RIGHT, turnManager);
        
        EdgeShape terrain = new EdgeShape();
        terrain.set(0, START_HEIGHT, graphicsWidth, START_HEIGHT);
        
        game = new GameWorld(terrain);
        userATank.setTankBody(game.addTank(30, START_HEIGHT + 1, userATank));
        userBTank.setTankBody(game.addTank(graphicsWidth - 110, START_HEIGHT + 1, userBTank));
        
        bullets = new BulletsController(graphicsWidth, graphicsHeight, game);

        hud = new Hud(graphicsWidth, graphicsHeight);
        List<PlayerHud> players = hud.getPlayerHuds();
        players.get(0).setTank(userATank);
        players.get(1).setTank(userBTank);
    }

    /**
     * This will render everything on screen along with executing code that should be done with every frame
     */
    public void render(float delta){
        game.update(delta);
        //Ensure this is called every frame so the user can move and fire during every frame
        turnManager.getTurnId().getInputManager().move();
        turnManager.getTurnId().getInputManager().tankFire(bullets);

        //Render the users tanks and bullets
        spriteBatch.begin();
            userA.getTank().renderSprite(spriteBatch);
            userB.getTank().renderSprite(spriteBatch);
            bullets.render(spriteBatch);
        spriteBatch.end();

        //Also render the line from each tank to fire
        //userA.getTank().renderShape(shapeRenderer);
        //userB.getTank().renderShape(shapeRenderer);

        hud.render(shapeRenderer, spriteBatch);
        
        //Cleanup unseen bullets
        bullets.cleanOutsideBullets();
    }

    public void setTankTextures(){
        userB.getTank().setTexture("tanks/GreenTankRightBody.png","tanks/GreenTankRightTurret.png");
    }

}
