package org.example.Models;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;

public class Models {
    private final float x = 0;
    private final float y = 0;
    private final float z = 0;
    private float cubeSize = 1.0f;
    private Texture texture; // A textura para o cubo

    // Construtor que permite definir o tamanho do cubo e a textura
    public Models(float size, Texture texture) {
        this.cubeSize = size;
        this.texture = texture;
    }

    // Desenha o cubo com o tamanho especificado e a textura
    public void draw(GL2 gl, float x, float y) {
        gl.glPushMatrix();
        gl.glTranslatef(x, y, 0);

        // Ativar e vincular a textura
        if (texture != null) {
            texture.enable(gl);
            texture.bind(gl);
        }

        // Definir cor branca para utilizar as cores da textura
        gl.glColor3f(1, 1, 1);

        // Desenhar as seis faces do cubo usando o tamanho especificado
        float halfCubeSize = cubeSize / 2;
        gl.glBegin(GL2.GL_QUADS);

        // Face frontal
        gl.glTexCoord2f(0, 0); gl.glVertex3f(-halfCubeSize, -halfCubeSize, halfCubeSize);
        gl.glTexCoord2f(1, 0); gl.glVertex3f(halfCubeSize, -halfCubeSize, halfCubeSize);
        gl.glTexCoord2f(1, 1); gl.glVertex3f(halfCubeSize, halfCubeSize, halfCubeSize);
        gl.glTexCoord2f(0, 1); gl.glVertex3f(-halfCubeSize, halfCubeSize, halfCubeSize);

        // Face traseira
        gl.glTexCoord2f(0, 0); gl.glVertex3f(-halfCubeSize, -halfCubeSize, -halfCubeSize);
        gl.glTexCoord2f(1, 0); gl.glVertex3f(halfCubeSize, -halfCubeSize, -halfCubeSize);
        gl.glTexCoord2f(1, 1); gl.glVertex3f(halfCubeSize, halfCubeSize, -halfCubeSize);
        gl.glTexCoord2f(0, 1); gl.glVertex3f(-halfCubeSize, halfCubeSize, -halfCubeSize);

        // Face superior
        gl.glTexCoord2f(0, 0); gl.glVertex3f(-halfCubeSize, halfCubeSize, halfCubeSize);
        gl.glTexCoord2f(1, 0); gl.glVertex3f(halfCubeSize, halfCubeSize, halfCubeSize);
        gl.glTexCoord2f(1, 1); gl.glVertex3f(halfCubeSize, halfCubeSize, -halfCubeSize);
        gl.glTexCoord2f(0, 1); gl.glVertex3f(-halfCubeSize, halfCubeSize, -halfCubeSize);

        // Face inferior
        gl.glTexCoord2f(0, 0); gl.glVertex3f(-halfCubeSize, -halfCubeSize, halfCubeSize);
        gl.glTexCoord2f(1, 0); gl.glVertex3f(halfCubeSize, -halfCubeSize, halfCubeSize);
        gl.glTexCoord2f(1, 1); gl.glVertex3f(halfCubeSize, -halfCubeSize, -halfCubeSize);
        gl.glTexCoord2f(0, 1); gl.glVertex3f(-halfCubeSize, -halfCubeSize, -halfCubeSize);

        // Face lateral esquerda
        gl.glTexCoord2f(0, 0); gl.glVertex3f(-halfCubeSize, -halfCubeSize, halfCubeSize);
        gl.glTexCoord2f(1, 0); gl.glVertex3f(-halfCubeSize, halfCubeSize, halfCubeSize);
        gl.glTexCoord2f(1, 1); gl.glVertex3f(-halfCubeSize, halfCubeSize, -halfCubeSize);
        gl.glTexCoord2f(0, 1); gl.glVertex3f(-halfCubeSize, -halfCubeSize, -halfCubeSize);

        // Face lateral direita
        gl.glTexCoord2f(0, 0); gl.glVertex3f(halfCubeSize, -halfCubeSize, halfCubeSize);
        gl.glTexCoord2f(1, 0); gl.glVertex3f(halfCubeSize, halfCubeSize, halfCubeSize);
        gl.glTexCoord2f(1, 1); gl.glVertex3f(halfCubeSize, halfCubeSize, -halfCubeSize);
        gl.glTexCoord2f(0, 1); gl.glVertex3f(halfCubeSize, -halfCubeSize, -halfCubeSize);

        gl.glEnd();  // Fim do desenho do cubo

        // Desativar a textura
        if (texture != null) {
            texture.disable(gl);
        }

        gl.glPopMatrix();
    }

    // Retorna o tamanho do cubo
    public float getCubeSize() {
        return cubeSize;
    }
}
