package Exercise_26._21;

import Exercise_26._common.AVLTree;

public class Exercise_26_21 {
  public static <E extends Comparable<E>> E kthSmallest(AVLTree<E> tree, int k) {
    return tree.find(k);
  }

  public static void main(String[] args) {
    AVLTree<Integer> tree = tree(5, 3, 7, 2, 4, 6, 8);
    check(kthSmallest(tree, 1) == 2);
    check(kthSmallest(tree, 4) == 5);
    check(kthSmallest(tree, 7) == 8);
    check(kthSmallest(tree, 8) == null);
    System.out.println("Exercise_26_21 OK");
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
