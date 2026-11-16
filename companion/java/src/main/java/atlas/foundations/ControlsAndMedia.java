package atlas.foundations;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

public final class ControlsAndMedia {
    private ControlsAndMedia() {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Controls and Layout");
        JTextField name = new JTextField(16);
        JSlider zoom = new JSlider(50, 200, 100);
        JLabel output = new JLabel("Hello, world");
        JButton greet = new JButton("Greet");

        greet.addActionListener(event -> {
            String who = name.getText().isBlank() ? "world" : name.getText().trim();
            output.setText("Hello, " + who);
        });
        zoom.addChangeListener(event ->
            output.setFont(output.getFont().deriveFont(
                Font.PLAIN, Math.max(10f, zoom.getValue() / 4f))));

        JPanel form = new JPanel();
        form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        form.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        form.add(new JLabel("Name"));
        form.add(name);
        form.add(greet);
        form.add(new JLabel("Text size"));
        form.add(zoom);
        form.add(output);

        JPanel root = new JPanel(new BorderLayout());
        root.add(form, BorderLayout.CENTER);
        frame.setContentPane(root);
        frame.setPreferredSize(new Dimension(420, 320));
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
