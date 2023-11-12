import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball {
    private double x;
    private double y;
    private double radius;
    private double speedX;
    private double speedY;

    public static final double RADIUS = 10;

    public Ball(double x, double y, double radius, double speedX, double speedY) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.speedX = speedX;
        this.speedY = speedY;
    }
    public void move() {
        x += speedX;
        y += speedY;
    }
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getSpeedX() {
        return speedX;
    }
    public double getSpeedY() {
        return speedY;
    }
    public void setSpeedX(double speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }
}
