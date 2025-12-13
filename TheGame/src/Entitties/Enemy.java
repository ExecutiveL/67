package Entitties;

import static Utils.Constans.EnemyConstants.*;
import static Utils.Constans.directions.*;

import Utils.Checker;

public abstract class Enemy extends Entity {

    private int animationIndex, enemyState, enemyType;

    private int animationTick, animationSpeed = 30;

    private boolean firstUpdate = true;

    private boolean inAir = false;

    private boolean FallSpeed;

    private float gravity = 0.04f * Utils.DisplayManager.SCALE;

    private float walkSpeed = 0.4f * Utils.DisplayManager.SCALE;

    private int walkDir = LEFT;
    private int flipX = 1;



    public Enemy(float x, float y, int width, int height, int enemyType) {

        super(x, y, width, height);

        this.enemyType = enemyType;

        Hitbox(x, y, width, height);

    }

    private void updateAnimationTick() {

        animationTick++;

        if (animationTick >= animationSpeed) {

            animationTick = 0;

            animationIndex++;

            if (animationIndex >= GetSpriteAmount(enemyType, enemyState)) {

                animationIndex = 0;

            }

        }

    }

    public void Update(int[][] levelData) {

        updateAnimationTick();

        updateMove(levelData);

    }

    public void updateMove(int[][] levelData) {

        if (firstUpdate) {

            if (!Checker.OnTheTile(hitbox, levelData))

                inAir = true;

            firstUpdate = false;

        }

        if (inAir) {

            float FallSpeed = 0.5f * Utils.DisplayManager.SCALE;

            if (Checker.PositionVerification(hitbox.x, hitbox.y + FallSpeed, hitbox.width, hitbox.height, levelData)) {

                hitbox.y += FallSpeed;

                FallSpeed += gravity;

            } else {

                inAir = false;

                hitbox.y = Checker.FloorAndRoofChecker(hitbox, FallSpeed);

            }

        } else {

            switch (enemyState) {

                case IDLE:

                    enemyState = WALKING;

                    break;

                case WALKING:

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

                        if (Checker.isFloor(hitbox, Xspeed, levelData)) {

                            hitbox.x += Xspeed;

                            return;

                        }

                    changeWalkDir();

                    break;

            }

        }

    }

    private void changeWalkDir() {

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
}
