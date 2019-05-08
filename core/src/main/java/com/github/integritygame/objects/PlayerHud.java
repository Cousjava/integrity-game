package com.github.integritygame.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class PlayerHud {

    private int x;
    private int y;
    private int damage = 90;
    private String name;
    private Stage stage;
    private BitmapFont font;

    public PlayerHud(int x, int y){
        this.x = x;
        this.y = y;
        this.name = name;
        this.stage = new Stage();
        this.font = new BitmapFont();
    }

    public void setName(String name){
        this.name = name;
    }

    public void renderText(SpriteBatch spriteBatch){
        spriteBatch.begin();
        font.setColor(Color.BLACK);
        font.draw(spriteBatch, "Tank Damage: " + damage + "%", x, y + 45);
        font.draw(spriteBatch, name, x, y + 75);
        spriteBatch.end();
    }

    public void render(ShapeRenderer shapeRenderer){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1,1,1,1);
        shapeRenderer.rect(x, y, 200, 25);
        shapeRenderer.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(1,0,0,1);
        shapeRenderer.rect(x, y, damage * 2, 25);
        shapeRenderer.end();
    }

    //Return true if dead
    public boolean toggleByValue(boolean increase, int value){
        if(increase){
            damage = Math.min(Math.max(damage + value, 0), 100);
        }else{
            damage = Math.min(Math.max(damage - value, 0), 100);
        }
        if(damage <= 0){
            return true;
        }
        return false;
    }

}
