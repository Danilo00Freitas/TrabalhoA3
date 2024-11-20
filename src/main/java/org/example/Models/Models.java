package org.example.Models;

import com.jogamp.opengl.GL2;

public class Models {
    private final float x = 0;
    private final float y = 0;
    private final float z = 0;
    private float cubeSize = 1.0f;

    // Construtor que permite definir o tamanho do cubo
    public Models(float size) {
        this.cubeSize = size;
    }

    // Desenha o cubo com o tamanho especificado
    public void draw(GL2 gl, float x, float y) {
        gl.glPushMatrix();
        gl.glTranslatef(x, y, 0);
        gl.glColor3f(0, 0, 1); // Define a cor do cubo

        // Desenhar as seis faces do cubo usando o tamanho especificado
        float halfCubeSize = cubeSize / 2;
        gl.glBegin(GL2.GL_QUADS);

        // Face frontal
        gl.glVertex3f(-halfCubeSize, -halfCubeSize, halfCubeSize);
        gl.glVertex3f(halfCubeSize, -halfCubeSize, halfCubeSize);
        gl.glVertex3f(halfCubeSize, halfCubeSize, halfCubeSize);
        gl.glVertex3f(-halfCubeSize, halfCubeSize, halfCubeSize);

        // Face traseira
        gl.glVertex3f(-halfCubeSize, -halfCubeSize, -halfCubeSize);
        gl.glVertex3f(halfCubeSize, -halfCubeSize, -halfCubeSize);
        gl.glVertex3f(halfCubeSize, halfCubeSize, -halfCubeSize);
        gl.glVertex3f(-halfCubeSize, halfCubeSize, -halfCubeSize);

        // Face superior
        gl.glVertex3f(-halfCubeSize, halfCubeSize, halfCubeSize);
        gl.glVertex3f(halfCubeSize, halfCubeSize, halfCubeSize);
        gl.glVertex3f(halfCubeSize, halfCubeSize, -halfCubeSize);
        gl.glVertex3f(-halfCubeSize, halfCubeSize, -halfCubeSize);

        // Face inferior
        gl.glVertex3f(-halfCubeSize, -halfCubeSize, halfCubeSize);
        gl.glVertex3f(halfCubeSize, -halfCubeSize, halfCubeSize);
        gl.glVertex3f(halfCubeSize, -halfCubeSize, -halfCubeSize);
        gl.glVertex3f(-halfCubeSize, -halfCubeSize, -halfCubeSize);

        // Face lateral esquerda
        gl.glVertex3f(-halfCubeSize, -halfCubeSize, halfCubeSize);
        gl.glVertex3f(-halfCubeSize, halfCubeSize, halfCubeSize);
        gl.glVertex3f(-halfCubeSize, halfCubeSize, -halfCubeSize);
        gl.glVertex3f(-halfCubeSize, -halfCubeSize, -halfCubeSize);

        // Face lateral direita
        gl.glVertex3f(halfCubeSize, -halfCubeSize, halfCubeSize);
        gl.glVertex3f(halfCubeSize, halfCubeSize, halfCubeSize);
        gl.glVertex3f(halfCubeSize, halfCubeSize, -halfCubeSize);
        gl.glVertex3f(halfCubeSize, -halfCubeSize, -halfCubeSize);

        gl.glEnd();  // Fim do desenho do cubo
        gl.glPopMatrix();
    }

    // Retorna o tamanho do cubo
    public float getCubeSize() {
        return cubeSize;
    }
}
