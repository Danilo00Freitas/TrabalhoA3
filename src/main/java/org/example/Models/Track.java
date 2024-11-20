package org.example.Models;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;

public class Track {
    private float positionZ = 5;
    private Texture trackTexture;

    // Construtor para aceitar a textura
    public Track(Texture trackTexture) {
        this.trackTexture = trackTexture;
    }

    public void draw(GL2 gl) {
        gl.glPushMatrix();
        // Move a pista no eixo Z
        gl.glTranslatef(0, -1, positionZ);

        // Ativar e vincular a textura
        if (trackTexture != null) {
            trackTexture.enable(gl);
            trackTexture.bind(gl);
        }

        // Definir cor branca para utilizar as cores da textura
        gl.glColor3f(1, 1, 1);

        // Desenhar os quadros da pista
        gl.glBegin(GL2.GL_QUADS);

        // Coordenadas de textura e vértices
        gl.glTexCoord2f(0, 0); gl.glVertex3f(-5, 0, 0);
        gl.glTexCoord2f(1, 0); gl.glVertex3f(5, 0, 0);
        gl.glTexCoord2f(1, 1); gl.glVertex3f(5, 0, -100);
        gl.glTexCoord2f(0, 1); gl.glVertex3f(-5, 0, -100);

        gl.glEnd();

        // Desativar a textura
        if (trackTexture != null) {
            trackTexture.disable(gl);
        }

        gl.glPopMatrix();
    }

    public void move() {
        // Velocidade do movimento da pista
        positionZ += 0.1f;
        if (positionZ > 10) {
            // Reinicia a posição da pista
            positionZ = 5;
        }
    }
}
