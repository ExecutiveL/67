package PlayerInput;

import java.awt.event.KeyListener;

import GameStates.gamestate;

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
                
                switch (gamestate.state) {
                    case MENU:
                        panel.getGame().GetMenu().KeyReleased(e);
                        break;
                    case PLAYING:
                    panel.getGame().getPlaying().KeyReleased(e);
                    default:
                        break;
                }
                
            }


            @Override
            public void keyPressed(KeyEvent e) {
                 switch (gamestate.state) {
                    case MENU:
                        panel.getGame().GetMenu().KeyPressed(e);
                        break;
                    case PLAYING:
                        panel.getGame().getPlaying().KeyPressed(e);
                        break;
                    default:
                        break;
                }
             
            }
        }

