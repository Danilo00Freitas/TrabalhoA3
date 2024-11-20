package org.example.Models;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;

public class Background {
    private final Texture texture;

    public Background(Texture texture) {
        this.texture = texture;
    }

    public void draw(GL2 gl) {
        if (texture != null) {
            texture.enable(gl);
            texture.bind(gl);
        }

        gl.glPushMatrix();
        gl.glColor3f(1, 1, 1); // Branco para aplicar a textura corretamente

        gl.glBegin(GL2.GL_QUADS);

        // Plano inclinado
        gl.glTexCoord2f(0, 0); gl.glVertex3f(-60, -50, -60); // Base esquerda elevada
        gl.glTexCoord2f(1, 0); gl.glVertex3f(60, -50, -60);  // Base direita elevada
        gl.glTexCoord2f(1, 1); gl.glVertex3f(60, 70, -100);  // Topo direito elevado
        gl.glTexCoord2f(0, 1); gl.glVertex3f(-60, 70, -100);




        gl.glEnd();

        if (texture != null) {
            texture.disable(gl);
        }

        gl.glPopMatrix();
    }
}
