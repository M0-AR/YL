package Exercise_26._25;

import Exercise_26._common.AVLTree;

public class Exercise_26_25 {
  public static <E> boolean validParents(AVLTree.Node<E> node, AVLTree.Node<E> parent) {
    if (node == null) {
      return true;
    }
    if (node.parent != parent) {
      return false;
    }
    return validParents(node.left, node) && validParents(node.right, node);
  }

  public static void main(String[] args) {
    AVLTree<Integer> tree = tree(5, 3, 7, 2, 4, 6, 8);
    check(validParents(tree.getRoot(), null));
    tree.getRoot().left.parent = null;
    check(!validParents(tree.getRoot(), null));
    System.out.println("Exercise_26_25 OK");
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
