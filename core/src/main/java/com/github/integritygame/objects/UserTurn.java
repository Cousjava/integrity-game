package com.github.integritygame.objects;

import com.github.integritygame.util.InputManager;
import com.github.integritygame.util.TurnManager;

public class UserTurn {

    private InputManager inputManager;
    private Tank tank;

    public UserTurn(Tank tank) {
        this.tank = tank;
    }

    public void setInputManager(InputManager.CONTROL control, TurnManager<UserTurn> userTurnManager) {
        this.inputManager = new InputManager(control, tank, userTurnManager);
    }

    public Tank getTank() {
        return tank;
    }

    public InputManager getInputManager() {
        return inputManager;
    }


}
