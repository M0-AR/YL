package Exercise_24._03;

import java.util.ListIterator;

public class Exercise_24_03 {
    public static void main(String[] args) {
        TwoWayLinkedList<Integer> list = new TwoWayLinkedList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        System.out.println("List: " + list);
        System.out.println("Forward: " + traverse(list.listIterator(), true));
        System.out.println("Backward: " + traverse(list.listIterator(list.size()), false));

        ListIterator<Integer> iterator = list.listIterator();
        iterator.next();
        iterator.next();
        iterator.set(20);
        iterator.add(25);
        iterator.next();
        iterator.remove();
        System.out.println("After iterator edits: " + list);
        System.out.println("Size: " + list.size());
    }

    private static String traverse(ListIterator<Integer> iterator, boolean forward) {
        StringBuilder text = new StringBuilder();
        while (forward ? iterator.hasNext() : iterator.hasPrevious()) {
            text.append(forward ? iterator.next() : iterator.previous()).append(' ');
        }
        return text.toString().trim();
    }
}
