package Exercise_26._16;

import Exercise_26._common.AVLTree;

public class Exercise_26_16 {
  public static <E> E min(AVLTree.Node<E> node) {
    if (node == null) {
      return null;
    }
    while (node.left != null) {
      node = node.left;
    }
    return node.element;
  }

  public static <E> E max(AVLTree.Node<E> node) {
    if (node == null) {
      return null;
    }
    while (node.right != null) {
      node = node.right;
    }
    return node.element;
  }

  public static void main(String[] args) {
    AVLTree<Integer> tree = tree(5, 3, 7, 2, 4, 6, 8);
    check(min(tree.getRoot()) == 2);
    check(max(tree.getRoot()) == 8);
    check(min(null) == null);
    System.out.println("Exercise_26_16 OK");
  }

  private static AVLTree<Integer> tree(Integer... values) {
    AVLTree<Integer> tree = new AVLTree<>();
    for (int value : values) {
      tree.insert(value);
    }
    return tree;
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
