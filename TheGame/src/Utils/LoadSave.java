package Utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

public class LoadSave {
    public static final String PLAYER_ATLAS = "main_character.png";
    public static final String LEVEL_ATLAS = "level1_tiles.png";

	public static final String MENU_IMG = "menu_art.png";
	public static final String BUTTON_ATLAS = "Button.png";
	public static final String BACKGROUND_IMG = "Background.png";
	public static final String MOUNTAINS = "Mountains.png";
	public static final String GRASSBG = "GrassBG.png";
	public static final String TreesAtfront = "treesFront.png";
	public static final String TressAtBack = "tressBack.png";
	public static final String CLOUD_1 = "cloud1.png";

	public static final String ENEMY_1 = "enemy1.png";
	public static final String ENEMY_2 = "enemy2.png";
	
	

    public static BufferedImage getSpriteAtlas(String fileName) {
       BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/Asset/" + fileName);
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

	
	public static BufferedImage[] GetAllLevels() {
		URL url = LoadSave.class.getResource("/Asset/lvls/");
		File file = null;
		
		try {
			file = new File(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		File[] files = file.listFiles();
		File[] sortedFiles = new File[files.length];



		for (int i = 0; i < sortedFiles.length; i++) { 
        for (int j = 0; j < files.length; j++) {
            if (files[j].getName().equals("level_" + (i + 1) + ".png")) {
                sortedFiles[i] = files[j];
                break; 
            }
        }
    }
		BufferedImage[] imgs = new BufferedImage[sortedFiles.length];
		for (int i = 0; i < imgs.length; i++) {
			try {
				imgs[i] = ImageIO.read(sortedFiles[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}

