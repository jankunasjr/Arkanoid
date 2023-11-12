import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bricks {
    private int[][] bricksArray;
    private double brickWidth;
    private double brickHeight;

    public Bricks(int rows, int columns) {
        this.brickWidth = 40;
        this.brickHeight = 20;
        bricksArray = new int[rows][columns];
        initializeBricks();
    }
    private void initializeBricks() {
        for (int i = 0; i < bricksArray.length; i++) {
            for (int j = 0; j < bricksArray[i].length; j++) {
                bricksArray[i][j] = 1;
            }
        }
    }
    public int[][] getBricksArray() {
        return bricksArray;
    }
    public void setBrickValue(int row, int col, int value) {
        bricksArray[row][col] = value;
    }
    public void draw(GraphicsContext gc) {
        double topSpace = 100;
        for (int i = 0; i < bricksArray.length; i++) {
            for (int j = 0; j < bricksArray[i].length; j++) {
                if (bricksArray[i][j] == 1) {
                    gc.setFill(getBrickColor(i, j));
                    gc.fillRect(j * brickWidth, topSpace + i * brickHeight, brickWidth, brickHeight);
                }
            }
        }
    }
    private Color getBrickColor(int row, int col) {
        switch ((row + col) % 5) {
            case 0:
                return Color.RED;
            case 1:
                return Color.ORANGE;
            case 2:
                return Color.YELLOW;
            case 3:
                return Color.GREEN;
            case 4:
                return Color.BLUE;
            default:
                return Color.WHITE;
        }
    }
    public double getBrickWidth() {
        return brickWidth;
    }
    public double getBrickHeight() {
        return brickHeight;
    }
}
