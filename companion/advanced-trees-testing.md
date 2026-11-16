# Advanced Trees and Testing

This final volume closes two loops. The first is structural: binary search trees are elegant, but a balanced multiway tree is what makes databases and file systems fast. The second is methodological: a data structure is not finished until a test can prove it still behaves after the next change.

- [Chapter 40 — 2-4 Trees and B-Trees](#chapter-40-2-4-trees-and-b-trees)
- [Chapter 41 — Red-Black Trees](#chapter-41-red-black-trees)
- [Chapter 42 — Testing with JUnit](#chapter-42-testing-with-junit)

---

<a id="chapter-40-2-4-trees-and-b-trees"></a>
<a name="chapter-40-2-4-trees-and-b-trees"></a>
## Chapter 40 — 2-4 Trees and B-Trees

### The idea

A binary tree stores one key per node. A **2-4 tree** stores one, two, or three keys per node and has two, three, or four children. The extra keys make the tree shorter, which matters when every node access is expensive.

The rules for a 2-4 tree:

- Every internal node has 2, 3, or 4 children.
- A node with `k` keys has `k + 1` children.
- All leaves are at the same depth.
- Keys inside a node are sorted, and the subtrees between them respect the order.

A **B-tree** generalizes the idea to a large maximum degree. A B-tree of order `m` allows up to `m` children per node and keeps every leaf at the same depth. Databases use B-trees and B+ trees because one node can hold hundreds of keys, matching the size of a disk page.

### Why multiway trees matter

A binary search tree with one million nodes has a height of about 20 when balanced. That is fine in memory. A disk page read is thousands of times slower than a CPU comparison, so a binary tree would perform up to 20 page reads. A B-tree with 200 keys per node has a height of about 3. The difference is not a constant factor; it is the difference between a fast index and a slow one.

This is the key insight of the chapter: **the cost model changes the data structure.** In memory, comparisons dominate. On disk, page transfers dominate.

### Insertion and splitting

Insert into the appropriate leaf. If the leaf overflows, split it and promote the middle key to the parent. The split can cascade upward. If the root splits, a new root is created and the tree grows by one level — which is why every leaf stays at the same depth.

A 2-4 tree node with four keys is not allowed. The split is the invariant repair:

```text
before:  [10  20  30  40]
after:   [20]  with children [10] and [30  40]
```

The exact promotion rule depends on the order. For a B-tree, the middle key moves up and the remaining keys form two nodes. The parent may then overflow, so the process repeats.

Deletion is the mirror image: borrow from a sibling, or merge with a sibling and pull a key down from the parent. Merging can cascade, and the root can disappear when its last key is removed.

### B+ trees

A B+ tree stores all keys in the leaves and uses internal nodes only for routing. Leaves are linked, which makes range scans efficient:

```text
internal:      [30 | 70]
              /    |    \
leaves:   [10 20] [30 50] [70 90]
             |       |       |
             +-------+-------+
```

A query for “all keys between 35 and 80” finds the first leaf and follows the links. A B-tree would need a traversal. That is why most relational databases use B+ trees for secondary indexes.

### What to notice

- **A B-tree is not a binary tree with more keys.** The balance rule is global: all leaves at the same depth.
- **The minimum degree is a design parameter.** It trades height against node size and split frequency.
- **Splits are amortized.** A single insertion can split many nodes, but the total work over a sequence is still logarithmic per operation.
- **The same idea appears in file systems.** NTFS, ext4, and many others use B-tree variants for directories and metadata.

### Practice

1. Insert `10, 20, 30, 40, 50` into a 2-4 tree and draw every split.
2. Explain why a B-tree with order 200 is shallower than a balanced BST for one million keys.
3. Sketch a B+ tree range scan from key 35 to key 80. How many leaves are visited?

### Checkpoint

You are ready to move on when you can state the 2-4 tree invariants, explain why a B-tree is shallower than a BST, and describe the difference between a B-tree and a B+ tree.

---

<a id="chapter-41-red-black-trees"></a>
<a name="chapter-41-red-black-trees"></a>
## Chapter 41 — Red-Black Trees

### The idea

A red-black tree is a binary search tree that stays balanced by coloring nodes and enforcing five rules:

1. Every node is red or black.
2. The root is black.
3. Every leaf (`nil`) is black.
4. A red node has black children. Equivalently, no red node has a red parent.
5. Every path from a node to its descendant leaves contains the same number of black nodes.

The fifth rule is the one that does the work. It guarantees that the longest path is at most twice the shortest path, so the height is `O(log n)`.

Red-black trees are popular because they allow **one rotation per insertion** in the worst case and `O(log n)` updates, while keeping the bookkeeping simpler than AVL in many implementations. Java’s `TreeMap` and `TreeSet` are red-black trees.

### Rotations and recoloring

A rotation is the same local restructuring used by AVL trees. A red-black insertion adds a red node, then repairs violations by recoloring an uncle or rotating around a grandparent.

The cases are usually taught with the parent and uncle:

- **Case 1:** the uncle is red. Recolor the parent and uncle black, the grandparent red, and move the problem upward.
- **Case 2:** the uncle is black and the new node is an “inner” child. Rotate the parent to turn it into case 3.
- **Case 3:** the uncle is black and the new node is an “outer” child. Rotate the grandparent and recolor.

The labels are less important than the invariant. If you can state what must be true after the repair — root black, no red-red edge, equal black height — you can reconstruct the cases.

### A compact insertion sketch

```java
private Node insert(Node node, int key) {
    if (node == null) return new Node(key, RED);
    if (key < node.key) node.left = insert(node.left, key);
    else if (key > node.key) node.right = insert(node.right, key);
    else return node;

    if (isRed(node.right) && !isRed(node.left)) node = rotateLeft(node);
    if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
    if (isRed(node.left) && isRed(node.right)) flipColors(node);

    return node;
}

private void flipColors(Node node) {
    node.color = RED;
    node.left.color = BLACK;
    node.right.color = BLACK;
}
```

This left-leaning variant (from Sedgewick) is easier to code because it removes one symmetric case. It is still a red-black tree; the colors and rotations preserve the same invariants.

### Red-black vs AVL

| Property | AVL | Red-black |
| --- | --- | --- |
| Balance | stricter (height difference ≤ 1) | looser (longest ≤ 2× shortest) |
| Lookup | slightly faster | slightly slower |
| Insert/delete rotations | more | fewer |
| Typical use | read-heavy indexes | general-purpose maps |

Both are `O(log n)`. The choice is a constant-factor and workload decision, not an asymptotic one. If reads dominate and writes are rare, AVL’s tighter height can win. If writes are frequent, red-black’s cheaper repair can win.

### What to notice

- **Color is metadata, not decoration.** It encodes the black-height invariant.
- **The nil leaves matter.** They are where the black-height count terminates.
- **Deletion is the hard case.** It has more cases than insertion and is easy to get subtly wrong. Test it heavily.
- **A red-black tree is not “sort of balanced.”** The height bound is a proof, not a heuristic.

### Practice

1. Insert `10, 20, 30, 15, 25, 5` into a red-black tree and draw the color changes.
2. Write a validator that checks all five rules on a random tree.
3. Compare the height of an AVL tree and a red-black tree built from the same 1,000 sorted keys.

### Checkpoint

You are ready to move on when you can state the five red-black rules, explain what a rotation preserves, and describe why the black-height rule bounds the height.

---

<a id="chapter-42-testing-with-junit"></a>
<a name="chapter-42-testing-with-junit"></a>
## Chapter 42 — Testing with JUnit

### The idea

A test is an executable claim about behavior. JUnit turns those claims into a repeatable suite that runs on every change. The point is not “coverage”; the point is **confidence to change the code**.

A good unit test has four properties:

1. **Fast** — milliseconds, not minutes.
2. **Deterministic** — same result every run.
3. **Isolated** — no shared mutable state, no network, no real clock.
4. **Specific** — a failure name tells you what broke.

### The structure

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    @Test
    void insertsAndFindsKeys() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);

        assertTrue(tree.contains(30));
        assertFalse(tree.contains(40));
    }

    @Test
    void rejectsDuplicateKeys() {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(10);
        tree.insert(10);
        assertEquals(1, tree.size());
    }
}
```

The test name is a sentence about behavior. `testInsert` tells you nothing; `rejectsDuplicateKeys` tells you what contract broke.

### Assertions that explain failures

```java
assertEquals(3, list.size(), "size after three adds");
assertThrows(IndexOutOfBoundsException.class,
    () -> list.get(3));
