package GameStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Utils.DisplayManager;
import main.Game;

public class Menu extends State implements StateMethods {

    public Menu(Game game) {
        super(game);
        
    }

    @Override
    public void Update() {
        
      
    }

    @Override
    public void draw(Graphics g) {
        
       g.setColor(Color.BLACK);
       g.drawString("INVASION PROTOCOL", DisplayManager.GAME_WIDTH/2, 200);
    }

    @Override
    public void MouseClicked(MouseEvent e) {
       
       
    }

    @Override
    public void MousePressed(MouseEvent e) {
        
        
    }
    @Override
    public void MouseReleased(MouseEvent e) {
       
    }

    @Override
    public void MouseMoved(MouseEvent e) {
       
       
    }

    @Override
    public void KeyPressed(KeyEvent e) {
        if (e.getKeyCode()== KeyEvent.VK_ENTER) {
            gamestate.state = gamestate.PLAYING;
        }
       
       
    }

    @Override
    public void KeyReleased(KeyEvent e) {
       
       
    }

    
}
