package org.example;

import com.jogamp.opengl.GL2;

public class Track {
    private float positionZ = 0;

    public void draw(GL2 gl) {
        gl.glPushMatrix();
        //Moves the track on the Z axe
        gl.glTranslatef(0, -1, positionZ);
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(0.5f, 0.5f, 0.5f);
        gl.glVertex3f(-5, 0, 0);
        gl.glVertex3f(5, 0, 0);
        gl.glVertex3f(5, 0, -50);
        gl.glVertex3f(-5, 0, -50);
        gl.glEnd();
        gl.glPopMatrix();
    }

    public void move() {
        //Track move speed
        positionZ += 0.1f;
        if (positionZ > 10) {
        //Reseting track position
            positionZ = 0;
        }
    }
}