package Exercise_26._14;

import Exercise_26._common.AVLTree;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Exercise_26_14 {
  public static <E> List<E> levelOrder(AVLTree.Node<E> root) {
    List<E> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    Deque<AVLTree.Node<E>> queue = new ArrayDeque<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      AVLTree.Node<E> node = queue.remove();
      result.add(node.element);
      if (node.left != null) {
        queue.add(node.left);
      }
      if (node.right != null) {
        queue.add(node.right);
      }
    }
    return result;
  }

  public static void main(String[] args) {
    AVLTree<Integer> tree = tree(5, 3, 7, 2, 4, 6, 8);
    check(levelOrder(tree.getRoot()).equals(List.of(5, 3, 7, 2, 4, 6, 8)));
    System.out.println("Exercise_26_14 OK");
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
