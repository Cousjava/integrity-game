package com.github.integritygame.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.integritygame.util.VariableManager;

public class GeneralHud {

    private int x;
    private int y;
    private BitmapFont font;

    /**
     * Initialises the General Heads-Up-Display using the X and Y values from the current client area
     *
     * @param x Client X size
     * @param y Client Y size
     */
    public GeneralHud(int x, int y) {
        this.x = x;
        this.y = y;
        this.font = new BitmapFont();
        VariableManager.getInstance().setString("bulletType", "SMALL");
    }

    /**
     * Renders the information regarding the current turn and the currently selected bullet
     *
     * @param spriteBatch
     */
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        font.setColor(Color.BLACK);
        font.draw(spriteBatch, "Current Turn Info", x - 60, y + 75);
        final String bulletName = VariableManager.getInstance().getString("bulletType");
        font.draw(spriteBatch, "Bullet Type:" + bulletName.substring(0, 1).concat(bulletName.substring(1).toLowerCase()), x - 60, y + 55);
        //font.draw(spriteBatch, "Bullet Type: " + (VariableManager.getInstance().getString("bulletType").equals("SMALL") ? "Small" : (VariableManager.getInstance().getString("bulletType").equals("MEDIUM") ? "Medium" : "Large")), x - 60, y + 55);
        spriteBatch.end();
    }

}
