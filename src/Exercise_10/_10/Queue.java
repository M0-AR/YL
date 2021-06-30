package Exercise_10._10;
        // UML diagram
/****************************
 *          Queue           *
 ****************************
 * -elements: int[]         *
 * -size: int               *
 *--------------------------*
 * +enqueue(v: int): void   *
 * +dequeue(): int          *
 * +empty(): boolean        *
 * +getSize(): int          *
 ****************************/
public class Queue {
    private int[] elements;
    private int size;

    public Queue() {
        elements = new int[8];
        size = 0;
    }

    public void enqueue(int v) {
        if (size == elements.length) {
            int[] e = new int[elements.length * 2];

            System.arraycopy(elements, 0, e, 0, elements.length);

            elements = e;
        }
        elements[size] = v;
        size++;
    }

    public int dequeue() {
        int firstElement = elements[0];

        for (int i = 1; i < elements.length - 1; i++) {
            elements[i - 1] = elements[i];
        }

        size--;

       return firstElement;
    }

    public boolean empty() {
        return size == 0;
    }

    public int getSize() {
        return size + 1;
    }
}
