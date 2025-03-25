package github.rafael.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int y, yDefault;
    public boolean colision = false;
    public int speed = 6;

    public BufferedImage[] images = new BufferedImage[3];
    public int x;
    public Rectangle solidArea;
}
