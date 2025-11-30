package Utils;

public class Constans {
    
    public static class directions {
        public static final int LEFT = 0;
        public static final int RIGHT = 1;
        public static final int UP = 2;
        public static final int DOWN = 3;
    }

    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int WALKING = 1;
        public static final int JUMPING = 2;
        public static final int FALLING = 3;
        public static final int DAMAGE = 4;
        public static final int RUNNING = 5;

        public static int GetSpriteAmount(int playerAction) {
            switch (playerAction) {
                case IDLE:
                    return 4;
                case WALKING:
                    return 5;
                case JUMPING:
                    return 4;
                case FALLING:
                    return 1;
                case DAMAGE:
                    return 3;
                case RUNNING:
                    return 7;
                default:
                    return 1;
            }
        }
    }

}
