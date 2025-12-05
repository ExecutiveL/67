package Utils;

import Levels.level;

public class Checker {

    public static boolean PositionVerification(float x, float y, float width, float height, int[][]leveldata) {

        if(!tileBlock(x,y, leveldata))
            if (!tileBlock( x+ width, y + height, leveldata))
                if (!tileBlock(x + width, y, leveldata))
                    if (!tileBlock(x, y + height, leveldata))
                        return true;
        return false;
    }
    private static boolean tileBlock(float x, float y, int[][]leveldata) {
        if (x < 0 || x >= DisplayManager.GAME_WIDTH)
            return true;
        if (y < 0 || y >= DisplayManager.GAME_HEIGHT)
            return true;

        float xIndex = x/ DisplayManager.TILES_SIZE;
        float YIndex = y/DisplayManager.TILES_SIZE;

        int value = leveldata[(int)YIndex][(int)xIndex];

        if (value >= 60 || value < 0 || value != 48) {
            return true;
        } else {
            return false;
        }

    }
}
