package com.github.integritygame.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.github.integritygame.objects.BulletData;
import com.github.integritygame.objects.BulletsController;
import com.github.integritygame.objects.Tank;
import com.github.integritygame.screens.ScreenManager;

public class InputManager {

    public enum Control {
        LEFT, RIGHT
    }

    private Control control;
    private Tank tank;
    private TurnManager turnManager;

    public InputManager(Control control, Tank tank, TurnManager turnManager) {
        this.control = control;
        this.tank = tank;
        this.turnManager = turnManager;
    }

    /**
     * Defines how the player should move depending on what side they are
     */
    public void move() {
        if (tank.getFuel() != 0) {
            if ((control.equals(Control.LEFT) && Gdx.input.isKeyPressed(KeyBindingManager.keyMap.get(KeyBindingManager.ConfigurableKeys.LEFT_LEFT_MOVE))) || (control.equals(Control.RIGHT) && Gdx.input.isKeyPressed(KeyBindingManager.keyMap.get(KeyBindingManager.ConfigurableKeys.RIGHT_LEFT_MOVE)))) {
                tank.updateX(false);
                tank.toggelFuel(false, 1);

            }
            if ((control.equals(Control.LEFT) && Gdx.input.isKeyPressed(KeyBindingManager.keyMap.get(KeyBindingManager.ConfigurableKeys.LEFT_RIGHT_MOVE))) || (control.equals(Control.RIGHT) && Gdx.input.isKeyPressed(KeyBindingManager.keyMap.get(KeyBindingManager.ConfigurableKeys.RIGHT_RIGHT_MOVE)))) {
                tank.updateX(true);
                tank.toggelFuel(false, 1);
            }
        }
        if ((control.equals(Control.LEFT) && Gdx.input.isKeyPressed(KeyBindingManager.keyMap.get(KeyBindingManager.ConfigurableKeys.LEFT_AIM_DOWN))) || (control.equals(Control.RIGHT) && Gdx.input.isKeyPressed(KeyBindingManager.keyMap.get(KeyBindingManager.ConfigurableKeys.RIGHT_AIM_UP)))) {
            tank.rotate(true);
        }
        if ((control.equals(Control.LEFT) && Gdx.input.isKeyPressed(KeyBindingManager.keyMap.get(KeyBindingManager.ConfigurableKeys.LEFT_AIM_UP))) || (control.equals(Control.RIGHT) && Gdx.input.isKeyPressed(KeyBindingManager.keyMap.get(KeyBindingManager.ConfigurableKeys.RIGHT_AIM_DOWN)))) {
            tank.rotate(false);
        }
        if (Gdx.input.isKeyJustPressed(KeyBindingManager.keyMap.get(KeyBindingManager.ConfigurableKeys.BULLET_TOGGLE))) {
            switch (VariableManager.getInstance().getString("bulletType")) {
                case "SMALL":
                    VariableManager.getInstance().setString("bulletType", "MEDIUM");
                    break;
                case "MEDIUM":
                    VariableManager.getInstance().setString("bulletType", "LARGE");
                    break;
                case "LARGE":
                    VariableManager.getInstance().setString("bulletType", "SMALL");
                    break;
                case "NUKE":
                    VariableManager.getInstance().setString("bulletType", "NUKE");
                    break;
            }
        }
        //TODO- Add this to the UI to buy items
        if(Gdx.input.isKeyPressed(Input.Keys.F)){
            if(tank.getFuel() != 100){
                tank.toggelFuel(true, 1);
                tank.changeMoney(false,1);
            }
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.B)) {
            tank.toggleSpeedMultiplier(true);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.N)) {
            tank.toggleStaminaMultiplier(false);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.M)) {
            tank.toggleStrengthMultiplier(false);
        }
        //Debug use -- System.out.println(tank.multipliersToString());

        tank.stopTank();
    }

    public void escapeGame() {
        if (Gdx.input.isKeyPressed(KeyBindingManager.getMainGameExit())) {
            ScreenManager.getInstance().changeScreen(ScreenManager.Screens.MAIN_MENU);
        }
    }

    /**
     * THis defines how the payer should fire
     *
     * @param bullets The bullet to be fired
     */
    public void tankFire(BulletsController bullets) {
        if (Gdx.input.isKeyJustPressed(KeyBindingManager.keyMap.get(KeyBindingManager.ConfigurableKeys.FIRE))) {
            BulletData bulletData = new BulletData(VariableManager.getInstance().getString("bulletType"));
            Vector2 bullet = new Vector2(1, 1).setLength2(1000f);
            bullet.setAngle(tank.getRotation());
            bullets.addBullet(tank.getBarrelEnd(), bullet, tank, bulletData);
            tank.changeMoney(false, bulletData.getBulletData().costOnFire);
            turnManager.nextTurn();
        }
    }

}
