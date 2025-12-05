package PlayerInput;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import GameStates.gamestate;
import main.Panel;


public class MouseInputs implements MouseListener, MouseMotionListener {

    private Panel panel;
    

    public MouseInputs(Panel panel) {
        this.panel = panel;

    }
    @Override
    public void mouseClicked(MouseEvent e) {
        switch (gamestate.state) {
            case MENU:
                panel.getGame().GetMenu().MouseClicked(e);;
                break;
            case PLAYING:
                panel.getGame().getPlaying().MouseClicked(e);
                break;
            default:
                break;
        }
       
        
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
         switch (gamestate.state) {
            case MENU:
                panel.getGame().GetMenu().MousePressed(e);;
                break;
            case PLAYING:
                panel.getGame().getPlaying().MousePressed(e);;
                break;
            default:
                break;
         }
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
        
        
        }
        
    }



