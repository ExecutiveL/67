package main;

import javax.swing.JFrame;

public class Window {
    public Window(Panel panel) {
        //Frame setup
        JFrame frame = new JFrame();

        frame.setTitle("Laro");
        frame.add(panel);
        frame.pack();
        
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);       
        
        panel.setFocusable(true);
        panel.requestFocusInWindow();

    }
}
