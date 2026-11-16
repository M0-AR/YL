# Core Language: From Values to Interfaces

This volume covers chapters 2 through 14. The thread is simple: every program is a sequence of decisions about values, names, and responsibilities. First you learn how values are represented. Then you learn how to make decisions and repeat work. Then you bundle state and behavior into objects, and finally you let objects stand in for one another through inheritance and interfaces.

- [Chapter 2 — Elementary Programming](#chapter-2-elementary-programming)
- [Chapter 3 — Selections](#chapter-3-selections)
- [Chapter 4 — Math, Characters, and Strings](#chapter-4-math-characters-strings)
- [Chapter 5 — Loops](#chapter-5-loops)
- [Chapter 6 — Methods](#chapter-6-methods)
- [Chapter 7 — Single-Dimensional Arrays](#chapter-7-single-dimensional-arrays)
- [Chapter 8 — Multidimensional Arrays](#chapter-8-multidimensional-arrays)
- [Chapter 9 — Objects and Classes](#chapter-9-objects-and-classes)
- [Chapter 10 — Thinking in Objects](#chapter-10-thinking-in-objects)
- [Chapter 11 — Inheritance and Polymorphism](#chapter-11-inheritance-and-polymorphism)
- [Chapter 12 — Exception Handling and Text I/O](#chapter-12-exception-handling-and-text-io)
- [Chapter 13 — Abstract Classes and Interfaces](#chapter-13-abstract-classes-and-interfaces)
- [Chapter 14 — JavaFX Basics](#chapter-14-javafx-basics)

---

<a id="chapter-2-elementary-programming"></a>
<a name="chapter-2-elementary-programming"></a>
## Chapter 2 — Elementary Programming

### The idea

A variable is a name for a typed box. The type decides what can go in the box, how much space it takes, and which operations make sense. Java checks those rules before the program runs, which is why a type error is a compile-time problem rather than a surprise at 2 a.m.

The primitive types you will use most often are `int` for whole numbers, `double` for decimal values, `char` for one UTF-16 code unit, and `boolean` for truth values. `String` is not a primitive; it is an object, but Java gives it a literal syntax because text is so common.

### A worked example

```java
import java.util.Scanner;

public class CircleArea {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Radius: ");
        double radius = in.nextDouble();
        double area = Math.PI * radius * radius;
        System.out.printf("Area: %.2f%n", area);
    }
}
```

`nextDouble()` reads one token and converts it. The `printf` format string keeps the display decision separate from the arithmetic.

### What to notice

- `5 / 2` is `2`, because both operands are integers. `5.0 / 2` is `2.5`.
- A cast changes the type of a value: `(double) count / total` avoids integer division.
- `int` arithmetic can overflow silently. If a value may exceed about 2.1 billion, use `long`.
- `final` marks a name that will not be reassigned. Prefer `final double TAX_RATE = 0.08;` to a bare literal.
- `%` is remainder, not a percentage. It also works with negative numbers, so read the sign carefully.
- `Scanner` reads tokens. Mixing `nextInt()` and `nextLine()` can leave a newline waiting; consume it deliberately.

### Practice

1. Convert a Fahrenheit temperature to Celsius and print both with one decimal place.
2. Read minutes and print days, hours, and minutes. Test `0`, `59`, `60`, and `1440`.
3. Swap two `int` variables without using a third variable, then explain why the trick is easy to get wrong.

### Checkpoint

You are ready to move on when you can predict the result and type of an arithmetic expression before running it, and when you can explain why integer division and overflow are not the same problem.

---

<a id="chapter-3-selections"></a>
<a name="chapter-3-selections"></a>
## Chapter 3 — Selections

### The idea

A selection lets the program choose a path. The condition is a `boolean` expression, and the branches are blocks of statements. Java evaluates the condition once per decision, so the order of your branches is part of the design.

The two tools are `if`/`else` for general logic and `switch` for a fixed set of cases. A modern `switch` expression can also produce a value, which keeps the assignment and the decision in one place.

### A worked example

```java
public class LeapYear {
    public static void main(String[] args) {
        int year = 2024;
        boolean leap = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
        System.out.println(year + (leap ? " is a leap year" : " is not a leap year"));
    }
}
```

The parentheses matter. Without them, `&&` binds more tightly than `||`, and the century rule can attach to the wrong part of the expression.

A switch expression is useful when the cases are labels rather than ranges:

```java
String length = switch (month) {
    case 2 -> "28 or 29 days";
    case 4, 6, 9, 11 -> "30 days";
    default -> "31 days";
};
```

### What to notice

- `=` assigns; `==` compares. The compiler catches some mistakes, but not all of them.
- `&&` and `||` short-circuit. Put the cheap or protective check first: `if (s != null && s.length() > 0)`.
- A dangling `else` belongs to the nearest unmatched `if`. Braces make the intent obvious.
- Comparing floating-point values with `==` is fragile. Compare the difference against a tolerance.
- A switch on an enum or a sealed type can be exhaustive. A `default` is still useful for ordinary values.
- Nested `if` statements are a signal to extract a method or invert a condition.

### Practice

1. Read a BMI value and print underweight, normal, overweight, or obese using non-overlapping ranges.
2. Read three integers and print the median without sorting the whole array.
3. Replace a chain of `if` statements with a `switch` expression and check that the behavior is identical.

### Checkpoint

You are ready to move on when you can write a condition that is correct at every boundary, and when you can explain why branch order changes the result.

---

<a id="chapter-4-math-characters-strings"></a>
<a name="chapter-4-math-characters-strings"></a>
## Chapter 4 — Math, Characters, and Strings

### The idea

`Math` is a toolbox of static methods: `abs`, `max`, `min`, `pow`, `sqrt`, `round`, and the trigonometric functions. It works on numbers; it does not know anything about your problem.

A `char` is a numeric type in disguise. `'A'` is a code unit, and `(char) ('A' + 1)` is `'B'`. That makes character classification and simple ciphers easy, but it also means Unicode is wider than one `char`.

A `String` is an immutable sequence of characters. Every method that looks like it changes a string actually returns a new one. That design makes strings safe to share, but it also means a loop that concatenates with `+=` can create a lot of temporary objects.

### A worked example

```java
public class RandomLetter {
    public static void main(String[] args) {
        char letter = (char) ('A' + (int) (Math.random() * 26));
        String word = "level";
        boolean palindrome = isPalindrome(word);
        System.out.println(letter + " " + palindrome);
    }

    static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
}
```

`Math.random()` returns a `double` in `[0.0, 1.0)`. Multiplying by 26 and casting to `int` gives `0..25`, so adding `'A'` stays inside the uppercase letters.

### What to notice

- `String` comparison uses `equals`, not `==`. `==` asks whether two references point at the same object.
- `charAt` returns a `char`; `substring` returns a new `String`. The end index of `substring` is exclusive.
- `StringBuilder` is the right tool for repeated concatenation inside a loop.
- `Character.isLetter`, `isDigit`, and `isWhitespace` are safer than hand-written ranges.
- `Math.round(2.5)` returns `3`; it rounds halfway cases toward positive infinity, not toward zero.
- Formatting belongs at the edge of the program. Keep numbers as numbers and format only for display.

### Practice

1. Generate a license plate with three uppercase letters followed by four digits.
2. Count the vowels in a sentence, ignoring case and punctuation.
3. Convert a hexadecimal string to an integer without using `Integer.parseInt`, then check your result against it.

### Checkpoint

You are ready to move on when you can explain the difference between a `char`, a code point, and a `String`, and when you can choose between `String`, `StringBuilder`, and a character array.

---

<a id="chapter-5-loops"></a>
<a name="chapter-5-loops"></a>
## Chapter 5 — Loops

### The idea

A loop repeats a block while a condition holds. The three forms are not interchangeable decorations:

- `while` checks before the first iteration.
- `do`/`while` checks after the first iteration, so the body always runs once.
- `for` groups initialization, condition, and update in one place.

Most loop bugs are boundary bugs. The condition is true one time too many, the update happens in the wrong place, or the loop variable is modified inside the body.

### A worked example

```java
public class DigitSum {
    public static void main(String[] args) {
        int value = 90210;
        int sum = 0;
        int reversed = 0;
        for (int n = value; n > 0; n /= 10) {
            int digit = n % 10;
            sum += digit;
            reversed = reversed * 10 + digit;
        }
        System.out.println("sum=" + sum + " reversed=" + reversed);
    }
}
```

The loop invariant is: `n` holds the digits not yet processed. Each iteration removes the last digit with `% 10` and shifts the rest right with `/ 10`.

### What to notice

- A `for` loop’s update runs after the body, not before the next condition check.
- `break` leaves the nearest loop; `continue` skips to the next iteration. Both can make a loop harder to read.
- A sentinel value such as `-1` is a common way to end input, but it must not be a valid data value.
- Nested loops multiply their work. A loop inside a loop over the same `n` items is usually O(n²).
- An infinite loop is often a missing update, a condition that can never become false, or an integer that overflows.
- `do`/`while` is appropriate for a menu that must appear at least once.

### Practice

1. Compute the greatest common divisor with Euclid’s algorithm using a `while` loop.
2. Print a multiplication table with aligned columns for `1..9`.
3. Write a number-guessing loop that stops on the correct value or after seven tries.

### Checkpoint

You are ready to move on when you can state the loop invariant for a loop you wrote and when you can find an off-by-one error by testing the first and last iterations.

---

<a id="chapter-6-methods"></a>
<a name="chapter-6-methods"></a>
## Chapter 6 — Methods

### The idea

A method gives a name to a piece of work. The name should say what the method promises, and the parameters should carry everything the method needs. A method that reads global state or prints as a side effect is harder to test than one that takes values and returns a result.

Java passes arguments by value. For a primitive, the method receives a copy of the value. For an object reference, the method receives a copy of the reference, so it can mutate the object but cannot reassign the caller’s variable.

### A worked example

```java
public class NumberTheory {
    public static void main(String[] args) {
        System.out.println(gcd(84, 30));
        System.out.println(isPrime(97));
    }

    static int gcd(int a, int b) {
        while (b != 0) {
            int remainder = a % b;
            a = b;
            b = remainder;
        }
        return a;
    }

    static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int divisor = 2; divisor * divisor <= n; divisor++) {
            if (n % divisor == 0) return false;
        }
        return true;
    }
}
```

The `isPrime` loop stops at the square root because a larger factor would have a smaller partner.

### What to notice

- A method signature is its name plus parameter types. Return type and parameter names are not part of the signature.
- Overloading chooses a method at compile time based on the static types of the arguments.
- A `void` method can still have side effects. Prefer returning a value when the caller needs the result.
- Local variables disappear when the method returns. Fields live with the object.
- Recursion is a method calling itself; it needs a base case and progress toward that base case.
- A method with too many parameters often wants a small parameter object.

### Practice

1. Write `isPalindrome(String)` and reuse it for a sentence after removing non-letters.
2. Write overloaded `convert` methods for miles-to-kilometers and kilograms-to-pounds.
3. Refactor a long `main` into three methods, each with one responsibility.

### Checkpoint

You are ready to move on when you can explain why changing an object inside a method is visible to the caller but reassigning the parameter is not.

---

<a id="chapter-7-single-dimensional-arrays"></a>
<a name="chapter-7-single-dimensional-arrays"></a>
## Chapter 7 — Single-Dimensional Arrays

### The idea

An array is a fixed-size sequence of values with the same type. The size is chosen when the array is created and cannot change. Each slot has an index from `0` to `length - 1`.

An array variable holds a reference. Assigning one array variable to another copies the reference, not the contents. That is why `b = a` and then `b[0] = 99` also changes `a[0]`.

### A worked example

```java
import java.util.Arrays;

public class AboveAverage {
    public static void main(String[] args) {
        int[] scores = {72, 88, 95, 61, 79};
        double average = Arrays.stream(scores).average().orElse(0);
        long above = Arrays.stream(scores).filter(score -> score > average).count();
        System.out.printf("average=%.1f above=%d%n", average, above);
    }
}
```

The stream version is compact, but the loop version teaches the same traversal:

```java
int total = 0;
for (int score : scores) total += score;
double average = (double) total / scores.length;
```

### What to notice

- New arrays are initialized to `0`, `false`, or `null` depending on the element type.
- `array.length` is a field, not a method. Strings use `length()`.
- `Arrays.toString` is for display; printing the array directly shows a reference-like value.
- `Arrays.sort` sorts in place. `Arrays.binarySearch` requires a sorted array.
- `System.arraycopy` and `Arrays.copyOf` copy elements; assignment copies only the reference.
- An empty array is not the same as a `null` reference. Decide which one your API accepts.

### Practice

1. Reverse an array in place without creating a second array.
2. Count distinct values and print them in first-seen order.
3. Compute the mean, minimum, maximum, and standard deviation in one pass.

### Checkpoint

You are ready to move on when you can draw the memory picture for two variables that refer to the same array, and when you can explain why `binarySearch` on an unsorted array is meaningless.

---

<a id="chapter-8-multidimensional-arrays"></a>
<a name="chapter-8-multidimensional-arrays"></a>
## Chapter 8 — Multidimensional Arrays

### The idea

Java does not have true rectangular arrays. A two-dimensional array is an array of references to one-dimensional arrays. That distinction matters: rows can have different lengths, and a row can be `null`.

Most matrix work uses a rectangular shape, but the language does not enforce it. Your code should either enforce the shape or handle ragged data explicitly.

### A worked example

```java
public class Matrix {
    public static void main(String[] args) {
        int[][] grid = {
            {1, 2, 3},
            {4, 5, 6}
        };
        System.out.println(sum(grid));
        print(transpose(grid));
    }

    static int sum(int[][] grid) {
        int total = 0;
        for (int[] row : grid) {
            for (int value : row) total += value;
        }
        return total;
    }

    static int[][] transpose(int[][] grid) {
        int[][] result = new int[grid[0].length][grid.length];
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                result[c][r] = grid[r][c];
            }
        }
        return result;
    }

    static void print(int[][] grid) {
        for (int[] row : grid) {
            for (int value : row) System.out.print(value + " ");
            System.out.println();
        }
    }
}
```

`grid.length` is the number of rows. `grid[0].length` is the length of the first row, which is the number of columns only if the array is rectangular.

### What to notice

- A row is an array reference. `grid[1]` is the second row, not the second element.
- `new int[3][4]` creates three rows of four columns. `new int[3][]` creates three null rows.
- Iterating with `for (int[] row : grid)` is safer than assuming every row has the same length.
- A transpose swaps dimensions. A non-square matrix cannot be transposed in place without extra storage.
- Use row-major traversal for cache-friendly access when the data is rectangular.
- A `null` row causes a `NullPointerException` at the point of dereference, not at array creation.

### Practice

1. Add two matrices and reject mismatched dimensions with a clear message.
2. Compute each row sum and each column sum for a rectangular matrix.
3. Represent a tic-tac-toe board and write a method that detects a winner.

### Checkpoint

You are ready to move on when you can explain why a Java 2D array is an array of arrays, and when you can write a traversal that works for a ragged matrix.

---

<a id="chapter-9-objects-and-classes"></a>
<a name="chapter-9-objects-and-classes"></a>
## Chapter 9 — Objects and Classes

### The idea

A class is a blueprint. An object is one instance of that blueprint, with its own state and access to the class’s behavior. Fields hold state; methods define behavior; a constructor establishes a valid initial state.

Encapsulation means the object controls its own invariants. A private field plus a public method that validates input is stronger than a public field that any caller can corrupt.

### A worked example

```java
public class Circle {
    private final double radius;

    public Circle(double radius) {
        if (radius < 0) throw new IllegalArgumentException("radius must be non-negative");
        this.radius = radius;
    }

    public double area() {
        return Math.PI * radius * radius;
    }

    public double circumference() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "Circle[radius=" + radius + "]";
    }

    public static void main(String[] args) {
        Circle c = new Circle(2.5);
        System.out.println(c + " area=" + c.area());
    }
}
```

The constructor rejects an invalid object before it exists. Once constructed, `radius` cannot change, so every `Circle` is valid for its whole lifetime.

### What to notice

- If you declare any constructor, the compiler no longer supplies a no-argument constructor.
- `this` refers to the current object and is also used to call another constructor with `this(...)`.
- `==` compares references; `equals` is the method you override to compare state.
- `toString` is called implicitly by string concatenation and `println`.
- A `final` field must be assigned exactly once, either at declaration or in every constructor.
- An object reference can be `null`. A `null` is not an object, and calling a method on it throws.

### Practice

1. Write a `Rectangle` class with validation and `area`, `perimeter`, and `isSquare`.
2. Write a `BankAccount` whose balance cannot go negative and whose withdraw method returns a boolean.
3. Write an immutable `Point` class and explain why returning a new point is safer than mutating the old one.

### Checkpoint

You are ready to move on when you can explain the difference between a class and an object, and when you can describe the state invariant that your constructor protects.

---

<a id="chapter-10-thinking-in-objects"></a>
<a name="chapter-10-thinking-in-objects"></a>
## Chapter 10 — Thinking in Objects

### The idea

Once you have objects, the next question is how they relate. Composition means one object owns or uses another. Aggregation means it refers to objects that can outlive it. The distinction is about lifetime and responsibility, not syntax.

Good object design keeps invariants local. A `Course` should not let a caller insert a null student; a `Money` object should not allow its currency to change after construction. Immutable value objects are easy to share and reason about because their state cannot change underneath you.

### A worked example

```java
import java.util.ArrayList;
import java.util.List;

class Student {
    private final String name;
    Student(String name) { this.name = name; }
    String name() { return name; }
}

class Course {
    private final String title;
    private final List<Student> roster = new ArrayList<>();

    Course(String title) { this.title = title; }

    void enroll(Student student) {
        if (student == null) throw new IllegalArgumentException("student");
        roster.add(student);
    }

    int size() { return roster.size(); }

    List<Student> roster() {
        return List.copyOf(roster);
    }
}
```

`roster()` returns a copy, so a caller cannot bypass `enroll` and add an unchecked student.

### What to notice

- Composition usually means the part is created and destroyed with the whole. Aggregation usually means the part is shared.
- A static field belongs to the class, not to an instance. A static method cannot use `this`.
- `final` on a reference prevents reassignment, not mutation. A `final List` can still be modified.
- Defensive copies protect invariants when mutable objects cross an API boundary.
- A class with only data and no behavior is sometimes necessary, but do not confuse it with a well-designed object.
- Object arrays and collections store references. The objects themselves are not copied.

### Practice

1. Build a `Library` that owns books and refuses duplicate ISBNs.
2. Build an `Invoice` with line items and a total that cannot drift from the lines.
3. Build a `Deck` that deals cards without allowing the same card to be dealt twice.

### Checkpoint

You are ready to move on when you can decide whether a relationship is composition or aggregation, and when you can explain why an immutable object is easier to share.

---

<a id="chapter-11-inheritance-and-polymorphism"></a>
<a name="chapter-11-inheritance-and-polymorphism"></a>
## Chapter 11 — Inheritance and Polymorphism

### The idea

Inheritance lets one class extend another and reuse its accessible members. Polymorphism lets a variable of the parent type refer to an object of a child type, with the child’s override chosen at runtime.

The power is real, but so is the coupling. A subclass depends on the parent’s contract. If the relationship is not genuinely “is a kind of,” composition is usually safer than inheritance.

### A worked example

```java
abstract class Shape {
    abstract double area();
}

class Circle extends Shape {
    private final double radius;
    Circle(double radius) { this.radius = radius; }
    @Override double area() { return Math.PI * radius * radius; }
}

class Rectangle extends Shape {
    private final double width;
    private final double height;
    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    @Override double area() { return width * height; }
}

public class Shapes {
    public static void main(String[] args) {
        Shape[] shapes = { new Circle(1.5), new Rectangle(2, 3) };
        double total = 0;
        for (Shape shape : shapes) total += shape.area();
        System.out.println(total);
    }
}
```

The loop knows only `Shape`. Each object supplies its own `area()`. That is dynamic dispatch.

### What to notice

- `@Override` is a compiler check. Use it even when the annotation is optional.
- `super(...)` calls a parent constructor and must be the first statement in the child constructor.
- A child cannot access `private` parent fields directly. Use `protected` or, better, parent methods.
- Java has single inheritance for classes but multiple inheritance for interfaces.
- `instanceof` with a pattern variable can make a safe cast concise: `if (shape instanceof Circle c)`.
- Every class extends `Object`, so `toString`, `equals`, and `hashCode` are inherited contracts.

### Practice

1. Add a `Triangle` subclass and use the same `Shape[]` loop without changing the loop.
2. Build a payroll hierarchy with `Employee`, `SalariedEmployee`, and `HourlyEmployee`.
3. Override `toString` in each subclass and print a mixed collection.

### Checkpoint

You are ready to move on when you can explain why the parent type can call an overridden method and why inheritance should not be used just to reuse a method.

---

<a id="chapter-12-exception-handling-and-text-io"></a>
<a name="chapter-12-exception-handling-and-text-io"></a>
## Chapter 12 — Exception Handling and Text I/O

### The idea

An exception is a structured way to say that a method could not do its job. It separates the normal path from the failure path. A `try` block contains the work, `catch` handles a failure, and `finally` runs whether the work succeeded or not.

Checked exceptions are part of the method contract: the compiler requires the caller to handle or declare them. Unchecked exceptions usually indicate programming errors, such as a null dereference or an invalid index.

### A worked example

```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SumFile {
    public static void main(String[] args) {
        Path path = Path.of("numbers.txt");
        int total = 0;
        try {
            for (String line : Files.readAllLines(path)) {
                total += Integer.parseInt(line.trim());
            }
            System.out.println(total);
        } catch (IOException e) {
            System.out.println("Could not read " + path + ": " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("File contains a non-number: " + e.getMessage());
        }
    }
}
```

`IOException` covers file-system failures. `NumberFormatException` covers bad data. They are different problems and deserve different messages.

### What to notice

- Catch the most specific exception first. A broad `catch (Exception e)` can hide bugs.
- Do not swallow an exception. Log it, translate it, or rethrow it with context.
- `finally` is for cleanup that must happen. Try-with-resources is better for closing files and streams.
- A try-with-resources resource must implement `AutoCloseable`; it is closed in reverse order.
- `throw` raises an exception; `throws` declares that a method may raise one.
- Text I/O has a charset. Use `Files.newBufferedReader` with an explicit charset when interoperability matters.
- A custom exception should carry the information the caller needs to recover or report.

### Practice

1. Sum every valid integer in a file and report the line number of each bad line.
2. Validate a user’s age and throw an `IllegalArgumentException` with a useful message.
3. Copy a text file with `Files.newBufferedReader` and `Files.newBufferedWriter`, preserving line endings.

### Checkpoint

You are ready to move on when you can explain the difference between a checked and an unchecked exception, and when you can write a `try` block that cleans up its resources without a `finally`.

---

<a id="chapter-13-abstract-classes-and-interfaces"></a>
<a name="chapter-13-abstract-classes-and-interfaces"></a>
## Chapter 13 — Abstract Classes and Interfaces

### The idea

An abstract class is a partially built parent. It can hold state, provide concrete methods, and declare abstract methods that subclasses must implement. You cannot instantiate it directly.

An interface is a contract. It says what a type can do without saying how. A class can implement many interfaces, which is how Java gets most of the flexibility that multiple inheritance would provide.

### A worked example

```java
import java.util.Arrays;

abstract class Shape implements Comparable<Shape> {
    abstract double area();

    @Override
    public int compareTo(Shape other) {
        return Double.compare(area(), other.area());
    }
}

class Circle extends Shape {
    private final double radius;
    Circle(double radius) { this.radius = radius; }
    @Override double area() { return Math.PI * radius * radius; }
}

class Square extends Shape {
    private final double side;
    Square(double side) { this.side = side; }
    @Override double area() { return side * side; }
}

public class SortedShapes {
    public static void main(String[] args) {
        Shape[] shapes = { new Circle(2), new Square(1), new Circle(0.5) };
        Arrays.sort(shapes);
        for (Shape shape : shapes) System.out.printf("%.2f%n", shape.area());
    }
}
```

`Comparable<Shape>` is an interface. `Shape` provides the comparison once, and every subclass inherits it.

### What to notice

- An abstract class can have constructors, fields, and concrete methods. An interface cannot hold instance state.
- Interface fields are implicitly `public static final`. Interface methods are implicitly public.
- A `default` method provides behavior without forcing every implementer to write it.
- If two interfaces provide conflicting default methods, the class must resolve the conflict explicitly.
- A functional interface has one abstract method and can be implemented by a lambda.
- Prefer an interface for a capability (`Comparable`, `Runnable`) and an abstract class for shared state or a partial implementation.

### Practice

1. Define a `Movable` interface and implement it in two unrelated classes.
2. Define a `Payable` interface and sort a mixed payroll by amount.
3. Write a `Comparator` that sorts shapes by perimeter without changing `Shape`.

### Checkpoint

You are ready to move on when you can choose between an abstract class and an interface for a new design, and when you can explain why a class can implement many interfaces but extend only one class.

---

<a id="chapter-14-javafx-basics"></a>
<a name="chapter-14-javafx-basics"></a>
## Chapter 14 — JavaFX Basics

### The idea

A JavaFX application is a tree of nodes displayed in a stage. The stage is the window; the scene is the content; the root node is the top of the tree. Layout panes arrange their children, and shapes, controls, and text are all nodes.

JavaFX runs on its own application thread. You build the scene graph on that thread, and you must return to it before changing the UI from a background task.

### A worked example

```java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class HelloFx extends Application {
    @Override
    public void start(Stage stage) {
        Circle circle = new Circle(80, Color.web("#22d3ee"));
        StackPane root = new StackPane(circle);
        stage.setScene(new Scene(root, 320, 240));
        stage.setTitle("Hello, JavaFX");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```

`launch` starts the JavaFX runtime and calls `start` on the application thread. `StackPane` centers the circle and resizes it with the window.

### What to notice

- The `main` method is still the entry point, but `launch` does the UI setup.
- A node can appear in only one place in the scene graph. Adding it elsewhere removes it from its old parent.
- Layout panes decide position and size. Setting `x` and `y` directly fights the layout.
- CSS can style nodes, but inline properties are easier to experiment with.
- JavaFX is not part of the JDK in recent releases; add the JavaFX SDK or let Maven/Gradle resolve it.
- Long work belongs off the UI thread. Use `Task` or `Platform.runLater` to return safely.

### Practice

1. Draw a flag with three rectangles and a circle, using a layout pane rather than absolute coordinates.
2. Center a text label and change its color when the window is resized.
3. Generate twenty random circles with random colors and keep them inside the scene.

### Checkpoint

You are ready to move on when you can explain the stage–scene–node hierarchy, and when you can add a shape to a layout pane without setting absolute coordinates.
