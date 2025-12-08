package Levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Utils.DisplayManager;
import Utils.LoadSave;

import main.Game;



public class levelmaniger {
    private BufferedImage[] levelSprite;
    private level level;

    public levelmaniger(Game game) {
		importOutsideSprites();
		level = new level(LoadSave.GetLevelData());
	}

	private void importOutsideSprites() {
		BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
		System.out.println("Image size: " + img.getWidth() + "x" + img.getHeight());

		 int tileWidth = 16;
   		 int tileHeight = 16;

		 int cols = img.getWidth() / tileWidth;
		int rows = img.getHeight() / tileHeight;

		levelSprite = new BufferedImage[16];
		for (int j = 0; j < rows; j++)
			for (int i = 0; i < cols; i++) {
				int index = j * cols + i;
				levelSprite[index] = img.getSubimage(i * tileWidth, j * tileHeight, tileWidth, tileHeight);
			}
	}

	public void draw(Graphics g, int LvlOffset) {
		for (int j = 0; j < DisplayManager.TILES_IN_HEIGHT; j++)
			for (int i = 0; i < level.getLevelData()[0].length; i++) {
				int index = level.getSpriteIndex(i, j);
				g.drawImage(levelSprite[index], DisplayManager.TILES_SIZE * i - LvlOffset, DisplayManager.TILES_SIZE * j, DisplayManager.TILES_SIZE, DisplayManager.TILES_SIZE, null);

			}
	}

	public void update() {

	}
	public level getCurrentLevel() {
		return level;
	}

}