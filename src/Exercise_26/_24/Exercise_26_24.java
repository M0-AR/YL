package Exercise_26._24;

import Exercise_26._common.AVLTree;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Function;

public class Exercise_26_24 {
  private static class TreeNode<E> {
    private final E value;
    private TreeNode<E> left;
    private TreeNode<E> right;

    private TreeNode(E value) {
      this.value = value;
    }
  }

  public static <E> String serialize(AVLTree.Node<E> node) {
    StringBuilder result = new StringBuilder();
    serialize(node, result);
    return result.toString();
  }

  private static <E> void serialize(AVLTree.Node<E> node, StringBuilder result) {
    if (node == null) {
      result.append("# ");
      return;
    }
    result.append(node.element).append(' ');
    serialize(node.left, result);
    serialize(node.right, result);
  }

  public static <E> TreeNode<E> deserialize(String text, Function<String, E> parser) {
    Deque<String> tokens = new ArrayDeque<>();
    for (String token : text.trim().split(" +")) {
      tokens.add(token);
    }
    return deserialize(tokens, parser);
  }

  private static <E> TreeNode<E> deserialize(
      Deque<String> tokens, Function<String, E> parser) {
    String token = tokens.remove();
    if (token.equals("#")) {
      return null;
    }
    TreeNode<E> node = new TreeNode<>(parser.apply(token));
    node.left = deserialize(tokens, parser);
    node.right = deserialize(tokens, parser);
    return node;
  }

  public static <E> String serialize(TreeNode<E> node) {
    StringBuilder result = new StringBuilder();
    serialize(node, result);
    return result.toString();
  }

  private static <E> void serialize(TreeNode<E> node, StringBuilder result) {
    if (node == null) {
      result.append("# ");
      return;
    }
    result.append(node.value).append(' ');
    serialize(node.left, result);
    serialize(node.right, result);
  }

  public static void main(String[] args) {
    AVLTree<Integer> tree = tree(5, 3, 7, 2, 4, 6, 8);
    String text = serialize(tree.getRoot());
    TreeNode<Integer> copy = deserialize(text, Integer::valueOf);
    check(serialize(copy).equals(text));
    System.out.println("Exercise_26_24 OK");
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
