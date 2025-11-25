package main;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import PlayerInput.KeyBoardInputs;
import PlayerInput.MouseInputs;

public class Panel extends JPanel {
    //Crosshair
    private int xDelta = 0, yDelta = 0;
    private int PlayerX = 0, PlayerY = 0;

    private BufferedImage image;
    
    
    public Panel() {
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(new MouseInputs(this));
        addMouseMotionListener(new MouseInputs(this)); 
        Panelsize();
        Img();
    }
    private void Img() {
        InputStream is = getClass().getResourceAsStream("/MC.png");

        try {
            image = ImageIO.read(is);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private void Panelsize() {
       Dimension size = new Dimension(960, 576);
       setMinimumSize(size);
       setPreferredSize(size);
       setMaximumSize(size);
    }
    //rendering
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        int width = 50;
        int height = 50;


         //draw player
        
        g.fillRect(xDelta - 25, yDelta -25, width, height);
        g.drawImage(image, PlayerX,PlayerY, null);
       
        
        }

    //crosshair 
    public void setCrosshair(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
    } 
    public void UpdatePosition(int x, int y) {
        PlayerX += x;
        PlayerY += y;
        repaint();
    }
   
}