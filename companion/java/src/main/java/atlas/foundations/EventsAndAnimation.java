package atlas.foundations;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;

public final class EventsAndAnimation extends JPanel {
    private static final int BALL = 28;

    private double x = 40;
    private double y = 120;
    private double vx = 180;
    private double vy = 110;
    private long previous = System.nanoTime();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(34, 211, 238));
        g.fillOval((int) x, (int) y, BALL, BALL);
    }

    public void start() {
        new Timer(16, event -> {
            long now = System.nanoTime();
            double dt = Math.min((now - previous) / 1_000_000_000.0, 0.05);
            previous = now;

            x += vx * dt;
            y += vy * dt;

            if (x < 0 || x > getWidth() - BALL) {
                vx = -vx;
                x = Math.max(0, Math.min(x, getWidth() - BALL));
            }
            if (y < 0 || y > getHeight() - BALL) {
                vy = -vy;
                y = Math.max(0, Math.min(y, getHeight() - BALL));
            }
            repaint();
        }).start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Events and Animation");
        EventsAndAnimation panel = new EventsAndAnimation();
        frame.add(panel);
        frame.setSize(480, 320);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        panel.start();
    }
}
