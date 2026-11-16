package Exercise_15._35;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Exercise_15_35 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Lattice lattice = new Lattice();
        Button start = new Button("Start");
        start.setOnAction(e -> lattice.toggle(start));

        BorderPane root = new BorderPane(lattice);
        root.setBottom(start);
        BorderPane.setAlignment(start, Pos.CENTER);

        Scene scene = new Scene(root, 340, 380);
        primaryStage.setTitle("Exercise_15_35");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    static class Lattice extends Pane {
        private static final int N = 16;
        private static final double SIZE = 320;
        private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        private final double gap = SIZE / N;
        private final List<Line> steps = new ArrayList<>();
        private final Circle endMarker = new Circle(4, Color.CRIMSON);
        private List<int[]> path;
        private Timeline timeline;

        Lattice() {
            setPrefSize(SIZE, SIZE);
            drawGrid();
        }

        private void drawGrid() {
            for (int i = 0; i <= N; i++) {
                Line horizontal = new Line(0, i * gap, SIZE, i * gap);
                Line vertical = new Line(i * gap, 0, i * gap, SIZE);
                horizontal.setStroke(Color.LIGHTGRAY);
                vertical.setStroke(Color.LIGHTGRAY);
                getChildren().addAll(horizontal, vertical);
            }
        }

        void toggle(Button button) {
            if (timeline != null && timeline.getStatus() == Animation.Status.RUNNING) {
                timeline.pause();
                button.setText("Resume");
                return;
            }
            if (timeline != null && timeline.getStatus() == Animation.Status.PAUSED) {
                timeline.play();
                button.setText("Pause");
                return;
            }
            startNewPath(button);
        }

        private void startNewPath(Button button) {
            getChildren().removeAll(steps);
            steps.clear();
            getChildren().remove(endMarker);
            timeline = null;

            path = buildPath();
            if (path.size() < 2) {
                button.setText("Start");
                return;
            }

            timeline = new Timeline();
            for (int i = 1; i < path.size(); i++) {
                final int index = i;
                timeline.getKeyFrames().add(new KeyFrame(Duration.millis(i * 90), e -> addStep(index)));
            }
            timeline.setOnFinished(e -> {
                button.setText("Start");
                timeline = null;
            });
            button.setText("Pause");
            timeline.play();
        }

        private List<int[]> buildPath() {
            List<int[]> result = new ArrayList<>();
            boolean[][] visited = new boolean[N + 1][N + 1];
            int i = N / 2;
            int j = N / 2;
            visited[i][j] = true;
            result.add(new int[]{i, j});

            while (i > 0 && i < N && j > 0 && j < N) {
                int[] candidates = new int[4];
                int count = 0;
                for (int d = 0; d < DIRECTIONS.length; d++) {
                    int nextI = i + DIRECTIONS[d][0];
                    int nextJ = j + DIRECTIONS[d][1];
                    if (nextI >= 0 && nextI <= N && nextJ >= 0 && nextJ <= N
                            && !visited[nextI][nextJ]) {
                        candidates[count++] = d;
                    }
                }
                if (count == 0) {
                    break;
                }
                int direction = candidates[(int) (Math.random() * count)];
                i += DIRECTIONS[direction][0];
                j += DIRECTIONS[direction][1];
                visited[i][j] = true;
                result.add(new int[]{i, j});
            }
            return result;
        }

        private void addStep(int index) {
            int[] from = path.get(index - 1);
            int[] to = path.get(index);
            Line step = new Line(from[0] * gap, from[1] * gap, to[0] * gap, to[1] * gap);
            step.setStroke(Color.CRIMSON);
            step.setStrokeWidth(2);
            steps.add(step);
            getChildren().add(step);

            if (index == path.size() - 1) {
                endMarker.setCenterX(to[0] * gap);
                endMarker.setCenterY(to[1] * gap);
                getChildren().add(endMarker);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
