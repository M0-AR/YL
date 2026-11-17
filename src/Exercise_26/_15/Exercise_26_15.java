package Exercise_26._15;

import Exercise_26._common.AVLTree;
import java.util.ArrayList;
import java.util.List;

public class Exercise_26_15 {
  public static <E extends Comparable<E>> List<E> searchPath(
      AVLTree.Node<E> root, E key) {
    List<E> path = new ArrayList<>();
    AVLTree.Node<E> current = root;
    while (current != null) {
      path.add(current.element);
      int comparison = key.compareTo(current.element);
      if (comparison == 0) {
        break;
      }
      current = comparison < 0 ? current.left : current.right;
    }
    return path;
  }

  public static void main(String[] args) {
    AVLTree<Integer> tree = tree(5, 3, 7, 2, 4, 6, 8);
    check(searchPath(tree.getRoot(), 4).equals(List.of(5, 3, 4)));
    check(searchPath(tree.getRoot(), 9).equals(List.of(5, 7, 8)));
    System.out.println("Exercise_26_15 OK");
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
