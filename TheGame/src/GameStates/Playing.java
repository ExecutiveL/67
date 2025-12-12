package GameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import Entitties.Player;
import Levels.levelmaniger;
import static Utils.Constans.Environment.*;
import Utils.DisplayManager;
import Utils.LoadSave;
import main.Game;

public class Playing extends State implements StateMethods {

    private Player player;
    private levelmaniger levelmaniger;

    private int xLvlOffset;
    private int leftBorder = (int) (0.2 * DisplayManager.GAME_WIDTH);
    private int RightBorder = (int) (0.8 * DisplayManager.GAME_WIDTH);
    private int MaxLvlOffsetX;

    private BufferedImage background,mountain,grass,cloud_1,treesF,treesB;
    private int[] smallcloudposition;
    private Random rnd = new Random();

    public Playing(Game game) {
        super(game);
        initClasses();

        background = LoadSave.getSpriteAtlas(LoadSave.BACKGROUND_IMG);
        mountain = LoadSave.getSpriteAtlas(LoadSave.MOUNTAINS);
        grass = LoadSave.getSpriteAtlas(LoadSave.GRASSBG);
        treesF = LoadSave.getSpriteAtlas(LoadSave.TreesAtfront);
        treesB = LoadSave.getSpriteAtlas(LoadSave.TressAtBack);

        cloud_1 =LoadSave.getSpriteAtlas(LoadSave.CLOUD_1);
        smallcloudposition = new int[8];

        for(int i = 0; i < smallcloudposition.length;i++) {
            smallcloudposition[i] = (int)(90 * DisplayManager.SCALE) + rnd.nextInt((int)(10 * DisplayManager.SCALE));
        }

        calcLvlOffset();
        loadForestLevel();

    }

    private void loadForestLevel() {
        
    }

    private void calcLvlOffset() {
       MaxLvlOffsetX = levelmaniger.getCurrentLevel().getMaxLvlOffsetX();

    }

    private void initClasses() {
        levelmaniger = new levelmaniger(game);
        player = new Player(100, 100, (int) (36 * DisplayManager.SCALE), (int) (40 * DisplayManager.SCALE));
        player.loadleveldata(levelmaniger.getCurrentLevel().getLevelData());

    }

    public void WindowFocusLost() {
        player.resetDirBooleans();
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void Update() {
        levelmaniger.update();
        player.update();

        CheckBorder();

    }

    private void CheckBorder() {
       int playerX = (int) player.getHitbox().x;
       int diff = playerX - xLvlOffset;

       if (diff > RightBorder) {
        xLvlOffset += diff - RightBorder;
       } else if (diff < leftBorder) {
        xLvlOffset += diff - leftBorder;
       }
       if (xLvlOffset > MaxLvlOffsetX)
         xLvlOffset = MaxLvlOffsetX;
        else if (xLvlOffset < 0) {
            xLvlOffset = 0;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background,0,0,DisplayManager.GAME_WIDTH,DisplayManager.GAME_HEIGHT,null);
        
        drawBG(g);

        levelmaniger.draw(g,xLvlOffset);
        player.render(g,xLvlOffset);


    }

    private void drawBG(Graphics g) {
        for (int i = 0; i < 3; i++) {
            g.drawImage(mountain,i *mountainwidth - (int)(xLvlOffset * 0.3), (int)( 150 * DisplayManager.SCALE), mountainwidth, mountainheight,null);
            g.drawImage(grass,i * grasswidth - (int)(xLvlOffset * 0.4), (int)( 175 * DisplayManager.SCALE), grasswidth, grassheight,null);
            g.drawImage(treesB,i * grasswidth - (int)(xLvlOffset * 0.5), (int)( 200 * DisplayManager.SCALE), grasswidth, grassheight,null);
            g.drawImage(treesF,i * grasswidth - (int)(xLvlOffset * 0.6), (int)( 220 * DisplayManager.SCALE), grasswidth, grassheight,null);
        }
        
       for (int i = 0; i < smallcloudposition.length; i++) {
        g.drawImage(cloud_1, Final_1cloudWidth * 4 * i - (int)(xLvlOffset * 0.7), smallcloudposition[i], Final_1cloudWidth, Final_1cloudHeight, null);
    }
       }
       

    @Override
    public void MouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1){
            player.setAttacking(true);
            System.out.println("[LEFT CLICK] Attack");
    }
    }
    @Override
    public void MousePressed(MouseEvent e) {
         
        

    }

    @Override
    public void MouseReleased(MouseEvent e) {
          if (e.getButton() == MouseEvent.BUTTON1){
            player.setAttacking(false);
        

    }
}
    @Override
    public void MouseMoved(MouseEvent e) {

    }

    @Override
    public void KeyPressed(KeyEvent e) {
          int key = e.getKeyCode();

               if (key == KeyEvent.VK_A) {
                   player.setLeft(true);
                   System.out.println("[A] Left");
               } else if (key == KeyEvent.VK_D) {
                   player.setRight(true);
                   System.out.println("[D] Right");
               }
               if (key == KeyEvent.VK_SPACE) {
                   player.setJumping(true);
                   System.out.println("[SPACE] Jump");
               }
               if (key == KeyEvent.VK_ESCAPE) {
                    gamestate.state = gamestate.MENU;
               }

    }

    @Override
    public void KeyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            player.setLeft(false);
        } else if (key == KeyEvent.VK_D) {
            player.setRight(false);

        }
        if (key == KeyEvent.VK_SPACE) {
            player.setJumping(false);
        }

    }

}
