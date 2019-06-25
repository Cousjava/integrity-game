package com.integrity.games.util;

import com.badlogic.gdx.math.Vector2;
import java.util.Random;

/**
 * Generates a 2D terrain.
 * 
 * The terrain is composed of a series of heights at close
 * intervals to simulate curves using a stochastic process.
 * 
 * The result is a series of points at 1 unit horizontal intervals
 * with a height between 0 and 1;
 * 
 * @author jonathan coustick
 * 
 */
public class TerrainGenerator {
    
    private final float variance;
    private int length;
    private int iterations;
    
    Vector2[] points;
    
    private Random random;
    
    /**
     * Creates a generator of random terrain
     * @param variance Degree of variance.
     * @param length The length of distance to generate for
     * @param iterations The number of iteration to undergo. Must be in the range 0-31 inclusive.
     */
    public TerrainGenerator(float variance, int length, int iterations) {
        this.variance = variance;
        this.length = length;
        this.iterations = iterations;
        random = new Random();
        if (iterations < 0 || iterations > 31) {
            // Cannot be 32 as 2^32 is > MAX_INT and will overflow
            throw new IllegalArgumentException(iterations + " is out of permissible range: must be 0-31 inclusive");
        }
        int numberPoints = (int) Math.pow(2, iterations);
        points = new Vector2[numberPoints + 1];
        
    }
    
    
    public Vector2[] generate() {
        points[0] = new Vector2(0, jitter(0));
        points[points.length - 1] = new Vector2(length, jitter(0));
        
        System.out.println(this.toString());
        new Exception().printStackTrace();
        for (int i = 1; i <= iterations; i++) {
            double limit =  Math.pow(2, i);
            int stepper = (int) Math.pow(2, iterations - i);
            for (int j = 1; j < limit; j+=2) {   
                points[stepper * j] = new Vector2( (float) ((length * stepper * j) / (points.length - 1)), (points[stepper * (j - 1)].y + points[stepper * (j + 1)].y) / 2 + jitter(i));
            }
            
        }        
        return points;
    }
    
    private float jitter(int iteration) {
        float jitter = (random.nextFloat() - 0.5f) * variance;
        jitter /= iteration + 1;
        if (jitter > 1) {
            jitter = 1;
        } else if (jitter < 0) {
            jitter = 0;
        }
        return jitter;
    }
    
}
