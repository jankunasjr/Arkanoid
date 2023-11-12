import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class GameLogic {
    public Paddle paddle;
    public Ball ball;
    public Bricks bricks;
    private int score;
    private boolean gameOver;

    public GameLogic() {
        restartGame();
    }
    public void updateGame() {
        if (!gameOver) {
            ball.move();
            checkBallPaddleCollision();
            checkBallBrickCollisions();
            checkWallBounce();
            checkGameOver();
        }
    }
    public void handleKeyPress(KeyCode code, double maxWidth) {
        if (code == KeyCode.LEFT) {
            paddle.moveLeft(10);
        } else if (code == KeyCode.RIGHT) {
            paddle.moveRight(10, maxWidth);
        }
    }
    public boolean checkWin() {
        for (int i = 0; i < bricks.getBricksArray().length; i++) {
            for (int j = 0; j < bricks.getBricksArray()[i].length; j++) {
                if (bricks.getBricksArray()[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }
    public void restartGame() {
        bricks = new Bricks(5, 10);
        paddle = new Paddle((ArkanoidGame.getGameWidth() - Paddle.WIDTH) / 2, Paddle.HEIGHT);
        ball = new Ball(ArkanoidGame.getGameWidth() / 2, ArkanoidGame.getGameHeight() - 50, Ball.RADIUS, 2, -2);
        score = 0;
        gameOver = false;
    }
    private void checkBallPaddleCollision() {
        if (ball.getY() > ArkanoidGame.getGameHeight() - Paddle.HEIGHT - Ball.RADIUS &&
                ball.getX() > paddle.getX() && ball.getX() < paddle.getX() + Paddle.WIDTH) {
            ball.setSpeedY(-ball.getSpeedY());
        }
    }
    private void checkBallBrickCollisions() {
        double topSpace = 100;
        int row = (int) ((ball.getY() - topSpace) / bricks.getBrickHeight());

        if (row >= 0 && row < bricks.getBricksArray().length) {
            int col = (int) (ball.getX() / bricks.getBrickWidth());

            if (col >= 0 && col < bricks.getBricksArray()[0].length && bricks.getBricksArray()[row][col] == 1) {
                bricks.setBrickValue(row, col, 0);
                score += 10;
                ball.setSpeedY(-ball.getSpeedY());
            }
        }
    }
    private void checkWallBounce() {
        if (ball.getX() - Ball.RADIUS < 0 || ball.getX() + Ball.RADIUS > ArkanoidGame.getGameWidth()) {
            ball.setSpeedX(-ball.getSpeedX());
        }
        if (ball.getY() - Ball.RADIUS < 0) {
            ball.setSpeedY(-ball.getSpeedY());
        }
    }
    private void checkGameOver() {
        double bottomSpace = 10;
        if (ball.getY() > ArkanoidGame.getGameHeight() - bottomSpace) {
            showGameOverPopup();
        } else if (checkWin()) {
            showGameWinPopup();
        }
    }
    private void showGameOverPopup() {
        showEndGamePopup("LOSER", "CANT WIN IN LIFE, HUH? Your score: " + score);
    }
    private void showGameWinPopup() {
        showEndGamePopup("CONGRATS!", "YAY YOU WIN! Your score: " + score);
    }
    private void showEndGamePopup(String title, String contentText) {
        gameOver = true;
        Platform.runLater(() -> {
            Stage popupStage = createPopupStage(title, contentText);

            Button restartButton = new Button("Restart");
            Button quitButton = new Button("Quit");

            restartButton.setOnAction(e -> {
                popupStage.close();
                restartGame();
            });

            quitButton.setOnAction(e -> {
                popupStage.close();
                Platform.exit();
            });

            VBox vbox = new VBox(10, new Label(title), new Label(contentText), restartButton, quitButton);
            vbox.setStyle("-fx-alignment: center;");
            popupStage.setScene(new Scene(vbox));
            popupStage.showAndWait();
        });
    }


    private Stage createPopupStage(String title, String contentText) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setMinWidth(250);

        Label label = new Label();
        label.setText(contentText);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label);
        layout.setStyle("-fx-padding: 10;");
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        Scene scene = new Scene(layout);
        stage.setScene(scene);

        return stage;
    }
}
