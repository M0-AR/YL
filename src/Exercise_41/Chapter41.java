package Exercise_41;

public class Chapter41 {
    public static final class Node {
        private final int key;
        private Node left;
        private Node right;
        private boolean red;

        public Node(int key, boolean red) {
            this.key = key;
            this.red = red;
        }

        public int key() {
            return key;
        }

        public boolean isRed() {
            return red;
        }

        public Node left() {
            return left;
        }

        public Node right() {
            return right;
        }
    }

    public static Node rotateLeft(Node root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.key) {
            root.left = rotateLeft(root.left, key);
            return root;
        }
        if (key > root.key) {
            root.right = rotateLeft(root.right, key);
            return root;
        }
        if (root.right == null) {
            return root;
        }
        return rotateLeftNode(root);
    }

    public static Node insert(Node root, int key) {
        Node result = insertNode(root, key);
        result.red = false;
        return result;
    }

    public static boolean validate(Node root) {
        if (root == null) {
            return true;
        }
        if (root.red) {
            return false;
        }
        return check(root, 0, new int[] {-1});
    }

    private static Node insertNode(Node node, int key) {
        if (node == null) {
            return new Node(key, true);
        }
        if (key < node.key) {
            node.left = insertNode(node.left, key);
        } else if (key > node.key) {
            node.right = insertNode(node.right, key);
        } else {
            return node;
        }
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeftNode(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRightNode(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
    }

    private static Node rotateLeftNode(Node node) {
        Node pivot = node.right;
        node.right = pivot.left;
        pivot.left = node;
        pivot.red = node.red;
        node.red = true;
        return pivot;
    }

    private static Node rotateRightNode(Node node) {
        Node pivot = node.left;
        node.left = pivot.right;
        pivot.right = node;
        pivot.red = node.red;
        node.red = true;
        return pivot;
    }

    private static void flipColors(Node node) {
        node.red = true;
        node.left.red = false;
        node.right.red = false;
    }

    private static boolean isRed(Node node) {
        return node != null && node.red;
    }

    private static boolean check(Node node, int blackHeight, int[] expected) {
        if (node == null) {
            if (expected[0] == -1) {
                expected[0] = blackHeight;
            }
            return expected[0] == blackHeight;
        }
        if (isRed(node.right)) {
            return false;
        }
        if (isRed(node) && (isRed(node.left) || isRed(node.right))) {
            return false;
        }
        int nextHeight = blackHeight + (isRed(node) ? 0 : 1);
        return check(node.left, nextHeight, expected) && check(node.right, nextHeight, expected);
    }

    public static void main(String[] args) {
        Node root = null;
        for (int key = 1; key <= 10; key++) {
            root = insert(root, key);
        }
        System.out.println(root.key());
        System.out.println(validate(root));
        Node rotated = rotateLeft(root, 2);
        System.out.println(rotated.key());
    }
}
