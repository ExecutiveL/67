package Ui;

import GameStates.gamestate;
import Utils.LoadSave;
import static Utils.Constans.UI.Buttons.*;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class MenuButton {

    private int xPos, yPos, rowIndex,index;
    
    private gamestate state;
    private BufferedImage[] img;
    private boolean mouseOver,MousePressed;
    private Rectangle bounds;


    public MenuButton(int xPos, int yPos, int rowIndex, gamestate state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;

        RenderImg();
        initBounds();

    }


    private void initBounds() {
        bounds = new Rectangle(xPos,yPos,B_WIDTH,B_HEIGHT);
    }


    private void RenderImg() {
        img = new BufferedImage[3];
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.BUTTON_ATLAS);
        for (int i = 0; i < img.length; i++) {
            img[i] = temp.getSubimage(i * B_WIDTH_DEFAULT, rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
        }
    }
    public void draw(Graphics g) {
        g.drawImage(img[index], xPos, yPos, B_WIDTH, B_HEIGHT, null);

    }
    public void Update() {
        index = 0;
        if (mouseOver)
            index = 1;
        if (MousePressed)
            index = 2;
    }
    public boolean isMouseOver() {
        return mouseOver;
    }
    public void SetMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }
    public boolean isMousePressed() {
        return MousePressed;
    }
    public void SetMousePressed(boolean MousePressed) {
        this.MousePressed = MousePressed;
    }
    public Rectangle getBounds() {
        return bounds;
    }
    public void applyGamestate() {
        gamestate.state = state;
    }
    public void reset() {
        mouseOver = false;
        MousePressed =false;
    }
}
