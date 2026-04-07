package yennhi.view;

import javax.swing.JFrame;
import java.awt.Color;

public class MainFrame extends JFrame {
    public MainFrame(GamePanel gamePanel) {
        setTitle("Tetris: Neon Synthwave (MVC Pattern)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(new Color(15, 15, 26));
        
        add(gamePanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
