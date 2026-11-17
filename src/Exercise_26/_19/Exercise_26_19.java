package Exercise_26._19;

import Exercise_26._common.AVLTree;

public class Exercise_26_19 {
  public static <E> boolean sameTree(AVLTree.Node<E> first, AVLTree.Node<E> second) {
    if (first == null || second == null) {
      return first == second;
    }
    if (!first.element.equals(second.element)) {
      return false;
    }
    return sameTree(first.left, second.left) && sameTree(first.right, second.right);
  }

  public static void main(String[] args) {
    AVLTree<Integer> first = tree(5, 3, 7, 2, 4, 6, 8);
    AVLTree<Integer> second = tree(5, 3, 7, 2, 4, 6, 8);
    check(sameTree(first.getRoot(), second.getRoot()));
    second.insert(9);
    check(!sameTree(first.getRoot(), second.getRoot()));
    System.out.println("Exercise_26_19 OK");
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
