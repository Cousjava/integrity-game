package com.integrity.games.util;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

/**
 *
 * @author jonathan
 */
public class GroundShader extends ShaderProgram {
    
    public GroundShader(FileHandle vertexShader, FileHandle fragmentShader) {
        super(vertexShader, fragmentShader);
    }
    
}
