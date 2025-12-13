package Entitties;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import GameStates.Playing;
import Utils.LoadSave;
import Utils.Checker;
import Utils.DisplayManager;
import static Utils.Constans.PlayerConstants.*;




public class Player extends Entity {
    private Playing playing;
    private BufferedImage[][] animations;
    private int animationIndex, animationTick, animationSpeed = 30;
    private int playerAction = IDLE;
    private boolean moving = false, attacking = false;
    private boolean die,dead;
    private int[][] leveldata;
    private boolean left,right,jump;
    private float playerSpeed = 2.0f;
    private Float Xspeed = 0f;

    private float XdrawOffset = 6 * DisplayManager.SCALE;
    private float YdrawOffset = 7 * DisplayManager.SCALE;

    private int flipX = 1;

    //talon
    private float AirSpeed =0f;
    private float Gravity = 0.035f * DisplayManager.SCALE;
    private float JumpSpeed = -2.25f * DisplayManager.SCALE;
    private float FallSpeed = 0.5f * DisplayManager.SCALE;
    private boolean AtAir = false;

    //
    private BufferedImage StatusBar;

    private int StatusBarWidth = (int)(190 * DisplayManager.SCALE);
    private int StatusBarHeight = (int)(58 * DisplayManager.SCALE);
    private int StatusBarX = (int)(10 * DisplayManager.SCALE);
    private int StatusBarY = (int)(10 * DisplayManager.SCALE);

    private int HealthBarWidth = (int)(150 * DisplayManager.SCALE);
    private int HealthBarHeight = (int)(4 * DisplayManager.SCALE);
    private int HealthBarStartX = (int)(34 * DisplayManager.SCALE);
    private int HealthBarStartY = (int)(14 * DisplayManager.SCALE);

    private int MaxHealth = 100;
    private int CurrentHealth = MaxHealth;
    private int healthWidth = HealthBarWidth;
    
    
    
    public Player(float x, float y,int width, int height, Playing playing) {
        super(x, y,width,height);
        loadAnimations();
        Hitbox(x, y, 22 * DisplayManager.SCALE, 19 * DisplayManager.SCALE);
        this.playing = playing;

    }

    public void update() {
        updateHealthBar();

        if (dead) {
        return;
        }

        updatePosition();
       
        //OutofBounds();
        updateAnimation();
        setAnimation();

        if (CurrentHealth <=0) {
            playing.setGameOver(true);
        }
       
        
        
    }

    private void updateHealthBar() {
       healthWidth = (int) ((CurrentHealth / (float)MaxHealth) * HealthBarWidth);

    }

    public void render(Graphics g, int levelOffset) {

        int xDest= (int)(hitbox.x - XdrawOffset) - levelOffset;
        int yDest= (int)(hitbox.y - YdrawOffset);

        BufferedImage animation = animations[playerAction][animationIndex];

        int destX1 =xDest;
        int destX2 = xDest + width;

        if (flipX == -1) {
            destX1 = xDest + width;
            destX2 = xDest;
        }

        g.drawImage(animation, destX1, yDest, destX2, yDest + height, 0,0 , animation.getWidth(),animation.getHeight(), null);
        DrawHitbox(g, levelOffset);
        DrawStatusBar(g);
    }

    private void DrawStatusBar(Graphics g) {
        g.drawImage(StatusBar, StatusBarX,StatusBarY,StatusBarWidth,StatusBarHeight,null);
        g.setColor(Color.RED);
        g.fillRect(HealthBarStartX + StatusBarX, HealthBarStartY + StatusBarY, healthWidth, HealthBarHeight);
    }

    private void updateAnimation() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= GetSpriteAmount(playerAction)) {
                animationIndex = 0;
                attacking = false;
            if (playerAction == DYING) {
                animationIndex =GetSpriteAmount(DYING) -1;
                dead=true;
            }
            if (playerAction == HIT) {
                playerAction = IDLE;
            }
            if (playerAction != DYING && playerAction != HIT) {
                 animationIndex = 0;
            }
        }
     }
    }

    public void setAnimation() {
       int start = playerAction;

    if (die) {
        playerAction = DYING;
    } else if (playerAction == HIT) {

    } else { 
        
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
    } 
    
    if (start != playerAction) {
        animationTick = 0;
        animationIndex = 0;
    }
}

    public void updatePosition() {

        moving = false;
        
        if (die) {
            return;
        }

        if (jump) {
           Jumping();
        }

       if (!left && !right) {
            Xspeed = 0f;
       } else if (left && !right) {
            Xspeed = -playerSpeed;
            flipX = -1;
       } else if (!left && right) {
            Xspeed = playerSpeed;
            flipX = 1;
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
            animations = new BufferedImage[7][];

            for (int i = 0; i < animations.length; i++) {
                int frameCount = GetSpriteAmount(i);
                animations[i] = new BufferedImage[frameCount];
                for (int j = 0; j < frameCount; j++) {
                    animations[i][j] = image.getSubimage(j * 32, i * 32, 32, 32);
                }
            }
            StatusBar = LoadSave.getSpriteAtlas(LoadSave.HealthBar);

    }
    public void loadleveldata(int[][] leveldata) {
        this.leveldata = leveldata;
        if(!Checker.OnTheTile(hitbox, leveldata)) {
            AtAir = true;
        }
    }
    public void ChangeHealth(int value) {
        CurrentHealth += value;
        System.out.println("Player Current Health: " + CurrentHealth);
        if (CurrentHealth <= 0) {
            CurrentHealth = 0;
            die = true;
            
        } else if (CurrentHealth >= MaxHealth) {
            CurrentHealth = MaxHealth;
            
        }
        if (value <0 &&!die) {
            playerAction = HIT;
            animationTick = 0;
            animationIndex = 0;
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
    public boolean isDead() {
    return dead;
}

    public void shoot() {
      
    int dir = flipX; 
    
    
    int startX = (int) hitbox.x;
    int startY = (int) hitbox.y + (int) (height / 2.5); 
    
    playing.getProjectileManager().newProjectile(startX, startY, dir);
    System.out.println("SHOT FIRED!");
    }
}

