package com.github.integritygame.util;

import com.badlogic.gdx.Input;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KeyBindingManager {

    private static int LEFT_LEFT_MOVE_KEY;
    private static int LEFT_RIGHT_MOVE_KEY;
    private static int LEFT_AIM_UP_KEY;
    private static int LEFT_AIM_DOWN_KEY;
    private static int RIGHT_LEFT_MOVE_KEY;
    private static int RIGHT_RIGHT_MOVE_KEY;
    private static int RIGHT_AIM_UP_KEY;
    private static int RIGHT_AIM_DOWN_KEY;
    private static int FIRE_KEY;

    //fixed keys - can not be changed
    private final static int MAIN_GAME_EXIT = Input.Keys.ESCAPE;

    public KeyBindingManager() {
        this.resetDefaultKeys();
    }

    /**
     * Method to set the control keys back to default
     */
    public static void resetDefaultKeys() {
        LEFT_LEFT_MOVE_KEY = Input.Keys.A;
        LEFT_RIGHT_MOVE_KEY = Input.Keys.D;
        LEFT_AIM_UP_KEY = Input.Keys.W;
        LEFT_AIM_DOWN_KEY = Input.Keys.S;
        RIGHT_LEFT_MOVE_KEY = Input.Keys.LEFT;
        RIGHT_RIGHT_MOVE_KEY = Input.Keys.RIGHT;
        RIGHT_AIM_UP_KEY = Input.Keys.UP;
        RIGHT_AIM_DOWN_KEY = Input.Keys.DOWN;
        FIRE_KEY = Input.Keys.SPACE;
    }

    /**
     * Checks whether duplicate keys have been configured.
     * Will return true if there is a duplicate.
     *
     * @return duplicateKeyStatus
     */
    public static boolean checkForDuplicateKeys() {
        boolean duplicateKeyStatus = false;
        List<Integer> keyArray = new ArrayList<>();

        keyArray.add(LEFT_LEFT_MOVE_KEY);
        keyArray.add(LEFT_RIGHT_MOVE_KEY);
        keyArray.add(LEFT_AIM_UP_KEY);
        keyArray.add(LEFT_AIM_DOWN_KEY);
        keyArray.add(RIGHT_LEFT_MOVE_KEY);
        keyArray.add(RIGHT_RIGHT_MOVE_KEY);
        keyArray.add(RIGHT_AIM_UP_KEY);
        keyArray.add(RIGHT_AIM_DOWN_KEY);
        keyArray.add(FIRE_KEY);

        for (int key : keyArray) {
            if (Collections.frequency(keyArray, key) > 1) {
                duplicateKeyStatus = true;
            }
        }

        return duplicateKeyStatus;
    }


    /**
     * Getters and setter methods below
     *
     */

    public static int getLeftLeftMoveKey() {
        return LEFT_LEFT_MOVE_KEY;
    }

    public static void setLeftLeftMoveKey(int leftLeftMoveKey) {
        LEFT_LEFT_MOVE_KEY = leftLeftMoveKey;
    }

    public static int getLeftRightMoveKey() {
        return LEFT_RIGHT_MOVE_KEY;
    }

    public static void setLeftRightMoveKey(int leftRightMoveKey) {
        LEFT_RIGHT_MOVE_KEY = leftRightMoveKey;
    }

    public static int getLeftAimUpKey() {
        return LEFT_AIM_UP_KEY;
    }

    public static void setLeftAimUpKey(int leftAimUpKey) {
        LEFT_AIM_UP_KEY = leftAimUpKey;
    }

    public static int getLeftAimDownKey() {
        return LEFT_AIM_DOWN_KEY;
    }

    public static void setLeftAimDownKey(int leftAimDownKey) {
        LEFT_AIM_DOWN_KEY = leftAimDownKey;
    }

    public static int getRightLeftMoveKey() {
        return RIGHT_LEFT_MOVE_KEY;
    }

    public static void setRightLeftMoveKey(int rightLeftMoveKey) {
        RIGHT_LEFT_MOVE_KEY = rightLeftMoveKey;
    }

    public static int getRightRightMoveKey() {
        return RIGHT_RIGHT_MOVE_KEY;
    }

    public static void setRightRightMoveKey(int rightRightMoveKey) {
        RIGHT_RIGHT_MOVE_KEY = rightRightMoveKey;
    }

    public static int getRightAimUpKey() {
        return RIGHT_AIM_UP_KEY;
    }

    public static void setRightAimUpKey(int rightAimUpKey) {
        RIGHT_AIM_UP_KEY = rightAimUpKey;
    }

    public static int getRightAimDownKey() {
        return RIGHT_AIM_DOWN_KEY;
    }

    public static void setRightAimDownKey(int rightAimDownKey) {
        RIGHT_AIM_DOWN_KEY = rightAimDownKey;
    }

    public static int getFireKey() {
        return FIRE_KEY;
    }

    public static void setFireKey(int fireKey) {
        FIRE_KEY = fireKey;
    }

    public static int getMainGameExit() {
        return MAIN_GAME_EXIT;
    }

}
