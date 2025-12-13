package Entitties;

import static Utils.Constans.EnemyConstants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import Utils.Checker;
import Utils.DisplayManager;

public class Enemy1 extends Enemy {
    
    private Rectangle2D.Float attackBox;
    private float attackBoxOffsetX;
   

    public Enemy1(float x, float y) {

        super(x, y, Enemy1_width, Enemy1_height, ENEMY1);

        Hitbox(x, y, (int) (26 * DisplayManager.SCALE), (int) (18 * DisplayManager.SCALE));
        attackBox();

    }
    public void Update(int[][] levelData, Player player) {
        updateMove(levelData, player);
        updateAnimationTick();
        updateAttackBox();
    }
     private void updateAttackBox() {
        attackBox.x = hitbox.x + attackBoxOffsetX * flipX;
        attackBox.y = hitbox.y;
    }
     public void updateMove(int[][] levelData, Player player) {
        if (firstUpdate) {
          firstUpdateCheck(levelData);
        }
        if (inAir) {
            UpdateInAir(levelData);
        } else {
            switch (enemyState) {
                case IDLE:
                    newState(WALKING);
                    break;
                case WALKING:
                    if (canSeePlayer(levelData, player)) {
                        towardsPlayer(player);
                    }
                    if (InAttackRange(player)) {
                        newState(ATTACK);
                    }
                    Move(levelData);
                    break;
                case ATTACK:
                    if (animationIndex == 0)
                        AttackChecked = false;
                    if(animationIndex == 2 && !AttackChecked) 
                        checkPlayerHit(attackBox, player);
                    break;
                case HIT:
                    break;
            }

        }

    }
    private void attackBox() {
        attackBox = new Rectangle2D.Float(x,y,(int)(40 * DisplayManager.SCALE),(int)(20* DisplayManager.SCALE));
        attackBoxOffsetX = (int) (2 * DisplayManager.SCALE);
    }
    public void DrawAttackBox(Graphics g, int xLvlOffset) {
        g.setColor(Color.RED);
        g.drawRect((int) attackBox.x - xLvlOffset, (int) attackBox.y, (int) attackBox.width, (int) attackBox.height);
}
}