package com.github.integritygame.util;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.integritygame.objects.BulletsController;
import com.github.integritygame.objects.Hud;
import com.github.integritygame.objects.Tank;
import com.github.integritygame.objects.UserTurn;

import java.util.Arrays;
import java.util.LinkedList;

public class GameManager {

    private TurnManager<UserTurn> turnManager;

    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;

    private UserTurn userA;
    private UserTurn userB;

    private BulletsController bullets;

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

        bullets = new BulletsController(graphicsWidth, graphicsHeight);

        userA = new UserTurn(new Tank(30, 100,80,35, false));
        userB = new UserTurn(new Tank(graphicsWidth - 110,100,80,35, true));
        setTankTextures();

        //This will register the different objects that will need to tanke turns
        turnManager = new TurnManager<>(new LinkedList<>(Arrays.asList(userA, userB)));

        //This will create an input manager for each user
        userA.setInputManager(InputManager.CONTROL.LEFT, turnManager);
        userB.setInputManager(InputManager.CONTROL.RIGHT, turnManager);

        hud = new Hud(graphicsWidth, graphicsHeight);
    }

    /**
     * This will render everything on screen along with executing code that should be done with every frame
     */
    public void render(){
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
    }

    public void setTankTextures(){
        userB.getTank().setTexture("tanks/GreenTankRightBody.png","tanks/GreenTankRightTurret.png");
    }

}
