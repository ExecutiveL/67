package Entitties;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import static Utils.Constans.PlayerConstants.*;


public class Player extends Entity {

    private BufferedImage[][] animations;
    private int animationIndex, animationTick, animationSpeed = 30;
    private int playerAction = IDLE;
    private boolean moving = false, jumping = false, attacking = false;
   
    private boolean left,right;
    
    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }

    public void update(double deltaTime) {
        updatePosition(deltaTime);
        updateAnimation();
        setAnimation();
        
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][animationIndex], (int)x, (int)y, 100, 100, null);
    }

    private void updateAnimation() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= GetSpriteAmount(playerAction)) {
                animationIndex = 0;
            }
        }
    }

    public void setAnimation() {

        if (!moving && !jumping) {
            playerAction = IDLE;
        } else if (jumping) {
            playerAction = JUMPING;
        } else if (moving) {
            playerAction = WALKING;
        }
    }

    public void updatePosition(double deltaTime) {
        double speed = 500;
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

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    private void loadAnimations() {
        try (InputStream is = getClass().getResourceAsStream("/main_character.png")) {
            BufferedImage image = ImageIO.read(is);
            animations = new BufferedImage[4][];
            for (int i = 0; i < animations.length; i++) {
                int frameCount = GetSpriteAmount(i);
                animations[i] = new BufferedImage[frameCount];
                for (int j = 0; j < frameCount; j++) {
                    animations[i][j] = image.getSubimage(j * 32, i * 32, 32, 32);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
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
