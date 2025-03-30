package github.rafael.windows.components;

import github.rafael.windows.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class GameOver{
    GamePanel gamePanel;
    BufferedImage image;

    public GameOver(GamePanel gamePanel){
        this.gamePanel =gamePanel;
        try {
            this.image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/config/gameOver.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void draw(Graphics2D g2) {
        int widthComponent = gamePanel.getTileSize()*3;
        int heigthComponent = gamePanel.getTileSize();
        int positionX= (gamePanel.sizeScreenColScale/2) - (widthComponent/2);
        int positionY = (gamePanel.sizeScreenRowScale/2) - (heigthComponent/2);
        g2.drawImage(image, positionX, positionY, widthComponent, heigthComponent, null);

    }
}
