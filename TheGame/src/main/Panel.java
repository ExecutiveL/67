package main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import PlayerInput.KeyBoardInputs;
import PlayerInput.MouseInputs;

import static Utils.Constans.PlayerConstants.*;
import static Utils.Constans.directions.*;

public class Panel extends JPanel {
    //Crosshair
    private int xDelta = 0, yDelta = 0;
    private int PlayerX = 0, PlayerY = 0;

    private BufferedImage image;
    //private BufferedImage[][] animations;
    private BufferedImage[][] animations;
    private int AnimationIndex, Animationtick, AnimationSpeed = 20;
    private int playerAction = IDLE;
    private int playerDirection = -1;
    private boolean moving = false;
    private boolean jumping = false;

    public Panel() {
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(new MouseInputs(this));
        addMouseMotionListener(new MouseInputs(this)); 
        Panelsize();

        Img();
        animation();
    }
    private void animation() {
        animations = new BufferedImage[4][8];

        for(int i =0; i < animations.length; i++) {
            int framecount = GetSpriteAmount(i);
            animations[i] = new BufferedImage[framecount];
            
            for(int j = 0; j < animations[i].length; j++) {
                animations[i][j] = image.getSubimage(j * 32 , i * 32 , 32, 32);
            }
        }

        
        
 }//Load image
    private void Img() {
        InputStream is = getClass().getResourceAsStream("/main_character.png");

        try {
            image = ImageIO.read(is);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    } //Panel size
    private void Panelsize() {
       Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
       setMinimumSize(size);
       setPreferredSize(size);
       setMaximumSize(size);
    } //Animation
    private void UpdateAni() {
        Animationtick++;
        if (Animationtick >= AnimationSpeed) {
            Animationtick = 0;
            AnimationIndex++;
            if (AnimationIndex >= GetSpriteAmount(playerAction)) {
                AnimationIndex = 0;
            }
        }
       
    }
    public void setAnimation() {
        if(!moving && !jumping) {
            playerAction = IDLE;
            return;
        } else if (jumping) {
            playerAction = JUMPING;
        } else if (moving) {
            playerAction = WALKING;
        }
        }

    public void UpdatePosition() {
        if (moving) {
            
            switch (playerDirection) {
                case LEFT:
                    PlayerX -= 5;
                    break;
                case RIGHT:
                   PlayerX += 5;
                    break;
                case UP:
                    PlayerY -= 5;
                    break;
                case DOWN:
                    PlayerY += 5;
                    break;
            }
         
         
        }
    }
    //rendering
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        int width = 50;
        int height = 50;

        UpdateAni();
        setAnimation();
        UpdatePosition();
         //draw player
        g.fillRect(xDelta - 25, yDelta -25, width, height);
        g.drawImage(animations[playerAction][AnimationIndex], PlayerX, PlayerY,160,160, null);
       
        
        }

    //crosshair 
    public void setCrosshair(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    }// Platyer Direction
    public void PlayerDir(int direction) {
        this.playerDirection = direction;
        moving = true;
    } //movement
    public void Moving(boolean moving) {
        this.moving = moving;
    }// juMPINH
    public void Jumping (boolean Jumping) {
        this.jumping = Jumping;
    }
}