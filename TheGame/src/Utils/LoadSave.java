package Utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class LoadSave {
    public static final String PLAYER_ATLAS = "main_character.png";
    public static final String LEVEL_ATLAS = "ForestTile.png";
    public static final String LEVEL_MAP = "Forest_Stage.png";

    public static BufferedImage getSpriteAtlas(String fileName) {
       BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
		if (is == null) {
			throw new IllegalArgumentException("Resource not found: " + fileName);
		}
		try {
			img = ImageIO.read(is);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}

	public static int[][] GetLevelData() {
	BufferedImage img = getSpriteAtlas(LEVEL_MAP);
	System.out.println("width:"+ img.getWidth() + "Height:" + img.getHeight());
    int[][] lvlData = new int[img.getHeight()][img.getWidth()];

    for (int j = 0; j < img.getHeight(); j++) {
        for (int i = 0; i < img.getWidth(); i++) {
            Color color = new Color(img.getRGB(i, j));
            int value = color.getRed();
            if (value >= 60) value = 0;
            lvlData[j][i] = value;
        }
    }
    return lvlData;
	}
}

