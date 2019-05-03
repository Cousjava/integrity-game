package com.integrity.games.world;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

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
