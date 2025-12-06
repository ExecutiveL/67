package Entitties;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


import Utils.LoadSave;
import Utils.Checker;
import Utils.DisplayManager;
import static Utils.Constans.PlayerConstants.*;




public class Player extends Entity {

    private BufferedImage[][] animations;
    private int animationIndex, animationTick, animationSpeed = 30;
    private int playerAction = IDLE;
    private boolean moving = false, attacking = false;
    private int[][] leveldata;
    private boolean left,right,jump;
    private float playerSpeed = 2.0f;
    private Float Xspeed = 0f;

    private float XdrawOffset = 6 * DisplayManager.SCALE;
    private float YdrawOffset = 7 * DisplayManager.SCALE;

    //talon
    private float AirSpeed =0f;
    private float Gravity = 0.05f * DisplayManager.SCALE;
    private float JumpSpeed = -2.25f * DisplayManager.SCALE;
    private float FallSpeed = 0.5f * DisplayManager.SCALE;
    private boolean AtAir = false;
    
    public Player(float x, float y,int width, int height) {
        super(x, y,width,height);
        loadAnimations();
        Hitbox(x, y, 22 * DisplayManager.SCALE, 24 * DisplayManager.SCALE);

    }

    public void update() {
        updatePosition();
       
        //OutofBounds();
        updateAnimation();
        setAnimation();

       
        
        
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][animationIndex], (int)(hitbox.x - XdrawOffset), (int)(hitbox.y - YdrawOffset), width , height,null);
        DrawHitbox(g);
    }

    private void updateAnimation() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= GetSpriteAmount(playerAction)) {
                animationIndex = 0;
                attacking = false;
            }
        }
    }

    public void setAnimation() {
        int start = playerAction;

        if (AtAir) {
            if (AirSpeed < 0) {
                 playerAction = JUMPING;
            } else {
                playerAction = FALLING;
            }
        }

        else if (moving) {
            playerAction = WALKING;
        } else {
            playerAction = IDLE;
        }

        if (attacking) {
            playerAction = ATTACKING;
        }
        if (start != playerAction) {
            animationTick = 0;
            animationIndex = 0;
        }
       
    }

    public void updatePosition() {

        moving = false;
        
        if (jump) {
           Jumping();
        }

       if (!left && !right) {
            Xspeed = 0f;
       } else if (left && !right) {
            Xspeed = -playerSpeed;
       } else if (!left && right) {
            Xspeed = playerSpeed;
       } else {
        Xspeed = 0f;
       }

       if (Xspeed == 0 && !AtAir)
            return;
        
        if (!AtAir) {
            if (!Checker.OnTheTile(hitbox, leveldata)) {
                AtAir =true;
            }
        } 
    
        if (AtAir) {
            if (Checker.PositionVerification(hitbox.x, hitbox.y + AirSpeed, hitbox.width, hitbox.height, leveldata)) {
                hitbox.y += AirSpeed;
                AirSpeed += Gravity;

                updateXposition(AirSpeed);
            } else {
                hitbox.y = Checker.FloorAndRoofChecker(hitbox,AirSpeed);
                if(AirSpeed > 0) {
                    resetInAir();
                } else {
                    AirSpeed = FallSpeed;
                    updateXposition(AirSpeed);
                }
            }
        } else {
            updateXposition(Xspeed);
        }
        moving = true;
        
    }
    private void updateXposition(float xspeed) {
         if(Checker.PositionVerification(hitbox.x + Xspeed, hitbox.y,hitbox.width, hitbox.height,leveldata)) {
            hitbox.x += Xspeed;
            
            
        } else {
            hitbox.x = Checker.CloserToWall(hitbox,Xspeed);
        }
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;

    }

    public void Jumping() {
        if (AtAir)
            return;
        AtAir = true;
        AirSpeed = JumpSpeed;
    }

    private void loadAnimations() {

            BufferedImage image = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);
            System.out.println("width:"+ image.getWidth() + "Height:" + image.getHeight());
            animations = new BufferedImage[5][];
            for (int i = 0; i < animations.length; i++) {
                int frameCount = GetSpriteAmount(i);
                animations[i] = new BufferedImage[frameCount];
                for (int j = 0; j < frameCount; j++) {
                    animations[i][j] = image.getSubimage(j * 32, i * 32, 32, 32);
                }
            }
    }
    public void loadleveldata(int[][] leveldata) {
        this.leveldata = leveldata;
        if(!Checker.OnTheTile(hitbox, leveldata)) {
            AtAir = true;
        }
    }
    
   /*  public void OutofBounds() {
    if (x < 0) x = 0;
    if (x > DisplayManager.GameWidth - DisplayManager.PLAYER_WIDTH)
        x = DisplayManager.GameWidth - DisplayManager.PLAYER_WIDTH;

    if (y < 0) y = 0;
    if (y > DisplayManager.GameHeight - DisplayManager.PLAYER_HEIGHT)
        y = DisplayManager.GameHeight - DisplayManager.PLAYER_HEIGHT; */
    
    public void resetDirBooleans() {
        left = false;
        right = false;
    }
    public void setLeft(boolean left) {
        this.left = left;
    }
    public void setRight(boolean right) {
        this.right = right;
    }
    public boolean isLeft() {
        return left;
    }
    public boolean isRight() {
        return right;
    }
    private void resetInAir() {
        AtAir = false;
        AirSpeed = 0;

    }
    public void setJumping(boolean jump) {
        this.jump = jump;
    }
}

