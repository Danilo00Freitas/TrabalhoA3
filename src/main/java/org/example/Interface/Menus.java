package org.example.Interface;

import org.example.System.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Menus {

    private JFrame menuFrame;
    private JFrame gameOverFrame;
    private Game game;
    private JLabel scoreLabel;

    public Menus(ActionListener startGameListener, ActionListener exitListener, ActionListener retryListener) {
        createMenuWindow(startGameListener, exitListener);
        createGameOverWindow(retryListener, exitListener);
    }

    public void setGame(Game game) {
        this.game = game;
    }

    private void createMenuWindow(ActionListener startGameListener, ActionListener exitListener) {
        menuFrame = new JFrame("Menu");
        menuFrame.setSize(400, 300);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Centraliza a janela após definir o tamanho
        menuFrame.setLocationRelativeTo(null);

        JPanel menuPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre elementos

        // Título do menu
        JLabel titleLabel = new JLabel("Menu Principal");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        menuPanel.add(titleLabel, gbc);

        // Botão Iniciar Jogo
        JButton startButton = new JButton("Iniciar Jogo");
        startButton.addActionListener(startGameListener);
        gbc.gridy = 1;
        menuPanel.add(startButton, gbc);

        // Botão Sair
        JButton exitButton = new JButton("Sair");
        exitButton.addActionListener(exitListener);
        gbc.gridy = 2;
        menuPanel.add(exitButton, gbc);

        menuFrame.getContentPane().add(menuPanel);
    }

    private void createGameOverWindow(ActionListener retryListener, ActionListener exitListener) {
        gameOverFrame = new JFrame("Game Over");
        gameOverFrame.setSize(400, 300);
        gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Centraliza a janela após definir o tamanho
        gameOverFrame.setLocationRelativeTo(null);

        JPanel gameOverPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre elementos

        // Título de Game Over
        JLabel gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gameOverPanel.add(gameOverLabel, gbc);

        // Rótulo de pontuação
        scoreLabel = new JLabel("Pontuação: 0");
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridy = 1;
        gameOverPanel.add(scoreLabel, gbc);

        // Botão Reiniciar Jogo
        JButton retryButton = new JButton("Reiniciar Jogo");
        retryButton.addActionListener(e -> {
            if (game != null) {
                try {
                    game.resetGame();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            retryListener.actionPerformed(e);
        });
        gbc.gridy = 2;
        gameOverPanel.add(retryButton, gbc);

        // Botão Sair
        JButton exitButton = new JButton("Sair");
        exitButton.addActionListener(exitListener);
        gbc.gridy = 3;
        gameOverPanel.add(exitButton, gbc);

        gameOverFrame.getContentPane().add(gameOverPanel);
    }

    public void showMenu() {
        menuFrame.setVisible(true);
        gameOverFrame.setVisible(false);
    }

    public void showGameOver(int score) {
        menuFrame.setVisible(false);
        scoreLabel.setText("Pontuação: " + score);
        gameOverFrame.setVisible(true);
    }

    public void hideAll() {
        menuFrame.setVisible(false);
        gameOverFrame.setVisible(false);
    }
}
