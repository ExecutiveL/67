package PlayerInput;


import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import main.Panel;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private Panel panel;
    

    public MouseInputs(Panel panel) {
        this.panel = panel;

    }
    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.println("Mouse Clicked");
        
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
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
        //crosshair
        panel.setCrosshair(e.getX(), e.getY());
        
        
        }
        
    }


