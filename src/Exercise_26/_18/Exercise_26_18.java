package Exercise_26._18;

import Exercise_26._common.AVLTree;
import java.util.ArrayList;
import java.util.List;

public class Exercise_26_18 {
  private static class TreeNode<E> {
    private final E value;
    private TreeNode<E> left;
    private TreeNode<E> right;

    private TreeNode(E value) {
      this.value = value;
    }
  }

  public static <E> TreeNode<E> cloneTree(AVLTree.Node<E> node) {
    if (node == null) {
      return null;
    }
    TreeNode<E> copy = new TreeNode<>(node.element);
    copy.left = cloneTree(node.left);
    copy.right = cloneTree(node.right);
    return copy;
  }

  public static <E> List<E> preorder(TreeNode<E> node) {
    List<E> result = new ArrayList<>();
    preorder(node, result);
    return result;
  }

  private static <E> void preorder(TreeNode<E> node, List<E> result) {
    if (node == null) {
      return;
    }
    result.add(node.value);
    preorder(node.left, result);
    preorder(node.right, result);
  }

  public static void main(String[] args) {
    AVLTree<Integer> tree = tree(5, 3, 7, 2, 4, 6, 8);
    TreeNode<Integer> copy = cloneTree(tree.getRoot());
    check(copy != null);
    check((Object) copy != tree.getRoot());
    check(preorder(copy).equals(List.of(5, 3, 2, 4, 7, 6, 8)));
    System.out.println("Exercise_26_18 OK");
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
