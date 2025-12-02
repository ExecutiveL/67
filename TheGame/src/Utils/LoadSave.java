package Utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class LoadSave {
    public static final String PLAYER_ATLAS = "main_character.png";

    public static BufferedImage getSpriteAtlas(String fileName) {
        try (InputStream is = LoadSave.class.getResourceAsStream("/"+ fileName)) {
            if (is == null) {
                System.err.println("Resource not found: /main_character.png");
                return null;
            }
            return ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
