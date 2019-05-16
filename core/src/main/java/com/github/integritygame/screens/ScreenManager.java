package com.github.integritygame.screens;

import com.badlogic.gdx.Game;
import java.util.HashMap;

/**
 * <h1>Screen Manager</h1>
 * The ScreenManager allows you to retrieve a AbstractScreen from predefined screens enum 'Screens'
 * This is a singleton
 */
public class ScreenManager {

    private static ScreenManager instance;
    private HashMap<Screens, AbstractScreen> screens;
    private Game parent;

    /**
     * Defines the different types of screens available
     */
    public enum Screens {
        GAME_INFO, MAIN_GAME, MAIN_MENU, SETTINGS_MENU, HELP_MENU, PRE_GAME, GAME_OVER
    }

    private ScreenManager() {
        screens = new HashMap<>();
    }

    /**
     * This will allow you to add the parent Game to the class which allows us to change screens
     */
    public void init(Game parent){
        this.parent = parent;
    }

    /**
     * Retrieve the ScreenManager instance
     * @return ScreenManager singleton
     */
    public static synchronized ScreenManager getInstance(){
        if(instance == null){
            instance = new ScreenManager();
        }
        return instance;
    }

    /**
     * Retrieve an AbstractScreen
     * @param screen Enum value for the screen wanted
     * @return AbstractScreen of the screen wanted
     */
    private AbstractScreen getScreen(Screens screen){
        //Ensures there is a new game every time if the game is exited
        if(screen.equals(Screens.MAIN_GAME)){
            screens.put(Screens.MAIN_GAME, new MainGameScreen());
            }

        if(!screens.containsKey(screen)) {
            switch (screen) {
                case GAME_INFO:
                    screens.put(Screens.GAME_INFO, new GameInfoScreen());
                    break;
                case MAIN_MENU:
                    screens.put(Screens.MAIN_MENU, new MainMenuScreen());
                    break;
                case SETTINGS_MENU:
                    screens.put(Screens.SETTINGS_MENU, new SettingsMenuScreen());
                    break;
                case HELP_MENU:
                    screens.put(Screens.HELP_MENU, new HelpMenuScreen());
                    break;
                case PRE_GAME:
                    screens.put(Screens.PRE_GAME, new PreGameScreen());
                    break;
                case GAME_OVER:
                    screens.put(Screens.GAME_OVER, new GameOverScreen());
                    break;
            }
        }
        return screens.get(screen);
    }

    /**
     * This takes a Screens enum value and allows us to set the screen
     */
    public void changeScreen(Screens screen){
        System.out.println(getScreen(screen));

        parent.setScreen(getScreen(screen));
    }
}
