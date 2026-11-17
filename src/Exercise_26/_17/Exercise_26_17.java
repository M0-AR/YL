package Exercise_26._17;

import Exercise_26._common.AVLTree;
import java.util.List;

public class Exercise_26_17 {
  public static void main(String[] args) {
    AVLTree<Integer> tree = tree(5, 3, 7, 2, 4, 6, 8);
    check(tree.delete(7));
    check(!tree.delete(7));
    check(tree.inorderList().equals(List.of(2, 3, 4, 5, 6, 8)));
    check(tree.getSize() == 6);
    System.out.println("Exercise_26_17 OK");
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
