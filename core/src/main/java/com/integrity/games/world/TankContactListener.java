package com.integrity.games.world;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.github.integritygame.objects.Bullet;
import com.github.integritygame.objects.Tank;
import com.github.integritygame.screens.ScreenManager;

/**
 * When collisions occur between bullets and/or tanks
 * this determines what happens
 * @author jonathan cosutick
 */
public class TankContactListener implements ContactListener {
    
    private static final int TANK_COLLISION_DAMAGE = 5;

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();
        
        if (fixtureA == null || fixtureB == null) { return; }
        
        Object fixtureAData = fixtureA.getBody().getUserData();
        Object fixtureBData = fixtureB.getBody().getUserData();
        
        if (fixtureAData instanceof Tank && fixtureBData instanceof Tank) {
            tankCollision((Tank) fixtureAData, (Tank) fixtureBData);
            return;
        }
        if (fixtureAData instanceof Tank && fixtureBData instanceof Bullet) {
            bulletCollision((Tank) fixtureAData, (Bullet) fixtureBData);
        }
        if (fixtureBData instanceof Tank && fixtureAData instanceof Bullet) {
            bulletCollision((Tank) fixtureBData, (Bullet) fixtureAData);
        }
        if (fixtureAData instanceof Bullet) {
            ((Bullet) fixtureAData).setImpacted(true);
        }
        if (fixtureBData instanceof Bullet) {
            ((Bullet) fixtureBData).setImpacted(true);
        }
        
    }
    
    /**
     * Both tanks collide with each other
     */
    private void tankCollision(Tank tankA, Tank tankB) {
        tankA.toggleByValue(false, TANK_COLLISION_DAMAGE);
        tankB.toggleByValue(false, TANK_COLLISION_DAMAGE);
    }
    
    /**
     * A bullet has hit the tank
     */
    private void bulletCollision(Tank tank, Bullet bullet) {
        tank.toggleByValue(false, bullet.getBulletData().getBulletData().damage);
        bullet.getFiringTank().changeMoney(true, bullet.getBulletData().getBulletData().moneyIfHit);
        if (tank.isDead()) {
            ScreenManager.getInstance().changeScreen(ScreenManager.Screens.GAME_OVER);
        }
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
        // no-op
    }
    
}
