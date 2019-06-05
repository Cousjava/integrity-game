package com.github.integritygame.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PlayerHud {

    private int x;
    private int y;
    private int damage = 100;
    private String name;
    private BitmapFont font;
    private int money = 100;

    private Tank tank;

    /**
     * Initialises the Player HUD between the given X and Y points
     *
     * @param x X coordinate
     * @param y Y coordinate
     */
    public PlayerHud(int x, int y) {
        this.x = x;
        this.y = y;
        this.font = new BitmapFont();
    }

    /**
     * Sets the name of the Player this HUD relates to
     *
     * @param name HUD owner
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Renders the HUD owners' stats such as money and the amount of damage they have taken
     *
     * @param spriteBatch Rendering medium
     */
    public void renderText(SpriteBatch spriteBatch) {
        damage = tank.getDamage();
        money = tank.getMoney();
        spriteBatch.begin();
        font.setColor(Color.BLACK);
        font.draw(spriteBatch, name, x, y + 75);
        font.draw(spriteBatch, "Money: $" + money, x, y + 55);
        font.draw(spriteBatch, "Tank Damage: " + damage + "%", x, y + 35);
        spriteBatch.end();
    }

    /**
     * Renders the shapes pertaining to the user details
     *
     * @param shapeRenderer Rendering engine
     */
    public void render(ShapeRenderer shapeRenderer) {
        damage = tank.getDamage();
        money = tank.getMoney();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1, 1, 1, 1);
        shapeRenderer.rect(x, y - 10, 200, 25);
        shapeRenderer.setColor(1, 0, 0, 1);
        shapeRenderer.rect(x, y - 10, damage * 2, 25);
        shapeRenderer.end();
    }

    /**
     * Assigns this HUD a tank to read data from
     *
     * @param tank
     */
    public void setTank(Tank tank) {
        this.tank = tank;
    }

}
