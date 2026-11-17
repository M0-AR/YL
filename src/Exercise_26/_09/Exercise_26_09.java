package Exercise_26._09;

import Exercise_26._common.AVLTree;

public class Exercise_26_09 {
  public static <E> int countLeaves(AVLTree.Node<E> node) {
    if (node == null) {
      return 0;
    }
    if (node.left == null && node.right == null) {
      return 1;
    }
    return countLeaves(node.left) + countLeaves(node.right);
  }

  public static void main(String[] args) {
    AVLTree<Integer> tree = tree(5, 3, 7, 2, 4, 6, 8);
    check(countLeaves(tree.getRoot()) == 4);
    check(countLeaves(null) == 0);
    System.out.println("Exercise_26_09 OK");
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
