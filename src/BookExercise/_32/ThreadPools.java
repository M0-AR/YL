package BookExercise._32;

import java.util.concurrent.*;

public class ThreadPools {
    public static void main(String[] args) {

        // Test
        // Create a fixed thread pool with maximum three threads
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // Submit runnable tasks to the executor
        double startTime = System.nanoTime();
        executorService.execute( new PrintChar('a', 100) );
        executorService.execute( new PrintChar('b', 100) );
        executorService.execute( new PrintNum( 100) );

        // Shut down the executor
        executorService.shutdown();
        double endTime = System.nanoTime();
        System.out.println(endTime - startTime);

        // Test
//        double startTime0 = System.nanoTime();
//        System.out.println('a' + " " + 100);
//        System.out.println('b' + " " + 100);
//        System.out.println( 100);
//        double endTime0 = System.nanoTime();
//        System.out.println( endTime0 - startTime0 );
    }
}

class PrintChar implements Runnable {
    char ch;
    int n;

    public PrintChar(char ch, int n) {
        this.ch = ch;
        this.n = n;
    }

    @Override
    public void run() {
        System.out.println(ch + " " + n);
    }
}


class PrintNum implements Runnable {
    int n;

    public PrintNum( int n) {
        this.n = n;
    }

    @Override
    public void run() {
        System.out.println(n);
    }
}