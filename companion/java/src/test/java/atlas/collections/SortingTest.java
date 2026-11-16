package atlas.collections;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SortingTest {
    private static final int[] EXPECTED = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    @Test
    void insertionSortOrdersValues() {
        int[] values = {9, 4, 7, 1, 8, 2, 6, 3, 5};
        Sorting.insertionSort(values);
        assertArrayEquals(EXPECTED, values);
    }

    @Test
    void mergeSortOrdersValues() {
        int[] values = {9, 4, 7, 1, 8, 2, 6, 3, 5};
        Sorting.mergeSort(values);
        assertArrayEquals(EXPECTED, values);
    }

    @Test
    void quickSortOrdersValues() {
        int[] values = {9, 4, 7, 1, 8, 2, 6, 3, 5};
        Sorting.quickSort(values);
        assertArrayEquals(EXPECTED, values);
    }

    @Test
    void allAlgorithmsHandleRandomInput() {
        Random random = new Random(42);
        int[] original = random.ints(500, -1_000, 1_000).toArray();

        int[] insertion = original.clone();
        int[] merge = original.clone();
        int[] quick = original.clone();

        Sorting.insertionSort(insertion);
        Sorting.mergeSort(merge);
        Sorting.quickSort(quick);

        int[] expected = original.clone();
        Arrays.sort(expected);

        assertArrayEquals(expected, insertion);
        assertArrayEquals(expected, merge);
        assertArrayEquals(expected, quick);
    }

    @Test
    void emptyAndSingleElementArraysAreSorted() {
        int[] empty = {};
        int[] single = {7};

        Sorting.mergeSort(empty);
        Sorting.quickSort(single);

        assertTrue(Sorting.isSorted(empty));
        assertTrue(Sorting.isSorted(single));
    }
}
