package Utils;

import java.awt.Dimension;
import java.awt.Toolkit;

public class DisplayManager {
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int GameWidth = screenSize.width;
    public static final int GameHeight = screenSize.height;

    // Base design resolution
    public static final int baseWidth = 1024;
    public static final int baseHeight = 768;

    // Uniform scale 
    public static final float scale = Math.min(
        (float) GameWidth / baseWidth,
        (float) GameHeight / baseHeight
    );

    public static int scale(int value) {
        return (int)(value * scale);
    }
}
