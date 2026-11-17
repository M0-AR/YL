package Exercise_26._11;

import Exercise_26._common.AVLTree;
import java.util.ArrayList;
import java.util.List;

public class Exercise_26_11 {
  public static <E> List<E> inorder(AVLTree.Node<E> node) {
    List<E> result = new ArrayList<>();
    inorder(node, result);
    return result;
  }

  private static <E> void inorder(AVLTree.Node<E> node, List<E> result) {
    if (node == null) {
      return;
    }
    inorder(node.left, result);
    result.add(node.element);
    inorder(node.right, result);
  }

  public static void main(String[] args) {
    AVLTree<Integer> tree = tree(5, 3, 7, 2, 4, 6, 8);
    check(inorder(tree.getRoot()).equals(List.of(2, 3, 4, 5, 6, 7, 8)));
    System.out.println("Exercise_26_11 OK");
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
