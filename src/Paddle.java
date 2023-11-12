import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Paddle {
    private double x;
    private double width;
    private double height;
    public static final double WIDTH = 60;
    public static final double HEIGHT = 10;

    public Paddle(double x, double height) {
        this.x = x;
        this.width = WIDTH;
        this.height = height;
    }
    public void moveLeft(double step) {
        if (x > 0) {
            x -= step;
        }
    }
    public void moveRight(double step, double maxWidth) {
        if (x < maxWidth - width) {
            x += step;
        }
    }
    public void draw(GraphicsContext gc, double canvasHeight) {
        gc.setFill(Color.BLUE);
        gc.fillRect(x, canvasHeight - height, width, height);
    }
    public double getX() {
        return x;
    }
}
