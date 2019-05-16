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

    public Hud(int width, int height) {
        this.width = width;
        this.height = height;
        hBar = new ArrayList<>();
        hBar.add(new PlayerHud(10, height - 90));
        hBar.add(new PlayerHud(width - 210, height - 90));
        hBar.get(0).setName(VariableManager.getInstance().getString("PlayerOneName"));
        hBar.get(1).setName(VariableManager.getInstance().getString("PlayerTwoName"));
        generalHud = new GeneralHud(width / 2, height - 90);
    }

    public void render(ShapeRenderer shapeRenderer, SpriteBatch spriteBatch) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(121 / 255f, 121 / 255f, 121 / 255f, 1);
        shapeRenderer.rect(0, height - 110, width, 110);
        shapeRenderer.end();

        generalHud.render(spriteBatch);

        for (PlayerHud hud : hBar) {
            hud.render(shapeRenderer);
            hud.renderText(spriteBatch);
        }
    }

    public void setTankName(int tankNumber, String name) {
        if (tankNumber <= hBar.size()) {
            hBar.get(tankNumber).setName(name);
        }
    }

    public List<PlayerHud> getPlayerHuds() {
        return hBar;
    }

}
