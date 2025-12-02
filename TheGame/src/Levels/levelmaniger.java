package Levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Utils.LoadSave;
import main.Game;

public class levelmaniger {

    private Game game;
    private BufferedImage[] levelSprite;
    private level level;

    public levelmaniger(Game game) {
        this.game = game;
        //levelSprite = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
        importOutsideSprite();
        level = new level(LoadSave.GetLevelData());
 
    }
    private void importOutsideSprite() {
        BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[54];
        System.out.println("Atlas size: " + img.getWidth() + "x" + img.getHeight());

        for (int  j = 0; j < 9; j++) 
            for (int i=0; i < 6; i++) {
                int index = j * 6 +i;
                levelSprite[index] = img.getSubimage(i * 16, j * 16, 16, 16);

            }
        }
    public void draw(Graphics g) {
        for (int j = 0; )
       g.drawImage(levelSprite[0], 300, 300,100,100, null);
    }
    public void update() {
        
    }

}
