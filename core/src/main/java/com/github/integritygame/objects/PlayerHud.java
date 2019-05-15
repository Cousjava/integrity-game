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
    
    private Tank tank;

    public PlayerHud(int x, int y){
        this.x = x;
        this.y = y;
        this.font = new BitmapFont();
    }

    public void setName(String name){
        this.name = name;
    }

    public void renderText(SpriteBatch spriteBatch){
        damage = tank.getDamage();
        spriteBatch.begin();
        font.setColor(Color.BLACK);
        font.draw(spriteBatch, "Tank Damage: " + damage + "%", x, y + 45);
        font.draw(spriteBatch, name, x, y + 75);
        spriteBatch.end();
    }

    public void render(ShapeRenderer shapeRenderer){
        damage = tank.getDamage();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1,1,1,1);
        shapeRenderer.rect(x, y, 200, 25);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1,0,0,1);
        shapeRenderer.rect(x, y, damage * 2, 25);
        shapeRenderer.end();
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

}
