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
        gl.glTranslatef(x, y, z);
        gl.glScalef(size, size, size); 
        gl.glBegin(GL2.GL_QUADS);
        gl.glColor3f(1, 0, 0); 
        
        // Setting a cube (obstacle)
        gl.glVertex3f(-0.5f, -0.5f, -0.5f);
        gl.glVertex3f(0.5f, -0.5f, -0.5f);
        gl.glVertex3f(0.5f, 0.5f, -0.5f);
        gl.glVertex3f(-0.5f, 0.5f, -0.5f);

        gl.glEnd();
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