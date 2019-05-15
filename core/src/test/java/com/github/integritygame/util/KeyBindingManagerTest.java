package com.github.integritygame.util;

import org.junit.Test;
import com.badlogic.gdx.Input;

import static org.junit.Assert.*;

public class KeyBindingManagerTest {

    @Test
    public void checkForDuplicateKeys() {
        KeyBindingManager keyMang = new KeyBindingManager();

        //test default layout
        assertFalse(keyMang.checkForDuplicateKeys());

        keyMang.setLeftLeftMoveKey(Input.Keys.LEFT);
        keyMang.setRightLeftMoveKey(Input.Keys.LEFT);

        //test duplicate state
        assertTrue(keyMang.checkForDuplicateKeys());

    }
}