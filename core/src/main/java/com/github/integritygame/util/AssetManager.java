package com.github.integritygame.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.github.integritygame.objects.BulletData;

import java.util.*;

public class AssetManager {

    private static AssetManager instance;

    public enum Background {DESERT, GRASS, HOME}

    public enum TankStyles {BLUE_TANK, GREEN_TANK, LIGHT_GREEN_TANK}

    public enum PowerUp {
        ONE, TWO;

        public static PowerUp getRandomPowerUp() {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    }


    private Skin skin;
    private Skin customButton;
    private String helpText;
    private BitmapFont titleFont;
    private BitmapFont defaultFont;
    private Map<Background, Texture> backgrounds;
    private Map<BulletData.BulletName, Texture> bullets;
    private Map<TankStyles, Texture[]> tankTextures;
    private Map<PowerUp, Texture> powerups;

    private AssetManager() {
        initAssets();
    }

    private void initAssets() {
        skin = new Skin(Gdx.files.internal("visui/assets/uiskin.json"));

        helpText = Gdx.files.internal("text/helpText.txt").readString();

        customButton = new Skin();
        customButton.addRegions(new TextureAtlas("buttons/simpleButton.txt"));

        titleFont = new BitmapFont(Gdx.files.internal("fonts/defused.fnt"));
        defaultFont = new BitmapFont();

        backgrounds = new HashMap<>();
        backgrounds.put(Background.DESERT, new Texture(Gdx.files.internal("backgrounds/tank-desert-background.jpeg")));
        backgrounds.put(Background.GRASS, new Texture(Gdx.files.internal("backgrounds/tank-grass-background.jpeg")));
        backgrounds.put(Background.HOME, new Texture(Gdx.files.internal("backgrounds/tank-main-menu-background.jpeg")));

        bullets = new HashMap<>();
        bullets.put(BulletData.BulletName.SMALL, new Texture(Gdx.files.internal("projectiles/ProjectileBlack.png")));
        bullets.put(BulletData.BulletName.MEDIUM, new Texture(Gdx.files.internal("projectiles/ProjectileBlackGreen.png")));
        bullets.put(BulletData.BulletName.LARGE, new Texture(Gdx.files.internal("projectiles/ProjectileWhiteGreen.png")));

        tankTextures = new HashMap<>();
        tankTextures.put(TankStyles.GREEN_TANK, new Texture[]{new Texture(Gdx.files.internal("tanks/GreenTankBody.png")), new Texture(Gdx.files.internal("tanks/GreenTankTurret.png"))});
        tankTextures.put(TankStyles.BLUE_TANK, new Texture[]{new Texture(Gdx.files.internal("tanks/BlueTankBody.png")), new Texture(Gdx.files.internal("tanks/BlueTankTurret.png"))});
        tankTextures.put(TankStyles.LIGHT_GREEN_TANK, new Texture[]{new Texture(Gdx.files.internal("tanks/LightGreenTankBody.png")), new Texture(Gdx.files.internal("tanks/LightGreenTankTurret.png"))});

        powerups = new HashMap<>();
        powerups.put(PowerUp.ONE, new Texture(Gdx.files.internal("petrol.png")));
        powerups.put(PowerUp.TWO, new Texture(Gdx.files.internal("money.png")));
    }

    public static synchronized AssetManager getInstance() {
        if (instance == null) {
            instance = new AssetManager();
        }
        return instance;
    }

    public Label getTextAsWhiteNonTitle(String text) {
        return getText(Color.WHITE, text, false);
    }

    public Label getText(Color color, String text, boolean title) {
        Label.LabelStyle textLableStyle = new Label.LabelStyle();
        textLableStyle.font = title ? titleFont : defaultFont;
        textLableStyle.fontColor = color;
        return new Label(text, textLableStyle);
    }

    public TextField getTextField() {
        return new TextField("", skin);
    }

    public TextButton getTextButton(String buttonText) {
        return new TextButton(buttonText, skin);
    }

    public TextButton.TextButtonStyle getCustomTextButton() {
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = defaultFont;
        buttonStyle.up = customButton.getDrawable("rounded_rectangle_button");
        buttonStyle.down = customButton.getDrawable("rounded_rectangle_button");
        buttonStyle.checked = customButton.getDrawable("rounded_rectangle_button");
        return buttonStyle;
    }

    public ImageButton getPowerUpButton(PowerUp powerUp){
        ImageButton image = new ImageButton(skin, powerUp.toString());
        image.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(powerups.get(powerUp), 32, 32));
        image.setSize(32,32);
        return image;
    }

    public Texture getBackgrounds(Background background) {
        return backgrounds.get(background);
    }

    public Texture getBullets(BulletData.BulletName bullet) {
        return bullets.get(bullet);
    }

    public String getHelpText() {
        return helpText;
    }

    public Texture[] getTankTexture(TankStyles tankStyles) {
        return tankTextures.get(tankStyles);
    }
}
