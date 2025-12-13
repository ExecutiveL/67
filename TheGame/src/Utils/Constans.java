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
    public static class EnemyConstants {
        public static final int ENEMY1 = 255;

        public static final int IDLE = 0;
        public static final int WALKING = 1;
        public static final int ATTACK = 2;
        public static final int HIT = 3;
        public static final int DEAD = 4;

        public static final int Enemy1_width_default = 32;
        public static final int Enemy1_height_default = 32;

        public static final int Enemy1_width = (int) (Enemy1_width_default * DisplayManager.SCALE);
        public static final int Enemy1_height = (int) (Enemy1_height_default * DisplayManager.SCALE);

        public static final int ENEMY1_OFFSET_X = (int)(26*DisplayManager.SCALE);
        public static final int ENEMY1_OFFSET_Y = (int)(7*DisplayManager.SCALE);

        public static int GetSpriteAmount(int EnemyType, int EnemyState) {
            switch (EnemyType) {
                case ENEMY1:
                    switch (EnemyState) {
                        case IDLE:
                            return 4;
                        case WALKING:
                            return 4;
                        case ATTACK:
                            return 4;
                        case HIT:
                            return 4;
                        case DEAD:
                            return 4;
                    }
               
                    return 0;
            }
            return 0;
        }

        public static int GetMaxHealth(int enemy_type) {
            switch (enemy_type) {
                case ENEMY1:
                    return 25;
                default:
                    return 1;
            }
        }
        public static int GetEnemyDamage(int enemy_type) {
            switch (enemy_type) {
                case ENEMY1:
                    return 25;
                default:
                    return 0;
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

        public static final int CityWidth = 576;
        public static final int CityHeight = 324;

        public static final int FinalCityWidth = (int)(CityWidth * DisplayManager.SCALE);
        public static final int FinalCityHeight = (int)(CityHeight * DisplayManager.SCALE);


        
        
    }

}
