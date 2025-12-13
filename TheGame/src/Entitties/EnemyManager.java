package Entitties;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import GameStates.Playing;
import Utils.LoadSave;


import static Utils.Constans.EnemyConstants.*;

public class EnemyManager {

    private BufferedImage[][] Enemy1Arr;
     private BufferedImage[][] Enemy2Arr;

    private ArrayList<Enemy1> Enemy1s = new ArrayList<>();
    private ArrayList<Enemy2> Enemy2s = new ArrayList<>();

    public EnemyManager(Playing playing) {

        LoadEnemy();

        AddEnemies();

    }
    private void AddEnemies() {
        Enemy1s = LoadSave.getEnemySprite();
        Enemy2s = LoadSave.GetEnemy2s();

    }
    public void update(int[][] levelData,Player player) {

        for (Enemy1 c : Enemy1s)
            if (c.isActive())
            c.Update(levelData, player);

    }

    public void draw(Graphics g, int xLvlOffset) {
        drawEnemy1(g, xLvlOffset);
        drawEnemy2(g, xLvlOffset);
        
        
    }

    private void drawEnemy1(Graphics g, int xLvlOffset) {

       for (Enemy1 c : Enemy1s) {
        
        
        if (c.isActive()) { 
            int flipVal = c.getFlipX();
            int drawX = (int) c.getHitbox().x - xLvlOffset;
            int drawW = Enemy1_width * flipVal;

            
            if (flipVal == -1) { 
                drawX += Enemy1_width;
            }
            g.drawImage(Enemy1Arr[c.getEnemyState()][c.getAnimationIndex()], drawX,
                    (int) c.getHitbox().y, drawW, Enemy1_height, null);

            
            c.DrawAttackBox(g, xLvlOffset);
            
        }
    }
}
      private void drawEnemy2(Graphics g, int xLvlOffset) { 
    for (Enemy2 e : Enemy2s) {
        if (e.isActive()) { 
            int flipVal = e.getFlipX();
            int drawX = (int) e.getHitbox().x - xLvlOffset;
            int drawW = Enemy2_width * flipVal;

            if (flipVal == -1) { 
                drawX += Enemy2_width;
            }
            g.drawImage(Enemy2Arr[e.getEnemyState()][e.getAnimationIndex()], drawX,
                            (int) e.getHitbox().y, drawW, Enemy2_height, null);

            e.DrawAttackBox(g, xLvlOffset);
        }
    }
}

    public void LoadEnemy() {

        Enemy1Arr = new BufferedImage[5][4];

        BufferedImage temp = Utils.LoadSave.getSpriteAtlas(LoadSave.ENEMY_1);

        for (int j = 0; j < Enemy1Arr.length; j++) {
            for (int i = 0; i < Enemy1Arr[j].length; i++) {

                Enemy1Arr[j][i] = temp.getSubimage(i * Enemy1_width_default, j * Enemy1_height_default,
                        Enemy1_width_default, Enemy1_height_default);
        }
        Enemy2Arr = new BufferedImage[5][4];
        BufferedImage temp2 = Utils.LoadSave.getSpriteAtlas(LoadSave.ENEMY_1);

        for (int a = 0; j < Enemy1Arr.length; j++) {
            for (int i = 0; i < Enemy1Arr[a].length; i++) {

                Enemy1Arr[j][i] = temp2.getSubimage(i * Enemy1_width_default, j * Enemy1_height_default,
                        Enemy1_width_default, Enemy1_height_default);
            
                        
            
                    }        }
                }
        

    }
    public void checkEnemyHit(Rectangle2D.Float attackBox) {
        for(Enemy1 e: Enemy1s) 
            if (attackBox.intersects(e.getHitbox())) {
                e.Hurt(10);
                return;
            }
        for(Enemy2 e: Enemy2s) 
        if (attackBox.intersects(e.getHitbox())) {
            e.Hurt(10);
            return;
        }

    
    }
    public boolean checkProjectileHit(Rectangle2D.Float hitbox) {
 
    for (Enemy1 e : Enemy1s) { 

        if (e.isActive()) { 
            
           
            if (hitbox.intersects(e.getHitbox())) {
                e.Hurt(10); 
                
                return true; 
            }
        }
        
    }
    for (Enemy2 e : Enemy2s) {
        if (e.isActive() && hitbox.intersects(e.getHitbox())) {
            e.Hurt(10);
            return true;
    
}
}
return false;
    } 
}
