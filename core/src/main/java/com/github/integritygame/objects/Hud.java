package com.github.integritygame.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hud {

    private List<PlayerHud> hBar;
    private int width;
    private int height;

    public Hud(int width, int height){
        this.width = width;
        this.height = height;
        hBar = new ArrayList<>();
        hBar.add(new PlayerHud(10,height - 90));
        hBar.add(new PlayerHud(width - 210,height - 90));
        hBar.get(0).setName("Zach");
        hBar.get(1).setName("Computer");
    }

    public void render(ShapeRenderer shapeRenderer, SpriteBatch spriteBatch){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(121/255f,121/255f,121/255f,1);
        shapeRenderer.rect(0, height - 100, width, 100);
        shapeRenderer.end();

        for(PlayerHud hud : hBar){
            hud.render(shapeRenderer);
            hud.renderText(spriteBatch);
        }
    }

    //Returns true if deads
    public boolean setPlayerTankDamage(boolean increase, int tankNumber, int damage){
        if(tankNumber <= hBar.size()){
            return hBar.get(tankNumber).toggleByValue(increase, damage);
        }
        return false;
    }

    public void setTankName(int tankNumber, String name){
        if(tankNumber <= hBar.size()){
            hBar.get(tankNumber).setName(name);
        }
    }

}
