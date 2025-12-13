package Entitties;

import static Utils.Constans.EnemyConstants.*;

import static Utils.Constans.directions.*;

import java.awt.geom.Rectangle2D;

import Utils.Checker;
import Utils.DisplayManager;


public abstract class Enemy extends Entity {

    protected int animationIndex, enemyState, enemyType;

    protected int animationTick, animationSpeed = 30;

    protected boolean firstUpdate = true;

    protected boolean inAir = false;

    protected float FallSpeed;

    protected float gravity = 0.04f * Utils.DisplayManager.SCALE;

    protected float walkSpeed = 0.4f * Utils.DisplayManager.SCALE;

    protected int walkDir = LEFT;
    protected int flipX = 1;

    protected int tileY;
    protected float AttackRange = DisplayManager.TILES_SIZE;
    protected int MaxHealth;
    protected int CurrentHealth;
    protected boolean active = true;
    protected boolean AttackChecked;
   

    public Enemy(float x, float y, int width, int height, int enemyType) {

        super(x, y, width, height);

        this.enemyType = enemyType;

        Hitbox(x, y, width, height);
        this.FallSpeed = 0.0f;
        MaxHealth = GetMaxHealth(enemyType);
        CurrentHealth = MaxHealth;

    }
    protected void firstUpdateCheck(int [][] levelData) {
        if (firstUpdate) {
            if (!Checker.OnTheTile(hitbox, levelData))
                inAir = true;
            firstUpdate = false;
        }
    }
    protected void UpdateInAir(int[][] levelData) {
            if (Checker.PositionVerification(hitbox.x, hitbox.y + FallSpeed, hitbox.width, hitbox.height, levelData)) {
                hitbox.y += FallSpeed;
                FallSpeed += gravity;
            } else {
                inAir = false;
                hitbox.y = Checker.FloorAndRoofChecker(hitbox, FallSpeed);
                tileY = (int) (hitbox.y / DisplayManager.TILES_SIZE);
                FallSpeed = 0f;
            }
    }
    protected void Move(int[][] levelData) {
        float Xspeed = 0;
                    if (walkDir == LEFT) {
                        Xspeed = -walkSpeed;
                        flipX = 1;
                    }
                    else {
                        Xspeed = walkSpeed;
                        flipX = -1;
                    }
                    if (Checker.PositionVerification(hitbox.x + Xspeed, hitbox.y, hitbox.width, hitbox.height,
                            levelData))
                        if (Checker.IsFloor(hitbox, Xspeed, levelData)) {
                            hitbox.x += Xspeed;
                            return;
                        }
                    changeWalkDir();
    }
    protected void newState(int enemyState) {
        this.enemyState = enemyState;
            animationIndex = 0;
            animationTick = 0;
        }
    protected Boolean canSeePlayer(int[][] levelData, Player player) {
        int playerY = (int) (player.getHitbox().y / DisplayManager.TILES_SIZE);
        if (playerY == tileY) {
            if(InRange(player)) {
                if(Checker.IsSightClear(levelData,hitbox, player.hitbox, tileY))
                    return true;
            }
        }
        return false;
    }
    protected void towardsPlayer(Player player) {
        if (player.hitbox.x < hitbox.x) {
            walkDir = LEFT;
            
        } else {
            walkDir = RIGHT;
            
        }
    }
    protected boolean InRange(Player player) {
        int absValue = (int) Math.abs(player.hitbox.x-hitbox.x);
        return absValue <= AttackRange * 5;
    }
    protected boolean InAttackRange(Player player) {
        int absValue = (int) Math.abs(player.hitbox.x - hitbox.x);
        return absValue <= AttackRange;
    }
        
    protected void updateAnimationTick() {

        animationTick++;

        if (animationTick >= animationSpeed) {

            animationTick = 0;

            animationIndex++;

            if (animationIndex >= GetSpriteAmount(enemyType, enemyState)) {
                animationIndex = 0;
            if (enemyState == ATTACK)
                    enemyState = IDLE;
            else if (enemyState == HIT)
                    enemyState = IDLE;
            else if (enemyState == DEAD)
                    active = false;
                
            }

        }
    }
    protected void Hurt(int amount) {
        CurrentHealth -= amount;
        if(CurrentHealth <= 0) {
            newState(DEAD);
        } else
            newState(HIT);
    }
    protected void checkPlayerHit(Rectangle2D.Float attackBox, Player player) {
        if(attackBox.intersects(player.hitbox)) {
            player.ChangeHealth(-GetEnemyDamage(enemyType));
            
        }
        AttackChecked = true;

    }
    protected void changeWalkDir() {
        if (walkDir == LEFT)

            walkDir = RIGHT;
        else
            walkDir = LEFT;

    }
    public int getAnimationIndex() {
        return animationIndex;
    }
    public int getEnemyState() {

        return enemyState;

    }
    public int getFlipX() {
    return flipX;
}
    public boolean isActive() {
        return active;
    }
}

