package com.github.integritygame.objects;

import com.github.integritygame.util.InputManager;
import com.github.integritygame.util.TurnManager;

public class UserTurn {

    private Tank tank;
    private InputManager inputManager;
    private InputManager.Control control;

    /**
     * Initialises the object with the Tank who is going first
     *
     * @param tank First tank
     */
    public UserTurn(Tank tank, InputManager.Control control){
        this.tank = tank;
        this.control = control;
        tank.registerTurn(this);
    }

    /**
     * Sets the input manage to control the tank who is able to shoot
     *
     * @param control         Movement controls
     * @param userTurnManager Turn manage
     */
    public void setInputManager(TurnManager<UserTurn> userTurnManager){
        this.inputManager = new InputManager(control, tank, userTurnManager);
    }

    /**
     * Gets the tank who's turn it is
     *
     * @return Current tank
     */
    public Tank getTank() {
        return tank;
    }

    /**
     * Returns the current input manager
     *
     * @return Input Manager
     */
    public InputManager getInputManager() {
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
