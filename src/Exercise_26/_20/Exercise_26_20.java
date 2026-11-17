package Exercise_26._20;

import Exercise_26._common.AVLTree;

public class Exercise_26_20 {
  public static <E> boolean isAVL(AVLTree.Node<E> node) {
    return heightOrMinusOne(node) >= 0;
  }

  private static <E> int heightOrMinusOne(AVLTree.Node<E> node) {
    if (node == null) {
      return 0;
    }
    int left = heightOrMinusOne(node.left);
    int right = heightOrMinusOne(node.right);
    if (left < 0 || right < 0 || Math.abs(left - right) > 1) {
      return -1;
    }
    return 1 + Math.max(left, right);
  }

  public static void main(String[] args) {
    AVLTree<Integer> tree = tree(5, 3, 7, 2, 4, 6, 8);
    check(isAVL(tree.getRoot()));
    check(isAVL(null));
    AVLTree.Node<Integer> root = tree.getRoot();
    AVLTree.Node<Integer> three = root.left;
    AVLTree.Node<Integer> two = three.left;
    AVLTree.Node<Integer> four = three.right;
    root.left = three;
    three.left = two;
    two.left = four;
    four.left = root.right.left;
    root.right = root.right.right;
    check(!isAVL(root));
    System.out.println("Exercise_26_20 OK");
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
