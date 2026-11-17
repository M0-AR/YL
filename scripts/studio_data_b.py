"""Authored studio content: chapters 2-6 and 8-13."""

CHAPTERS = [
    {
        "n": 2,
        "title": "Elementary Programming",
        "part": "Part 2 · Core language",
        "tagline": "Operators have precedence, types have rules, and the order of evaluation is not a suggestion.",
        "minutes": 35,
        "tags": ["operators", "precedence", "types", "integer division", "expressions"],
        "objectives": [
            "Evaluate a mixed arithmetic expression using Java precedence.",
            "Distinguish integer division from floating-point division.",
            "Predict when concatenation changes an expression from arithmetic to text.",
        ],
        "subgoals": [
            {
                "title": "Multiplication binds tighter than addition",
                "idea": "<code>2 + 3 * 4</code> is 14, not 20. Parentheses are the only way to override precedence.",
            },
            {
                "title": "Same-type arithmetic returns that type",
                "idea": "Two ints produce an int. <code>7 / 2</code> is 3 because the fractional part is discarded, not rounded.",
            },
            {
                "title": "A cast changes the operation",
                "idea": "<code>(double) 7 / 2</code> promotes the left operand, so the division becomes floating point.",
            },
            {
                "title": "The plus sign is overloaded",
                "idea": "With two numbers it adds. With a string on either side it concatenates and converts the other operand to text.",
            },
            {
                "title": "Assignment is right-associative",
                "idea": "<code>a = b = 5</code> stores 5 in <code>b</code>, then stores that value in <code>a</code>. The expression itself has a value.",
            },
        ],
        "example": {
            "title": "PrecedenceLedger: one expression, three traps",
            "intro": "The program prints four values. Only one of them is the result of doing everything left to right.",
            "code": [
                "public class PrecedenceLedger {",
                "    public static void main(String[] args) {",
                "        int a = 2 + 3 * 4;",
                "        int b = (2 + 3) * 4;",
                "        int c = 7 / 2;",
                "        double d = (double) 7 / 2;",
                '        System.out.println(a + " " + b + " " + c + " " + d);',
                "    }",
                "}",
            ],
            "steps": [
                {"label": "Precedence", "note": "<code>3 * 4</code> runs first, so <code>a</code> is 14."},
                {"label": "Parentheses", "note": "The parentheses force addition first, so <code>b</code> is 20."},
                {"label": "Integer division", "note": "Both operands are int, so the result is 3 and the remainder is discarded."},
                {"label": "Cast", "note": "The cast applies to 7 before the division, producing 3.5."},
                {"label": "Concatenation", "note": "The first <code>+</code> sees a string, so every later number is converted to text."},
            ],
            "predict": {
                "prompt": "What would <code>1 + 2 + \"A\"</code> print, and why is it different from <code>\"A\" + 1 + 2</code>?",
                "answer": "The first prints <code>3A</code> because the numeric additions happen before the string appears; the second prints <code>A12</code> because concatenation starts immediately.",
            },
        },
        "engine": "expr",
        "spec": {
            "hint": "Reduce the expression one operation at a time. Precedence is visible in the order.",
            "expression": 'int total = 2 + 3 * 4;',
            "steps": [
                {
                    "fragment": "3 * 4",
                    "value": "12",
                    "result": "int total = 2 + 12;",
                    "note": "Multiplication binds tighter than addition.",
                },
                {
                    "fragment": "2 + 12",
                    "value": "14",
                    "result": "int total = 14;",
                    "note": "Addition is evaluated after the product.",
                },
                {
                    "fragment": "int total = 14",
                    "value": "14",
                    "result": "total holds 14",
                    "note": "The assignment stores the final value in the variable.",
                },
            ],
            "types": [
                "2 + 3 * 4        -> int 14",
                "7 / 2            -> int 3",
                "7.0 / 2          -> double 3.5",
                '(double) 7 / 2   -> double 3.5',
            ],
        },
        "parsons": {
            "prompt": "Build a program that computes the average of 7 and 2 without losing the fraction.",
            "lines": [
                "public class Average {",
                "    public static void main(String[] args) {",
                "        int sum = 7 + 2;",
                "        double average = (double) sum / 2;",
                '        System.out.println("average=" + average);',
                "    }",
                "}",
            ],
            "solution": [0, 1, 2, 3, 4, 5, 6],
            "seed": 31,
            "why": "The cast must happen before the division; casting the finished int result would be too late.",
        },
        "quiz": [
            {
                "q": "What is the value of 9 / 4 in Java?",
                "options": ["2.25", "2", "3", "A compile error"],
                "a": 1,
                "why": "Both operands are int, so the fractional part is discarded.",
            },
            {
                "q": "Which expression prints A12?",
                "options": ['"A" + 1 + 2', '1 + 2 + "A"', '"A" + (1 + 2)', "1 + (2 + \"A\")"],
                "a": 0,
                "why": "Evaluation is left to right; the string appears first, so both numbers are concatenated as text.",
            },
            {
                "q": "What does 2 + 3 * 4 evaluate to?",
                "options": ["20", "14", "24", "9"],
                "a": 1,
                "why": "Multiplication runs first: 3 * 4 is 12, then 2 + 12 is 14.",
            },
        ],
        "artifact": {
            "name": "PrecedenceLedger.java",
            "description": "The worked example with a TODO that adds two more expressions: one integer division and one concatenation trap.",
            "code": """public class PrecedenceLedger {
    public static void main(String[] args) {
        int a = 2 + 3 * 4;
        int b = (2 + 3) * 4;
        int c = 7 / 2;
        double d = (double) 7 / 2;

        // TODO: add one expression where precedence changes the answer,
        // and one where integer division changes the answer.

        System.out.println(a + " " + b + " " + c + " " + d);
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_02",
        },
        "reflection": "Write two expressions that look similar but produce different types. Explain the exact rule that separates them.",
        "studio": {
            "title": "Expression reducer",
            "intro": "The playground shows the replacement order. Try to predict the next line before pressing Next.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/core-language.md#chapter-2-elementary-programming",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_02",
        },
    },
    {
        "n": 3,
        "title": "Selections",
        "part": "Part 2 · Core language",
        "tagline": "A branch is a guard: it decides whether the next statement belongs to this path or another one.",
        "minutes": 35,
        "tags": ["if", "else", "boolean", "short-circuit", "logic", "trace"],
        "objectives": [
            "Trace which branch executes for a given input.",
            "Explain short-circuit evaluation and why operand order matters.",
            "Avoid the classic assignment-versus-comparison mistake.",
        ],
        "subgoals": [
            {
                "title": "A condition is a boolean expression",
                "idea": "Comparisons such as <code>score >= 90</code> produce a boolean. Java does not accept an int where a boolean is required.",
            },
            {
                "title": "Branches are checked in order",
                "idea": "The first true branch wins and the rest are skipped. Ordering the tests from most specific to least specific is a design decision.",
            },
            {
                "title": "else binds to the nearest if",
                "idea": "Indentation is for humans. Braces are the only reliable way to show which <code>else</code> belongs to which <code>if</code>.",
            },
            {
                "title": "Short-circuit evaluation skips work",
                "idea": "In <code>a &amp;&amp; b</code>, if <code>a</code> is false, <code>b</code> is not evaluated. In <code>a || b</code>, if <code>a</code> is true, <code>b</code> is skipped.",
            },
            {
                "title": "= assigns; == compares",
                "idea": "<code>if (x = 3)</code> is a compile error for ints, but the distinction still matters when the types differ. Read conditions as questions, not assignments.",
            },
        ],
        "example": {
            "title": "GradeGate: first match wins",
            "intro": "The score is 78. Only one branch should assign a grade, and every later test should be irrelevant.",
            "code": [
                "int score = 78;",
                "char grade;",
                "if (score >= 90) {",
                "    grade = 'A';",
                "} else if (score >= 80) {",
                "    grade = 'B';",
                "} else if (score >= 70) {",
                "    grade = 'C';",
                "} else {",
                "    grade = 'F';",
                "}",
                "System.out.println(grade);",
            ],
            "steps": [
                {"label": "First test fails", "note": "78 is not at least 90, so the A branch is skipped."},
                {"label": "Second test fails", "note": "78 is not at least 80 either."},
                {"label": "Third test passes", "note": "78 is at least 70. The C branch runs and every later branch is skipped."},
                {"label": "Output", "note": "The variable is assigned exactly once, so printing it cannot produce an uninitialized-value error."},
            ],
            "predict": {
                "prompt": "If the tests were reordered with the lowest threshold first, what would a score of 95 receive?",
                "answer": "It would receive F, because the first test would succeed and every later branch would be skipped. Order carries meaning.",
            },
        },
        "engine": "trace",
        "spec": {
            "hint": "Follow the highlighted condition and watch the call stack variables update.",
            "code": [
                "int score = 78;",
                "char grade;",
                "if (score >= 90) {",
                "    grade = 'A';",
                "} else if (score >= 80) {",
                "    grade = 'B';",
                "} else if (score >= 70) {",
                "    grade = 'C';",
                "} else {",
                "    grade = 'F';",
                "}",
                "System.out.println(grade);",
            ],
            "steps": [
                {"line": 0, "note": "Initialize the score.", "stack": [{"name": "main", "vars": {"score": 78, "grade": "?"}}]},
                {"line": 2, "note": "78 >= 90 is false.", "stack": [{"name": "main", "vars": {"score": 78, "grade": "?"}}]},
                {"line": 4, "note": "78 >= 80 is false.", "stack": [{"name": "main", "vars": {"score": 78, "grade": "?"}}]},
                {"line": 6, "note": "78 >= 70 is true; enter this branch.", "stack": [{"name": "main", "vars": {"score": 78, "grade": "?"}}]},
                {"line": 7, "note": "Assign C. The remaining branches are skipped.", "stack": [{"name": "main", "vars": {"score": 78, "grade": "C"}}]},
                {"line": 11, "note": "Print the assigned grade.", "stack": [{"name": "main", "vars": {"score": 78, "grade": "C"}}], "output": "C"},
            ],
        },
        "parsons": {
            "prompt": "Rebuild the branching ladder so 78 prints C.",
            "lines": [
                "int score = 78;",
                "if (score >= 90) {",
                "    System.out.println(\"A\");",
                "} else if (score >= 80) {",
                "    System.out.println(\"B\");",
                "} else if (score >= 70) {",
                "    System.out.println(\"C\");",
                "} else {",
                "    System.out.println(\"F\");",
                "}",
            ],
            "solution": [0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
            "seed": 37,
            "why": "The tests descend by threshold; moving the 70 test earlier would capture values that belong to higher grades.",
        },
        "quiz": [
            {
                "q": "In a && b, when is b evaluated?",
                "options": ["Always", "Only when a is true", "Only when a is false", "Never"],
                "a": 1,
                "why": "Short-circuit evaluation skips b when the left side already decides the result.",
            },
            {
                "q": "Which operator compares two values?",
                "options": ["=", "==", "=>", "::"],
                "a": 1,
                "why": "== is equality; = is assignment.",
            },
            {
                "q": "What happens after the first true branch in an if/else-if ladder?",
                "options": [
                    "Every later branch also runs",
                    "The remaining branches are skipped",
                    "The program restarts",
                    "The condition is re-evaluated",
                ],
                "a": 1,
                "why": "The ladder is exclusive: one path is selected.",
            },
        ],
        "artifact": {
            "name": "GradeGate.java",
            "description": "A starter that reads an integer score and assigns a letter grade, with a TODO for validating scores outside 0-100.",
            "code": """public class GradeGate {
    public static void main(String[] args) {
        int score = Integer.parseInt(args[0]);
        char grade;

        if (score >= 90) {
            grade = 'A';
        } else if (score >= 80) {
            grade = 'B';
        } else if (score >= 70) {
            grade = 'C';
        } else {
            grade = 'F';
        }

        // TODO: reject scores below 0 or above 100 before grading.

        System.out.println(grade);
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_03",
        },
        "reflection": "Describe one input that would expose a bug in a branch ladder whose tests are in the wrong order.",
        "studio": {
            "title": "Branch tracer",
            "intro": "Step through the conditions. The output stays empty until the chosen branch assigns a grade.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/core-language.md#chapter-3-selections",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_03",
        },
    },
    {
        "n": 4,
        "title": "Math, Characters, Strings",
        "part": "Part 2 · Core language",
        "tagline": "Strings look like values but behave like objects, and characters are numbers wearing quotes.",
        "minutes": 35,
        "tags": ["strings", "char", "math", "concatenation", "immutability", "expr"],
        "objectives": [
            "Predict the result of mixing strings, characters, and numbers with +.",
            "Use string methods without confusing indexes with lengths.",
            "Explain why a String cannot be changed in place.",
        ],
        "subgoals": [
            {
                "title": "+ is left-associative",
                "idea": "<code>\"A\" + 1 + 2</code> groups as <code>(\"A\" + 1) + 2</code>, producing A12. Put the arithmetic in parentheses when you want 3A.",
            },
            {
                "title": "A char is a small integer",
                "idea": "<code>'A'</code> is a single UTF-16 code unit. <code>'A' + 1</code> is an int, so cast back to char to print B.",
            },
            {
                "title": "String indexes run from 0 to length - 1",
                "idea": "<code>substring(start, end)</code> includes start and excludes end. That half-open rule makes length arithmetic predictable.",
            },
            {
                "title": "Strings are immutable",
                "idea": "Methods such as <code>toUpperCase</code> return a new string. The original object never changes.",
            },
            {
                "title": "Math methods are static",
                "idea": "<code>Math.max</code> and <code>Math.round</code> are called on the class. They do not need an object.",
            },
        ],
        "example": {
            "title": "StringWorkshop: read the expression tree",
            "intro": "The same three values produce different results depending on where the string appears and where the parentheses go.",
            "code": [
                "public class StringWorkshop {",
                "    public static void main(String[] args) {",
                '        String left = "A" + 1 + 2;',
                '        String right = "A" + (1 + 2);',
                "        char next = (char) ('A' + 1);",
                '        String word = "java";',
                "        System.out.println(left + \" \" + right + \" \" + next);",
                '        System.out.println(word.substring(0, 2).toUpperCase());',
                "    }",
                "}",
            ],
            "steps": [
                {"label": "Left-associative concatenation", "note": "<code>\"A\" + 1</code> becomes \"A1\", then <code>\"A1\" + 2</code> becomes \"A12\"."},
                {"label": "Parentheses restore arithmetic", "note": "<code>1 + 2</code> is 3, then the string concatenation produces \"A3\"."},
                {"label": "Character arithmetic", "note": "<code>'A' + 1</code> is the int 66; casting to char gives 'B'."},
                {"label": "Half-open substring", "note": "<code>substring(0, 2)</code> takes indexes 0 and 1, producing \"ja\"."},
                {"label": "Immutable result", "note": "<code>toUpperCase()</code> returns \"JA\"; <code>word</code> is still \"java\"."},
            ],
            "predict": {
                "prompt": "What would <code>word.substring(2)</code> return for \"java\"?",
                "answer": "\"va\". With one argument, substring runs to the end of the string.",
            },
        },
        "engine": "expr",
        "spec": {
            "hint": "Reduce the concatenation from left to right. The type of the left operand decides the operation.",
            "expression": 'String result = "A" + 1 + 2;',
            "steps": [
                {
                    "fragment": '"A" + 1',
                    "value": '"A1"',
                    "result": 'String result = "A1" + 2;',
                    "note": "A string on the left turns + into concatenation.",
                },
                {
                    "fragment": '"A1" + 2',
                    "value": '"A12"',
                    "result": 'String result = "A12";',
                    "note": "The second number is also converted to text.",
                },
                {
                    "fragment": 'String result = "A12"',
                    "value": '"A12"',
                    "result": 'result refers to "A12"',
                    "note": "The variable holds a reference to the new string object.",
                },
            ],
            "types": [
                '"A" + (1 + 2)   -> "A3"',
                "\"'A' + 1          -> int 66\"",
                "(char) ('A' + 1) -> 'B'",
                '"java".length()  -> 4',
            ],
        },
        "parsons": {
            "prompt": "Build a program that prints the first two letters of a word in upper case.",
            "lines": [
                "public class Prefix {",
                "    public static void main(String[] args) {",
                '        String word = "java";',
                "        String prefix = word.substring(0, 2);",
                "        System.out.println(prefix.toUpperCase());",
                "    }",
                "}",
            ],
            "solution": [0, 1, 2, 3, 4, 5, 6],
            "seed": 41,
            "why": "substring(0, 2) takes indexes 0 and 1; toUpperCase returns a new string, which println prints.",
        },
        "quiz": [
            {
                "q": "What does 'A' + 1 evaluate to?",
                "options": ["A1", "B", "66", "A compile error"],
                "a": 2,
                "why": "A char is promoted to int for arithmetic, so the result is the int 66.",
            },
            {
                "q": "What does \"java\".substring(1, 3) return?",
                "options": ["ja", "av", "ava", "jav"],
                "a": 1,
                "why": "Indexes 1 and 2 are included; index 3 is the exclusive end.",
            },
            {
                "q": "After calling word.toUpperCase(), what is word?",
                "options": ["The upper-case copy", "Unchanged", "Null", "A compile error"],
                "a": 1,
                "why": "Strings are immutable; the method returns a new string.",
            },
        ],
        "artifact": {
            "name": "StringWorkshop.java",
            "description": "The expression laboratory as a program, with a TODO that prints the last character without hard-coding its index.",
            "code": """public class StringWorkshop {
    public static void main(String[] args) {
        String left = "A" + 1 + 2;
        String right = "A" + (1 + 2);
        char next = (char) ('A' + 1);
        String word = "java";

        // TODO: print the last character using word.length() - 1.

        System.out.println(left + " " + right + " " + next);
        System.out.println(word.substring(0, 2).toUpperCase());
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_04",
        },
        "reflection": "Why is it useful that String is immutable? Give one bug that immutability prevents.",
        "studio": {
            "title": "Expression reducer: text edition",
            "intro": "The playground reduces a concatenation chain. Notice that the first operand decides whether + adds or joins.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/core-language.md#chapter-4-math-characters-strings",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_04",
        },
    },
    {
        "n": 5,
        "title": "Loops",
        "part": "Part 2 · Core language",
        "tagline": "A loop is a promise: initialization, a guard, a body, and an update that moves toward termination.",
        "minutes": 40,
        "tags": ["loops", "for", "while", "iteration", "off-by-one", "trace"],
        "objectives": [
            "Trace a for loop and state how many times the body runs.",
            "Choose between for, while, and do-while for a given task.",
            "Diagnose an off-by-one error from the guard and the update.",
        ],
        "subgoals": [
            {
                "title": "Every counting loop has four parts",
                "idea": "Initialize the counter, test the guard, run the body, then update. Missing or misordering the update creates an infinite loop or an off-by-one result.",
            },
            {
                "title": "The guard is checked before each iteration",
                "idea": "A for loop is a while loop with the pieces in one line. When the guard becomes false, the body is skipped entirely.",
            },
            {
                "title": "The update moves the state",
                "idea": "<code>i++</code> changes the counter after the body. If the body also changes <code>i</code>, reasoning about termination becomes harder.",
            },
            {
                "title": "do-while runs at least once",
                "idea": "The condition is checked after the body. Use it when the first attempt must happen before you can decide whether to repeat.",
            },
            {
                "title": "Nested loops multiply work",
                "idea": "An outer loop of n iterations containing an inner loop of n iterations performs about n² body executions.",
            },
        ],
        "example": {
            "title": "LoopLedger: sum 1 through 4",
            "intro": "The output is 10. The interesting part is the state just before the guard fails.",
            "code": [
                "int sum = 0;",
                "for (int i = 1; i <= 4; i++) {",
                "    sum += i;",
                "}",
                "System.out.println(sum);",
            ],
            "steps": [
                {"label": "Initialize", "note": "<code>sum</code> is 0 and <code>i</code> is 1. The guard 1 &lt;= 4 is true."},
                {"label": "Iteration 1", "note": "Add 1, then increment to 2. Sum is 1."},
                {"label": "Iteration 2", "note": "Add 2, then increment to 3. Sum is 3."},
                {"label": "Iteration 3", "note": "Add 3, then increment to 4. Sum is 6."},
                {"label": "Iteration 4", "note": "Add 4, then increment to 5. Sum is 10."},
                {"label": "Guard fails", "note": "5 &lt;= 4 is false, so the body is skipped and the loop exits."},
            ],
            "predict": {
                "prompt": "How many times does the body run if the guard is <code>i &lt; 4</code> instead?",
                "answer": "Three times, for i = 1, 2, 3. The sum becomes 6, not 10.",
            },
        },
        "engine": "trace",
        "spec": {
            "hint": "Watch i and sum on every step. The last step is the guard failing, not another addition.",
            "code": [
                "int sum = 0;",
                "for (int i = 1; i <= 4; i++) {",
                "    sum += i;",
                "}",
                "System.out.println(sum);",
            ],
            "steps": [
                {"line": 0, "note": "sum starts at 0.", "stack": [{"name": "main", "vars": {"i": "—", "sum": 0}}]},
                {"line": 1, "note": "Initialize i = 1; 1 <= 4 is true.", "stack": [{"name": "main", "vars": {"i": 1, "sum": 0}}]},
                {"line": 2, "note": "sum = 0 + 1 = 1.", "stack": [{"name": "main", "vars": {"i": 1, "sum": 1}}]},
                {"line": 1, "note": "Update i to 2; 2 <= 4 is true.", "stack": [{"name": "main", "vars": {"i": 2, "sum": 1}}]},
                {"line": 2, "note": "sum = 1 + 2 = 3.", "stack": [{"name": "main", "vars": {"i": 2, "sum": 3}}]},
                {"line": 1, "note": "Update i to 3; 3 <= 4 is true.", "stack": [{"name": "main", "vars": {"i": 3, "sum": 3}}]},
                {"line": 2, "note": "sum = 3 + 3 = 6.", "stack": [{"name": "main", "vars": {"i": 3, "sum": 6}}]},
                {"line": 1, "note": "Update i to 4; 4 <= 4 is true.", "stack": [{"name": "main", "vars": {"i": 4, "sum": 6}}]},
                {"line": 2, "note": "sum = 6 + 4 = 10.", "stack": [{"name": "main", "vars": {"i": 4, "sum": 10}}]},
                {"line": 1, "note": "Update i to 5; 5 <= 4 is false. Exit the loop.", "stack": [{"name": "main", "vars": {"i": 5, "sum": 10}}]},
                {"line": 4, "note": "Print the final sum.", "stack": [{"name": "main", "vars": {"i": 5, "sum": 10}}], "output": "10"},
            ],
        },
        "parsons": {
            "prompt": "Build a loop that adds the numbers 1 through 3 and prints the total.",
            "lines": [
                "int sum = 0;",
                "for (int i = 1; i <= 3; i++) {",
                "    sum += i;",
                "}",
                'System.out.println("sum=" + sum);',
            ],
            "solution": [0, 1, 2, 3, 4],
            "seed": 43,
            "why": "The accumulator starts at zero, the guard includes 3 with <=, and the total is printed after the loop.",
        },
        "quiz": [
            {
                "q": "How many times does for (int i = 0; i < 5; i++) run its body?",
                "options": ["4", "5", "6", "Infinite"],
                "a": 1,
                "why": "i takes the values 0, 1, 2, 3, and 4.",
            },
            {
                "q": "Which loop guarantees at least one execution of its body?",
                "options": ["for", "while", "do-while", "None"],
                "a": 2,
                "why": "do-while checks its condition after the body.",
            },
            {
                "q": "What usually causes an infinite loop?",
                "options": [
                    "A guard that never becomes false",
                    "Too many braces",
                    "Using int instead of long",
                    "Printing inside the body",
                ],
                "a": 0,
                "why": "If the update does not move the state toward the guard failing, the loop repeats forever.",
            },
        ],
        "artifact": {
            "name": "LoopLedger.java",
            "description": "The summation loop with a TODO that prints the running total on every iteration.",
            "code": """public class LoopLedger {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 1; i <= 4; i++) {
            sum += i;
            // TODO: print i and the running sum here.
        }
        System.out.println(sum);
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_05",
        },
        "reflection": "Take a loop you have written. Identify its initialization, guard, update, and terminating state in one sentence each.",
        "studio": {
            "title": "Loop tracer",
            "intro": "The playground records the guard and the body separately. Pause on the final guard check to see why the loop ends.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/core-language.md#chapter-5-loops",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_05",
        },
    },
    {
        "n": 6,
        "title": "Methods",
        "part": "Part 2 · Core language",
        "tagline": "A method is a named contract: parameters go in, a return value comes out, and the caller's state is isolated.",
        "minutes": 35,
        "tags": ["methods", "parameters", "return", "scope", "call stack", "trace"],
        "objectives": [
            "Trace a method call through the call stack.",
            "Explain pass-by-value for primitives and references.",
            "Choose a return type and signature that make misuse hard.",
        ],
        "subgoals": [
            {
                "title": "A call creates a new frame",
                "idea": "Parameters and local variables live in the callee's frame. The caller's variables are not the same storage even when the names match.",
            },
            {
                "title": "Arguments are evaluated before the call",
                "idea": "<code>max(7, 3)</code> passes the values 7 and 3. The method receives copies, not the caller's variables.",
            },
            {
                "title": "return ends the method and produces a value",
                "idea": "A non-void method must return on every reachable path. The returned value becomes the value of the call expression.",
            },
            {
                "title": "Scope is where a name is visible",
                "idea": "A local declared inside a block disappears at the closing brace. Two methods can use the same local name without collision.",
            },
            {
                "title": "Signatures are part of the API",
                "idea": "The name plus parameter types identifies a method. A clear signature makes the contract readable before the body is opened.",
            },
        ],
        "example": {
            "title": "MethodTrace: one call, two frames",
            "intro": "The call stack shows <code>main</code> paused while <code>max</code> runs. The result then returns to the caller.",
            "code": [
                "public class MethodTrace {",
                "    static int max(int a, int b) {",
                "        return a > b ? a : b;",
                "    }",
                "    public static void main(String[] args) {",
                "        int result = max(7, 3);",
                "        System.out.println(result);",
                "    }",
                "}",
            ],
            "steps": [
                {"label": "main starts", "note": "No locals yet. The JVM calls <code>main</code> with an empty argument array."},
                {"label": "Evaluate arguments", "note": "The literals 7 and 3 are ready. A new frame for <code>max</code> is created."},
                {"label": "max executes", "note": "Inside <code>max</code>, a is 7 and b is 3. The conditional expression yields 7."},
                {"label": "Return", "note": "The frame disappears and the value 7 replaces the call expression in <code>main</code>."},
                {"label": "Print", "note": "<code>result</code> is 7, so the output is 7."},
            ],
            "predict": {
                "prompt": "If <code>max</code> assigned <code>a = 0</code> before returning, would the caller's variable change?",
                "answer": "No. The parameter is a copy in the callee's frame; the caller's variable is unaffected.",
            },
        },
        "engine": "trace",
        "spec": {
            "hint": "Watch the stack grow and shrink. The callee's locals never appear in main's frame.",
            "code": [
                "public class MethodTrace {",
                "    static int max(int a, int b) {",
                "        return a > b ? a : b;",
                "    }",
                "    public static void main(String[] args) {",
                "        int result = max(7, 3);",
                "        System.out.println(result);",
                "    }",
                "}",
            ],
            "steps": [
                {"line": 4, "note": "main begins.", "stack": [{"name": "main", "vars": {}}]},
                {"line": 5, "note": "Evaluate max(7, 3); push a new frame.", "stack": [{"name": "main", "vars": {"result": "?"}}, {"name": "max", "vars": {"a": 7, "b": 3}}]},
                {"line": 1, "note": "7 > 3 is true, so the conditional yields 7.", "stack": [{"name": "main", "vars": {"result": "?"}}, {"name": "max", "vars": {"a": 7, "b": 3}}]},
                {"line": 2, "note": "Return 7; the max frame is removed.", "stack": [{"name": "main", "vars": {"result": "?"}}]},
                {"line": 5, "note": "Store the returned value in result.", "stack": [{"name": "main", "vars": {"result": 7}}]},
                {"line": 6, "note": "Print result.", "stack": [{"name": "main", "vars": {"result": 7}}], "output": "7"},
            ],
        },
        "parsons": {
            "prompt": "Build a method that squares an int and a main method that prints 49.",
            "lines": [
                "public class Square {",
                "    static int square(int value) {",
                "        return value * value;",
                "    }",
                "    public static void main(String[] args) {",
                "        System.out.println(square(7));",
                "    }",
                "}",
            ],
            "solution": [0, 1, 2, 3, 4, 5, 6, 7],
            "seed": 47,
            "why": "The method must be declared inside the class, and main can call it because both are static members of the same class.",
        },
        "quiz": [
            {
                "q": "What does a method call create?",
                "options": ["A new class", "A new stack frame", "A new package", "A new thread"],
                "a": 1,
                "why": "Each active call has its own frame holding parameters and locals.",
            },
            {
                "q": "When a primitive is passed to a method, what does the method receive?",
                "options": ["A reference to the caller's variable", "A copy of the value", "Nothing", "The variable name"],
                "a": 1,
                "why": "Java passes primitives by value; the callee cannot change the caller's variable.",
            },
            {
                "q": "What must a non-void method do on every reachable path?",
                "options": ["Print something", "Return a value", "Throw an exception", "Declare a local"],
                "a": 1,
                "why": "The compiler verifies that a value is returned before the method can finish.",
            },
        ],
        "artifact": {
            "name": "MethodTrace.java",
            "description": "The two-frame example with a TODO that adds a second helper and calls it from max's result.",
            "code": """public class MethodTrace {
    static int max(int a, int b) {
        return a > b ? a : b;
    }

    // TODO: add a helper that squares its argument, then print max(7, 3) squared.

    public static void main(String[] args) {
        int result = max(7, 3);
        System.out.println(result);
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_06",
        },
        "reflection": "Draw the call stack for a call to a method that calls another method. Mark the exact moment the first frame resumes.",
        "studio": {
            "title": "Call-stack tracer",
            "intro": "Step forward until two frames are visible, then step until the callee disappears. That is the return path.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/core-language.md#chapter-6-methods",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_06",
        },
    },
    {
        "n": 8,
        "title": "Multidimensional Arrays",
        "part": "Part 2 · Core language",
        "tagline": "A 2D array is an array of arrays: rows are references, and each row can have a different length.",
        "minutes": 40,
        "tags": ["arrays", "2d", "grid", "matrices", "ragged", "structure"],
        "objectives": [
            "Allocate a rectangular 2D array and a ragged one.",
            "Traverse a grid by rows and by columns without swapping indexes.",
            "Explain why <code>grid.length</code> is the number of rows, not the number of cells.",
        ],
        "subgoals": [
            {
                "title": "Rows first, columns second",
                "idea": "<code>grid[row][column]</code> is the convention. Swapping the indexes compiles but reads the transpose.",
            },
            {
                "title": "length is per dimension",
                "idea": "<code>grid.length</code> is the number of rows. <code>grid[0].length</code> is the length of the first row.",
            },
            {
                "title": "Ragged arrays are legal",
                "idea": "Each row is a separate array object, so rows can have different lengths. A rectangular traversal must use each row's own length.",
            },
            {
                "title": "A grid is an array of references",
                "idea": "The outer array stores references to row arrays. Copying the outer reference does not copy the rows.",
            },
            {
                "title": "Row-major traversal is cache-friendly",
                "idea": "Visiting a whole row before moving to the next matches how the data is laid out and how most loops are written.",
            },
        ],
        "example": {
            "title": "GridAudit: row sums and a column probe",
            "intro": "The nested loop visits every cell once. The inner loop uses the current row's length, so the same code would survive a ragged grid.",
            "code": [
                "public class GridAudit {",
                "    public static void main(String[] args) {",
                "        int[][] grid = {",
                "            {3, 5, 7},",
                "            {2, 4, 6},",
                "            {9, 1, 8}",
                "        };",
                "        for (int row = 0; row < grid.length; row++) {",
                "            int rowSum = 0;",
                "            for (int col = 0; col < grid[row].length; col++) {",
                "                rowSum += grid[row][col];",
                "            }",
                '            System.out.println("row " + row + " = " + rowSum);',
                "        }",
                "    }",
                "}",
            ],
            "steps": [
                {"label": "Outer array", "note": "Three row references are created. The outer length is 3."},
                {"label": "Row objects", "note": "Each brace pair creates a row array. All three rows happen to have length 3 here."},
                {"label": "Outer loop", "note": "<code>row</code> selects one row reference at a time."},
                {"label": "Inner loop", "note": "<code>col</code> indexes within that row. The guard uses <code>grid[row].length</code>, not a hard-coded 3."},
                {"label": "Report", "note": "Each row sum is printed before the next row starts. No cell is visited twice."},
            ],
            "predict": {
                "prompt": "What would <code>grid[1][0]</code> print for this grid?",
                "answer": "2. The first index selects the second row; the second index selects its first element.",
            },
        },
        "engine": "structure",
        "spec": {
            "hint": "Step through creation, a single-cell read, and both traversal directions. Highlighted cells are the ones currently inspected.",
            "mode": "grid",
            "frames": [
                {
                    "label": "Grid created",
                    "rows": [["3", "5", "7"], ["2", "4", "6"], ["9", "1", "8"]],
                    "highlight": [],
                    "note": "Three rows, three columns. grid.length is 3 and grid[0].length is 3.",
                },
                {
                    "label": "Read grid[1][0]",
                    "rows": [["3", "5", "7"], ["2", "4", "6"], ["9", "1", "8"]],
                    "highlight": [[1, 0]],
                    "note": "Row 1, column 0 holds 2. The first index is always the row.",
                },
                {
                    "label": "Row-major traversal",
                    "rows": [["3", "5", "7"], ["2", "4", "6"], ["9", "1", "8"]],
                    "highlight": [[0, 0], [0, 1], [0, 2], [1, 0], [1, 1], [1, 2], [2, 0], [2, 1], [2, 2]],
                    "note": "Every row is finished before the next row begins. Each cell is visited once.",
                },
                {
                    "label": "Column probe",
                    "rows": [["3", "5", "7"], ["2", "4", "6"], ["9", "1", "8"]],
                    "highlight": [[0, 1], [1, 1], [2, 1]],
                    "note": "Keeping the column fixed and varying the row walks down a column.",
                },
                {
                    "label": "Update a cell",
                    "rows": [["3", "5", "7"], ["2", "4", "99"], ["9", "1", "8"]],
                    "highlight": [[1, 2]],
                    "note": "Assignment replaces one cell. The outer array and other rows are untouched.",
                },
            ],
        },
        "parsons": {
            "prompt": "Build a nested loop that sums every cell in a rectangular grid.",
            "lines": [
                "int total = 0;",
                "for (int row = 0; row < grid.length; row++) {",
                "    for (int col = 0; col < grid[row].length; col++) {",
                "        total += grid[row][col];",
                "    }",
                "}",
                'System.out.println("total=" + total);',
            ],
            "solution": [0, 1, 2, 3, 4, 5, 6],
            "seed": 53,
            "why": "The accumulator starts at zero, the outer loop selects a row, and the inner loop uses that row's own length.",
        },
        "quiz": [
            {
                "q": "What does grid.length return for a 3-by-4 array?",
                "options": ["12", "3", "4", "7"],
                "a": 1,
                "why": "The outer array has one element per row, so length is the row count.",
            },
            {
                "q": "Which expression reads row 2, column 3?",
                "options": ["grid[3][2]", "grid[2][3]", "grid(2)(3)", "grid[2,3]"],
                "a": 1,
                "why": "Java uses separate bracket pairs, row first.",
            },
            {
                "q": "Why is grid[0].length used instead of a constant?",
                "options": [
                    "It is faster",
                    "It survives a ragged or resized row",
                    "It changes the array",
                    "It is required by the compiler",
                ],
                "a": 1,
                "why": "Reading the current row's length keeps the loop correct for every row shape.",
            },
        ],
        "artifact": {
            "name": "GridAudit.java",
            "description": "The row-sum example with a TODO that computes and prints the sum of the main diagonal.",
            "code": """public class GridAudit {
    public static void main(String[] args) {
        int[][] grid = {
            {3, 5, 7},
            {2, 4, 6},
            {9, 1, 8}
        };

        for (int row = 0; row < grid.length; row++) {
            int rowSum = 0;
            for (int col = 0; col < grid[row].length; col++) {
                rowSum += grid[row][col];
            }
            System.out.println("row " + row + " = " + rowSum);
        }

        // TODO: print the sum of grid[0][0], grid[1][1], grid[2][2].
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_08",
        },
        "reflection": "Sketch a ragged grid and write a traversal that never reads past the end of any row.",
        "studio": {
            "title": "Grid viewer",
            "intro": "The row and column labels stay fixed while the highlight moves. Use the search box to locate a value by its flat index.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/core-language.md#chapter-8-multidimensional-arrays",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_08",
        },
    },
    {
        "n": 9,
        "title": "Objects and Classes",
        "part": "Part 2 · Core language",
        "tagline": "A class defines a contract; an object is one running instance with its own state.",
        "minutes": 40,
        "tags": ["objects", "classes", "constructors", "encapsulation", "contract"],
        "objectives": [
            "Write a constructor that establishes every invariant immediately.",
            "Distinguish a reference from the object it points to.",
            "State preconditions and postconditions for a mutating method.",
        ],
        "subgoals": [
            {
                "title": "The constructor must leave a valid object",
                "idea": "After <code>new</code> returns, every field should satisfy the class invariant. A half-built object is a bug waiting to escape.",
            },
            {
                "title": "Fields hold state; methods define behavior",
                "idea": "Fields describe what an object knows. Methods decide what it can do and which state changes are legal.",
            },
            {
                "title": "A reference can be shared",
                "idea": "Two variables can point to the same object. Mutating through one reference is visible through the other.",
            },
            {
                "title": "this names the current object",
                "idea": "<code>this.balance</code> disambiguates a field from a parameter with the same name.",
            },
            {
                "title": "Preconditions are the caller's job",
                "idea": "If a method requires a nonnegative amount, the caller must supply one. The class can also check and throw to fail fast.",
            },
        ],
        "example": {
            "title": "Account: a constructor and a guarded withdrawal",
            "intro": "The constructor creates a valid balance. <code>withdraw</code> promises the balance never goes negative.",
            "code": [
                "public class Account {",
                "    private int balance;",
                "",
                "    public Account(int initial) {",
                "        if (initial < 0) throw new IllegalArgumentException();",
                "        this.balance = initial;",
                "    }",
                "",
                "    public void withdraw(int amount) {",
                "        if (amount <= 0 || amount > balance) {",
                "            throw new IllegalArgumentException();",
                "        }",
                "        balance -= amount;",
                "    }",
                "",
                "    public int getBalance() { return balance; }",
                "}",
            ],
            "steps": [
                {"label": "Private field", "note": "Only the class can change <code>balance</code> directly."},
                {"label": "Precondition on construction", "note": "A negative initial balance is rejected before the object exists."},
                {"label": "Postcondition", "note": "After construction, <code>balance == initial</code>."},
                {"label": "Guard before mutation", "note": "The withdrawal rejects nonpositive amounts and overdrafts before touching the field."},
                {"label": "Valid mutation", "note": "The subtraction preserves the invariant <code>balance &gt;= 0</code>."},
            ],
            "predict": {
                "prompt": "What happens if <code>withdraw(120)</code> is called on an account with 100?",
                "answer": "The guard fails and throws <code>IllegalArgumentException</code>. The balance remains 100.",
            },
        },
        "engine": "contract",
        "spec": {
            "hint": "Select a case. The clause list shows exactly which precondition or postcondition failed.",
            "signature": "public void withdraw(int amount)",
            "clauses": [
                {"kind": "pre", "text": "initial >= 0", "check": {"type": "nonneg", "field": "initial"}},
                {"kind": "pre", "text": "amount > 0", "check": {"type": "positive", "field": "amount"}},
                {"kind": "post", "text": "balance >= 0", "check": {"type": "nonneg", "field": "balance"}},
            ],
            "cases": [
                {"label": "Valid withdrawal", "inputs": {"initial": 100, "amount": 30, "balance": 70}, "expected": True, "note": "Both preconditions hold and the postcondition is preserved."},
                {"label": "Negative opening balance", "inputs": {"initial": -5, "amount": 10, "balance": -15}, "expected": False, "note": "The constructor rejects the object before any withdrawal."},
                {"label": "Zero withdrawal", "inputs": {"initial": 100, "amount": 0, "balance": 100}, "expected": False, "note": "Zero is not a meaningful withdrawal; reject it explicitly."},
                {"label": "Overdraft", "inputs": {"initial": 50, "amount": 80, "balance": -30}, "expected": False, "note": "The postcondition catches the broken balance."},
            ],
        },
        "parsons": {
            "prompt": "Build a class with a private field and a constructor that rejects negative values.",
            "lines": [
                "public class Counter {",
                "    private int value;",
                "    public Counter(int start) {",
                "        if (start < 0) throw new IllegalArgumentException();",
                "        this.value = start;",
                "    }",
                "    public int getValue() { return value; }",
                "}",
            ],
            "solution": [0, 1, 2, 3, 4, 5, 6, 7],
            "seed": 59,
            "why": "Declare the field, validate the parameter, assign the field, then expose a read-only accessor.",
        },
        "quiz": [
            {
                "q": "What does a constructor guarantee when it returns?",
                "options": ["The object is garbage collected", "The object satisfies its invariants", "Every method has run", "The fields are public"],
                "a": 1,
                "why": "A well-designed constructor leaves the object in a valid state.",
            },
            {
                "q": "If two variables reference the same object, what does mutating through one do?",
                "options": ["Nothing", "The change is visible through both", "It copies the object", "It throws"],
                "a": 1,
                "why": "Both variables hold the same reference.",
            },
            {
                "q": "What does this.balance refer to inside a method?",
                "options": ["A local variable", "The current object's field", "A static field", "The superclass"],
                "a": 1,
                "why": "this is the receiver of the current call.",
            },
        ],
        "artifact": {
            "name": "Account.java",
            "description": "The guarded account class with a TODO that adds a deposit method and states its precondition.",
            "code": """public class Account {
    private int balance;

    public Account(int initial) {
        if (initial < 0) throw new IllegalArgumentException();
        this.balance = initial;
    }

    public void withdraw(int amount) {
        if (amount <= 0 || amount > balance) {
            throw new IllegalArgumentException();
        }
        balance -= amount;
    }

    // TODO: add deposit(int amount) with a positive-amount precondition.

    public int getBalance() { return balance; }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_09",
        },
        "reflection": "Write one precondition and one postcondition for a method you use often. Which one is the caller responsible for?",
        "studio": {
            "title": "Contract tester",
            "intro": "Choose a case and read every clause. The table at the bottom shows whether the contract held across all cases.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/core-language.md#chapter-9-objects-and-classes",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_09",
        },
    },
    {
        "n": 10,
        "title": "Thinking in Objects",
        "part": "Part 2 · Core language",
        "tagline": "Encapsulation is not secrecy for its own sake; it is a small surface that can keep its promises.",
        "minutes": 35,
        "tags": ["encapsulation", "composition", "invariants", "references", "contract"],
        "objectives": [
            "Design a class boundary that protects an invariant.",
            "Explain why returning a mutable field breaks encapsulation.",
            "Choose composition when one object owns another.",
        ],
        "subgoals": [
            {
                "title": "Keep fields private by default",
                "idea": "A private field can only change through class methods, which is where the invariant checks live.",
            },
            {
                "title": "Getters should not leak mutable internals",
                "idea": "Returning an internal array lets a caller change the object without passing through any guard.",
            },
            {
                "title": "Composition means has-a",
                "idea": "An <code>Invoice</code> has a <code>Customer</code>. The contained object keeps its own identity and invariants.",
            },
            {
                "title": "Copy on the boundary",
                "idea": "If a caller must receive internal data, return a copy. If a caller supplies data, copy it on the way in when ownership matters.",
            },
            {
                "title": "Invariants are never optional",
                "idea": "Every public method is an opportunity to break an invariant. Either prevent the change or reject it before applying it.",
            },
        ],
        "example": {
            "title": "ScoreBoard: a private array with a safe view",
            "intro": "The class owns its array. Callers can read a copy, but they cannot mutate the internal storage accidentally.",
            "code": [
                "import java.util.Arrays;",
                "",
                "public class ScoreBoard {",
                "    private final int[] scores;",
                "",
                "    public ScoreBoard(int[] scores) {",
                "        this.scores = scores.clone();",
                "    }",
                "",
                "    public int[] snapshot() {",
                "        return scores.clone();",
                "    }",
                "",
                "    public int total() {",
                "        int sum = 0;",
                "        for (int score : scores) sum += score;",
                "        return sum;",
                "    }",
                "}",
            ],
            "steps": [
                {"label": "Final field", "note": "The reference cannot be reassigned after construction; the array contents still can change internally."},
                {"label": "Defensive copy in", "note": "The caller's array is cloned, so later changes to it do not affect the board."},
                {"label": "Defensive copy out", "note": "The snapshot is also a clone, so callers cannot mutate the internal array."},
                {"label": "Behavior on the inside", "note": "The total is computed from the owned data, not from a caller-supplied array."},
            ],
            "predict": {
                "prompt": "If the constructor stored the caller's array directly, what could a caller do after construction?",
                "answer": "It could change any score by writing to its own array reference, silently breaking the board's total.",
            },
        },
        "engine": "contract",
        "spec": {
            "hint": "The invariant is the class's standing promise. Check which case breaks it.",
            "signature": "public ScoreBoard(int[] scores)",
            "clauses": [
                {"kind": "pre", "text": "scores != null", "check": {"type": "nonnull", "field": "scores"}},
                {"kind": "pre", "text": "scores.length >= 1", "check": {"type": "length", "field": "scores", "min": 1}},
                {"kind": "invariant", "text": "all scores >= 0", "check": {"type": "manual", "field": "allNonnegative", "ok": True}},
                {"kind": "post", "text": "total >= 0", "check": {"type": "nonneg", "field": "total"}},
            ],
            "cases": [
                {"label": "Valid board", "inputs": {"scores": [70, 85, 90], "allNonnegative": True, "total": 245}, "expected": True, "note": "Every clause holds."},
                {"label": "Null array", "inputs": {"scores": None, "allNonnegative": False, "total": 0}, "expected": False, "note": "The constructor should reject null before cloning."},
                {"label": "Empty array", "inputs": {"scores": [], "allNonnegative": True, "total": 0}, "expected": False, "note": "An empty board cannot satisfy the length precondition."},
                {"label": "Negative score", "inputs": {"scores": [70, -5], "allNonnegative": False, "total": 65}, "expected": False, "note": "The invariant rejects the invalid element even though the total is positive."},
            ],
        },
        "parsons": {
            "prompt": "Build the defensive-copy constructor and snapshot method.",
            "lines": [
                "public class ScoreBoard {",
                "    private final int[] scores;",
                "    public ScoreBoard(int[] scores) {",
                "        this.scores = scores.clone();",
                "    }",
                "    public int[] snapshot() {",
                "        return scores.clone();",
                "    }",
                "}",
            ],
            "solution": [0, 1, 2, 3, 4, 5, 6, 7, 8],
            "seed": 61,
            "why": "Both boundaries clone: one to keep the caller from changing the internals, one to keep the caller from mutating the returned array.",
        },
        "quiz": [
            {
                "q": "Why is returning an internal mutable array dangerous?",
                "options": [
                    "It uses too much memory",
                    "The caller can mutate the object's state without checks",
                    "Arrays cannot be returned",
                    "It makes the class final",
                ],
                "a": 1,
                "why": "The reference gives direct write access, bypassing the class's methods and invariants.",
            },
            {
                "q": "Composition describes which relationship?",
                "options": ["is-a", "has-a", "uses-a", "was-a"],
                "a": 1,
                "why": "An object owns or contains another object.",
            },
            {
                "q": "When should a constructor copy an incoming mutable argument?",
                "options": ["Never", "When the class intends to own the data", "Only for strings", "Only in main"],
                "a": 1,
                "why": "Ownership requires isolation from later caller mutations.",
            },
        ],
        "artifact": {
            "name": "ScoreBoard.java",
            "description": "The encapsulated board with a TODO that adds a safe method for the highest score.",
            "code": """public class ScoreBoard {
    private final int[] scores;

    public ScoreBoard(int[] scores) {
        if (scores == null || scores.length == 0) {
            throw new IllegalArgumentException();
        }
        this.scores = scores.clone();
    }

    public int[] snapshot() {
        return scores.clone();
    }

    public int total() {
        int sum = 0;
        for (int score : scores) sum += score;
        return sum;
    }

    // TODO: add highScore() without exposing the internal array.
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_10",
        },
        "reflection": "Find one getter in your own code that returns a mutable object. Decide whether it needs a copy, a read-only view, or neither.",
        "studio": {
            "title": "Invariant tester",
            "intro": "The contract is the class's standing promise. Step through the cases and identify which boundary was crossed.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/core-language.md#chapter-10-thinking-in-objects",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_10",
        },
    },
    {
        "n": 11,
        "title": "Inheritance and Polymorphism",
        "part": "Part 2 · Core language",
        "tagline": "The compiler checks the declared type; the runtime chooses the override.",
        "minutes": 45,
        "tags": ["inheritance", "polymorphism", "overriding", "dispatch", "super", "trace"],
        "objectives": [
            "Trace a virtual method call from the declared type to the runtime type.",
            "Distinguish overriding from overloading.",
            "Use super to reuse a parent's contract without duplicating its state.",
        ],
        "subgoals": [
            {
                "title": "A subtype is substitutable",
                "idea": "A <code>Dog</code> can be used wherever an <code>Animal</code> is expected. The declared type controls which members are visible.",
            },
            {
                "title": "Overrides are chosen at runtime",
                "idea": "The actual object's class supplies the method body. This is dynamic dispatch.",
            },
            {
                "title": "Overloads are chosen at compile time",
                "idea": "Parameter types decide which overload is called. The runtime type of an argument does not participate.",
            },
            {
                "title": "super calls the parent version",
                "idea": "A subclass can extend behavior instead of replacing it. The parent's constructor runs before the child's body.",
            },
            {
                "title": "Protected is a package-and-subclass boundary",
                "idea": "Use the narrowest access that still allows the subclass to do its job.",
            },
        ],
        "example": {
            "title": "Dispatch: one reference, two types",
            "intro": "The variable is declared as <code>Animal</code> but refers to a <code>Dog</code>. The call still reaches <code>Dog.speak</code>.",
            "code": [
                "class Animal {",
                "    String speak() { return \"...\"; }",
                "}",
                "class Dog extends Animal {",
                "    @Override String speak() { return \"Woof\"; }",
                "}",
                "public class Dispatch {",
                "    public static void main(String[] args) {",
                "        Animal pet = new Dog();",
                "        System.out.println(pet.speak());",
                "    }",
                "}",
            ],
            "steps": [
                {"label": "Declared type", "note": "<code>pet</code> is visible as an <code>Animal</code>. Only Animal's members can be called through it."},
                {"label": "Runtime type", "note": "The object was created with <code>new Dog()</code>, so its class is Dog."},
                {"label": "Virtual call", "note": "The JVM looks up <code>speak()</code> on the runtime class and finds Dog's override."},
                {"label": "Return", "note": "The override returns \"Woof\", even though the call was written against Animal."},
            ],
            "predict": {
                "prompt": "If Dog added a method <code>fetch()</code> that Animal does not have, could <code>pet.fetch()</code> compile?",
                "answer": "No. The compiler only sees the declared type Animal, which has no fetch method.",
            },
        },
        "engine": "trace",
        "spec": {
            "hint": "Notice the frame's class: the runtime receiver decides the override, not the declared type of the variable.",
            "code": [
                "class Animal {",
                "    String speak() { return \"...\"; }",
                "}",
                "class Dog extends Animal {",
                "    @Override String speak() { return \"Woof\"; }",
                "}",
                "public class Dispatch {",
                "    public static void main(String[] args) {",
                "        Animal pet = new Dog();",
                "        System.out.println(pet.speak());",
                "    }",
                "}",
            ],
            "steps": [
                {"line": 7, "note": "main begins.", "stack": [{"name": "main", "vars": {}}]},
                {"line": 8, "note": "Create a Dog object and store it in an Animal reference.", "stack": [{"name": "main", "vars": {"pet": "Dog@1"}}]},
                {"line": 9, "note": "Dispatch speak() on the runtime class Dog.", "stack": [{"name": "main", "vars": {"pet": "Dog@1"}}, {"name": "Dog.speak", "vars": {}}]},
                {"line": 4, "note": "The override returns \"Woof\".", "stack": [{"name": "main", "vars": {"pet": "Dog@1"}}]},
                {"line": 9, "note": "Print the returned string.", "stack": [{"name": "main", "vars": {"pet": "Dog@1"}}], "output": "Woof"},
            ],
        },
        "parsons": {
            "prompt": "Build a parent class and a child that overrides its method.",
            "lines": [
                "class Animal {",
                "    String speak() { return \"...\"; }",
                "}",
                "class Dog extends Animal {",
                "    @Override",
                "    String speak() { return \"Woof\"; }",
                "}",
            ],
            "solution": [0, 1, 2, 3, 4, 5, 6],
            "seed": 67,
            "why": "The child declares extends, then repeats the same signature with @Override so the compiler can verify the relationship.",
        },
        "quiz": [
            {
                "q": "Which type decides whether a method call compiles?",
                "options": ["The runtime type", "The declared type", "The return type", "The package"],
                "a": 1,
                "why": "The compiler checks the reference's declared type.",
            },
            {
                "q": "Which type decides which override runs?",
                "options": ["The declared type", "The runtime type", "The variable name", "The constructor"],
                "a": 1,
                "why": "Dynamic dispatch uses the object's actual class.",
            },
            {
                "q": "What does @Override ask the compiler to verify?",
                "options": [
                    "That the method is faster",
                    "That a superclass method with the same signature exists",
                    "That the class is final",
                    "That the method is static",
                ],
                "a": 1,
                "why": "It catches signature typos that would otherwise create a new method instead of overriding.",
            },
        ],
        "artifact": {
            "name": "Dispatch.java",
            "description": "The dispatch example with a TODO that adds a Cat subclass and a loop over an Animal array.",
            "code": """class Animal {
    String speak() { return "..."; }
}

class Dog extends Animal {
    @Override String speak() { return "Woof"; }
}

// TODO: add Cat with its own override.

public class Dispatch {
    public static void main(String[] args) {
        Animal pet = new Dog();
        System.out.println(pet.speak());
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_11",
        },
        "reflection": "Explain why adding a method to a subclass does not make it callable through a parent reference. What safety property does that enforce?",
        "studio": {
            "title": "Dispatch tracer",
            "intro": "The stack frame names show the runtime class. Compare that with the declared type in main.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/core-language.md#chapter-11-inheritance-and-polymorphism",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_11",
        },
    },
    {
        "n": 12,
        "title": "Exception Handling and Text I/O",
        "part": "Part 2 · Core language",
        "tagline": "An exception is a control path with a destination; catch only what you can actually handle.",
        "minutes": 40,
        "tags": ["exceptions", "try", "catch", "finally", "parsing", "text io", "state"],
        "objectives": [
            "Trace a thrown exception from the failure point to the matching catch block.",
            "Distinguish checked from unchecked exceptions.",
            "Use try-with-resources to guarantee cleanup.",
        ],
        "subgoals": [
            {
                "title": "Throw unwinds the stack",
                "idea": "The JVM abandons the current frame and looks for a matching catch in each caller. Frames without a handler disappear.",
            },
            {
                "title": "Catch by type",
                "idea": "A catch for a superclass accepts subclasses. Order specific catches before general ones or the general one shadows them.",
            },
            {
                "title": "Checked exceptions are part of the signature",
                "idea": "The compiler requires callers to catch or declare them. Unchecked exceptions usually indicate programming errors.",
            },
            {
                "title": "finally always runs",
                "idea": "Use it for cleanup that must happen whether the try succeeds, throws, or returns early.",
            },
            {
                "title": "Fail with context",
                "idea": "Wrap a low-level exception in a domain exception when the caller needs to know what operation failed, not just which library threw.",
            },
        ],
        "example": {
            "title": "ParseGuard: recover from bad text",
            "intro": "The first parse succeeds; the second throws, is caught, and the program continues with a fallback value.",
            "code": [
                "public class ParseGuard {",
                "    static int parse(String text, int fallback) {",
                "        try {",
                "            return Integer.parseInt(text);",
                "        } catch (NumberFormatException ex) {",
                "            return fallback;",
                "        }",
                "    }",
                "    public static void main(String[] args) {",
                '        System.out.println(parse("42", -1));',
                '        System.out.println(parse("4o", -1));',
                "    }",
                "}",
            ],
            "steps": [
                {"label": "Valid parse", "note": "\"42\" becomes 42 and the method returns normally."},
                {"label": "Invalid parse", "note": "\"4o\" cannot be converted. <code>parseInt</code> throws a NumberFormatException."},
                {"label": "Matching catch", "note": "The catch in <code>parse</code> handles the exception, so the caller never sees it."},
                {"label": "Recovery", "note": "The fallback -1 is returned instead of crashing the program."},
            ],
            "predict": {
                "prompt": "What would happen if the catch block were removed?",
                "answer": "The exception would propagate out of <code>parse</code>. Because it is unchecked, the program would terminate with a stack trace unless a caller caught it.",
            },
        },
        "engine": "state",
        "spec": {
            "hint": "Follow text through the try and the catch. The output only appears when the method returns.",
            "code": [
                'String text = "4o";',
                "int value;",
                "try {",
                "    value = Integer.parseInt(text);",
                "} catch (NumberFormatException ex) {",
                "    value = -1;",
                "}",
                "System.out.println(value);",
            ],
            "vars": ["text", "value", "flow"],
            "steps": [
                {"line": 0, "vars": {"text": '"4o"', "value": "—", "flow": "try"}, "note": "The input is not a valid integer."},
                {"line": 3, "vars": {"text": '"4o"', "value": "—", "flow": "throwing"}, "note": "parseInt throws; the assignment never completes."},
                {"line": 5, "vars": {"text": '"4o"', "value": "-1", "flow": "catch"}, "note": "The matching catch catches NumberFormatException and assigns the fallback."},
                {"line": 7, "vars": {"text": '"4o"', "value": "-1", "flow": "after"}, "note": "Execution continues after the try/catch. The program did not terminate."},
            ],
        },
        "parsons": {
            "prompt": "Build a method that returns a fallback when parsing fails.",
            "lines": [
                "static int parse(String text, int fallback) {",
                "    try {",
                "        return Integer.parseInt(text);",
                "    } catch (NumberFormatException ex) {",
                "        return fallback;",
                "    }",
                "}",
            ],
            "solution": [0, 1, 2, 3, 4, 5, 6],
            "seed": 71,
            "why": "The risky operation is inside try; the matching catch supplies a value on every failure path.",
        },
        "quiz": [
            {
                "q": "What happens when an exception is thrown and no frame handles it?",
                "options": ["The program continues", "The JVM terminates the program", "The exception becomes a warning", "The method returns null"],
                "a": 1,
                "why": "An uncaught exception ends the thread and usually the program.",
            },
            {
                "q": "Which block always runs, even after a return in try?",
                "options": ["catch", "finally", "else", "static"],
                "a": 1,
                "why": "finally is the cleanup path.",
            },
            {
                "q": "Why catch NumberFormatException specifically?",
                "options": [
                    "It is faster",
                    "To handle the actual failure instead of hiding every exception",
                    "Because it is checked",
                    "To avoid importing Integer",
                ],
                "a": 1,
                "why": "Specific catches keep recovery honest and let unexpected failures surface.",
            },
        ],
        "artifact": {
            "name": "ParseGuard.java",
            "description": "The parse-with-fallback example plus a TODO that reports the bad text when the fallback is used.",
            "code": """public class ParseGuard {
    static int parse(String text, int fallback) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException ex) {
            // TODO: report which text failed without printing a stack trace.
            return fallback;
        }
    }

    public static void main(String[] args) {
        System.out.println(parse("42", -1));
        System.out.println(parse("4o", -1));
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_12",
        },
        "reflection": "Describe a situation where catching an exception is worse than letting it propagate. What information would the caller lose?",
        "studio": {
            "title": "Exception state machine",
            "intro": "The flow variable shows the transition from try to throw to catch. The value box shows when the assignment finally happens.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/core-language.md#chapter-12-exception-handling-and-text-io",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_12",
        },
    },
    {
        "n": 13,
        "title": "Abstract Classes and Interfaces",
        "part": "Part 2 · Core language",
        "tagline": "An interface is a promise without a body; an abstract class is a partial implementation with a boundary.",
        "minutes": 40,
        "tags": ["interfaces", "abstract", "polymorphism", "default methods", "contract"],
        "objectives": [
            "Choose between an interface and an abstract class for a design.",
            "Write a contract that every implementation can satisfy.",
            "Explain why default methods preserve backward compatibility.",
        ],
        "subgoals": [
            {
                "title": "An interface names capabilities",
                "idea": "A class can implement many interfaces. Use an interface when unrelated types can share a promise.",
            },
            {
                "title": "An abstract class shares partial state",
                "idea": "It can hold fields and concrete methods while requiring subclasses to finish specific operations.",
            },
            {
                "title": "Contracts belong at the boundary",
                "idea": "Preconditions, postconditions, and invariants should be documented on the interface so every implementation is judged by the same rules.",
            },
            {
                "title": "Default methods add behavior without breaking clients",
                "idea": "An interface can supply a method body. Existing implementers inherit it instead of failing to compile.",
            },
            {
                "title": "Program to the interface",
                "idea": "Callers that depend on the interface can accept any implementation, including test doubles.",
            },
        ],
        "example": {
            "title": "ShapeContract: one interface, two implementations",
            "intro": "The interface promises a name, an area, and a perimeter. The tester does not care whether the shape is a circle or a square.",
            "code": [
                "interface Shape {",
                "    String name();",
                "    double area();",
                "    double perimeter();",
                "}",
                "class Square implements Shape {",
                "    private final double side;",
                "    Square(double side) { this.side = side; }",
                "    public String name() { return \"square\"; }",
                "    public double area() { return side * side; }",
                "    public double perimeter() { return 4 * side; }",
                "}",
            ],
            "steps": [
                {"label": "Interface", "note": "No fields and no constructor; just the three promises."},
                {"label": "Implementation", "note": "The class supplies the state and every method body."},
                {"label": "Positive side", "note": "A valid implementation should reject a nonpositive side or document the precondition."},
                {"label": "Area and perimeter", "note": "Both must be positive for every valid shape."},
                {"label": "Polymorphic use", "note": "A method accepting <code>Shape</code> works with Square and any future implementation."},
            ],
            "predict": {
                "prompt": "If a new <code>Circle</code> class is added, does the tester need to change?",
                "answer": "No, as long as Circle implements the same interface contract. That is the point of programming to the interface.",
            },
        },
        "engine": "contract",
        "spec": {
            "hint": "Every implementation is judged by the same clauses. Pick a case and see which promise fails.",
            "signature": "interface Shape { String name(); double area(); double perimeter(); }",
            "clauses": [
                {"kind": "post", "text": "name != null", "check": {"type": "nonnull", "field": "name"}},
                {"kind": "post", "text": "area > 0", "check": {"type": "positive", "field": "area"}},
                {"kind": "post", "text": "perimeter > 0", "check": {"type": "positive", "field": "perimeter"}},
            ],
            "cases": [
                {"label": "Valid square", "inputs": {"name": "square", "area": 16, "perimeter": 16}, "expected": True, "note": "All three promises hold."},
                {"label": "Zero side", "inputs": {"name": "square", "area": 0, "perimeter": 0}, "expected": False, "note": "A degenerate shape violates both positive-value promises."},
                {"label": "Missing name", "inputs": {"name": None, "area": 12.5, "perimeter": 14}, "expected": False, "note": "A null name makes the shape unusable in reports and tests."},
                {"label": "Valid circle", "inputs": {"name": "circle", "area": 78.5, "perimeter": 31.4}, "expected": True, "note": "The tester does not need to know the concrete class."},
            ],
        },
        "parsons": {
            "prompt": "Build an interface and one implementation that satisfies it.",
            "lines": [
                "interface Shape {",
                "    double area();",
                "    double perimeter();",
                "}",
                "class Square implements Shape {",
                "    private final double side;",
                "    Square(double side) { this.side = side; }",
                "    public double area() { return side * side; }",
                "    public double perimeter() { return 4 * side; }",
                "}",
            ],
            "solution": [0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
            "seed": 73,
            "why": "The interface declares only promises; the class supplies the field, constructor, and both bodies.",
        },
        "quiz": [
            {
                "q": "How many interfaces can one Java class implement?",
                "options": ["Exactly one", "At most two", "Any number", "None"],
                "a": 2,
                "why": "A class can implement multiple interfaces, which is how Java supports multiple capability inheritance.",
            },
            {
                "q": "What can an abstract class provide that an interface traditionally could not?",
                "options": ["Method signatures", "Instance fields and constructors", "Constants", "Public methods"],
                "a": 1,
                "why": "An abstract class can hold state and initialization logic; interfaces describe capabilities.",
            },
            {
                "q": "Why were default methods added to interfaces?",
                "options": [
                    "To make interfaces slower",
                    "To add behavior without breaking existing implementers",
                    "To replace abstract classes",
                    "To allow multiple constructors",
                ],
                "a": 1,
                "why": "Existing classes inherit the default body instead of being forced to implement a new method.",
            },
        ],
        "artifact": {
            "name": "ShapeContract.java",
            "description": "The interface and square implementation with a TODO that adds a circle and a contract check for positive dimensions.",
            "code": """interface Shape {
    String name();
    double area();
    double perimeter();
}

class Square implements Shape {
    private final double side;

    Square(double side) {
        if (side <= 0) throw new IllegalArgumentException();
        this.side = side;
    }

    public String name() { return "square"; }
    public double area() { return side * side; }
    public double perimeter() { return 4 * side; }
}

// TODO: add Circle and a test that accepts any Shape.
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_13",
        },
        "reflection": "Name one design where an interface is clearly better than an abstract class, and one where the abstract class is better. Justify both.",
        "studio": {
            "title": "Shape contract tester",
            "intro": "The clause list is the design. Every implementation should satisfy exactly these promises, no more and no less.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/core-language.md#chapter-13-abstract-classes-and-interfaces",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_13",
        },
    },
]
