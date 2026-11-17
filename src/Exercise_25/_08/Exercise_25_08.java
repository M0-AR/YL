package Exercise_25._08;

import Exercise_25._common.BST;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Exercise_25_08 {
    public static void main(String[] args) {
        BST<Integer> tree = new BST<>(new Integer[] {50, 30, 70, 20, 40, 60, 80});

        List<Integer> forward = new ArrayList<>();
        ListIterator<Integer> iterator = tree.listIterator();
        while (iterator.hasNext()) {
            forward.add(iterator.next());
        }
        System.out.println("Forward: " + forward);

        List<Integer> backward = new ArrayList<>();
        while (iterator.hasPrevious()) {
            backward.add(iterator.previous());
        }
        System.out.println("Backward: " + backward);

        iterator.add(35);
        System.out.println("After add 35: " + tree.inorderList());

        iterator = tree.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next() == 60) {
                iterator.set(65);
                break;
            }
        }
        System.out.println("After set 65: " + tree.inorderList());

        iterator = tree.listIterator();
        if (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        System.out.println("After remove: " + tree.inorderList());
    }
}
