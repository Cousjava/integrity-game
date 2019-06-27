package com.github.integritygame.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.github.integritygame.objects.*;
import com.github.integritygame.screens.ScreenManager;
import com.integrity.games.world.GameWorld;

import java.time.temporal.ValueRange;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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
    private static final int START_HEIGHT = 100;

    private int graphicsWidth;
    private int graphicsHeight;

    private Stage powerUpStage;

    private float timeAux;

    /**
     * Create a game manager
     *
     * @param graphicsWidth  Width of the screen
     * @param graphicsHeight Height of the screen
     * @param spriteBatch    SpriteBatch so we can render them instead of creating a new one
     * @param shapeRenderer  ShapeRenderer so we can render them instead of creating a new one
     */
    public GameManager(int graphicsWidth, int graphicsHeight, SpriteBatch spriteBatch, ShapeRenderer shapeRenderer, Stage powerUpStage) {
        this.spriteBatch = spriteBatch;
        this.shapeRenderer = shapeRenderer;
        this.graphicsWidth = graphicsWidth;
        this.graphicsHeight = graphicsHeight;
        this.powerUpStage = powerUpStage;

        configureTanksAndUserTurn();
        configureHud();
        configureGameWorld();

    }

    private void configureTanksAndUserTurn() {
        userA = new UserTurn(new Tank(30, 180), InputManager.Control.LEFT);
        userB = new UserTurn(new Tank(graphicsWidth - 40, 180), InputManager.Control.RIGHT);
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
        userA.getTank().setTankBody(game.addTank(30, START_HEIGHT + 1, userA.getTank()));
        userB.getTank().setTankBody(game.addTank(graphicsWidth - 40, START_HEIGHT + 1, userB.getTank()));
        bullets = new BulletsController(graphicsWidth, graphicsHeight, game);
    }

    public void addPowerUp(AssetManager.PowerUp powerUp){
        int top = 550;
        int bottom = 150;
        int left = 0;
        int right = 1220;
        ImageButton a = AssetManager.getInstance().getPowerUpButton(powerUp);
        a.setPosition(new Random().nextInt(right - left) + left,new Random().nextInt(top - bottom) + bottom);
        a.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y){
                if(powerUp.equals(AssetManager.PowerUp.ONE)){
                    turnManager.getTurnId().getTank().toggelFuel(true, 100);
                }else if(powerUp.equals(AssetManager.PowerUp.TWO)){
                    turnManager.getTurnId().getTank().changeMoney(true, 100);
                }
                a.remove();
            }
        });
        powerUpStage.addActor(a);
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

        //Render the users tanks and bullets and background
        spriteBatch.begin();
        spriteBatch.draw(AssetManager.getInstance().getBackgrounds(VariableManager.getInstance().getBackground()), 0, 0, graphicsWidth, graphicsHeight);
        userA.getTank().renderSprite(spriteBatch);
        userB.getTank().renderSprite(spriteBatch);
        bullets.render(spriteBatch);
        spriteBatch.end();

        hud.render(shapeRenderer, spriteBatch);

        if (turnManager.getTurnId().getTank().isDead()) {
            VariableManager.getInstance().setVictoryType(VariableManager.VictoryType.DESTROY);

            if(userA.getTank().equals(turnManager.getTurnId().getTank())){
                VariableManager.getInstance().setString(VariableManager.VICTOR_KEY, VariableManager.PLAYER_TWO);
            } else {
                VariableManager.getInstance().setString(VariableManager.VICTOR_KEY, VariableManager.PLAYER_ONE);
            }

            ScreenManager.getInstance().changeScreen(ScreenManager.Screens.GAME_OVER);
        }

        if (!bullets.isOnScreen() && userA.getTank().isBankrupt() || userB.getTank().isBankrupt()) {
            VariableManager.getInstance().setVictoryType(VariableManager.VictoryType.BANKRUPT);
            if (userA.getTank().isBankrupt()) {
                VariableManager.getInstance().setString(VariableManager.VICTOR_KEY, VariableManager.PLAYER_TWO);
            } else {
                VariableManager.getInstance().setString(VariableManager.VICTOR_KEY, VariableManager.PLAYER_ONE);
            }
            ScreenManager.getInstance().changeScreen(ScreenManager.Screens.GAME_OVER);
        }

        //Cleanup unseen bullets
        bullets.cleanOutsideBullets();

        if(powerUpStage.getActors().size == 0){
            if(timeAux>=10){ //10 seconds
                addPowerUp(AssetManager.PowerUp.getRandomPowerUp());
                timeAux=0;
            }else{
                timeAux+=delta;
            }
        }
    }

}
