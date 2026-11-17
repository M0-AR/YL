package Exercise_26._07;

import Exercise_25._common.BST;

import java.util.Scanner;

public class Exercise_26_07 {
  public static void main(String[] args) {
    MyBST<Integer> tree = new MyBST<>();
    Scanner input = new Scanner(System.in);
    System.out.print("How many integers to be inserted into the tree? ");
    int number = input.nextInt();
    System.out.print("Enter " + number + " integers: ");
    for (int i = 0; i < number; i++) {
      tree.insert(input.nextInt());
    }
    System.out.println("Is this tree an AVL tree? " + tree.isAVLTree());
  }
}

class MyBST<E extends Comparable<E>> extends BST<E> {
  @Override
  public int height() {
    return height(getRoot());
  }

  public int height(BST.TreeNode<E> node) {
    if (node == null) {
      return -1;
    }
    return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
  }

  public boolean isAVLTree() {
    return isAVLTree(getRoot());
  }

  public boolean isAVLTree(BST.TreeNode<E> node) {
    if (node == null) {
      return true;
    }
    int balance = height(node.getLeft()) - height(node.getRight());
    return Math.abs(balance) <= 1
        && isAVLTree(node.getLeft())
        && isAVLTree(node.getRight());
  }
}
