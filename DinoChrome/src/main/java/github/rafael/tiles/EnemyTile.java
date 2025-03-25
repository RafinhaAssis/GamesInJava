package github.rafael.tiles;

import github.rafael.windows.GamePanel;

import java.util.Random;

public class EnemyTile {
    GamePanel gamePanel;
    private int maxEnemy = 2;
    private int enemyInMap = 0;
    private Random random = new Random();

    public EnemyTile(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void updateEnemy(){
        desableEnemy();

        if(enemyInMap<maxEnemy){
            int code = gamePanel.getTileManager().getTiles(gamePanel.getSizeScreenCol()-2,4);
            if(code<5){
                int enemyCode = random.nextInt(5,7);
                this.gamePanel.getTileManager().setTiles(enemyCode, gamePanel.getSizeScreenCol()-2, 4);
                enemyInMap++;
            }

        }
    }

    private void desableEnemy(){
        if(enemyInMap==maxEnemy){
            for(int l=0; l<gamePanel.getSizeScreenRows(); l++){
                int code = gamePanel.getTileManager().getTilesAuxiliary(gamePanel.getSizeScreenCol()-1, l);
                if(code>=5 && code<=6){
                    gamePanel.getTileManager().setTilesAuxiliary(1,gamePanel.getSizeScreenCol()-1, l);
                    enemyInMap--;
                }
            }
        }

    }
}
