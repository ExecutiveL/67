package Entitties;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import GameStates.Playing;
import Utils.LoadSave;
import static Utils.Constans.EnemyConstants.*;

public class EnemyManager {

    private Playing playing;

    private BufferedImage[][] Enemy1Arr;

    private ArrayList<Enemy1> Enemy1s = new ArrayList<>();

    public EnemyManager(Playing playing) {

        this.playing = playing;

        LoadEnemy();

        AddEnemies();

    }

    private void AddEnemies() {

        Enemy1s = LoadSave.getEnemySprite();

    }

    public void update(int[][] levelData) {

        for (Enemy1 c : Enemy1s)

            c.Update(levelData);

    }

    public void draw(Graphics g, int xLvlOffset) {

        drawEnemy1(g, xLvlOffset);
        

    }

    private void drawEnemy1(Graphics g, int xLvlOffset) {

        for (Enemy1 c : Enemy1s) {
            int flipVal = c.getFlipX();
            int drawX = (int) c.getHitbox().x - xLvlOffset;
            int drawW = Enemy1_width * flipVal;

            if (flipVal == -1) {
            drawX += Enemy1_width;
        }

            g.drawImage(Enemy1Arr[c.getEnemyState()][c.getAnimationIndex()], drawX,
                    (int) c.getHitbox().y, drawW, Enemy1_height, null);

            c.DrawHitbox(g, xLvlOffset);

        }

    }

    public void LoadEnemy() {

        Enemy1Arr = new BufferedImage[5][4];

        BufferedImage temp = Utils.LoadSave.getSpriteAtlas(LoadSave.ENEMY_1);

        for (int j = 0; j < Enemy1Arr.length; j++)

            for (int i = 0; i < Enemy1Arr[j].length; i++) {

                Enemy1Arr[j][i] = temp.getSubimage(i * Enemy1_width_default, j * Enemy1_height_default,
                        Enemy1_width_default, Enemy1_height_default);

            }

    }

}
