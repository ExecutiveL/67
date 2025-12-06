package GameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Entitties.Player;
import Levels.levelmaniger;
import Utils.DisplayManager;
import main.Game;

public class Playing extends State implements StateMethods {

    private Player player;
    private levelmaniger levelmaniger;

    public Playing(Game game) {
        super(game);
        initClasses();

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

    }

    @Override
    public void draw(Graphics g) {
        levelmaniger.draw(g);
        player.render(g);

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
               if (key == KeyEvent.VK_BACK_SPACE) {
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
