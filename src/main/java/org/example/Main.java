package org.example;

import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        // Configura o perfil do OpenGL (compatível com JOGL)
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);

        // Cria o canvas do JOGL
        GLCanvas canvas = new GLCanvas(capabilities);
        Game game = new Game();
        canvas.addGLEventListener(game);
        canvas.setSize(800, 600);

        // Configura o JFrame (janela) para exibir o jogo
        JFrame frame = new JFrame("Corrida Procedural");
        frame.getContentPane().add(canvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Configura o animador de FPS para renderizar constantemente
        FPSAnimator animator = new FPSAnimator(canvas, 60);
        animator.start();
    }
}
