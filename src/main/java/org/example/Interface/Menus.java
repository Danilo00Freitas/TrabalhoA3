package org.example.Interface;

import org.example.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Menus {

    private JFrame menuFrame;
    private JFrame gameOverFrame;
    private Game game;

    public Menus(ActionListener startGameListener, ActionListener exitListener, ActionListener retryListener) {
        createMenuWindow(startGameListener, exitListener);
        createGameOverWindow(retryListener, exitListener);
    }
    public void setGame(Game game){
        this.game = game;
    }

    private void createMenuWindow(ActionListener startGameListener, ActionListener exitListener) {
        menuFrame = new JFrame("Menu");
        menuFrame.setSize(400, 300);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLocationRelativeTo(null);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        JButton startButton = new JButton("Iniciar Jogo");
        startButton.addActionListener(startGameListener);

        JButton exitButton = new JButton("Sair");
        exitButton.addActionListener(exitListener);

        menuPanel.add(startButton);
        menuPanel.add(exitButton);

        menuFrame.getContentPane().add(menuPanel);
    }

    private void createGameOverWindow(ActionListener retryListener, ActionListener exitListener) {
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

        // Ajuste o comportamento do botão para reiniciar o jogo
        retryButton.addActionListener(e -> {
            game.resetGame();
            retryListener.actionPerformed(e);
        });

        JButton exitButton = new JButton("Sair");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(exitListener);

        gameOverPanel.add(gameOverLabel);
        gameOverPanel.add(retryButton);
        gameOverPanel.add(exitButton);

        gameOverFrame.getContentPane().add(gameOverPanel);
    }

    public void showMenu() {
        menuFrame.setVisible(true);
        gameOverFrame.setVisible(false);
    }

    public void showGameOver() {
        menuFrame.setVisible(false);
        gameOverFrame.setVisible(true);
    }

    public void hideAll() {
        menuFrame.setVisible(false);
        gameOverFrame.setVisible(false);
    }
}
