package Entitties;

import static Utils.Constans.EnemyConstants.*;

import Utils.DisplayManager;

public class Enemy1 extends Enemy {

    public Enemy1(float x, float y) {

        super(x, y, Enemy1_width, Enemy1_height, ENEMY1);

        Hitbox(x, y, (int) (26 * DisplayManager.SCALE), (int) (25 * DisplayManager.SCALE));

    }
    
}
