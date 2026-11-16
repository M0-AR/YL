# Collections and Algorithms: Making Data Obey

This volume covers the part of the curriculum where the question changes from “how do I write this?” to “how does this behave at scale?” The chapters are linked because they share one idea: a data structure is a promise about cost, order, and invariants. The implementation is how the promise is kept.

- [Chapter 21 — Sets and Maps](#chapter-21-sets-and-maps)
- [Chapter 22 — Efficient Algorithms](#chapter-22-efficient-algorithms)
- [Chapter 23 — Sorting](#chapter-23-sorting)
- [Chapter 24 — Implementing Collections](#chapter-24-implementing-collections)
- [Chapter 25 — Binary Search Trees](#chapter-25-binary-search-trees)
- [Chapter 26 — AVL Trees](#chapter-26-avl-trees)
- [Chapter 27 — Hashing](#chapter-27-hashing)
- [Chapter 29 — Weighted Graphs](#chapter-29-weighted-graphs)

---

<a id="chapter-21-sets-and-maps"></a>
<a name="chapter-21-sets-and-maps"></a>
## Chapter 21 — Sets and Maps

### The idea

A `Set` answers one question: “have I seen this before?” A `Map` answers a different one: “what is associated with this key?” Both are built on the same machinery — a way to decide whether two keys are the same — but they expose different views of the data.

The three common set implementations make different promises:

| Type | Order | Typical lookup | Best for |
| --- | --- | --- | --- |
| `HashSet` | none | O(1) average | membership, deduplication |
| `LinkedHashSet` | insertion order | O(1) average | predictable iteration |
| `TreeSet` | sorted | O(log n) | range queries, ordered output |

The map family mirrors it: `HashMap`, `LinkedHashMap`, `TreeMap`.

### The contract you must keep

Hashing only works if two ideas agree:

- If `a.equals(b)` is true, then `a.hashCode() == b.hashCode()` must also be true.
- If two objects are not equal, their hash codes may collide. A collision is allowed; it is not a bug.

```java
record Book(String isbn, String title) {}
```

A `record` generates `equals` and `hashCode` from its components, which is exactly the behavior a map key needs. A hand-written class must do the same. A mutable key is dangerous: if a field used in `hashCode` changes while the key is in a `HashSet`, the set can no longer find it.

### Counting with a map

The classic pattern is a frequency table:

```java
Map<String, Integer> counts = new HashMap<>();
for (String word : words) {
    counts.merge(word, 1, Integer::sum);
}
```

`merge` is a compact version of “get, add, put back.” The older explicit version teaches the same control flow:

```java
Integer previous = counts.get(word);
counts.put(word, previous == null ? 1 : previous + 1);
```

For grouping, `computeIfAbsent` removes the null check:

```java
Map<String, List<String>> byInitial = new TreeMap<>();
for (String name : names) {
    byInitial.computeIfAbsent(name.substring(0, 1), k -> new ArrayList<>())
             .add(name);
}
```

### Choosing the right view

`Map.keySet()`, `values()`, and `entrySet()` are views, not copies. Changing the map changes the view. Iterating a view while structurally modifying the map throws `ConcurrentModificationException`; collect the changes first or use the iterator’s `remove`.

```java
Iterator<Map.Entry<String, Integer>> it = counts.entrySet().iterator();
while (it.hasNext()) {
    if (it.next().getValue() < 2) it.remove();
}
```

### What to notice

- **Order is a promise, not a default.** If your output must be stable, choose a map that promises order.
- **`TreeMap` sorts by `compareTo`, not by `equals`.** A comparator inconsistent with equals can make a sorted map behave strangely.
- **Nulls are policy.** `HashMap` allows one null key; `TreeMap` with natural ordering does not. `Set.of` and `Map.of` reject nulls entirely.
- **A hash collision is normal.** A hash table that never collided would not be a hash table; it would be an array with a perfect index function.

### Practice

1. Count the words in a paragraph, then print the five most frequent using a `PriorityQueue` or a sorted stream.
2. Group students by grade, preserving insertion order. Which map type do you choose and why?
3. Write a class with a bad `hashCode` that returns a constant. Measure how lookup time changes as the set grows.

### Checkpoint

You are ready to move on when you can explain why `equals` and `hashCode` must agree, when you would choose `TreeMap` over `HashMap`, and why a mutable key can disappear inside a set.

---

<a id="chapter-22-efficient-algorithms"></a>
<a name="chapter-22-efficient-algorithms"></a>
## Chapter 22 — Efficient Algorithms

### The idea

An algorithm is efficient when its work grows slowly as the input grows. That sentence is more useful than a table of Big-O definitions because it forces you to ask: which operation is growing, and how often does it run?

The common growth classes, from fast to slow:

- `O(1)` — constant: array index, hash lookup average.
- `O(log n)` — halving: binary search, balanced-tree lookup.
- `O(n)` — one pass: scanning, counting.
- `O(n log n)` — sorting by comparison.
- `O(n²)` — nested loops over the same input.
- `O(2ⁿ)` — exploring subsets.

Big-O hides constants, and constants matter in real systems. A hash lookup with a terrible hash function is not constant in practice. A “fast” algorithm with a huge allocation can lose to a simple loop. Use complexity to eliminate bad designs, then measure to choose between good ones.

### Pattern 1: binary search

Binary search is not “search a sorted array.” It is “discard half of the remaining candidates with one comparison.”

```java
static int indexOf(int[] sorted, int target) {
    int low = 0, high = sorted.length - 1;
    while (low <= high) {
        int mid = low + (high - low) / 2;
        if (sorted[mid] == target) return mid;
        if (sorted[mid] < target) low = mid + 1;
        else high = mid - 1;
    }
    return -1;
}
```

`low + (high - low) / 2` avoids overflow when the indices are large. The loop condition and the two boundary updates are the entire proof: the target is always inside `[low, high]` or it is absent.

### Pattern 2: two pointers

When the input is sorted, a pair problem can often be solved without a nested loop.

```java
static boolean hasPairSum(int[] sorted, int target) {
    int left = 0, right = sorted.length - 1;
    while (left < right) {
        int sum = sorted[left] + sorted[right];
        if (sum == target) return true;
        if (sum < target) left++;
        else right--;
    }
    return false;
}
```

Each step removes one candidate permanently. That is why the loop is linear: the pointers only move inward, and together they visit at most `n` positions.

### Pattern 3: sliding window

A window is a contiguous range that grows at the right and shrinks at the left. Use it when the problem asks for the best subarray with a constraint.

```java
static int longestDistinct(String s) {
    Map<Character, Integer> last = new HashMap<>();
    int best = 0, left = 0;
    for (int right = 0; right < s.length(); right++) {
        char c = s.charAt(right);
        if (last.containsKey(c)) {
            left = Math.max(left, last.get(c) + 1);
        }
        last.put(c, right);
        best = Math.max(best, right - left + 1);
    }
    return best;
}
```

The `Math.max` is essential: a stale position from before the current window must not move `left` backward.

### Pattern 4: prefix sums

If you need many range sums, precompute cumulative totals once.

```java
int[] prefix = new int[values.length + 1];
for (int i = 0; i < values.length; i++) {
    prefix[i + 1] = prefix[i] + values[i];
}
int sum = prefix[high + 1] - prefix[low];
```

The range query is `O(1)` after an `O(n)` setup. The trade-off is memory: one extra array for fast queries.

### Pattern 5: dynamic programming

Dynamic programming is not a trick; it is a table of answers to subproblems. Start with a recurrence, then decide whether to fill the table bottom-up or memoize top-down.

```java
static int waysToClimb(int stairs) {
    if (stairs <= 2) return stairs;
    int[] ways = new int[stairs + 1];
    ways[1] = 1;
    ways[2] = 2;
    for (int i = 3; i <= stairs; i++) {
        ways[i] = ways[i - 1] + ways[i - 2];
    }
    return ways[stairs];
}
```

The recurrence is `ways[i] = ways[i-1] + ways[i-2]`. Once you can say that sentence, the code is almost forced.

### What to notice

- **Nested loops are not automatically quadratic.** If the inner loop always covers a shrinking range, the total can still be linear.
- **Sorting can unlock linear algorithms.** Two pointers, binary search, and many greedy methods need order.
- **Do not optimize the wrong layer.** A database query inside a loop will dominate any micro-optimization in the loop body.
- **Measure with realistic input.** A million-element benchmark says little about a ten-element form.

### Practice

1. Write a binary search that returns the insertion point when the target is absent. Test the empty array, the first position, and the last.
2. Find the longest subarray whose sum is at most `k` using a sliding window. What assumption makes the window valid?
3. Compare a recursive Fibonacci function with a memoized version on `n = 35`. Explain the difference in calls, not just seconds.

### Checkpoint

You are ready to move on when you can derive a sliding-window solution from the constraint, explain why binary search needs sorted input, and state the recurrence before writing a dynamic-programming table.

---

<a id="chapter-23-sorting"></a>
<a name="chapter-23-sorting"></a>
## Chapter 23 — Sorting

### The idea

Sorting is the canonical algorithm problem because it exposes every trade-off at once: time, extra memory, stability, and the shape of the input.

The comparison model gives a lower bound of `Ω(n log n)` for comparison sorts. You cannot beat that by comparing pairs in the worst case. You can beat it with counting or radix sort when the keys are small integers and you can use them as array indices.

| Algorithm | Average | Worst | Extra space | Stable |
| --- | --- | --- | --- | --- |
| Insertion | O(n²) | O(n²) | O(1) | yes |
| Merge | O(n log n) | O(n log n) | O(n) | yes |
| Quick | O(n log n) | O(n²) | O(log n) | no |
| Heap | O(n log n) | O(n log n) | O(1) | no |

**Stability** means equal keys keep their original relative order. That matters when you sort by one field after sorting by another: a stable sort preserves the earlier decision inside each group.

### Insertion sort: simple and adaptive

Insertion sort walks forward and inserts each item into the sorted prefix. It is quadratic in the worst case, but it is excellent on small or nearly sorted arrays. Production sorts often use it for small partitions.

```java
static void insertionSort(int[] a) {
    for (int i = 1; i < a.length; i++) {
        int key = a[i];
        int j = i - 1;
        while (j >= 0 && a[j] > key) {
            a[j + 1] = a[j];
            j--;
        }
        a[j + 1] = key;
    }
}
```

### Merge sort: divide, solve, combine

```java
static void mergeSort(int[] a) {
    if (a.length < 2) return;
    int mid = a.length / 2;
    int[] left = Arrays.copyOfRange(a, 0, mid);
    int[] right = Arrays.copyOfRange(a, mid, a.length);
    mergeSort(left);
    mergeSort(right);
    merge(a, left, right);
}

static void merge(int[] out, int[] left, int[] right) {
    int i = 0, j = 0, k = 0;
    while (i < left.length && j < right.length) {
        out[k++] = left[i] <= right[j] ? left[i++] : right[j++];
    }
    while (i < left.length) out[k++] = left[i++];
    while (j < right.length) out[k++] = right[j++];
}
```

The `<=` in the merge is what makes the sort stable. If you write `<`, equal elements from the right run can jump ahead of equal elements from the left.

### Quick sort: partition, recurse

Quick sort chooses a pivot, partitions smaller and larger elements around it, then sorts the partitions. Average `O(n log n)`, worst `O(n²)` when the pivot is consistently terrible. Randomizing the pivot makes the bad case unlikely without changing the algorithm.

```java
static void quickSort(int[] a, int low, int high) {
    if (low >= high) return;
    int p = partition(a, low, high);
    quickSort(a, low, p - 1);
    quickSort(a, p + 1, high);
}

static int partition(int[] a, int low, int high) {
    int pivot = a[high];
    int i = low;
    for (int j = low; j < high; j++) {
        if (a[j] <= pivot) {
            int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
            i++;
        }
    }
    int tmp = a[i]; a[i] = a[high]; a[high] = tmp;
    return i;
}
```

The partition invariant is the proof: everything before `i` is at most the pivot, everything from `i` to `j - 1` is greater, and the unexamined region is `j..high-1`.

### What Java actually uses

`Arrays.sort(int[])` uses a dual-pivot quick sort for primitives. `Arrays.sort(Object[])` and `List.sort` use TimSort, a stable adaptive merge sort that detects runs. The choice is deliberate:

- Primitives have no identity and no stability requirement; quicksort avoids object overhead.
- Objects may carry equal keys that must preserve order; TimSort is stable and fast on partially sorted data.

If you need a custom order, pass a `Comparator`. Keep it consistent with equals and free of side effects; a comparator that mutates state can corrupt the sort or throw the “comparison method violates its general contract” error.

### What to notice

- **Stability is a semantic requirement**, not a performance detail. Decide whether you need it before choosing an algorithm.
- **Quicksort’s worst case is real.** An already sorted array with a first-element pivot is the classic trap.
- **Sorting can be the bottleneck.** If you sort inside a loop, you may have chosen the wrong data structure.
- **Do not sort when a heap or a selection algorithm will do.** “Top 10 of a million” does not require a full order.

### Practice

1. Instrument insertion, merge, and quick sort with comparison and swap counters. Run all three on random, sorted, and reversed arrays of 1,000 elements.
2. Make merge sort sort strings by length, then alphabetically for equal lengths. Verify stability by checking equal-length names.
3. Add a random pivot to quicksort and explain why it reduces the chance of the quadratic case.

### Checkpoint

You are ready to move on when you can explain the lower bound for comparison sorting, the difference between stable and unstable sorts, and why Java chooses different algorithms for primitive and object arrays.

---

<a id="chapter-24-implementing-collections"></a>
<a name="chapter-24-implementing-collections"></a>
## Chapter 24 — Implementing Collections

### The idea

Using `ArrayList` is easy. Implementing one teaches you what the API is hiding: storage, growth, bounds, iteration, and the difference between a view and a copy.

A dynamic array has four jobs:

1. Keep a contiguous buffer of elements.
2. Track how many slots are actually used.
3. Grow the buffer when it is full.
4. Present a safe, typed interface to the outside world.

### A compact implementation

```java
public final class GrowableList<E> implements Iterable<E> {
    private Object[] data = new Object[4];
    private int size;

    public void add(E value) {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
        data[size++] = value;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        Objects.checkIndex(index, size);
        return (E) data[index];
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int cursor;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public E next() {
                if (cursor >= size) throw new NoSuchElementException();
                return GrowableList.this.get(cursor++);
            }
        };
    }
}
```

The cast in `get` is unavoidable because Java arrays are covariant and generics are erased. `Objects.checkIndex` gives a precise exception instead of a corrupt read.

### Growth is amortized

Doubling looks wasteful: at size 8 you allocate 16, at 16 you allocate 32. But each element is copied only a constant number of times across the whole sequence of additions, so the **amortized** cost of `add` at the end is `O(1)`. Growing by a fixed amount, such as `+10`, makes the total cost quadratic.

This is the kind of trade-off an interview question is really testing: not whether you can write `copyOf`, but whether you can explain why the growth factor matters.

### Iterators and fail-fast behavior

The iterator above reads `size` dynamically. If the list shrinks while you iterate, `hasNext()` adapts. If the list grows, the iterator may see new elements. That is not the behavior of `ArrayList`, which tracks a modification count and throws `ConcurrentModificationException` when the structure changes during iteration.

Fail-fast is a bug detector, not a concurrency guarantee. It catches many accidental mutations early. It does not make an unsafe collection safe across threads.

### Bounded ring buffer

A queue does not need to grow. A fixed-size ring buffer reuses slots:

```java
public final class Ring<E> {
    private final Object[] slots;
    private int head, tail, count;

    public Ring(int capacity) {
        slots = new Object[Math.max(1, capacity)];
    }

    public boolean offer(E value) {
        if (count == slots.length) return false;
        slots[tail] = value;
        tail = (tail + 1) % slots.length;
        count++;
        return true;
    }

    @SuppressWarnings("unchecked")
    public E poll() {
        if (count == 0) return null;
        E value = (E) slots[head];
        slots[head] = null;
        head = (head + 1) % slots.length;
        count--;
        return value;
    }
}
```

The modulo is what makes the array circular. Clearing the slot on removal is not cosmetic; it prevents the buffer from retaining a reference to an object that has logically left the queue.

### What to notice

- **Do not expose the internal array.** Return a copy, a read-only view, or a controlled accessor.
- **Generics are erased at runtime.** `new E[10]` is illegal; `new Object[10]` plus a checked cast is the usual workaround.
- **`equals` and `hashCode` are part of the collection contract.** A custom list that does not implement them cannot be compared meaningfully.
- **A collection is not thread-safe by default.** `Collections.synchronizedList` wraps every method in a lock; `CopyOnWriteArrayList` trades writes for cheap snapshots. Neither is automatically the right answer.

### Practice

1. Add `remove(int index)` to `GrowableList` and preserve order. What is the cost, and when would a swap-with-last removal be acceptable?
2. Add a modification counter and make the iterator throw `ConcurrentModificationException` after a structural change.
3. Implement `Ring` with a `size()` method and a `forEach` that visits elements in FIFO order without removing them.

### Checkpoint

You are ready to move on when you can explain amortized growth, why an iterator can detect structural modification, and why a ring buffer can be both fixed-size and correct.

---

<a id="chapter-25-binary-search-trees"></a>
<a name="chapter-25-binary-search-trees"></a>
## Chapter 25 — Binary Search Trees

### The idea

A binary search tree is a linked structure with one invariant:

> For every node, all keys in the left subtree are smaller, and all keys in the right subtree are larger.

That invariant turns a search into a sequence of decisions. At each node you compare once and discard an entire subtree. If the tree is balanced, the height is `O(log n)` and search, insert, and delete are `O(log n)`. If the tree degenerates into a chain, the height is `O(n)` and the structure has become a linked list with extra steps.

### Search and insert

```java
public final class BinarySearchTree {
    private static final class Node {
        int key;
        Node left, right;
        Node(int key) { this.key = key; }
    }

    private Node root;

    public boolean contains(int key) {
        Node current = root;
        while (current != null) {
            if (key == current.key) return true;
            current = key < current.key ? current.left : current.right;
        }
        return false;
    }

    public void insert(int key) {
        root = insert(root, key);
    }

    private Node insert(Node node, int key) {
        if (node == null) return new Node(key);
        if (key < node.key) node.left = insert(node.left, key);
        else if (key > node.key) node.right = insert(node.right, key);
        return node;
    }
}
```

The recursive insert returns the (possibly new) subtree root. That makes the parent assignment explicit and avoids a separate parent pointer. Duplicate keys are ignored here; a real dictionary might store a count or a value at each key.

### Deletion has three cases

1. **Leaf:** remove it.
2. **One child:** replace the node with its child.
3. **Two children:** replace the node’s key with its in-order successor (the smallest key in the right subtree), then delete the successor from the right subtree.

```java
private Node delete(Node node, int key) {
    if (node == null) return null;
    if (key < node.key) node.left = delete(node.left, key);
    else if (key > node.key) node.right = delete(node.right, key);
    else {
        if (node.left == null) return node.right;
        if (node.right == null) return node.left;
        node.key = min(node.right).key;
        node.right = delete(node.right, node.key);
    }
    return node;
}

private Node min(Node node) {
    while (node.left != null) node = node.left;
    return node;
}
```

The successor is the smallest key that is still larger than the deleted key. Replacing with the predecessor from the left subtree is equally valid.

### Traversals

An in-order traversal visits keys in sorted order. That is the simplest way to test the invariant:

```java
private void inorder(Node node, List<Integer> out) {
    if (node == null) return;
    inorder(node.left, out);
    out.add(node.key);
    inorder(node.right, out);
}
```

Pre-order (node, left, right) is useful for copying a tree. Post-order (left, right, node) is useful for deletion or computing subtree values.

### What to notice

- **A BST is not automatically balanced.** Inserting `1, 2, 3, 4, 5` produces a chain.
- **Recursion depth follows height.** A degenerate tree can overflow the stack on large inputs.
- **Deletion is the hard part.** Search and insert are easy to get right; two-child deletion is where invariants break.
- **Range queries are natural.** “All keys between 10 and 20” is a pruned traversal, not a full scan.

### Practice

1. Add `floor(key)` and `ceiling(key)`. Return the closest key that is less than or equal to, or greater than or equal to, the target.
2. Write `height()` and `isBalanced()` without storing extra fields.
3. Build a tree from sorted input and print its height. Then build one from shuffled input and compare.

### Checkpoint

You are ready to move on when you can state the BST invariant, handle all three deletion cases, and explain why a BST’s worst-case search is not logarithmic.

---

<a id="chapter-26-avl-trees"></a>
<a name="chapter-26-avl-trees"></a>
## Chapter 26 — AVL Trees

### The idea

An AVL tree is a binary search tree with one extra rule:

> For every node, the heights of its left and right subtrees differ by at most one.

The **balance factor** is `height(left) - height(right)`. It must be `-1`, `0`, or `1`. When an insertion or deletion pushes a node to `±2`, a rotation restores the rule.

The promise is strong: an AVL tree with `n` nodes has height at most about `1.44 log₂(n + 2)`. Search, insert, and delete are all `O(log n)` in the worst case, not just on average.

### Rotations are local restructuring

A rotation changes the shape of a subtree without changing its in-order sequence. There are four cases:

| Case | Shape | Fix |
| --- | --- | --- |
| LL | left-left | single right rotation |
| RR | right-right | single left rotation |
| LR | left-right | left rotation on child, then right on node |
| RL | right-left | right rotation on child, then left on node |

```java
private Node rotateRight(Node y) {
    Node x = y.left;
    Node middle = x.right;
    x.right = y;
    y.left = middle;
    updateHeight(y);
    updateHeight(x);
    return x;
}

private Node rotateLeft(Node x) {
    Node y = x.right;
    Node middle = y.left;
    y.left = x;
    x.right = middle;
    updateHeight(x);
    updateHeight(y);
    return y;
}
```

The order of height updates matters: update the old root before the new root. If you update the new root first, it still sees the old child heights.

### Rebalancing after insertion

Insert as in a BST, then walk back up. At each node:

1. Update its height.
2. Compute its balance factor.
3. If it is outside `[-1, 1]`, identify the case and rotate.
4. Return the new subtree root.

```java
private Node rebalance(Node node) {
    updateHeight(node);
    int balance = height(node.left) - height(node.right);

    if (balance > 1 && height(node.left.left) >= height(node.left.right)) {
        return rotateRight(node);
    }
    if (balance > 1 && height(node.left.left) < height(node.left.right)) {
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }
    if (balance < -1 && height(node.right.right) >= height(node.right.left)) {
        return rotateLeft(node);
    }
    if (balance < -1 && height(node.right.right) < height(node.right.left)) {
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }
    return node;
}
```

Deletion uses the same rebalancing, but the imbalance can appear at every ancestor, so the walk must continue all the way to the root. Insertion needs at most one rotation to restore the whole tree, although the height updates still propagate upward.

### What to notice

- **Store height, not balance factor**, unless you are confident about the update rules. Height is easier to verify.
- **Double rotations look like two single rotations for a reason.** The first rotation turns the LR case into an LL case.
- **AVL is stricter than red-black.** It gives faster lookups and more rotations on writes. Red-black trees are often preferred for general-purpose maps; AVL trees are excellent for read-heavy indexes.
- **Recursion is natural but not mandatory.** An iterative implementation needs parent pointers or an explicit stack.

### Practice

1. Insert `30, 20, 10` and draw each rotation. Then insert `10, 20, 30` and compare.
2. Write a validator that checks both the BST ordering and the AVL balance condition. Run it after every insertion in a random test.
3. Delete a node with two children and trace the rebalancing walk to the root. How many rotations occur?

### Checkpoint

You are ready to move on when you can identify the four rotation cases from a diagram, explain why an AVL tree is always `O(log n)`, and state why deletion may require more rebalancing than insertion.

---

<a id="chapter-27-hashing"></a>
<a name="chapter-27-hashing"></a>
## Chapter 27 — Hashing

### The idea

A hash table turns a key into an array index. If the function is good and the table is not too full, the index is found in constant time and the table delivers the average `O(1)` promise that makes maps and sets practical.

The pipeline is:

1. Compute an integer hash from the key.
2. Mix the bits to spread patterns.
3. Map the result into `[0, capacity)`.
4. Resolve collisions.

A **collision** happens when two different keys map to the same index. Collisions are inevitable: there are more possible strings than array slots. The design question is how to handle them without losing the performance promise.

### Separate chaining

Each slot holds a small bucket — usually a linked list or a tree:

```java
public final class HashTable<K, V> {
    private static final class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;
        Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private Entry<K, V>[] buckets = new Entry[16];
    private int size;

    public V get(K key) {
        int index = indexFor(key);
        for (Entry<K, V> e = buckets[index]; e != null; e = e.next) {
            if (e.key.equals(key)) return e.value;
        }
        return null;
    }

    public void put(K key, V value) {
        int index = indexFor(key);
        for (Entry<K, V> e = buckets[index]; e != null; e = e.next) {
            if (e.key.equals(key)) {
                e.value = value;
                return;
            }
        }
        buckets[index] = new Entry<>(key, value, buckets[index]);
        size++;
        if (size > buckets.length * 0.75) resize();
    }

    private int indexFor(K key) {
        return (key.hashCode() & 0x7fffffff) % buckets.length;
    }

    private void resize() {
        Entry<K, V>[] old = buckets;
        buckets = new Entry[old.length * 2];
        size = 0;
        for (Entry<K, V> head : old) {
            for (Entry<K, V> e = head; e != null; e = e.next) {
                put(e.key, e.value);
            }
        }
    }
}
```

The `& 0x7fffffff` clears the sign bit so the modulo is non-negative. In a production table, the capacity is a power of two and the index is computed with a mask after bit mixing; `HashMap` does both.

### Load factor and resizing

The **load factor** is `size / capacity`. Chaining degrades as the average bucket length grows. Resizing doubles the table and rehashes every key, which is `O(n)` — but only occasionally. The amortized cost of `put` remains `O(1)`.

A load factor around `0.75` is a common compromise between memory and collision rate. `HashMap` uses `0.75` and converts a long bucket into a red-black tree when it grows past a threshold, which protects against pathological collisions.

### Open addressing

Instead of a chain, open addressing stores every entry directly in the table and probes for the next free slot:

- **Linear probing:** `index + 1`, `index + 2`, … Simple, cache-friendly, prone to clustering.
- **Quadratic probing:** `index + 1²`, `index + 2²`, … Reduces primary clustering.
- **Double hashing:** a second hash chooses the step.

Deletion in open addressing cannot simply empty a slot; that would break the probe sequence for later keys. It uses a **tombstone** marker instead.

### What to notice

- **A mutable key breaks the table.** If a field used in `hashCode` changes after insertion, the key is in the wrong bucket.
- **`hashCode` must be stable and consistent with `equals`.** Two equal keys must hash the same.
- **A bad hash turns `O(1)` into `O(n)`.** A constant hash is valid but useless.
- **Hash flooding is a security issue.** An attacker who can predict collisions can force long chains. Randomized hashing and treeified buckets mitigate it.

### Practice

1. Insert 10,000 random integers into the table above and print the longest bucket. Then insert multiples of 16 and compare.
2. Implement `remove` with tombstones for an open-addressed table. Explain why setting the slot to `null` is wrong.
3. Write a `hashCode` for a `Point` record by hand, then compare it with the generated one.

### Checkpoint

You are ready to move on when you can explain the hash pipeline, why resizing is amortized, the difference between chaining and open addressing, and how a mutable key can disappear.

---

<a id="chapter-29-weighted-graphs"></a>
<a name="chapter-29-weighted-graphs"></a>
## Chapter 29 — Weighted Graphs

### The idea

An unweighted graph asks “can I get there?” A weighted graph asks “what does it cost?” The edges carry numbers: distance, time, price, risk, or latency. The shortest path is the path with the smallest total weight, not necessarily the fewest edges.

The standard representation is an adjacency list of weighted edges:

```java
record Edge(int to, int weight) {}

List<List<Edge>> graph = new ArrayList<>();
for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
graph.get(0).add(new Edge(1, 4));
graph.get(1).add(new Edge(0, 4)); // undirected
```

For dense graphs, an adjacency matrix uses `O(n²)` memory and gives `O(1)` edge lookup. For sparse graphs, adjacency lists use `O(V + E)` memory and are almost always the better default.

### Dijkstra’s algorithm

Dijkstra grows a set of nodes whose shortest distance is final. At each step it removes the unsettled node with the smallest known distance, then relaxes its outgoing edges.

```java
static int[] dijkstra(List<List<Edge>> graph, int source) {
    int n = graph.size();
    int[] dist = new int[n];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[source] = 0;

    PriorityQueue<int[]> pq = new PriorityQueue<>(
        Comparator.comparingInt(row -> row[1])
    );
    pq.add(new int[]{source, 0});

    while (!pq.isEmpty()) {
        int[] row = pq.poll();
        int node = row[0], d = row[1];
        if (d != dist[node]) continue; // stale entry

        for (Edge edge : graph.get(node)) {
            int next = edge.to();
            int candidate = d + edge.weight();
            if (candidate < dist[next]) {
                dist[next] = candidate;
                pq.add(new int[]{next, candidate});
            }
        }
    }
    return dist;
}
```

The `d != dist[node]` check is not an optimization; it is correctness for a lazy priority queue that may contain several outdated entries for the same node. Without it, the algorithm can process an old, worse distance.

### Why non-negative weights matter

Dijkstra assumes that once a node is removed from the queue, its distance cannot improve. A negative edge can violate that assumption: a longer path to a node might later become cheaper after a negative step.

For negative edges, use Bellman-Ford, which relaxes every edge `V - 1` times and can detect a negative cycle on the next pass. For all-pairs shortest paths, Floyd-Warshall is the classic `O(V³)` choice.

### Shortest path vs minimum spanning tree

These are different problems and the distinction is worth remembering:

- **Shortest path** minimizes the cost from one source to one destination. Dijkstra.
- **Minimum spanning tree** connects every node with the smallest total edge weight and no cycles. Kruskal or Prim.

A shortest-path tree is not generally an MST, and an MST does not generally contain the shortest path between two nodes.

### What to notice

- **Directed vs undirected changes the graph.** Add the reverse edge only when the relationship is symmetric.
- **The priority queue may hold stale entries.** That is cheaper than decrease-key in Java’s `PriorityQueue`, but you must skip them.
- **Overflow is real.** `Integer.MAX_VALUE + weight` wraps negative. Use a guard or `long` distances.
- **Disconnected nodes keep `MAX_VALUE`.** Do not print that as a real distance.

### Practice

1. Add path reconstruction with a `parent[]` array. Print the actual route, not just the total.
2. Run Dijkstra on a graph with one zero-weight edge. Does it still work? Why?
3. Build a small road network, compute shortest paths, then compute an MST. Explain why the two answers differ.

### Checkpoint

You are ready to move on when you can explain the relaxation step, why Dijkstra fails on negative edges, what the stale-entry check does, and how shortest paths differ from an MST.

---

The next volume moves from data structures to systems: threads, sockets, databases, web applications, and the testing discipline that keeps them honest.
