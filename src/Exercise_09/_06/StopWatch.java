package Exercise_09._06;

/***********************************
 *            StopWatch            *
 ***********************************
 * -startTime: long                *
 * -endTime: long                  *
 *---------------------------------*
 * +start(): void                  *
 * +stop(): void                   *
 * +getElapsedTime(): long         *
 * +getStartTime(): long           *
 * +getEndTime(): long             *
 ***********************************/

public class StopWatch {
    private long startTime;
    private long endTime;

    public StopWatch() {
        this.startTime = System.currentTimeMillis();
    }

    // Resets the startTime to the current time
    public void start() {
        this.startTime = System.currentTimeMillis();
    }

    // Sets the endTime to the current time
    public void stop() {
        this.endTime = System.currentTimeMillis();
    }

    public long getElapsedTime() {
        return this.endTime - this.startTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }
}
