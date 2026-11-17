package Exercise_26._12;

import Exercise_26._common.AVLTree;
import java.util.ArrayList;
import java.util.List;

public class Exercise_26_12 {
  public static <E> List<E> preorder(AVLTree.Node<E> node) {
    List<E> result = new ArrayList<>();
    preorder(node, result);
    return result;
  }

  private static <E> void preorder(AVLTree.Node<E> node, List<E> result) {
    if (node == null) {
      return;
    }
    result.add(node.element);
    preorder(node.left, result);
    preorder(node.right, result);
  }

  public static void main(String[] args) {
    AVLTree<Integer> tree = tree(5, 3, 7, 2, 4, 6, 8);
    check(preorder(tree.getRoot()).equals(List.of(5, 3, 2, 4, 7, 6, 8)));
    System.out.println("Exercise_26_12 OK");
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
