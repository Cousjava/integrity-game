package com.github.integritygame.util;

import com.badlogic.gdx.Input;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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