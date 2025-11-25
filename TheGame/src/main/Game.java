package main;

public class Game implements Runnable {
    
    private Window window;
    private Panel panel;

    //Game Loop
    private Thread gameThread;
    private final int FPS = 120;

    public Game() {
        panel = new Panel();
        window = new Window(panel);
        panel.requestFocus();
        startGameLoop();
        
    }
    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    //Game loop
    @Override
    public void run() {
        // TODO Auto-generated method stub
        double timePerFrame = 1000000000.0 / FPS;
        long lastFrameTime = System.nanoTime();
        long current = System.nanoTime();
        //FPS counter
        int frames = 0;
        long lastcheck = 0;

        while (true) {
            current = System.nanoTime();
            if (current - lastFrameTime >= timePerFrame) {
                panel.repaint();
                lastFrameTime = current;
                frames++;
            }
            //FPS counter
            if (System.currentTimeMillis() - lastcheck >= 1000) {
                lastcheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames = 0;
        }
        
    }
        

}
}
