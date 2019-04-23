package test;

import com.github.integritygame.MyGdxGame;
import com.github.integritygame.screens.*;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.*;

public class ScreenManagerTest {

    private MyGdxGame game;
    private ScreenManager test;

    @Before
    public void before(){
        game = mock(MyGdxGame.class);
        test = ScreenManager.getInstance();
        test.init(game);
    }

    @Test
    public void testSingleton(){
        assertThat(test, equalTo(ScreenManager.getInstance()));
    }

    @Test
    public void testGameInfoScreen(){
        test.changeScreen(ScreenManager.Screens.GAME_INFO);
        verify(game, times(1)).setScreen(refEq(new GameInfoScreen()));
    }

    //@Test TODO - Fix Test
    //public void testMainGameScreen(){
    //    test.changeScreen(ScreenManager.Screens.MAIN_GAME);
    //    verify(game, times(1)).setScreen(refEq(new MainGameScreen()));
    //}

    @Test
    public void testMainMenuScreen(){
        test.changeScreen(ScreenManager.Screens.MAIN_MENU);
        verify(game, times(1)).setScreen(refEq(new MainMenuScreen()));
    }

    @Test
    public void testSettingsMenu(){
        test.changeScreen(ScreenManager.Screens.SETTINGS_MENU);
        verify(game, times(1)).setScreen(refEq(new SettingsMenuScreen()));
    }

}
