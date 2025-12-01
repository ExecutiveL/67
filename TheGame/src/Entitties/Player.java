package Entitties;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import static Utils.Constans.PlayerConstants.*;
import static Utils.Constans.directions.*;

public class Player extends Entity {
    private BufferedImage[][] animations;
    private int animationIndex, animationTick, animationSpeed = 30;
    private int playerAction = IDLE;
    private int playerDirection = -1;
    private boolean moving = false;
    private boolean jumping = false;
    
    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }

    public void update(double deltaTime) {
        updateAnimation();
        setAnimation();
        updatePosition(deltaTime);
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][animationIndex], (int)x, (int)y, 160, 160, null);
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
        double speed = 1000;
        if (moving) {
            switch (playerDirection) {
                case LEFT:  x -= speed * deltaTime; break;
                case RIGHT: x += speed * deltaTime; break;
            }
        }
    }

    public void setPlayerDir(int direction) {
        this.playerDirection = direction;
        moving = true;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
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
}
