package Levels;

public class level {

    private int[][] levelData;

    public level(int[][] levelData) {
        this.levelData = levelData;
    }
    public int getSpriteIndex(int x,int y) {
        return levelData[y][x];

    }
    public int[][] getLevelData() {
    return levelData;
}
}
