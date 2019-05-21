package com.github.integritygame.objects;

import com.github.integritygame.util.InputManager;
import com.github.integritygame.util.TurnManager;

public class UserTurn {

    private Tank tank;
    private InputManager inputManager;
    private InputManager.Control control;

    public UserTurn(Tank tank, InputManager.Control control){
        this.tank = tank;
        this.control = control;
        tank.registerTurn(this);
    }

    public void setInputManager(TurnManager<UserTurn> userTurnManager){
        this.inputManager = new InputManager(control, tank, userTurnManager);
    }

    public Tank getTank(){
        return tank;
    }

    public InputManager getInputManager(){
        return inputManager;
    }

    public void onTurn(BulletsController bullet){
        inputManager.move();
        inputManager.tankFire(bullet);
    }

    public void always(){
        inputManager.escapeGame();
    }

    public InputManager.Control getControl() {
        return control;
    }
}
