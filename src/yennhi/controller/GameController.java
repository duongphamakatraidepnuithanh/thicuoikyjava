package yennhi.controller;

import yennhi.model.GameModel;
import yennhi.model.Tetromino;
import yennhi.utils.FileHelper;
import yennhi.view.GamePanel;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameController implements ActionListener {
    private GameModel model;
    private GamePanel view;
    private Timer timer;

    private final int[][][] SHAPES = {
        {{1, 1, 1, 1}}, {{2, 0, 0}, {2, 2, 2}}, {{0, 0, 3}, {3, 3, 3}},
        {{4, 4}, {4, 4}}, {{0, 5, 5}, {5, 5, 0}}, {{0, 6, 0}, {6, 6, 6}},
        {{7, 7, 0}, {0, 7, 7}}
    };

    public GameController(GameModel model, GamePanel view) {
        this.model = model;
        this.view = view;
        this.view.setController(this); // Liên kết View với Controller
        
        // Đọc điểm cao nhất từ file
        model.setHighScore(FileHelper.readHighScore());
        
        timer = new Timer(400, this);
    }

    // Xử lý sự kiện phím bấm từ View truyền sang
    public void handleKeyPress(int keyCode) {
        if ((!model.isGameStarted() || model.isGameOver()) && keyCode == KeyEvent.VK_ENTER) {
            startGame();
            return;
        }

        if (!model.isGameStarted() || model.isGameOver()) return;

        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                if (!checkCollision(model.getCurrentX() - 1, model.getCurrentY(), model.getCurrentPiece().getShape())) {
                    model.setCurrentX(model.getCurrentX() - 1);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (!checkCollision(model.getCurrentX() + 1, model.getCurrentY(), model.getCurrentPiece().getShape())) {
                    model.setCurrentX(model.getCurrentX() + 1);
                }
                break;
            case KeyEvent.VK_DOWN:
                if (!checkCollision(model.getCurrentX(), model.getCurrentY() + 1, model.getCurrentPiece().getShape())) {
                    model.setCurrentY(model.getCurrentY() + 1);
                }
                break;
            case KeyEvent.VK_UP:
                rotateShape();
                break;
        }
        view.repaint(); // Yêu cầu View vẽ lại sau khi cập nhật Model
    }

    private void startGame() {
        int[][] board = model.getBoard();
        for (int i = 0; i < GameModel.BOARD_HEIGHT; i++) {
            for (int j = 0; j < GameModel.BOARD_WIDTH; j++) {
                board[i][j] = 0;
            }
        }
        model.setScore(0);
        model.setGameOver(false);
        model.setGameStarted(true);
        model.setNextPiece(null); // Reset khối tiếp theo
        spawnPiece();
        timer.start();
        view.repaint();
    }

    private void spawnPiece() {
        Random rand = new Random();
        if (model.getNextPiece() == null) {
            int index = rand.nextInt(SHAPES.length);
            model.setNextPiece(new Tetromino(cloneShape(SHAPES[index]), index + 1));
        }

        model.setCurrentPiece(model.getNextPiece());
        
        int nextIndex = rand.nextInt(SHAPES.length);
        model.setNextPiece(new Tetromino(cloneShape(SHAPES[nextIndex]), nextIndex + 1));

        model.setCurrentX(GameModel.BOARD_WIDTH / 2 - model.getCurrentPiece().getShape()[0].length / 2);
        model.setCurrentY(0);

        if (checkCollision(model.getCurrentX(), model.getCurrentY(), model.getCurrentPiece().getShape())) {
            model.setGameOver(true);
            timer.stop();
        }
    }

    private int[][] cloneShape(int[][] original) {
        int[][] clone = new int[original.length][original[0].length];
        for (int i = 0; i < original.length; i++) {
            System.arraycopy(original[i], 0, clone[i], 0, original[i].length);
        }
        return clone;
    }

    // Thuật toán xoay ma trận
    private void rotateShape() {
        int[][] currentShape = model.getCurrentPiece().getShape();
        int rows = currentShape.length;
        int cols = currentShape[0].length;
        int[][] rotatedShape = new int[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                rotatedShape[j][rows - 1 - i] = currentShape[i][j];
            }
        }

        if (!checkCollision(model.getCurrentX(), model.getCurrentY(), rotatedShape)) {
            model.getCurrentPiece().setShape(rotatedShape);
        }
    }

    // Thuật toán kiểm tra va chạm
    private boolean checkCollision(int nextX, int nextY, int[][] shape) {
        int[][] board = model.getBoard();
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] != 0) {
                    int boardX = nextX + j;
                    int boardY = nextY + i;
                    if (boardX < 0 || boardX >= GameModel.BOARD_WIDTH || boardY >= GameModel.BOARD_HEIGHT) return true;
                    if (boardY >= 0 && board[boardY][boardX] != 0) return true;
                }
            }
        }
        return false;
    }

    private void placePiece() {
        int[][] board = model.getBoard();
        int[][] shape = model.getCurrentPiece().getShape();
        int colorIndex = model.getCurrentPiece().getColorIndex();

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] != 0) {
                    board[model.getCurrentY() + i][model.getCurrentX() + j] = colorIndex;
                }
            }
        }
        checkLines();
        spawnPiece();
    }

    private void checkLines() {
        int[][] board = model.getBoard();
        for (int i = GameModel.BOARD_HEIGHT - 1; i >= 0; i--) {
            boolean isFull = true;
            for (int j = 0; j < GameModel.BOARD_WIDTH; j++) {
                if (board[i][j] == 0) {
                    isFull = false; break;
                }
            }
            if (isFull) {
                model.setScore(model.getScore() + 100);
                
                // Lưu file nếu phá kỷ lục
                if (model.getScore() > model.getHighScore()) {
                    model.setHighScore(model.getScore());
                    FileHelper.saveHighScore(model.getHighScore());
                }

                for (int y = i; y > 0; y--) {
                    System.arraycopy(board[y - 1], 0, board[y], 0, GameModel.BOARD_WIDTH);
                }
                for (int j = 0; j < GameModel.BOARD_WIDTH; j++) board[0][j] = 0;
                i++; 
            }
        }
    }

    // Vòng lặp game rơi tự do
    @Override
    public void actionPerformed(ActionEvent e) {
        if (model.isGameStarted() && !model.isGameOver()) {
            if (!checkCollision(model.getCurrentX(), model.getCurrentY() + 1, model.getCurrentPiece().getShape())) {
                model.setCurrentY(model.getCurrentY() + 1);
            } else {
                placePiece();
            }
            view.repaint();
        }
    }
}