"""Authored studio content: data structures, graphs, and enterprise Java."""

CHAPTERS = [
    {
        "n": 24,
        "title": "Implementing Lists, Stacks, Queues, and Priority Queues",
        "part": "Part 4 · Algorithms and structures",
        "tagline": "Using a collection teaches the interface; implementing one teaches the cost hidden behind it.",
        "minutes": 55,
        "tags": ["array list", "linked list", "stack", "queue", "grow", "shift", "implementation"],
        "objectives": [
            "Implement a dynamic array with append, get, set, and remove.",
            "Explain when growth and shifting dominate the cost of a list operation.",
            "Build stack and queue behavior on top of a list without changing the public contract.",
        ],
        "subgoals": [
            {
                "title": "Capacity is not size",
                "idea": "The backing array may be larger than the number of stored elements. <code>size</code> counts real elements; <code>capacity</code> counts available slots.",
            },
            {
                "title": "Growth copies",
                "idea": "When the array is full, allocate a larger one, copy the elements, and replace the reference. A doubling strategy makes append amortized O(1).",
            },
            {
                "title": "Insert and remove shift",
                "idea": "Adding or removing in the middle moves every later element by one slot. That is O(n) even though reading by index is O(1).",
            },
            {
                "title": "Nodes trade memory for flexibility",
                "idea": "A linked list stores a next reference per element. Adding at the head is O(1), but indexed access must walk the chain.",
            },
            {
                "title": "Adapters preserve semantics",
                "idea": "A stack can wrap a list with push and pop mapped to addLast and removeLast. The adapter defines which operations clients may use.",
            },
        ],
        "example": {
            "title": "GrowList: append until the array doubles",
            "intro": "Watch the capacity jump. The four appends are not all the same cost: the fifth triggers a copy.",
            "code": [
                "String[] data = new String[4];",
                "int size = 0;",
                "void add(String value) {",
                "    if (size == data.length) {",
                "        data = Arrays.copyOf(data, data.length * 2);",
                "    }",
                "    data[size++] = value;",
                "}",
                "add(\"A\"); add(\"B\"); add(\"C\"); add(\"D\"); add(\"E\");",
            ],
            "steps": [
                {
                    "label": "Initial capacity",
                    "note": "Four slots exist and size is zero. The array is not the list; it is storage the list manages.",
                },
                {
                    "label": "Fill without copying",
                    "note": "A through D occupy indexes 0–3. Each append is O(1) and size becomes capacity.",
                },
                {
                    "label": "The array is full",
                    "note": "The next add sees <code>size == data.length</code>. Writing past the end would throw an exception.",
                },
                {
                    "label": "Double and copy",
                    "note": "A new eight-slot array is allocated and the four elements are copied. This single add is O(n).",
                },
                {
                    "label": "Append E",
                    "note": "The new slot at index 4 receives E. Future appends are cheap again until the array fills.",
                },
            ],
            "predict": {
                "prompt": "Why is doubling preferred over growing by one slot on every append?",
                "answer": "Growing by one copies on every append, so n appends cost O(n²). Doubling copies at 1, 2, 4, 8… giving O(n) total copying for n appends.",
            },
        },
        "engine": "structure",
        "spec": {
            "hint": "Step through append, growth, update, and removal. The cell count is capacity; the highlighted cells are live elements.",
            "mode": "list",
            "frames": [
                {
                    "label": "capacity 4, size 0",
                    "cells": ["_", "_", "_", "_"],
                    "highlight": [],
                    "note": "Four slots, none live. Underscore marks unused capacity.",
                },
                {
                    "label": "append A, B, C",
                    "cells": ["A", "B", "C", "_"],
                    "highlight": [0, 1, 2],
                    "note": "Three appends at indexes 0, 1, 2. No copy has happened.",
                },
                {
                    "label": "append D fills the array",
                    "cells": ["A", "B", "C", "D"],
                    "highlight": [0, 1, 2, 3],
                    "note": "size now equals capacity. The next append must grow.",
                },
                {
                    "label": "append E doubles capacity",
                    "cells": ["A", "B", "C", "D", "E", "_", "_", "_"],
                    "highlight": [4],
                    "note": "A new eight-slot array was allocated and four elements copied; E is the only new live element.",
                },
                {
                    "label": "remove index 1",
                    "cells": ["A", "C", "D", "E", "_", "_", "_", "_"],
                    "highlight": [1, 2, 3],
                    "note": "C, D, and E each shift left. The reference at index 4 is cleared so the removed value can be collected.",
                },
            ],
        },
        "parsons": {
            "prompt": "Rebuild the growth check and append.",
            "lines": [
                "void add(String value) {",
                "    if (size == data.length) {",
                "        data = Arrays.copyOf(data, data.length * 2);",
                "    }",
                "    data[size] = value;",
                "    size++;",
                "}",
            ],
            "solution": [0, 1, 2, 3, 4, 5, 6],
            "seed": 24,
            "why": "Capacity is checked before writing, and size advances only after the value is stored.",
        },
        "quiz": [
            {
                "q": "What is the cost of <code>get(index)</code> in an array-backed list?",
                "options": ["O(1)", "O(log n)", "O(n)", "O(n²)"],
                "a": 0,
                "why": "The element address is computed directly from the base and index.",
            },
            {
                "q": "Why does removing from the middle of an array list cost O(n)?",
                "options": [
                    "The array must be sorted",
                    "Every later element shifts one slot left",
                    "The JVM copies the whole array to disk",
                    "The size field cannot be changed",
                ],
                "a": 1,
                "why": "Contiguous storage requires closing the gap, which touches each following element.",
            },
            {
                "q": "What makes doubling produce amortized O(1) append?",
                "options": [
                    "Each append copies every element",
                    "Copy work happens at exponentially spaced sizes",
                    "Doubling removes the size field",
                    "The array is never full",
                ],
                "a": 1,
                "why": "Total copying for n appends is bounded by a geometric series proportional to n.",
            },
        ],
        "artifact": {
            "name": "SimpleList.java",
            "description": "A minimal array-backed list with add and get. Complete remove and prove that size shrinks.",
            "code": """import java.util.Arrays;

public class SimpleList<E> {
    private Object[] data = new Object[4];
    private int size = 0;

    public void add(E value) {
        if (size == data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
        data[size++] = value;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index " + index + ", size " + size);
        }
        return (E) data[index];
    }

    public E remove(int index) {
        // TODO: validate, save the value, shift later elements left, clear the old last slot, and decrement size.
        return null;
    }

    public int size() {
        return size;
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_24",
        },
        "reflection": "Explain why a linked list is a poor default for a list used mostly by index, then name one workload where it is the better choice.",
        "studio": {
            "title": "Capacity versus size",
            "intro": "The viewer shows unused capacity as underscores. Watch a copy happen only when every slot is live.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/collections-algorithms.md#chapter-24-implementing-collections",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_24",
        },
    },
    {
        "n": 25,
        "title": "Binary Search Trees",
        "part": "Part 4 · Algorithms and structures",
        "tagline": "A binary search tree keeps the ordering rule local: everything left is smaller, everything right is larger.",
        "minutes": 55,
        "tags": ["bst", "tree", "insert", "search", "inorder", "height", "delete"],
        "objectives": [
            "Insert and search in a binary search tree while preserving the ordering rule.",
            "Trace an inorder traversal and explain why it sorts the keys.",
            "Describe how deletion handles a node with two children.",
        ],
        "subgoals": [
            {
                "title": "The invariant is local",
                "idea": "For every node, all keys in the left subtree are smaller and all keys in the right subtree are larger. Checking one node is enough if the invariant held before.",
            },
            {
                "title": "Search follows comparisons",
                "idea": "Compare at the root, then descend left or right. Each step removes one subtree, so search is O(height).",
            },
            {
                "title": "Insert at a leaf",
                "idea": "Follow the same path as search until a null child appears, then attach a new leaf there. The shape depends on insertion order.",
            },
            {
                "title": "Inorder is sorted",
                "idea": "Left subtree, node, right subtree. The invariant places every smaller key before the node and every larger key after it.",
            },
            {
                "title": "Deletion has three cases",
                "idea": "A leaf is removed directly. A node with one child is replaced by that child. A node with two children is replaced by its inorder successor or predecessor.",
            },
        ],
        "example": {
            "title": "IntTree: insert, search, print in order",
            "intro": "The tree shape is a side effect of insertion order. The ordering rule, not the shape, is the invariant.",
            "code": [
                "Node insert(Node root, int key) {",
                "    if (root == null) return new Node(key);",
                "    if (key < root.key) root.left = insert(root.left, key);",
                "    else if (key > root.key) root.right = insert(root.right, key);",
                "    return root;",
                "}",
                "",
                "boolean contains(Node root, int key) {",
                "    if (root == null) return false;",
                "    if (key == root.key) return true;",
                "    return key < root.key",
                "        ? contains(root.left, key)",
                "        : contains(root.right, key);",
                "}",
            ],
            "steps": [
                {
                    "label": "Insert 50",
                    "note": "The empty tree becomes a single root. Its left and right children are null.",
                },
                {
                    "label": "Insert 30",
                    "note": "30 is smaller than 50, so it becomes the left child. The root does not move.",
                },
                {
                    "label": "Insert 70",
                    "note": "70 is larger than 50, so it becomes the right child.",
                },
                {
                    "label": "Insert 20 and 40",
                    "note": "Both compare against 50 then 30. 20 goes left of 30; 40 goes right of 30.",
                },
                {
                    "label": "Search 40",
                    "note": "40 < 50 so go left; 40 > 30 so go right; equal, found. The path length is the number of comparisons.",
                },
            ],
            "predict": {
                "prompt": "What does an inorder traversal of the final tree print?",
                "answer": "20, 30, 40, 50, 70. Inorder visits the left subtree, then the node, then the right subtree, which is ascending order for a valid BST.",
            },
        },
        "engine": "structure",
        "spec": {
            "hint": "Step through insertion and search. The highlighted node is the current comparison.",
            "mode": "tree",
            "frames": [
                {
                    "label": "insert 50",
                    "nodes": [{"id": "50", "label": "50", "x": 0.5, "y": 0.08}],
                    "highlight": ["50"],
                    "note": "The first key becomes the root.",
                },
                {
                    "label": "insert 30",
                    "nodes": [
                        {"id": "50", "label": "50", "x": 0.5, "y": 0.08},
                        {"id": "30", "label": "30", "x": 0.28, "y": 0.42, "parent": "50"},
                    ],
                    "highlight": ["30"],
                    "note": "30 is smaller than 50, so it is attached as the left child.",
                },
                {
                    "label": "insert 70",
                    "nodes": [
                        {"id": "50", "label": "50", "x": 0.5, "y": 0.08},
                        {"id": "30", "label": "30", "x": 0.28, "y": 0.42, "parent": "50"},
                        {"id": "70", "label": "70", "x": 0.72, "y": 0.42, "parent": "50"},
                    ],
                    "highlight": ["70"],
                    "note": "70 is larger than 50, so it is attached as the right child.",
                },
                {
                    "label": "insert 20 and 40",
                    "nodes": [
                        {"id": "50", "label": "50", "x": 0.5, "y": 0.08},
                        {"id": "30", "label": "30", "x": 0.28, "y": 0.42, "parent": "50"},
                        {"id": "70", "label": "70", "x": 0.72, "y": 0.42, "parent": "50"},
                        {"id": "20", "label": "20", "x": 0.14, "y": 0.76, "parent": "30"},
                        {"id": "40", "label": "40", "x": 0.4, "y": 0.76, "parent": "30"},
                    ],
                    "highlight": ["20", "40"],
                    "note": "Both keys compare with 50 and then 30 before finding an empty child slot.",
                },
                {
                    "label": "search 40",
                    "nodes": [
                        {"id": "50", "label": "50", "x": 0.5, "y": 0.08},
                        {"id": "30", "label": "30", "x": 0.28, "y": 0.42, "parent": "50"},
                        {"id": "70", "label": "70", "x": 0.72, "y": 0.42, "parent": "50"},
                        {"id": "20", "label": "20", "x": 0.14, "y": 0.76, "parent": "30"},
                        {"id": "40", "label": "40", "x": 0.4, "y": 0.76, "parent": "30"},
                    ],
                    "highlight": ["40"],
                    "note": "The search path is 50 → 30 → 40. No other node is examined.",
                },
            ],
        },
        "parsons": {
            "prompt": "Rebuild recursive BST insertion.",
            "lines": [
                "Node insert(Node root, int key) {",
                "    if (root == null) return new Node(key);",
                "    if (key < root.key) root.left = insert(root.left, key);",
                "    else if (key > root.key) root.right = insert(root.right, key);",
                "    return root;",
                "}",
            ],
            "solution": [0, 1, 2, 3, 4, 5],
            "seed": 25,
            "why": "The null check creates a leaf; the comparisons choose a subtree; the unchanged root is returned to the caller.",
        },
        "quiz": [
            {
                "q": "Where is a newly inserted key placed?",
                "options": ["Always at the root", "At the first empty child slot on its search path", "Randomly", "As the leftmost leaf always"],
                "a": 1,
                "why": "Insertion follows comparisons until a null child is found, then attaches the new node there.",
            },
            {
                "q": "Which traversal prints BST keys in ascending order?",
                "options": ["Preorder", "Inorder", "Postorder", "Level order"],
                "a": 1,
                "why": "Inorder visits left subtree, node, then right subtree, matching the ordering rule.",
            },
            {
                "q": "A node with two children is deleted by replacing it with what?",
                "options": [
                    "Its left child only",
                    "Its inorder successor or predecessor",
                    "The root",
                    "A null reference",
                ],
                "a": 1,
                "why": "The successor is the smallest key in the right subtree and preserves the ordering rule.",
            },
        ],
        "artifact": {
            "name": "IntTree.java",
            "description": "A BST with insert, contains, and inorder. Add height and count leaves without changing the node type.",
            "code": """public class IntTree {
    private static final class Node {
        final int key;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
        }
    }

    private Node root;

    public void insert(int key) {
        root = insert(root, key);
    }

    private Node insert(Node node, int key) {
        if (node == null) return new Node(key);
        if (key < node.key) node.left = insert(node.left, key);
        else if (key > node.key) node.right = insert(node.right, key);
        return node;
    }

    public boolean contains(int key) {
        Node current = root;
        while (current != null) {
            if (key == current.key) return true;
            current = key < current.key ? current.left : current.right;
        }
        return false;
    }

    public int height() {
        // TODO: return the number of edges on the longest root-to-leaf path.
        return 0;
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_25",
        },
        "reflection": "Insert the keys 1 through 7 in ascending order. Describe the resulting shape and explain why search degenerates to O(n).",
        "studio": {
            "title": "Ordering rule, not shape",
            "intro": "The same five keys can form very different trees depending on insertion order. Follow the comparisons, not the drawing.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/collections-algorithms.md#chapter-25-binary-search-trees",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_25",
        },
    },
    {
        "n": 26,
        "title": "AVL Trees",
        "part": "Part 4 · Algorithms and structures",
        "tagline": "An AVL tree pays a little on every update to keep height logarithmic on every search.",
        "minutes": 55,
        "tags": ["avl", "rotation", "balance factor", "height", "self-balancing", "tree"],
        "objectives": [
            "Compute the balance factor of a node from subtree heights.",
            "Trace a single rotation and a double rotation.",
            "Explain why AVL height is O(log n) even in the worst case.",
        ],
        "subgoals": [
            {
                "title": "Height is measured in edges",
                "idea": "A leaf has height 0; a null child has height -1. A node's height is one plus the larger child height.",
            },
            {
                "title": "Balance factor is left minus right",
                "idea": "If the factor is -1, 0, or 1, the node is balanced. A factor of 2 or -2 marks the lowest unbalanced node after an insertion.",
            },
            {
                "title": "A single rotation fixes an outside case",
                "idea": "Left-left or right-right imbalance is repaired by rotating the child up and the old root down.",
            },
            {
                "title": "A double rotation fixes an inside case",
                "idea": "Left-right or right-left requires two rotations: first straighten the child, then rotate the root.",
            },
            {
                "title": "Balance is maintained on the way back",
                "idea": "Insertion updates heights as the recursion unwinds. The first unbalanced node is repaired, and the tree above it remains valid.",
            },
        ],
        "example": {
            "title": "RightRotate: 30 leans left, 20 rises",
            "intro": "The rotation changes three references. It does not change the inorder sequence, which is the point.",
            "code": [
                "Node rotateRight(Node y) {",
                "    Node x = y.left;",
                "    Node t2 = x.right;",
                "    x.right = y;",
                "    y.left = t2;",
                "    updateHeight(y);",
                "    updateHeight(x);",
                "    return x;",
                "}",
            ],
            "steps": [
                {
                    "label": "Unbalanced root",
                    "note": "30 has left child 20 and no right child. The balance factor is 1 - (-1) = 2.",
                },
                {
                    "label": "Name the child",
                    "note": "x is 20, the new subtree root. t2 is x.right, currently null.",
                },
                {
                    "label": "Rewire",
                    "note": "20's right becomes 30, and 30's left becomes the old right of 20. Order is preserved: everything in t2 is between 20 and 30.",
                },
                {
                    "label": "Refresh heights",
                    "note": "Update the old root first because it is now lower. Then update the new root.",
                },
                {
                    "label": "Return the new root",
                    "note": "The caller must attach the returned node. Forgetting the return leaves the old root in place.",
                },
            ],
            "predict": {
                "prompt": "If 20 had a right child 25, where would 25 end up after the right rotation?",
                "answer": "It becomes the left child of 30. The subtree order 20 < 25 < 30 is preserved.",
            },
        },
        "engine": "structure",
        "spec": {
            "hint": "Insert 10 into the left-leaning tree and follow the rotation. The inorder order never changes.",
            "mode": "tree",
            "frames": [
                {
                    "label": "balanced before insertion",
                    "nodes": [
                        {"id": "30", "label": "30", "x": 0.5, "y": 0.08},
                        {"id": "20", "label": "20", "x": 0.28, "y": 0.42, "parent": "30"},
                        {"id": "40", "label": "40", "x": 0.72, "y": 0.42, "parent": "30"},
                    ],
                    "highlight": [],
                    "note": "Heights: 20 and 40 are 0; 30 is 1. Balance factors are all within range.",
                },
                {
                    "label": "insert 10",
                    "nodes": [
                        {"id": "30", "label": "30", "x": 0.5, "y": 0.08},
                        {"id": "20", "label": "20", "x": 0.28, "y": 0.42, "parent": "30"},
                        {"id": "40", "label": "40", "x": 0.72, "y": 0.42, "parent": "30"},
                        {"id": "10", "label": "10", "x": 0.12, "y": 0.76, "parent": "20"},
                    ],
                    "highlight": ["30", "20", "10"],
                    "note": "30 now has left height 1 and right height 0, so its balance factor is 2.",
                },
                {
                    "label": "rotate right at 30",
                    "nodes": [
                        {"id": "20", "label": "20", "x": 0.5, "y": 0.08},
                        {"id": "10", "label": "10", "x": 0.25, "y": 0.46, "parent": "20"},
                        {"id": "30", "label": "30", "x": 0.72, "y": 0.46, "parent": "20"},
                        {"id": "40", "label": "40", "x": 0.86, "y": 0.78, "parent": "30"},
                    ],
                    "highlight": ["20"],
                    "note": "20 is the new subtree root. Heights are 0, 0, 1, so every balance factor is valid.",
                },
                {
                    "label": "inorder check",
                    "nodes": [
                        {"id": "20", "label": "20", "x": 0.5, "y": 0.08},
                        {"id": "10", "label": "10", "x": 0.25, "y": 0.46, "parent": "20"},
                        {"id": "30", "label": "30", "x": 0.72, "y": 0.46, "parent": "20"},
                        {"id": "40", "label": "40", "x": 0.86, "y": 0.78, "parent": "30"},
                    ],
                    "highlight": ["10", "20", "30", "40"],
                    "note": "Inorder remains 10, 20, 30, 40. Rotation changes shape, not sorted order.",
                },
            ],
        },
        "parsons": {
            "prompt": "Rebuild a right rotation, including the two height updates.",
            "lines": [
                "Node rotateRight(Node y) {",
                "    Node x = y.left;",
                "    Node t2 = x.right;",
                "    x.right = y;",
                "    y.left = t2;",
                "    updateHeight(y);",
                "    updateHeight(x);",
                "    return x;",
                "}",
            ],
            "solution": [0, 1, 2, 3, 4, 5, 6, 7, 8],
            "seed": 26,
            "why": "Save the child and its right subtree before rewiring; update the lower node first; return the new subtree root.",
        },
        "quiz": [
            {
                "q": "What is the balance factor of a node with left height 2 and right height 0?",
                "options": ["-2", "0", "1", "2"],
                "a": 3,
                "why": "Balance factor is left height minus right height: 2 - 0 = 2.",
            },
            {
                "q": "Which case requires a double rotation?",
                "options": ["Left-left", "Right-right", "Left-right", "A leaf insertion"],
                "a": 2,
                "why": "An inside case must first be straightened into an outside case, then rotated again.",
            },
            {
                "q": "What invariant does a rotation preserve?",
                "options": [
                    "The exact tree shape",
                    "The inorder key sequence",
                    "The root key",
                    "The number of leaves",
                ],
                "a": 1,
                "why": "A rotation changes parent-child links but keeps all keys in the same sorted order.",
            },
        ],
        "artifact": {
            "name": "AvlIntTree.java",
            "description": "An AVL insertion skeleton with height and balance helpers. Add the right-heavy rotation case.",
            "code": """public class AvlIntTree {
    private static final class Node {
        final int key;
        Node left;
        Node right;
        int height = 0;

        Node(int key) {
            this.key = key;
        }
    }

    private Node root;

    private int height(Node node) {
        return node == null ? -1 : node.height;
    }

    private void updateHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int balance(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node middle = x.right;
        x.right = y;
        y.left = middle;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private Node rebalance(Node node) {
        updateHeight(node);
        int factor = balance(node);
        // TODO: handle left-heavy and right-heavy cases, including double rotations.
        return node;
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_26",
        },
        "reflection": "Compare an AVL tree with an unbalanced BST for a workload of many searches and few inserts. Which would you choose, and what does the choice cost on insert?",
        "studio": {
            "title": "Rotation observer",
            "intro": "The tree viewer redraws after each insertion. Check the inorder order before and after the rotation.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/collections-algorithms.md#chapter-26-avl-trees",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_26",
        },
    },
    {
        "n": 27,
        "title": "Hashing",
        "part": "Part 4 · Algorithms and structures",
        "tagline": "Hashing turns a key into a bucket index; collisions are normal, and the collision policy decides the real cost.",
        "minutes": 50,
        "tags": ["hash", "bucket", "collision", "chaining", "load factor", "rehash"],
        "objectives": [
            "Compute a bucket index from a hash code and table length.",
            "Trace separate chaining through a collision.",
            "Explain how load factor and resizing affect lookup cost.",
        ],
        "subgoals": [
            {
                "title": "Hash, then compress",
                "idea": "A hash code is an int. The table index is usually <code>Math.floorMod(hash, capacity)</code>, which handles negative hashes correctly.",
            },
            {
                "title": "Collisions are expected",
                "idea": "Different keys can map to the same bucket. A hash table is correct only because it has a policy for that case.",
            },
            {
                "title": "Chaining stores a list per bucket",
                "idea": "Each bucket holds the entries that hashed there. Lookup scans only that bucket, not the whole table.",
            },
            {
                "title": "Load factor measures crowding",
                "idea": "Entries divided by buckets. As it rises, chains lengthen. A resize allocates a larger table and rehashes every entry.",
            },
            {
                "title": "equals must match hashCode",
                "idea": "If two objects are equal, they must have the same hash code. Breaking that contract makes an entry impossible to find.",
            },
        ],
        "example": {
            "title": "SevenBins: three keys, one chain",
            "intro": "The modulus is small on purpose. The collision is the lesson, not an accident.",
            "code": [
                "int capacity = 7;",
                "int a = Math.floorMod(Integer.hashCode(12), capacity);",
                "int b = Math.floorMod(Integer.hashCode(19), capacity);",
                "int c = Math.floorMod(Integer.hashCode(26), capacity);",
                "// insert all three, then search for 19",
            ],
            "steps": [
                {
                    "label": "Empty table",
                    "note": "Seven buckets, each with an empty chain. Capacity is a power-of-two alternative in many libraries, but modulus works for any size.",
                },
                {
                    "label": "Insert 12",
                    "note": "12 mod 7 is 5, so 12 is stored in bucket 5.",
                },
                {
                    "label": "Insert 19",
                    "note": "19 mod 7 is also 5. The bucket now holds a chain; this is a collision.",
                },
                {
                    "label": "Insert 26",
                    "note": "26 mod 7 is 5 again. The chain has three entries, and lookup in bucket 5 becomes a short linear scan.",
                },
                {
                    "label": "Search 19",
                    "note": "Compute the bucket, then compare keys in the chain. A good hash spreads keys; equal keys always share a bucket.",
                },
            ],
            "predict": {
                "prompt": "If the capacity doubled to 14, where would 12, 19, and 26 go?",
                "answer": "12 → 12, 19 → 5, and 26 → 12. The collision between 12 and 26 remains, while 19 separates from them.",
            },
        },
        "engine": "structure",
        "spec": {
            "hint": "Watch bucket 5 grow. The chain length is the lookup cost for a miss and for the last element.",
            "mode": "hash",
            "frames": [
                {
                    "label": "seven empty buckets",
                    "buckets": [
                        {"index": 0, "items": []},
                        {"index": 1, "items": []},
                        {"index": 2, "items": []},
                        {"index": 3, "items": []},
                        {"index": 4, "items": []},
                        {"index": 5, "items": []},
                        {"index": 6, "items": []},
                    ],
                    "highlight": [],
                    "note": "Capacity 7, size 0, load factor 0.",
                },
                {
                    "label": "insert 12",
                    "buckets": [
                        {"index": 0, "items": []},
                        {"index": 1, "items": []},
                        {"index": 2, "items": []},
                        {"index": 3, "items": []},
                        {"index": 4, "items": []},
                        {"index": 5, "items": ["12"]},
                        {"index": 6, "items": []},
                    ],
                    "highlight": [5],
                    "note": "12 mod 7 = 5. The bucket was empty, so the chain has length 1.",
                },
                {
                    "label": "insert 19",
                    "buckets": [
                        {"index": 0, "items": []},
                        {"index": 1, "items": []},
                        {"index": 2, "items": []},
                        {"index": 3, "items": []},
                        {"index": 4, "items": []},
                        {"index": 5, "items": ["12", "19"]},
                        {"index": 6, "items": []},
                    ],
                    "highlight": [5],
                    "note": "19 mod 7 = 5. A collision appends to the chain.",
                },
                {
                    "label": "insert 26",
                    "buckets": [
                        {"index": 0, "items": []},
                        {"index": 1, "items": []},
                        {"index": 2, "items": []},
                        {"index": 3, "items": []},
                        {"index": 4, "items": []},
                        {"index": 5, "items": ["12", "19", "26"]},
                        {"index": 6, "items": []},
                    ],
                    "highlight": [5],
                    "note": "Three entries now share bucket 5. Load factor is 3/7, about 0.43.",
                },
                {
                    "label": "search 19",
                    "buckets": [
                        {"index": 0, "items": []},
                        {"index": 1, "items": []},
                        {"index": 2, "items": []},
                        {"index": 3, "items": []},
                        {"index": 4, "items": []},
                        {"index": 5, "items": ["12", "19", "26"]},
                        {"index": 6, "items": []},
                    ],
                    "highlight": [5],
                    "note": "One bucket is selected, then the chain is scanned. The worst case is the chain length.",
                },
            ],
        },
        "parsons": {
            "prompt": "Rebuild a chained put operation without deleting an existing equal key.",
            "lines": [
                "int index = Math.floorMod(key.hashCode(), buckets.length);",
                "List<Entry> chain = buckets[index];",
                "for (Entry entry : chain) {",
                "    if (entry.key.equals(key)) {",
                "        entry.value = value;",
                "        return;",
                "    }",
                "}",
                "chain.add(new Entry(key, value));",
                "size++;",
            ],
            "solution": [0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
            "seed": 27,
            "why": "Compute the bucket first, update an equal key in place, and append only when no match exists.",
        },
        "quiz": [
            {
                "q": "What is a collision?",
                "options": [
                    "Two equal keys in different tables",
                    "Different keys mapping to the same bucket",
                    "A table with zero entries",
                    "A hash code larger than the capacity",
                ],
                "a": 1,
                "why": "A collision occurs when distinct keys compress to the same index.",
            },
            {
                "q": "Why does a resize require rehashing every entry?",
                "options": [
                    "The keys change value",
                    "The bucket index depends on the table capacity",
                    "The entries must be sorted",
                    "The hash codes are deleted",
                ],
                "a": 1,
                "why": "The modulus changes with capacity, so the same hash code can map to a different bucket.",
            },
            {
                "q": "What must be true when two objects are equal?",
                "options": [
                    "They have the same memory address",
                    "They have the same hash code",
                    "They are the same class instance",
                    "They are both null",
                ],
                "a": 1,
                "why": "Equal objects must land in the same bucket, or lookup cannot find the stored entry.",
            },
        ],
        "artifact": {
            "name": "ChainMap.java",
            "description": "A small string-keyed map with separate chaining. Add get and a load-factor check that triggers a resize.",
            "code": """import java.util.ArrayList;
import java.util.List;

public class ChainMap {
    private static final class Entry {
        final String key;
        int value;

        Entry(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final List<Entry>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public ChainMap(int capacity) {
        buckets = new List[capacity];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    public void put(String key, int value) {
        int index = Math.floorMod(key.hashCode(), buckets.length);
        for (Entry entry : buckets[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        buckets[index].add(new Entry(key, value));
        size++;
    }

    public Integer get(String key) {
        // TODO: find the bucket and return the matching value, or null.
        return null;
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_27",
        },
        "reflection": "Explain why a hash table cannot answer a range query such as all keys between 10 and 20 without scanning, while a tree can.",
        "studio": {
            "title": "Bucket chain",
            "intro": "The hash viewer shows the bucket array and each chain. Insert three keys that collide and watch the scan length grow.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/collections-algorithms.md#chapter-27-hashing",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_27",
        },
    },
    {
        "n": 28,
        "title": "Graphs and Applications",
        "part": "Part 4 · Algorithms and structures",
        "tagline": "A graph is a set of relationships; traversal is the discipline of visiting each relationship exactly once.",
        "minutes": 55,
        "tags": ["graph", "bfs", "dfs", "adjacency", "traversal", "connectivity"],
        "objectives": [
            "Represent an undirected graph with adjacency lists and an edge set.",
            "Trace breadth-first and depth-first search from a start vertex.",
            "Explain why a visited set is required and what it prevents.",
        ],
        "subgoals": [
            {
                "title": "Vertices and edges",
                "idea": "A graph has vertices and edges. An undirected edge appears in both adjacency lists; a directed edge appears only in the source list.",
            },
            {
                "title": "Adjacency lists scale with edges",
                "idea": "Each vertex stores its neighbors. Total storage is O(V + E), which is efficient for sparse graphs.",
            },
            {
                "title": "BFS uses a queue",
                "idea": "Visit the start, enqueue its unvisited neighbors, then process the queue in order. BFS discovers vertices by increasing distance in unweighted graphs.",
            },
            {
                "title": "DFS uses a stack or recursion",
                "idea": "Follow one path as far as possible, then backtrack. DFS is natural for connectivity, cycles, and topological ordering.",
            },
            {
                "title": "Visited prevents cycles",
                "idea": "Without a visited set, a cycle sends the traversal around forever. Mark a vertex when it is discovered, not when it is removed.",
            },
        ],
        "example": {
            "title": "BreadthFirst: six vertices, two levels",
            "intro": "The queue order is the discovery order. A vertex enters the queue once, when it is first seen.",
            "code": [
                "void bfs(Map<String, List<String>> graph, String start) {",
                "    Set<String> seen = new HashSet<>();",
                "    Queue<String> queue = new ArrayDeque<>();",
                "    seen.add(start);",
                "    queue.add(start);",
                "    while (!queue.isEmpty()) {",
                "        String current = queue.remove();",
                "        System.out.print(current + \" \");",
                "        for (String next : graph.get(current)) {",
                "            if (seen.add(next)) queue.add(next);",
                "        }",
                "    }",
                "}",
            ],
            "steps": [
                {
                    "label": "Start at A",
                    "note": "A is marked seen before it is enqueued. This prevents a later edge from adding it twice.",
                },
                {
                    "label": "Process A",
                    "note": "A's neighbors B and D are unvisited, so both enter the queue and are marked.",
                },
                {
                    "label": "Process B",
                    "note": "B's neighbors include A, which is already seen. C and E are new, so they join the queue.",
                },
                {
                    "label": "Process D",
                    "note": "D's neighbors are already seen or already queued. The queue order keeps the traversal level by level.",
                },
                {
                    "label": "Process C, E, F",
                    "note": "Each vertex is printed once. The visited set, not the graph, guarantees termination.",
                },
            ],
            "predict": {
                "prompt": "If <code>seen.add(start)</code> were removed, what could happen in a graph with a cycle?",
                "answer": "The start could be enqueued again from a neighbor, and the traversal could loop or at least duplicate work. Cycles break naive traversal.",
            },
        },
        "engine": "graph",
        "spec": {
            "hint": "Run BFS and DFS from A. The frontier and visited colors show the different exploration orders.",
            "nodes": [
                {"id": "A", "x": 0.12, "y": 0.5},
                {"id": "B", "x": 0.36, "y": 0.2},
                {"id": "C", "x": 0.62, "y": 0.16},
                {"id": "D", "x": 0.36, "y": 0.8},
                {"id": "E", "x": 0.64, "y": 0.56},
                {"id": "F", "x": 0.86, "y": 0.4},
            ],
            "edges": [
                {"a": "A", "b": "B"},
                {"a": "A", "b": "D"},
                {"a": "B", "b": "C"},
                {"a": "B", "b": "E"},
                {"a": "D", "b": "E"},
                {"a": "C", "b": "F"},
                {"a": "E", "b": "F"},
            ],
            "algorithm": "bfs",
            "start": "A",
            "directed": False,
        },
        "parsons": {
            "prompt": "Rebuild breadth-first search with a visited set and a queue.",
            "lines": [
                "Set<String> seen = new HashSet<>();",
                "Queue<String> queue = new ArrayDeque<>();",
                "seen.add(start);",
                "queue.add(start);",
                "while (!queue.isEmpty()) {",
                "    String current = queue.remove();",
                "    for (String next : graph.get(current)) {",
                "        if (seen.add(next)) queue.add(next);",
                "    }",
                "}",
            ],
            "solution": [0, 1, 2, 3, 4, 5, 6, 7, 8, 9],
            "seed": 28,
            "why": "The start is marked before enqueueing; each neighbor is tested with seen.add, which both checks and records.",
        },
        "quiz": [
            {
                "q": "Which data structure gives breadth-first order?",
                "options": ["Stack", "Queue", "Priority queue", "Set"],
                "a": 1,
                "why": "FIFO order processes all vertices at one distance before moving farther out.",
            },
            {
                "q": "When should a vertex be marked visited?",
                "options": [
                    "When it is removed from the queue",
                    "When it is first discovered",
                    "After all its neighbors are processed",
                    "Never; the graph is finite",
                ],
                "a": 1,
                "why": "Marking at discovery prevents duplicate enqueues through different edges.",
            },
            {
                "q": "What is the storage cost of adjacency lists?",
                "options": ["O(V²) always", "O(V + E)", "O(E²)", "O(log V)"],
                "a": 1,
                "why": "Each vertex stores a list and each edge contributes one or two entries.",
            },
        ],
        "artifact": {
            "name": "GraphWalk.java",
            "description": "An adjacency-list graph with BFS order and a connected-components count. Add cycle detection without recursion.",
            "code": """import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class GraphWalk {
    private final Map<String, List<String>> graph = new HashMap<>();

    public void addEdge(String a, String b) {
        graph.computeIfAbsent(a, key -> new ArrayList<>()).add(b);
        graph.computeIfAbsent(b, key -> new ArrayList<>()).add(a);
    }

    public List<String> bfs(String start) {
        List<String> order = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();
        seen.add(start);
        queue.add(start);
        while (!queue.isEmpty()) {
            String current = queue.remove();
            order.add(current);
            for (String next : graph.getOrDefault(current, List.of())) {
                if (seen.add(next)) queue.add(next);
            }
        }
        return order;
    }

    public int components() {
        // TODO: count connected components using bfs and a global visited set.
        return 0;
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_28",
        },
        "reflection": "Describe a real network where BFS would answer the question you care about, and a different question where DFS would be the better fit.",
        "studio": {
            "title": "Traversal frontier",
            "intro": "The same graph supports BFS and DFS. Compare the frontier shape; the difference is the container, not the graph.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/io-generics-graphs.md#chapter-28-graphs",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_28",
        },
    },
    {
        "n": 29,
        "title": "Weighted Graphs and Applications",
        "part": "Part 4 · Algorithms and structures",
        "tagline": "Weights turn a graph from a map of connections into a map of costs, and the cheapest path is rarely the shortest in edges.",
        "minutes": 55,
        "tags": ["weighted graph", "dijkstra", "prim", "shortest path", "minimum spanning tree", "relaxation"],
        "objectives": [
            "Represent weighted edges and distinguish path cost from hop count.",
            "Trace Dijkstra's relaxation step and explain why negative weights break it.",
            "Compare a shortest-path tree with a minimum spanning tree.",
        ],
        "subgoals": [
            {
                "title": "Cost is additive",
                "idea": "A path's cost is the sum of its edge weights. A path with fewer edges can be more expensive than a longer, lighter route.",
            },
            {
                "title": "Dijkstra grows a settled set",
                "idea": "Repeatedly remove the unsettled vertex with the smallest known distance. Its distance is final because every edge weight is non-negative.",
            },
            {
                "title": "Relaxation improves a guess",
                "idea": "If <code>dist[u] + w(u,v) &lt; dist[v]</code>, replace the guess for v and record u as its predecessor.",
            },
            {
                "title": "Negative edges break the proof",
                "idea": "A later negative edge could lower a distance after a vertex was settled. Use Bellman-Ford when negative weights are possible.",
            },
            {
                "title": "Shortest path is not spanning tree",
                "idea": "Dijkstra minimizes cost from one source to every vertex. Prim minimizes the total weight of a tree connecting all vertices.",
            },
        ],
        "example": {
            "title": "RouteCost: settle A, relax neighbors",
            "intro": "The table of tentative distances is the algorithm. The drawing only makes the relaxation visible.",
            "code": [
                "Map<String, Integer> dist = new HashMap<>();",
                "dist.put(start, 0);",
                "while (unsettled not empty) {",
                "    String u = vertex with smallest dist;",
                "    settle(u);",
                "    for (Edge e : edgesFrom(u)) {",
                "        int candidate = dist.get(u) + e.weight;",
                "        if (candidate < dist.getOrDefault(e.to, INF)) {",
                "            dist.put(e.to, candidate);",
                "            parent.put(e.to, u);",
                "        }",
                "    }",
                "}",
            ],
            "steps": [
                {
                    "label": "Initialize",
                    "note": "The source distance is 0; every other distance is infinity. No vertex is settled.",
                },
                {
                    "label": "Settle A",
                    "note": "A has the smallest tentative distance, 0. Relax its edges: B becomes 2 and D becomes 6.",
                },
                {
                    "label": "Settle B",
                    "note": "B is next at 2. The edge to C gives 2 + 3 = 5, improving C from infinity. The edge to D gives 7, which is worse than 6.",
                },
                {
                    "label": "Settle C and D",
                    "note": "C settles at 5, D at 6. The edge from C to E proposes 11; the edge from D to E proposes 10, so E is updated.",
                },
                {
                    "label": "Read the tree",
                    "note": "Following parent links from E gives E ← D ← A. The route cost is 10 even though the hop count is 2.",
                },
            ],
            "predict": {
                "prompt": "A direct edge A→E has weight 50. Why is it not chosen while A→D→E costs 10?",
                "answer": "Dijkstra minimizes total cost, not hop count. Relaxation keeps the cheaper 10 and ignores the direct 50.",
            },
        },
        "engine": "graph",
        "spec": {
            "hint": "Run Dijkstra from A. Watch tentative distances fall as cheaper routes are discovered.",
            "nodes": [
                {"id": "A", "x": 0.1, "y": 0.5},
                {"id": "B", "x": 0.34, "y": 0.18},
                {"id": "C", "x": 0.6, "y": 0.18},
                {"id": "D", "x": 0.34, "y": 0.82},
                {"id": "E", "x": 0.66, "y": 0.58},
                {"id": "F", "x": 0.88, "y": 0.35},
            ],
            "edges": [
                {"a": "A", "b": "B", "w": 2},
                {"a": "A", "b": "D", "w": 6},
                {"a": "B", "b": "C", "w": 3},
                {"a": "B", "b": "D", "w": 5},
                {"a": "C", "b": "E", "w": 6},
                {"a": "D", "b": "E", "w": 4},
                {"a": "E", "b": "F", "w": 2},
            ],
            "algorithm": "dijkstra",
            "start": "A",
            "directed": False,
        },
        "parsons": {
            "prompt": "Rebuild the relaxation step.",
            "lines": [
                "for (Edge e : edgesFrom(u)) {",
                "    int candidate = dist.get(u) + e.weight;",
                "    if (candidate < dist.getOrDefault(e.to, Integer.MAX_VALUE)) {",
                "        dist.put(e.to, candidate);",
                "        parent.put(e.to, u);",
                "    }",
                "}",
            ],
            "solution": [0, 1, 2, 3, 4, 5, 6],
            "seed": 29,
            "why": "Only a strictly cheaper candidate replaces the distance, and the predecessor records the chosen route.",
        },
        "quiz": [
            {
                "q": "What does Dijkstra's algorithm settle at each step?",
                "options": [
                    "The most recently discovered vertex",
                    "The unsettled vertex with the smallest tentative distance",
                    "The vertex with the most edges",
                    "A random frontier vertex",
                ],
                "a": 1,
                "why": "The minimum tentative distance is final when all edge weights are non-negative.",
            },
            {
                "q": "Why does a negative edge break Dijkstra's correctness?",
                "options": [
                    "Negative weights cannot be stored",
                    "A settled vertex could later receive a cheaper distance",
                    "The graph becomes directed",
                    "The queue overflows",
                ],
                "a": 1,
                "why": "The proof depends on no later path being cheaper than the settled minimum.",
            },
            {
                "q": "What does Prim's algorithm minimize?",
                "options": [
                    "Distance from one source",
                    "Total weight of a spanning tree",
                    "Number of vertices",
                    "The longest path",
                ],
                "a": 1,
                "why": "Prim grows a tree by repeatedly adding the cheapest edge that connects a new vertex.",
            },
        ],
        "artifact": {
            "name": "WeightedRoute.java",
            "description": "A weighted adjacency map with Dijkstra distances and predecessor reconstruction. Print the path to a target.",
            "code": """import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class WeightedRoute {
    private record Edge(String to, int weight) {}

    private final Map<String, List<Edge>> graph = new HashMap<>();

    public void addEdge(String a, String b, int weight) {
        graph.computeIfAbsent(a, key -> new ArrayList<>()).add(new Edge(b, weight));
        graph.computeIfAbsent(b, key -> new ArrayList<>()).add(new Edge(a, weight));
    }

    public Map<String, Integer> distances(String start) {
        Map<String, Integer> dist = new HashMap<>();
        PriorityQueue<Map.Entry<String, Integer>> frontier =
                new PriorityQueue<>(Map.Entry.comparingByValue());
        dist.put(start, 0);
        frontier.add(Map.entry(start, 0));
        while (!frontier.isEmpty()) {
            Map.Entry<String, Integer> current = frontier.remove();
            if (current.getValue() > dist.getOrDefault(current.getKey(), Integer.MAX_VALUE)) continue;
            for (Edge edge : graph.getOrDefault(current.getKey(), List.of())) {
                int candidate = current.getValue() + edge.weight();
                if (candidate < dist.getOrDefault(edge.to(), Integer.MAX_VALUE)) {
                    dist.put(edge.to(), candidate);
                    frontier.add(Map.entry(edge.to(), candidate));
                }
            }
        }
        return dist;
    }

    public List<String> path(String start, String target) {
        // TODO: keep predecessors and rebuild the route from target back to start.
        return List.of();
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_29",
        },
        "reflection": "Give an example where the minimum spanning tree is the right answer and a shortest-path tree is not. Explain the difference in one sentence.",
        "studio": {
            "title": "Cost, not hops",
            "intro": "Dijkstra on a weighted graph. Compare the chosen route with the route that uses the fewest edges.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/collections-algorithms.md#chapter-29-weighted-graphs",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_29",
        },
    },
    {
        "n": 30,
        "title": "Multithreading and Parallel Programming",
        "part": "Part 5 · Enterprise Java",
        "tagline": "Threads make progress concurrent; synchronization makes shared state predictable. You need both, and they solve different problems.",
        "minutes": 55,
        "tags": ["thread", "race condition", "synchronized", "atomic", "executor", "concurrency"],
        "objectives": [
            "Identify a race condition in interleaved reads and writes.",
            "Use synchronization or an atomic type to protect shared state.",
            "Explain why starting a thread and joining it are different events.",
        ],
        "subgoals": [
            {
                "title": "Interleaving is not sequential",
                "idea": "Two threads can read the same old value before either writes. The final state can be as if one update never happened.",
            },
            {
                "title": "count++ is three steps",
                "idea": "Read, add, write. A thread can be paused between any two. The increment is not atomic just because it is one line.",
            },
            {
                "title": "Synchronization defines a critical section",
                "idea": "A lock ensures one thread at a time executes the protected block. The lock must be the same object for every access.",
            },
            {
                "title": "Atomics compose a single operation",
                "idea": "<code>AtomicInteger.incrementAndGet</code> is one atomic operation. It helps for counters, not for invariants spanning several fields.",
            },
            {
                "title": "Join establishes happens-before",
                "idea": "<code>join</code> waits for a thread to finish and makes its writes visible to the joining thread. Without it, the main thread may read before the work is done.",
            },
        ],
        "example": {
            "title": "LostUpdate: two threads, one increment each",
            "intro": "The final value is 1, not 2. Both threads read the same zero before either wrote.",
            "code": [
                "int count = 0;",
                "Thread a = new Thread(() -> count++);",
                "Thread b = new Thread(() -> count++);",
                "a.start();",
                "b.start();",
                "a.join(); b.join();",
            ],
            "steps": [
                {
                    "label": "Initial state",
                    "note": "count is 0. No thread has started.",
                },
                {
                    "label": "Start both",
                    "note": "The scheduler may run either thread first. Start order does not guarantee execution order.",
                },
                {
                    "label": "A reads 0",
                    "note": "A has read the old value but has not written yet.",
                },
                {
                    "label": "B reads 0",
                    "note": "B also reads 0 before A writes. The race window is open.",
                },
                {
                    "label": "Both write 1",
                    "note": "A writes 1, then B writes 1. One increment is lost. Join waits for both, but it cannot repair the race.",
                },
            ],
            "predict": {
                "prompt": "Does making the field <code>volatile</code> fix the lost update?",
                "answer": "No. Volatile ensures visibility of reads and writes, but count++ is still three separate steps. Use a lock or AtomicInteger.",
            },
        },
        "engine": "state",
        "spec": {
            "hint": "Step through the interleaving. The final count is a property of the schedule, not the number of threads.",
            "code": [
                "int count = 0;",
                "Thread a = new Thread(() -> count++);",
                "Thread b = new Thread(() -> count++);",
                "a.start();",
                "b.start();",
                "a.join(); b.join();",
            ],
            "vars": ["count", "threadA", "threadB"],
            "steps": [
                {
                    "line": 0,
                    "vars": {"count": "0", "threadA": "new", "threadB": "new"},
                    "note": "One shared variable, two threads that have not started.",
                },
                {
                    "line": 3,
                    "vars": {"count": "0", "threadA": "running", "threadB": "new"},
                    "note": "A starts. The scheduler may pause it at any instruction.",
                },
                {
                    "line": 4,
                    "vars": {"count": "0", "threadA": "paused after read", "threadB": "running"},
                    "note": "B starts and also reads count as 0.",
                },
                {
                    "line": 3,
                    "vars": {"count": "1", "threadA": "wrote 1", "threadB": "paused after read"},
                    "note": "A writes 1. B still holds the stale 0 in its working state.",
                },
                {
                    "line": 4,
                    "vars": {"count": "1", "threadA": "done", "threadB": "wrote 1"},
                    "note": "B writes 1. The result is 1 even though two increments occurred.",
                },
                {
                    "line": 5,
                    "vars": {"count": "1", "threadA": "done", "threadB": "done"},
                    "note": "Both joins return. Waiting for completion does not undo the interleaving.",
                },
            ],
        },
        "parsons": {
            "prompt": "Rebuild a safe counter update using synchronization.",
            "lines": [
                "final Object lock = new Object();",
                "int count = 0;",
                "Runnable task = () -> {",
                "    synchronized (lock) {",
                "        count++;",
                "    }",
                "};",
                "Thread a = new Thread(task);",
                "Thread b = new Thread(task);",
                "a.start();",
                "b.start();",
                "a.join();",
                "b.join();",
            ],
            "solution": [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12],
            "seed": 30,
            "why": "Both threads synchronize on the same lock object, so the read-add-write sequence cannot interleave.",
        },
        "quiz": [
            {
                "q": "Why can two increments produce a final value of 1?",
                "options": [
                    "The second increment is skipped by the JVM",
                    "Both threads read the same old value before writing",
                    "Join resets the counter",
                    "Threads share no memory",
                ],
                "a": 1,
                "why": "The read-add-write sequence interleaves and one write overwrites the other.",
            },
            {
                "q": "What does <code>synchronized</code> guarantee?",
                "options": [
                    "The block runs faster",
                    "Only one thread at a time executes a block guarded by the same lock",
                    "The thread never pauses",
                    "All threads finish in start order",
                ],
                "a": 1,
                "why": "Mutual exclusion is the core guarantee; visibility follows from the lock's happens-before edge.",
            },
            {
                "q": "What does <code>join()</code> do?",
                "options": [
                    "Starts a thread",
                    "Waits for a thread to finish",
                    "Cancels a thread",
                    "Copies shared state",
                ],
                "a": 1,
                "why": "Join blocks the caller until the target thread terminates.",
            },
        ],
        "artifact": {
            "name": "SafeCounter.java",
            "description": "Compare a synchronized counter with an AtomicInteger under two threads. Assert that both reach 2000 increments.",
            "code": """import java.util.concurrent.atomic.AtomicInteger;

public class SafeCounter {
    private int locked;
    private final AtomicInteger atomic = new AtomicInteger();

    public synchronized void incrementLocked() {
        locked++;
    }

    public void incrementAtomic() {
        atomic.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        SafeCounter counter = new SafeCounter();
        Runnable work = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.incrementLocked();
                counter.incrementAtomic();
            }
        };
        Thread a = new Thread(work);
        Thread b = new Thread(work);
        a.start();
        b.start();
        a.join();
        b.join();
        System.out.println(counter.locked + " " + counter.atomic.get());
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_30",
        },
        "reflection": "Name one invariant in a banking transfer that a single atomic counter cannot protect. Explain why a lock around the whole transfer is the safer unit.",
        "studio": {
            "title": "Interleaving inspector",
            "intro": "The state machine exposes both threads. Pause between read and write to reproduce the lost update deliberately.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/enterprise.md#chapter-30-multithreading-and-parallelism",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_30",
        },
    },
    {
        "n": 31,
        "title": "Networking",
        "part": "Part 5 · Enterprise Java",
        "tagline": "A socket is a byte stream between two processes; a protocol is the agreement about what those bytes mean.",
        "minutes": 50,
        "tags": ["socket", "server", "client", "tcp", "stream", "protocol", "echo"],
        "objectives": [
            "Distinguish a listening server socket from an accepted connection.",
            "Trace a request and response across a TCP connection.",
            "Explain why message framing is required on a byte stream.",
        ],
        "subgoals": [
            {
                "title": "Bind, listen, accept",
                "idea": "A <code>ServerSocket</code> binds a port and listens. <code>accept</code> returns a new socket for one client; the listening socket stays open.",
            },
            {
                "title": "Connect performs the handshake",
                "idea": "A client <code>Socket</code> constructor connects to a host and port. When it returns, the transport is ready for bytes.",
            },
            {
                "title": "Streams carry bytes, not messages",
                "idea": "TCP delivers an ordered byte stream. It does not preserve write boundaries, so the receiver must know how to find the end of a message.",
            },
            {
                "title": "Close both directions",
                "idea": "Closing a socket releases the file descriptor. Try-with-resources closes the stream chain and the socket together.",
            },
            {
                "title": "Blocking calls need threads or timeouts",
                "idea": "<code>accept</code> and <code>read</code> block until data or closure arrives. A server that handles one client at a time stalls everyone else.",
            },
        ],
        "example": {
            "title": "EchoPair: one line across the wire",
            "intro": "The server accepts, reads one line, and writes it back. The client sends and then reads the reply.",
            "code": [
                "ServerSocket server = new ServerSocket(5000);",
                "Socket client = new Socket(\"localhost\", 5000);",
                "Socket accepted = server.accept();",
                "PrintWriter out = new PrintWriter(client.getOutputStream(), true);",
                "out.println(\"ping\");",
                "String reply = new BufferedReader(",
                "    new InputStreamReader(accepted.getInputStream())).readLine();",
            ],
            "steps": [
                {
                    "label": "Listen",
                    "note": "The server socket owns port 5000. No client is connected yet.",
                },
                {
                    "label": "Connect",
                    "note": "The client socket completes the TCP handshake. The server's accept call can now return.",
                },
                {
                    "label": "Accept",
                    "note": "The accepted socket represents this one connection. The listening socket remains available for others.",
                },
                {
                    "label": "Write",
                    "note": "<code>println</code> appends a newline. The newline is the framing that lets readLine know the message ended.",
                },
                {
                    "label": "Read",
                    "note": "The server reads until the newline. Without the newline or a length prefix, the read would wait for more bytes.",
                },
            ],
            "predict": {
                "prompt": "What happens if the client writes \"ping\" without a newline and the server calls readLine?",
                "answer": "readLine blocks waiting for a line terminator or end of stream. The bytes arrived, but the message boundary did not.",
            },
        },
        "engine": "state",
        "spec": {
            "hint": "Step through the connection setup. The wire is empty until the writer flushes a framed line.",
            "code": [
                "ServerSocket server = new ServerSocket(5000);",
                "Socket client = new Socket(\"localhost\", 5000);",
                "Socket accepted = server.accept();",
                "PrintWriter out = new PrintWriter(client.getOutputStream(), true);",
                "out.println(\"ping\");",
                "String reply = new BufferedReader(new InputStreamReader(accepted.getInputStream())).readLine();",
            ],
            "vars": ["server", "client", "wire"],
            "steps": [
                {
                    "line": 0,
                    "vars": {"server": "listening:5000", "client": "none", "wire": "\"\""},
                    "note": "The listening socket is bound. It cannot carry application data itself.",
                },
                {
                    "line": 1,
                    "vars": {"server": "listening:5000", "client": "connected", "wire": "\"\""},
                    "note": "The handshake completes. The connection is queued for accept.",
                },
                {
                    "line": 2,
                    "vars": {"server": "listening:5000", "client": "connected", "wire": "\"\""},
                    "note": "accept returns a dedicated socket for this client.",
                },
                {
                    "line": 4,
                    "vars": {"server": "listening:5000", "client": "connected", "wire": "\"ping\\n\""},
                    "note": "println writes the bytes and the newline framing.",
                },
                {
                    "line": 5,
                    "vars": {"server": "listening:5000", "client": "connected", "wire": "\"\""},
                    "note": "readLine consumes through the newline and returns ping. The wire is drained.",
                },
            ],
        },
        "parsons": {
            "prompt": "Rebuild the server side of a one-line echo.",
            "lines": [
                "try (ServerSocket server = new ServerSocket(5000);",
                "     Socket accepted = server.accept();",
                "     BufferedReader in = new BufferedReader(",
                "         new InputStreamReader(accepted.getInputStream()));",
                "     PrintWriter out = new PrintWriter(accepted.getOutputStream(), true)) {",
                "    String line = in.readLine();",
                "    out.println(line);",
                "}",
            ],
            "solution": [0, 1, 2, 3, 4, 5, 6, 7],
            "seed": 31,
            "why": "The server listens, accepts one connection, opens both stream directions, then reads a framed line and echoes it.",
        },
        "quiz": [
            {
                "q": "What does <code>ServerSocket.accept()</code> return?",
                "options": [
                    "The listening socket",
                    "A new socket for one client connection",
                    "A byte array",
                    "A thread pool",
                ],
                "a": 1,
                "why": "The listening socket remains open; accept returns a connected socket for the accepted client.",
            },
            {
                "q": "Why is a newline needed for <code>readLine</code>?",
                "options": [
                    "TCP requires newlines",
                    "It marks a message boundary on the byte stream",
                    "It encrypts the payload",
                    "It closes the socket",
                ],
                "a": 1,
                "why": "The stream has no message boundaries, so the protocol must supply one.",
            },
            {
                "q": "What is the risk of handling clients one at a time in the accept loop?",
                "options": [
                    "The port changes",
                    "A slow client blocks all later clients",
                    "TCP reorders the bytes",
                    "The server cannot close sockets",
                ],
                "a": 1,
                "why": "A blocking handler prevents the loop from accepting the next connection.",
            },
        ],
        "artifact": {
            "name": "EchoClient.java",
            "description": "A client that sends one framed line and prints the reply. Add a read timeout so a silent server cannot hang it forever.",
            "code": """import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {
    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 5000);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()))) {
            // TODO: set a read timeout, send one framed line, and print the reply.
            out.println("ping");
            System.out.println(in.readLine());
        }
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_31",
        },
        "reflection": "Design a two-field message protocol for a chat client. Explain how the receiver knows where one message ends and the next begins.",
        "studio": {
            "title": "Connection lifecycle",
            "intro": "The state viewer separates listening, connected, accepted, and drained. Bytes move only after both sides agree on framing.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/enterprise.md#chapter-31-networking",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_31",
        },
    },
    {
        "n": 32,
        "title": "SQL and JDBC",
        "part": "Part 5 · Enterprise Java",
        "tagline": "SQL describes the result you want; JDBC is the boundary where Java types and database types meet.",
        "minutes": 55,
        "tags": ["sql", "jdbc", "prepared statement", "result set", "transaction", "injection"],
        "objectives": [
            "Write a parameterized query and read its result set.",
            "Explain why string concatenation creates SQL injection risk.",
            "Manage connection, statement, and result-set resources in the right order.",
        ],
        "subgoals": [
            {
                "title": "SQL is declarative",
                "idea": "You state the rows and columns you want. The database chooses an execution plan. Ordering is not guaranteed unless you write ORDER BY.",
            },
            {
                "title": "Prepared statements separate code from data",
                "idea": "A placeholder receives a typed value. The driver sends it as data, so a quote in the value cannot change the query structure.",
            },
            {
                "title": "A result set is a cursor",
                "idea": "<code>next()</code> advances one row and returns false at the end. Read columns by label or index while the cursor is on a row.",
            },
            {
                "title": "Close in reverse order",
                "idea": "Result set, statement, connection. Try-with-resources closes them in reverse declaration order and preserves the original exception.",
            },
            {
                "title": "Transactions define a unit",
                "idea": "Set auto-commit false, perform several statements, then commit or roll back. A half-applied transfer is worse than a failed one.",
            },
        ],
        "example": {
            "title": "HonorRoll: filter, order, limit",
            "intro": "The SQL clauses are applied in a defined logical order. The result is a table, even when it has one column.",
            "code": [
                "String sql = \"SELECT id, name, score FROM students \"",
                "    + \"WHERE score >= ? ORDER BY score DESC LIMIT ?\";",
                "try (PreparedStatement query = connection.prepareStatement(sql)) {",
                "    query.setInt(1, 80);",
                "    query.setInt(2, 3);",
                "    try (ResultSet rows = query.executeQuery()) {",
                "        while (rows.next()) {",
                "            System.out.println(rows.getInt(\"id\") + \" \" + rows.getString(\"name\"));",
                "        }",
                "    }",
                "}",
            ],
            "steps": [
                {
                    "label": "Parameter placeholders",
                    "note": "The question marks are positions, not text substitutions. The driver binds typed values to them.",
                },
                {
                    "label": "Bind the threshold",
                    "note": "The first parameter is an int, so the database compares numbers, not strings.",
                },
                {
                    "label": "Bind the limit",
                    "note": "The second parameter limits rows after filtering and sorting.",
                },
                {
                    "label": "Execute and iterate",
                    "note": "The cursor starts before the first row. <code>next</code> moves to the first match and returns true.",
                },
                {
                    "label": "Read by label",
                    "note": "Labels are clearer than indexes and survive a column reorder. The driver converts SQL types to Java types.",
                },
            ],
            "predict": {
                "prompt": "What changes if the query is built as <code>\"... WHERE name = '\" + input + \"'\"</code> and input is <code>' OR '1'='1</code>?",
                "answer": "The condition becomes always true and returns every row. The input changed the query structure; a prepared statement would treat it as one literal name.",
            },
        },
        "engine": "data",
        "spec": {
            "hint": "Toggle the pipeline. Filter, sort, project, and limit mirror WHERE, ORDER BY, SELECT, and LIMIT.",
            "columns": ["id", "name", "score", "team"],
            "rows": [
                [1, "Ada", 94, "alpha"],
                [2, "Grace", 88, "beta"],
                [3, "Linus", 76, "alpha"],
                [4, "Margaret", 91, "gamma"],
                [5, "Alan", 83, "beta"],
                [6, "Barbara", 97, "alpha"],
            ],
            "operations": [
                {"id": "pass", "label": "WHERE score >= 80", "type": "filter", "column": "score", "op": ">=", "value": 80},
                {"id": "rank", "label": "ORDER BY score DESC", "type": "sort", "column": "score", "dir": "desc"},
                {"id": "cols", "label": "SELECT name, score", "type": "select", "columns": ["name", "score"]},
                {"id": "top", "label": "LIMIT 3", "type": "limit", "n": 3},
            ],
            "defaults": ["pass", "rank", "cols", "top"],
        },
        "parsons": {
            "prompt": "Rebuild the parameterized query and its result loop.",
            "lines": [
                "String sql = \"SELECT id, name, score FROM students WHERE score >= ? ORDER BY score DESC\";",
                "try (PreparedStatement query = connection.prepareStatement(sql)) {",
                "    query.setInt(1, 80);",
                "    try (ResultSet rows = query.executeQuery()) {",
                "        while (rows.next()) {",
                "            System.out.println(rows.getString(\"name\") + \" \" + rows.getInt(\"score\"));",
                "        }",
                "    }",
                "}",
            ],
            "solution": [0, 1, 2, 3, 4, 5, 6, 7, 8],
            "seed": 32,
            "why": "The statement is prepared, the parameter is bound before execution, and the result set is closed before the statement.",
        },
        "quiz": [
            {
                "q": "Why does a prepared statement reduce SQL injection risk?",
                "options": [
                    "It encrypts the query",
                    "It sends parameter values as data, not as query text",
                    "It removes all quotes",
                    "It runs the query twice",
                ],
                "a": 1,
                "why": "The query structure is fixed before values are bound, so data cannot become syntax.",
            },
            {
                "q": "What does <code>ResultSet.next()</code> return at the end of the rows?",
                "options": ["true", "false", "null", "An exception always"],
                "a": 1,
                "why": "False signals that the cursor has moved past the last row.",
            },
            {
                "q": "Why disable auto-commit for a transfer between two accounts?",
                "options": [
                    "To make each statement faster",
                    "To commit both updates as one unit or roll both back",
                    "To avoid prepared statements",
                    "To close the connection sooner",
                ],
                "a": 1,
                "why": "A transaction prevents a debit without its matching credit.",
            },
        ],
        "artifact": {
            "name": "StudentQuery.java",
            "description": "A JDBC query skeleton with a parameterized score threshold. Add a second parameter for the team and close every resource.",
            "code": """import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentQuery {
    public static void printHonorRoll(Connection connection, int minimum) throws Exception {
        String sql = "SELECT id, name, score FROM students "
                + "WHERE score >= ? ORDER BY score DESC";
        try (PreparedStatement query = connection.prepareStatement(sql)) {
            query.setInt(1, minimum);
            try (ResultSet rows = query.executeQuery()) {
                while (rows.next()) {
                    System.out.println(rows.getInt("id") + " "
                            + rows.getString("name") + " "
                            + rows.getInt("score"));
                }
            }
        }
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_32",
        },
        "reflection": "Explain why <code>ORDER BY</code> must be explicit even if the rows happen to come back sorted in a test run.",
        "studio": {
            "title": "SQL pipeline",
            "intro": "The data viewer applies operations in sequence. Notice that ORDER BY after LIMIT gives a different result from LIMIT after ORDER BY.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/enterprise.md#chapter-32-jdbc",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_32",
        },
    },
    {
        "n": 33,
        "title": "JavaServer Faces",
        "part": "Part 5 · Enterprise Java",
        "tagline": "JSF is a component lifecycle: decode, validate, update the model, invoke the action, then render.",
        "minutes": 50,
        "tags": ["jsf", "lifecycle", "managed bean", "validation", "faces", "component"],
        "objectives": [
            "List the JSF request lifecycle phases in order.",
            "Explain where conversion and validation run relative to the model update.",
            "Describe the role of a managed bean in a JSF page.",
        ],
        "subgoals": [
            {
                "title": "The view is a component tree",
                "idea": "A Facelets page becomes a tree of components. The lifecycle walks that tree; it is not a simple template render.",
            },
            {
                "title": "Decode maps input to components",
                "idea": "Submitted values are applied to component local values. The model is not touched yet.",
            },
            {
                "title": "Validation can short-circuit",
                "idea": "If a converter or validator fails, the lifecycle jumps to render response. The action never runs with invalid data.",
            },
            {
                "title": "Update model copies to the bean",
                "idea": "Only after validation do component values move into the managed bean. This ordering protects the model.",
            },
            {
                "title": "Invoke, then render",
                "idea": "The action method runs after the model is valid. The response may be a forward to another view or the same view with messages.",
            },
        ],
        "example": {
            "title": "RegistrationBean: one form, one lifecycle",
            "intro": "The bean holds the model. The page binds inputs to properties. The button invokes an action only after validation succeeds.",
            "code": [
                "<h:form>",
                "  <h:inputText value=\"#{registration.name}\" required=\"true\" />",
                "  <h:inputText value=\"#{registration.email}\" required=\"true\" />",
                "  <h:commandButton value=\"Register\" action=\"#{registration.submit}\" />",
                "</h:form>",
                "",
                "@ManagedBean",
                "public class RegistrationBean {",
                "    private String name;",
                "    private String email;",
                "    public String submit() { return \"confirmed\"; }",
                "}",
            ],
            "steps": [
                {
                    "label": "Build the view",
                    "note": "The form and its inputs become component objects. The value expressions are stored on the components.",
                },
                {
                    "label": "Apply request values",
                    "note": "The submitted strings are stored as local values on each input component.",
                },
                {
                    "label": "Process validations",
                    "note": "<code>required</code> fails on an empty field. A failure sends the lifecycle directly to render response.",
                },
                {
                    "label": "Update model",
                    "note": "Valid values are written through the expressions into the managed bean properties.",
                },
                {
                    "label": "Invoke application",
                    "note": "<code>submit</code> runs with a valid bean. Its return value selects the next view.",
                },
            ],
            "predict": {
                "prompt": "If the email field is empty, does <code>RegistrationBean.submit</code> run?",
                "answer": "No. The required validator fails during process validations, so the lifecycle skips update model and invoke application and re-renders with an error message.",
            },
        },
        "engine": "state",
        "spec": {
            "hint": "Step through the lifecycle. A validation failure changes the path, not just the message.",
            "code": [
                "FacesContext ctx = FacesContext.getCurrentInstance();",
                "UIViewRoot view = ctx.getViewRoot();",
                "view.processDecodes(ctx);",
                "view.processValidators(ctx);",
                "view.processUpdates(ctx);",
                "ctx.renderResponse();",
            ],
            "vars": ["phase", "model", "response"],
            "steps": [
                {
                    "line": 0,
                    "vars": {"phase": "restore view", "model": "unchanged", "response": "none"},
                    "note": "The component tree is restored or built for this request.",
                },
                {
                    "line": 2,
                    "vars": {"phase": "apply request values", "model": "unchanged", "response": "none"},
                    "note": "Submitted strings land in component local values.",
                },
                {
                    "line": 3,
                    "vars": {"phase": "process validations", "model": "unchanged", "response": "pending"},
                    "note": "Converters and validators run. A failure will skip the model update.",
                },
                {
                    "line": 4,
                    "vars": {"phase": "update model", "model": "written", "response": "pending"},
                    "note": "Valid values are copied into the managed bean.",
                },
                {
                    "line": 5,
                    "vars": {"phase": "invoke application", "model": "written", "response": "confirmed"},
                    "note": "The action runs and chooses the next view.",
                },
                {
                    "line": 5,
                    "vars": {"phase": "render response", "model": "written", "response": "confirmed"},
                    "note": "The selected view is rendered; the response is complete.",
                },
            ],
        },
        "parsons": {
            "prompt": "Rebuild the JSF form that binds two fields and invokes an action.",
            "lines": [
                "<h:form>",
                "  <h:inputText value=\"#{registration.name}\" required=\"true\" />",
                "  <h:inputText value=\"#{registration.email}\" required=\"true\" />",
                "  <h:commandButton value=\"Register\" action=\"#{registration.submit}\" />",
                "</h:form>",
            ],
            "solution": [0, 1, 2, 3, 4],
            "seed": 33,
            "why": "The form wraps both inputs, and the command button invokes the bean action only after validation.",
        },
        "quiz": [
            {
                "q": "When does the managed bean receive submitted values?",
                "options": [
                    "During apply request values",
                    "During process validations",
                    "During update model, after validation succeeds",
                    "After render response",
                ],
                "a": 2,
                "why": "The model update phase runs only after conversion and validation pass.",
            },
            {
                "q": "What happens when a required field is empty?",
                "options": [
                    "The action runs with null",
                    "The lifecycle skips to render response with an error",
                    "The bean is deleted",
                    "The server restarts",
                ],
                "a": 1,
                "why": "Validation failure short-circuits the request so invalid data never reaches the model or action.",
            },
            {
                "q": "What is a managed bean in JSF?",
                "options": [
                    "A CSS file",
                    "A Java object whose properties and actions are bound to view components",
                    "A database table",
                    "A browser cookie",
                ],
                "a": 1,
                "why": "The bean is the model/controller surface referenced by value expressions.",
            },
        ],
        "artifact": {
            "name": "RegistrationBean.java",
            "description": "A JSF managed bean with name, email, and a submit action. Add a validator that rejects disposable domains for the exercise.",
            "code": """import javax.faces.bean.ManagedBean;

@ManagedBean
public class RegistrationBean {
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String submit() {
        // TODO: validate the domain, then return "confirmed" or null to stay on the page.
        return "confirmed";
    }
}
""",
            "href": "https://github.com/M0-AR/YL/tree/master/src/Exercise_33",
        },
        "reflection": "Explain why validation belongs before the model update rather than inside the action method. Name one failure that ordering prevents.",
        "studio": {
            "title": "Lifecycle walker",
            "intro": "Follow the six phases. The model column stays untouched until validation has passed.",
        },
        "links": {
            "companion": "https://github.com/M0-AR/YL/blob/master/companion/enterprise.md#chapter-33-javaserver-faces",
            "repo": "https://github.com/M0-AR/YL/tree/master/src/Exercise_33",
        },
    },
]
