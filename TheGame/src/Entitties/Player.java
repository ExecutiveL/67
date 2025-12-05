package Entitties;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


import Utils.LoadSave;

import static Utils.Constans.PlayerConstants.*;



public class Player extends Entity {
    private BufferedImage[][] animations;
    private int animationIndex, animationTick, animationSpeed = 30;
    private int playerAction = IDLE;
    private boolean moving = false, jumping = false, attacking = false;
   
    private boolean left,right;
    
    public Player(float x, float y,int width, int height) {
        super(x, y,width,height);
        loadAnimations();
    }

    public void update(double deltaTime) {
        updatePosition(deltaTime);
        //OutofBounds();
        updateAnimation();
        setAnimation();
        
        
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][animationIndex],(int)x, (int)y, width , height,null);
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

        if (!moving && !jumping) {
            playerAction = IDLE;
        } else if (jumping) {
            playerAction = JUMPING;
        } else if (moving) {
            playerAction = WALKING;
        }
        if (attacking) {
            playerAction = ATTACKING;
        }
        if (start != playerAction) {
            animationTick = 0;
            animationIndex = 0;
        }
    }

    public void updatePosition(double deltaTime) {
        int speed = 200;
        if(left && !right) {
            x-= speed * deltaTime;
            moving = true;
        } else if (right && !left) {
            x+= speed * deltaTime;
            moving = true;
        } else {
            moving = false;
        }
        
    }
    public void setAttacking(boolean attacking) {
        this.attacking = attacking;

    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    private void loadAnimations() {

            BufferedImage image = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);

            animations = new BufferedImage[5][];
            for (int i = 0; i < animations.length; i++) {
                int frameCount = GetSpriteAmount(i);
                animations[i] = new BufferedImage[frameCount];
                for (int j = 0; j < frameCount; j++) {
                    animations[i][j] = image.getSubimage(j * 32, i * 32, 32, 32);
                }
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
}
