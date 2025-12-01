package PlayerInput;

import java.awt.event.KeyListener;

import java.awt.event.KeyEvent;
import main.Panel;



public class KeyBoardInputs implements KeyListener {

    private Panel panel;
    public KeyBoardInputs(Panel panel) {
        this.panel = panel;
    }
            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub
                int key = e.getKeyCode();

                if (key == KeyEvent.VK_A) {
                     panel.getGame().getPlayer().setLeft(false);
               } else if (key == KeyEvent.VK_D) {
                     panel.getGame().getPlayer().setRight(false);
                     
               }
             if (key == KeyEvent.VK_SPACE) {
                 panel.getGame().getPlayer().setJumping(false);
             }

        }

            @Override
            public void keyPressed(KeyEvent e) {
                // Keyboard Buttons Output Display
               int key = e.getKeyCode();

               if (key == KeyEvent.VK_A) {
                      panel.getGame().getPlayer().setLeft(true);
                    System.out.println("Left");

               } else if (key == KeyEvent.VK_D) {
                    panel.getGame().getPlayer().setRight(true);
                    System.out.println("Right");
               }
               if (key == KeyEvent.VK_SPACE) {
                   panel.getGame().getPlayer().setJumping(true);
               }
            }
        }
