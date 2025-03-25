package github.rafael;

import github.rafael.windows.GamePanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame windows = new JFrame();
        windows.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        windows.setResizable(false);
        GamePanel gamePanel  = new GamePanel();
        windows.setUndecorated(true);

        windows.add(gamePanel);


        windows.pack();
        windows.setLocationRelativeTo(null);
        windows.setVisible(true);

        gamePanel.startGame();
    }
}