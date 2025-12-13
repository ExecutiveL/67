package Managers;

import java.util.ArrayList;

import GameStates.Playing;
import Utils.LoadSave;
import Utils.Projectile;
import Utils.Checker;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.geom.Rectangle2D;


public class ProjectileManager {
    private Playing playing;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private BufferedImage bulletImg;

    public ProjectileManager(Playing playing) {
        this.playing = playing;
        
        bulletImg = LoadSave.getSpriteAtlas(LoadSave.BULLET);
    }


    public void newProjectile(int x, int y, int dir) {
      
        projectiles.add(new Projectile(x, y, dir));
    }

 
    public void update() {
        for (Projectile p : projectiles) {
        if (p.isActive()) {
            p.updatePos();
            Rectangle2D.Float hitbox = p.getHitbox();
            
           
            if (playing.getEnemyManager().checkProjectileHit(hitbox)) { 
                p.setActive(false);
                continue;
            }
            
           
            if (Checker.IsProjectileHittingWall(hitbox, 
                    playing.getLevelmaniger().getCurrentLevel().getLevelData())) { 
                p.setActive(false); 
                continue; 
            }
            
           
            if (hitbox.x < 0 || hitbox.x > playing.getGame().getGameWidth() + 50) { 
                p.setActive(false);
            }
        
        } 
        
    } 

   
    projectiles.removeIf(p -> !p.isActive());
    
} 
  
public void draw(Graphics g, int xLvlOffset) {
    for (Projectile p : projectiles) {
        if (p.isActive()) {
            Rectangle2D.Float hitbox = p.getHitbox();
            g.drawImage(bulletImg, (int) hitbox.x - xLvlOffset, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height, null);
            
            
            g.setColor(Color.GREEN); 
            
         
            g.drawRect(
                (int) hitbox.x - xLvlOffset,  
                (int) hitbox.y,               
                (int) hitbox.width,
                (int) hitbox.height
            );
        }
    }
}
}