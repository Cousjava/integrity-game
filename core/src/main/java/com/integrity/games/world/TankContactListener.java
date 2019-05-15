package com.integrity.games.world;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.github.integritygame.objects.Bullet;
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
        
        Object fixtureAData = fixtureA.getBody().getUserData();
        Object fixtureBData = fixtureB.getBody().getUserData();
        
        Tank tank = null;
        if (fixtureAData instanceof Tank && fixtureBData instanceof Tank) {
            tankCollision((Tank) fixtureAData, (Tank) fixtureBData);
            return;
        }
        if (fixtureAData instanceof Tank && fixtureBData instanceof Bullet) {
            bulletCollision((Tank) fixtureAData);
        }
        if (fixtureBData instanceof Tank && fixtureAData instanceof Bullet) {
            bulletCollision((Tank) fixtureBData);
        }
        if (fixtureAData instanceof Bullet) {
            ((Bullet) fixtureAData).setImpacted(true);
        }
        if (fixtureBData instanceof Bullet) {
            ((Bullet) fixtureBData).setImpacted(true);
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
