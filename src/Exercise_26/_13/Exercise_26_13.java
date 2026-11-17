package Exercise_26._13;

import Exercise_26._common.AVLTree;
import java.util.ArrayList;
import java.util.List;

public class Exercise_26_13 {
  public static <E> List<E> postorder(AVLTree.Node<E> node) {
    List<E> result = new ArrayList<>();
    postorder(node, result);
    return result;
  }

  private static <E> void postorder(AVLTree.Node<E> node, List<E> result) {
    if (node == null) {
      return;
    }
    postorder(node.left, result);
    postorder(node.right, result);
    result.add(node.element);
  }

  public static void main(String[] args) {
    AVLTree<Integer> tree = tree(5, 3, 7, 2, 4, 6, 8);
    check(postorder(tree.getRoot()).equals(List.of(2, 4, 3, 6, 8, 7, 5)));
    System.out.println("Exercise_26_13 OK");
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
