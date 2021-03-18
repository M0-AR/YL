package Exercise_10._05;

public class StackOfIntegers {
    public static final int DEFAULT_CAPACITY = 16;
    private int[] elements;
    private int size;

    public StackOfIntegers() {
        this(DEFAULT_CAPACITY);
    }

    public StackOfIntegers(int capacity) {
        this.elements = new int[capacity];
    }

    /** Push a new integer to the top of the stack */
    public void push(int value) {
        if (size >= elements.length) {
            int[] temp = new int[size * 2];
            System.arraycopy( elements, 0, temp, 0, elements.length);
            elements = temp;
        }
        elements[size++] = value;
    }

    /** Return the top element form the stack */
    public int pop() {
        return elements[--size];
    }

    /** Return the top element from the stack */
    public int peek() {
        return elements[size - 1];
    }

    /** Is the stack empty */
    public boolean empty() {
        return size == 0;
    }

    /** Return the number of elements in the stack */
    public int getSize() {
        return size;
    }
}
