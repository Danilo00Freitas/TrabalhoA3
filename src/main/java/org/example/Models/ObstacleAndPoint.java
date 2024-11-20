package org.example.Models;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ObstacleAndPoint {
    private final float speed = 0.3f;
    private final float y;
    private final float size;
    private final float pointSize;
    private boolean isCollected;
    public float z;
    private float x;
    private Texture pointTexture;
    private Texture obstacleTexture;

    public ObstacleAndPoint(float x, float z, float size) throws IOException {
        this.x = x;
        this.y = 0;
        this.z = z;
        this.size = size;
        this.pointSize = size / 4;
        this.isCollected = false;

        // Carregar as texturas
        pointTexture = loadTexture("/home/dandan/Documents/faculdade/computGraf/A3/TrabalhoA3/src/main/java/org/example/Models/textures/point.jpg");
        obstacleTexture = loadTexture("/home/dandan/Documents/faculdade/computGraf/A3/TrabalhoA3/src/main/java/org/example/Models/textures/obstacle.jpg");
    }

    private Texture loadTexture(String path) throws IOException {
        // Lê a imagem
        BufferedImage img = ImageIO.read(new File(path));
        // Converte a BufferedImage para um InputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(img, "png", byteArrayOutputStream);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        // Cria a textura a partir do InputStream
        return TextureIO.newTexture(byteArrayInputStream, true, "PNG");
    }

    public void drawObstacle(GL2 gl) {
        gl.glPushMatrix();
        gl.glTranslatef(x, y, z);
        gl.glScalef(size, size, size);

        // Ativar a textura
        obstacleTexture.enable(gl);
        obstacleTexture.bind(gl);

        gl.glColor3f(1, 1, 1); // Branco, pois a textura já contém a cor

        // Desenhar as seis faces do cubo
        gl.glBegin(GL2.GL_QUADS);

        // Face frontal
        gl.glTexCoord2f(0, 0); gl.glVertex3f(-0.5f, -0.5f, 0.5f);
        gl.glTexCoord2f(1, 0); gl.glVertex3f(0.5f, -0.5f, 0.5f);
        gl.glTexCoord2f(1, 1); gl.glVertex3f(0.5f, 0.5f, 0.5f);
        gl.glTexCoord2f(0, 1); gl.glVertex3f(-0.5f, 0.5f, 0.5f);

        // Face traseira
        gl.glTexCoord2f(0, 0); gl.glVertex3f(-0.5f, -0.5f, -0.5f);
        gl.glTexCoord2f(1, 0); gl.glVertex3f(0.5f, -0.5f, -0.5f);
        gl.glTexCoord2f(1, 1); gl.glVertex3f(0.5f, 0.5f, -0.5f);
        gl.glTexCoord2f(0, 1); gl.glVertex3f(-0.5f, 0.5f, -0.5f);

        // Face superior
        gl.glTexCoord2f(0, 0); gl.glVertex3f(-0.5f, 0.5f, 0.5f);
        gl.glTexCoord2f(1, 0); gl.glVertex3f(0.5f, 0.5f, 0.5f);
        gl.glTexCoord2f(1, 1); gl.glVertex3f(0.5f, 0.5f, -0.5f);
        gl.glTexCoord2f(0, 1); gl.glVertex3f(-0.5f, 0.5f, -0.5f);

        // Face inferior
        gl.glTexCoord2f(0, 0); gl.glVertex3f(-0.5f, -0.5f, 0.5f);
        gl.glTexCoord2f(1, 0); gl.glVertex3f(0.5f, -0.5f, 0.5f);
        gl.glTexCoord2f(1, 1); gl.glVertex3f(0.5f, -0.5f, -0.5f);
        gl.glTexCoord2f(0, 1); gl.glVertex3f(-0.5f, -0.5f, -0.5f);

        // Face lateral esquerda
        gl.glTexCoord2f(0, 0); gl.glVertex3f(-0.5f, -0.5f, 0.5f);
        gl.glTexCoord2f(1, 0); gl.glVertex3f(-0.5f, 0.5f, 0.5f);
        gl.glTexCoord2f(1, 1); gl.glVertex3f(-0.5f, 0.5f, -0.5f);
        gl.glTexCoord2f(0, 1); gl.glVertex3f(-0.5f, -0.5f, -0.5f);

        // Face lateral direita
        gl.glTexCoord2f(0, 0); gl.glVertex3f(0.5f, -0.5f, 0.5f);
        gl.glTexCoord2f(1, 0); gl.glVertex3f(0.5f, 0.5f, 0.5f);
        gl.glTexCoord2f(1, 1); gl.glVertex3f(0.5f, 0.5f, -0.5f);
        gl.glTexCoord2f(0, 1); gl.glVertex3f(0.5f, -0.5f, -0.5f);

        gl.glEnd();

        gl.glPopMatrix();

        // Desativar a textura
        obstacleTexture.disable(gl);
    }

    public void drawPoint(GL2 gl) {
        if (isCollected) return;

        gl.glPushMatrix();
        gl.glTranslatef(x, 3.5f, z);
        gl.glScalef(pointSize, pointSize, pointSize);

        // Ativar a textura
        pointTexture.enable(gl);
        pointTexture.bind(gl);

        gl.glColor3f(1, 1, 1); // Branco, pois a textura já contém a cor

        // Desenhar as seis faces do cubo
        gl.glBegin(GL2.GL_QUADS);

        // Face frontal
        gl.glTexCoord2f(0, 0); gl.glVertex3f(-0.5f, -0.5f, 0.5f);
        gl.glTexCoord2f(1, 0); gl.glVertex3f(0.5f, -0.5f, 0.5f);
        gl.glTexCoord2f(1, 1); gl.glVertex3f(0.5f, 0.5f, 0.5f);
        gl.glTexCoord2f(0, 1); gl.glVertex3f(-0.5f, 0.5f, 0.5f);

        // Face traseira
        gl.glTexCoord2f(0, 0); gl.glVertex3f(-0.5f, -0.5f, -0.5f);
        gl.glTexCoord2f(1, 0); gl.glVertex3f(0.5f, -0.5f, -0.5f);
        gl.glTexCoord2f(1, 1); gl.glVertex3f(0.5f, 0.5f, -0.5f);
        gl.glTexCoord2f(0, 1); gl.glVertex3f(-0.5f, 0.5f, -0.5f);

        // Face superior
        gl.glTexCoord2f(0, 0); gl.glVertex3f(-0.5f, 0.5f, 0.5f);
        gl.glTexCoord2f(1, 0); gl.glVertex3f(0.5f, 0.5f, 0.5f);
        gl.glTexCoord2f(1, 1); gl.glVertex3f(0.5f, 0.5f, -0.5f);
        gl.glTexCoord2f(0, 1); gl.glVertex3f(-0.5f, 0.5f, -0.5f);

        // Face inferior
        gl.glTexCoord2f(0, 0); gl.glVertex3f(-0.5f, -0.5f, 0.5f);
        gl.glTexCoord2f(1, 0); gl.glVertex3f(0.5f, -0.5f, 0.5f);
        gl.glTexCoord2f(1, 1); gl.glVertex3f(0.5f, -0.5f, -0.5f);
        gl.glTexCoord2f(0, 1); gl.glVertex3f(-0.5f, -0.5f, -0.5f);

        // Face lateral esquerda
        gl.glTexCoord2f(0, 0); gl.glVertex3f(-0.5f, -0.5f, 0.5f);
        gl.glTexCoord2f(1, 0); gl.glVertex3f(-0.5f, 0.5f, 0.5f);
        gl.glTexCoord2f(1, 1); gl.glVertex3f(-0.5f, 0.5f, -0.5f);
        gl.glTexCoord2f(0, 1); gl.glVertex3f(-0.5f, -0.5f, -0.5f);

        // Face lateral direita
        gl.glTexCoord2f(0, 0); gl.glVertex3f(0.5f, -0.5f, 0.5f);
        gl.glTexCoord2f(1, 0); gl.glVertex3f(0.5f, 0.5f, 0.5f);
        gl.glTexCoord2f(1, 1); gl.glVertex3f(0.5f, 0.5f, -0.5f);
        gl.glTexCoord2f(0, 1); gl.glVertex3f(0.5f, -0.5f, -0.5f);

        gl.glEnd();

        gl.glPopMatrix();

        // Desativar a textura
        pointTexture.disable(gl);
    }

    public void move(float newX) {

        z += speed;
        if (z > 5) {
            resetObstaclePosition(newX);
        }
    }

    private void resetObstaclePosition(float newX) {
        this.z = -50;
        this.x = newX;
        this.isCollected = false;
    }

    public boolean checkObstacleColision(
            float otherX, float otherZ, float otherY, float otherSize) {
        float halfSize = size / 2;

        // Limites do obstáculo inferior
        float left = x - halfSize;
        float right = x + halfSize;
        float front = z + halfSize;
        float back = z - halfSize;
        float top = y + halfSize; // Superior do obstáculo
        float bottom = y - halfSize; // Inferior do obstáculo

        // Limites do outro objeto (jogador)
        float otherHalfSize = otherSize / 2;
        float otherLeft = otherX - otherHalfSize;
        float otherRight = otherX + otherHalfSize;
        float otherFront = otherZ + otherHalfSize;
        float otherBack = otherZ - otherHalfSize;
        float otherTop = otherY + otherHalfSize;
        float otherBottom = otherY - otherHalfSize;

        // Verificar colisão
        boolean collisionX = (right >= otherLeft) && (left <= otherRight);
        boolean collisionZ = (front >= otherBack) && (back <= otherFront);
        boolean collisionY = (top >= otherBottom) && (bottom <= otherTop);

        return collisionX && collisionZ && collisionY;
    }

    public boolean checkPointColision(
            float otherX, float otherZ, float otherY, float otherSize,float pointYLocation) {
        if (isCollected) return false;

        float halfPointSize = pointSize / 2;
        float y = pointYLocation;

        // Limites do ponto
        float left = x - halfPointSize;
        float right = x + halfPointSize;
        float front = z + halfPointSize;
        float back = z - halfPointSize;
        float top = y + halfPointSize;  // Considera o y do ponto
        float bottom = y - halfPointSize;

        // Limites do jogador
        float otherHalfSize = otherSize / 2;
        float otherLeft = otherX - otherHalfSize;
        float otherRight = otherX + otherHalfSize;
        float otherFront = otherZ + otherHalfSize;
        float otherBack = otherZ - otherHalfSize;
        float otherTop = otherY + otherHalfSize;
        float otherBottom = otherY - otherHalfSize;

        // Verificar colisão
        boolean collisionX = (right >= otherLeft) && (left <= otherRight);
        boolean collisionZ = (front >= otherBack) && (back <= otherFront);
        boolean collisionY = (top >= otherBottom) && (bottom <= otherTop);

        if (collisionX && collisionZ && collisionY) {
            isCollected = true; // Marca o ponto como coletado
            return true; // Retorna verdadeiro porque houve colisão
        }
        return false; // Sem colisão
    }
}
