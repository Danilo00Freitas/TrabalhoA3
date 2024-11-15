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

public class GameInterface {

    private Game game;
    private GLCanvas canvas;
    private FPSAnimator animator;
    private enum GameState { MENU, PLAYING, GAME_OVER }
    private GameState currentState = GameState.MENU;

    private JFrame menuFrame;
    private JFrame gameFrame;
    private JFrame gameOverFrame;

    public GameInterface() {
        // Inicialização do JOGL (apenas uma vez)
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        canvas = new GLCanvas(capabilities);

        game = new Game();
        canvas.addGLEventListener(game);

        // Inicializar os controles do jogo
        game.initControls(canvas);

        // Criar as janelas
        createMenuWindow();
        createGameWindow();
        createGameOverWindow();

        showMenu(); // Exibir o menu na inicialização
    }

    private void createMenuWindow() {
        menuFrame = new JFrame("Menu");
        menuFrame.setSize(400, 300);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLocationRelativeTo(null);

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

        menuFrame.getContentPane().add(menuPanel);
    }

    private void createGameWindow() {
        gameFrame = new JFrame("Jogo");
        gameFrame.setSize(800, 600);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.getContentPane().add(canvas, BorderLayout.CENTER);
    }

    private void createGameOverWindow() {
        gameOverFrame = new JFrame("Game Over");
        gameOverFrame.setSize(400, 300);
        gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameOverFrame.setLocationRelativeTo(null);

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

        gameOverFrame.getContentPane().add(gameOverPanel);
    }

    public void showMenu() {
        currentState = GameState.MENU;
        stopGame();

        // Esconder a janela do jogo e do Game Over
        gameFrame.setVisible(false);
        gameOverFrame.setVisible(false);

        // Exibir o menu
        menuFrame.setVisible(true);
    }

    private void startGame() {
        currentState = GameState.PLAYING;
        menuFrame.setVisible(false); // Esconder o menu

        // Exibir a janela do jogo
        gameFrame.setVisible(true);

        // Aguardar o canvas estar pronto e começar a animação
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (animator == null || !animator.isAnimating()) {
                    animator = new FPSAnimator(canvas, 60);
                    animator.start();
                }
            }
        });
    }

    private void stopGame() {
        if (animator != null && animator.isAnimating()) {
            animator.stop();
        }
    }

    public void showGameOver() {
        currentState = GameState.GAME_OVER;
        stopGame();

        // Esconder a janela do jogo
        gameFrame.setVisible(false);

        // Exibir a janela de Game Over
        gameOverFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameInterface();
            }
        });
    }
}
