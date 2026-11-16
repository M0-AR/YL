package Exercise_26;

public class Chapter26 {
    public static int exerciseOne(int[] values) {
        return height(build(values));
    }

    public static int exerciseTwo(int[] values) {
        Node root = build(values);
        return root == null ? -1 : root.key;
    }

    public static boolean exerciseThree(int[] values) {
        return isBalanced(build(values));
    }

    private static Node build(int[] values) {
        Node root = null;
        if (values != null) {
            for (int value : values) {
                root = insert(root, value);
            }
        }
        return root;
    }

    private static Node insert(Node node, int key) {
        if (node == null) {
            return new Node(key);
        }
        if (key < node.key) {
            node.left = insert(node.left, key);
        } else if (key > node.key) {
            node.right = insert(node.right, key);
        } else {
            return node;
        }
        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = height(node.left) - height(node.right);
        if (balance > 1 && key < node.left.key) {
            return rotateRight(node);
        }
        if (balance < -1 && key > node.right.key) {
            return rotateLeft(node);
        }
        if (balance > 1 && key > node.left.key) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (balance < -1 && key < node.right.key) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }

    private static Node rotateRight(Node node) {
        Node pivot = node.left;
        Node middle = pivot.right;
        pivot.right = node;
        node.left = middle;
        node.height = 1 + Math.max(height(node.left), height(node.right));
        pivot.height = 1 + Math.max(height(pivot.left), height(pivot.right));
        return pivot;
    }

    private static Node rotateLeft(Node node) {
        Node pivot = node.right;
        Node middle = pivot.left;
        pivot.left = node;
        node.right = middle;
        node.height = 1 + Math.max(height(node.left), height(node.right));
        pivot.height = 1 + Math.max(height(pivot.left), height(pivot.right));
        return pivot;
    }

    private static int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private static boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }
        int balance = height(node.left) - height(node.right);
        return Math.abs(balance) <= 1 && isBalanced(node.left) && isBalanced(node.right);
    }

    private static final class Node {
        final int key;
        Node left;
        Node right;
        int height = 1;

        Node(int key) {
            this.key = key;
        }
    }

    public static void main(String[] args) {
        int[] ascending = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(exerciseOne(ascending));
        System.out.println(exerciseTwo(new int[] {30, 20, 10}));
        System.out.println(exerciseThree(ascending));
    }
}
