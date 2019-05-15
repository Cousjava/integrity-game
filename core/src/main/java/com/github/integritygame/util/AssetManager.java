package com.github.integritygame.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class AssetManager {

    public enum Background{
        DESERT, GRASS, HOME
    };

    private static Skin skin = new Skin(Gdx.files.internal("visui/assets/uiskin.json"));

    public AssetManager(){
    }

    public static Label screenTitle(Color color, String text){
        Label.LabelStyle titleLabelStyle = new Label.LabelStyle();
        titleLabelStyle.font = new BitmapFont(Gdx.files.internal("fonts/defused.fnt"));
        titleLabelStyle.fontColor = color;
        return new Label(text, titleLabelStyle);
    }

    public static Label labelSimpleWhiteText(String name){
        Label.LabelStyle textLableStyle = new Label.LabelStyle();
        textLableStyle.fontColor = Color.WHITE;
        textLableStyle.font = new BitmapFont();
        return new Label(name, textLableStyle);
    }

    public static TextField skinnedTextField(){
        return new TextField("", skin);
    }

    public static Skin skin(){
        return skin;
    }

    public static TextButton.TextButtonStyle preGameScreenButtons(){
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = new BitmapFont();
        TextureAtlas buttonAtlas = new TextureAtlas("buttons/simpleButton.txt");
        Skin skinButton = new Skin();
        skinButton.addRegions(buttonAtlas);
        buttonStyle.up = skinButton.getDrawable("rounded_rectangle_button");
        buttonStyle.down = skinButton.getDrawable("rounded_rectangle_button");
        buttonStyle.checked = skinButton.getDrawable("rounded_rectangle_button");
        return buttonStyle;
    }

    public static TextButton settingsTextButton(String buttonText){
        TextButton button = new TextButton(buttonText, skin);

        return button;
    }

    public static Texture background(Background backgrounds){
        if(Background.GRASS.equals(backgrounds)){
            return new Texture(Gdx.files.internal("backgrounds/tank-grass-background.jpeg"));
        }
        if(Background.DESERT.equals(backgrounds)){
            return new Texture(Gdx.files.internal("backgrounds/tank-desert-background.jpeg"));
        }
        if(Background.HOME.equals(backgrounds)){
            return new Texture(Gdx.files.internal("backgrounds/tank-main-menu-background.jpeg"));
        }
        return null;
    }

}
