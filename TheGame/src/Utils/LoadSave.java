package Utils;

import static Utils.Constans.EnemyConstants.ENEMY1;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;


import javax.imageio.ImageIO;

import Entitties.Enemy1;

public class LoadSave {
    public static final String PLAYER_ATLAS = "main_character.png";
    public static final String LEVEL_ATLAS = "level1_tiles.png";

	public static final String MENU_IMG = "menu_art.png";
	public static final String BUTTON_ATLAS = "Button.png";
	//Forest Stage
	public static final String BACKGROUND_IMG = "Background.png";
	public static final String MOUNTAINS = "Mountains.png";
	public static final String GRASSBG = "GrassBG.png";
	public static final String TreesAtfront = "treesFront.png";
	public static final String TressAtBack = "tressBack.png";
	public static final String CLOUD_1 = "cloud1.png";

	public static final String ENEMY_1 = "enemy1.png";
	public static final String ENEMY_2 = "enemy2.png";


	public static final String LEVEL_1 = "level_1.png";
	public static final String HealthBar = "healthBar.png";
	//City Stage
	public static final String CITYBACKGROUND_IMG = "CityBg.png";
	public static final String CITY_1 = "City1.png";
	public static final String CITY_2 = "City2.png";
	public static final String CITY_3 = "City3.png";
	public static final String CITY_4 = "City4.png";
	

    public static BufferedImage getSpriteAtlas(String fileName) {
    BufferedImage img = null;
    
    // Open the resource stream using Try-With-Resources
    try (InputStream is = LoadSave.class.getResourceAsStream("/asset/" + fileName)) {
        
        if (is == null) {
            System.err.println("Failed to load resource: /asset/" + fileName);
            
            return null; 
        }
        img = ImageIO.read(is); 

    } catch (IOException e) {
        e.printStackTrace();
    }
    return img;
}
	public static ArrayList<Enemy1> getEnemySprite() {
		BufferedImage img = getSpriteAtlas(LEVEL_1);
		ArrayList<Enemy1> list = new ArrayList<>();
		

		for(int j = 0; j < img.getHeight();j++)
			for(int i =0; i < img.getWidth();i++) {
				Color color = new Color(img.getRGB(i,j));
           	
				int value = color.getGreen();
				if (value == ENEMY1) {
					list.add(new Enemy1(i * DisplayManager.TILES_SIZE, j * DisplayManager.TILES_SIZE));
				}
			}
			return list;
	}

	
	public static BufferedImage[] GetAllLevels() {
		URL url = LoadSave.class.getResource("/asset/lvls/");
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
		return imgs;
	}
	
}

