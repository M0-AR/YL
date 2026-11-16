# Companion Curriculum

This folder is the original teaching companion for the 23 chapters that do not have a dedicated exercise folder in `src/`. It is written for this repository. It does not reproduce chapter text, figures, or exercise statements from *Introduction to Java Programming*.

The goal is simple: every chapter in the 42-chapter map should lead somewhere useful. Sometimes that is 439 worked files. Sometimes it is a compact lesson, a runnable example, a practice task, and a checkpoint that tells you whether the idea actually landed.

## The four volumes

| Volume | Chapters | What it covers |
| --- | --- | --- |
| [Foundations](foundations.md) | 1, 15, 16 | How Java runs, event-driven programming, animation, controls, layout, and media |
| [Collections and Algorithms](collections-algorithms.md) | 21–27, 29 | Sets, maps, algorithm design, sorting, custom collections, BSTs, AVL trees, hashing, weighted graphs |
| [Enterprise Java](enterprise.md) | 30–33, 35–39 | Threads, sockets, JDBC, JSF, advanced databases, internationalization, servlets, JSP, web services |
| [Advanced Trees and Testing](advanced-trees-testing.md) | 40–42 | 2-4 trees, B-trees, red-black trees, and JUnit |

## How to use a lesson

Each lesson follows the same rhythm:

1. **The idea** — a plain-language explanation with a mental model.
2. **A worked example** — small enough to read in one sitting.
3. **What to notice** — the invariant, trade-off, or failure mode that matters.
4. **Practice** — one change you can make and one question you can answer.
5. **Checkpoint** — you are ready to move on when you can explain the answer without looking.

Do not read four volumes in one day. Read one lesson, run one example, then close the tab and rebuild the idea from memory. That last step is where learning happens.

## Runnable code

The Maven project in [`java/`](java/) contains self-contained Java 17 examples for the core lessons and JUnit 5 tests for the data structures. From this folder:

```bash
cd java
mvn test
mvn -q exec:java -Dexec.mainClass=atlas.collections.Sorting
```

Some chapters need a runtime that is not part of a plain JDK:

- **JavaFX (14–16):** the companion event and control demos use Swing so they run anywhere. The JavaFX versions in `src/Exercise_14` need a JavaFX SDK or a build tool that resolves JavaFX.
- **JDBC (32, 35):** the code compiles against `java.sql`; running it needs a JDBC driver and a database.
- **JSF, servlets, JSP (33, 37, 38):** these require a Jakarta EE container. The lessons show the structure and the deployment notes.
- **Web services (39):** the client example uses the JDK's built-in `HttpClient`. A server can be any REST service.

## A note on versions

The original exercise files were written for Java 8–10 and some use JavaFX. The companion examples target Java 17 so they are easy to run today. Where a newer API changes the story — virtual threads, records, `HttpClient`, switch expressions — the lesson says so instead of pretending the old API is the only option.

## Copyright and scope

This is an independent, MIT-licensed companion. The textbook remains the work of its author and publisher. If you are studying the book, keep the book open beside the lesson: the lesson gives you a second explanation, a runnable model, and a place to practice. It is not a replacement for the assigned reading.
