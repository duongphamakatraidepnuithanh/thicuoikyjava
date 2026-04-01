package yennhi.view;

import yennhi.controller.GameController;
import yennhi.model.GameModel;
import yennhi.model.Tetromino;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {
    public static final int TILE_SIZE = 35;
    private static final int PANEL_WIDTH = 6; 
    
    private GameModel model;
    private GameController controller; // Dùng để gửi sự kiện phím bấm

    private final Color[] NEON_COLORS = {
        new Color(15, 15, 26),      
        new Color(0, 229, 255),     
        new Color(41, 121, 255),    
        new Color(255, 145, 0),     
        new Color(255, 234, 0),     
        new Color(0, 230, 118),     
        new Color(213, 0, 249),     
        new Color(255, 23, 68)      
    };

    public GamePanel(GameModel model) {
        this.model = model;
        setPreferredSize(new Dimension((GameModel.BOARD_WIDTH + PANEL_WIDTH) * TILE_SIZE, GameModel.BOARD_HEIGHT * TILE_SIZE));
        setBackground(new Color(15, 15, 26));
        setFocusable(true);

        // Bắt sự kiện phím và đẩy sang Controller xử lý (Đúng chuẩn MVC)
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (controller != null) {
                    controller.handleKeyPress(e.getKeyCode());
                }
            }
        });
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        drawGrid(g2d);

        if (model.isGameStarted()) {
            drawBoard(g2d);
            drawCurrentPiece(g2d);
        }

        drawInfoPanel(g2d);

        if (!model.isGameStarted()) drawStartScreen(g2d);
        if (model.isGameOver()) drawGameOverScreen(g2d);
    }

    private void drawGrid(Graphics2D g2d) {
        g2d.setColor(new Color(45, 45, 75, 100)); 
        for (int i = 0; i <= GameModel.BOARD_WIDTH; i++) g2d.drawLine(i * TILE_SIZE, 0, i * TILE_SIZE, GameModel.BOARD_HEIGHT * TILE_SIZE);
        for (int i = 0; i <= GameModel.BOARD_HEIGHT; i++) g2d.drawLine(0, i * TILE_SIZE, GameModel.BOARD_WIDTH * TILE_SIZE, i * TILE_SIZE);
    }

    private void drawBoard(Graphics2D g2d) {
        int[][] board = model.getBoard();
        for (int i = 0; i < GameModel.BOARD_HEIGHT; i++) {
            for (int j = 0; j < GameModel.BOARD_WIDTH; j++) {
                if (board[i][j] != 0) {
                    drawNeonTile(g2d, j * TILE_SIZE, i * TILE_SIZE, NEON_COLORS[board[i][j]]);
                }
            }
        }
    }

    private void drawCurrentPiece(Graphics2D g2d) {
        Tetromino piece = model.getCurrentPiece();
        if (piece != null) {
            int[][] shape = piece.getShape();
            for (int i = 0; i < shape.length; i++) {
                for (int j = 0; j < shape[i].length; j++) {
                    if (shape[i][j] != 0) {
                        drawNeonTile(g2d, (model.getCurrentX() + j) * TILE_SIZE, (model.getCurrentY() + i) * TILE_SIZE, NEON_COLORS[piece.getColorIndex()]);
                    }
                }
            }
        }
    }

    private void drawInfoPanel(Graphics2D g2d) {
        int infoX = GameModel.BOARD_WIDTH * TILE_SIZE + 20;
        
        g2d.setFont(new Font("Consolas", Font.BOLD, 20));
        g2d.setColor(new Color(255, 255, 255, 180));
        g2d.drawString("HI-SCORE", infoX, 50);
        g2d.setColor(new Color(0, 229, 255));
        g2d.drawString(String.format("%05d", model.getHighScore()), infoX, 85);

        g2d.setColor(new Color(255, 255, 255, 180));
        g2d.drawString("SCORE", infoX, 150);
        g2d.setColor(new Color(255, 234, 0));
        g2d.drawString(String.format("%05d", model.getScore()), infoX, 185);

        if (model.isGameStarted() && model.getNextPiece() != null) {
            g2d.setColor(new Color(255, 255, 255, 180));
            g2d.drawString("NEXT", infoX, 250);
            int[][] nextShape = model.getNextPiece().getShape();
            int colorIndex = model.getNextPiece().getColorIndex();
            for (int i = 0; i < nextShape.length; i++) {
                for (int j = 0; j < nextShape[i].length; j++) {
                    if (nextShape[i][j] != 0) {
                        drawNeonTile(g2d, infoX + j * TILE_SIZE, 280 + i * TILE_SIZE, NEON_COLORS[colorIndex]);
                    }
                }
            }
        }
    }

    private void drawStartScreen(Graphics2D g2d) {
        g2d.setColor(new Color(0, 0, 0, 180));
        g2d.fillRect(0, 0, getWidth(), getHeight());
        
        g2d.setFont(new Font("Consolas", Font.BOLD, 60));
        g2d.setColor(new Color(213, 0, 249)); 
        g2d.drawString("TETRIS", 70, getHeight() / 2 - 80);
        g2d.setColor(new Color(0, 229, 255));
        g2d.drawString("NEON", 100, getHeight() / 2 - 20);

        g2d.setFont(new Font("Consolas", Font.BOLD, 22));
        g2d.setColor(Color.WHITE);
        g2d.drawString("[ PRESS ENTER TO START ]", 45, getHeight() / 2 + 80);
    }

    private void drawGameOverScreen(Graphics2D g2d) {
        g2d.setColor(new Color(0, 0, 0, 220)); 
        g2d.fillRect(0, 0, GameModel.BOARD_WIDTH * TILE_SIZE, getHeight());
        g2d.setFont(new Font("Consolas", Font.BOLD, 40));
        g2d.setColor(new Color(255, 23, 68));
        g2d.drawString("GAME OVER", 40, getHeight() / 2);
        g2d.setFont(new Font("Consolas", Font.BOLD, 16));
        g2d.setColor(Color.WHITE);
        g2d.drawString("[ ENTER TO RESTART ]", 65, getHeight() / 2 + 40);
    }

    private void drawNeonTile(Graphics2D g2d, int x, int y, Color color) {
        int padding = 2, size = TILE_SIZE - 2 * padding, arc = 12;
        g2d.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 60)); 
        g2d.fillRoundRect(x, y, TILE_SIZE, TILE_SIZE, arc + 4, arc + 4);
        g2d.setColor(color.darker());
        g2d.fillRoundRect(x + padding, y + padding, size, size, arc, arc);
        g2d.setColor(color);
        g2d.fillRoundRect(x + padding + 2, y + padding + 2, size - 4, size - 4, arc - 2, arc - 2);
        g2d.setColor(new Color(255, 255, 255, 90));
        g2d.fillRoundRect(x + padding + 2, y + padding + 2, size - 4, (size - 4) / 3, arc - 2, arc - 2);
    }
}