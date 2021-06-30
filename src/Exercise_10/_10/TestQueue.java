package Exercise_10._10;

public class TestQueue {
    public static void main(String[] args) {
        Queue queue = new Queue();

        // Adds 20 numbers from 1 to 20 into the queue
        for (int i = 1; i <= 20; i++) {
            queue.enqueue(i);
        }

        // Removes the numbers from queue and displays them
        for (int i = 0; i < 20; i++) {
            System.out.print(queue.dequeue() + ((i != 19) ? ", " : ""));
        }
    }
}
