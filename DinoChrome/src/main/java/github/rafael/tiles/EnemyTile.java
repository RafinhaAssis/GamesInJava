package github.rafael.tiles;

import github.rafael.windows.GamePanel;

import java.util.Random;

public class EnemyTile {
    GamePanel gamePanel;
    private int maxEnemy = 3;
    private int enemyInMap = 0;
    private Random random = new Random();
    private int spaceReal = 0;

    public EnemyTile(GamePanel gamePanel){
        this.gamePanel = gamePanel;

    }

    public void updateEnemy(){
        desableEnemy();

        if(enemyInMap<maxEnemy){
            spaceReal++;

            int code = gamePanel.getTileManager().getTiles(this.gamePanel.getSizeScreenCol()-2,4);
            if(code<5 && spaceReal>random.nextInt(160,220)){
                int enemyCode = random.nextInt(5,7);
                this.gamePanel.getTileManager().setTiles(enemyCode, gamePanel.getSizeScreenCol()-2, 4);
                enemyInMap++;
                spaceReal=0;
            }
            spaceReal++;
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

    public void setEnemyInMap(int enemyInMap) {
        this.enemyInMap = enemyInMap;
    }
}
