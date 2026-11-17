package Exercise_26._22;

import Exercise_26._common.AVLTree;
import java.util.ArrayList;
import java.util.List;

public class Exercise_26_22 {
  public static <E extends Comparable<E>> List<E> range(
      AVLTree.Node<E> node, E low, E high) {
    List<E> result = new ArrayList<>();
    collect(node, low, high, result);
    return result;
  }

  private static <E extends Comparable<E>> void collect(
      AVLTree.Node<E> node, E low, E high, List<E> result) {
    if (node == null) {
      return;
    }
    if (node.element.compareTo(low) > 0) {
      collect(node.left, low, high, result);
    }
    if (node.element.compareTo(low) >= 0 && node.element.compareTo(high) <= 0) {
      result.add(node.element);
    }
    if (node.element.compareTo(high) < 0) {
      collect(node.right, low, high, result);
    }
  }

  public static void main(String[] args) {
    AVLTree<Integer> tree = tree(5, 3, 7, 2, 4, 6, 8);
    check(range(tree.getRoot(), 3, 6).equals(List.of(3, 4, 5, 6)));
    check(range(tree.getRoot(), 9, 10).isEmpty());
    System.out.println("Exercise_26_22 OK");
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
