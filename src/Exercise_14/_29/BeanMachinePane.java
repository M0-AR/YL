package Exercise_14._29;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class BeanMachinePane extends Pane {
    private final int slots;
    private final double w;
    private final double h;

    public BeanMachinePane(int slots) {
        this(slots, 500, 430);
    }

    public BeanMachinePane(int slots, double w, double h) {
        this.slots = slots;
        this.w = w;
        this.h = h;
        paint();
    }

    private void paint() {
        getChildren().clear();

        double margin = 45;
        double topY = 80;
        double bottomY = h - 105;
        double left = margin;
        double right = w - margin;
        double boardWidth = right - left;
        double slotWidth = boardWidth / slots;

        Line leftWall = new Line(w / 2, topY - 35, left, bottomY);
        Line rightWall = new Line(w / 2, topY - 35, right, bottomY);
        leftWall.setStroke(Color.BLACK);
        rightWall.setStroke(Color.BLACK);
        getChildren().addAll(leftWall, rightWall);

        double rowGap = (bottomY - topY) / Math.max(1, slots - 2);
        for (int row = 0; row <= slots - 2; row++) {
            int count = slots - 1 - row;
            double y = bottomY - row * rowGap;

            for (int i = 0; i < count; i++) {
                double x = left + slotWidth * (i + 1 + row / 2.0);
                Circle peg = new Circle(x, y, 4.5, Color.DARKSLATEGRAY);
                getChildren().add(peg);
            }
        }

        double floorY = bottomY + 55;
        Line floor = new Line(left, floorY, right, floorY);
        floor.setStroke(Color.BLACK);
        Line leftBase = new Line(left, bottomY, left, floorY);
        Line rightBase = new Line(right, bottomY, right, floorY);
        leftBase.setStroke(Color.BLACK);
        rightBase.setStroke(Color.BLACK);
        getChildren().addAll(floor, leftBase, rightBase);

        for (int i = 1; i < slots; i++) {
            double x = left + slotWidth * i;
            Line divider = new Line(x, bottomY, x, floorY);
            divider.setStroke(Color.BLACK);
            getChildren().add(divider);
        }

        Circle ball = new Circle(w / 2, topY - 48, 6, Color.CRIMSON);
        getChildren().add(ball);
    }
}
