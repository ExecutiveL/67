package Levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Utils.LoadSave;
import java.awt.Color;
import main.Game;



public class levelmaniger {

    private Game game;
    private BufferedImage[] levelSprite;
    private level level;

    public levelmaniger(Game game) {
     this.game = game;
		importOutsideSprites();
		level = new level(LoadSave.GetLevelData());
	}

	private void importOutsideSprites() {
		BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
		levelSprite = new BufferedImage[48];
		for (int j = 0; j < 4; j++)
			for (int i = 0; i < 12; i++) {
				int index = j * 12 + i;
				levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
			}
	}

	public void draw(Graphics g) {
		for (int j = 0; j < Game.TILES_IN_HEIGHT; j++)
			for (int i = 0; i < Game.TILES_IN_WIDTH; i++) {
				int index = level.getSpriteIndex(i, j);
				g.drawImage(levelSprite[index], Game.TILES_SIZE * i, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
				g.setColor(Color.BLACK);
				g.drawString(String.valueOf(index), i * TILE_SIZE + 10, j * TILE_SIZE + 20);

			}
	}

	public void update() {

	}

}