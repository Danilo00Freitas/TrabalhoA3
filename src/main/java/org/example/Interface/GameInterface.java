package org.example.Interface;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.FPSAnimator;
import org.example.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.jogamp.opengl.awt.GLCanvas;

public class GameInterface extends JFrame {

    private Game game;
    private GLCanvas canvas;
    private FPSAnimator animator;
    private enum GameState { MENU, PLAYING, GAME_OVER }
    private GameState currentState = GameState.MENU;

    public GameInterface() {
        setTitle("Meu Jogo");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        game = new Game();
        showMenu();

        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        canvas = new GLCanvas(capabilities);

        canvas.addGLEventListener(game);
        game.initControls(canvas); // Inicializa os controles após a criação do canvas

        getContentPane().add(canvas, BorderLayout.CENTER);
    }

    public void showMenu() {
        currentState = GameState.MENU;
        stopGame();
        getContentPane().removeAll();

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        JButton startButton = new JButton("Iniciar Jogo");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        JButton exitButton = new JButton("Sair");
        exitButton.addActionListener(e -> System.exit(0));

        menuPanel.add(startButton);
        menuPanel.add(exitButton);

        getContentPane().add(menuPanel);
        revalidate();
        repaint();
    }

    private void startGame() {
        currentState = GameState.PLAYING;
        getContentPane().removeAll();

        GLProfile glProfile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(glProfile);
        canvas = new GLCanvas(capabilities);

        game = new Game();
        canvas.addGLEventListener(game);
        game.initControls(canvas);

        getContentPane().add(canvas, BorderLayout.CENTER);
        revalidate();
        repaint();

        animator = new FPSAnimator(canvas, 60);
        animator.start();
    }

    private void stopGame() {
        if (animator != null && animator.isAnimating()) {
            animator.stop();
        }
        if (canvas != null) {
            canvas.removeGLEventListener(game);
            canvas = null;
            game = null;
        }
    }

    private void showGameOver() {
        currentState = GameState.GAME_OVER;
        stopGame();
        getContentPane().removeAll();

        JPanel gameOverPanel = new JPanel();
        gameOverPanel.setLayout(new BoxLayout(gameOverPanel, BoxLayout.Y_AXIS));

        JLabel gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton retryButton = new JButton("Reiniciar Jogo");
        retryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        retryButton.addActionListener(e -> showMenu());

        JButton exitButton = new JButton("Sair");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(e -> System.exit(0));

        gameOverPanel.add(gameOverLabel);
        gameOverPanel.add(retryButton);
        gameOverPanel.add(exitButton);

        getContentPane().add(gameOverPanel);
        revalidate();
        repaint();
    }
}
