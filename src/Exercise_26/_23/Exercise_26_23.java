package Exercise_26._23;

import Exercise_26._common.AVLTree;

public class Exercise_26_23 {
  public static <E extends Comparable<E>> E floor(AVLTree.Node<E> node, E key) {
    E result = null;
    while (node != null) {
      if (key.compareTo(node.element) == 0) {
        return node.element;
      }
      if (key.compareTo(node.element) > 0) {
        result = node.element;
        node = node.right;
      } else {
        node = node.left;
      }
    }
    return result;
  }

  public static <E extends Comparable<E>> E ceiling(AVLTree.Node<E> node, E key) {
    E result = null;
    while (node != null) {
      if (key.compareTo(node.element) == 0) {
        return node.element;
      }
      if (key.compareTo(node.element) < 0) {
        result = node.element;
        node = node.left;
      } else {
        node = node.right;
      }
    }
    return result;
  }

  public static void main(String[] args) {
    AVLTree<Integer> tree = tree(5, 3, 7, 2, 4, 6, 8);
    check(floor(tree.getRoot(), 5) == 5);
    check(ceiling(tree.getRoot(), 5) == 5);
    check(floor(tree.getRoot(), 1) == null);
    check(ceiling(tree.getRoot(), 1) == 2);
    check(floor(tree.getRoot(), 9) == 8);
    check(ceiling(tree.getRoot(), 9) == null);
    System.out.println("Exercise_26_23 OK");
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
