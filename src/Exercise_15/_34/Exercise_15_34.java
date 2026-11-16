package Exercise_15._34;

import java.util.ArrayList;
import java.util.List;

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

public class Exercise_15_34 extends Application {
    @Override
    public void start(Stage primaryStage) {
        Lattice lattice = new Lattice();
        Button start = new Button("Start");
        start.setOnAction(e -> lattice.newPath());

        BorderPane root = new BorderPane(lattice);
        root.setBottom(start);
        BorderPane.setAlignment(start, Pos.CENTER);

        Scene scene = new Scene(root, 340, 380);
        primaryStage.setTitle("Exercise_15_34");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    static class Lattice extends Pane {
        private static final int N = 16;
        private static final double SIZE = 320;
        private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        private final double gap = SIZE / N;
        private final List<Line> pathLines = new ArrayList<>();
        private final Circle endMarker = new Circle(4, Color.CRIMSON);

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

        void newPath() {
            getChildren().removeAll(pathLines);
            pathLines.clear();
            getChildren().remove(endMarker);

            boolean[][] visited = new boolean[N + 1][N + 1];
            int i = N / 2;
            int j = N / 2;
            visited[i][j] = true;

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
                int nextI = i + DIRECTIONS[direction][0];
                int nextJ = j + DIRECTIONS[direction][1];

                Line step = new Line(i * gap, j * gap, nextI * gap, nextJ * gap);
                step.setStroke(Color.CRIMSON);
                step.setStrokeWidth(2);
                pathLines.add(step);
                getChildren().add(step);

                i = nextI;
                j = nextJ;
                visited[i][j] = true;
            }

            endMarker.setCenterX(i * gap);
            endMarker.setCenterY(j * gap);
            getChildren().add(endMarker);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
