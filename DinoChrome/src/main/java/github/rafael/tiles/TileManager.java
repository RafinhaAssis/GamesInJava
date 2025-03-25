package github.rafael.tiles;

import github.rafael.configGame.Configurations;
import github.rafael.windows.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tiles;
    private int[][] mapTile;
    int[][] auxiliaryMap;
    int taxUpdate;

    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        tiles = new Tile[7];
        mapTile = new int[gamePanel.getSizeScreenCol()][gamePanel.getSizeScreenRows()];
        auxiliaryMap = new int[gamePanel.getSizeScreenCol()][gamePanel.getSizeScreenRows()];
        startTales();
        loadMap();
    }

    void startTales(){
        try{
            tiles[0] = new Tile();
            tiles[0].images = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sprite_0.png")));
            tiles[1] = new Tile();
            tiles[1].images = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sprite_1.png")));
            tiles[2] = new Tile();
            tiles[2].images = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sprite_2.png")));
            tiles[3] = new Tile();
            tiles[3].images = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sprite_3.png")));
            tiles[4] = new Tile();
            tiles[4].images = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/sprite_4.png")));

            tiles[5] = new Tile();
            tiles[5].images = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/personagens/enemy/cacto_1.png")));
            tiles[5].colision = true;

            tiles[6] = new Tile();
            tiles[6].images = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/personagens/enemy/cacto_3.png")));
            tiles[6].colision = true;

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    void loadMap(){
        try{
            InputStream is = getClass().getResourceAsStream("/map/map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            int row = 0;

            while ((line = br.readLine()) != null) {
                String[] linePosition = line.split(" ");
                for(int i=0; i<gamePanel.getSizeScreenCol(); i++){
                    mapTile[i][row] = Integer.parseInt(linePosition[i]);
                }
                row++;
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void auxiliaryMapRenderization(){
        taxUpdate++;
        if(taxUpdate==(Configurations.speed*2)){
            for(int l=0; l<gamePanel.getSizeScreenRows(); l++) {
                for (int c = 1; c < gamePanel.getSizeScreenCol(); c++) {
                    auxiliaryMap[c-1][l] = mapTile[c][l];
                }
                auxiliaryMap[gamePanel.getSizeScreenCol()-1][l] = mapTile[0][l];
            }
            mapTile = auxiliaryMap;
            taxUpdate = 0;
        }
    }

    public void draw(Graphics2D g2){
        for(int l=0; l<gamePanel.getSizeScreenRows(); l++){
            for(int c=0; c<gamePanel.getSizeScreenCol(); c++){
                g2.drawImage(tiles[mapTile[c][l]].images,
                        c*gamePanel.getTileSize(),
                        l*gamePanel.getTileSize(),
                        gamePanel.getTileSize(),
                        gamePanel.getTileSize(), null
                        );
            }
        }
        auxiliaryMapRenderization();
    }


    // Modificadores de acesso:
    public void setTiles(int value, int c, int l){
        mapTile[c][l] = value;
    }

    public int getTiles( int c, int l){
        return mapTile[c][l] ;
    }

    public int getTilesAuxiliary( int c, int l){
        return auxiliaryMap[c][l] ;
    }


    public void setTilesAuxiliary(int value, int c, int l) {
        auxiliaryMap[c][l] = value;
    }

    public boolean getMapTile(int c, int l){
        return tiles[mapTile[c][l]].colision;
    }
}
