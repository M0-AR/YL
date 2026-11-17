package Exercise_24._15;

import java.util.Iterator;

public class Exercise_24_15 {
    public static void main(String[] args) {
        MyList<String> list = new MyArrayList<>();
        list.add("Tom");
        list.add("George");
        list.add("Peter");
        list.add(1, "Jean");
        check(list.size() == 4, "add and size");
        check(list.get(1).equals("Jean"), "get");
        check(list.indexOf("Peter") == 3, "indexOf");
        check(list.lastIndexOf("Peter") == 3, "lastIndexOf");
        check(list.contains("George"), "contains");
        check(list.set(0, "Jane").equals("Tom"), "set returns old value");
        check(list.get(0).equals("Jane"), "set stores new value");
        check(list.remove(0).equals("Jane"), "remove by index");
        check(list.size() == 3, "size after remove");

        int count = 0;
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }
        check(count == 3, "iterator");
        list.clear();
        check(list.size() == 0 && list.isEmpty(), "clear and isEmpty");
        System.out.println("MyArrayList tests passed");
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError("Failed: " + message);
        }
        System.out.println("PASS: " + message);
    }
}
