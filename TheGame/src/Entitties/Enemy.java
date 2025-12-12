package Entitties;

import static Utils.Constans.PlayerConstants.GetSpriteAmount;

import Entitties.Entity;

public abstract class Enemy extends Entity {

    private int animationIndex, enemyState, enemyType,enemyAction;
    private int animationTick, animationSpeed = 30;


    public Enemy(float x, float y, int width, int height,int enemyType) {
        super(x, y, width, height);
        this.enemyType = enemyType;

        Hitbox(x, y, width, height);
        
    }
    private void updateAnimationTick() {
         animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >=  GetSpriteAmount(enemyAction)) {
                animationIndex = 0;
            }
            
            }
        }
    public void Update() {
        updateAnimationTick();
    }
    public int getAnimationIndex() {
        return animationIndex;
    }
    public int getEnemyState() {
        return enemyState;
    }
    }


