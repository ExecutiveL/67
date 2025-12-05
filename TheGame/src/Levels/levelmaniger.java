package Levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Utils.DisplayManager;
import Utils.LoadSave;
import java.awt.Color;
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
		levelSprite = new BufferedImage[54];
		for (int j = 0; j < 4; j++)
			for (int i = 0; i < 12; i++) {
				int index = j * 12 + i;
				levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
			}
	}

	public void draw(Graphics g) {
		for (int j = 0; j < DisplayManager.TILES_IN_HEIGHT; j++)
			for (int i = 0; i < DisplayManager.TILES_IN_WIDTH; i++) {
				int index = level.getSpriteIndex(i, j);
				g.drawImage(levelSprite[index], DisplayManager.TILES_SIZE * i, DisplayManager.TILES_SIZE * j, DisplayManager.TILES_SIZE, DisplayManager.TILES_SIZE, null);
				
				g.setColor(Color.white);
				g.drawString(String.valueOf(index), i * DisplayManager.TILES_SIZE + 10, j * DisplayManager.TILES_SIZE + 20);

			}
	}

	public void update() {

	}

}