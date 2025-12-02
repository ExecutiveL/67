package main;

import java.awt.Dimension;

import javax.swing.JPanel;

import PlayerInput.KeyBoardInputs;
import PlayerInput.MouseInputs;

public class Panel extends JPanel {
    private Game game;
    
    public Panel(Game game) {
        this.game = game;

        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(new MouseInputs(this));
        addMouseMotionListener(new MouseInputs(this)); 
        Panelsize();

        setFocusable(true);
        requestFocusInWindow();
        
    }
        private void Panelsize() {
        Dimension size = new Dimension(game.getGameWidth(), game.getGameHeight());
        setPreferredSize(size);
        System.out.println("Size" + game.getGameWidth() + ":" + game.getGameHeight());
        }
        public void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);
            game.render(g);

            }
        public Game getGame() {
            return game;
        }
    }