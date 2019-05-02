package com.integrity.games.util;

import com.badlogic.gdx.math.Vector2;

/**
 * A Vector class with a constructor using the polar coordinate system
 * @author jonathan
 */
public class PolarVector extends Vector2 {
    
    /**
     * Create a new vector
     * @param angle angle of vector
     * @param length2 
     */
    public PolarVector(float length2, float angle) {
        //set as unit vector
        this.x = 1;
        this.y = 1;
        //then modify it
        this.setAngleRad(angle);
        this.setLength2(length2);        
    }
}
