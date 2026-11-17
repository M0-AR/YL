package Exercise_26._08;

import Exercise_26._common.AVLTree;

public class Exercise_26_08 {
  public static <E> int countNodes(AVLTree.Node<E> node) {
    if (node == null) {
      return 0;
    }
    return 1 + countNodes(node.left) + countNodes(node.right);
  }

  public static void main(String[] args) {
    AVLTree<Integer> tree = tree(5, 3, 7, 2, 4, 6, 8);
    check(countNodes(tree.getRoot()) == 7);
    check(countNodes(tree.getRoot()) == tree.getSize());
    System.out.println("Exercise_26_08 OK");
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
