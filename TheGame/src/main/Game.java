package main;

import Utils.DisplayManager;
import java.awt.Graphics;

import GameStates.Menu;
import GameStates.Playing;
import GameStates.gamestate;

public class Game implements Runnable {
    
    private Panel panel;

    //Game Loop
    private Thread gameThread;
    private final int FPS = 120;
    private final int UPS = 200;

    private Playing playing;
    private Menu menu;

    public Game() {
        initClasses();

        panel = new Panel(this);
        new Window(panel);
        
        startGameLoop();
        
    }
    private void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
    }
    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void update() {
        switch(gamestate.state) {
            case MENU:
                menu.Update();
                break;
            case PLAYING:
                playing.Update();
                break;
            default:
                break;
        }
        
    }
    public void render(Graphics g) {
        switch(gamestate.state) {
            case MENU:
                menu.draw(g);
                break;
            case PLAYING:
                playing.draw(g);
                break;
            default:
                break;
        }
        
    }
      
    public int getGameWidth() {
        return DisplayManager.GAME_WIDTH;
    }

    public int getGameHeight() {
        return DisplayManager.GAME_HEIGHT;
    }
    //Game loop
    @Override
    public void run() {
        // TODO Auto-generated method stub
    double timePerFrame = 1_000_000_000.0 / FPS;
    double timePerUpdate = 1_000_000_000.0 / UPS;

    long previousTime = System.nanoTime();
    double deltaU = 0, deltaF = 0;

    int frames = 0, updates = 0;
    long lastCheck = System.currentTimeMillis();

    while (true) {
        long currentTime = System.nanoTime();
        long elapsed = currentTime - previousTime;
        
        
        
        previousTime = currentTime;

        deltaU += elapsed / timePerUpdate;
        deltaF += elapsed / timePerFrame;

        while (deltaU >= 1) {
            update();
            updates++;
            deltaU--;
        }

        if (deltaF >= 1) {
            panel.repaint();
            frames++;
            deltaF--;
        }

        if (System.currentTimeMillis() - lastCheck >= 1000) {
            lastCheck = System.currentTimeMillis();
            System.out.println("FPS: " + frames + " UPS: " + updates);
            frames = 0;
            updates = 0;
        }

      
        try { Thread.sleep(1); } catch (InterruptedException e) {}
        }
    }
    public void WindowFocusLost() {
        if(gamestate.state == gamestate.PLAYING) {
            playing.getPlayer().resetDirBooleans();
        }
    }
    public Menu GetMenu() {
        return menu;
    }
    public Playing getPlaying() {
        return playing;
    }
   
}