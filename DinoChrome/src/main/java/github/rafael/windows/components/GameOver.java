package github.rafael.windows.components;

import github.rafael.windows.GamePanel;

import java.awt.*;

public class GameOver{
    GamePanel gamePanel;

    public GameOver(GamePanel gamePanel){
        this.gamePanel =gamePanel;
    }


    public void draw(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        g2.setPaint(Color.BLACK);
        g2.drawString("Game Over", 50, 100);
    }
}
