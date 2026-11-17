"""Authored studio content: Part 1 foundations and early chapters."""

CHAPTERS = [
    {
        "n": 1,
        "title": "Introduction to Java",
        "part": "Part 1 · Foundations",
        "tagline": "Write once, run anywhere starts with a clear boundary: source code, bytecode, and the JVM.",
        "minutes": 30,
        "tags": ["java", "jvm", "bytecode", "main", "println", "state"],
        "objectives": [
            "Trace a tiny Java program from source text to screen output.",
            "Explain what <code>main</code>, a statement, and a string concatenation each contribute.",
            "Predict the value of a variable after reassignment without running the program.",
        ],
        "subgoals": [
            {
                "title": "Source is text; bytecode is the contract",
                "idea": "<code>javac</code> translates source into class files. The JVM reads those class files, so the same bytecode can run on another operating system with a compatible JVM.",
            },
            {
                "title": "Every application starts at main",
                "idea": "<code>public static void main(String[] args)</code> is the entry point the JVM looks for. <code>static</code> means the method belongs to the class, not to an object.",
            },
            {
                "title": "A variable is a named box with a type",
                "idea": "<code>int lives = 3;</code> reserves an integer slot, names it <code>lives</code>, and stores 3. The type decides which operations are legal.",
            },
            {
                "title": "Assignment replaces, it does not compare",
                "idea": "<code>lives = lives - 1;</code> evaluates the right side first, then stores the result in the same box. The old value is gone.",
            },
            {
                "title": "println converts values to text",
                "idea": "The <code>+</code> operator joins a string with another value. Java converts the number to text as part of the concatenation.",
            },
        ],
        "example": {
            "title": "HelloBlueprint: three statements, one observable state",
            "intro": "This program is deliberately small. Watch the variable, not just the output: the screen only changes on the last line.",
            "code": [
                "public class HelloBlueprint {",
                "    public static void main(String[] args) {",
                "        int lives = 3;",
                "        lives = lives - 1;",
                '        System.out.println("Lives: " + lives);',
                "    }",
                "}",
            ],
            "steps": [
                {
                    "label": "Class shell",
                    "note": "The class name must match the file name. This is a compile-time rule, not a runtime one.",
                },
                {
                    "label": "Entry point",
                    "note": "The JVM calls <code>main</code> with an array of command-line arguments. An empty array is still an array.",
                },
                {
                    "label": "First assignment",
                    "note": "The box <code>lives</code> now holds 3. Nothing has been printed yet.",
                },
                {
                    "label": "Reassignment",
                    "note": "The right side reads 3, subtracts 1, and stores 2. The previous value is replaced.",
                },
                {
                    "label": "Output",
                    "note": "String concatenation turns 2 into text, so the console receives exactly <code>Lives: 2</code>.",
                },
            ],
            "predict": {
                "prompt": "If the two assignment lines were swapped, what would the program print?",
                "answer": "It would not compile: <code>lives</code> would be read before it is declared. Declaration order matters in Java.",
            },
        },
        "engine": "state",
        "spec": {
            "hint": "Step through the program and watch the variable box and the screen.",
            "code": [
                "int lives = 3;",
                "lives = lives - 1;",
                'System.out.println("Lives: " + lives);',
            ],
            "vars": ["lives", "screen"],
            "steps": [
                {
                    "line": 0,
                    "vars": {"lives": "3", "screen": '""'},
                    "note": "Declaration and initialization: the box lives contains 3, and the screen is empty.",
                },
                {
                    "line": 1,
                    "vars": {"lives": "2", "screen": '""'},
                    "note": "Read 3, subtract 1, store 2. The screen is still empty.",
                },
                {
                    "line": 2,
                    "vars": {"lives": "2", "screen": '"Lives: 2"'},
                    "note": "Concatenation produces one string, and println writes it followed by a newline.",
                },
            ],
        },
        "parsons": {
            "prompt": "Rebuild the program so it prints exactly one line: <code>Lives: 2</code>.",
            "lines": [
                "public class HelloBlueprint {",
                "    public static void main(String[] args) {",
                "        int lives = 3;",
                "        lives = lives - 1;",
                '        System.out.println("Lives: " + lives);',
                "    }",
                "}",
            ],
            "solution": [0, 1, 2, 3, 4, 5, 6],
            "seed": 11,
            "why": "The class wraps the entry point, the variable is declared before use, and the output statement comes last.",
        },
        "quiz": [
            {
                "q": "Which file does the JVM actually execute?",
                "options": ["The .java source file", "The .class bytecode file", "The README", "The compiler itself"],
                "a": 1,
                "why": "javac produces class files; the JVM loads and runs bytecode.",
            },
            {
                "q": "After <code>int n = 5; n = n + 2;</code>, what is in n?",
                "options": ["5", "2", "7", "The program fails to compile"],
                "a": 2,
                "why": "The right side is evaluated first, then the result replaces the old value.",
            },
            {
                "q": "Why is <code>main</code> marked static?",
                "options": [
                    "So it can change every variable",
                    "So the JVM can call it without creating an object",
                    "So it runs faster",
                    "Because all Java methods are static",
                ],
                "a": 1,
                "why": "A static method belongs to the class, so no instance is needed before the program starts.",
            },
        ],
        "artifact": {
            "name": "HelloBlueprint.java",
            "description": "A starter file with a deliberate TODO: change the initial lives value and add one more statement that reports whether the player is still alive.",
            "code": """public class HelloBlueprint {
    public static void main(String[] args) {
        int lives = 3;
        lives = lives - 1;

        // TODO: print the lives value, then print "Still playing" when lives > 0.

        System.out.println("Lives: " + lives);
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_01",
        },
        "reflection": "Explain, in two sentences, the difference between compiling and running. Then name one thing the JVM makes possible that a native binary does not.",
        "studio": {
            "title": "Variable state machine",
            "intro": "The playground isolates the three statements. Use the step controls to see exactly when the screen changes.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/foundations.md#chapter-1-how-java-runs",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_01",
        },
    },
    {
        "n": 7,
        "title": "Single-Dimensional Arrays",
        "part": "Part 2 · Core language",
        "tagline": "An array is one object holding a fixed number of same-typed slots, addressed by an integer index.",
        "minutes": 40,
        "tags": ["arrays", "index", "length", "traversal", "search", "structure"],
        "objectives": [
            "Declare, allocate, and initialize an array without confusing the three steps.",
            "Use a loop and the length field to visit every element exactly once.",
            "Explain why an index equal to length is out of bounds.",
        ],
        "subgoals": [
            {
                "title": "Declaration is not allocation",
                "idea": "<code>int[] scores;</code> creates a reference variable. <code>new int[5]</code> creates the array object. The braces form <code>{72, 85, 91, 64, 88}</code> combine creation and initialization.",
            },
            {
                "title": "Indexes start at zero",
                "idea": "The first slot is index 0 and the last is length - 1. For five scores the legal indexes are 0, 1, 2, 3, and 4.",
            },
            {
                "title": "length is a field, not a method",
                "idea": "Use <code>scores.length</code> without parentheses. The value is fixed when the array object is created.",
            },
            {
                "title": "Traversal is a contract",
                "idea": "A correct loop starts at 0, continues while <code>i &lt; scores.length</code>, and processes <code>scores[i]</code>. Changing either boundary is an off-by-one bug.",
            },
            {
                "title": "Linear search reports a position",
                "idea": "Scan until the target is found, then stop. If the loop ends without a match, return -1 because -1 is not a legal index.",
            },
            {
                "title": "Elements are mutable",
                "idea": "<code>scores[2] = 95;</code> replaces the value in that slot. The array object stays the same; only its contents change.",
            },
        ],
        "example": {
            "title": "ArrayAudit: one pass, three facts",
            "intro": "The loop reads every element once and accumulates a sum. The cast to double is what prevents integer division from truncating the average.",
            "code": [
                "public class ArrayAudit {",
                "    public static void main(String[] args) {",
                "        int[] scores = {72, 85, 91, 64, 88};",
                "        int sum = 0;",
                "        for (int i = 0; i < scores.length; i++) {",
                "            sum += scores[i];",
                "        }",
                "        double average = (double) sum / scores.length;",
                '        System.out.printf("sum=%d average=%.1f%n", sum, average);',
                "    }",
                "}",
            ],
            "steps": [
                {
                    "label": "Create the array object",
                    "note": "Five integer slots exist and are initialized to the listed values. The variable <code>scores</code> refers to the object.",
                },
                {
                    "label": "Accumulator starts at zero",
                    "note": "Zero is the identity for addition. Starting anywhere else would bias every result.",
                },
                {
                    "label": "Visit each slot once",
                    "note": "The guard uses <code>&lt;</code>, not <code>&lt;=</code>. When <code>i</code> reaches 5 the loop stops before touching <code>scores[5]</code>.",
                },
                {
                    "label": "Convert before dividing",
                    "note": "Integer division would produce 80 instead of 80.0. Casting the sum to double changes the operation.",
                },
                {
                    "label": "Format the report",
                    "note": "<code>%d</code> prints an integer, <code>%.1f</code> prints one decimal place, and <code>%n</code> is a portable newline.",
                },
            ],
            "predict": {
                "prompt": "What would the average print if the cast to double were removed?",
                "answer": "It would print <code>80.0</code> because 400 / 5 is exact. Change the sum to 401 and the truncated result becomes visible: 80.0 instead of 80.2.",
            },
        },
        "engine": "structure",
        "spec": {
            "hint": "Step through access, traversal, search, and update. The search box checks the current state.",
            "mode": "array",
            "frames": [
                {
                    "label": "Array object created",
                    "cells": ["72", "85", "91", "64", "88"],
                    "highlight": [],
                    "note": "Five slots, indexes 0 through 4. length is 5 and never changes.",
                },
                {
                    "label": "Read scores[0]",
                    "cells": ["72", "85", "91", "64", "88"],
                    "highlight": [0],
                    "note": "Random access is constant time: the runtime computes the slot address from the index.",
                },
                {
                    "label": "Read scores[2]",
                    "cells": ["72", "85", "91", "64", "88"],
                    "highlight": [2],
                    "note": "The third element is 91. There is no scores[5]; that index would throw an exception.",
                },
                {
                    "label": "Traverse and accumulate",
                    "cells": ["72", "85", "91", "64", "88"],
                    "highlight": [0, 1, 2, 3, 4],
                    "note": "Each element contributes once. The running sum passes through 72, 157, 248, 312, and 400.",
                },
                {
                    "label": "Search for 91",
                    "cells": ["72", "85", "91", "64", "88"],
                    "highlight": [2],
                    "note": "Linear search compares 72, then 85, then 91. It reports index 2 after three comparisons.",
                },
                {
                    "label": "Update scores[4]",
                    "cells": ["72", "85", "91", "64", "95"],
                    "highlight": [4],
                    "note": "Assignment changes one slot in place. The array reference and length do not change.",
                },
            ],
        },
        "parsons": {
            "prompt": "Build a loop that prints every score on its own line, then prints the count.",
            "lines": [
                "int[] scores = {72, 85, 91, 64, 88};",
                "int count = 0;",
                "for (int i = 0; i < scores.length; i++) {",
                "    System.out.println(scores[i]);",
                "    count++;",
                "}",
                'System.out.println("count=" + count);',
            ],
            "solution": [0, 1, 2, 3, 4, 5, 6],
            "seed": 19,
            "why": "The array must exist before the loop, the counter starts at zero, and the guard uses length rather than a hard-coded 5.",
        },
        "quiz": [
            {
                "q": "What is the last legal index of an array whose length is 8?",
                "options": ["8", "7", "9", "0"],
                "a": 1,
                "why": "Indexes run from 0 to length - 1, so the last index is 7.",
            },
            {
                "q": "Which expression gives the number of elements?",
                "options": ["scores.length()", "scores.size()", "scores.length", "scores.count"],
                "a": 2,
                "why": "For arrays, length is a field. Parentheses would try to call a method that does not exist.",
            },
            {
                "q": "Why return -1 from a search instead of 0 when nothing matches?",
                "options": [
                    "0 is slower",
                    "0 is a legal index, so it cannot mean not found",
                    "Java requires -1 for all searches",
                    "It makes the array shorter",
                ],
                "a": 1,
                "why": "Index 0 may hold a real match. A separate sentinel such as -1 avoids ambiguity.",
            },
        ],
        "artifact": {
            "name": "ArrayAudit.java",
            "description": "The worked example as a runnable starter, plus one TODO: find the largest score without sorting the array.",
            "code": """public class ArrayAudit {
    public static void main(String[] args) {
        int[] scores = {72, 85, 91, 64, 88};
        int sum = 0;
        for (int i = 0; i < scores.length; i++) {
            sum += scores[i];
        }
        double average = (double) sum / scores.length;

        // TODO: track the largest value in a second variable during the same loop.

        System.out.printf("sum=%d average=%.1f%n", sum, average);
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_07",
        },
        "reflection": "Describe a bug you could create by writing <code>i &lt;= scores.length</code>. What would Java do, and at which index?",
        "studio": {
            "title": "Array state viewer",
            "intro": "Move through access, traversal, search, and update. The index labels are always visible so off-by-one errors have nowhere to hide.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/core-language.md#chapter-7-single-dimensional-arrays",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_07",
        },
    },
    {
        "n": 23,
        "title": "Sorting",
        "part": "Part 4 · Algorithms and structures",
        "tagline": "Sorting is the art of maintaining an invariant while the data is still moving.",
        "minutes": 45,
        "tags": ["sorting", "bubble", "selection", "insertion", "merge", "complexity", "sort"],
        "objectives": [
            "Explain the invariant that each sorting algorithm protects.",
            "Compare quadratic and linearithmic growth using recorded comparisons.",
            "Choose a stable algorithm when equal keys must keep their original order.",
        ],
        "subgoals": [
            {
                "title": "An invariant is a promise that stays true",
                "idea": "Bubble sort promises the largest remaining values are already at the end. Selection sort promises the prefix is final. Insertion sort promises the prefix is sorted but not necessarily final.",
            },
            {
                "title": "Comparisons and swaps are different costs",
                "idea": "A comparison reads two values. A swap writes two slots. Algorithms with the same comparison count can have very different write costs.",
            },
            {
                "title": "Bubble sort moves large values right",
                "idea": "Each pass compares adjacent pairs and swaps when they are out of order. After pass k, the last k values are final.",
            },
            {
                "title": "Selection sort minimizes swaps",
                "idea": "Find the minimum of the unsorted suffix, then place it once. It always performs about n²/2 comparisons but at most n - 1 swaps.",
            },
            {
                "title": "Insertion sort is excellent on nearly sorted data",
                "idea": "Shift only while the left neighbor is larger. On an already sorted array the inner loop never runs, so the pass is linear.",
            },
            {
                "title": "Merge sort divides before it conquers",
                "idea": "Split until ranges have one element, then merge two sorted ranges by repeatedly taking the smaller front value. The merge step is linear in the combined range.",
            },
            {
                "title": "Stability is a property, not a speed",
                "idea": "A stable sort preserves the relative order of equal keys. Merge sort is stable when the merge takes from the left range on ties; selection sort is not.",
            },
        ],
        "example": {
            "title": "SortTrace: insertion sort with visible passes",
            "intro": "The program prints the array after each insertion. The printed snapshots are the same states the playground shows.",
            "code": [
                "import java.util.Arrays;",
                "",
                "public class SortTrace {",
                "    public static void main(String[] args) {",
                "        int[] values = {42, 17, 93, 8, 61, 24};",
                "        for (int i = 1; i < values.length; i++) {",
                "            int key = values[i];",
                "            int j = i - 1;",
                "            while (j >= 0 && values[j] > key) {",
                "                values[j + 1] = values[j];",
                "                j--;",
                "            }",
                "            values[j + 1] = key;",
                "            System.out.println(Arrays.toString(values));",
                "        }",
                "    }",
                "}",
            ],
            "steps": [
                {
                    "label": "Start at the second element",
                    "note": "A single element is already sorted, so index 0 is the initial sorted prefix.",
                },
                {
                    "label": "Save the key",
                    "note": "The value at index i will be overwritten during shifting, so it must be stored first.",
                },
                {
                    "label": "Shift larger values right",
                    "note": "The loop stops at a smaller value or at the beginning. Both conditions are required to avoid reading index -1.",
                },
                {
                    "label": "Insert into the gap",
                    "note": "After the shifts, j + 1 is the first position whose left neighbor is not larger. That is where the key belongs.",
                },
                {
                    "label": "Print after every pass",
                    "note": "The prefix grows by one sorted element per outer iteration. The suffix is untouched but not yet sorted.",
                },
            ],
            "predict": {
                "prompt": "How many shifts happen when the input is already sorted, such as <code>{1, 2, 3, 4}</code>?",
                "answer": "Zero. The while condition is false immediately at every pass, so insertion sort runs in linear time on sorted input.",
            },
        },
        "engine": "sort",
        "spec": {
            "hint": "Switch algorithms on the same input. Compare how much movement each one needs.",
            "algorithms": ["bubble", "selection", "insertion", "merge"],
            "input": [42, 17, 93, 8, 61, 24],
        },
        "parsons": {
            "prompt": "Rebuild selection sort. It should place the minimum of the remaining suffix at index i.",
            "lines": [
                "for (int i = 0; i < values.length - 1; i++) {",
                "    int min = i;",
                "    for (int j = i + 1; j < values.length; j++) {",
                "        if (values[j] < values[min]) {",
                "            min = j;",
                "        }",
                "    }",
                "    int temp = values[i];",
                "    values[i] = values[min];",
                "    values[min] = temp;",
                "}",
            ],
            "solution": [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
            "seed": 29,
            "why": "The outer loop ends one early because the last element is forced into place. The inner loop searches the suffix, then one swap places the minimum.",
        },
        "quiz": [
            {
                "q": "Which algorithm is stable when the merge prefers the left range on ties?",
                "options": ["Selection sort", "Bubble sort", "Merge sort", "None of them"],
                "a": 2,
                "why": "Taking the left value first preserves the original order of equal keys, which is the definition of stability.",
            },
            {
                "q": "What is the worst-case number of comparisons for bubble sort on n elements?",
                "options": ["n", "n log n", "n²", "2ⁿ"],
                "a": 2,
                "why": "The nested passes compare roughly n(n - 1)/2 pairs, which is quadratic.",
            },
            {
                "q": "Why can insertion sort beat merge sort on nearly sorted data?",
                "options": [
                    "It uses more memory",
                    "Its inner loop rarely runs, so the passes approach linear time",
                    "It skips comparisons entirely",
                    "It sorts without moving values",
                ],
                "a": 1,
                "why": "Each element is already close to its place, so few shifts are needed even though the worst case is quadratic.",
            },
        ],
        "artifact": {
            "name": "SortTrace.java",
            "description": "The insertion-sort trace from the worked example, with a TODO that records the number of comparisons and shifts.",
            "code": """import java.util.Arrays;

public class SortTrace {
    public static void main(String[] args) {
        int[] values = {42, 17, 93, 8, 61, 24};
        int shifts = 0;

        for (int i = 1; i < values.length; i++) {
            int key = values[i];
            int j = i - 1;
            while (j >= 0 && values[j] > key) {
                values[j + 1] = values[j];
                j--;
                shifts++; // TODO: also count comparisons.
            }
            values[j + 1] = key;
            System.out.println(Arrays.toString(values));
        }
        System.out.println("shifts=" + shifts);
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_23",
        },
        "reflection": "Pick one algorithm from the playground. State its invariant in one sentence, then explain which state in the timeline first proves the invariant holds.",
        "studio": {
            "title": "Sorting laboratory",
            "intro": "Four algorithms, one input. Step slowly through the first pass, then jump to the end and compare the number of recorded states.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/collections-algorithms.md#chapter-23-sorting",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_23",
        },
    },
]
