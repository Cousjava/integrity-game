package com.github.integritygame.util;

import com.badlogic.gdx.Input;
import java.util.HashMap;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class KeyBindingManagerTest {

    @Test
    public void checkForDuplicateKeys() {
        KeyBindingManager keyMang = new KeyBindingManager();
        HashMap<KeyBindingManager.ConfigurableKeys, Integer> keyBindings = new HashMap<>();

        //test default layout
        assertFalse(KeyBindingManager.checkForDuplicateKeys(keyBindings, 0));

        assertTrue(KeyBindingManager.setKey(KeyBindingManager.ConfigurableKeys.FIRE, 0));
        System.out.println("Expecting error...");
        assertFalse(KeyBindingManager.setKey(KeyBindingManager.ConfigurableKeys.BULLET_TOGGLE, 0));

    }
}