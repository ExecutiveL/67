package GameStates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import Ui.MenuButton;
import Utils.DisplayManager;
import Utils.LoadSave;
import main.Game;

public class Menu extends State implements StateMethods {
    private BufferedImage background;
    private MenuButton[] buttons = new MenuButton[2];
    private int backgroundwidth,backgroundheight;

    public Menu(Game game) {
        super(game);
        loadButtons();
        loadBackgrounds();
        
    }

    private void loadBackgrounds() {
       background = LoadSave.getSpriteAtlas(LoadSave.MENU_IMG);
       backgroundwidth = background.getWidth();
       backgroundheight = background.getHeight();
    }

    private void loadButtons() {
       buttons[0] = new MenuButton(1300,350,0, gamestate.PLAYING);
       buttons[1] = new MenuButton(1300,450,1, gamestate.QUIT);
       
    }

    @Override
    public void Update() {
        for(MenuButton mb : buttons)
            mb.Update();
        
      
    }

    @Override
    public void draw(Graphics g) {

        g.drawImage(background, 0,0, 1664,896, null);
       for(MenuButton mb : buttons)
            mb.draw(g);
    }

    @Override
    public void MouseClicked(MouseEvent e) {
       
       
    }

    @Override
    public void MousePressed(MouseEvent e) {
        for(MenuButton mb : buttons) {
            if (Inside(e, mb)) {
                mb.SetMousePressed(true);
                break;
            }
        }
            
        
        
    }
    @Override
    public void MouseReleased(MouseEvent e) {
         for(MenuButton mb : buttons) {
            if (Inside(e,mb)) {
                if (mb.isMousePressed())
                    mb.applyGamestate();
                break;
            }
         }
         resetButtons();
       
    }

    private void resetButtons() {
       for(MenuButton mb : buttons) {
         mb.reset();
       }
    }

    @Override
    public void MouseMoved(MouseEvent e) {
         for(MenuButton mb : buttons)
            mb.SetMouseOver(false);
        
          for(MenuButton mb : buttons)
            if(Inside(e,mb)) {
                mb.SetMouseOver(true);
                System.out.println("Hovering over: " + mb.getBounds());
                break;
            }
       
       
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
