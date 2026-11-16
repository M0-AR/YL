# Enterprise Java: From One Thread to Many Services

This volume covers the part of the curriculum where a Java program stops being a single process with a `main` method and becomes a system with concurrency, network boundaries, persistence, and a lifecycle. The chapters are independent enough to read out of order, but the recurring theme is the same: **every boundary creates a failure mode that the compiler cannot catch.**

- [Chapter 30 — Multithreading and Parallelism](#chapter-30-multithreading-and-parallelism)
- [Chapter 31 — Networking](#chapter-31-networking)
- [Chapter 32 — JDBC](#chapter-32-jdbc)
- [Chapter 33 — JavaServer Faces](#chapter-33-javaserver-faces)
- [Chapter 35 — Advanced Database Programming](#chapter-35-advanced-database-programming)
- [Chapter 36 — Internationalization](#chapter-36-internationalization)
- [Chapter 37 — Servlets](#chapter-37-servlets)
- [Chapter 38 — JavaServer Pages](#chapter-38-javaserver-pages)
- [Chapter 39 — Web Services](#chapter-39-web-services)

---

<a id="chapter-30-multithreading-and-parallelism"></a>
<a name="chapter-30-multithreading-and-parallelism"></a>
## Chapter 30 — Multithreading and Parallelism

### The idea

A thread is a path of execution that shares memory with other threads in the same process. Sharing makes communication cheap. It also makes correctness hard, because two threads can read and write the same state without seeing each other’s changes in a predictable order.

There are two different goals, and they need different tools:

- **Concurrency** — many tasks make progress at once, often because they are waiting on I/O.
- **Parallelism** — many tasks run at the same instant on different CPU cores.

A web server is usually concurrent: most of its threads are waiting. A numerical simulation is usually parallel: the work is CPU-bound and splitting it reduces wall-clock time.

### Starting work safely

Do not create a raw `Thread` for every task. Use an executor, which separates *what* to run from *how* many threads run it.

```java
ExecutorService pool = Executors.newFixedThreadPool(4);

Future<Integer> sum = pool.submit(() -> {
    int total = 0;
    for (int i = 1; i <= 1_000_000; i++) total += i;
    return total;
});

int result = sum.get(); // blocks until ready
pool.shutdown();
```

`Future.get()` blocks and can throw `ExecutionException` if the task failed. `CompletableFuture` composes asynchronous steps without blocking the caller:

```java
CompletableFuture.supplyAsync(() -> fetchPrice("AAPL"))
    .thenApply(price -> price * 100)
    .thenAccept(System.out::println)
    .exceptionally(error -> {
        error.printStackTrace();
        return null;
    });
```

Java 21 adds **virtual threads**, which are cheap enough to create one per request. They do not make code faster; they make blocking code scale. The mental model stays the same: a virtual thread still needs safe access to shared state.

### The race condition

```java
class Counter {
    private int value;

    void increment() {
        value++; // read, add, write — three steps, not one
    }
}
```

If two threads call `increment` at the same time, both can read the same old value and one update is lost. The fix is not “add a sleep.” The fix is to make the read-modify-write atomic:

```java
class Counter {
    private final AtomicInteger value = new AtomicInteger();

    void increment() {
        value.incrementAndGet();
    }
}
```

For a larger invariant involving several fields, use a lock:

```java
private final Object lock = new Object();

void transfer(Account from, Account to, int amount) {
    synchronized (lock) {
        if (from.balance() < amount) throw new IllegalStateException("insufficient");
        from.withdraw(amount);
        to.deposit(amount);
    }
}
```

A single global lock is correct but serializes everything. Finer-grained locks improve throughput and introduce deadlock risk. If two transfers lock accounts in different orders, each can hold one lock and wait for the other. The standard fix is a **global lock order**: always lock the lower account ID first.

### Visibility, not just atomicity

The Java Memory Model allows a thread to cache values. `synchronized` and `volatile` establish happens-before relationships:

- Unlocking a monitor happens-before a later lock of the same monitor.
- A write to a `volatile` field happens-before a later read of that field.

`volatile` is enough for a flag or a published immutable reference. It is not enough for `count++`, because the increment is not atomic.

### What to notice

- **Thread leaks are memory leaks.** An executor that is never shut down can keep the JVM alive.
- **Blocking inside a synchronized block is dangerous.** It holds the lock while waiting.
- **Concurrent collections are not a license to ignore invariants.** `ConcurrentHashMap` makes each operation safe; a check-then-act sequence across two operations still needs care.
- **Measure before parallelizing.** Coordination overhead can make a small task slower on many threads.

### Practice

1. Write a counter with `int`, `AtomicInteger`, and `synchronized`. Run 10 threads × 100,000 increments and compare the totals.
2. Implement a bounded producer-consumer queue with `BlockingQueue`. Shut it down cleanly.
3. Create two `CompletableFuture` tasks and combine them with `thenCombine`. Add a timeout and a fallback.

### Checkpoint

You are ready to move on when you can explain the difference between concurrency and parallelism, why `count++` is not atomic, what `volatile` guarantees, and how lock ordering prevents deadlock.

---

<a id="chapter-31-networking"></a>
<a name="chapter-31-networking"></a>
## Chapter 31 — Networking

### The idea

A network connection is two programs agreeing on a protocol over a stream of bytes. Java’s `Socket` API gives you a TCP connection: ordered, reliable bytes, with no message boundaries.

That last clause is the source of most beginner bugs. TCP does not preserve your `write` calls as messages. If the sender writes `"hello"` and `"world"`, the receiver may read `"helloworld"`, or `"hel"` then `"loworld"`. A protocol needs **framing**: a newline, a length prefix, or a fixed-size record.

### A small TCP server

```java
try (ServerSocket server = new ServerSocket(5050)) {
    while (true) {
        Socket client = server.accept();
        new Thread(() -> handle(client)).start();
    }
}

static void handle(Socket client) {
    try (client;
         BufferedReader in = new BufferedReader(
             new InputStreamReader(client.getInputStream(), StandardCharsets.UTF_8));
         BufferedWriter out = new BufferedWriter(
             new OutputStreamWriter(client.getOutputStream(), StandardCharsets.UTF_8))) {
        String line;
        while ((line = in.readLine()) != null) {
            out.write("echo: " + line);
            out.newLine();
            out.flush();
        }
    } catch (IOException error) {
        error.printStackTrace();
    }
}
```

`readLine()` hides the framing: it reads until `\n`. That works for a line protocol and not for binary data. For binary protocols, read exactly `N` bytes with `readNBytes` or a length header.

### Timeouts are mandatory

A socket operation can block forever if the peer disappears without closing the connection. Set a connect timeout and a read timeout:

```java
Socket socket = new Socket();
socket.connect(new InetSocketAddress("example.com", 80), 3_000);
socket.setSoTimeout(5_000);
```

The connect timeout bounds the handshake. `setSoTimeout` bounds each read. Neither is optional in production code.

### HTTP without a library

The JDK’s `HttpClient` handles the protocol details:

```java
HttpClient client = HttpClient.newBuilder()
    .connectTimeout(Duration.ofSeconds(5))
    .build();

HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.github.com/repos/M0-AR/YL"))
    .header("Accept", "application/vnd.github+json")
    .timeout(Duration.ofSeconds(10))
    .GET()
    .build();

HttpResponse<String> response = client.send(
    request, HttpResponse.BodyHandlers.ofString());
System.out.println(response.statusCode());
System.out.println(response.body());
```

Use `sendAsync` when you cannot block. Check the status code before parsing the body; a JSON parser given an HTML error page produces a confusing exception.

### Blocking threads vs non-blocking I/O

The thread-per-connection model is simple and works well for hundreds of connections. For tens of thousands, thread-per-connection becomes expensive. Options:

- **Virtual threads** (Java 21) — keep the blocking style, reduce the cost per thread.
- **NIO selectors** — one thread multiplexes many channels, at the cost of a more complex programming model.
- **An asynchronous framework** — Netty, Vert.x, or a servlet container with non-blocking support.

Do not choose the complex model before measuring. Most business services never need it.

### What to notice

- **TCP is a stream, not a message queue.** Frame your data.
- **Close resources with try-with-resources.** A leaked socket leaks a file descriptor.
- **Character encoding must be explicit.** `new InputStreamReader(stream)` uses the platform default, which varies by machine.
- **A server that spawns a thread per request needs a limit.** Otherwise a traffic spike becomes an outage.

### Practice

1. Extend the echo server with a `QUIT` command and a per-client connection count.
2. Write a client that sends 10 lines and reads 10 responses. Break it by reading a fixed 5 bytes at a time; explain the result.
3. Fetch a JSON endpoint with `HttpClient`, check the status, and print only one field. Add a timeout.

### Checkpoint

You are ready to move on when you can explain why TCP needs framing, why timeouts are not optional, and when thread-per-connection stops being a good design.

---

<a id="chapter-32-jdbc"></a>
<a name="chapter-32-jdbc"></a>
## Chapter 32 — JDBC

### The idea

JDBC is a set of interfaces that let Java talk to a relational database without compiling against one vendor. The driver implements the interfaces; your code depends on `java.sql`.

The flow is always:

1. Obtain a `Connection`.
2. Create a `PreparedStatement`.
3. Bind parameters.
4. Execute and read a `ResultSet`.
5. Close everything, usually with try-with-resources.
6. Commit or roll back.

### A safe query

```java
String sql = "select id, name, balance from account where balance >= ?";

try (Connection connection = dataSource.getConnection();
     PreparedStatement statement = connection.prepareStatement(sql)) {

    statement.setInt(1, 1_000);

    try (ResultSet rows = statement.executeQuery()) {
        while (rows.next()) {
            long id = rows.getLong("id");
            String name = rows.getString("name");
            int balance = rows.getInt("balance");
            System.out.printf("%d %s %d%n", id, name, balance);
        }
    }
}
```

The `?` placeholder is not string substitution. The driver sends the value separately, so a value such as `'; drop table account; --` is treated as data, not SQL. **Never build a query by concatenating user input.** `PreparedStatement` is the default; string concatenation is a security bug.

### Transactions

By default, a connection is in autocommit mode: every statement commits immediately. For a transfer, turn autocommit off and make the unit explicit:

```java
try (Connection connection = dataSource.getConnection()) {
    connection.setAutoCommit(false);
    try {
        withdraw(connection, fromId, amount);
        deposit(connection, toId, amount);
        connection.commit();
    } catch (SQLException error) {
        connection.rollback();
        throw error;
    }
}
```

The transaction is the boundary of atomicity. If the process dies between the two updates and autocommit was on, the database is inconsistent.

### Isolation is a trade-off

SQL defines four isolation levels, each preventing a different anomaly:

| Level | Dirty read | Non-repeatable read | Phantom read |
| --- | --- | --- | --- |
| Read uncommitted | possible | possible | possible |
| Read committed | prevented | possible | possible |
| Repeatable read | prevented | prevented | possible |
| Serializable | prevented | prevented | prevented |

Higher isolation is safer and more expensive. Most databases default to read committed; some use repeatable read or snapshot isolation. Know your database’s default; do not assume the SQL standard’s.

### Batching and generated keys

```java
try (PreparedStatement statement = connection.prepareStatement(
        "insert into account(name, balance) values (?, ?)",
        Statement.RETURN_GENERATED_KEYS)) {

    for (Account account : accounts) {
        statement.setString(1, account.name());
        statement.setInt(2, account.balance());
        statement.addBatch();
    }
    statement.executeBatch();

    try (ResultSet keys = statement.getGeneratedKeys()) {
        while (keys.next()) System.out.println(keys.getLong(1));
    }
}
```

Batching reduces round trips. It does not make the whole batch atomic; wrap it in a transaction if that is the requirement.

### What to notice

- **A `Connection` is expensive.** Use a pool in a server; create one per command-line run in a demo.
- **Close in reverse order.** Try-with-resources does this for you.
- **SQL exceptions carry state.** Read `getSQLState()` and the vendor error code before showing a generic failure.
- **ORM is not a replacement for SQL knowledge.** It generates SQL; you still need to read the query plan when it is slow.

### Practice

1. Create an in-memory table with a unique constraint and insert a duplicate. Catch the `SQLException` and print its SQL state.
2. Implement a transfer with a transaction and force an error after the first update. Verify that the rollback restores both balances.
3. Compare 1,000 individual inserts with one batch. Measure the wall-clock difference.

### Checkpoint

You are ready to move on when you can explain why `PreparedStatement` prevents injection, what autocommit does, how a rollback works, and why a connection pool exists.

---

<a id="chapter-33-javaserver-faces"></a>
<a name="chapter-33-javaserver-faces"></a>
## Chapter 33 — JavaServer Faces

### The idea

JSF is a component-based web framework. Instead of writing a servlet that prints HTML, you declare a page as a tree of components — input, button, table, message — and the framework handles the request lifecycle, conversion, validation, and rendering.

The mental model is closer to a desktop UI toolkit than to a request/response API:

- A **Facelets** page (`.xhtml`) describes the component tree.
- A **managed bean** or CDI bean holds the page state and actions.
- The **FacesServlet** receives a request, restores the view, applies request values, validates, updates the model, invokes actions, and renders the response.
- **Navigation** is usually a logical outcome from an action method.
- **Ajax** can partially re-render components without a full page load.

Modern JSF lives under the Jakarta EE namespace (`jakarta.faces.*`). The older `javax.faces.*` namespace belongs to Java EE 8 and earlier. Mixing the two is a common deployment error.

### A minimal page and bean

```xhtml
<h:form>
    <h:outputLabel for="name" value="Name"/>
    <h:inputText id="name" value="#{greeter.name}" required="true"/>
    <h:message for="name" styleClass="error"/>

    <h:commandButton value="Greet" action="#{greeter.greet}">
        <f:ajax execute="@form" render="result"/>
    </h:commandButton>

    <h:outputText id="result" value="#{greeter.message}"/>
</h:form>
```

```java
@Named
@RequestScoped
public class Greeter {
    private String name;
    private String message;

    public String greet() {
        message = "Hello, " + name;
        return null; // stay on the same view
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getMessage() { return message; }
}
```

`@Named` comes from CDI, not JSF. The container resolves the bean, and the expression language reads and writes its properties.

### The lifecycle is the framework

JSF’s six phases are not trivia; they explain the bugs:

1. **Restore view** — rebuild the component tree.
2. **Apply request values** — copy submitted values into components.
3. **Process validations** — convert and validate.
4. **Update model values** — write valid values into the bean.
5. **Invoke application** — run action methods.
6. **Render response** — produce HTML.

If validation fails, phases 4–5 are skipped and the page is re-rendered with errors. That is why an action method should not assume the bean already contains the submitted value.

### What to notice

- **Scope matters.** A request-scoped bean loses state on the next request. A view-scoped bean survives Ajax within one view. A session-scoped bean survives across views but is shared across tabs.
- **Component IDs are part of the contract.** Ajax `render` targets IDs, not CSS selectors. Wrapping components in naming containers changes the generated client ID.
- **Validation belongs at the boundary.** Use standard validators and custom `Validator` implementations; do not duplicate the same rule in every action.
- **JSF is stateful.** It stores the component tree between requests, which simplifies development and complicates clustering. Modern deployments use stateless mode or a shared session store.

### Practice

1. Add an email field with a custom validator and an `<h:message>` next to it.
2. Convert the greeting to a view-scoped bean and verify that the message survives an Ajax request.
3. Change the action to navigate to a second page and pass one value through a flash scope.

### Checkpoint

You are ready to move on when you can explain what a component tree is, why the JSF lifecycle skips the model update on a validation error, and how bean scope changes the behavior of a page.

---

<a id="chapter-35-advanced-database-programming"></a>
<a name="chapter-35-advanced-database-programming"></a>
## Chapter 35 — Advanced Database Programming

### The idea

At this point the question is no longer “how do I run a query?” It is “how do I keep a database correct and fast while many clients use it?” That requires four skills:

1. **Transactions** — grouping work into an atomic unit.
2. **Isolation** — choosing what concurrent transactions may see.
3. **Locking** — preventing lost updates without deadlocking.
4. **Set-based thinking** — letting the database do the work instead of shipping rows to Java.

### ACID, briefly

- **Atomicity** — all or nothing.
- **Consistency** — constraints remain true.
- **Isolation** — concurrent transactions do not corrupt each other.
- **Durability** — committed data survives a crash.

The database enforces atomicity and durability. Your code chooses transaction boundaries and isolation. A transaction that spans a user’s “think time” is a design error, not a feature.

### Lost updates and optimistic locking

Two clerks read the same balance, each adds 100, and each writes back. One update disappears. Pessimistic locking prevents it with `select ... for update`; optimistic locking detects it with a version column:

```sql
update account
set balance = balance + ?, version = version + 1
where id = ? and version = ?;
```

If the update count is zero, someone else changed the row. The application retries or reports a conflict. Optimistic locking scales better when conflicts are rare; pessimistic locking is simpler when conflicts are common and the lock is held briefly.

### Stored procedures and triggers

A stored procedure moves a multi-step operation next to the data:

```sql
create procedure transfer(
    in p_from bigint,
    in p_to bigint,
    in p_amount numeric(12,2)
)
language sql
begin atomic
    update account set balance = balance - p_amount where id = p_from;
    update account set balance = balance + p_amount where id = p_to;
end;
```

Benefits: fewer round trips, a stable execution plan, and a permission boundary. Costs: logic lives in a second language, versioning is harder, and debugging is less familiar to Java developers.

A trigger runs automatically on insert, update, or delete. Use triggers for auditing and derived integrity rules — not for hidden business workflows that surprise the next developer.

### Batch, metadata, and row sets

- **Batch updates** reduce network round trips. Use them for bulk inserts, not for a single user action.
- **Database metadata** (`DatabaseMetaData`, `ResultSetMetaData`) lets tools discover tables, columns, and types at runtime.
- **`RowSet`** is a disconnected, serializable result set. It is useful for small offline datasets and is not a replacement for a real cache.
- **Connection pools** (HikariCP, Agroal) amortize connection cost and enforce limits. A pool without a timeout is a queue that can grow until memory runs out.

### What to notice

- **N+1 queries are the classic performance bug.** Loading 100 orders and then one customer query per order is 101 round trips.
- **Transaction boundaries belong to the service layer.** A repository method that commits on its own cannot participate in a larger unit of work.
- **Indexes are a write-time cost.** Add them for real query patterns, not for every column.
- **Migrations are part of the application.** Schema changes must be versioned, reviewed, and reversible.

### Practice

1. Implement optimistic locking on a table with a `version` column. Run two concurrent updates and handle the conflict.
2. Write a query that finds the top five customers by total order value. Compare a correlated subquery with a join and `group by`.
3. Add an audit trigger that records every balance change with a timestamp.

### Checkpoint

You are ready to move on when you can explain optimistic vs pessimistic locking, why a transaction should be short, what an N+1 query is, and when a stored procedure is worth the operational cost.

---

<a id="chapter-36-internationalization"></a>
<a name="chapter-36-internationalization"></a>
## Chapter 36 — Internationalization

### The idea

Internationalization (i18n) is the discipline of writing software that does not assume the user’s language, country, currency, calendar, or writing direction. Localization (l10n) is the work of adding a specific locale.

The core rule: **separate text and formatting from logic.** A string concatenated in code is hard to translate; a date formatted with the platform default is wrong for most of the world.

### Locale and resources

```java
Locale locale = Locale.forLanguageTag("ar-SY");
ResourceBundle messages = ResourceBundle.getBundle("Messages", locale);

String greeting = messages.getString("greeting");
String itemCount = MessageFormat.format(
    messages.getString("items"), 3);

System.out.println(greeting);
System.out.println(itemCount);
```

`Messages.properties` is the fallback; `Messages_ar.properties` is the Arabic bundle; `Messages_ar_SY.properties` is the country-specific override. The lookup walks from most specific to least specific.

Use `MessageFormat` for placeholders because word order changes between languages. Do not build a sentence by concatenating fragments; translators need the whole sentence and its grammatical context.

### Formatting is locale-sensitive

```java
NumberFormat money = NumberFormat.getCurrencyInstance(locale);
DateFormat date = DateFormat.getDateInstance(DateFormat.LONG, locale);

System.out.println(money.format(1234.5));
System.out.println(date.format(new Date()));
```

`1,234.50`, `1.234,50`, and `١٬٢٣٤٫٥٠` are the same number in different locales. A hardcoded `"%.2f"` is not a currency format; it ignores grouping, symbol, and decimal separator.

For modern date-time code, prefer `java.time` with `DateTimeFormatter`:

```java
DateTimeFormatter formatter = DateTimeFormatter
    .ofLocalizedDateTime(FormatStyle.MEDIUM)
    .withLocale(locale);
String text = ZonedDateTime.now(ZoneId.of("Asia/Damascus")).format(formatter);
```

### Unicode, encodings, and collation

- **Unicode** is the character set. **UTF-8** is an encoding of it. Use UTF-8 at every boundary: files, database, HTTP, and console.
- **Normalization** matters: the same visual text can have different code-point sequences. Use `Normalizer.normalize` when comparing user input that may come from different systems.
- **Collation** is language-specific ordering. `Collator.getInstance(locale)` sorts names the way a speaker expects; `String.compareTo` sorts by code point.
- **Right-to-left** languages need layout support, not just translated strings. Use logical properties and test the actual UI.

### What to notice

- **Never use `Locale.getDefault()` for user-facing formatting** unless the default is genuinely the user’s choice. Pass the locale explicitly.
- **Plural rules are not “add s.”** `MessageFormat` has plural support; some languages have more than two forms.
- **Time zones are not offsets.** Store `Instant` or `ZonedDateTime`, not a local time with no zone.
- **Translation is not a find-and-replace.** Text length changes, word order changes, and images may be culturally specific.

### Practice

1. Create English and Arabic bundles for a three-line checkout message. Format the total with the correct currency.
2. Format the same `Instant` for `America/New_York` and `Asia/Damascus`. Explain why the dates can differ.
3. Sort a list of names with `String.compareTo` and with a `Collator`. Find a pair that changes order.

### Checkpoint

You are ready to move on when you can explain the difference between a locale and an encoding, why concatenated strings are hard to translate, and why a timestamp needs a time zone.

---

<a id="chapter-37-servlets"></a>
<a name="chapter-37-servlets"></a>
## Chapter 37 — Servlets

### The idea

A servlet is a Java class that handles HTTP requests inside a container. The container — Tomcat, Jetty, Undertow — owns the network, the thread pool, and the servlet lifecycle. Your class receives a request and writes a response.

The lifecycle is small:

1. The container creates one servlet instance (usually).
2. It calls `init()` once.
3. It calls `service()` for every request, which dispatches to `doGet`, `doPost`, and so on.
4. It calls `destroy()` once before shutdown.

Because one instance serves many requests concurrently, **a servlet must be thread-safe**. Instance fields are shared state. Use local variables for request data and synchronize only when you genuinely need shared mutable state.

### A minimal servlet

```java
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        String name = request.getParameter("name");
        if (name == null || name.isBlank()) name = "world";

        response.setContentType("text/plain; charset=UTF-8");
        response.getWriter().printf("Hello, %s%n", name);
    }
}
```

`@WebServlet` requires the Jakarta Servlet API and a container that scans annotations. In a traditional WAR, the container also reads `WEB-INF/web.xml`.

### Request, response, session

- **Request parameters** come from the query string or a form body. They are strings; validate and convert them.
- **Headers** carry content type, authentication, caching, and cookies.
- **Session** is server-side state keyed by a cookie. It is convenient and easy to abuse; store only what you must, and set a timeout.
- **Cookies** are client-side state. They are visible to the user and must be treated as untrusted input.

```java
HttpSession session = request.getSession(false);
if (session == null || session.getAttribute("user") == null) {
    response.sendRedirect("login");
    return;
}
```

### Filters and listeners

A filter wraps a request before or after the servlet:

```java
@WebFilter("/*")
public class EncodingFilter implements Filter {
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }
}
```

Use filters for cross-cutting concerns: encoding, authentication, logging, compression, CORS. A listener observes application or session lifecycle events; it is useful for startup resources, not per-request logic.

### What to notice

- **Do not write HTML by concatenating strings.** Use a template engine (JSP, Thymeleaf, Freemarker) and escape untrusted values.
- **Set the content type and charset before writing.** A response committed with the wrong encoding cannot be fixed later.
- **A servlet is not a singleton by accident; it is a singleton by design.** Treat fields as shared.
- **Deployment is part of the lesson.** A WAR needs a container; a `main` method is not enough.

### Practice

1. Add a `doPost` that reads a form field and returns a JSON response. Set the status to 400 for a missing field.
2. Write a filter that adds a request ID to every response header.
3. Store a username in the session and protect a second servlet with a login check.

### Checkpoint

You are ready to move on when you can explain the servlet lifecycle, why servlet fields are dangerous, what a filter does, and how a session differs from a cookie.

---

<a id="chapter-38-javaserver-pages"></a>
<a name="chapter-38-javaserver-pages"></a>
## Chapter 38 — JavaServer Pages

### The idea

JSP is a template technology: an HTML page with embedded dynamic expressions, compiled by the container into a servlet. It is older than JSF and still useful for simple server-rendered views, especially in legacy systems.

The modern rule is **JSP as a view only**:

- No database calls in the page.
- No business logic in scriptlets.
- No Java code when an expression language (EL) and JSTL can do the job.
- A servlet or controller prepares the model, forwards to the JSP, and the JSP renders it.

### A clean page

```jsp
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="en">
<head><title>Accounts</title></head>
<body>
  <h1>Accounts</h1>

  <c:choose>
    <c:when test="${empty accounts}">
      <p>No accounts yet.</p>
    </c:when>
    <c:otherwise>
      <table>
        <c:forEach items="${accounts}" var="account">
          <tr>
            <td><c:out value="${account.name}"/></td>
            <td><c:out value="${account.balance}"/></td>
          </tr>
        </c:forEach>
      </table>
    </c:otherwise>
  </c:choose>
</body>
</html>
```

`<c:out>` escapes HTML. That is not decoration; it is the difference between a page and a cross-site scripting vulnerability. Use it for every value that came from a user, even if you “know” it is safe.

### EL and scope

EL resolves names from page, request, session, and application scope, in that order. `${accounts}` finds the attribute set by the controller. You can be explicit with `${requestScope.accounts}` when the page grows and ambiguity becomes a risk.

EL is not a programming language. Keep conditions small. If a page needs a loop with a counter and three branches, the model is probably wrong.

### Includes and layouts

- `<%@ include file="..." %>` is a static include at translation time.
- `<jsp:include page="..." />` is a runtime include.
- Tag files (`.tag`) package reusable fragments.
- A layout can be approximated with includes or a decorator library.

For new projects, a modern template engine (Thymeleaf, Freemarker) is often a better fit. JSP remains important because a large amount of enterprise Java still runs it.

### What to notice

- **Scriptlets are a maintenance smell.** `<% ... %>` mixes concerns and is hard to test.
- **JSP is compiled.** The first request after a change can be slow; the container caches the compiled servlet.
- **Error pages must be configured.** An unhandled exception should not leak a stack trace to the browser.
- **JSP is not dead, but it is not the default.** Choose it when the deployment already uses it; do not choose it for a new greenfield UI without a reason.

### Practice

1. Move a scriptlet-based page to EL and JSTL. Remove every `<%` block.
2. Add an error page for `500` and a “not found” page for `404`.
3. Create a tag file for a reusable account row and include it from two pages.

### Checkpoint

You are ready to move on when you can explain why JSP should be a view only, what `<c:out>` protects against, and the difference between a static include and a runtime include.

---

<a id="chapter-39-web-services"></a>
<a name="chapter-39-web-services"></a>
## Chapter 39 — Web Services

### The idea

A web service is an interface over HTTP with a contract that other programs can use. REST is not a protocol; it is a set of architectural constraints. The important ones for day-to-day work:

- **Resources** are identified by URLs (`/accounts/42`).
- **Representations** are formats such as JSON or XML.
- **HTTP methods** carry the intent: `GET` reads, `POST` creates, `PUT` replaces, `PATCH` partially updates, `DELETE` removes.
- **Statelessness** means each request carries enough context to be understood alone.
- **Status codes** are part of the contract, not a decoration.

### A JAX-RS resource

```java
@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountResource {

    private final AccountService service;

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") long id) {
        return service.find(id)
            .map(account -> Response.ok(account).build())
            .orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response create(AccountRequest request, @Context UriInfo uriInfo) {
        Account created = service.create(request);
        URI location = uriInfo.getAbsolutePathBuilder()
            .path(Long.toString(created.id()))
            .build();
        return Response.created(location).entity(created).build();
    }
}
```

The annotations describe the contract; the container handles serialization and routing. Keep the resource thin. Business rules belong in a service, not in the HTTP layer.

### Status codes that tell the truth

| Code | Meaning | Typical use |
| --- | --- | --- |
| 200 | OK | successful read |
| 201 | Created | `POST` created a resource, with `Location` |
| 204 | No Content | successful delete or update with no body |
| 400 | Bad Request | malformed input |
| 401 | Unauthorized | missing or invalid authentication |
| 403 | Forbidden | authenticated but not allowed |
| 404 | Not Found | resource does not exist |
| 409 | Conflict | duplicate or version conflict |
| 422 | Unprocessable Entity | syntactically valid but semantically invalid |
| 500 | Internal Server Error | unexpected server failure |

Returning `200` with `{"error": "..."}` for every failure makes clients guess. Use the status line for the class of outcome and the body for the details.

### A client with the JDK

```java
HttpClient client = HttpClient.newBuilder()
    .connectTimeout(Duration.ofSeconds(5))
    .build();

HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://api.example.com/accounts/42"))
    .header("Accept", "application/json")
    .timeout(Duration.ofSeconds(10))
    .GET()
    .build();

HttpResponse<String> response = client.send(
    request, HttpResponse.BodyHandlers.ofString());

if (response.statusCode() == 200) {
    System.out.println(response.body());
} else {
    System.err.println("failed: " + response.statusCode());
}
```

### What to notice

- **Idempotency matters.** A client may retry a `GET`, `PUT`, or `DELETE` safely. A `POST` may create a duplicate; use an idempotency key when retries are possible.
- **Do not leak entities.** A JPA entity with lazy fields can trigger unexpected queries or expose internal columns. Map to a response record.
- **Version the contract.** Add `/v1` or a media type; do not silently change field meanings.
- **Timeouts and pagination are part of the API.** An endpoint that returns everything will eventually return too much.

### Practice

1. Design three endpoints for a task list: list, create, and complete. Choose the status codes and the JSON shape before writing code.
2. Add validation that returns `400` with a field-level error list. Keep the success shape unchanged.
3. Call a public JSON API with `HttpClient`, parse one field, and handle a non-200 response.

### Checkpoint

You are ready to move on when you can choose an HTTP method from the operation’s semantics, explain why `201` includes a `Location`, and describe the difference between `401` and `403`.

---

The final volume goes deeper into balanced trees and then closes the loop with the testing discipline that makes every previous chapter verifiable.
