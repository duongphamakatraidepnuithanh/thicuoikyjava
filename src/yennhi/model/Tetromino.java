package yennhi.model;

public class Tetromino {
    private int[][] shape;
    private int colorIndex;

    public Tetromino(int[][] shape, int colorIndex) {
        this.shape = shape;
        this.colorIndex = colorIndex;
    }

    public int[][] getShape() { return shape; }
    public void setShape(int[][] shape) { this.shape = shape; }

    public int getColorIndex() { return colorIndex; }
    public void setColorIndex(int colorIndex) { this.colorIndex = colorIndex; }
}