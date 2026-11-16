package Exercise_15._33;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Exercise_15_33 extends Application {
    private static final int SLOTS = 8;
    private static final int BALLS = 10;

    @Override
    public void start(Stage primaryStage) {
        BeanMachinePane pane = new BeanMachinePane(500, 500, SLOTS);
        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setTitle("Exercise_15_33");
        primaryStage.setScene(scene);
        primaryStage.show();
        pane.dropBalls(BALLS);
    }

    static class BeanMachinePane extends Pane {
        private static final double CENTER_X = 250;
        private static final double TOP_Y = 50;
        private static final double BOARD_TOP = 360;
        private static final double SLOT_TOP = 360;
        private static final double SLOT_BOTTOM = 455;
        private static final double LEFT = 50;
        private static final double RIGHT = 450;
        private static final double STEP = 25;
        private static final double ROW_GAP = 38;
        private static final int ROWS = 7;
        private static final double BALL_RADIUS = 6;

        private final int slots;
        private final Random random = new Random();
        private final List<BallPath> balls = new ArrayList<>();
        private final Text tally = new Text(16, 486, "");
        private final AnimationTimer timer;

        private int[] counts;
        private int finished;
        private long startNanos = -1;

        BeanMachinePane(double width, double height, int slots) {
            this.slots = slots;
            setPrefSize(width, height);
            drawBoard();
            tally.setFont(Font.font("Monospaced", 13));
            getChildren().add(tally);

            timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    if (startNanos < 0) {
                        startNanos = now;
                    }
                    update(now);
                }
            };
        }

        private void drawBoard() {
            Line leftWall = new Line(CENTER_X, TOP_Y, LEFT, BOARD_TOP);
            Line rightWall = new Line(CENTER_X, TOP_Y, RIGHT, BOARD_TOP);
            leftWall.setStroke(Color.BLACK);
            rightWall.setStroke(Color.BLACK);
            getChildren().addAll(leftWall, rightWall);

            for (int row = 0; row < ROWS; row++) {
                double y = 100 + row * ROW_GAP;
                for (int i = 0; i <= row; i++) {
                    double x = CENTER_X - row * STEP + i * STEP * 2;
                    getChildren().add(new Circle(x, y, 3.2, Color.DARKSLATEGRAY));
                }
            }

            double slotWidth = (RIGHT - LEFT) / slots;
            for (int i = 0; i <= slots; i++) {
                double x = LEFT + i * slotWidth;
                Line divider = new Line(x, SLOT_TOP, x, SLOT_BOTTOM);
                divider.setStroke(Color.BLACK);
                getChildren().add(divider);
            }

            Line floor = new Line(LEFT, SLOT_BOTTOM, RIGHT, SLOT_BOTTOM);
            floor.setStroke(Color.BLACK);
            getChildren().add(floor);
        }

        void dropBalls(int numberOfBalls) {
            counts = new int[slots];
            updateTally();

            for (int i = 0; i < numberOfBalls; i++) {
                List<Point2D> path = new ArrayList<>();
                path.add(new Point2D(CENTER_X, TOP_Y));
                int rights = 0;
                for (int row = 0; row < ROWS; row++) {
                    if (random.nextBoolean()) {
                        rights++;
                    }
                    double x = CENTER_X + (2 * rights - row - 1) * STEP;
                    double y = 100 + row * ROW_GAP;
                    path.add(new Point2D(x, y));
                }
                int slot = rights;
                double slotCenter = LEFT + (slot + 0.5) * (RIGHT - LEFT) / slots;
                path.add(new Point2D(slotCenter, 390));

                Circle ball = new Circle(CENTER_X, TOP_Y, BALL_RADIUS, Color.CRIMSON);
                ball.setVisible(false);
                getChildren().add(ball);
                balls.add(new BallPath(path, slot, ball, i * 280_000_000L));
            }

            timer.start();
        }

        private void update(long now) {
            for (BallPath ballPath : balls) {
                if (ballPath.finished) {
                    continue;
                }
                long elapsed = now - startNanos - ballPath.delay;
                if (elapsed < 0) {
                    continue;
                }

                ballPath.ball.setVisible(true);
                double progress = Math.min(1.0, elapsed / 2_000_000_000.0);
                Point2D position = ballPath.positionAt(progress);
                ballPath.ball.setCenterX(position.getX());
                ballPath.ball.setCenterY(position.getY());

                if (progress >= 1.0) {
                    ballPath.finished = true;
                    finished++;
                    counts[ballPath.slot]++;
                    ballPath.ball.setCenterY(SLOT_BOTTOM - 14 - (counts[ballPath.slot] - 1) * 12);
                    updateTally();
                }
            }

            if (finished == balls.size()) {
                timer.stop();
            }
        }

        private void updateTally() {
            StringBuilder text = new StringBuilder("Slot counts: ");
            for (int count : counts) {
                text.append(count).append("  ");
            }
            tally.setText(text.toString());
        }
    }

    static class BallPath {
        private final List<Point2D> points;
        private final double[] lengths;
        private final double totalLength;
        private final int slot;
        private final Circle ball;
        private final long delay;

        private boolean finished;

        BallPath(List<Point2D> points, int slot, Circle ball, long delay) {
            this.points = points;
            this.slot = slot;
            this.ball = ball;
            this.delay = delay;
            lengths = new double[points.size() - 1];
            double total = 0;
            for (int i = 0; i < lengths.length; i++) {
                lengths[i] = points.get(i).distance(points.get(i + 1));
                total += lengths[i];
            }
            totalLength = total;
        }

        Point2D positionAt(double progress) {
            double target = totalLength * progress;
            double covered = 0;
            for (int i = 0; i < lengths.length; i++) {
                if (covered + lengths[i] >= target) {
                    double fraction = lengths[i] == 0 ? 0 : (target - covered) / lengths[i];
                    Point2D from = points.get(i);
                    Point2D to = points.get(i + 1);
                    return new Point2D(
                            from.getX() + (to.getX() - from.getX()) * fraction,
                            from.getY() + (to.getY() - from.getY()) * fraction);
                }
                covered += lengths[i];
            }
            return points.get(points.size() - 1);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
