package Levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Utils.LoadSave;
import main.Game;



public class levelmaniger {

    private Game game;
    private BufferedImage[] levelSprite;
    private level level;

    // Define how large each tile should be drawn on screen
    private static final int TILE_SIZE = 80;

    public levelmaniger(Game game) {
        this.game = game;
        importOutsideSprite();
        level = new level(LoadSave.GetLevelData());
    }

    private void importOutsideSprite() {
        BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
        System.out.println("Atlas size: " + img.getWidth() + "x" + img.getHeight());

        int tileSize = 16;
        int cols = img.getWidth() / tileSize;   
        int rows = img.getHeight() / tileSize;  

        levelSprite = new BufferedImage[rows * cols];

        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < cols; i++) {
                int index = j * cols + i;
                levelSprite[index] = img.getSubimage(i * tileSize, j * tileSize, tileSize, tileSize);
            }
        }
    }

    public void draw(Graphics g) {
        if (level == null || LoadSave.GetLevelData().length == 0) {
            System.err.println("No level data to draw!");
            return;
        }

        int[][] levelData = LoadSave.GetLevelData();

        for (int j = 0; j < levelData.length; j++) {
            for (int i = 0; i < levelData[0].length; i++) {
                int index = level.getSpriteIndex(i, j);
                if (index >= 0 && index < levelSprite.length) {
                    g.drawImage(levelSprite[index], i * TILE_SIZE, j * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
                }
            }
        }
    }

    public void update() {
    }
}
