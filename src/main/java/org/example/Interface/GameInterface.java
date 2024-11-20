package org.example.Interface;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import org.example.System.Game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameInterface {

    private Game game;
    private final GLCanvas canvas;
    private FPSAnimator animator;
    private final Menus menus;
    private GameState currentState = GameState.MENU;
    private JFrame gameFrame;

    public GameInterface() throws IOException {
        // Configurar OpenGL (JOGL)
        GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        canvas = new GLCanvas(capabilities);

        game = new Game();
        game.setGameInterface(this);
        canvas.addGLEventListener(game);
        game.initControls(canvas);

        // Criar janelas
        createGameWindow();

        // Criar menus e gerenciar eventos
        menus = new Menus(
                e -> startGame(),       // Listener para iniciar o jogo
                e -> System.exit(0),    // Listener para sair do jogo
                e -> {
                    try {
                        restartGame();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }         // Listener para reiniciar após Game Over
        );
        menus.setGame(game);
        showMenu();
    }

    private void createGameWindow() {
        gameFrame = new JFrame("Jogo");
        gameFrame.setSize(800, 600);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.getContentPane().add(canvas, BorderLayout.CENTER);
    }

    public void showMenu() {
        currentState = GameState.MENU;
        stopGame();
        menus.showMenu();
        gameFrame.setVisible(false);
    }

    private void startGame() {
        game.setGameState(Game.gameState.INGAME);
        menus.hideAll();
        gameFrame.setVisible(true);

        if (animator == null) {
            animator = new FPSAnimator(canvas, 60);
            animator.start();
        } else if (!animator.isAnimating()) {
            animator.start();
        }
    }

    public void showGameOver() {
        currentState = GameState.GAME_OVER;
        stopGame();
        menus.showGameOver(game.getScore());
        gameFrame.setVisible(false);
    }

    private void stopGame() {
        if (animator != null && animator.isAnimating()) {
            animator.stop();
        }
    }

    private void restartGame() throws IOException {
        // Finaliza o estado atual do jogo
        stopGame();

        // Remove os listeners para evitar conflitos
        canvas.removeGLEventListener(game);

        // Cria uma nova instância do jogo
        game = new Game();
        game.setGameInterface(this);
        canvas.addGLEventListener(game);
        game.initControls(canvas);

        // Reinicia o jogo
        startGame();
    }

    private enum GameState {MENU, PLAYING, GAME_OVER}
}

