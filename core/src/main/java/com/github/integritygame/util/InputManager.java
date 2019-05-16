package com.github.integritygame.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.github.integritygame.MyGdxGame;
import com.github.integritygame.objects.BulletData;
import com.github.integritygame.objects.BulletsController;
import com.github.integritygame.objects.GeneralHud;
import com.github.integritygame.objects.Tank;
import com.github.integritygame.screens.ScreenManager;

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
        if((control.equals(CONTROL.LEFT) && Gdx.input.isKeyPressed(keyManager.keyMap.get(KeyBindingManager.ConfigurableKeys.LEFT_LEFT_MOVE)))||(control.equals(CONTROL.RIGHT) && Gdx.input.isKeyPressed(keyManager.keyMap.get(KeyBindingManager.ConfigurableKeys.RIGHT_LEFT_MOVE)))){
            tank.updateX(false);
        }
        if((control.equals(CONTROL.LEFT) && Gdx.input.isKeyPressed(keyManager.keyMap.get(KeyBindingManager.ConfigurableKeys.LEFT_RIGHT_MOVE)))||(control.equals(CONTROL.RIGHT) && Gdx.input.isKeyPressed(keyManager.keyMap.get(KeyBindingManager.ConfigurableKeys.RIGHT_RIGHT_MOVE)))){
            tank.updateX(true);
        }
        if((control.equals(CONTROL.LEFT) && Gdx.input.isKeyPressed(keyManager.keyMap.get(KeyBindingManager.ConfigurableKeys.LEFT_AIM_UP)))||(control.equals(CONTROL.RIGHT) && Gdx.input.isKeyPressed(keyManager.keyMap.get(KeyBindingManager.ConfigurableKeys.RIGHT_AIM_UP)))){
            tank.rotate(true);
        }
        if((control.equals(CONTROL.LEFT) && Gdx.input.isKeyPressed(keyManager.keyMap.get(KeyBindingManager.ConfigurableKeys.LEFT_AIM_DOWN)))||(control.equals(CONTROL.RIGHT) && Gdx.input.isKeyPressed(keyManager.keyMap.get(KeyBindingManager.ConfigurableKeys.RIGHT_AIM_DOWN)))){
            tank.rotate(false);
        }
        if(Gdx.input.isKeyJustPressed(keyManager.keyMap.get(KeyBindingManager.ConfigurableKeys.BULLET_TOGGLE))){
            if(VariableManager.getInstance().getString("bulletType").equals("SMALL")){
                VariableManager.getInstance().setString("bulletType","MEDIUM");
            }else if(VariableManager.getInstance().getString("bulletType").equals("MEDIUM")){
                VariableManager.getInstance().setString("bulletType","LARGE");
            }else if(VariableManager.getInstance().getString("bulletType").equals("LARGE")){
                VariableManager.getInstance().setString("bulletType","SMALL");
            }


        }
    }

    public void escapeGame() {
        if(Gdx.input.isKeyPressed(keyManager.getMainGameExit())){
            ScreenManager.getInstance().changeScreen(ScreenManager.Screens.MAIN_MENU);
        }
    }

    /**
     * THis defines how the payer should fire
     * @param bullets The bullet to be fired
     */
    public void tankFire(BulletsController bullets) {
        if(Gdx.input.isKeyJustPressed(keyManager.keyMap.get(KeyBindingManager.ConfigurableKeys.FIRE))){
            BulletData bulletData = new BulletData(VariableManager.getInstance().getString("bulletType"));
            Vector2 bullet = new Vector2(1, 1).setLength2(1000f);
            bullet.setAngle(tank.getRotation());
            bullets.addBullet(tank.getBarrelEnd(), bullet,tank, bulletData);
            tank.changeMoney(false, bulletData.getBulletData().costOnFire);
            turnManager.nextTurn();
        }
    }

}
