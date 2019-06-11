package com.github.integritygame.objects;

import com.badlogic.gdx.math.Vector2;
import com.github.integritygame.GameTest;
import com.github.integritygame.util.InputManager;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.Assert;

public class TankTest extends GameTest {
    
    private static int OFFSET = 18;
    private static int HEIGHT = 24;

    @Test
    public void testTankMovementPositive() {
        int basex = 40 + OFFSET;
        
        Tank tank = new Tank(0, 0);
        tank.setTankBody(gameWorld.createBody(tank.getTankBodyDef()));
        assertThat(tank.getCurrentPosition(), equalTo(new Vector2(basex, HEIGHT)));
        tank.updateX(true);
        gameWorld.step(1, 1, 1);
        Assert.assertTrue(approximateEquals(tank.getCurrentPosition(), new Vector2(basex + 2f, HEIGHT)));
        tank.updateX(true);
        gameWorld.step(1, 1, 1);
        Assert.assertTrue(approximateEquals(tank.getCurrentPosition(), new Vector2(basex + 4f, HEIGHT)));
    }

    @Test
    public void testTankMovementNegative() {
        int basex = 40 + OFFSET;
        
        Tank tank = new Tank(20, 0);
        tank.setTankBody(gameWorld.createBody(tank.getTankBodyDef()));
        basex += 20;
        
        assertThat(tank.getCurrentPosition(), equalTo(new Vector2(basex, HEIGHT)));
        tank.updateX(false);
        gameWorld.step(1, 1, 1);
        Assert.assertTrue(tank.getCurrentPosition() + "not eqaul to " + (new Vector2(basex - 2f, HEIGHT)).toString(),
                approximateEquals(tank.getCurrentPosition(), new Vector2(basex - 2f, HEIGHT)));
        
        tank.updateX(false);
        gameWorld.step(1, 1, 1);
         Assert.assertTrue(approximateEquals(tank.getCurrentPosition(), new Vector2(basex - 4f, HEIGHT)));
    }

    @Test
    public void testHittingEdgeOfScreenLeft() {
        Tank tank = new Tank(0, 0);
        int basex = 40 + OFFSET;
        tank.setTankBody(gameWorld.createBody(tank.getTankBodyDef()));
        assertThat(tank.getCurrentPosition(), equalTo(new Vector2(basex, HEIGHT)));
        tank.updateX(false);
        assertThat(tank.getCurrentPosition(), equalTo(new Vector2(basex, HEIGHT)));
    }

    @Test
    public void testHittingEdgeOfScreenRight() {
        int basex = 40 + OFFSET;
        Tank tank = new Tank(1240, 0);
        basex += 1240;
        tank.setTankBody(gameWorld.createBody(tank.getTankBodyDef()));
        assertThat(tank.getCurrentPosition(), equalTo(new Vector2(basex, HEIGHT)));
        tank.updateX(true);
        assertThat(tank.getCurrentPosition(), equalTo(new Vector2(basex, HEIGHT)));
    }

    @Test
    public void rotatePositive() {
        Tank tank = new Tank(0, 0);
        UserTurn turn = new UserTurn(tank, InputManager.Control.RIGHT);
        tank.registerTurn(turn);
        tank.setTankBody(gameWorld.createBody(tank.getTankBodyDef()));
        assertThat(tank.getRotation(), equalTo(180f));
        tank.rotate(true);
        assertThat(tank.getRotation(), equalTo(179f));
    }

    @Test
    public void rotateNegative() {
        Tank tank = new Tank(0, 0);
        tank.setTankBody(gameWorld.createBody(tank.getTankBodyDef()));
        assertThat(tank.getRotation(), equalTo(0f));
        tank.rotate(false);
        assertThat(tank.getRotation(), equalTo(1f));
    }
    
    /**
     * Returns true if two vectors are almost the same.
     * This is because vectors are float-based and so arithmetic is not exact.
     */
    private boolean approximateEquals(Vector2 vector1, Vector2 vector2) {
        return vector1.dst(vector2) < 0.01;
        
    }

}
