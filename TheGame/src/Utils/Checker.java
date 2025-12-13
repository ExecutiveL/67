package Utils;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;



public class Checker {

    public static boolean PositionVerification(float x, float y, float width, float height, int[][] leveldata) {

        if (!tileBlock(x, y, leveldata))

            if (!tileBlock(x + width, y + height, leveldata))

                if (!tileBlock(x + width, y, leveldata))

                    if (!tileBlock(x, y + height, leveldata))

                        return true;

        return false;

    }

    private static boolean tileBlock(float x, float y, int[][] leveldata) {

        int maxWidth = leveldata[0].length * DisplayManager.TILES_SIZE;

        if (x < 0 || x >= maxWidth)

            return true;

        if (y < 0 || y >= DisplayManager.GAME_HEIGHT)

            return true;

        float xIndex = x / DisplayManager.TILES_SIZE;
        float YIndex = y / DisplayManager.TILES_SIZE;

        return isTileBlock((int) xIndex, (int) YIndex, leveldata);

    }
    private static boolean isTileBlock (int Xtile, int Ytile, int[][] leveldata) {
        int value = leveldata[Ytile][Xtile];

         if (value >= 16 || value < 0 || value != 0) {
            return true;
        } else {
            return false;

        }
    }

    public static float CloserToWall(Rectangle2D.Float hitbox, float Xspeed) {

        int currentTille = (int) (hitbox.x / DisplayManager.TILES_SIZE);

        if (Xspeed > 0) {

            int TileXpos = currentTille * DisplayManager.TILES_SIZE;

            int XoffSet = (int) (DisplayManager.TILES_SIZE - hitbox.width);

            return TileXpos + XoffSet - 1;

        } else {

            return currentTille * DisplayManager.TILES_SIZE;

        }

    }

    public static float FloorAndRoofChecker(Rectangle2D.Float hitbox, float AirSpeed) {

        int currentTille = (int) (hitbox.y / DisplayManager.TILES_SIZE);

        if (AirSpeed > 0) {

            int TileYpos = currentTille * DisplayManager.TILES_SIZE;

            int YoffSet = (int) (DisplayManager.TILES_SIZE - hitbox.height);

            return TileYpos + YoffSet - 1;

        } else {

            return currentTille * DisplayManager.TILES_SIZE;

        }

    }

    public static boolean OnTheTile(Rectangle2D.Float hitbox, int[][] leveldata) {

        if (!tileBlock(hitbox.x, hitbox.y + hitbox.height + 1, leveldata)) {

            if (!tileBlock(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, leveldata)) {

                return false;

            }

        }

        return true;

    }

  public static boolean IsFloor(Rectangle2D.Float hitbox, float xSpeed, int[][] lvlData) {
		return tileBlock(hitbox.x + xSpeed, hitbox.y + hitbox.height + 1, lvlData);
	}
    public static boolean IsAllTilesWalkable(int xStart, int xEnd, int y, int[][] lvlData) {
		for (int i = 0; i < xEnd - xStart; i++) {
			if (isTileBlock(xStart + i, y, lvlData))
				return false;
			if (!isTileBlock(xStart + i, y + 1, lvlData))
				return false;
		}
		return true;
	}
    public static boolean IsSightClear(int[][] lvlData, Rectangle2D.Float firstHitbox, Rectangle2D.Float secondHitbox, int yTile) {
		int firstXTile = (int) (firstHitbox.x / DisplayManager.TILES_SIZE);
		int secondXTile = (int) (secondHitbox.x / DisplayManager.TILES_SIZE);

		if (firstXTile > secondXTile)
			return IsAllTilesWalkable(secondXTile, firstXTile, yTile, lvlData);
		else
			return IsAllTilesWalkable(firstXTile, secondXTile, yTile, lvlData);

	}
     public static int[][] GetLevelData(BufferedImage img) {

        int[][] lvlData = new int[img.getHeight()][img.getWidth()];

        for (int j = 0; j < img.getHeight(); j++) {

            for (int i = 0; i < img.getWidth(); i++) {

                Color color = new Color(img.getRGB(i, j));

                int value = color.getRed();

                if (value >= 16)
                    value = 0;

                lvlData[j][i] = value;

            }

        }

        return lvlData;

    }

}

