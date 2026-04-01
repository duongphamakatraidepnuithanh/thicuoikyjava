package yennhi.model;

public class GameModel {
    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGHT = 20;

    private int[][] board;
    private int score;
    private int highScore;
    private boolean isGameOver;
    private boolean isGameStarted;

    private Tetromino currentPiece;
    private Tetromino nextPiece;
    private int currentX;
    private int currentY;

    public GameModel() {
        board = new int[BOARD_HEIGHT][BOARD_WIDTH];
        score = 0;
        isGameOver = false;
        isGameStarted = false;
    }

    // Các Getter và Setter
    public int[][] getBoard() { return board; }
    public void setBoard(int[][] board) { this.board = board; }

    public int getScore() { return score; }
    public void setScore(int score) { this.score = score; }

    public int getHighScore() { return highScore; }
    public void setHighScore(int highScore) { this.highScore = highScore; }

    public boolean isGameOver() { return isGameOver; }
    public void setGameOver(boolean gameOver) { isGameOver = gameOver; }

    public boolean isGameStarted() { return isGameStarted; }
    public void setGameStarted(boolean gameStarted) { isGameStarted = gameStarted; }

    public Tetromino getCurrentPiece() { return currentPiece; }
    public void setCurrentPiece(Tetromino currentPiece) { this.currentPiece = currentPiece; }

    public Tetromino getNextPiece() { return nextPiece; }
    public void setNextPiece(Tetromino nextPiece) { this.nextPiece = nextPiece; }

    public int getCurrentX() { return currentX; }
    public void setCurrentX(int currentX) { this.currentX = currentX; }

    public int getCurrentY() { return currentY; }
    public void setCurrentY(int currentY) { this.currentY = currentY; }
}