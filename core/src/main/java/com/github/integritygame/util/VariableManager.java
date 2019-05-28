package com.github.integritygame.util;

import java.util.HashMap;

public class VariableManager {

    private static VariableManager instance;

    private HashMap<String, String> strings;

    private VariableManager() {
        strings = new HashMap<>();
    }

    public static synchronized VariableManager getInstance() {
        if (instance == null) {
            instance = new VariableManager();
        }
        return instance;
    }

    public String getString(String key) {
        return strings.get(key);
    }

    public void setString(String key, String value) {
        strings.put(key, value);
    }

}
