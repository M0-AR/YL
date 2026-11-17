package Exercise_25._13;

import Exercise_25._11.BSTView;
import Exercise_25._common.BST;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BSTAnimationPane extends BorderPane {
    private final BST<Integer> tree;
    private final BSTView view;
    private final TextField numberField;
    private final Label resultLabel;

    public BSTAnimationPane() {
        tree = new BST<>(new Integer[] {50, 30, 70, 20, 40, 60, 80});
        view = new BSTView();
        view.setTree(tree);

        numberField = new TextField();
        numberField.setPromptText("Integer");
        numberField.setPrefColumnCount(8);

        resultLabel = new Label("Insert or delete an integer.");

        Button insert = new Button("Insert");
        Button delete = new Button("Delete");
        Button showInorder = new Button("Show Inorder");
        Button showPreorder = new Button("Show Preorder");
        Button showPostorder = new Button("Show Postorder");

        insert.setOnAction(event -> insert());
        delete.setOnAction(event -> delete());
        showInorder.setOnAction(event -> resultLabel.setText("Inorder: " + tree.inorderList()));
        showPreorder.setOnAction(event -> resultLabel.setText("Preorder: " + tree.preorderList()));
        showPostorder.setOnAction(event -> resultLabel.setText("Postorder: " + tree.postorderList()));
        numberField.setOnAction(event -> insert.fire());

        HBox inputRow = new HBox(8, new Label("Number:"), numberField, insert, delete);
        inputRow.setAlignment(Pos.CENTER_LEFT);
        HBox showRow = new HBox(8, showInorder, showPreorder, showPostorder);
        showRow.setAlignment(Pos.CENTER_LEFT);
        VBox top = new VBox(8, inputRow, showRow);
        top.setPadding(new Insets(10));

        ScrollPane scroll = new ScrollPane(view);
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);
        resultLabel.setPadding(new Insets(10));

        setTop(top);
        setCenter(scroll);
        setBottom(resultLabel);
    }

    private void insert() {
        Integer value = readValue();
        if (value == null) {
            return;
        }
        if (tree.insert(value)) {
            view.refresh();
            resultLabel.setText("Inserted " + value + ".");
        } else {
            resultLabel.setText(value + " is already in the tree.");
        }
        numberField.clear();
        numberField.requestFocus();
    }

    private void delete() {
        Integer value = readValue();
        if (value == null) {
            return;
        }
        if (tree.delete(value)) {
            view.refresh();
            resultLabel.setText("Deleted " + value + ".");
        } else {
            resultLabel.setText(value + " is not in the tree.");
        }
        numberField.clear();
        numberField.requestFocus();
    }

    private Integer readValue() {
        String text = numberField.getText().trim();
        if (text.isEmpty()) {
            resultLabel.setText("Enter an integer.");
            return null;
        }
        try {
            return Integer.valueOf(text);
        } catch (NumberFormatException exception) {
            resultLabel.setText("Enter a valid integer.");
            return null;
        }
    }
}
