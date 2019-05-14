package com.github.integritygame.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.github.integritygame.MyGdxGame;
import com.github.integritygame.objects.BulletsController;
import com.github.integritygame.objects.Tank;

public class InputManager {

    public enum CONTROL {
        LEFT, RIGHT
    }

    private CONTROL control;
    private Tank tank;
    private TurnManager turnManager;
    private static KeyBindingManager keyManager = MyGdxGame.keyManager;

    public InputManager(CONTROL control, Tank tank, TurnManager turnManager){
        this.control = control;
        this.tank = tank;
        this.turnManager = turnManager;
    }

    /**
     * Defines how the player should move depending on what side they are
     */
    public void move(){
        if((control.equals(CONTROL.LEFT) && Gdx.input.isKeyPressed(keyManager.getLeftLeftMoveKey()))||(control.equals(CONTROL.RIGHT) && Gdx.input.isKeyPressed(keyManager.getRightLeftMoveKey()))){
            tank.updateX(false);
        }
        if((control.equals(CONTROL.LEFT) && Gdx.input.isKeyPressed(keyManager.getLeftRightMoveKey()))||(control.equals(CONTROL.RIGHT) && Gdx.input.isKeyPressed(keyManager.getRightRightMoveKey()))){
            tank.updateX(true);
        }
        if((control.equals(CONTROL.LEFT) && Gdx.input.isKeyPressed(keyManager.getLeftAimUpKey()))||(control.equals(CONTROL.RIGHT) && Gdx.input.isKeyPressed(keyManager.getRightAimUpKey()))){
            tank.rotate(true);
        }
        if((control.equals(CONTROL.LEFT) && Gdx.input.isKeyPressed(keyManager.getLeftAimDownKey()))||(control.equals(CONTROL.RIGHT) && Gdx.input.isKeyPressed(keyManager.getRightAimDownKey()))){
            tank.rotate(false);
        }
    }

    /**
     * THis defines how the payer should fire
     * @param bullets The bullet to be fired
     */
    public void tankFire(BulletsController bullets) {
        if(Gdx.input.isKeyJustPressed(keyManager.getFireKey())){
            Vector2 bullet = new Vector2(1, 1).setLength2(1f);
            bullet.setAngle(tank.getRotation());
            bullets.addBullet(tank.getCurrentPosition(), bullet);
            turnManager.nextTurn();
        }
    }

}
