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

    public boolean checkCollisionAndEndGame(float otherX, float otherZ, float otherY, float otherSize, float playerY, float playerSize) {
        // Metade do tamanho do objeto atual (obstáculo)
        float halfSize = size / 2;
        float otherHalfSize = otherSize / 2;

        // Calcular os limites do objeto atual (obstáculo)
        float left = x - halfSize;
        float right = x + halfSize;
        float front = z + halfSize;
        float back = z - halfSize;
        float upperFace = y + halfSize;

        // Calcular os limites do outro objeto (jogador)
        float otherLeft = otherX - otherHalfSize;
        float otherRight = otherX + otherHalfSize;
        float otherFront = otherZ + otherHalfSize;
        float otherBack = otherZ - otherHalfSize;
        float otherDownFace = otherY - otherHalfSize;

        // Calcular os limites da altura do jogador (levando em conta a posição Y)
        float playerHalfSize = playerSize / 2;
        float playerTop = playerY + playerHalfSize;
        float playerBottom = playerY - playerHalfSize;

        // Verificar colisão na horizontal (x, z)
        boolean collisionX = (right >= otherLeft) && (left <= otherRight);
        boolean collisionZ = (front >= otherBack) && (back <= otherFront);

        // Verificar colisão na direção Y (altura), considerando a posição Y enquanto o jogador está pulando
        boolean collisionY = (playerTop > back) && (playerBottom < front);  // Corrigido para uma colisão vertical adequada

        // A colisão ocorre quando as três condições de colisão (horizontal e vertical) são verdadeiras
        return collisionX && collisionZ && collisionY;
    }




}

