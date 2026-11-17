package Exercise_26._02;

import java.util.Random;

import Exercise_26._common.AVLTree;

public class Exercise_26_02 {
    private static final int COUNT = 500_000;

    public static void main(String[] args) {
        long[] bst = benchmark(COUNT, false);
        long[] avl = benchmark(COUNT, true);
        System.out.printf("%-10s%14s%14s%14s%14s%n",
                "Tree", "insert", "search", "delete", "total");
        System.out.printf("%-10s%14d%14d%14d%14d%n",
                "BST", bst[0], bst[1], bst[2], bst[0] + bst[1] + bst[2]);
        System.out.printf("%-10s%14d%14d%14d%14d%n",
                "AVL", avl[0], avl[1], avl[2], avl[0] + avl[1] + avl[2]);
    }

    static long[] benchmark(int count, boolean balanced) {
        Random random = new Random(2026);
        int[] values = new int[count];
        for (int i = 0; i < count; i++) {
            values[i] = random.nextInt();
        }

        long insertTime;
        long searchTime;
        long deleteTime;
        if (balanced) {
            AVLTree<Integer> tree = new AVLTree<>();
            long start = System.nanoTime();
            for (int value : values) {
                tree.insert(value);
            }
            insertTime = System.nanoTime() - start;

            shuffle(values, random);
            start = System.nanoTime();
            for (int value : values) {
                tree.search(value);
            }
            searchTime = System.nanoTime() - start;

            shuffle(values, random);
            start = System.nanoTime();
            for (int value : values) {
                tree.delete(value);
            }
            deleteTime = System.nanoTime() - start;
        } else {
            BinarySearchTree tree = new BinarySearchTree();
            long start = System.nanoTime();
            for (int value : values) {
                tree.insert(value);
            }
            insertTime = System.nanoTime() - start;

            shuffle(values, random);
            start = System.nanoTime();
            for (int value : values) {
                tree.search(value);
            }
            searchTime = System.nanoTime() - start;

            shuffle(values, random);
            start = System.nanoTime();
            for (int value : values) {
                tree.delete(value);
            }
            deleteTime = System.nanoTime() - start;
        }
        return new long[] {insertTime / 1_000_000, searchTime / 1_000_000,
                deleteTime / 1_000_000};
    }

    private static void shuffle(int[] values, Random random) {
        for (int i = values.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = values[i];
            values[i] = values[j];
            values[j] = temp;
        }
    }
}
