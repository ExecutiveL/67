package Utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class LoadSave {
    public static final String PLAYER_ATLAS = "main_character.png";
    public static final String LEVEL_ATLAS = "Tiles.png";
    public static final String LEVEL_MAP = "level.png";

    public static BufferedImage getSpriteAtlas(String fileName) {
        try (InputStream is = LoadSave.class.getResourceAsStream("/"+ fileName)) {
            if (is == null) {
                System.err.println("Resource not found: " + fileName);
                return null;
            }
            return ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static int[][] GetLevelData() {
        int[][] leveldata = new int[100][100];
        BufferedImage img = getSpriteAtlas(LEVEL_MAP);

        for(int j = 0; j < img.getHeight(); j++)
            for(int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if(value >= 54)
                    value = 0;
                leveldata[j][i] = value;
        }
        return leveldata;
    }
}

