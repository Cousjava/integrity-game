package com.integrity.games.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.World;
import com.github.integritygame.objects.Tank;
import com.integrity.games.util.PolarVector;

/**
 *
 * @author jonathan
 */
public class GameWorld {
    
    private static final int worldHeight = 180;
    
    World world;
    
    /**
     * 
     * @param terrain Definition of terrain for the world.
     */
    public GameWorld(EdgeShape terrain) {
        
        world = new World(new PolarVector(1, 0), true);
        Body terrainBody = world.createBody(new BodyDef());
        //terrainBody.setLinearDamping(1);
        terrainBody.createFixture(terrain, 0);
        
        world.setContactListener(new TankContactListener());
        worldEdges();
    }
    
    public GameWorld() {
        EdgeShape terrain = new EdgeShape();
        terrain.set(0, 180, 1000, 180);
        world = new World(new PolarVector(1, 0), true);
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
        edgeBody.createFixture(leftEdge, 0);
        edgeBody.createFixture(rightEdge, 0);
    }
    
    /**
     * Creates a body to represent a tank
     * and add it to the world.
     * This function does not set the tanks mass.
     * @param x x position of tank
     * @param y y position of tank
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
    
    public void update(float delta) {
        world.step(delta, 1, 1);
    }
    
    
    
}
