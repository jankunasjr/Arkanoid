import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;

public class ArkanoidGame extends Application {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 600;
    private GameLogic gameLogic;

    public static void main(String[] args) {
        launch(args);
    }
    public static double getGameWidth() {
        return WIDTH;
    }
    public static double getGameHeight() {
        return HEIGHT;
    }
    public void drawGame(GraphicsContext gc, double canvasHeight) {
        gameLogic.paddle.draw(gc, canvasHeight);
        gameLogic.ball.draw(gc);
        gameLogic.bricks.draw(gc);
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Arkanoid Game");

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        StackPane root = new StackPane();
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setOnKeyPressed(e -> gameLogic.handleKeyPress(e.getCode(), WIDTH));

        primaryStage.setScene(scene);
        primaryStage.show();

        gameLogic = new GameLogic();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                gc.clearRect(0, 0, WIDTH, HEIGHT);
                gameLogic.updateGame();
                drawGame(gc, HEIGHT);
            }
        }.start();
    }
}
