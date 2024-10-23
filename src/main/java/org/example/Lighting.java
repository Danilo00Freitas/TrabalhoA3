package org.example;

import com.jogamp.opengl.GL2;

public class Lighting {
    public void applyLighting(GL2 gl) {
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT0);

        // Configura a fonte de luz
        float[] lightPos = {0.0f, 10.0f, 10.0f, 1.0f}; // Posição da luz
        float[] lightColor = {1.0f, 1.0f, 1.0f, 1.0f}; // Cor da luz branca

        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_POSITION, lightPos, 0);
        gl.glLightfv(GL2.GL_LIGHT0, GL2.GL_DIFFUSE, lightColor, 0);
    }
}
