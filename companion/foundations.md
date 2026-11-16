# Foundations: How Programs Become Systems

This volume covers the first chapter and the two interface chapters that sit between JavaFX basics and the data-structure half of the book. The thread is the same in all three: a program is a model of something, and the runtime is what turns that model into behavior.

- [Chapter 1 — How Java Runs](#chapter-1-how-java-runs)
- [Chapter 15 — Events and Animation](#chapter-15-events-and-animation)
- [Chapter 16 — Controls, Layout, and Media](#chapter-16-controls-layout-and-media)

---

<a id="chapter-1-how-java-runs"></a>
<a name="chapter-1-how-java-runs"></a>
## Chapter 1 — How Java Runs

### The idea

Java is compiled twice, but only one of those steps produces a file you have to manage.

1. **Compile:** `javac` reads a `.java` source file and writes a `.class` file containing bytecode. Bytecode is not machine code for your CPU. It is a compact instruction set for the Java Virtual Machine.
2. **Load:** when you run the program, the JVM loads the class, verifies the bytecode, and prepares its static state.
3. **Execute:** the JVM interprets the bytecode and, where it matters, compiles hot paths to native machine code. That second compiler is the JIT — the just-in-time compiler.

This design is why the same `.class` file can run on a laptop, a server, and a phone without being recompiled. The JVM is the adapter between one portable format and many operating systems.

The three names around the runtime are easy to mix up:

- **JDK** — the development kit: compiler, launcher, debugger, documentation, and the JVM.
- **JRE** — the runtime environment: the JVM plus the standard libraries.
- **JVM** — the virtual machine that actually executes bytecode.

Install a JDK when you want to write and run Java. The exact vendor matters less than the version. Use a current long-term-support release such as Java 17 or 21 unless a course requires something older.

### A first program

```java
public class Atlas {
    public static void main(String[] args) {
        String language = "Java";
        int chapters = 42;
        System.out.println(language + " in " + chapters + " chapters.");
    }
}
```

Save it as `Atlas.java`. The public class name and the file name must match, including capitalization. Then:

```bash
javac Atlas.java
java Atlas
```

`java Atlas` names the class, not the file. Do not write `java Atlas.class`; the launcher adds the `.class` suffix itself.

The `main` signature is a contract with the launcher:

```java
public static void main(String[] args)
```

- `public` so the launcher can reach it.
- `static` so no object is needed before the program starts.
- `void` because the process exit status is not the return value.
- `String[] args` receives command-line arguments.

### Packages and the classpath

Real programs do not live in one folder. A package is a namespace and a directory path:

```java
package com.example.atlas;

public class Atlas {
    public static void main(String[] args) {
        System.out.println("packaged");
    }
}
```

This file belongs in `com/example/atlas/Atlas.java`. Compile from the source root and run with the fully qualified name:

```bash
javac com/example/atlas/Atlas.java
java com.example.atlas.Atlas
```

The **classpath** tells the launcher where to look for classes that are not part of the JDK. In modern projects a build tool writes that path for you. When something works in the IDE but fails in the terminal, a missing classpath entry is usually the reason.

### What to notice

- A `NullPointerException` is not a syntax error. The program compiled. The failure happened at runtime because a reference pointed at nothing.
- `ArrayIndexOutOfBoundsException` means the index was not inside `0..length - 1`. Java does not silently wrap around.
- `Exception in thread "main"` is a report from the default handler, not a separate crash reporter.
- `javac` errors are about types and names. Runtime exceptions are about values and state. Learn to tell them apart before you change code.

### Practice

1. Change the program to read a name from `args` and print a greeting. Run it with `java Atlas Ada`.
2. Move the class into `package learning;` and make it compile from the parent directory.
3. Break the file name on purpose and read the compiler message. Then fix it.

### Checkpoint

You are ready to move on when you can explain, without notes, why `javac` and `java` are separate commands, what a `.class` file contains, and why the JVM does not need your source code to run the program.

---

<a id="chapter-15-events-and-animation"></a>
<a name="chapter-15-events-and-animation"></a>
## Chapter 15 — Events and Animation

### The idea

A console program is a recipe: read, compute, print, exit. A graphical program is a conversation. It waits for something to happen, reacts, then waits again. That “something” is an **event**.

The cast is small:

- **Event source** — the button, key, mouse, timer, or window that noticed something.
- **Event object** — a description of what happened: which key, where the pointer was, what the target was.
- **Handler/listener** — your code, registered to be called when that description arrives.
- **Event loop** — the single thread that takes events from the queue and dispatches them.

The important consequence: your handler runs on the UI thread. If it sleeps, computes a large prime, or reads a slow file, the window stops repainting. The program is not “frozen” in a mysterious way; you are occupying the one thread that could have been drawing.

### One button, three equivalent shapes

JavaFX lets you attach behavior in more than one syntax. The underlying model is the same.

```java
Button run = new Button("Run");

// 1. Anonymous class
run.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        System.out.println("running");
    }
});

// 2. Lambda
run.setOnAction(event -> System.out.println("running"));

// 3. Method reference
run.setOnAction(Atlas::run);
```

A lambda is not magic. It is a compact implementation of a functional interface — an interface with one abstract method. `EventHandler<ActionEvent>` qualifies.

### Animation is a sequence of frames

An animation is not a special object that “moves.” It is a loop that changes state and asks the scene to redraw, fast enough that the eye sees motion.

JavaFX offers two useful levels:

- **`Timeline`** — declarative keyframes. Good for UI transitions, fades, slides, and scripted sequences.
- **`AnimationTimer`** — a callback on every pulse. Good for games and simulations where the next frame depends on the current state.

```java
Timeline timeline = new Timeline(
    new KeyFrame(Duration.ZERO,
        new KeyValue(circle.translateXProperty(), 0)),
    new KeyFrame(Duration.seconds(2),
        new KeyValue(circle.translateXProperty(), 320,
            Interpolator.EASE_BOTH))
);
timeline.setCycleCount(Animation.INDEFINITE);
timeline.setAutoReverse(true);
timeline.play();
```

That animation is smooth because the runtime interpolates between the two values, not because the circle has a velocity. If you need physics, keep your own velocity and use elapsed time:

```java
long previous = System.nanoTime();

new AnimationTimer() {
    @Override
    public void handle(long now) {
        double dt = (now - previous) / 1_000_000_000.0;
        previous = now;
        x += vx * dt;
        y += vy * dt;
        ball.setCenterX(x);
        ball.setCenterY(y);
    }
}.start();
```

Multiplying by `dt` is what makes the motion independent of frame rate. A slow machine should show the same path, not a slower ball.

### A runnable, dependency-free version

JavaFX is not bundled with every JDK, so the companion demo uses Swing. The event model and the timer are direct cousins.

```java
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics;

public class EventsAndAnimation extends JPanel {
    private double x = 40, y = 120, vx = 180, vy = 110;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(34, 211, 238));
        g.fillOval((int) x, (int) y, 28, 28);
    }

    public void start() {
        Timer timer = new Timer(16, event -> {
            double dt = 0.016;
            x += vx * dt;
            y += vy * dt;
            if (x < 0 || x > getWidth() - 28) vx = -vx;
            if (y < 0 || y > getHeight() - 28) vy = -vy;
            repaint();
        });
        timer.start();
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
```

The timer fires an event. The handler changes state and calls `repaint()`. The painting code draws the new state. No part of that sequence knows how to “animate”; the loop does all the work.

### What to notice

- **Never block the UI thread.** Move long work to a worker and publish the result back to the UI thread.
- **A timer is not a guarantee.** The interval is a request, not a contract. Use elapsed time for anything that must be physically consistent.
- **Listeners hold references.** A listener registered on a long-lived object can keep a short-lived object alive. Remove it when the owner goes away.
- **Input state is not an event.** A key press is an event; “the left key is currently down” is state. Games need both.

### Practice

1. Add keyboard control to the bouncing ball. Move it with the arrow keys while the timer keeps running.
2. Replace the fixed `dt` with the real elapsed time and verify that the ball still crosses the panel in the same number of seconds when you change the timer interval.
3. Add a “pause” button. Notice that pause is state, not a second timer.

### Checkpoint

You are ready to move on when you can explain the difference between an event source and an event handler, why animation needs a loop, and what goes wrong when a handler performs slow work on the UI thread.

---

<a id="chapter-16-controls-layout-and-media"></a>
<a name="chapter-16-controls-layout-and-media"></a>
## Chapter 16 — Controls, Layout, and Media

### The idea

A user interface is a layout problem wrapped around a state problem. Controls display state. Layout decides where they go. Binding keeps the two in sync.

JavaFX separates these concerns more cleanly than older toolkits:

- **Controls** — `Button`, `Label`, `TextField`, `ComboBox`, `ListView`, `TableView`, `Slider`, `ProgressBar`.
- **Layout panes** — `BorderPane`, `HBox`, `VBox`, `GridPane`, `StackPane`, `FlowPane`.
- **Properties and binding** — observable state that can drive the UI automatically.
- **CSS** — visual styling separated from structure.

The mental model: a layout pane is a parent with a policy. It does not place children at absolute pixels. It asks each child for its preferred size and then allocates space according to the pane’s rules. That is why a window can resize without a manual recalculation pass in your code.

### Layout is a negotiation

```java
BorderPane root = new BorderPane();

HBox toolbar = new HBox(8,
    new Button("Open"),
    new Button("Save"),
    new Label("Ready")
);

ListView<String> files = new ListView<>();
files.getItems().addAll("Atlas.java", "Graph.java", "Quiz.java");

Slider zoom = new Slider(50, 200, 100);
Label zoomLabel = new Label();

root.setTop(toolbar);
root.setCenter(files);
root.setBottom(zoom);
```

`BorderPane` gives the top and bottom their preferred height and the center the rest. `HBox` gives each child its preferred width, in order. If you want a child to absorb extra space, you say so explicitly:

```java
HBox.setHgrow(files, Priority.ALWAYS);
```

That is the whole layout conversation: preferred size, available space, and growth policy.

### Binding instead of manual synchronization

Without binding, every change needs two updates: change the model, then update the widget. It is easy to forget the second one.

```java
Slider zoom = new Slider(50, 200, 100);
Label readout = new Label();

readout.textProperty().bind(
    zoom.valueProperty().asString("%.0f%%")
);
```

Now the label cannot drift out of sync. Binding is a one-way dependency: the slider is the source, the label is the observer. For two-way synchronization use `bindBidirectional`.

This is also the right way to disable a button until a field is valid:

```java
Button submit = new Button("Submit");
submit.disableProperty().bind(
    nameField.textProperty().isEmpty()
);
```

The rule lives in one place, next to the control it protects.

### Tables are a composition, not a grid of strings

`TableView` is the control people underestimate. A table has:

- a type parameter for the row model,
- columns with cell value factories,
- optional cell factories for rendering,
- selection and sorting behavior provided by the control.

```java
record Student(String name, int score) {}

TableView<Student> table = new TableView<>();
TableColumn<Student, String> nameCol = new TableColumn<>("Name");
nameCol.setCellValueFactory(cell ->
    new SimpleStringProperty(cell.getValue().name()));

TableColumn<Student, Number> scoreCol = new TableColumn<>("Score");
scoreCol.setCellValueFactory(cell ->
    new SimpleIntegerProperty(cell.getValue().score()));

table.getColumns().addAll(nameCol, scoreCol);
table.getItems().addAll(
    new Student("Ada", 98),
    new Student("Grace", 96)
);
```

The row model is plain data. The column knows how to extract one property. The table handles scrolling, selection, and column resizing. Keep business logic out of the cell factories; they run often and should be cheap.

### Media has a lifecycle

`Media`, `MediaPlayer`, and `MediaView` are three different things:

- `Media` is the source and metadata.
- `MediaPlayer` controls playback: play, pause, seek, volume, time.
- `MediaView` displays video.

```java
Media media = new Media(
    getClass().getResource("/media/intro.mp4").toExternalForm()
);
MediaPlayer player = new MediaPlayer(media);
MediaView view = new MediaView(player);
player.setAutoPlay(true);
```

Always release a player when you are done with it: `player.stop()` and `player.dispose()`. A player holds native resources, and “it will be garbage collected eventually” is not a resource-management strategy.

For audio-only playback, you still need a `MediaPlayer`; you simply do not need a `MediaView`. Check `media.getError()` before playing, because a missing codec often fails quietly.

### What to notice

- **Do not use absolute positioning for everything.** It looks correct on one window size and breaks on every other.
- **Binding is not a background thread.** It synchronizes values; it does not make slow work fast.
- **CSS is not an afterthought.** A consistent stylesheet is cheaper than repeating colors and padding in Java.
- **Accessibility is part of the layout.** Labels, focus order, and keyboard access are not extras. A control that only works with a mouse is unfinished.

### Practice

1. Build a form with a name field, a role combo box, and a submit button. Disable submit until the name is non-empty.
2. Add a `TableView` of three rows and a `Slider` that filters by score. Use a binding or a listener; do not rebuild the table on every pixel.
3. Sketch the same window with `VBox` and with `GridPane`. Which one survives a resize with less code?

### Checkpoint

You are ready to move on when you can look at a screenshot and name the layout panes you would use, explain why a bound label cannot drift, and describe the difference between `Media`, `MediaPlayer`, and `MediaView`.

---

The next volume moves from the interface back to the engine: sets, maps, algorithm design, sorting, and the data structures that make large programs predictable.
