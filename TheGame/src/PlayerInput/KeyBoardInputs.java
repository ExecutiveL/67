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
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // Keyboard Buttons
               int key = e.getKeyCode();
               if (key == KeyEvent.VK_W) {
                     panel.UpdatePosition(0, -5);
                     System.out.println("Up");
               } else if (key == KeyEvent.VK_A) {
                     panel.UpdatePosition(-5, 0);
                     System.out.println("Left");
               } else if (key == KeyEvent.VK_S) {
                    panel.UpdatePosition(0, 5);
                     System.out.println("Down");
               } else if (key == KeyEvent.VK_D) {
                    panel.UpdatePosition(5, 0);
                     System.out.println("Right");
               }
            }
        }


