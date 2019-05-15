package com.integrity.games.world;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.github.integritygame.objects.Tank;

/**
 *
 * @author jonathan cosutick
 */
public class TankContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        
        if (fixtureA == null || fixtureB == null) { return; }
        
        System.out.println(fixtureA.getUserData());
        System.out.println(fixtureB.getUserData());
        
      Tank tank = null;
        if (fixtureA.getUserData() instanceof Tank) {
            tank = (Tank) fixtureA.getUserData();
        }
        if (fixtureB.getUserData() instanceof Tank) {
            if (tank == null) {
                tank = (Tank) fixtureB.getUserData();
            } else {
                //both are tanks
                tankCollision(tank, (Tank) fixtureB.getUserData());
                return;
            }
        }
        if (tank != null) {
            //One is tank, the other is bullet
            bulletCollision(tank);
        }
        
    }
    
    /**
     * 
     */
    private void tankCollision(Tank tankA, Tank tankB) {
        tankA.toggleByValue(false, 5);
        tankB.toggleByValue(false, 5);
    }
    
    private void bulletCollision(Tank tank) {
        tank.toggleByValue(false, 10);
    }

    @Override
    public void endContact(Contact contact) {
        //no-op
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
        // no-op
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
        // TODO: Impact damage on tank
    }
    
}
