package com.github.integritygame.util;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.integritygame.objects.BulletsController;
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

    public GameManager(int graphicsWidth, int graphicsHeight, SpriteBatch spriteBatch, ShapeRenderer shapeRenderer){
        this.spriteBatch = spriteBatch;
        this.shapeRenderer = shapeRenderer;

        bullets = new BulletsController(graphicsWidth, graphicsHeight);

        userA = new UserTurn(new Tank(30, 180,80,35));
        userB = new UserTurn(new Tank(graphicsWidth - 110,180,80,35));
        setTankTextures();

        turnManager = new TurnManager<>(new LinkedList<>(Arrays.asList(userA, userB)));

        userA.setInputManager(InputManager.CONTROL.LEFT, turnManager);
        userB.setInputManager(InputManager.CONTROL.RIGHT, turnManager);
    }

    public void render(){
        turnManager.getTurnId().getInputManager().move();
        turnManager.getTurnId().getInputManager().tankFire(bullets);

        spriteBatch.begin();
            userA.getTank().renderSprite(spriteBatch);
            userB.getTank().renderSprite(spriteBatch);
            bullets.render(spriteBatch);
        spriteBatch.end();

        userA.getTank().renderShape(shapeRenderer);
        userB.getTank().renderShape(shapeRenderer);
    }

    public void setTankTextures(){
        userB.getTank().setTexture("tanks/DesertColourTankLeft.png");
    }

}
