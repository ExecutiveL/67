package main;

import java.awt.Dimension;
import java.awt.Toolkit;

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
        
    }
        private void Panelsize() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
        }
            public void GameUpdate (double deltaTime) {
            }
        public void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);
            game.render(g);

            }
        public Game getGame() {
            return game;
        }
    }