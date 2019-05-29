package com.github.integritygame.objects;

import com.badlogic.gdx.math.Vector2;
import com.github.integritygame.GameTest;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class TankTest extends GameTest {

    @Test
    public void testTankMovementPositive() {
        Tank tank = new Tank(0, 0, 40, 25, false);
        assertThat(tank.getCurrentPosition(), equalTo(new Vector2(20, 12)));
        tank.updateX(true);
        assertThat(tank.getCurrentPosition(), equalTo(new Vector2(20.5f, 12)));
        tank.updateX(true);
        assertThat(tank.getCurrentPosition(), equalTo(new Vector2(21, 12)));
    }

    @Test
    public void testTankMovementNegative() {
        Tank tank = new Tank(20, 0, 40, 25, false);
        assertThat(tank.getCurrentPosition(), equalTo(new Vector2(40, 12)));
        tank.updateX(false);
        assertThat(tank.getCurrentPosition(), equalTo(new Vector2(39.5f, 12)));
        tank.updateX(false);
        assertThat(tank.getCurrentPosition(), equalTo(new Vector2(39, 12)));
    }

    @Test
    public void testHittingEdgeOfScreenLeft() {
        Tank tank = new Tank(0, 0, 40, 25, false);
        assertThat(tank.getCurrentPosition(), equalTo(new Vector2(20, 12)));
        tank.updateX(false);
        assertThat(tank.getCurrentPosition(), equalTo(new Vector2(20, 12)));
    }

    @Test
    public void testHittingEdgeOfScreenRight() {
        Tank tank = new Tank(1240, 0, 40, 25, false);
        assertThat(tank.getCurrentPosition(), equalTo(new Vector2(1260, 12)));
        tank.updateX(true);
        assertThat(tank.getCurrentPosition(), equalTo(new Vector2(1260, 12)));
    }

    @Test
    public void rotatePositive() {
        Tank tank = new Tank(0, 0, 40, 25, false);
        assertThat(tank.getRotation(), equalTo(90f));
        tank.rotate(true);
        assertThat(tank.getRotation(), equalTo(89f));
    }

    @Test
    public void rotateNegative() {
        Tank tank = new Tank(0, 0, 40, 25, false);
        assertThat(tank.getRotation(), equalTo(90f));
        tank.rotate(false);
        assertThat(tank.getRotation(), equalTo(91f));
    }

}
