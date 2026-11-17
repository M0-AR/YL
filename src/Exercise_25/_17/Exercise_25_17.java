package Exercise_25._17;

import Exercise_25._16.HuffmanTree;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Exercise_25_17 extends Application {
    private final HuffmanTreeView view = new HuffmanTreeView();
    private final TextField textField = new TextField("Huffman coding");
    private final TextField bitsField = new TextField();
    private final Label encodedLabel = new Label();
    private final Label decodedLabel = new Label();
    private HuffmanTree tree;

    @Override
    public void start(Stage stage) {
        textField.setPrefColumnCount(32);
        bitsField.setPrefColumnCount(42);
        encodedLabel.setWrapText(true);
        decodedLabel.setWrapText(true);

        Button build = new Button("Build Tree");
        Button decode = new Button("Decode Text");
        build.setOnAction(event -> buildTree());
        decode.setOnAction(event -> decodeBits());
        textField.setOnAction(event -> buildTree());
        bitsField.setOnAction(event -> decodeBits());

        HBox textRow = new HBox(8, new Label("Text:"), textField, build);
        textRow.setAlignment(Pos.CENTER_LEFT);
        HBox bitsRow = new HBox(8, new Label("Bits:"), bitsField, decode);
        bitsRow.setAlignment(Pos.CENTER_LEFT);

        VBox top = new VBox(8, textRow, encodedLabel);
        top.setPadding(new Insets(10));
        VBox bottom = new VBox(8, bitsRow, decodedLabel);
        bottom.setPadding(new Insets(10));

        ScrollPane scroll = new ScrollPane(view);
        scroll.setFitToHeight(true);
        scroll.setPannable(true);

        BorderPane root = new BorderPane();
        root.setTop(top);
        root.setCenter(scroll);
        root.setBottom(bottom);

        stage.setScene(new Scene(root, 980, 680));
        stage.setTitle("Exercise 25.17 - Huffman Coding Animation");
        stage.show();
        buildTree();
    }

    private void buildTree() {
        String text = textField.getText();
        if (text == null || text.isEmpty()) {
            tree = null;
            view.clear();
            encodedLabel.setText("Enter text to encode.");
            bitsField.clear();
            decodedLabel.setText("");
            return;
        }

        tree = HuffmanTree.fromText(text);
        view.setTree(tree);
        String bits = tree.encode(text);
        encodedLabel.setText("Encoded bits (" + bits.length() + "): " + bits);
        bitsField.setText(bits);
        decodedLabel.setText("");
    }

    private void decodeBits() {
        if (tree == null) {
            decodedLabel.setText("Build a tree before decoding.");
            return;
        }
        try {
            String text = tree.decode(bitsField.getText().trim());
            decodedLabel.setText("Decoded text: " + text);
        } catch (IllegalArgumentException exception) {
            decodedLabel.setText(exception.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
