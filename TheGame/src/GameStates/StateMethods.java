package GameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface StateMethods {

    public void Update();

    public void draw(Graphics g);

    public void MouseClicked(MouseEvent e);

    public void MousePressed(MouseEvent e);

    public void MouseReleased(MouseEvent e);

    public void MouseMoved(MouseEvent e);

    public void KeyPressed(KeyEvent e);
    
    public void KeyReleased(KeyEvent e);


}
