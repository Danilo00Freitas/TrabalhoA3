package org.example;

import com.jogamp.opengl.GL2;

public class Models {
    private float x = 0, y = 0;

    public void draw(GL2 gl) {
        gl.glPushMatrix();
        gl.glTranslatef(x, y, 0);
        gl.glColor3f(0, 0, 1); // Cor da nave
        gl.glBegin(GL2.GL_TRIANGLES);
        // Desenha um simples triângulo como a nave
        gl.glVertex3f(-0.5f, 0, 0);
        gl.glVertex3f(0.5f, 0, 0);
        gl.glVertex3f(0, 1, 0);
        gl.glEnd();
        gl.glPopMatrix();
    }

    public void move(float deltaX, float deltaY) {
        x += deltaX;
        y += deltaY;
    }
}
