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
        public static final int ATTACKING = 4;
        public static final int DYING = 5;

        public static int GetSpriteAmount(int playerAction) {
            switch (playerAction) {
                case IDLE:
                    return 8;
                case WALKING:
                    return 8;
                case JUMPING:
                    return 5;
                case FALLING:
                    return 3;
                case ATTACKING:
                    return 8;
                case DYING:
                    return 8;
                default:
                    return 1;
            }
        }
    }

}
