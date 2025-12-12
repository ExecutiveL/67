package Levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.util.ArrayList;

import Utils.DisplayManager;
import Utils.LoadSave;

import main.Game;



public class levelmaniger {
    private BufferedImage[] levelSprite;
    private ArrayList<level> levels;
	private int currentLevelIndex = 0;

    public levelmaniger(Game game) {
		importOutsideSprites();
		levels = new ArrayList<>();
		buildAllLevels();
	}

	private void buildAllLevels() {
		BufferedImage[] allLevels = LoadSave.GetAllLevels();
		for (BufferedImage img : allLevels) {
			levels.add(new level(img));
		}
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
			for (int i = 0; i < levels.get(currentLevelIndex).getLevelData()[0].length; i++) {
				int index = levels.get(currentLevelIndex).getSpriteIndex(i, j);
				g.drawImage(levelSprite[index], DisplayManager.TILES_SIZE * i - LvlOffset, DisplayManager.TILES_SIZE * j, DisplayManager.TILES_SIZE, DisplayManager.TILES_SIZE, null);

			}
	}

	public void update() {

	}
	public level getCurrentLevel() {
		return levels.get(currentLevelIndex);
	}
	public int getNoLevels() {
		return levels.size();
	}

}