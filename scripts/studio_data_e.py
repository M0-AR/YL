"""Authored studio content: bonus chapters for advanced GUI, enterprise, trees, testing, and streams."""

CHAPTERS = [
    {
        "n": 34,
        "title": "Advanced JavaFX",
        "part": "Part 6 · Bonus chapters",
        "tagline": "Advanced JavaFX is mostly separation: the view declares structure, the model holds state, and controllers connect them without owning either.",
        "minutes": 50,
        "tags": ["javafx", "tableview", "fxml", "binding", "menu", "dialog", "controller"],
        "objectives": [
            "Separate an FXML view from its controller and model.",
            "Bind a TableView to an observable list instead of rebuilding it by hand.",
            "Choose between a dialog and an inline detail pane for an edit flow.",
        ],
        "subgoals": [
            {
                "title": "FXML declares, Java behaves",
                "idea": "The FXML file names components and controller hooks. The controller handles events and exposes state; it should not hard-code the layout.",
            },
            {
                "title": "Observable lists drive tables",
                "idea": "A <code>TableView</code> listens to an <code>ObservableList</code>. Adding to the list updates the table without a manual refresh call.",
            },
            {
                "title": "Bindings remove synchronization code",
                "idea": "A bound property follows its source. If the label text is bound to the selected row, selection changes update the label automatically.",
            },
            {
                "title": "Menus are commands, not state",
                "idea": "A menu item should invoke the same command as a toolbar button. Duplicating logic in each control creates two behaviors to maintain.",
            },
            {
                "title": "Dialogs are a boundary",
                "idea": "A modal dialog blocks the owner window and returns a result. Inline editing keeps context visible. Choose by whether the user needs the rest of the screen.",
            },
        ],
        "example": {
            "title": "BookTable: one list, three regions",
            "intro": "The table, detail pane, and status bar all read the same selection. No region stores a second copy of the selected book.",
            "code": [
                "public class BookController {",
                "    @FXML private TableView<Book> table;",
                "    @FXML private Label detail;",
                "    private final ObservableList<Book> books = FXCollections.observableArrayList();",
                "",
                "    @FXML",
                "    private void initialize() {",
                "        table.setItems(books);",
                "        table.getSelectionModel().selectedItemProperty().addListener(",
                "            (obs, oldBook, newBook) -> detail.setText(describe(newBook)));",
                "    }",
                "}",
            ],
            "steps": [
                {
                    "label": "Injected view parts",
                    "note": "The FXML loader assigns the table and label after constructing the controller. The fields are not created in Java.",
                },
                {
                    "label": "One observable source",
                    "note": "The list is the model for the table. Any code that adds a book mutates this list.",
                },
                {
                    "label": "Initialize after injection",
                    "note": "<code>initialize</code> runs when the FXML is fully loaded, so the injected fields are available.",
                },
                {
                    "label": "Listen to selection",
                    "note": "The listener receives old and new values. The detail label derives from the new selection.",
                },
                {
                    "label": "Handle null",
                    "note": "When the table is cleared, the new value can be null. <code>describe</code> must handle that or the UI throws.",
                },
            ],
            "predict": {
                "prompt": "What happens if a book is added directly to a new ArrayList instead of the observable list?",
                "answer": "The table never sees the change. It observes the list instance set with <code>setItems</code>, not every list that happens to contain books.",
            },
        },
        "engine": "layout",
        "spec": {
            "hint": "Compare BorderPane, SplitPane-like regions, and StackPane overlap. The table needs the center; menus and status need the edges.",
            "mode": "border",
            "children": ["Menu", "Toolbar", "Book table", "Detail", "Status"],
            "gap": 10,
            "padding": 14,
        },
        "parsons": {
            "prompt": "Rebuild the controller initialization that connects the table to its list and selection.",
            "lines": [
                "@FXML private TableView<Book> table;",
                "@FXML private Label detail;",
                "private final ObservableList<Book> books = FXCollections.observableArrayList();",
                "@FXML",
                "private void initialize() {",
                "    table.setItems(books);",
                "    table.getSelectionModel().selectedItemProperty().addListener(",
                "        (obs, oldBook, newBook) -> detail.setText(describe(newBook)));",
                "}",
            ],
            "solution": [0, 1, 2, 3, 4, 5, 6, 7, 8],
            "seed": 34,
            "why": "Injected fields exist before initialize; the table receives the observable list; the listener reads the selection.",
        },
        "quiz": [
            {
                "q": "What does an FXML controller own?",
                "options": [
                    "The exact pixel positions of every node",
                    "Event handlers and view state exposed to the FXML",
                    "The JavaFX runtime",
                    "The CSS parser",
                ],
                "a": 1,
                "why": "FXML declares structure; the controller supplies behavior and state hooks.",
            },
            {
                "q": "Why does a TableView update when an ObservableList changes?",
                "options": [
                    "It polls the list every second",
                    "It observes the list and receives change notifications",
                    "It copies the list on every frame",
                    "It reloads the FXML",
                ],
                "a": 1,
                "why": "ObservableList emits changes that the table listens to.",
            },
            {
                "q": "When is a modal dialog the better choice?",
                "options": [
                    "When the user must finish or cancel before returning to the main view",
                    "When the edit is one character",
                    "When the table is empty",
                    "When the window is maximized",
                ],
                "a": 0,
                "why": "A modal dialog deliberately blocks the owner to force a decision.",
            },
        ],
        "artifact": {
            "name": "BookController.java",
            "description": "A table controller with an observable list and a selection listener. Add a filter field that hides rows without creating a second list.",
            "code": """import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class BookController {
    @FXML private TableView<Book> table;
    @FXML private Label detail;
    private final ObservableList<Book> books = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        table.setItems(books);
        table.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldBook, newBook) -> detail.setText(describe(newBook)));
    }

    private String describe(Book book) {
        // TODO: return a useful placeholder when book is null.
        return book.title() + " (" + book.year() + ")";
    }

    public record Book(String title, int year) {}
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_34",
        },
        "reflection": "Describe one behavior that belongs in the controller and one that belongs in the model. Explain how the split makes testing easier.",
        "studio": {
            "title": "Region layout",
            "intro": "The advanced view is a set of regions, not a pile of absolute positions. Switch managers and see which one preserves the edge-center relationship.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/advanced-gui.md#chapter-34-advanced-gui",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_34",
        },
    },
    {
        "n": 35,
        "title": "Advanced Database Programming",
        "part": "Part 6 · Bonus chapters",
        "tagline": "A database program is a transaction boundary: either every related change lands, or none of them does.",
        "minutes": 50,
        "tags": ["transaction", "batch", "metadata", "prepared statement", "rollback", "connection pool"],
        "objectives": [
            "Group related updates into one transaction with commit and rollback.",
            "Use batch execution for repeated parameterized statements.",
            "Explain why connection pooling changes the cost model of a server.",
        ],
        "subgoals": [
            {
                "title": "Auto-commit is a policy",
                "idea": "With auto-commit on, every statement is its own transaction. Turn it off when several statements must succeed together.",
            },
            {
                "title": "Commit publishes, rollback discards",
                "idea": "Changes are invisible to other transactions until commit. Rollback returns the connection to its previous consistent state.",
            },
            {
                "title": "Batches reduce round trips",
                "idea": "<code>addBatch</code> queues parameter sets and <code>executeBatch</code> sends them together. It changes latency, not transaction semantics.",
            },
            {
                "title": "Metadata describes the result",
                "idea": "<code>ResultSetMetaData</code> reports column names and types. It is useful for generic tools, not for normal application queries.",
            },
            {
                "title": "Pools reuse connections",
                "idea": "Opening a connection is expensive. A pool returns connections to a managed set; code must still close them so they return.",
            },
        ],
        "example": {
            "title": "Transfer: debit and credit as one unit",
            "intro": "The two updates are only meaningful together. The transaction is the unit of correctness.",
            "code": [
                "connection.setAutoCommit(false);",
                "try {",
                "    debit.executeUpdate();",
                "    credit.executeUpdate();",
                "    connection.commit();",
                "} catch (SQLException failure) {",
                "    connection.rollback();",
                "    throw failure;",
                "} finally {",
                "    connection.setAutoCommit(true);",
                "}",
            ],
            "steps": [
                {
                    "label": "Start the unit",
                    "note": "Auto-commit is disabled. The next statements belong to one transaction.",
                },
                {
                    "label": "First update",
                    "note": "The debit changes rows inside the transaction. Other sessions do not see it yet.",
                },
                {
                    "label": "Second update",
                    "note": "The credit is applied in the same unit. If this fails, the debit must not survive.",
                },
                {
                    "label": "Commit",
                    "note": "Both changes become visible together and durable according to the database configuration.",
                },
                {
                    "label": "Failure path",
                    "note": "Rollback discards the partial work, and the exception is rethrown so the caller knows the transfer failed.",
                },
            ],
            "predict": {
                "prompt": "What happens if the program crashes after the debit but before commit?",
                "answer": "The transaction is rolled back by the database on connection loss. Neither the debit nor the credit is committed.",
            },
        },
        "engine": "data",
        "spec": {
            "hint": "Treat the toggles as transaction steps. Filter, sort, and limit show how the visible result changes before commit.",
            "columns": ["id", "owner", "balance"],
            "rows": [
                [1, "Ada", 1200],
                [2, "Grace", 80],
                [3, "Linus", 450],
                [4, "Margaret", 35],
                [5, "Alan", 900],
            ],
            "operations": [
                {"id": "low", "label": "Find low balances (< 100)", "type": "filter", "column": "balance", "op": "<", "value": 100},
                {"id": "sort", "label": "Order by balance", "type": "sort", "column": "balance", "dir": "asc"},
                {"id": "cols", "label": "Project owner, balance", "type": "select", "columns": ["owner", "balance"]},
            ],
            "defaults": ["low", "sort", "cols"],
        },
        "parsons": {
            "prompt": "Rebuild a transfer that commits on success and rolls back on failure.",
            "lines": [
                "connection.setAutoCommit(false);",
                "try {",
                "    debit.executeUpdate();",
                "    credit.executeUpdate();",
                "    connection.commit();",
                "} catch (SQLException failure) {",
                "    connection.rollback();",
                "    throw failure;",
                "}",
            ],
            "solution": [0, 1, 2, 3, 4, 5, 6, 7, 8],
            "seed": 35,
            "why": "Both updates complete before commit; any failure reaches rollback and is rethrown.",
        },
        "quiz": [
            {
                "q": "When do transaction changes become visible to other sessions?",
                "options": ["After each statement", "After commit", "After rollback", "When the connection closes"],
                "a": 1,
                "why": "Commit publishes the unit atomically.",
            },
            {
                "q": "What does a batch primarily reduce?",
                "options": [
                    "The number of rows changed",
                    "The number of network round trips",
                    "The need for transactions",
                    "The size of the database",
                ],
                "a": 1,
                "why": "Batching groups statement executions into fewer exchanges with the database.",
            },
            {
                "q": "Why must pooled connections still be closed by application code?",
                "options": [
                    "Closing deletes the database",
                    "Closing returns the connection to the pool for reuse",
                    "Closing commits every transaction",
                    "Closing disables prepared statements",
                ],
                "a": 1,
                "why": "The pool wrapper intercepts close and returns the physical connection to the managed set.",
            },
        ],
        "artifact": {
            "name": "TransferService.java",
            "description": "A transfer service with a transaction boundary. Add a check that the source balance cannot go negative.",
            "code": """import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransferService {
    public void transfer(Connection connection, int from, int to, int amount) throws SQLException {
        connection.setAutoCommit(false);
        try (PreparedStatement debit = connection.prepareStatement(
                "UPDATE accounts SET balance = balance - ? WHERE id = ? AND balance >= ?");
             PreparedStatement credit = connection.prepareStatement(
                "UPDATE accounts SET balance = balance + ? WHERE id = ?")) {
            debit.setInt(1, amount);
            debit.setInt(2, from);
            debit.setInt(3, amount);
            // TODO: execute both updates and verify the debit changed exactly one row.
            credit.setInt(1, amount);
            credit.setInt(2, to);
            credit.executeUpdate();
            connection.commit();
        } catch (SQLException failure) {
            connection.rollback();
            throw failure;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_35",
        },
        "reflection": "A batch of 1,000 inserts is split into ten batches of 100. Explain the trade-off between round trips and the size of a rollback if one row fails.",
        "studio": {
            "title": "Transaction visibility",
            "intro": "The table is the visible state. Use the pipeline to see what a query would return while the transaction is still open.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/enterprise.md#chapter-35-advanced-database-programming",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_35",
        },
    },
    {
        "n": 36,
        "title": "Internationalization",
        "part": "Part 6 · Bonus chapters",
        "tagline": "Internationalization is the discipline of keeping text, formatting, and assumptions about locale out of the code path.",
        "minutes": 45,
        "tags": ["i18n", "locale", "resource bundle", "format", "currency", "date", "collation"],
        "objectives": [
            "Externalize user-visible text into resource bundles.",
            "Format numbers, currency, and dates with an explicit locale.",
            "Explain why string concatenation is a poor way to build translated messages.",
        ],
        "subgoals": [
            {
                "title": "Locale is a bundle of conventions",
                "idea": "A locale carries language, country, and variant. It affects formatting and text lookup, not just translation.",
            },
            {
                "title": "Resource bundles hold text",
                "idea": "A base bundle plus locale-specific bundles let the same key resolve differently. Missing keys fall back rather than crash.",
            },
            {
                "title": "Format with the locale",
                "idea": "<code>NumberFormat</code>, <code>DateFormat</code>, and <code>Intl</code>-style APIs know decimal separators, currency symbols, and date order.",
            },
            {
                "title": "Do not concatenate grammar",
                "idea": "Word order and plural rules differ. A message pattern with placeholders lets translators control the sentence.",
            },
            {
                "title": "Sorting is locale-sensitive",
                "idea": "Collation can order accented letters differently. A binary string sort is not a user-visible alphabetical order.",
            },
        ],
        "example": {
            "title": "PriceTag: one value, several conventions",
            "intro": "The number is the same; the representation changes with the locale. The currency is part of the format, not the text.",
            "code": [
                "double price = 1234.5;",
                "Locale germany = Locale.GERMANY;",
                "NumberFormat currency = NumberFormat.getCurrencyInstance(germany);",
                "String formatted = currency.format(price);",
                "ResourceBundle bundle = ResourceBundle.getBundle(\"messages\", germany);",
                "String label = bundle.getString(\"price.label\");",
                "System.out.println(label + \": \" + formatted);",
            ],
            "steps": [
                {
                    "label": "The value",
                    "note": "The double is locale-free. It has no comma, dot, or symbol until formatting.",
                },
                {
                    "label": "Choose the locale",
                    "note": "Germany implies German text conventions and the euro currency for this format.",
                },
                {
                    "label": "Format the number",
                    "note": "The formatter inserts the grouping separator, decimal separator, and currency symbol appropriate to the locale.",
                },
                {
                    "label": "Load the text",
                    "note": "The bundle resolves the key to German text. A missing German bundle would fall back to the base bundle.",
                },
                {
                    "label": "Compose the message",
                    "note": "The label and value are separate. A full message pattern would be even better for grammar.",
                },
            ],
            "predict": {
                "prompt": "Why is <code>\"Total: \" + price</code> not internationalized even if the label is translated?",
                "answer": "The number uses the default locale and a fixed decimal point. The label translation does not change number formatting or word order.",
            },
        },
        "engine": "data",
        "spec": {
            "hint": "Change the locale. The same rows are reformatted; the underlying values do not change.",
            "columns": ["city", "population", "revenue"],
            "rows": [
                ["Berlin", 3.7, 158000000],
                ["Munich", 1.5, 92000000],
                ["Hamburg", 1.8, 74000000],
                ["Cologne", 1.1, 51000000],
            ],
            "operations": [
                {"id": "big", "label": "Population at least 1.5 million", "type": "filter", "column": "population", "op": ">=", "value": 1.5},
                {"id": "sort", "label": "Order by revenue", "type": "sort", "column": "revenue", "dir": "desc"},
                {"id": "top", "label": "Keep top three", "type": "limit", "n": 3},
            ],
            "defaults": ["big", "sort", "top"],
            "i18n": True,
            "currencyColumn": "revenue",
            "currency": "EUR",
            "locale": "de-DE",
        },
        "parsons": {
            "prompt": "Rebuild localized currency formatting and bundle lookup.",
            "lines": [
                "double price = 1234.5;",
                "Locale germany = Locale.GERMANY;",
                "NumberFormat currency = NumberFormat.getCurrencyInstance(germany);",
                "String formatted = currency.format(price);",
                "ResourceBundle bundle = ResourceBundle.getBundle(\"messages\", germany);",
                "String label = bundle.getString(\"price.label\");",
            ],
            "solution": [0, 1, 2, 3, 4, 5],
            "seed": 36,
            "why": "The value exists first, the locale selects conventions, and text is resolved through the bundle rather than hard-coded.",
        },
        "quiz": [
            {
                "q": "What does a Locale affect?",
                "options": [
                    "Only translation",
                    "Text lookup, number, currency, and date formatting",
                    "The JVM bytecode",
                    "The database schema",
                ],
                "a": 1,
                "why": "Locale carries language and regional formatting conventions.",
            },
            {
                "q": "Why should translated messages use placeholders instead of concatenation?",
                "options": [
                    "Concatenation is slower",
                    "Word order and grammar differ across languages",
                    "Placeholders encrypt values",
                    "Strings cannot be concatenated",
                ],
                "a": 1,
                "why": "A pattern lets the translator control the full sentence and the position of values.",
            },
            {
                "q": "What happens when a locale-specific bundle lacks a key?",
                "options": [
                    "The program always crashes",
                    "The lookup falls back to a parent bundle",
                    "The key is deleted",
                    "The JVM translates it",
                ],
                "a": 1,
                "why": "ResourceBundle searches parent bundles before failing.",
            },
        ],
        "artifact": {
            "name": "MessageFormatDemo.java",
            "description": "A localized message with a number and a date. Add a second locale and verify that the argument order still reads naturally.",
            "code": """import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageFormatDemo {
    public static void main(String[] args) {
        Locale locale = Locale.GERMANY;
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
        String pattern = bundle.getString("order.summary");
        String amount = NumberFormat.getCurrencyInstance(locale).format(1234.5);
        String date = DateTimeFormatter.ofLocalizedDate(
                java.time.format.FormatStyle.MEDIUM).withLocale(locale).format(LocalDate.now());
        // TODO: use MessageFormat with the pattern so the translator controls word order.
        System.out.println(pattern + " " + amount + " " + date);
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_36",
        },
        "reflection": "Find one string in a program you know that is built by concatenation. Rewrite it as a message pattern with named or numbered arguments.",
        "studio": {
            "title": "Locale switch",
            "intro": "The same rows are formatted for en-US, de-DE, fr-FR, and ja-JP. Watch separators and currency symbols move.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/enterprise.md#chapter-36-internationalization",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_36",
        },
    },
    {
        "n": 37,
        "title": "Servlets",
        "part": "Part 6 · Bonus chapters",
        "tagline": "A servlet is a singleton living inside a container; the container owns its lifecycle and calls it on many threads.",
        "minutes": 50,
        "tags": ["servlet", "container", "lifecycle", "request", "response", "session", "thread safety"],
        "objectives": [
            "Describe the servlet lifecycle from init through destroy.",
            "Separate request-scoped data from instance fields.",
            "Explain why servlet instance fields are shared across requests.",
        ],
        "subgoals": [
            {
                "title": "The container owns the lifecycle",
                "idea": "The container creates one servlet instance, calls <code>init</code> once, dispatches requests, and calls <code>destroy</code> before removal.",
            },
            {
                "title": "One instance, many threads",
                "idea": "Requests run concurrently on the same instance. Instance fields are shared state and need synchronization or removal.",
            },
            {
                "title": "Request and response are per call",
                "idea": "The <code>HttpServletRequest</code> and response objects belong to one exchange. Store per-request data in request attributes, not fields.",
            },
            {
                "title": "Sessions span requests",
                "idea": "A session is server-side state keyed by a cookie or URL token. It is shared by one user, not by all users.",
            },
            {
                "title": "Forward versus redirect",
                "idea": "A forward stays server-side and preserves request attributes. A redirect asks the browser to make a new request and loses request scope.",
            },
        ],
        "example": {
            "title": "GreetingServlet: one instance, one request",
            "intro": "The name comes from the request, never from a field. A field would leak one user's value into another user's response.",
            "code": [
                "public class GreetingServlet extends HttpServlet {",
                "    public void init() {",
                "        log(\"ready\");",
                "    }",
                "    protected void doGet(HttpServletRequest request, HttpServletResponse response)",
                "            throws IOException {",
                "        String name = request.getParameter(\"name\");",
                "        response.setContentType(\"text/plain; charset=UTF-8\");",
                "        response.getWriter().println(\"Hello, \" + name);",
                "    }",
                "    public void destroy() {",
                "        log(\"stopped\");",
                "    }",
                "}",
            ],
            "steps": [
                {
                    "label": "Container creates the instance",
                    "note": "There is normally one servlet object, not one per request.",
                },
                {
                    "label": "init runs once",
                    "note": "Use it for expensive setup. It is not called for every request.",
                },
                {
                    "label": "Service dispatches",
                    "note": "The container selects <code>doGet</code> based on the HTTP method and calls it on a request thread.",
                },
                {
                    "label": "Request data stays local",
                    "note": "<code>name</code> is a local variable, so two concurrent requests cannot overwrite each other.",
                },
                {
                    "label": "destroy runs once",
                    "note": "The container calls it before shutting the servlet down; release resources here.",
                },
            ],
            "predict": {
                "prompt": "What goes wrong if <code>name</code> is stored in an instance field instead of a local variable?",
                "answer": "Two simultaneous requests can interleave writes to the same field, so one user can receive another user's name. The field is shared across threads.",
            },
        },
        "engine": "state",
        "spec": {
            "hint": "Follow the lifecycle. The request object exists per call; the servlet instance exists across calls.",
            "code": [
                "servlet.init();",
                "servlet.service(request, response);",
                "servlet.doGet(request, response);",
                "response.getWriter().println(\"Hello, \" + request.getParameter(\"name\"));",
                "servlet.destroy();",
            ],
            "vars": ["phase", "request", "response"],
            "steps": [
                {
                    "line": 0,
                    "vars": {"phase": "initialized", "request": "none", "response": "none"},
                    "note": "The container has created one instance and completed initialization.",
                },
                {
                    "line": 1,
                    "vars": {"phase": "service", "request": "GET /hello?name=Ada", "response": "open"},
                    "note": "A request arrives on its own thread. The container calls service.",
                },
                {
                    "line": 2,
                    "vars": {"phase": "doGet", "request": "name=Ada", "response": "open"},
                    "note": "The HTTP method selects doGet. The parameter is read from this request only.",
                },
                {
                    "line": 3,
                    "vars": {"phase": "write", "request": "name=Ada", "response": "Hello, Ada"},
                    "note": "The writer produces the body. The response is scoped to this exchange.",
                },
                {
                    "line": 4,
                    "vars": {"phase": "destroyed", "request": "closed", "response": "closed"},
                    "note": "Shutdown releases the instance. Another request would need a new container lifecycle.",
                },
            ],
        },
        "parsons": {
            "prompt": "Rebuild a thread-safe servlet method that reads a request parameter.",
            "lines": [
            "protected void doGet(HttpServletRequest request, HttpServletResponse response)",
                "        throws IOException {",
                "    String name = request.getParameter(\"name\");",
                "    response.setContentType(\"text/plain; charset=UTF-8\");",
                "    response.getWriter().println(\"Hello, \" + name);",
                "}",
            ],
            "solution": [0, 1, 2, 3, 4, 5],
            "seed": 37,
            "why": "The parameter is read into a local variable, the content type is set before writing, and the response body is produced last.",
        },
        "quiz": [
            {
                "q": "How many servlet instances does a container normally create for one mapping?",
                "options": ["One per request", "One per session", "One per servlet", "One per thread"],
                "a": 2,
                "why": "The container reuses a single instance and dispatches requests to it concurrently.",
            },
            {
                "q": "Where should per-request data be stored?",
                "options": ["In an instance field", "In a local variable or request attribute", "In a static field", "In the servlet context"],
                "a": 1,
                "why": "Instance and static fields are shared; request scope isolates each exchange.",
            },
            {
                "q": "What is lost after a redirect?",
                "options": ["The response status", "Request attributes from the previous request", "The browser", "The server"],
                "a": 1,
                "why": "A redirect creates a new request, so request-scoped data does not carry over.",
            },
        ],
        "artifact": {
            "name": "CounterServlet.java",
            "description": "A servlet that counts requests per session, not globally. Explain why a static counter would be the wrong scope.",
            "code": """import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CounterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession(true);
        Integer visits = (Integer) session.getAttribute("visits");
        int next = visits == null ? 1 : visits + 1;
        session.setAttribute("visits", next);
        response.setContentType("text/plain; charset=UTF-8");
        // TODO: write the visit count and explain the session scope in a comment.
        response.getWriter().println("visits=" + next);
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_37",
        },
        "reflection": "Explain why a servlet instance field is dangerous even if the application usually has one user. Name the deployment condition that turns the risk into a bug.",
        "studio": {
            "title": "Container lifecycle",
            "intro": "The phase viewer separates one-time initialization from per-request work. The request column resets; the instance does not.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/enterprise.md#chapter-37-servlets",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_37",
        },
    },
    {
        "n": 38,
        "title": "JavaServer Pages",
        "part": "Part 6 · Bonus chapters",
        "tagline": "A JSP is a template that becomes a servlet; the useful discipline is keeping Java logic out of the markup.",
        "minutes": 45,
        "tags": ["jsp", "el", "jstl", "template", "servlet", "translation", "escape"],
        "objectives": [
            "Explain how a JSP is translated into a servlet.",
            "Use EL and JSTL to render a collection without scriptlets.",
            "Escape user-controlled output to prevent markup injection.",
        ],
        "subgoals": [
            {
                "title": "Translation happens first",
                "idea": "The container translates a JSP into a servlet class, compiles it, and then follows the servlet lifecycle. The first request can pay that cost.",
            },
            {
                "title": "EL reads scoped attributes",
                "idea": "<code>${names}</code> searches page, request, session, and application scope. The name is a key, not a Java variable.",
            },
            {
                "title": "JSTL supplies control flow",
                "idea": "<code>c:forEach</code> and <code>c:if</code> replace scriptlet loops and branches. The page stays markup-first.",
            },
            {
                "title": "Output is not automatically safe",
                "idea": "A value containing HTML becomes markup unless escaped. Use <code>c:out</code> or an escape function for untrusted text.",
            },
            {
                "title": "The servlet prepares the model",
                "idea": "Queries and business rules belong in the servlet or service layer. The JSP receives attributes and renders them.",
            },
        ],
        "example": {
            "title": "NameList: one attribute, one loop",
            "intro": "The servlet puts a list in request scope. The page renders it with a tag, not a scriptlet.",
            "code": [
                "<% request.setAttribute(\"names\", List.of(\"Ada\", \"Grace\", \"Linus\")); %>",
                "<ul>",
                "  <c:forEach items=\"${names}\" var=\"name\">",
                "    <li><c:out value=\"${name}\" /></li>",
                "  </c:forEach>",
                "</ul>",
            ],
            "steps": [
                {
                    "label": "Prepare the model",
                    "note": "The attribute is request-scoped, so it disappears after this request.",
                },
                {
                    "label": "Translate the page",
                    "note": "The container turns the JSP into a servlet. The HTML becomes writes to the response.",
                },
                {
                    "label": "Evaluate the collection",
                    "note": "EL resolves <code>names</code> from request scope and exposes it to the loop.",
                },
                {
                    "label": "Render each item",
                    "note": "The loop repeats the list item markup for every name.",
                },
                {
                    "label": "Escape the value",
                    "note": "<code>c:out</code> escapes HTML characters, so a name containing angle brackets is displayed as text.",
                },
            ],
            "predict": {
                "prompt": "What happens if a name is <code>&lt;script&gt;alert(1)&lt;/script&gt;</code> and the page uses <code>${name}</code> instead of <code>c:out</code>?",
                "answer": "The browser receives executable markup, not text. Escaping turns the angle brackets into entities and prevents script execution.",
            },
        },
        "engine": "state",
        "spec": {
            "hint": "Step from the servlet model to the rendered response. The attribute is read once per request.",
            "code": [
                "request.setAttribute(\"names\", names);",
                "RequestDispatcher view = request.getRequestDispatcher(\"list.jsp\");",
                "view.forward(request, response);",
                "// container translates and runs list.jsp",
                "// c:forEach renders one li per name",
                "response.getWriter().println(\"<ul>...</ul>\");",
            ],
            "vars": ["stage", "model", "output"],
            "steps": [
                {
                    "line": 0,
                    "vars": {"stage": "servlet", "model": "names=[Ada, Grace, Linus]", "output": "\"\""},
                    "note": "The servlet has prepared a request attribute.",
                },
                {
                    "line": 1,
                    "vars": {"stage": "dispatch", "model": "names ready", "output": "\"\""},
                    "note": "The dispatcher points at the JSP resource.",
                },
                {
                    "line": 2,
                    "vars": {"stage": "forward", "model": "names ready", "output": "\"\""},
                    "note": "Forward stays server-side, so the request attribute remains available.",
                },
                {
                    "line": 4,
                    "vars": {"stage": "render", "model": "names read", "output": "<ul><li>Ada</li>..."},
                    "note": "The loop repeats markup and escapes each value.",
                },
                {
                    "line": 5,
                    "vars": {"stage": "complete", "model": "names read", "output": "HTML response"},
                    "note": "The response body is complete; request scope ends.",
                },
            ],
        },
        "parsons": {
            "prompt": "Rebuild the JSP fragment that renders an escaped list.",
            "lines": [
                "<ul>",
                "  <c:forEach items=\"${names}\" var=\"name\">",
                "    <li><c:out value=\"${name}\" /></li>",
                "  </c:forEach>",
                "</ul>",
            ],
            "solution": [0, 1, 2, 3, 4],
            "seed": 38,
            "why": "The list wraps the loop; c:out escapes each value before it becomes markup.",
        },
        "quiz": [
            {
                "q": "What does a JSP become before it runs?",
                "options": ["A CSS file", "A servlet class", "A database table", "A JavaScript bundle"],
                "a": 1,
                "why": "The container translates and compiles the JSP into a servlet.",
            },
            {
                "q": "Which scope does <code>${names}</code> search first?",
                "options": ["Application", "Session", "Request", "Page"],
                "a": 3,
                "why": "EL searches page, then request, session, and application scope.",
            },
            {
                "q": "Why use <code>c:out</code> for user-controlled text?",
                "options": [
                    "It sorts the text",
                    "It escapes HTML so the value is displayed, not executed",
                    "It stores the text in a session",
                    "It compresses the response",
                ],
                "a": 1,
                "why": "Escaping prevents markup injection from values that contain angle brackets.",
            },
        ],
        "artifact": {
            "name": "list.jsp",
            "description": "A JSTL page that renders a request attribute with a count and an empty-state message. No scriptlets.",
            "code": """<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul>
  <c:forEach items="${names}" var="name">
    <li><c:out value="${name}" /></li>
  </c:forEach>
</ul>
<c:if test="${empty names}">
  <p>No names were provided.</p>
</c:if>
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_38",
        },
        "reflection": "Explain why business logic in a JSP is harder to test than the same logic in a servlet or service class.",
        "studio": {
            "title": "From attribute to HTML",
            "intro": "The viewer tracks the request-scoped model and the response separately. Forward keeps the model; redirect would not.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/enterprise.md#chapter-38-javaserver-pages",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_38",
        },
    },
    {
        "n": 39,
        "title": "Web Services",
        "part": "Part 6 · Bonus chapters",
        "tagline": "A web service is a contract over HTTP: method, path, representation, status, and the rules for repeating a request.",
        "minutes": 50,
        "tags": ["rest", "http", "json", "status", "idempotent", "api", "client"],
        "objectives": [
            "Map CRUD operations onto HTTP methods and status codes.",
            "Explain idempotency and why retries are safe for some methods.",
            "Separate transport concerns from representation and resource state.",
        ],
        "subgoals": [
            {
                "title": "Resources have identifiers",
                "idea": "A URI names a resource. <code>/books/42</code> is a resource; the JSON is one representation of it.",
            },
            {
                "title": "Methods carry intent",
                "idea": "GET reads, POST creates, PUT replaces, PATCH partially updates, DELETE removes. The method is part of the contract, not decoration.",
            },
            {
                "title": "Status codes are part of the response",
                "idea": "200 means success, 201 means created, 400 means a bad request, 404 means missing, 409 means a conflict, 500 means server failure.",
            },
            {
                "title": "Idempotency makes retries safe",
                "idea": "Repeating a GET, PUT, or DELETE should have the same effect as one call. POST is not generally idempotent.",
            },
            {
                "title": "Representations are negotiated",
                "idea": "Content-Type describes the body; Accept requests a representation. JSON is common but not the only option.",
            },
        ],
        "example": {
            "title": "BookEndpoint: read one resource",
            "intro": "The path identifies the resource, the method states the intent, and the status reports the outcome.",
            "code": [
                "GET /api/books/42",
                "Accept: application/json",
                "",
                "200 OK",
                "Content-Type: application/json",
                "{\"id\":42,\"title\":\"Distributed Systems\",\"year\":2020}",
            ],
            "steps": [
                {
                    "label": "Request line",
                    "note": "GET is safe and idempotent: it should not change server state.",
                },
                {
                    "label": "Representation request",
                    "note": "Accept tells the server the client prefers JSON. The server may return another supported type.",
                },
                {
                    "label": "Lookup",
                    "note": "The server maps the path to a resource identifier and queries its store.",
                },
                {
                    "label": "Success status",
                    "note": "200 means the representation is in the body. A missing book would be 404, not an empty 200.",
                },
                {
                    "label": "Body",
                    "note": "The JSON is a representation, not the resource itself. Another request could ask for XML or a summary.",
                },
            ],
            "predict": {
                "prompt": "A client retries the same GET after a timeout. Is that safe?",
                "answer": "Yes. GET is safe and idempotent, so repeating it should not change state. The retry may return a newer representation, but it will not create a duplicate.",
            },
        },
        "engine": "state",
        "spec": {
            "hint": "Step through one request. Each stage has a different failure mode and a different status code.",
            "code": [
                "route(method, path)",
                "resource = repository.find(id)",
                "representation = serialize(resource)",
                "response.status(200)",
                "response.body(representation)",
            ],
            "vars": ["method", "status", "body"],
            "steps": [
                {
                    "line": 0,
                    "vars": {"method": "GET", "status": "pending", "body": "\"\""},
                    "note": "The router matches the method and path to a handler.",
                },
                {
                    "line": 1,
                    "vars": {"method": "GET", "status": "pending", "body": "\"\""},
                    "note": "The repository returns the resource or null. Null leads to 404 later.",
                },
                {
                    "line": 2,
                    "vars": {"method": "GET", "status": "pending", "body": "JSON"},
                    "note": "Serialization chooses a representation; it does not change the resource.",
                },
                {
                    "line": 3,
                    "vars": {"method": "GET", "status": "200 OK", "body": "JSON"},
                    "note": "The status states the outcome. A successful empty body would still be a success, but here the body carries the resource.",
                },
                {
                    "line": 4,
                    "vars": {"method": "GET", "status": "200 OK", "body": "JSON"},
                    "note": "The response is sent. Repeating the request is safe.",
                },
            ],
        },
        "parsons": {
            "prompt": "Rebuild a client that sends a GET, checks the status, and parses JSON.",
            "lines": [
                "HttpRequest request = HttpRequest.newBuilder()",
                "    .uri(URI.create(\"https://example.test/api/books/42\"))",
                "    .header(\"Accept\", \"application/json\")",
                "    .GET()",
                "    .build();",
                "HttpResponse<String> response = client.send(request, BodyHandlers.ofString());",
                "if (response.statusCode() == 200) {",
                "    System.out.println(response.body());",
                "}",
            ],
            "solution": [0, 1, 2, 3, 4, 5, 6, 7, 8],
            "seed": 39,
            "why": "Build the request, send it, then branch on the status before reading the body.",
        },
        "quiz": [
            {
                "q": "Which method is generally not idempotent?",
                "options": ["GET", "PUT", "DELETE", "POST"],
                "a": 3,
                "why": "Repeating a POST can create multiple resources; GET, PUT, and DELETE are defined to be idempotent.",
            },
            {
                "q": "What does 404 mean?",
                "options": ["The server crashed", "The resource was not found", "The request succeeded", "The representation is invalid"],
                "a": 1,
                "why": "404 is a client-visible statement that the target resource does not exist.",
            },
            {
                "q": "What is the difference between a resource and its representation?",
                "options": [
                    "They are the same bytes",
                    "The resource is the identified thing; the representation is a format of it",
                    "A representation has no status code",
                    "Only resources can be JSON",
                ],
                "a": 1,
                "why": "The URI identifies the resource; the body encodes one representation of its state.",
            },
        ],
        "artifact": {
            "name": "BookClient.java",
            "description": "A small HTTP client that handles 200, 404, and other statuses without printing the body as if it were always valid.",
            "code": """import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BookClient {
    public static void main(String[] args) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://example.test/api/books/42"))
                .header("Accept", "application/json")
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // TODO: handle 200, 404, and unexpected statuses with clear messages.
        System.out.println(response.statusCode() + " " + response.body());
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_39",
        },
        "reflection": "Design the status code and body for creating a book when the title already exists. Explain why a generic 200 would make the client's job harder.",
        "studio": {
            "title": "Request to response",
            "intro": "The state viewer separates routing, lookup, serialization, status, and body. Failures diverge at different stages.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/enterprise.md#chapter-39-web-services",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_39",
        },
    },
    {
        "n": 40,
        "title": "2-4 Trees and B-Trees",
        "part": "Part 6 · Bonus chapters",
        "tagline": "A multiway tree keeps data balanced by allowing nodes to hold several keys and by splitting before a node overflows.",
        "minutes": 55,
        "tags": ["2-4 tree", "b-tree", "split", "multiway", "balanced", "disk"],
        "objectives": [
            "Describe the key and child counts of a 2-3-4 tree node.",
            "Trace an insertion that splits a full node.",
            "Explain why a high branching factor reduces disk reads.",
        ],
        "subgoals": [
            {
                "title": "A 2-node has one key",
                "idea": "A 2-node has one key and two children. A 3-node has two keys and three children. A 4-node has three keys and four children.",
            },
            {
                "title": "All leaves are at the same depth",
                "idea": "The tree grows only by splitting the root, so every leaf stays on one level. Height grows slowly and uniformly.",
            },
            {
                "title": "Split before descending",
                "idea": "If a child is full, split it before entering. The middle key rises to the parent, and the two halves become separate nodes.",
            },
            {
                "title": "Order is local to the node",
                "idea": "Keys inside a node are sorted, and each child holds a range between neighboring keys. Search compares within the node, then chooses a child.",
            },
            {
                "title": "Branching factor fits the storage",
                "idea": "A B-tree node can hold many keys, matching a disk block. Fewer levels mean fewer I/O operations for a lookup.",
            },
        ],
        "example": {
            "title": "Split: 10, 20, 30 becomes a parent and two children",
            "intro": "Inserting 40 into a full root forces a split. The middle key moves up, and the tree gains one level.",
            "code": [
                "// root is a 4-node: [10, 20, 30]",
                "// inserting 40 makes the node overflow",
                "int middle = 20;",
                "// left child: [10]",
                "// right child: [30, 40]",
                "// new root: [20]",
            ],
            "steps": [
                {
                    "label": "Full root",
                    "note": "The root holds three keys, the maximum for a 4-node.",
                },
                {
                    "label": "Detect overflow",
                    "note": "A fourth key would exceed the node capacity. The split happens before the new key is stored.",
                },
                {
                    "label": "Promote the middle",
                    "note": "20 rises to a new root. It separates the smaller and larger keys.",
                },
                {
                    "label": "Split the remainder",
                    "note": "10 stays left; 30 and 40 form the right node. All leaves remain at the same depth.",
                },
                {
                    "label": "Search after split",
                    "note": "Search compares with 20 at the root, then enters the left or right child. The height increased by one, not by one per insertion.",
                },
            ],
            "predict": {
                "prompt": "How many children does a 4-node have before it splits?",
                "answer": "Four. Three sorted keys create four ranges, so a full 4-node has four child references even when those children are null at a leaf.",
            },
        },
        "engine": "structure",
        "spec": {
            "hint": "Step through insertion and the split. Labels with several keys represent one multiway node.",
            "mode": "tree",
            "frames": [
                {
                    "label": "insert 10",
                    "nodes": [{"id": "r", "label": "10", "x": 0.5, "y": 0.1}],
                    "highlight": ["r"],
                    "note": "A single-key root: a 2-node with two empty child slots.",
                },
                {
                    "label": "insert 20",
                    "nodes": [{"id": "r", "label": "10 | 20", "x": 0.5, "y": 0.1}],
                    "highlight": ["r"],
                    "note": "Two keys in one node: a 3-node with three child ranges.",
                },
                {
                    "label": "insert 30",
                    "nodes": [{"id": "r", "label": "10 | 20 | 30", "x": 0.5, "y": 0.1}],
                    "highlight": ["r"],
                    "note": "The node is full. It is a 4-node and cannot accept another key.",
                },
                {
                    "label": "insert 40: split",
                    "nodes": [
                        {"id": "root", "label": "20", "x": 0.5, "y": 0.1},
                        {"id": "left", "label": "10", "x": 0.25, "y": 0.52, "parent": "root"},
                        {"id": "right", "label": "30 | 40", "x": 0.74, "y": 0.52, "parent": "root"},
                    ],
                    "highlight": ["root"],
                    "note": "The middle key 20 rises; the left and right ranges become children of the new root.",
                },
                {
                    "label": "search 40",
                    "nodes": [
                        {"id": "root", "label": "20", "x": 0.5, "y": 0.1},
                        {"id": "left", "label": "10", "x": 0.25, "y": 0.52, "parent": "root"},
                        {"id": "right", "label": "30 | 40", "x": 0.74, "y": 0.52, "parent": "root"},
                    ],
                    "highlight": ["right"],
                    "note": "40 is greater than 20, so the right child is the only possible location.",
                },
            ],
        },
        "parsons": {
            "prompt": "Rebuild the split of a full 4-node.",
            "lines": [
                "int middle = keys[1];",
                "Node left = new Node(keys[0]);",
                "Node right = new Node(keys[2], keys[3]);",
                "Node parent = new Node(middle);",
                "parent.children.add(left);",
                "parent.children.add(right);",
                "return parent;",
            ],
            "solution": [0, 1, 2, 3, 4, 5, 6],
            "seed": 40,
            "why": "The middle key becomes the parent, the lower and upper keys become children, and the new subtree root is returned.",
        },
        "quiz": [
            {
                "q": "How many keys can a 4-node hold?",
                "options": ["1", "2", "3", "4"],
                "a": 2,
                "why": "A 4-node has three keys and four children.",
            },
            {
                "q": "When does a 2-4 tree gain height?",
                "options": [
                    "On every insertion",
                    "When the root splits",
                    "On every search",
                    "When a leaf is deleted",
                ],
                "a": 1,
                "why": "Splits below the root keep the height; only a root split adds a level.",
            },
            {
                "q": "Why are B-trees useful for disk storage?",
                "options": [
                    "They store one key per block",
                    "A node can hold many keys, reducing tree height and I/O",
                    "They never split",
                    "They require no comparisons",
                ],
                "a": 1,
                "why": "A high branching factor packs more routing information into each block.",
            },
        ],
        "artifact": {
            "name": "MultiNode.java",
            "description": "A 2-4 node with sorted keys and child references. Add a leaf check and a search method that returns the matching key or null.",
            "code": """import java.util.ArrayList;
import java.util.List;

public class MultiNode {
    private final List<Integer> keys = new ArrayList<>();
    private final List<MultiNode> children = new ArrayList<>();

    public boolean isLeaf() {
        return children.isEmpty();
    }

    public Integer find(int key) {
        // TODO: compare within the sorted keys and return the match or null.
        return null;
    }

    public void insertKey(int key) {
        int index = 0;
        while (index < keys.size() && keys.get(index) < key) index++;
        keys.add(index, key);
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_40",
        },
        "reflection": "Explain why a B-tree with a branching factor of 100 can serve millions of keys in only a few levels. Use the logarithm in your answer.",
        "studio": {
            "title": "Split the root",
            "intro": "The tree viewer treats a combined label as one multiway node. Watch the height grow only when the root splits.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/advanced-trees-testing.md#chapter-40-2-4-trees-and-b-trees",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_40",
        },
    },
    {
        "n": 41,
        "title": "Red-Black Trees",
        "part": "Part 6 · Bonus chapters",
        "tagline": "A red-black tree encodes 2-4 balance as colors: no red-red parent-child pair, and every root-to-leaf path has the same black height.",
        "minutes": 55,
        "tags": ["red-black", "color", "rotation", "recolor", "balance", "tree"],
        "objectives": [
            "State the red-black invariants and check them on a small tree.",
            "Trace a recoloring and the rotation that follows.",
            "Explain how red-black rules correspond to 2-4 tree nodes.",
        ],
        "subgoals": [
            {
                "title": "Colors encode structure",
                "idea": "A black node with red children represents a 4-node. A black node with one red child represents a 3-node. Colors store multiway information in a binary tree.",
            },
            {
                "title": "No red-red edge",
                "idea": "A red node cannot have a red parent. This prevents long runs of unbalanced nodes and bounds the height.",
            },
            {
                "title": "Black height is uniform",
                "idea": "Every path from a node to a null leaf passes through the same number of black nodes. That is the balance guarantee.",
            },
            {
                "title": "Recolor first, rotate second",
                "idea": "If the parent and uncle are red, recolor them black and the grandparent red. If the uncle is black, rotate to restore the shape.",
            },
            {
                "title": "The root is black",
                "idea": "Recoloring the root black after insertion preserves the invariants and does not change the black-height relationship.",
            },
        ],
        "example": {
            "title": "Insert 25: red parent, red child",
            "intro": "The new node is red. The fix depends on the uncle's color, not on the new node alone.",
            "code": [
                "// tree: 20 black, 10 black, 30 red",
                "// insert 25 as the left child of 30",
                "// 30 is red and 25 is red: violation",
                "// uncle is 10, which is black",
                "// rotate right at 30, then recolor",
                "// root remains black",
            ],
            "steps": [
                {
                    "label": "Start",
                    "note": "The tree is balanced: black root 20, black 10, red 30. Every path has the same black height.",
                },
                {
                    "label": "Insert red 25",
                    "note": "New nodes start red. 25 is less than 30, so it becomes the left child.",
                },
                {
                    "label": "Detect red-red",
                    "note": "30 is red and its child 25 is red, violating the no-red-red rule.",
                },
                {
                    "label": "Check the uncle",
                    "note": "The uncle is 10, which is black. A recolor alone would not fix the shape, so a rotation is required.",
                },
                {
                    "label": "Rotate and recolor",
                    "note": "25 moves up, 30 moves down-right, and colors are reassigned to restore both invariants.",
                },
            ],
            "predict": {
                "prompt": "If the uncle were red, would a rotation be required?",
                "answer": "No. Recolor the parent, uncle, and grandparent; the red-red violation moves upward and may resolve at the root. Rotate only when the uncle is black.",
            },
        },
        "engine": "structure",
        "spec": {
            "hint": "Amber marks red; slate marks black. Check the root-to-leaf black count after every change.",
            "mode": "tree",
            "frames": [
                {
                    "label": "valid tree",
                    "nodes": [
                        {"id": "20", "label": "20", "x": 0.5, "y": 0.08, "tone": "slate"},
                        {"id": "10", "label": "10", "x": 0.27, "y": 0.42, "parent": "20", "tone": "slate"},
                        {"id": "30", "label": "30", "x": 0.73, "y": 0.42, "parent": "20", "tone": "amber"},
                    ],
                    "highlight": [],
                    "note": "Root black; red 30 has no red children. Paths have the same black count.",
                },
                {
                    "label": "insert red 25",
                    "nodes": [
                        {"id": "20", "label": "20", "x": 0.5, "y": 0.08, "tone": "slate"},
                        {"id": "10", "label": "10", "x": 0.27, "y": 0.42, "parent": "20", "tone": "slate"},
                        {"id": "30", "label": "30", "x": 0.73, "y": 0.42, "parent": "20", "tone": "amber"},
                        {"id": "25", "label": "25", "x": 0.6, "y": 0.76, "parent": "30", "tone": "amber"},
                    ],
                    "highlight": ["30", "25"],
                    "note": "Red 25 under red 30 violates the no-red-red invariant.",
                },
                {
                    "label": "rotate right at 30",
                    "nodes": [
                        {"id": "20", "label": "20", "x": 0.5, "y": 0.08, "tone": "slate"},
                        {"id": "10", "label": "10", "x": 0.27, "y": 0.42, "parent": "20", "tone": "slate"},
                        {"id": "25", "label": "25", "x": 0.7, "y": 0.42, "parent": "20", "tone": "amber"},
                        {"id": "30", "label": "30", "x": 0.84, "y": 0.76, "parent": "25", "tone": "amber"},
                    ],
                    "highlight": ["25"],
                    "note": "The rotation restores the binary search order but the colors still need work.",
                },
                {
                    "label": "recolor",
                    "nodes": [
                        {"id": "20", "label": "20", "x": 0.5, "y": 0.08, "tone": "slate"},
                        {"id": "10", "label": "10", "x": 0.27, "y": 0.42, "parent": "20", "tone": "slate"},
                        {"id": "25", "label": "25", "x": 0.7, "y": 0.42, "parent": "20", "tone": "slate"},
                        {"id": "30", "label": "30", "x": 0.84, "y": 0.76, "parent": "25", "tone": "amber"},
                    ],
                    "highlight": ["25", "30"],
                    "note": "25 becomes black and 30 stays red. No red-red pair remains.",
                },
                {
                    "label": "invariant check",
                    "nodes": [
                        {"id": "20", "label": "20", "x": 0.5, "y": 0.08, "tone": "slate"},
                        {"id": "10", "label": "10", "x": 0.27, "y": 0.42, "parent": "20", "tone": "slate"},
                        {"id": "25", "label": "25", "x": 0.7, "y": 0.42, "parent": "20", "tone": "slate"},
                        {"id": "30", "label": "30", "x": 0.84, "y": 0.76, "parent": "25", "tone": "amber"},
                    ],
                    "highlight": ["20"],
                    "note": "Every path passes through the same number of black nodes, and the root is black.",
                },
            ],
        },
        "parsons": {
            "prompt": "Rebuild the recolor step when the uncle is red.",
            "lines": [
                "parent.setRed(false);",
                "uncle.setRed(false);",
                "grandparent.setRed(true);",
                "node = grandparent;",
                "// continue fixing upward",
            ],
            "solution": [0, 1, 2, 3, 4],
            "seed": 41,
            "why": "Parent and uncle turn black, the grandparent turns red, and the violation moves up the tree.",
        },
        "quiz": [
            {
                "q": "Which pair is forbidden in a red-black tree?",
                "options": ["Black parent, red child", "Red parent, red child", "Black root", "Red leaf"],
                "a": 1,
                "why": "A red node may not have a red parent.",
            },
            {
                "q": "What does uniform black height guarantee?",
                "options": [
                    "Every node has two children",
                    "No root-to-leaf path is more than twice as long as another",
                    "All keys are sorted",
                    "Every leaf is red",
                ],
                "a": 1,
                "why": "Bounded black height plus no red-red edges bounds the height relative to the shortest path.",
            },
            {
                "q": "What happens when the uncle is black?",
                "options": [
                    "Only recolor, never rotate",
                    "Rotate and recolor to restore the invariants",
                    "Delete the new node",
                    "Make the root red",
                ],
                "a": 1,
                "why": "A rotation is needed to fix the shape before colors can satisfy the rules.",
            },
        ],
        "artifact": {
            "name": "RbNode.java",
            "description": "A red-black node with color helpers and a black-height checker. Add the no-red-red check as a recursive method.",
            "code": """public final class RbNode {
    int key;
    RbNode left;
    RbNode right;
    RbNode parent;
    boolean red;

    RbNode(int key) {
        this.key = key;
        this.red = true;
    }

    static boolean hasRedRed(RbNode node) {
        if (node == null) return false;
        if (node.red && node.parent != null && node.parent.red) return true;
        // TODO: recurse into both children.
        return false;
    }

    static int blackHeight(RbNode node) {
        if (node == null) return 1;
        int left = blackHeight(node.left);
        int right = blackHeight(node.right);
        // TODO: return the uniform black height or a sentinel when the paths disagree.
        return left + (node.red ? 0 : 1);
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_41",
        },
        "reflection": "Explain how a black node with two red children corresponds to a 4-node. Then explain why a red-red pair would correspond to an invalid multiway node.",
        "studio": {
            "title": "Color invariants",
            "intro": "The tree viewer marks red amber and black slate. After each change, check the black count on every path.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/advanced-trees-testing.md#chapter-41-red-black-trees",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_41",
        },
    },
    {
        "n": 42,
        "title": "Testing Using JUnit",
        "part": "Part 6 · Bonus chapters",
        "tagline": "A test is an executable claim: arrange the state, perform one action, assert the observable result.",
        "minutes": 45,
        "tags": ["junit", "test", "assert", "arrange", "act", "assert", "regression"],
        "objectives": [
            "Structure a JUnit test with arrange, act, and assert phases.",
            "Choose assertions that fail with useful messages.",
            "Explain why a test should exercise behavior rather than private implementation.",
        ],
        "subgoals": [
            {
                "title": "One behavior per test",
                "idea": "A test that checks five unrelated facts hides which one failed. Name the test after the behavior and assert the smallest observable outcome.",
            },
            {
                "title": "Arrange, act, assert",
                "idea": "Build the object, call the method once, then compare the result. Setup noise makes the action hard to see.",
            },
            {
                "title": "Assertions carry the contract",
                "idea": "<code>assertEquals</code> compares values; <code>assertThrows</code> checks the exception type. The assertion should state what the caller observes.",
            },
            {
                "title": "Tests must be independent",
                "idea": "A test should not depend on execution order or shared mutable state. Each test creates what it needs.",
            },
            {
                "title": "Regression tests pin a bug",
                "idea": "A test written after a bug reproduces the failure before the fix and passes after it. That is the test's value.",
            },
        ],
        "example": {
            "title": "CalculatorTest: one sum, one assertion",
            "intro": "The test is short because the behavior is small. A failure points at one method and one expectation.",
            "code": [
                "@Test",
                "void addsTwoNumbers() {",
                "    Calculator calculator = new Calculator();",
                "    int result = calculator.add(2, 3);",
                "    assertEquals(5, result);",
                "}",
            ],
            "steps": [
                {
                    "label": "Annotation",
                    "note": "<code>@Test</code> tells JUnit to run the method as an independent test case.",
                },
                {
                    "label": "Arrange",
                    "note": "The calculator is created inside the test, so no other test can change it.",
                },
                {
                    "label": "Act",
                    "note": "One call performs the behavior under test. The result is stored, not printed.",
                },
                {
                    "label": "Assert",
                    "note": "The expected value is compared with the actual value. JUnit reports both on failure.",
                },
                {
                    "label": "Isolation",
                    "note": "No static state is touched, so the test can run in any order and in parallel.",
                },
            ],
            "predict": {
                "prompt": "A test passes when run alone but fails in the full suite. What kind of defect does that suggest?",
                "answer": "Shared mutable state or order dependence. One test is changing something another test assumes, which violates independence.",
            },
        },
        "engine": "trace",
        "spec": {
            "hint": "Follow the test call stack. The assertion is the last frame and produces the pass or fail verdict.",
            "code": [
                "@Test",
                "void addsTwoNumbers() {",
                "    Calculator calculator = new Calculator();",
                "    int result = calculator.add(2, 3);",
                "    assertEquals(5, result);",
                "}",
            ],
            "steps": [
                {
                    "line": 0,
                    "note": "JUnit discovers the annotated method and creates a fresh test instance.",
                    "stack": [{"name": "JUnit", "vars": {"phase": "discover"}}],
                    "output": "",
                },
                {
                    "line": 2,
                    "note": "Arrange: the calculator exists only for this test.",
                    "stack": [
                        {"name": "JUnit", "vars": {"phase": "run"}},
                        {"name": "addsTwoNumbers", "vars": {"calculator": "new"}},
                    ],
                    "output": "",
                },
                {
                    "line": 3,
                    "note": "Act: add(2, 3) is called. The test frame waits for the return value.",
                    "stack": [
                        {"name": "JUnit", "vars": {"phase": "run"}},
                        {"name": "addsTwoNumbers", "vars": {"result": "pending"}},
                        {"name": "Calculator.add", "vars": {"a": "2", "b": "3"}},
                    ],
                    "output": "",
                },
                {
                    "line": 4,
                    "note": "Assert: expected 5, actual 5. The assertion passes.",
                    "stack": [
                        {"name": "JUnit", "vars": {"phase": "assert"}},
                        {"name": "addsTwoNumbers", "vars": {"result": "5"}},
                    ],
                    "output": "",
                },
                {
                    "line": 5,
                    "note": "The test method returns. JUnit records one passing test.",
                    "stack": [{"name": "JUnit", "vars": {"passed": "1", "failed": "0"}}],
                    "output": "OK (1 test)",
                },
            ],
        },
        "parsons": {
            "prompt": "Rebuild a test that expects an exception for a negative withdrawal.",
            "lines": [
                "@Test",
                "void rejectsNegativeWithdrawal() {",
                "    Account account = new Account(100);",
                "    assertThrows(IllegalArgumentException.class, () -> {",
                "        account.withdraw(-5);",
                "    });",
                "}",
            ],
            "solution": [0, 1, 2, 3, 4, 5, 6],
            "seed": 42,
            "why": "The object is arranged first, and the assertion wraps the exact action that should fail.",
        },
        "quiz": [
            {
                "q": "What are the three phases of a focused unit test?",
                "options": [
                    "Compile, run, deploy",
                    "Arrange, act, assert",
                    "Open, read, close",
                    "Mock, stub, spy",
                ],
                "a": 1,
                "why": "Set up the state, perform one action, then check the observable result.",
            },
            {
                "q": "Why should tests be independent?",
                "options": [
                    "JUnit requires alphabetical order",
                    "Shared state makes failures depend on execution order",
                    "Independent tests use less memory",
                    "Assertions cannot run twice",
                ],
                "a": 1,
                "why": "Order dependence turns a test suite into a fragile sequence.",
            },
            {
                "q": "What is the best reason to add a test after fixing a bug?",
                "options": [
                    "To increase the line count",
                    "To reproduce the original failure and prevent its return",
                    "To replace the fix",
                    "To slow down the build",
                ],
                "a": 1,
                "why": "A regression test pins the corrected behavior and fails if the bug returns.",
            },
        ],
        "artifact": {
            "name": "AccountTest.java",
            "description": "Tests for deposit, withdraw, and overdraft. Add a test that proves a rejected withdrawal leaves the balance unchanged.",
            "code": """import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class AccountTest {
    @Test
    void depositIncreasesBalance() {
        Account account = new Account(100);
        account.deposit(50);
        assertEquals(150, account.balance());
    }

    @Test
    void withdrawRejectsOverdraft() {
        Account account = new Account(100);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(150));
        // TODO: assert that the failed withdrawal did not change the balance.
    }

    static final class Account {
        private int balance;

        Account(int balance) {
            this.balance = balance;
        }

        void deposit(int amount) {
            if (amount <= 0) throw new IllegalArgumentException("amount");
            balance += amount;
        }

        void withdraw(int amount) {
            if (amount <= 0 || amount > balance) throw new IllegalArgumentException("amount");
            balance -= amount;
        }

        int balance() {
            return balance;
        }
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_42",
        },
        "reflection": "Write one test name that describes a behavior, not a method name. Explain what a failure of that test would tell you.",
        "studio": {
            "title": "Test call stack",
            "intro": "The trace shows JUnit, the test method, the production method, and the assertion. Each frame has a different responsibility.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/advanced-trees-testing.md#chapter-42-testing-with-junit",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_42",
        },
    },
    {
        "n": 43,
        "title": "Java 8 Features",
        "part": "Part 6 · Bonus chapters",
        "tagline": "A stream pipeline describes a transformation lazily, then executes it once when a terminal operation asks for a result.",
        "minutes": 50,
        "tags": ["stream", "lambda", "functional", "map", "filter", "collect", "optional"],
        "objectives": [
            "Build a stream pipeline with intermediate and terminal operations.",
            "Explain why intermediate operations do not run until a terminal operation is called.",
            "Use a lambda to pass behavior without creating a named class.",
        ],
        "subgoals": [
            {
                "title": "A lambda is a function value",
                "idea": "<code>x -&gt; x * 2</code> is behavior that can be passed to a method. The target type supplies the parameter and return types.",
            },
            {
                "title": "Intermediate operations are lazy",
                "idea": "<code>filter</code>, <code>map</code>, and <code>sorted</code> build a pipeline description. They do not process elements yet.",
            },
            {
                "title": "The terminal operation drives the work",
                "idea": "<code>collect</code>, <code>forEach</code>, <code>count</code>, and <code>reduce</code> request a result. Without one, the pipeline does nothing.",
            },
            {
                "title": "Streams do not mutate the source",
                "idea": "A stream reads from a source and produces a new result. The original collection remains unchanged unless you explicitly modify it.",
            },
            {
                "title": "Optional models absence",
                "idea": "<code>findFirst</code> returns an <code>Optional</code>. Callers must decide what to do when no value exists instead of risking a null.",
            },
        ],
        "example": {
            "title": "BookPipeline: filter, sort, limit",
            "intro": "The chain reads like the question: recent books, longest first, top three. Nothing executes until <code>toList</code>.",
            "code": [
                "List<Book> selected = books.stream()",
                "    .filter(book -> book.year() >= 2014)",
                "    .sorted(Comparator.comparingInt(Book::pages).reversed())",
                "    .limit(3)",
                "    .toList();",
            ],
            "steps": [
                {
                    "label": "Open the stream",
                    "note": "<code>stream()</code> creates a view over the list. The list is not copied.",
                },
                {
                    "label": "Describe the filter",
                    "note": "The predicate is stored. No book is tested yet because the pipeline is lazy.",
                },
                {
                    "label": "Describe the order",
                    "note": "The comparator records how to sort. Sorting cannot happen until elements flow through.",
                },
                {
                    "label": "Describe the limit",
                    "note": "The limit is another stage. It may allow short-circuiting if the stream is ordered and the source is sized.",
                },
                {
                    "label": "Terminal operation",
                    "note": "<code>toList</code> pulls elements through the pipeline and returns a new list. The source list is unchanged.",
                },
            ],
            "predict": {
                "prompt": "What does the pipeline do if the <code>toList()</code> line is removed?",
                "answer": "Nothing. The intermediate stages are lazy descriptions; without a terminal operation, no element is processed.",
            },
        },
        "engine": "data",
        "spec": {
            "hint": "Toggle the stages. The pipeline mirrors filter, sorted, and limit; the source rows never change.",
            "columns": ["title", "year", "pages"],
            "rows": [
                ["Modern Java", 2021, 420],
                ["Algorithms in Practice", 2019, 610],
                ["Streams at Work", 2022, 280],
                ["Legacy Patterns", 2013, 350],
                ["Data Structures", 2018, 540],
                ["Testing Habits", 2016, 240],
            ],
            "operations": [
                {"id": "recent", "label": "filter(year >= 2014)", "type": "filter", "column": "year", "op": ">=", "value": 2014},
                {"id": "longest", "label": "sorted(pages, descending)", "type": "sort", "column": "pages", "dir": "desc"},
                {"id": "top", "label": "limit(3)", "type": "limit", "n": 3},
                {"id": "project", "label": "map to title, pages", "type": "select", "columns": ["title", "pages"]},
            ],
            "defaults": ["recent", "longest", "top", "project"],
        },
        "parsons": {
            "prompt": "Rebuild the stream pipeline: recent books, longest first, top three.",
            "lines": [
                "List<Book> selected = books.stream()",
                "    .filter(book -> book.year() >= 2014)",
                "    .sorted(Comparator.comparingInt(Book::pages).reversed())",
                "    .limit(3)",
                "    .toList();",
            ],
            "solution": [0, 1, 2, 3, 4],
            "seed": 43,
            "why": "The source opens the stream, intermediate stages describe the transformation, and toList executes the pipeline.",
        },
        "quiz": [
            {
                "q": "When do intermediate stream operations process elements?",
                "options": [
                    "Immediately when called",
                    "When a terminal operation runs",
                    "When the stream is garbage collected",
                    "Never",
                ],
                "a": 1,
                "why": "Intermediate stages are lazy; a terminal operation drives the traversal.",
            },
            {
                "q": "Does <code>books.stream().filter(...)</code> modify the original list?",
                "options": [
                    "Yes, it removes elements",
                    "No, it produces a new result",
                    "Only if the list is sorted",
                    "Only for the first element",
                ],
                "a": 1,
                "why": "Streams do not mutate the source collection.",
            },
            {
                "q": "What problem does <code>Optional</code> address?",
                "options": [
                    "Slow sorting",
                    "The absence of a result without a bare null",
                    "Thread synchronization",
                    "File encoding",
                ],
                "a": 1,
                "why": "Optional makes absence explicit in the return type and forces the caller to handle it.",
            },
        ],
        "artifact": {
            "name": "StreamReport.java",
            "description": "A stream pipeline over books. Add a grouping by decade and a count without writing an explicit loop.",
            "code": """import java.util.Comparator;
import java.util.List;

public class StreamReport {
    public record Book(String title, int year, int pages) {}

    public static List<String> longestRecent(List<Book> books, int minimumYear, int limit) {
        return books.stream()
                .filter(book -> book.year() >= minimumYear)
                .sorted(Comparator.comparingInt(Book::pages).reversed())
                .limit(limit)
                .map(Book::title)
                .toList();
    }

    public static long totalPages(List<Book> books) {
        // TODO: use mapToInt and sum instead of an explicit loop.
        return 0;
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_43",
        },
        "reflection": "Rewrite one loop from an earlier chapter as a stream pipeline. Then name one case where the loop is clearer and keep it.",
        "studio": {
            "title": "Lazy pipeline",
            "intro": "The data viewer applies stages in order. Notice that the source is unchanged and each toggle adds one description, not one copy.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/collections-algorithms.md#chapter-43-aggregate-operations-for-collection-streams",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_43",
        },
    },
]
