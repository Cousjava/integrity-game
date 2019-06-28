package com.github.integritygame.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.integritygame.util.VariableManager;

import java.util.ArrayList;
import java.util.List;

public class Hud {

    private List<PlayerHud> hBar;
    private GeneralHud generalHud;
    private int width;
    private int height;

    /**
     * Initializes the Heads-Up-Display given the current width and height of the game client
     *
     * @param width  Client width
     * @param height Client height
     */
    public Hud(int width, int height) {
        this.width = width;
        this.height = height;
        hBar = new ArrayList<>();
        hBar.add(new PlayerHud(10, height - 90));
        hBar.add(new PlayerHud(width - 275, height - 90));
        hBar.get(0).setName(VariableManager.getInstance().getString("PlayerOneName"));
        hBar.get(1).setName(VariableManager.getInstance().getString("PlayerTwoName"));
        generalHud = new GeneralHud(width / 2, height - 90);
    }

    /**
     * Renders the HUD elements
     *
     * @param shapeRenderer Rendering engine
     * @param spriteBatch   Rendered content
     */
    public void render(ShapeRenderer shapeRenderer, SpriteBatch spriteBatch) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(121 / 255f, 121 / 255f, 121 / 255f, 1);
        shapeRenderer.rect(0, height - 120, width, 120);
        shapeRenderer.end();

        generalHud.render(spriteBatch);

        for (PlayerHud hud : hBar) {
            hud.render(shapeRenderer);
            hud.renderText(spriteBatch);
        }
    }

    /**
     * Sets the name linked to the tank
     *
     * @param tankNumber Player 1 or Player 2
     * @param name       Name of person operating the tank
     */
    public void setTankName(int tankNumber, String name) {
        if (tankNumber <= hBar.size()) {
            hBar.get(tankNumber).setName(name);
        }
    }

    /**
     * Returns all the HUD elements in the bar
     *
     * @return HUD elements
     */
    public List<PlayerHud> getPlayerHuds() {
        return hBar;
    }

}
