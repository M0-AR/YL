# I/O, Generics, and Graphs: Moving and Shaping Data

This volume covers chapters 17 through 20 and chapter 28. The common question is how data is represented, moved, and traversed. Binary I/O moves bytes, generics make containers type-safe, collections choose a cost model, and graphs model relationships.

- [Chapter 17 — Binary I/O](#chapter-17-binary-io)
- [Chapter 18 — Recursion](#chapter-18-recursion)
- [Chapter 19 — Generics](#chapter-19-generics)
- [Chapter 20 — Lists, Stacks, Queues, and Priority Queues](#chapter-20-lists-stacks-queues)
- [Chapter 28 — Graphs](#chapter-28-graphs)

---

<a id="chapter-17-binary-io"></a>
<a name="chapter-17-binary-io"></a>
## Chapter 17 — Binary I/O

### The idea

Text I/O translates characters to bytes using a charset. Binary I/O writes the bytes of a value directly. A `DataOutputStream` writes primitives in a defined format; a `DataInputStream` reads them back in the same order.

Streams are layered. A low-level stream talks to the file or socket. A buffered stream reduces small reads and writes. A data stream adds typed methods. You assemble the layers you need.

### A worked example

```java
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BinaryNumbers {
    public static void main(String[] args) throws IOException {
        try (DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream("numbers.bin")))) {
            out.writeInt(42);
            out.writeDouble(3.5);
        }

        try (DataInputStream in = new DataInputStream(
                new BufferedInputStream(new FileInputStream("numbers.bin")))) {
            System.out.println(in.readInt() + " " + in.readDouble());
        }
    }
}
```

The reader must know the same order and types as the writer. Binary formats are compact and fast, but they are not self-describing.

### What to notice

- `readInt()` consumes exactly four bytes. If the file ends early, it throws `EOFException`.
- Java’s data streams use big-endian order. Mixing in another format requires a deliberate conversion.
- `ObjectOutputStream` can serialize an object graph, but every class involved must implement `Serializable`.
- `transient` fields are skipped during serialization. They come back as default values.
- `Serializable` is a marker interface with real security and versioning consequences. Do not use it as a default persistence format.
- Try-with-resources closes the outermost stream and, through it, the underlying stream.
- A binary file has no charset. A text editor may show nonsense even when the bytes are correct.

### Practice

1. Write an array of `int` values and read it back without storing the length separately.
2. Serialize a small record-like class, then add a field and observe what happens when you deserialize the old file.
3. Compare the size of the same numbers stored as text and as binary.

### Checkpoint

You are ready to move on when you can explain the difference between a byte stream and a character stream, and when you can name the matching read method for every write method you use.

---

<a id="chapter-18-recursion"></a>
<a name="chapter-18-recursion"></a>
## Chapter 18 — Recursion

### The idea

A recursive method solves a problem by calling itself on a smaller instance. Two things must be true: there is a base case that returns without another call, and every recursive call moves toward that base case.

The call stack holds each unfinished call. Deep recursion can overflow the stack, and a naive recursive formula can repeat the same work exponentially many times.

### A worked example

```java
public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(fib(50));
    }

    static long fib(int n) {
        long[] memo = new long[n + 1];
        return fib(n, memo);
    }

    static long fib(int n, long[] memo) {
        if (n <= 1) return n;
        if (memo[n] != 0) return memo[n];
        memo[n] = fib(n - 1, memo) + fib(n - 2, memo);
        return memo[n];
    }
}
```

Without the memo array, `fib(50)` would make billions of calls. With it, each subproblem is solved once.

### What to notice

- A base case that is never reached causes a `StackOverflowError`.
- Recursion is natural for trees, nested structures, divide-and-conquer, and backtracking.
- The naive Fibonacci recurrence is O(2ⁿ); memoization reduces it to O(n).
- Java does not guarantee tail-call optimization. A deep tail recursion can still overflow.
- A recursive method can return a value on the way down or combine results on the way up.
- Sometimes an explicit stack is clearer and safer than recursion for very deep data.

### Practice

1. Write recursive `sum(int[] values, int index)` and identify the base case.
2. Print every permutation of a short string using backtracking.
3. Solve the same problem recursively and iteratively, then compare readability and stack use.

### Checkpoint

You are ready to move on when you can write the base case and the progress step before writing the recursive call, and when you can explain why memoization changes the cost.

---

<a id="chapter-19-generics"></a>
<a name="chapter-19-generics"></a>
## Chapter 19 — Generics

### The idea

Generics let a class or method work with a type chosen by the caller while keeping compile-time type safety. `Box<String>` and `Box<Integer>` are the same class at runtime, but the compiler prevents you from putting an `Integer` into a `Box<String>`.

Type parameters are erased at runtime. That is why you cannot create `new T[n]`, call `T.class`, or overload solely on a generic type. The compiler inserts casts where needed.

### A worked example

```java
import java.util.function.Function;

class Box<T> {
    private final T value;

    Box(T value) {
        this.value = value;
    }

    T get() {
        return value;
    }

    <R> Box<R> map(Function<T, R> transform) {
        return new Box<>(transform.apply(value));
    }
}

public class BoxDemo {
    public static void main(String[] args) {
        Box<String> name = new Box<>("atlas");
        Box<Integer> length = name.map(String::length);
        System.out.println(length.get());
    }
}
```

The `map` method introduces its own type parameter `R`, so it can return a box of a different type without losing safety.

### What to notice

- Generic type arguments must be reference types. Use `Integer`, not `int`.
- A bounded parameter such as `<T extends Comparable<T>>` lets the method call `compareTo`.
- A wildcard `? extends T` is for reading; `? super T` is for writing. This is the PECS rule.
- Static members cannot use the class’s type parameter because they are shared by all instances.
- Generic arrays are awkward because of erasure. Prefer collections or pass a `Class<T>` token.
- A raw type such as `Box` disables generic checking. Avoid it in new code.

### Practice

1. Write a generic `Pair<A, B>` with `first()` and `second()`.
2. Write `static <T extends Comparable<T>> T max(List<T> values)`.
3. Explain why `List<Object>` cannot be passed to a method that accepts `List<String>`.

### Checkpoint

You are ready to move on when you can explain what erasure removes, and when you can choose between a bounded type parameter and a wildcard.

---

<a id="chapter-20-lists-stacks-queues"></a>
<a name="chapter-20-lists-stacks-queues"></a>
## Chapter 20 — Lists, Stacks, Queues, and Priority Queues

### The idea

A collection interface describes a contract; an implementation chooses the cost. `ArrayList` is fast for indexed access and appends. `LinkedList` is fast for inserting at the ends but slow for indexed access. A `Deque` supports stack and queue operations at both ends. A `PriorityQueue` returns the smallest element according to a comparator, not the oldest.

Choose by the operations you perform most often, not by the name that sounds most familiar.

### A worked example

```java
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class QueueDemo {
    public static void main(String[] args) {
        Queue<String> jobs = new ArrayDeque<>();
        jobs.offer("invoice");
        jobs.offer("report");
        while (!jobs.isEmpty()) {
            System.out.println("printing " + jobs.poll());
        }

        Queue<Integer> shortestFirst = new PriorityQueue<>();
        shortestFirst.add(7);
        shortestFirst.add(2);
        shortestFirst.add(9);
        System.out.println(shortestFirst.poll());
    }
}
```

`ArrayDeque` is usually the best general-purpose queue. `PriorityQueue` is a heap: `poll()` returns the minimum, but iteration does not guarantee sorted order.

### What to notice

- `add` throws on failure; `offer` returns `false`. `remove` throws when empty; `poll` returns `null`.
- `List.remove(int)` removes by index; `List.remove(Object)` removes by value. Autoboxing can surprise you.
- A stack is a `Deque` used with `push`, `pop`, and `peek`. The legacy `Stack` class is synchronized and usually unnecessary.
- A priority queue does not sort its backing array. Only the head is guaranteed.
- An iterator is fail-fast: structurally modifying the collection while iterating throws `ConcurrentModificationException`.
- Big-O describes growth, not wall-clock time. A small `ArrayList` can beat a fancy structure.

### Practice

1. Simulate a print queue with arrival times and service times.
2. Use an `ArrayDeque` to check whether parentheses are balanced.
3. Merge two sorted lists into one sorted list without sorting again.

### Checkpoint

You are ready to move on when you can pick a collection from its operation costs, and when you can explain why `PriorityQueue` iteration is not sorted.

---

<a id="chapter-28-graphs"></a>
<a name="chapter-28-graphs"></a>
## Chapter 28 — Graphs

### The idea

A graph is a set of vertices and a set of edges. Edges can be directed or undirected, weighted or unweighted. The representation decides which questions are cheap: an adjacency matrix answers “are these two connected?” in O(1); an adjacency list answers “who are my neighbors?” in time proportional to the degree.

Breadth-first search explores by distance in an unweighted graph. Depth-first search follows a path as far as it can before backing up. Both need a visited set; without it, cycles cause infinite work.

### A worked example

```java
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BreadthFirst {
    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        graph.put(1, List.of(2, 3));
        graph.put(2, List.of(4));
        graph.put(3, List.of(4, 5));
        graph.put(4, List.of(5));
        graph.put(5, List.of());

        visit(graph, 1);
    }

    static void visit(Map<Integer, List<Integer>> graph, int start) {
        Set<Integer> seen = new HashSet<>();
        Queue<Integer> queue = new ArrayDeque<>();
        seen.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            System.out.print(vertex + " ");
            for (int next : graph.getOrDefault(vertex, List.of())) {
                if (seen.add(next)) {
                    queue.add(next);
                }
            }
        }
    }
}
```

`seen.add(next)` returns `false` when the vertex was already present, so the same check both tests and records membership.

### What to notice

- An undirected edge is stored twice in an adjacency list. A directed edge is stored once.
- BFS uses a queue and finds shortest paths by edge count. DFS uses a stack or recursion.
- A disconnected graph needs an outer loop over all vertices, not just one start.
- Topological sorting only makes sense for a directed acyclic graph.
- A weighted graph needs Dijkstra or Bellman–Ford; BFS is not enough when edge weights differ.
- Recursive DFS can overflow on a long path. An explicit stack avoids that.

### Practice

1. Count connected components in an undirected graph.
2. Detect a cycle in a directed graph with three colors or an explicit state array.
3. Print the shortest path from one vertex to another by storing a parent map during BFS.

### Checkpoint

You are ready to move on when you can choose an adjacency representation from the expected operations, and when you can explain why BFS needs a queue while DFS can use a stack.
