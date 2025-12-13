package Ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import GameStates.Playing;
import GameStates.gamestate;
import Utils.DisplayManager;

public class GameOver {
    
    private Playing playing;

    public GameOver(Playing playing) {
        this.playing = playing;
    }
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, DisplayManager.GAME_HEIGHT, DisplayManager.GAME_HEIGHT);

        g.setColor(Color.WHITE);
        g.drawString("Game Over", DisplayManager.GAME_WIDTH/2, 150);
        g.drawString("Press Enter to go to Menu", DisplayManager.GAME_WIDTH/2, 300);
        
    }
    public void KeyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            playing.resetAll();
            gamestate.state = gamestate.MENU;
        }
    }
    
}
