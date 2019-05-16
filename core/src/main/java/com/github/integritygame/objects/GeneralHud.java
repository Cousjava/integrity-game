package com.github.integritygame.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.integritygame.util.VariableManager;

public class GeneralHud {

    private int x;
    private int y;
    private BitmapFont font;

    public GeneralHud(int x, int y) {
        this.x = x;
        this.y = y;
        this.font = new BitmapFont();
        VariableManager.getInstance().setString("bulletType", "SMALL");
    }

    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        font.setColor(Color.BLACK);
        font.draw(spriteBatch, "Current Turn Info", x - 60, y + 75);
        font.draw(spriteBatch, "Bullet Type: " + (VariableManager.getInstance().getString("bulletType").equals("SMALL") ? "Small" : (VariableManager.getInstance().getString("bulletType").equals("MEDIUM") ? "Medium" : "Large")), x - 60, y + 55);
        spriteBatch.end();
    }

}
