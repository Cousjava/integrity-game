package com.integrity.games.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.github.integritygame.objects.Tank;

/**
 * The world of the game and contains the physics stuff for it
 *
 * @author jonathan
 */
public class GameWorld {

    World world;
    
    private float timeStep;

    /**
     * @param terrain Definition of terrain for the world.
     */
    public GameWorld(EdgeShape terrain) {

        world = new World(new Vector2(0, -10), true);
        Body terrainBody = world.createBody(new BodyDef());
        terrainBody.createFixture(terrain, 0);

        world.setContactListener(new TankContactListener());
        worldEdges();
    }

    /**
     * Adds edges to all the walls of the world to
     * prevent tanks going off-screen
     */
    private void worldEdges() {
        EdgeShape leftEdge = new EdgeShape();
        leftEdge.set(0, 0, 0, Gdx.graphics.getHeight());
        EdgeShape rightEdge = new EdgeShape();
        rightEdge.set(Gdx.graphics.getWidth(), 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Body edgeBody = world.createBody(new BodyDef());
        edgeBody.setType(BodyDef.BodyType.StaticBody);
        edgeBody.createFixture(leftEdge, 0);
        edgeBody.createFixture(rightEdge, 0);

        leftEdge.dispose();
        rightEdge.dispose();
    }

    /**
     * Creates a body to represent a tank
     * and add it to the world.
     * This function does not set the tanks mass.
     *
     * @param x    x position of tank
     * @param y    y position of tank
     * @param tank details of the tank
     * @return Physical body of tank
     */
    public Body addTank(float x, float y, Tank tank) {
        BodyDef tankDef = tank.getTankBodyDef();
        tankDef.position.set(x, y);
        Body tankBody = world.createBody(tankDef);
        tankBody.createFixture(tank.getTankFixtureDef());
        return tankBody;

    }

    /**
     * Creates a bullet and adds it to the world
     */
    public Body addBullet(BodyDef bulletDef) {
        Body bulletBody = world.createBody(bulletDef);
        PolygonShape bulletShape = new PolygonShape();
        bulletShape.setAsBox(1, 1);
        Fixture bulletFixture = bulletBody.createFixture(bulletShape, 0.001f);
        Filter bulletFilter = bulletFixture.getFilterData();
        bulletFilter.categoryBits = 4;
        bulletFilter.maskBits = 3;
        bulletShape.dispose();
        return bulletBody;
    }

    /**
     * Update the world and run the simulation
     *
     * @param delta Delay between actions
     */
    public void update(float delta) {
        timeStep += delta;
        if (timeStep > 0.01f) {
            world.step(0.01f, 5, 5);
            timeStep -= 0.01f;
        }
    }

}
