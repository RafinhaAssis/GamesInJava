package github.rafael.windows.components;

import github.rafael.windows.GamePanel;

import java.awt.*;

public class Score {
    GamePanel gamePanel;

    public Score(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void draw(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        g2.setPaint(new Color(58, 57, 57));
        g2.drawString(String.valueOf(gamePanel.getDino().getScore()), gamePanel.sizeScreenColScale-70, 70);
    }
}
