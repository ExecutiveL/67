package Levels;

import java.awt.image.BufferedImage;

import Utils.DisplayManager;



public class level {
    
    private BufferedImage image;
    private int[][] levelData;

     private int LvlTilesWide;
    private int MaxTilesOffset;
    private int MaxLvlOffsetX;

    public level(BufferedImage image) {
        this.image = image;
        createLevelData();
        createEnemies();
        calcOffsets();
    }
    private void calcOffsets() {
        LvlTilesWide = image.getWidth();
        MaxTilesOffset = LvlTilesWide - DisplayManager.TILES_IN_WIDTH;
        MaxLvlOffsetX = DisplayManager.TILES_SIZE;
       
    }
       
    private void createEnemies() {
        
        
    }
    private void createLevelData() {
        levelData = Utils.Checker.GetLevelData(image);
       
    }
    public int getSpriteIndex(int x,int y) {
        return levelData[y][x];

    }
    public int[][] getLevelData() {
    return levelData;
}
    public int getMaxLvlOffsetX() {
        return MaxLvlOffsetX;
    }

}
