package PlayerInput;

import java.awt.event.KeyListener;

import java.awt.event.KeyEvent;
import main.Panel;

import static Utils.Constans.directions.*;

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
                  if (key == KeyEvent.VK_W) {
                      panel.Moving(false);
               } else if (key == KeyEvent.VK_A) {
                    panel.Moving(false);
                     
               } else if (key == KeyEvent.VK_S) {
                     panel.Moving(false);
                     
               } else if (key == KeyEvent.VK_D) {
                    panel.Moving(false);
                     
               }
               if (key == KeyEvent.VK_SHIFT) {
                    panel.Running(false);
            }
             if (key == KeyEvent.VK_SPACE) {
               panel.Jumping(false);
             }

        }

            @Override
            public void keyPressed(KeyEvent e) {
                // Keyboard Buttons
               int key = e.getKeyCode();
               if (key == KeyEvent.VK_W) {
                    panel.PlayerDir(UP);
                     
               } else if (key == KeyEvent.VK_A) {
                    panel.PlayerDir(LEFT);
                     System.out.println("Left");
               } else if (key == KeyEvent.VK_S) {
                    panel.PlayerDir(DOWN);
                     System.out.println("Down");
               } else if (key == KeyEvent.VK_D) {
                    panel.PlayerDir(RIGHT);
                     System.out.println("Right");
               }
               if (key == KeyEvent.VK_SHIFT) {
                    panel.Running(true);
               }
               if (key == KeyEvent.VK_SPACE) {
                    panel.Jumping(true);
               }
            }
        }


