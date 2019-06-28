package com.github.integritygame.util;

import java.util.HashMap;

public class VariableManager {

    public enum VictoryType{ BANKRUPT, DESTROY }

    public final static String VICTOR_KEY = "victor";
    public final static String PLAYER_ONE = "player1";
    public final static String PLAYER_TWO = "player2";

    private static VariableManager instance;

    private HashMap<String, String> strings;
    private AssetManager.Background background;

    private VictoryType victoryType;

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

    public AssetManager.Background getBackground() {
        return background;
    }

    public void setBackground(AssetManager.Background background) {
        this.background = background;
    }

    public VictoryType getVictoryType() {
        return victoryType;
    }

    public void setVictoryType(VictoryType victoryType) {
        this.victoryType = victoryType;
    }
}
