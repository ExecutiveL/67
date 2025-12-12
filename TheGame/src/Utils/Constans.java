package Utils;

public class Constans {


    public static class UI {
        public static class Buttons {
            public static final int B_WIDTH_DEFAULT = 140;
            public static final int B_HEIGHT_DEFAULT = 56;
            public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * DisplayManager.SCALE);
            public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * DisplayManager.SCALE);
        }
    }

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
        public static final int HIT = 5;
        public static final int DYING = 6;

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
                    return 4;
                case DYING:
                    return 8;
                default:
                    return 1;
            }
        }
    }
    public static class Environment {
        public static final int mountainwidth = 1024;
        public static final int mountainheight = 346;

        //public static final int FinalcloudWidth = (int)(cloudWidth*DisplayManager.SCALE);
        //public static final int FinalcloudHeight = (int)(cloudHeight*DisplayManager.SCALE);

        public static final int grasswidth= 1024;
        public static final int grassheight = 346;


        public static final int cloud_1Width = 25;
        public static final int cloud_1Height = 10;

        public static final int Final_1cloudWidth = (int)(cloud_1Width * 5);
        public static final int Final_1cloudHeight = (int)(cloud_1Height * 5);
        
        
    }

}
