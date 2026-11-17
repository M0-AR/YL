"""Authored studio content: GUI, I/O, generics, collections, and algorithms."""

CHAPTERS = [
    {
        "n": 14,
        "title": "JavaFX Basics",
        "part": "Part 3 · GUI programming",
        "tagline": "A JavaFX window is a tree of nodes: the stage is the window, the scene is its content, and layout panes decide where children sit.",
        "minutes": 45,
        "tags": ["javafx", "scene", "stage", "pane", "layout", "node", "properties"],
        "objectives": [
            "Describe the stage, scene, and scene-graph roles in a JavaFX application.",
            "Choose a layout pane that matches a simple dashboard arrangement.",
            "Explain how a bound property updates a control without manual refresh code.",
        ],
        "subgoals": [
            {
                "title": "Stage, scene, root",
                "idea": "A <code>Stage</code> is the top-level window. A <code>Scene</code> holds one root node. The root is usually a pane whose children form the scene graph.",
            },
            {
                "title": "Panes own placement",
                "idea": "A <code>GridPane</code> places children by column and row. An <code>HBox</code> places them in a row. The pane, not the child, decides the geometry.",
            },
            {
                "title": "Nodes are styled, not painted",
                "idea": "A <code>Label</code>, <code>Button</code>, and <code>Circle</code> are all nodes. You change their state through properties; the toolkit renders them.",
            },
            {
                "title": "Properties can be observed",
                "idea": "A property holds a value and notifies listeners when it changes. A binding expresses a dependent value, such as a label that always shows a counter.",
            },
            {
                "title": "The launch thread owns the UI",
                "idea": "JavaFX creates the application thread. Long work belongs on a background thread; UI updates return to the application thread.",
            },
        ],
        "example": {
            "title": "DashboardShell: four nodes, one grid",
            "intro": "The layout is the lesson. Read the column and row indexes before reading the text inside each control.",
            "code": [
                "public class DashboardShell extends Application {",
                "    @Override",
                "    public void start(Stage stage) {",
                "        Label title = new Label(\"Revenue\");",
                "        Label value = new Label(\"$12,480\");",
                "        Button refresh = new Button(\"Refresh\");",
                "        GridPane grid = new GridPane();",
                "        grid.setHgap(12);",
                "        grid.setVgap(8);",
                "        grid.add(title, 0, 0);",
                "        grid.add(value, 0, 1);",
                "        grid.add(refresh, 1, 0, 1, 2);",
                "        stage.setScene(new Scene(grid, 320, 180));",
                "        stage.show();",
                "    }",
                "}",
            ],
            "steps": [
                {
                    "label": "Application shell",
                    "note": "Extending <code>Application</code> lets the launcher create the stage and call <code>start</code> on the JavaFX application thread.",
                },
                {
                    "label": "Leaf nodes",
                    "note": "Labels and a button are controls: nodes with skin, focus behavior, and properties. They do not know their position yet.",
                },
                {
                    "label": "Layout container",
                    "note": "<code>GridPane</code> is a container. Gap settings affect the space between cells, not the space inside a control.",
                },
                {
                    "label": "Cell assignment",
                    "note": "The first two adds use column, row. The button spans one column and two rows, so it occupies the right side of both rows.",
                },
                {
                    "label": "Scene and show",
                    "note": "The scene gives the root a size. Until <code>show</code> runs, the stage exists but no window is visible.",
                },
            ],
            "predict": {
                "prompt": "What happens if the button is added with <code>grid.add(refresh, 0, 0)</code> after the title?",
                "answer": "Two children occupy the same cell. The later child is drawn on top, so the title is hidden or overlapped. A grid cell can hold multiple nodes, but that is rarely the layout you want.",
            },
        },
        "engine": "layout",
        "spec": {
            "hint": "Switch between HBox, VBox, FlowPane, GridPane, BorderPane, and StackPane. Watch the same four children adapt.",
            "mode": "grid",
            "children": ["Title", "Value", "Refresh", "Status"],
            "gap": 12,
            "padding": 16,
        },
        "parsons": {
            "prompt": "Rebuild the JavaFX shell so the grid has two rows and a visible stage.",
            "lines": [
                "public class DashboardShell extends Application {",
                "    @Override",
                "    public void start(Stage stage) {",
                "        GridPane grid = new GridPane();",
                "        grid.setHgap(12);",
                "        grid.add(new Label(\"Revenue\"), 0, 0);",
                "        grid.add(new Button(\"Refresh\"), 1, 0);",
                "        stage.setScene(new Scene(grid, 320, 180));",
                "        stage.show();",
                "    }",
                "}",
            ],
            "solution": [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
            "seed": 14,
            "why": "The application creates a pane, fills cells, wraps the pane in a sized scene, and only then shows the stage.",
        },
        "quiz": [
            {
                "q": "Which object is the root of the scene graph?",
                "options": ["The Stage", "The Scene's root node", "The application thread", "The CSS file"],
                "a": 1,
                "why": "A scene holds exactly one root node; every visible control is a descendant of that root.",
            },
            {
                "q": "Which pane is the natural choice for controls aligned in columns and rows?",
                "options": ["StackPane", "GridPane", "Pane", "Canvas"],
                "a": 1,
                "why": "GridPane assigns children to column and row cells and can span them.",
            },
            {
                "q": "What does <code>stage.show()</code> do?",
                "options": [
                    "Compiles the scene graph",
                    "Makes the window visible",
                    "Starts a new thread for each node",
                    "Converts the scene to an image",
                ],
                "a": 1,
                "why": "The stage and scene can exist before show; show makes the window appear.",
            },
        ],
        "artifact": {
            "name": "DashboardPane.java",
            "description": "A dashboard skeleton with a title, value, status label, and refresh button. Add a second metric row and keep the grid readable.",
            "code": """import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DashboardPane extends Application {
    @Override
    public void start(Stage stage) {
        Label title = new Label("Revenue");
        Label value = new Label("$12,480");
        Label status = new Label("Ready");
        Button refresh = new Button("Refresh");

        GridPane grid = new GridPane();
        grid.setHgap(12);
        grid.setVgap(8);
        grid.add(title, 0, 0);
        grid.add(value, 0, 1);
        grid.add(refresh, 1, 0);
        grid.add(status, 1, 1);

        // TODO: add a second metric (for example, Refunds) without breaking alignment.

        stage.setScene(new Scene(grid, 360, 200));
        stage.setTitle("Dashboard");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_14",
        },
        "reflection": "Sketch the scene graph for the dashboard, then explain which node would change if the refresh button were disabled and which node would change if the revenue value updated.",
        "studio": {
            "title": "Layout switchboard",
            "intro": "The same four children are relaid out by six managers. Notice which managers control position and which control overlap.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/core-language.md#chapter-14-javafx-basics",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_14",
        },
    },
    {
        "n": 15,
        "title": "Event-Driven Programming and Animations",
        "part": "Part 3 · GUI programming",
        "tagline": "A GUI program does not run top to bottom; it waits for events, then runs the handlers you registered.",
        "minutes": 45,
        "tags": ["events", "handler", "lambda", "animation", "timeline", "state"],
        "objectives": [
            "Trace an event from its source through a registered handler to a visible state change.",
            "Write a lambda handler that updates model state instead of only changing a label.",
            "Explain why animation state belongs on the application thread.",
        ],
        "subgoals": [
            {
                "title": "The source raises, the handler responds",
                "idea": "A button is an event source. <code>setOnAction</code> stores a handler. When the button fires, the toolkit calls that handler with an event object.",
            },
            {
                "title": "Registration is not execution",
                "idea": "The line that registers a handler runs once. The handler body runs later, once per event. Reading code in order can hide that gap.",
            },
            {
                "title": "State first, pixels second",
                "idea": "Update an <code>int</code> or model object, then refresh the label from that state. If the label is the only state, every new feature has to reverse-engineer it.",
            },
            {
                "title": "Animation is timed state",
                "idea": "A <code>Timeline</code> or <code>AnimationTimer</code> updates a property on each pulse. The same state-change rule applies, just many times per second.",
            },
            {
                "title": "One thread owns the scene",
                "idea": "Handlers and animation callbacks run on the JavaFX application thread. Blocking that thread freezes input, animation, and rendering together.",
            },
        ],
        "example": {
            "title": "ClickMeter: one counter, two observers",
            "intro": "The handler changes the counter. The label is refreshed from that counter, so the button text and the status line can never disagree.",
            "code": [
                "public class ClickMeter extends Application {",
                "    private int clicks = 0;",
                "    private final Label output = new Label(\"Clicks: 0\");",
                "    @Override",
                "    public void start(Stage stage) {",
                "        Button add = new Button(\"Add one\");",
                "        add.setOnAction(event -> {",
                "            clicks++;",
                "            output.setText(\"Clicks: \" + clicks);",
                "        });",
                "        VBox root = new VBox(8, add, output);",
                "        stage.setScene(new Scene(root, 240, 120));",
                "        stage.show();",
                "    }",
                "}",
            ],
            "steps": [
                {
                    "label": "Initial state",
                    "note": "The field starts at zero and the label starts with the same value. Two representations, one source of truth.",
                },
                {
                    "label": "Create the source",
                    "note": "The button exists but has no behavior. Clicking it now would do nothing visible.",
                },
                {
                    "label": "Register, do not run",
                    "note": "The lambda is stored. Its body runs only when an action event reaches the button.",
                },
                {
                    "label": "Event dispatch",
                    "note": "A click fires the handler on the application thread. The counter changes before the label is touched.",
                },
                {
                    "label": "Refresh from state",
                    "note": "The label derives its text from <code>clicks</code>. A reset or undo feature can now update one field and call the same refresh.",
                },
            ],
            "predict": {
                "prompt": "If the handler increments <code>clicks</code> twice but sets the label only once, what does the label show after one click?",
                "answer": "It shows the new value, because the single refresh reads the field after both increments. The label reflects state at refresh time, not the number of increments.",
            },
        },
        "engine": "state",
        "spec": {
            "hint": "Step through registration and dispatch. Watch the counter change before the screen does.",
            "code": [
                "int clicks = 0;",
                "Button add = new Button(\"Add one\");",
                "add.setOnAction(e -> clicks++);",
                "add.fire();",
                "screen = \"Clicks: \" + clicks;",
            ],
            "vars": ["clicks", "screen"],
            "steps": [
                {
                    "line": 0,
                    "vars": {"clicks": "0", "screen": "\"\""},
                    "note": "Initial state: zero clicks and an empty screen.",
                },
                {
                    "line": 1,
                    "vars": {"clicks": "0", "screen": "\"\""},
                    "note": "The button object exists, but no handler has been attached yet.",
                },
                {
                    "line": 2,
                    "vars": {"clicks": "0", "screen": "\"\""},
                    "note": "Registration stores the lambda. Nothing executes.",
                },
                {
                    "line": 3,
                    "vars": {"clicks": "1", "screen": "\"\""},
                    "note": "fire() dispatches an action event, so the handler increments the counter. The screen is still stale.",
                },
                {
                    "line": 4,
                    "vars": {"clicks": "1", "screen": "\"Clicks: 1\""},
                    "note": "The refresh reads the new counter and produces the visible text.",
                },
            ],
        },
        "parsons": {
            "prompt": "Rebuild a handler that increments a counter and refreshes one label.",
            "lines": [
                "int clicks = 0;",
                "Label output = new Label(\"Clicks: 0\");",
                "Button add = new Button(\"Add one\");",
                "add.setOnAction(event -> {",
                "    clicks++;",
                "    output.setText(\"Clicks: \" + clicks);",
                "});",
                "VBox root = new VBox(8, add, output);",
                "stage.setScene(new Scene(root, 240, 120));",
                "stage.show();",
            ],
            "solution": [0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
            "seed": 15,
            "why": "State is created first, the handler is registered before the user can click, and the scene is shown last.",
        },
        "quiz": [
            {
                "q": "When does the body of <code>add.setOnAction(e -> clicks++)</code> run?",
                "options": ["When the line is parsed", "When the button is constructed", "Each time the button fires an action", "Only when the window closes"],
                "a": 2,
                "why": "The call registers a handler; the body runs once per matching event.",
            },
            {
                "q": "Why keep the counter in a field instead of only in the label text?",
                "options": [
                    "Labels cannot store numbers",
                    "The field is the model that other features can read and update",
                    "Fields render faster than labels",
                    "JavaFX requires every handler to use a field",
                ],
                "a": 1,
                "why": "A separate model prevents the UI text from becoming the only, fragile source of truth.",
            },
            {
                "q": "What happens if a handler blocks the application thread for five seconds?",
                "options": [
                    "Only the button stops working",
                    "The window freezes and animations pause",
                    "JavaFX starts a second UI thread",
                    "The handler is cancelled automatically",
                ],
                "a": 1,
                "why": "Input, animation, and rendering all share the application thread, so blocking it freezes the interface.",
            },
        ],
        "artifact": {
            "name": "ClickCounter.java",
            "description": "A click counter with a reset button and a maximum of ten. Disable the add button at the limit and re-enable it after reset.",
            "code": """import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClickCounter extends Application {
    private int clicks = 0;
    private final Label output = new Label("Clicks: 0");
    private final Button add = new Button("Add one");
    private final Button reset = new Button("Reset");

    @Override
    public void start(Stage stage) {
        add.setOnAction(event -> {
            clicks++;
            refresh();
        });
        reset.setOnAction(event -> {
            clicks = 0;
            refresh();
        });

        VBox root = new VBox(8, add, reset, output);
        stage.setScene(new Scene(root, 240, 160));
        stage.show();
    }

    private void refresh() {
        output.setText("Clicks: " + clicks);
        // TODO: disable add at 10 and re-enable it when clicks is below 10.
    }

    public static void main(String[] args) {
        launch(args);
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_15",
        },
        "reflection": "Describe one bug that becomes possible when a handler updates a label directly and a second handler updates the counter. Then describe the smallest design change that removes the possibility.",
        "studio": {
            "title": "Registration versus dispatch",
            "intro": "The playground separates the line that installs a handler from the line that fires the event. The gap is the whole idea.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/foundations.md#chapter-15-events-and-animation",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_15",
        },
    },
    {
        "n": 16,
        "title": "JavaFX UI Controls and Multimedia",
        "part": "Part 3 · GUI programming",
        "tagline": "Controls are small state machines; a form is a layout of those machines plus one place that reads their values.",
        "minutes": 50,
        "tags": ["controls", "textfield", "checkbox", "combobox", "listview", "media", "form"],
        "objectives": [
            "Select controls that match the shape of the data being collected.",
            "Lay out a form with labels, inputs, and actions without relying on absolute positions.",
            "Read control values at submit time and explain why live listeners are a different design.",
        ],
        "subgoals": [
            {
                "title": "Controls expose properties, not strings",
                "idea": "A <code>TextField</code> has a <code>text</code> property. A <code>CheckBox</code> has <code>selected</code>. Read the typed property instead of parsing a rendered label.",
            },
            {
                "title": "Choose by cardinality",
                "idea": "One line of text: TextField. One choice from a short list: ComboBox or radio buttons. Many choices: ListView. Boolean: CheckBox.",
            },
            {
                "title": "Labels are instructions",
                "idea": "A visible label connected to its control tells every user what the field means. Placeholder text disappears and is not a label.",
            },
            {
                "title": "Validation belongs at the boundary",
                "idea": "Convert and validate when the form is submitted. Keep the raw control value until the conversion succeeds.",
            },
            {
                "title": "Media has a lifecycle",
                "idea": "A <code>MediaPlayer</code> can play, pause, and stop. Release it when the view closes; a player holds native resources.",
            },
        ],
        "example": {
            "title": "ProfileForm: three controls, one read",
            "intro": "The form does not react on every keystroke. It reads all controls in one place when the user submits.",
            "code": [
                "public class ProfileForm extends Application {",
                "    @Override",
                "    public void start(Stage stage) {",
                "        TextField name = new TextField();",
                "        CheckBox notify = new CheckBox(\"Email me\");",
                "        ComboBox<String> plan = new ComboBox<>();",
                "        plan.getItems().addAll(\"Free\", \"Pro\", \"Team\");",
                "        plan.setValue(\"Free\");",
                "        Button save = new Button(\"Save\");",
                "        Label result = new Label();",
                "        save.setOnAction(event -> {",
                "            String message = name.getText().trim()",
                "                + \" / \" + plan.getValue()",
                "                + \" / notify=\" + notify.isSelected();",
                "            result.setText(message);",
                "        });",
                "        GridPane grid = new GridPane();",
                "        grid.setHgap(8);",
                "        grid.setVgap(8);",
                "        grid.addRow(0, new Label(\"Name\"), name);",
                "        grid.addRow(1, new Label(\"Plan\"), plan);",
                "        grid.add(notify, 0, 2, 2, 1);",
                "        grid.addRow(3, save, result);",
                "        stage.setScene(new Scene(grid, 380, 200));",
                "        stage.show();",
                "    }",
                "}",
            ],
            "steps": [
                {
                    "label": "Create the controls",
                    "note": "Each control is created with its own type. The compiler now helps: <code>plan.getValue()</code> is a String, not an Object.",
                },
                {
                    "label": "Seed defaults",
                    "note": "A ComboBox with a selected value cannot return null on the first submit. Defaults are part of the form contract.",
                },
                {
                    "label": "Attach the action",
                    "note": "Save is the only handler. It reads the three controls together, which keeps the snapshot consistent.",
                },
                {
                    "label": "Normalize input",
                    "note": "<code>trim()</code> removes accidental surrounding spaces. Validation would happen next, before the value reaches storage.",
                },
                {
                    "label": "Lay out by role",
                    "note": "<code>addRow</code> keeps label and field aligned. The checkbox spans both columns because it has no separate visible label.",
                },
            ],
            "predict": {
                "prompt": "What does <code>plan.getValue()</code> return if the ComboBox is cleared with <code>getItems().clear()</code>?",
                "answer": "It can return null. A cleared selection is not the same as the default value, so submit code must handle null before using the string.",
            },
        },
        "engine": "layout",
        "spec": {
            "hint": "Change the manager and the gaps. A form that reads well in GridPane can fall apart in FlowPane when the window narrows.",
            "mode": "grid",
            "children": ["Name", "Plan", "Notify", "Save", "Result"],
            "gap": 8,
            "padding": 16,
        },
        "parsons": {
            "prompt": "Rebuild the form so one action reads all three controls.",
            "lines": [
                "TextField name = new TextField();",
                "CheckBox notify = new CheckBox(\"Email me\");",
                "ComboBox<String> plan = new ComboBox<>();",
                "plan.getItems().addAll(\"Free\", \"Pro\", \"Team\");",
                "plan.setValue(\"Free\");",
                "Button save = new Button(\"Save\");",
                "save.setOnAction(event -> {",
                "    String message = name.getText().trim() + \" / \" + plan.getValue();",
                "    System.out.println(message + \" / notify=\" + notify.isSelected());",
                "});",
            ],
            "solution": [0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
            "seed": 16,
            "why": "All controls exist and have defaults before the handler that reads them is registered.",
        },
        "quiz": [
            {
                "q": "Which control best represents a yes-or-no preference?",
                "options": ["TextField", "CheckBox", "ListView", "Slider"],
                "a": 1,
                "why": "A CheckBox has a boolean selected property, which matches a binary choice.",
            },
            {
                "q": "Why is placeholder text a poor substitute for a visible label?",
                "options": [
                    "It is rendered too slowly",
                    "It disappears after typing and may not be announced",
                    "JavaFX does not support placeholders",
                    "It cannot contain spaces",
                ],
                "a": 1,
                "why": "A label remains visible and can be associated with the control for assistive technology.",
            },
            {
                "q": "What should submit code do before using <code>plan.getValue()</code>?",
                "options": [
                    "Convert it to an int",
                    "Check for null and validate the selection",
                    "Call <code>show()</code> again",
                    "Clear the combo box",
                ],
                "a": 1,
                "why": "A control can have no selection, so null handling is part of the form boundary.",
            },
        ],
        "artifact": {
            "name": "SettingsForm.java",
            "description": "A settings form with a name field, a plan choice, and a notifications checkbox. Validate that the name is not blank before showing the summary.",
            "code": """import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SettingsForm extends Application {
    @Override
    public void start(Stage stage) {
        TextField name = new TextField();
        ComboBox<String> plan = new ComboBox<>();
        plan.getItems().addAll("Free", "Pro", "Team");
        plan.setValue("Free");
        CheckBox notify = new CheckBox("Email me");
        Button save = new Button("Save");
        Label result = new Label("Not saved");

        save.setOnAction(event -> {
            String candidate = name.getText().trim();
            // TODO: reject a blank candidate and show a useful message instead.
            result.setText(candidate + " / " + plan.getValue() + " / notify=" + notify.isSelected());
        });

        GridPane grid = new GridPane();
        grid.setHgap(8);
        grid.setVgap(8);
        grid.addRow(0, new Label("Name"), name);
        grid.addRow(1, new Label("Plan"), plan);
        grid.add(notify, 0, 2, 2, 1);
        grid.addRow(3, save, result);

        stage.setScene(new Scene(grid, 380, 200));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_16",
        },
        "reflection": "Name one control in a form you use often that stores the wrong type for its job. Explain what type would be a better match and why.",
        "studio": {
            "title": "Form layout stress test",
            "intro": "Five children stand in for a form. Try each layout manager and decide which one keeps the label-field relationship legible.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/foundations.md#chapter-16-controls-layout-and-media",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_16",
        },
    },
    {
        "n": 17,
        "title": "Binary I/O",
        "part": "Part 4 · Algorithms and structures",
        "tagline": "Text I/O stores characters; binary I/O stores typed values in a defined byte order, which is smaller and faster but unreadable without the format.",
        "minutes": 45,
        "tags": ["binary", "stream", "fileinputstream", "dataoutputstream", "serialization", "bytes"],
        "objectives": [
            "Choose between text I/O and binary I/O for a given persistence task.",
            "Trace how a typed value becomes bytes and how a reader reconstructs it.",
            "Explain why the writer and reader must agree on order, type, and count.",
        ],
        "subgoals": [
            {
                "title": "A stream is an ordered byte channel",
                "idea": "<code>FileInputStream</code> reads bytes; <code>FileOutputStream</code> writes bytes. Higher-level streams wrap them to add meaning.",
            },
            {
                "title": "Data streams preserve Java types",
                "idea": "<code>DataOutputStream.writeInt</code> writes four bytes. <code>DataInputStream.readInt</code> reads four bytes and rebuilds the int.",
            },
            {
                "title": "Order is the schema",
                "idea": "A binary file has no column names. If the writer writes id then score, the reader must read id then score. Mismatch produces plausible garbage.",
            },
            {
                "title": "Buffer to reduce calls",
                "idea": "A <code>BufferedInputStream</code> or <code>BufferedOutputStream</code> groups small operations. Use try-with-resources so the outermost close flushes and releases the file.",
            },
            {
                "title": "EOF is a signal, not a value",
                "idea": "<code>readInt</code> throws <code>EOFException</code> when no complete int remains. That exception is a normal way to end a read loop, not necessarily a bug.",
            },
        ],
        "example": {
            "title": "ScoreFile: write two ints, read them back",
            "intro": "The file is eight bytes: two four-byte ints. The offset is the only cursor; there are no separators.",
            "code": [
                "try (DataOutputStream out = new DataOutputStream(",
                "        new BufferedOutputStream(new FileOutputStream(\"scores.bin\")))) {",
                "    out.writeInt(72);",
                "    out.writeInt(85);",
                "}",
                "try (DataInputStream in = new DataInputStream(",
                "        new BufferedInputStream(new FileInputStream(\"scores.bin\")))) {",
                "    int first = in.readInt();",
                "    int second = in.readInt();",
                "    System.out.println(first + \",\" + second);",
                "}",
            ],
            "steps": [
                {
                    "label": "Open the chain",
                    "note": "The file stream moves bytes; the data stream adds the int format; the buffer reduces system calls. Close the outermost wrapper.",
                },
                {
                    "label": "Write the first int",
                    "note": "72 becomes four bytes in big-endian order by default for DataOutputStream.",
                },
                {
                    "label": "Write the second int",
                    "note": "The offset is now eight. The file contains no field names, only the two values in order.",
                },
                {
                    "label": "Read in the same order",
                    "note": "The first read consumes bytes 0–3 and returns 72. Reading an int first would reinterpret the byte pattern, not recover the label.",
                },
                {
                    "label": "Print the pair",
                    "note": "The reconstructed values match because writer and reader agreed on type and order.",
                },
            ],
            "predict": {
                "prompt": "What happens if the reader calls <code>readLong()</code> first on this file?",
                "answer": "It consumes eight bytes and builds one long from the two ints. The bytes are valid, but the meaning is wrong. The second read then hits end of file.",
            },
        },
        "engine": "state",
        "spec": {
            "hint": "Watch the byte offset. Every typed write advances it by the size of that type.",
            "code": [
                "DataOutputStream out = new DataOutputStream(new FileOutputStream(\"scores.bin\"));",
                "out.writeInt(72);",
                "out.writeInt(85);",
                "out.close();",
            ],
            "vars": ["offset", "lastValue", "fileState"],
            "steps": [
                {
                    "line": 0,
                    "vars": {"offset": "0", "lastValue": "—", "fileState": "open"},
                    "note": "The stream is open and the file is truncated. No bytes have been written.",
                },
                {
                    "line": 1,
                    "vars": {"offset": "4", "lastValue": "72", "fileState": "open"},
                    "note": "writeInt emits four bytes. The offset advances to 4.",
                },
                {
                    "line": 2,
                    "vars": {"offset": "8", "lastValue": "85", "fileState": "open"},
                    "note": "A second four-byte value lands at bytes 4–7. The file is now eight bytes.",
                },
                {
                    "line": 3,
                    "vars": {"offset": "8", "lastValue": "85", "fileState": "closed"},
                    "note": "close flushes buffered bytes and releases the handle. The file is complete.",
                },
            ],
        },
        "parsons": {
            "prompt": "Rebuild the write side of the score file using try-with-resources.",
            "lines": [
                "try (DataOutputStream out = new DataOutputStream(",
                "        new BufferedOutputStream(new FileOutputStream(\"scores.bin\")))) {",
                "    out.writeInt(72);",
                "    out.writeInt(85);",
                "}",
            ],
            "solution": [0, 1, 2, 3, 4],
            "seed": 17,
            "why": "The resource declaration opens the stream chain; the body writes both ints; the closing brace closes the outermost stream.",
        },
        "quiz": [
            {
                "q": "How many bytes does <code>writeInt(72)</code> produce?",
                "options": ["1", "2", "4", "8"],
                "a": 2,
                "why": "A Java int is 32 bits, and DataOutputStream writes a fixed four-byte representation.",
            },
            {
                "q": "Why must the reader know the write order?",
                "options": [
                    "The JVM stores field names in the file",
                    "A binary file has no separators or column names",
                    "Readers always read backwards",
                    "Text files cannot be read",
                ],
                "a": 1,
                "why": "Order and type are the schema; without them the bytes are just a sequence.",
            },
            {
                "q": "What is the right response to <code>EOFException</code> in a loop that reads a known-unknown count of records?",
                "options": [
                    "Ignore all data",
                    "Treat it as the normal end of the record stream",
                    "Convert the file to text",
                    "Reopen the file for writing",
                ],
                "a": 1,
                "why": "When no explicit count is stored, end of file is the terminator. Distinguish it from a partial or corrupt record.",
            },
        ],
        "artifact": {
            "name": "BinaryLedger.java",
            "description": "A record writer and reader. Store an id, a score, and a name length-prefixed string; stop reading cleanly at end of file.",
            "code": """import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BinaryLedger {
    public static void main(String[] args) throws IOException {
        try (DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream("ledger.bin")))) {
            write(out, 1, 72, "Ada");
            write(out, 2, 85, "Grace");
        }

        try (DataInputStream in = new DataInputStream(
                new BufferedInputStream(new FileInputStream("ledger.bin")))) {
            while (true) {
                try {
                    System.out.println(read(in));
                } catch (EOFException end) {
                    break;
                }
            }
        }
    }

    private static void write(DataOutputStream out, int id, int score, String name) throws IOException {
        out.writeInt(id);
        out.writeInt(score);
        out.writeUTF(name);
    }

    private static String read(DataInputStream in) throws IOException {
        int id = in.readInt();
        int score = in.readInt();
        String name = in.readUTF();
        return id + ":" + score + ":" + name;
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_17",
        },
        "reflection": "A colleague proposes storing money as a binary <code>double</code>. Give one reason that is acceptable and one reason a scaled integer or decimal type may be safer.",
        "studio": {
            "title": "Byte offset ledger",
            "intro": "Each write advances the offset by the width of its type. Use the steps to check the arithmetic before the file grows.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/io-generics-graphs.md#chapter-17-binary-io",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_17",
        },
    },
    {
        "n": 18,
        "title": "Recursion",
        "part": "Part 4 · Algorithms and structures",
        "tagline": "Recursion solves a problem by delegating a smaller copy of the same problem, then combining the result on the way back.",
        "minutes": 45,
        "tags": ["recursion", "base case", "call stack", "factorial", "fibonacci", "backtracking"],
        "objectives": [
            "Write a recursive method with an explicit base case and a smaller argument.",
            "Trace the call stack and the return values for a small input.",
            "Explain why naive Fibonacci recursion repeats work and how to avoid it.",
        ],
        "subgoals": [
            {
                "title": "Base case stops the descent",
                "idea": "Every recursive method needs an input it can answer without calling itself. Without a base case the stack grows until it overflows.",
            },
            {
                "title": "The recursive case must shrink",
                "idea": "<code>factorial(n)</code> calls <code>factorial(n - 1)</code>. If the argument does not move toward the base case, the method never terminates.",
            },
            {
                "title": "Work happens on the way back",
                "idea": "The multiplication in <code>n * factorial(n - 1)</code> waits until the inner call returns. The stack holds the pending work.",
            },
            {
                "title": "Each frame has its own locals",
                "idea": "The <code>n</code> in one frame is a different variable from the <code>n</code> in the caller. That separation is what makes the trace readable.",
            },
            {
                "title": "Overlap is the performance trap",
                "idea": "Naive Fibonacci recomputes the same inputs exponentially many times. Memoization or an iterative loop removes the repetition.",
            },
        ],
        "example": {
            "title": "FactorialTrace: five frames, one product",
            "intro": "Read the stack from bottom to top on the way down, then read the returned values from top to bottom.",
            "code": [
                "public static int factorial(int n) {",
                "    if (n <= 1) {",
                "        return 1;",
                "    }",
                "    return n * factorial(n - 1);",
                "}",
            ],
            "steps": [
                {
                    "label": "Call with 4",
                    "note": "<code>n</code> is 4, so the base case does not fire. The method must evaluate <code>4 * factorial(3)</code>.",
                },
                {
                    "label": "Descend to 3",
                    "note": "A new frame holds <code>n = 3</code>. The caller's frame stays alive with a pending multiplication.",
                },
                {
                    "label": "Descend to 2 and 1",
                    "note": "Two more frames are created. At <code>n = 1</code> the base case returns immediately.",
                },
                {
                    "label": "Unwind",
                    "note": "1 returns to the frame with 2, which returns 2; that returns to 3, which returns 6; that returns to 4.",
                },
                {
                    "label": "Final value",
                    "note": "The outermost call returns 24. The stack is empty and every pending multiplication has been applied.",
                },
            ],
            "predict": {
                "prompt": "How many calls to <code>factorial</code> occur for <code>factorial(4)</code>, including the first?",
                "answer": "Five: 4, 3, 2, 1, and the base-case call at 1. The sequence is one call per integer from 4 down to 1.",
            },
        },
        "engine": "trace",
        "spec": {
            "hint": "Step down the stack, then step back up. Watch which frame owns each value of n.",
            "code": [
                "public static int factorial(int n) {",
                "    if (n <= 1) {",
                "        return 1;",
                "    }",
                "    return n * factorial(n - 1);",
                "}",
            ],
            "steps": [
                {
                    "line": 0,
                    "note": "Call factorial(3). A frame is pushed.",
                    "stack": [{"name": "factorial", "vars": {"n": "3"}}],
                    "output": "",
                },
                {
                    "line": 4,
                    "note": "3 is greater than 1, so evaluate 3 * factorial(2). The frame waits.",
                    "stack": [
                        {"name": "factorial", "vars": {"n": "3", "waiting": "3 * ?"}},
                        {"name": "factorial", "vars": {"n": "2"}},
                    ],
                    "output": "",
                },
                {
                    "line": 4,
                    "note": "2 is greater than 1, so evaluate 2 * factorial(1). Another frame waits.",
                    "stack": [
                        {"name": "factorial", "vars": {"n": "3", "waiting": "3 * ?"}},
                        {"name": "factorial", "vars": {"n": "2", "waiting": "2 * ?"}},
                        {"name": "factorial", "vars": {"n": "1"}},
                    ],
                    "output": "",
                },
                {
                    "line": 2,
                    "note": "The base case returns 1. The frame with n = 1 disappears.",
                    "stack": [
                        {"name": "factorial", "vars": {"n": "3", "waiting": "3 * ?"}},
                        {"name": "factorial", "vars": {"n": "2", "waiting": "2 * ?"}},
                    ],
                    "output": "",
                },
                {
                    "line": 4,
                    "note": "2 * 1 = 2 returns to the caller. The next frame can finish.",
                    "stack": [{"name": "factorial", "vars": {"n": "3", "waiting": "3 * ?"}}],
                    "output": "",
                },
                {
                    "line": 4,
                    "note": "3 * 2 = 6 returns to the original call. The stack is empty.",
                    "stack": [],
                    "output": "6",
                },
            ],
        },
        "parsons": {
            "prompt": "Rebuild a recursive factorial with a correct base case.",
            "lines": [
                "public static int factorial(int n) {",
                "    if (n <= 1) {",
                "        return 1;",
                "    }",
                "    return n * factorial(n - 1);",
                "}",
            ],
            "solution": [0, 1, 2, 3, 4, 5],
            "seed": 18,
            "why": "The base case returns before the recursive call, and the recursive argument moves toward that base case.",
        },
        "quiz": [
            {
                "q": "What is the base case in the factorial method?",
                "options": ["n > 1", "n <= 1 returning 1", "n * factorial(n - 1)", "The first call"],
                "a": 1,
                "why": "The branch that returns without another call stops the recursion.",
            },
            {
                "q": "Why does naive Fibonacci recursion do repeated work?",
                "options": [
                    "It sorts the inputs first",
                    "The same subproblems are recomputed in separate branches",
                    "Java caches every method call automatically",
                    "It uses too many loops",
                ],
                "a": 1,
                "why": "fib(n-2) is reached through multiple paths, so values are recomputed instead of reused.",
            },
            {
                "q": "What happens if the recursive argument does not shrink?",
                "options": [
                    "The method runs faster",
                    "The base case is reached sooner",
                    "The call stack grows until a StackOverflowError",
                    "Java converts it to a loop",
                ],
                "a": 2,
                "why": "Each call adds a frame; without progress toward a base case, the stack exhausts its limit.",
            },
        ],
        "artifact": {
            "name": "RecursiveReport.java",
            "description": "A recursive digit-sum method plus a call counter. Compare the count with the number of digits and explain the relationship.",
            "code": """public class RecursiveReport {
    private static int calls = 0;

    public static void main(String[] args) {
        calls = 0;
        int result = digitSum(4705);
        System.out.println("sum=" + result + " calls=" + calls);
    }

    public static int digitSum(int n) {
        calls++;
        int value = Math.abs(n);
        if (value < 10) {
            return value;
        }
        // TODO: return the last digit plus the digitSum of the remaining digits.
        return 0;
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_18",
        },
        "reflection": "Explain in one paragraph why the call stack is a good model for recursion but a dangerous place to store large data.",
        "studio": {
            "title": "Call-stack walker",
            "intro": "The playground keeps every open frame visible. Step forward to descend and watch the pending multiplication survive.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/io-generics-graphs.md#chapter-18-recursion",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_18",
        },
    },
    {
        "n": 19,
        "title": "Generics",
        "part": "Part 4 · Algorithms and structures",
        "tagline": "Generics move type checks from runtime to compile time, then erase most of the evidence before the program runs.",
        "minutes": 50,
        "tags": ["generics", "type parameter", "bounded", "wildcard", "erasure", "compile-time"],
        "objectives": [
            "Declare a generic class or method and explain what the type parameter promises.",
            "Use a bounded type parameter when the implementation calls a method on the element.",
            "Distinguish compile-time type safety from runtime invariant checks.",
        ],
        "subgoals": [
            {
                "title": "A type parameter is a contract",
                "idea": "<code>Box<T></code> says the box holds one T. The caller chooses T once, and every get and put is checked against it.",
            },
            {
                "title": "Bounds unlock operations",
                "idea": "<code>T extends Comparable<T></code> lets the method call <code>compareTo</code>. Without the bound, T could be any type and the call would not compile.",
            },
            {
                "title": "Wildcards describe flexibility",
                "idea": "<code>? extends Number</code> reads numbers; <code>? super Integer</code> writes integers. The direction follows the producer/consumer rule.",
            },
            {
                "title": "Erasure removes the parameter",
                "idea": "At runtime, <code>List&lt;String&gt;</code> and <code>List&lt;Integer&gt;</code> are both <code>List</code>. You cannot overload on type arguments or create a generic array directly.",
            },
            {
                "title": "Invariants still need checks",
                "idea": "The compiler cannot prove that a capacity is positive or that a list contains no nulls. Generic safety does not replace boundary validation.",
            },
        ],
        "example": {
            "title": "Pick: the largest of three comparable values",
            "intro": "The bound is the whole method. Remove it and the comparison stops compiling.",
            "code": [
                "public static <T extends Comparable<T>> T largest(T a, T b) {",
                "    return a.compareTo(b) >= 0 ? a : b;",
                "}",
                "",
                "String word = largest(\"pear\", \"apple\");",
                "Integer count = largest(12, 7);",
            ],
            "steps": [
                {
                    "label": "Declare the type parameter",
                    "note": "<code>&lt;T extends Comparable&lt;T&gt;&gt;</code> appears before the return type. It is visible to the parameter list and the body.",
                },
                {
                    "label": "Use the bound",
                    "note": "Because T is comparable to itself, <code>a.compareTo(b)</code> is legal.",
                },
                {
                    "label": "Infer at the call site",
                    "note": "The compiler sees two Strings and chooses T = String. No cast is needed on the result.",
                },
                {
                    "label": "Reuse with another type",
                    "note": "The same method works for Integer because Integer implements Comparable&lt;Integer&gt;.",
                },
                {
                    "label": "Check the boundary",
                    "note": "If nulls are not allowed, the method must reject them. Erasure does not insert that check.",
                },
            ],
            "predict": {
                "prompt": "Why does <code>largest(1, \"two\")</code> fail to compile instead of throwing at runtime?",
                "answer": "Type inference must choose one T. Integer and String have no common comparable type, so the compiler rejects the call before bytecode exists.",
            },
        },
        "engine": "contract",
        "spec": {
            "hint": "The generic type protects element types. The runtime clauses still guard the collection boundary.",
            "signature": "public final class BoundedBuffer<E> { void add(E item); E remove(); }",
            "clauses": [
                {
                    "kind": "Precondition",
                    "text": "capacity >= 1",
                    "check": {"type": "positive", "field": "capacity"},
                },
                {
                    "kind": "Invariant",
                    "text": "0 <= size <= capacity",
                    "check": {"type": "manual", "field": "sizeFits"},
                },
                {
                    "kind": "Invariant",
                    "text": "no stored element is null",
                    "check": {"type": "manual", "field": "noNulls"},
                },
                {
                    "kind": "Postcondition",
                    "text": "size equals the number of stored elements",
                    "check": {"type": "manual", "field": "sizeMatches"},
                },
            ],
            "cases": [
                {
                    "label": "Valid buffer",
                    "inputs": {"capacity": 3, "size": 2, "sizeFits": True, "noNulls": True, "sizeMatches": True},
                    "expected": True,
                    "note": "The type parameter E is checked by the compiler; the size and null rules are runtime invariants.",
                },
                {
                    "label": "Zero capacity",
                    "inputs": {"capacity": 0, "size": 0, "sizeFits": True, "noNulls": True, "sizeMatches": True},
                    "expected": False,
                    "note": "A zero-capacity buffer violates its precondition even though no element is stored.",
                },
                {
                    "label": "Over capacity",
                    "inputs": {"capacity": 3, "size": 4, "sizeFits": False, "noNulls": True, "sizeMatches": True},
                    "expected": False,
                    "note": "The size invariant fails; the fix belongs in the add method, not in the caller.",
                },
                {
                    "label": "Null element",
                    "inputs": {"capacity": 3, "size": 1, "sizeFits": True, "noNulls": False, "sizeMatches": True},
                    "expected": False,
                    "note": "Generics permit a null reference, so the class must reject it explicitly if null is not part of the contract.",
                },
            ],
        },
        "parsons": {
            "prompt": "Rebuild the bounded generic method that returns the larger of two values.",
            "lines": [
                "public static <T extends Comparable<T>> T largest(T a, T b) {",
                "    if (a.compareTo(b) >= 0) {",
                "        return a;",
                "    }",
                "    return b;",
                "}",
            ],
            "solution": [0, 1, 2, 3, 4, 5],
            "seed": 19,
            "why": "The bound makes compareTo available; both branches return a value of the same type T.",
        },
        "quiz": [
            {
                "q": "What does <code>T extends Comparable<T></code> guarantee inside the method?",
                "options": [
                    "T is a String",
                    "Values of type T can be compared to other T values",
                    "T is mutable",
                    "T has a no-argument constructor",
                ],
                "a": 1,
                "why": "The bound exposes the compareTo method for values of the same type parameter.",
            },
            {
                "q": "What happens to <code>List&lt;String&gt;</code> and <code>List&lt;Integer&gt;</code> at runtime?",
                "options": [
                    "They remain distinct classes",
                    "Both are erased to List",
                    "The JVM rejects both",
                    "They become arrays",
                ],
                "a": 1,
                "why": "Type erasure removes the type arguments, which is why runtime reflection cannot distinguish them.",
            },
            {
                "q": "Which wildcard is appropriate for a method that only reads numbers from a list?",
                "options": ["? super Integer", "? extends Number", "? implements Number", "Object"],
                "a": 1,
                "why": "An extends wildcard is the producer form: the method can read Number values from the collection.",
            },
        ],
        "artifact": {
            "name": "Pair.java",
            "description": "A generic immutable pair with a swapped view. Add a static factory and keep both type parameters independent.",
            "code": """public final class Pair<A, B> {
    private final A first;
    private final B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A first() {
        return first;
    }

    public B second() {
        return second;
    }

    public Pair<B, A> swap() {
        // TODO: return a new Pair with the components exchanged.
        return null;
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_19",
        },
        "reflection": "Explain why a generic container cannot enforce a non-null element rule by itself, and describe where that rule should live.",
        "studio": {
            "title": "Compile-time versus runtime",
            "intro": "The contract tester separates what the compiler proves about E from the invariants the class must still check.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/io-generics-graphs.md#chapter-19-generics",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_19",
        },
    },
    {
        "n": 20,
        "title": "Lists, Stacks, Queues, and Priority Queues",
        "part": "Part 4 · Algorithms and structures",
        "tagline": "The collection interface tells you which end is legal; the implementation tells you what that operation costs.",
        "minutes": 50,
        "tags": ["list", "stack", "queue", "deque", "priority queue", "linkedlist", "arraydeque"],
        "objectives": [
            "Match a problem to a collection interface by its access pattern.",
            "Trace add, remove, and peek operations on a list, stack, and queue.",
            "Explain why a priority queue orders by priority rather than insertion time.",
        ],
        "subgoals": [
            {
                "title": "Interfaces name the operations",
                "idea": "<code>List</code> supports indexed access. <code>Deque</code> supports both ends. <code>Queue</code> supports offer, poll, and peek. Choose the interface first.",
            },
            {
                "title": "Stack is LIFO",
                "idea": "The most recently pushed item is the first popped. Undo history and depth-first search both rely on this order.",
            },
            {
                "title": "Queue is FIFO",
                "idea": "The earliest offered item is the first polled. Breadth-first search and work queues rely on this order.",
            },
            {
                "title": "Priority is not arrival time",
                "idea": "A <code>PriorityQueue</code> returns the smallest element according to its comparator, regardless of when it was inserted.",
            },
            {
                "title": "Implementation changes cost",
                "idea": "Indexed access is O(1) in <code>ArrayList</code> and O(n) in <code>LinkedList</code>. Add at the front is O(n) in ArrayList and O(1) in LinkedList or ArrayDeque.",
            },
        ],
        "example": {
            "title": "TaskLane: one deque, two policies",
            "intro": "The same data supports a queue when you add at the tail and a stack when you add at the head.",
            "code": [
                "Deque<String> lane = new ArrayDeque<>();",
                "lane.addLast(\"compile\");",
                "lane.addLast(\"test\");",
                "lane.addFirst(\"urgent-fix\");",
                "String next = lane.removeFirst();",
                "String tail = lane.peekLast();",
            ],
            "steps": [
                {
                    "label": "Empty lane",
                    "note": "ArrayDeque is chosen because both ends are used and null elements are forbidden.",
                },
                {
                    "label": "Append two tasks",
                    "note": "compile is first, test is last. This is the queue order.",
                },
                {
                    "label": "Push to the front",
                    "note": "urgent-fix becomes first. The deque now supports stack-like priority without a second structure.",
                },
                {
                    "label": "Remove the front",
                    "note": "<code>removeFirst</code> returns urgent-fix and throws if empty. <code>pollFirst</code> would return null instead.",
                },
                {
                    "label": "Inspect the tail",
                    "note": "<code>peekLast</code> returns test without removing it. The lane still holds compile and test.",
                },
            ],
            "predict": {
                "prompt": "After the steps shown, what is the lane from front to back?",
                "answer": "compile then test. urgent-fix was removed, and peeking does not change the structure.",
            },
        },
        "engine": "structure",
        "spec": {
            "hint": "Step through the deque operations. The front is on the left and the tail is on the right.",
            "mode": "list",
            "frames": [
                {
                    "label": "Empty deque",
                    "cells": [],
                    "highlight": [],
                    "note": "No elements. Both ends are the same insertion point.",
                },
                {
                    "label": "addLast(compile)",
                    "cells": ["compile"],
                    "highlight": [0],
                    "note": "The first element is both head and tail.",
                },
                {
                    "label": "addLast(test)",
                    "cells": ["compile", "test"],
                    "highlight": [1],
                    "note": "Queue order: the oldest element is at the front.",
                },
                {
                    "label": "addFirst(urgent-fix)",
                    "cells": ["urgent-fix", "compile", "test"],
                    "highlight": [0],
                    "note": "Adding at the head gives stack-like priority to the newest urgent item.",
                },
                {
                    "label": "removeFirst()",
                    "cells": ["compile", "test"],
                    "highlight": [],
                    "note": "urgent-fix is returned and removed. The remaining order is unchanged.",
                },
            ],
        },
        "parsons": {
            "prompt": "Rebuild a deque that behaves as a queue: append two tasks, then remove the oldest.",
            "lines": [
                "Deque<String> lane = new ArrayDeque<>();",
                "lane.addLast(\"compile\");",
                "lane.addLast(\"test\");",
                "String next = lane.removeFirst();",
                "System.out.println(next);",
            ],
            "solution": [0, 1, 2, 3, 4],
            "seed": 20,
            "why": "Adding at the tail and removing from the head is FIFO order.",
        },
        "quiz": [
            {
                "q": "Which structure returns the most recently added element first?",
                "options": ["Queue", "Stack", "PriorityQueue", "ArrayList"],
                "a": 1,
                "why": "A stack is last-in, first-out.",
            },
            {
                "q": "What does <code>PriorityQueue.poll()</code> return when the queue is not empty?",
                "options": [
                    "The oldest inserted element",
                    "The smallest element by the comparator",
                    "A random element",
                    "The largest element always",
                ],
                "a": 1,
                "why": "PriorityQueue is a min-heap by default; a custom comparator can define another priority.",
            },
            {
                "q": "Why prefer <code>ArrayDeque</code> over <code>LinkedList</code> for a queue in most code?",
                "options": [
                    "It allows nulls",
                    "It has lower per-operation overhead and better locality",
                    "It is the only Queue implementation",
                    "It supports indexed access",
                ],
                "a": 1,
                "why": "ArrayDeque avoids node allocation per element and is the usual default for deque behavior.",
            },
        ],
        "artifact": {
            "name": "WorkLane.java",
            "description": "A small task lane with add-urgent, add-normal, and next. Use one deque and document which end each operation uses.",
            "code": """import java.util.ArrayDeque;
import java.util.Deque;

public class WorkLane {
    private final Deque<String> lane = new ArrayDeque<>();

    public void addNormal(String task) {
        lane.addLast(task);
    }

    public void addUrgent(String task) {
        lane.addFirst(task);
    }

    public String next() {
        // TODO: return the next task without crashing on an empty lane.
        return lane.removeFirst();
    }

    public int size() {
        return lane.size();
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_20",
        },
        "reflection": "Pick one task from your week and classify its access pattern. Which interface would you choose, and which implementation would make the common operation cheap?",
        "studio": {
            "title": "Deque at both ends",
            "intro": "The list view exposes the front and tail. Add and remove from each end to see when the structure behaves as a stack and when it behaves as a queue.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/io-generics-graphs.md#chapter-20-lists-stacks-queues-and-priority-queues",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_20",
        },
    },
    {
        "n": 21,
        "title": "Sets and Maps",
        "part": "Part 4 · Algorithms and structures",
        "tagline": "A set answers membership; a map answers lookup. Both buy speed by giving up positional order.",
        "minutes": 45,
        "tags": ["set", "map", "hashset", "treemap", "frequency", "membership", "lookup"],
        "objectives": [
            "Choose a set or map when the problem is membership or keyed lookup.",
            "Use a map to accumulate frequencies in one pass.",
            "Explain the trade-off between hash-based and sorted collections.",
        ],
        "subgoals": [
            {
                "title": "Sets remove duplicates",
                "idea": "Adding the same value twice leaves one element. A <code>HashSet</code> does this in expected O(1); a <code>TreeSet</code> keeps sorted order in O(log n).",
            },
            {
                "title": "Maps store a value per key",
                "idea": "<code>Map&lt;K,V&gt;</code> is not a <code>Collection</code>. It has keySet, values, and entrySet views, each with its own iteration order.",
            },
            {
                "title": "getOrDefault removes a branch",
                "idea": "<code>counts.merge(word, 1, Integer::sum)</code> expresses increment without an explicit null check.",
            },
            {
                "title": "Keys need stable equality",
                "idea": "A hash-based map relies on <code>equals</code> and <code>hashCode</code>. A mutable key that changes after insertion can become unreachable.",
            },
            {
                "title": "Sorted is a cost and a feature",
                "idea": "A <code>TreeMap</code> supports range and order queries that a HashMap cannot answer without sorting. Choose by the query, not by habit.",
            },
        ],
        "example": {
            "title": "WordTally: one pass, one map",
            "intro": "Every word is a key. The merge operation handles the first occurrence and every later occurrence with the same expression.",
            "code": [
                "String[] words = {\"stream\", \"file\", \"stream\", \"buffer\"};",
                "Map<String, Integer> counts = new HashMap<>();",
                "for (String word : words) {",
                "    counts.merge(word, 1, Integer::sum);",
                "}",
                "List<Map.Entry<String, Integer>> ranked =",
                "    new ArrayList<>(counts.entrySet());",
                "ranked.sort(Map.Entry.<String, Integer>comparingByValue().reversed());",
                "System.out.println(ranked);",
            ],
            "steps": [
                {
                    "label": "Input array",
                    "note": "The array may contain duplicates. The map, not the array, will enforce uniqueness of keys.",
                },
                {
                    "label": "Empty map",
                    "note": "No entries yet. The key type is String and the value type is Integer.",
                },
                {
                    "label": "Merge each word",
                    "note": "First occurrence: store 1. Later occurrence: combine old value with 1 using sum.",
                },
                {
                    "label": "Copy entries",
                    "note": "The entry set is a view. Copying it into a list allows sorting without modifying the map.",
                },
                {
                    "label": "Sort by count",
                    "note": "The comparator orders by value descending. Ties keep an arbitrary order because HashMap has none.",
                },
            ],
            "predict": {
                "prompt": "If the sort used <code>comparingByKey()</code> instead, which word would appear first?",
                "answer": "buffer, because keys would be sorted lexicographically. The frequency would no longer decide the order.",
            },
        },
        "engine": "data",
        "spec": {
            "hint": "Toggle the pipeline steps. The rows are a precomputed tally; sorting and limiting answer different questions.",
            "columns": ["word", "count"],
            "rows": [
                ["stream", 42],
                ["buffer", 31],
                ["file", 27],
                ["reader", 19],
                ["writer", 17],
                ["socket", 12],
                ["thread", 9],
                ["lock", 6],
            ],
            "operations": [
                {"id": "sort", "label": "Sort by count, descending", "type": "sort", "column": "count", "dir": "desc"},
                {"id": "top", "label": "Keep the top three", "type": "limit", "n": 3},
                {"id": "active", "label": "Keep counts at least 20", "type": "filter", "column": "count", "op": ">=", "value": 20},
            ],
            "defaults": ["sort", "top"],
        },
        "parsons": {
            "prompt": "Rebuild the frequency count and the descending sort by value.",
            "lines": [
                "Map<String, Integer> counts = new HashMap<>();",
                "for (String word : words) {",
                "    counts.merge(word, 1, Integer::sum);",
                "}",
                "List<Map.Entry<String, Integer>> ranked = new ArrayList<>(counts.entrySet());",
                "ranked.sort(Map.Entry.<String, Integer>comparingByValue().reversed());",
                "System.out.println(ranked);",
            ],
            "solution": [0, 1, 2, 3, 4, 5, 6],
            "seed": 21,
            "why": "The map is filled first; only then can its entries be copied and sorted.",
        },
        "quiz": [
            {
                "q": "Which collection should answer whether an email address has been seen before?",
                "options": ["List", "Set", "Queue", "Map from address to itself"],
                "a": 1,
                "why": "The question is membership with no associated value, which is exactly a set.",
            },
            {
                "q": "What does <code>counts.merge(word, 1, Integer::sum)</code> do on the first occurrence?",
                "options": [
                    "Throws a NullPointerException",
                    "Stores 1 for that key",
                    "Stores 0",
                    "Removes the key",
                ],
                "a": 1,
                "why": "With no existing value, merge stores the supplied value, 1.",
            },
            {
                "q": "Why can a mutable key break a HashMap lookup?",
                "options": [
                    "HashMaps cannot store objects",
                    "Its hash code can change, moving it to a different bucket",
                    "Mutable keys are always sorted",
                    "The map copies the key on every get",
                ],
                "a": 1,
                "why": "The bucket is chosen from the hash at insertion time; a later change can make the key unreachable.",
            },
        ],
        "artifact": {
            "name": "WordTally.java",
            "description": "Read words from an array, count them, and print the distinct words in descending frequency. Break ties alphabetically.",
            "code": """import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordTally {
    public static void main(String[] args) {
        String[] words = {"stream", "file", "stream", "buffer", "file", "stream"};
        Map<String, Integer> counts = new HashMap<>();
        for (String word : words) {
            counts.merge(word, 1, Integer::sum);
        }

        List<Map.Entry<String, Integer>> ranked = new ArrayList<>(counts.entrySet());
        // TODO: sort by value descending, then by key ascending for ties.
        System.out.println(ranked);
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_21",
        },
        "reflection": "Describe a case where a TreeMap is worth the logarithmic cost over a HashMap. Be specific about the query that HashMap cannot answer cheaply.",
        "studio": {
            "title": "Set or map?",
            "intro": "The table is a frequency map. Apply filters, sorting, and limits to see how the same data answers different questions.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/collections-algorithms.md#chapter-21-sets-and-maps",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_21",
        },
    },
    {
        "n": 22,
        "title": "Developing Efficient Algorithms",
        "part": "Part 4 · Algorithms and structures",
        "tagline": "Efficiency is a claim about growth, not a stopwatch reading: how does the work increase when the input doubles?",
        "minutes": 50,
        "tags": ["complexity", "big-o", "binary search", "growth", "benchmark", "algorithm"],
        "objectives": [
            "Classify a simple loop or search by its asymptotic growth.",
            "Explain why binary search requires sorted input and halves the range.",
            "Separate algorithmic growth from constant-factor tuning.",
        ],
        "subgoals": [
            {
                "title": "Count the dominant operation",
                "idea": "A single loop over n elements is O(n). A loop inside a loop over the same n is O(n²). Constants and smaller terms are dropped.",
            },
            {
                "title": "Halving gives a logarithm",
                "idea": "Binary search removes half the remaining range each step, so the number of steps is about log₂ n.",
            },
            {
                "title": "Preconditions are part of the algorithm",
                "idea": "Binary search is only correct on sorted data. The sort may cost O(n log n), which can dominate a single search.",
            },
            {
                "title": "Worst case is the promise",
                "idea": "Big-O usually describes an upper bound on the worst case. Two algorithms with the same bound can still differ by a large constant.",
            },
            {
                "title": "Measure to confirm, not to define",
                "idea": "A benchmark on one machine cannot replace a growth argument. Use timing to detect constant factors and cache effects.",
            },
        ],
        "example": {
            "title": "Two searches, one sorted array",
            "intro": "Linear search may get lucky early. Binary search keeps halving, so its worst case is far smaller.",
            "code": [
                "public static int linear(int[] data, int target) {",
                "    for (int i = 0; i < data.length; i++) {",
                "        if (data[i] == target) return i;",
                "    }",
                "    return -1;",
                "}",
                "",
                "public static int binary(int[] data, int target) {",
                "    int low = 0, high = data.length - 1;",
                "    while (low <= high) {",
                "        int mid = low + (high - low) / 2;",
                "        if (data[mid] == target) return mid;",
                "        if (data[mid] < target) low = mid + 1;",
                "        else high = mid - 1;",
                "    }",
                "    return -1;",
                "}",
            ],
            "steps": [
                {
                    "label": "Linear scan",
                    "note": "Every element is examined in the worst case. The loop is O(n).",
                },
                {
                    "label": "Sorted precondition",
                    "note": "Binary search assumes ascending order. Without it, the comparison at mid points the wrong way.",
                },
                {
                    "label": "Compute mid safely",
                    "note": "<code>low + (high - low) / 2</code> avoids overflow that <code>(low + high) / 2</code> can cause for very large indexes.",
                },
                {
                    "label": "Discard half",
                    "note": "One comparison eliminates half the range. After k steps the range is about n / 2^k.",
                },
                {
                    "label": "Stop on equality or empty range",
                    "note": "The loop ends when the target is found or <code>low</code> passes <code>high</code>. Either way the result is correct for sorted input.",
                },
            ],
            "predict": {
                "prompt": "How many comparisons does binary search need, at most, for one million sorted elements?",
                "answer": "About 20, because 2²⁰ is slightly more than one million. Linear search could need one million.",
            },
        },
        "engine": "growth",
        "spec": {
            "hint": "Toggle the logarithmic scale. The straight line is linear work; the steep curve is quadratic work.",
            "series": [
                {"key": "log", "tone": "cyan"},
                {"key": "n", "tone": "emerald"},
                {"key": "nlogn", "tone": "violet"},
                {"key": "square", "tone": "amber"},
            ],
            "nMax": 64,
            "logScale": True,
        },
        "parsons": {
            "prompt": "Rebuild the binary search loop with a safe midpoint.",
            "lines": [
                "int low = 0, high = data.length - 1;",
                "while (low <= high) {",
                "    int mid = low + (high - low) / 2;",
                "    if (data[mid] == target) return mid;",
                "    if (data[mid] < target) low = mid + 1;",
                "    else high = mid - 1;",
                "}",
                "return -1;",
            ],
            "solution": [0, 1, 2, 3, 4, 5, 6, 7],
            "seed": 22,
            "why": "The bounds start at the ends, each comparison discards one half, and the loop ends when the range is empty.",
        },
        "quiz": [
            {
                "q": "What is the worst-case growth of one loop that visits every element?",
                "options": ["O(1)", "O(log n)", "O(n)", "O(n²)"],
                "a": 2,
                "why": "The work is proportional to the number of elements.",
            },
            {
                "q": "Why must the input be sorted for binary search?",
                "options": [
                    "Sorting makes the array smaller",
                    "The comparison decides which half can be discarded",
                    "Unsorted arrays cannot be indexed",
                    "The midpoint formula needs sorted values",
                ],
                "a": 1,
                "why": "The discard step is only valid if order guarantees the target cannot be in the other half.",
            },
            {
                "q": "When can sorting before binary search be slower than one linear search?",
                "options": [
                    "Never",
                    "When only one search is performed on a small array",
                    "When the array is already sorted",
                    "When the target is at index 0",
                ],
                "a": 1,
                "why": "Sorting costs O(n log n); for a single small search, a linear scan may be cheaper overall.",
            },
        ],
        "artifact": {
            "name": "GrowthLab.java",
            "description": "Instrument linear and binary search. Count comparisons for several input sizes and print the growth pattern.",
            "code": """public class GrowthLab {
    public static void main(String[] args) {
        for (int n = 8; n <= 1024; n *= 2) {
            int[] data = new int[n];
            for (int i = 0; i < n; i++) {
                data[i] = i * 2;
            }
            int target = n * 2 - 2;
            System.out.println(n + " linear=" + linearComparisons(data, target));
            System.out.println(n + " binary=" + binaryComparisons(data, target));
        }
    }

    static int linearComparisons(int[] data, int target) {
        int count = 0;
        for (int value : data) {
            count++;
            if (value == target) return count;
        }
        return count;
    }

    static int binaryComparisons(int[] data, int target) {
        // TODO: count each midpoint comparison and return the total.
        return 0;
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_22",
        },
        "reflection": "A teammate says an optimization made the program twice as fast. Ask two questions that distinguish a constant-factor win from an asymptotic improvement.",
        "studio": {
            "title": "Growth curve",
            "intro": "The chart compares four growth classes. Toggle the logarithmic scale to see why a line on a log plot still hides huge differences.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/collections-algorithms.md#chapter-22-efficient-algorithms",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_22",
        },
    },
]
