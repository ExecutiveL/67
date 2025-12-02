package main;

import java.awt.Graphics;

import Entitties.Player;

public class Game implements Runnable {
    
    private Window window;
    private Panel panel;

    //Game Loop
    private Thread gameThread;
    private final int FPS = 120;
    private final int UPS = 200;

    private Player player;

    public final static int tileDefaultSize = 32;
    public final static float Scale = 1.0f;
    public final static int TilesWitdth = 32;
    public final static int TilesHeight = 32;
    public final static int TileSize = (int)(tileDefaultSize * Scale);
    public final static int GameWidth = TileSize * TilesWitdth;
     public final static int GameHeight = TileSize * TilesHeight;

    public Game() {
        initClasses();

        panel = new Panel(this);
        window = new Window(panel);
        
        startGameLoop();
        
    }
    private void initClasses() {
        player = new Player(200,200);
    }
    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void update(double deltaTime) {
        player.update(deltaTime);
        
    }
    public void render(Graphics g) {
        player.render(g);
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
        double deltaTime = elapsed / 1_000_000_000.0; // convert ns → seconds
        
        previousTime = currentTime;

        deltaU += elapsed / timePerUpdate;
        deltaF += elapsed / timePerFrame;

        while (deltaU >= 1) {
            update(deltaTime);
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
        player.resetDirBooleans();
    }
    public Player getPlayer() {
        return player;
}

}