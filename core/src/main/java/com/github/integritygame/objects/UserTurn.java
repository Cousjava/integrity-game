package com.github.integritygame.objects;

import com.github.integritygame.util.InputManager;
import com.github.integritygame.util.TurnManager;

public class UserTurn {

    private InputManager inputManager;
    private Tank tank;

    /**
     * Initialises the object with the Tank who is going first
     *
     * @param tank First tank
     */
    public UserTurn(Tank tank) {
        this.tank = tank;
    }

    /**
     * Sets the input manage to control the tank who is able to shoot
     *
     * @param control         Movement controls
     * @param userTurnManager Turn manage
     */
    public void setInputManager(InputManager.CONTROL control, TurnManager<UserTurn> userTurnManager) {
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
}
