package github.rafael.events;

import github.rafael.entity.Entity;
import github.rafael.windows.GamePanel;

public class ColisionChecker {
    GamePanel gamePanel;

    private boolean colisionDino;

    public ColisionChecker(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void verifyColision(Entity entity){
        int entityMatrizPositionX = ((entity.x+ (entity.solidArea.x))/gamePanel.getTileSize());
        int entityMatrizPositionY= (entity.yDefault-entity.y+gamePanel.getTileSize())/ gamePanel.getTileSize();
        System.out.println(entity.x);
        if(gamePanel.getTileManager().getMapTile(entityMatrizPositionX,entityMatrizPositionY)){
            colisionDino = true;
        }
    }

    public boolean isColisionDino() {
        return colisionDino;
    }
}
