package github.rafael.entity;

import github.rafael.events.ColisionChecker;
import github.rafael.events.KeyEventCapture;
import github.rafael.windows.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.Objects;

public class Dino extends Entity{
    GamePanel gamePanel;
    KeyEventCapture keyEventCapture;
    ColisionChecker colisionChecker;

    //-> Controle de Sprites
    private int controlSprite = 0;
    private int controlFrequencyUpdate;

    //-> Configurações de pulo;
    int jumpMax;
    boolean jump;
    boolean statusJump;


    public Dino (GamePanel gamePanel, KeyEventCapture keyEventCapture, ColisionChecker colisionChecker){
        this.gamePanel =gamePanel;
        this.keyEventCapture = keyEventCapture;
        this.colisionChecker = colisionChecker;

        spritesDino();
        yDefault = (gamePanel.sizeScreenRowScale-gamePanel.getTileSize()*2)-(gamePanel.getTileSize()/6);
        x = gamePanel.getTileSize()+(gamePanel.getTileSize()/3)+5;

        jumpMax = gamePanel.getTileSize()*2+(gamePanel.getTileSize()/2);

        this.solidArea= new Rectangle();
        this.solidArea.x=48;
        this.solidArea.y=184;
        this.solidArea.width=32;
        this.solidArea.height=32;
    }

    private void spritesDino(){
        try{
            images[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/personagens/dino/dino_1.png")));
            images[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/personagens/dino/dino_3.png")));
            images[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/personagens/dino/dino_4.png")));
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void update(){

        if(keyEventCapture.spaceKey && !jump){
            jump = true;
            statusJump = true;
        }
        controlFrequencyUpdate++;
        if(controlFrequencyUpdate>6){
            controlSprite++;

            if(controlSprite==images.length){
                controlSprite=0;
            }
            controlFrequencyUpdate=0;
        }

        jumpMethod();
        colisionChecker.verifyColision(this);


    }

    private void jumpMethod(){
        if(jump){
            controlSprite=0;
            if(y>=jumpMax || !statusJump){
                y-=speed/2;
                statusJump = false;
            }
            if(y<jumpMax && statusJump){
                y+=speed/2;
            }

            if(y<=0 || keyEventCapture.down){
                y = 0;
                statusJump = true;
                jump=false;
            }
        }

    }



    public void draw(Graphics2D g2){
        g2.drawImage(images[controlSprite], x ,yDefault-y, gamePanel.getTileSize(),gamePanel.getTileSize(), null);
    }
}
