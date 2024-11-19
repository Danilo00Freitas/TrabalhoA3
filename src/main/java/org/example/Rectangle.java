package org.example;

import com.jogamp.opengl.GL2;
import org.example.Interface.GameInterface;

public class Rectangle {
    private final float speed = 0.3f;
    private float x, y;
    public float z;
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

        gl.glColor3f(1, 0, 0);

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

    public void move(float newX) {
        //Moves the obstacle towards the player
        z += speed;
        if (z > 5) {
            resetObstaclePosition(newX);
        }
    }

    private void resetObstaclePosition(float newX) {
        this.z = -50;
        this.x = newX;

    }

    public boolean checkCollisionAndEndGame(float otherX, float otherZ, float otherSize) {
        // Metade do tamanho do objeto atual e do outro objeto
        float halfSize = size / 2;
        float otherHalfSize = otherSize / 2;

        // Calcular os limites do objeto atual
        float left = x - halfSize;
        float right = x + halfSize;
        float front = z + halfSize;
        float back = z - halfSize;

        // Calcular os limites do outro objeto
        float otherLeft = otherX - otherHalfSize;
        float otherRight = otherX + otherHalfSize;
        float otherFront = otherZ + otherHalfSize;
        float otherBack = otherZ - otherHalfSize;

        // Verificar se há interseção entre os limites dos dois objetos
        boolean collisionX = (right >= otherLeft) && (left <= otherRight);
        boolean collisionZ = (front >= otherBack) && (back <= otherFront);

        // Retornar true se houver colisão em ambas as direções
        return collisionX && collisionZ;
    }


}

