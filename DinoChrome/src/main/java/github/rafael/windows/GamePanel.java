package github.rafael.windows;

import github.rafael.entity.Dino;
import github.rafael.events.ColisionChecker;
import github.rafael.events.KeyEventCapture;
import github.rafael.tiles.EnemyTile;
import github.rafael.tiles.TileManager;
import github.rafael.windows.components.GameOver;


import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int FPS= 60;
    int size = 16;
    int sizeScreenCol = 16;
    int sizeScreenRows = 6;
    int scaleSize = 3;

    private final int tileSize = size * scaleSize;

    int sizeScreenColScale = (size*scaleSize)*sizeScreenCol;
    public int sizeScreenRowScale = (size*scaleSize)*sizeScreenRows;

    KeyEventCapture keyEventCapture = new KeyEventCapture();
    Dino dino;
    private final TileManager tileManager;
    EnemyTile enemyTile;

    private boolean runtimeGame;

    ColisionChecker colisionChecker = new ColisionChecker(this);
    GameOver over = new GameOver(this);
    Thread initGame;

    public GamePanel(){
        this.dino = new Dino(this, keyEventCapture, colisionChecker);
        this.tileManager = new TileManager(this);
        this.enemyTile = new EnemyTile(this);

        this.setPreferredSize(new Dimension(sizeScreenColScale, sizeScreenRowScale));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addKeyListener(keyEventCapture);
    }

    public void startGame(){
        initGame = new Thread(this);
        initGame.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(initGame!=null){
                currentTime = System.nanoTime();
                delta += (currentTime-lastTime)/drawInterval;
                lastTime = currentTime;
                if(delta >=1 ){
                    runtimeGame = colisionChecker.isColisionDino();
                    if(!runtimeGame){
                        update();
                    }else{
                        if(keyEventCapture.spaceKey){
                            colisionChecker.setColisionDino(false);
                            tileManager.loadMap();
                            tileManager.setMoving(false);
                            enemyTile.setEnemyInMap(0);
                        }
                        else{
                            tileManager.setMoving(true);
                        }

                    }
                    repaint();

                    exit();
                    delta--;
                }
        }
    }

    private void update(){
        dino.update();
        enemyTile.updateEnemy();
    }

    private void exit(){
        if(keyEventCapture.escKey){
            System.exit(0);
        }
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        dino.draw(g2);
        if(runtimeGame){
            over.draw(g2);
        }
        g2.dispose();
    }


    public int getTileSize() {
        return tileSize;
    }

    public int getSizeScreenCol() {
        return sizeScreenCol;
    }

    public int getSizeScreenRows() {
        return sizeScreenRows;
    }

    public TileManager getTileManager() {
        return tileManager;
    }
}
