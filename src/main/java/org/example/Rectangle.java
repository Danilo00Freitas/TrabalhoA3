package org.example;

import com.jogamp.opengl.GL2;

public class Rectangle {
    private float x, y, z;
    private float size;

    public Rectangle(float x, float z, float size) {
        this.x = x;
        this.y = 0;
        this.z = z;
        this.size = size;
    }

    public void draw(GL2 gl) {
        gl.glPushMatrix();
        gl.glTranslatef(x, y, z);  // Move o cubo para a posição especificada
        gl.glScalef(size, size, size);  // Ajusta o tamanho do cubo de acordo com o fator "size"

        gl.glColor3f(1, 0, 0);  // Define a cor do cubo como vermelho

        // Desenhar as seis faces do cubo
        gl.glBegin(GL2.GL_QUADS);

        // Face frontal
        gl.glVertex3f(-0.5f, -0.5f, 0.5f);
        gl.glVertex3f(0.5f, -0.5f, 0.5f);
        gl.glVertex3f(0.5f, 0.5f, 0.5f);
        gl.glVertex3f(-0.5f, 0.5f, 0.5f);

        // Face traseira
        gl.glVertex3f(-0.5f, -0.5f, -0.5f);
        gl.glVertex3f(0.5f, -0.5f, -0.5f);
        gl.glVertex3f(0.5f, 0.5f, -0.5f);
        gl.glVertex3f(-0.5f, 0.5f, -0.5f);

        // Face superior
        gl.glVertex3f(-0.5f, 0.5f, 0.5f);
        gl.glVertex3f(0.5f, 0.5f, 0.5f);
        gl.glVertex3f(0.5f, 0.5f, -0.5f);
        gl.glVertex3f(-0.5f, 0.5f, -0.5f);

        // Face inferior
        gl.glVertex3f(-0.5f, -0.5f, 0.5f);
        gl.glVertex3f(0.5f, -0.5f, 0.5f);
        gl.glVertex3f(0.5f, -0.5f, -0.5f);
        gl.glVertex3f(-0.5f, -0.5f, -0.5f);

        // Face lateral esquerda
        gl.glVertex3f(-0.5f, -0.5f, 0.5f);
        gl.glVertex3f(-0.5f, 0.5f, 0.5f);
        gl.glVertex3f(-0.5f, 0.5f, -0.5f);
        gl.glVertex3f(-0.5f, -0.5f, -0.5f);

        // Face lateral direita
        gl.glVertex3f(0.5f, -0.5f, 0.5f);
        gl.glVertex3f(0.5f, 0.5f, 0.5f);
        gl.glVertex3f(0.5f, 0.5f, -0.5f);
        gl.glVertex3f(0.5f, -0.5f, -0.5f);

        gl.glEnd();  // Fim do desenho do cubo

        gl.glPopMatrix();
    }

    public void move() {
        //Moves the obstacle towards the player
        z += 0.1f; 
        if (z > 5) {
            resetObstaclePosition();
        }
    }

    private void resetObstaclePosition() {
        z = -50; 
    }
}