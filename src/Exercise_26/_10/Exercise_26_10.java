package Exercise_26._10;

import Exercise_26._common.AVLTree;
import java.util.ArrayList;
import java.util.List;

public class Exercise_26_10 {
  public static <E> int balanceFactor(AVLTree.Node<E> node) {
    return AVLTree.heightOf(node.left) - AVLTree.heightOf(node.right);
  }

  public static <E> List<Integer> balanceFactors(AVLTree.Node<E> node) {
    List<Integer> result = new ArrayList<>();
    collect(node, result);
    return result;
  }

  private static <E> void collect(AVLTree.Node<E> node, List<Integer> result) {
    if (node == null) {
      return;
    }
    collect(node.left, result);
    result.add(balanceFactor(node));
    collect(node.right, result);
  }

  public static void main(String[] args) {
    AVLTree<Integer> tree = tree(5, 3, 7, 2, 4, 6, 8);
    List<Integer> factors = balanceFactors(tree.getRoot());
    check(factors.size() == 7);
    for (int factor : factors) {
      check(Math.abs(factor) <= 1);
    }
    System.out.println("Exercise_26_10 OK");
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
