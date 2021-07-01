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

    /** Construct a queue with the default capacity 8 */
    public Queue() {
        elements = new int[8];
    }

    /** Add a new integer into the queue */
    public void enqueue(int v) {
        if (size == elements.length) {
            int[] temp = new int[elements.length * 2];
            System.arraycopy(elements, 0, temp, 0, elements.length);
            elements = temp;
        }

        elements[size++] = v;
    }

    /** Remove and return an element from the queue */
    public int dequeue() {
        int firstElement = elements[0];

        for (int i = 1; i < elements.length - 1; i++) {
            elements[i - 1] = elements[i];
        }

        size--;

       return firstElement;
    }

    /** Test whether the queue is empty */
    public boolean empty() {
        return size == 0;
    }

    /** Return the number of elements in the queue */
    public int getSize() {
        return size;
    }
}
