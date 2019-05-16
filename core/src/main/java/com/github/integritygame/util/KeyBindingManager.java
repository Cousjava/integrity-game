package com.github.integritygame.util;

import com.badlogic.gdx.Input;

import java.util.HashMap;

public class KeyBindingManager {

    public enum ConfigurableKeys {
        BULLET_TOGGLE, LEFT_LEFT_MOVE, LEFT_RIGHT_MOVE, LEFT_AIM_UP, LEFT_AIM_DOWN, RIGHT_LEFT_MOVE, RIGHT_RIGHT_MOVE, RIGHT_AIM_UP, RIGHT_AIM_DOWN, FIRE
    }

    private static int DEFAULT_LEFT_LEFT_MOVE_KEY = Input.Keys.A;
    private static int DEFAULT_LEFT_RIGHT_MOVE_KEY = Input.Keys.D;
    private static int DEFAULT_LEFT_AIM_UP_KEY = Input.Keys.S;
    private static int DEFAULT_LEFT_AIM_DOWN_KEY = Input.Keys.W;
    private static int DEFAULT_RIGHT_LEFT_MOVE_KEY = Input.Keys.LEFT;
    private static int DEFAULT_RIGHT_RIGHT_MOVE_KEY = Input.Keys.RIGHT;
    private static int DEFAULT_RIGHT_AIM_UP_KEY = Input.Keys.UP;
    private static int DEFAULT_RIGHT_AIM_DOWN_KEY = Input.Keys.DOWN;
    private static int DEFAULT_FIRE_KEY = Input.Keys.SPACE;
    private static int DEFAULT_BULLET_TOGGLE = Input.Keys.T;

    public static HashMap<ConfigurableKeys, Integer> keyMap = new HashMap<>();

    //fixed keys - can not be changed
    private final static int MAIN_GAME_EXIT = Input.Keys.ESCAPE;

    public KeyBindingManager() {
        this.resetDefaultKeys();
    }

    /**
     * Method to set the control keys back to default
     */
    public static void resetDefaultKeys() {
        keyMap.put(ConfigurableKeys.LEFT_LEFT_MOVE, DEFAULT_LEFT_LEFT_MOVE_KEY);
        keyMap.put(ConfigurableKeys.LEFT_RIGHT_MOVE, DEFAULT_LEFT_RIGHT_MOVE_KEY);
        keyMap.put(ConfigurableKeys.LEFT_AIM_UP, DEFAULT_LEFT_AIM_UP_KEY);
        keyMap.put(ConfigurableKeys.LEFT_AIM_DOWN, DEFAULT_LEFT_AIM_DOWN_KEY);
        keyMap.put(ConfigurableKeys.RIGHT_LEFT_MOVE, DEFAULT_RIGHT_LEFT_MOVE_KEY);
        keyMap.put(ConfigurableKeys.RIGHT_RIGHT_MOVE, DEFAULT_RIGHT_RIGHT_MOVE_KEY);
        keyMap.put(ConfigurableKeys.RIGHT_AIM_UP, DEFAULT_RIGHT_AIM_UP_KEY);
        keyMap.put(ConfigurableKeys.RIGHT_AIM_DOWN, DEFAULT_RIGHT_AIM_DOWN_KEY);
        keyMap.put(ConfigurableKeys.FIRE, DEFAULT_FIRE_KEY);
        keyMap.put(ConfigurableKeys.BULLET_TOGGLE, DEFAULT_BULLET_TOGGLE);
    }

    /**
     * Checks whether duplicate keys have been configured in a temporary Map.
     * Will return true if there is a duplicate.
     *
     * @param tempKeyMap the temporary map that is checked for duplicates
     * @param keyCode the integer value of the key to check for in map
     *
     * @return duplicateKeyStatus true if duplicates are detected
     */
    public static boolean checkForDuplicateKeys(HashMap<ConfigurableKeys, Integer> tempKeyMap, int keyCode) {
        boolean duplicateKeyStatus = false;

        long count = tempKeyMap.values().stream().filter(v -> v.equals(keyCode)).count();

        if (count > 1) {
            duplicateKeyStatus = true;
        }

        return duplicateKeyStatus;
    }

    /**
     * Used to set a key from the settings menu
     * @param keyName which configurable key to set
     * @param keyCode the value to set
     */
    public static void setKey(ConfigurableKeys keyName, int keyCode){
        HashMap<ConfigurableKeys, Integer> tempKeyMap = new HashMap<>(keyMap);

        tempKeyMap.replace(keyName, keyCode);

        if (checkForDuplicateKeys(tempKeyMap, keyCode)) {
            System.out.println("ERROR - Duplicate key found - no change will be made");
        } else {
            keyMap = tempKeyMap;
        }
    }

    /**
     * getter method for fixed Exit key
     * @return Integer for exit key
     */
    public static int getMainGameExit() {
        return MAIN_GAME_EXIT;
    }

}
