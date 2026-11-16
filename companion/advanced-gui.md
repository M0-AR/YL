# Advanced GUI: Composing a Real Interface

This volume covers chapter 34. The chapter moves from individual controls to the structure of a real application: menus, tables, dialogs, layout, data binding, and the separation between the view and the model.

- [Chapter 34 — Advanced GUI](#chapter-34-advanced-gui)

---

<a id="chapter-34-advanced-gui"></a>
<a name="chapter-34-advanced-gui"></a>
## Chapter 34 — Advanced GUI

### The idea

A useful interface is not a pile of controls. It is a composition with a clear hierarchy:

- **Containers** arrange children: `BorderPane`, `SplitPane`, `TabPane`, `ScrollPane`.
- **Controls** collect or display data: `TableView`, `ListView`, `ComboBox`, `MenuBar`.
- **Models** hold the data and its rules, independently of the widgets.
- **Bindings** connect a model property to a view property so one update follows the other.

The best JavaFX code keeps business rules out of event handlers. The handler translates a user action into a model operation; the model decides what is valid; the view observes the result.

### A worked example

```java
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PeopleTable extends Application {
    public record Person(String name, int age) {}

    @Override
    public void start(Stage stage) {
        TableView<Person> table = new TableView<>();

        TableColumn<Person, String> name = new TableColumn<>("Name");
        name.setCellValueFactory(row ->
                new SimpleStringProperty(row.getValue().name()));

        TableColumn<Person, Number> age = new TableColumn<>("Age");
        age.setCellValueFactory(row ->
                new SimpleIntegerProperty(row.getValue().age()));

        table.getColumns().add(name);
        table.getColumns().add(age);
        table.getItems().addAll(
                new Person("Ada", 36),
                new Person("Linus", 54));

        stage.setScene(new Scene(new BorderPane(table), 420, 260));
        stage.setTitle("People");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```

A `TableView` does not read fields directly. Each column declares a cell-value factory that returns an observable value for the row. The table re-renders when that value changes.

### What to notice

- `ObservableList` is the bridge between a model and a `TableView` or `ListView`. Replace the list contents rather than rebuilding the control.
- A cell-value factory returns an `ObservableValue`, not a plain value. That is what enables live updates.
- Layout panes own geometry. Set constraints with `HBox.setHgrow` or `VBox.setVgrow` instead of absolute positions.
- A `MenuBar` belongs at the top of a `BorderPane`. Menu items fire actions; they do not contain business logic.
- FXML separates structure from code, but it does not remove the need for a controller and a model.
- UI updates must happen on the JavaFX application thread. Use `Platform.runLater` or a `Task`’s `onSucceeded` handler.
- A dialog should return a result, not mutate hidden global state. `Optional<T>` is a clean way to represent “cancelled.”

### Practice

1. Add a form below the table that appends a new `Person` when the user presses Add.
2. Add a delete button that removes the selected row and does nothing when no row is selected.
3. Replace the static list with an `ObservableList` and bind a label to `table.getSelectionModel().selectedItemProperty()`.

### Checkpoint

You are ready to move on when you can explain the difference between a layout container and a control, and when you can connect a model list to a table without putting data rules in the cell factory.