assertAll(
    () -> assertEquals(1, tree.min()),
    () -> assertEquals(99, tree.max()),
    () -> assertEquals(5, tree.size())
);
```

`assertAll` runs every assertion and reports all failures, not just the first. The message is part of the test’s interface; write it for the person debugging at 2 a.m.

### Fixtures and lifecycle

```java
class AccountTest {
    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account("Ada", 100);
    }

    @Test
    void withdrawReducesBalance() {
        account.withdraw(30);
        assertEquals(70, account.balance());
    }

    @Test
    void withdrawRejectsOverdraft() {
        assertThrows(IllegalStateException.class,
            () -> account.withdraw(101));
    }
}
```

A fresh fixture per test prevents order dependence. `@BeforeAll` is for expensive, immutable setup; do not use it to share mutable state.

### Parameterized tests

When the same rule has many inputs, do not copy the test:

```java
@ParameterizedTest
@CsvSource({
    "1, 1",
    "2, 2",
    "3, 6",
    "5, 120"
})
void computesFactorial(int input, int expected) {
    assertEquals(expected, MathUtil.factorial(input));
}
```

### What to test

- **The contract, not the implementation.** Test that a set rejects duplicates, not that it calls a particular private method.
- **Boundaries.** Empty, one element, first, last, duplicate, null if allowed.
- **Failure paths.** Invalid input should fail in a documented way, not a random `NullPointerException`.
- **Invariants after mutation.** Insert, delete, then validate ordering and balance.
- **Concurrency only when you own the concurrency.** A flaky test is worse than no test.

### What not to do

- Do not assert on `System.out`. Return values or inject a collaborator.
- Do not use `Thread.sleep` to wait for async work. Use a latch, a future, or an awaitility-style condition.
- Do not test third-party libraries. Test your use of them.
- Do not chase 100% coverage. Coverage shows what ran, not what was checked.

### Test doubles

A **stub** returns canned data. A **fake** is a working lightweight implementation (an in-memory repository). A **mock** records interactions and is useful when the interaction itself is the contract. Prefer a fake for persistence; prefer a real object for pure logic.

### What to notice

- **A test that passes for the wrong reason is a liability.** Assert the important outcome, not just “no exception.”
- **Flaky tests erode trust.** Fix or delete them; do not retry them into silence.
- **Tests are documentation.** A new contributor should be able to read the test names and understand the intended behavior.
- **CI is the enforcement point.** A test suite that only runs on a laptop is a suggestion.

### Practice

1. Write tests for the AVL tree: insertion, deletion, duplicate rejection, and the balance invariant after 1,000 random operations.
2. Replace a `Thread.sleep` in an async test with a `CountDownLatch`.
3. Write a parameterized test for a payroll bracket function with at least five boundary cases.

### Checkpoint

You have finished the companion curriculum when you can write a focused test before changing a data structure, explain the difference between a stub, a fake, and a mock, and describe why a fast deterministic suite is a design tool rather than a chore.

---

## Where to go next

Pick a chapter you skipped, open its lab, and break it on purpose. Then write a test that captures what you expected. That loop — read, run, break, prove — is the whole method.
