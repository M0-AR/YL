package Exercise_24;

import java.util.Arrays;

public class Chapter24 {
    public static int[] exerciseOne(int[] values) {
        IntList list = new IntList();
        if (values != null) {
            for (int value : values) {
                list.add(value);
            }
        }
        return list.toArray();
    }

    public static int[] exerciseTwo(int[] values, int index) {
        IntList list = IntList.from(values);
        list.remove(index);
        return list.toArray();
    }

    public static int[] exerciseThree(int[] values, int index, int value) {
        IntList list = IntList.from(values);
        list.insert(index, value);
        return list.toArray();
    }

    private static int capacityAfterAdds(int count) {
        IntList list = new IntList();
        for (int i = 0; i < count; i++) {
            list.add(i);
        }
        return list.capacity();
    }

    private static final class IntList {
        private int[] data = new int[2];
        private int size;

        static IntList from(int[] values) {
            IntList list = new IntList();
            if (values != null) {
                for (int value : values) {
                    list.add(value);
                }
            }
            return list;
        }

        void add(int value) {
            ensureCapacity(size + 1);
            data[size++] = value;
        }

        void insert(int index, int value) {
            int position = Math.max(0, Math.min(index, size));
            ensureCapacity(size + 1);
            System.arraycopy(data, position, data, position + 1, size - position);
            data[position] = value;
            size++;
        }

        void remove(int index) {
            if (index < 0 || index >= size) {
                return;
            }
            System.arraycopy(data, index + 1, data, index, size - index - 1);
            size--;
        }

        int capacity() {
            return data.length;
        }

        int[] toArray() {
            return Arrays.copyOf(data, size);
        }

        private void ensureCapacity(int required) {
            if (required <= data.length) {
                return;
            }
            int next = data.length * 2;
            if (next < required) {
                next = required;
            }
            data = Arrays.copyOf(data, next);
        }
    }

    public static void main(String[] args) {
        int[] source = {5, 8, 12, 3};
        System.out.println(Arrays.toString(exerciseOne(source)));
        System.out.println(Arrays.toString(exerciseTwo(source, 1)));
        System.out.println(Arrays.toString(exerciseThree(source, 2, 99)));
        System.out.println(capacityAfterAdds(9));
    }
}
