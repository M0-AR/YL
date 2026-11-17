package Exercise_16._11;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Histogram extends Pane {
    private int[] counts = new int[26];

    public Histogram() {
        setPrefSize(780, 420);
        draw();
    }

    public int[] getCounts() {
        return counts.clone();
    }

    public void setCounts(int[] counts) {
        this.counts = counts.clone();
        draw();
    }

    private void draw() {
        getChildren().clear();
        int max = 1;
        for (int count : counts) {
            max = Math.max(max, count);
        }
        double barWidth = 22;
        double gap = 6;
        double baseY = 380;
        for (int i = 0; i < counts.length; i++) {
            double height = counts[i] * 300.0 / max;
            double x = 20 + i * (barWidth + gap);
            Rectangle bar = new Rectangle(x, baseY - height, barWidth, height);
            bar.setFill(Color.CORNFLOWERBLUE);
            bar.setStroke(Color.DARKSLATEBLUE);
            Text letter = new Text(x + 6, baseY + 18, String.valueOf((char) ('A' + i)));
            Text count = new Text(x + 4, baseY - height - 5, String.valueOf(counts[i]));
            count.setFill(Color.DIMGRAY);
            getChildren().addAll(bar, letter, count);
        }
    }
}
