package Exercise_16._40;

public class Exercise_16_40 {
  public static class CountdownTimer {
    private int remaining;
    private boolean running;

    public CountdownTimer(int seconds) {
      if (seconds < 0) {
        throw new IllegalArgumentException("negative seconds");
      }
      remaining = seconds;
    }

    public void start() {
      running = remaining > 0;
    }

    public void pause() {
      running = false;
    }

    public void reset(int seconds) {
      if (seconds < 0) {
        throw new IllegalArgumentException("negative seconds");
      }
      remaining = seconds;
      running = false;
    }

    public void tick() {
      if (running && remaining > 0) {
        remaining--;
        if (remaining == 0) {
          running = false;
        }
      }
    }

    public int remaining() {
      return remaining;
    }

    public boolean isRunning() {
      return running;
    }

    public boolean isFinished() {
      return remaining == 0;
    }
  }

  public static void main(String[] args) {
    CountdownTimer timer = new CountdownTimer(5);
    timer.start();
    timer.tick();
    timer.tick();
    check(timer.remaining() == 3);
    timer.pause();
    timer.tick();
    check(timer.remaining() == 3);
    timer.start();
    timer.tick();
    timer.tick();
    timer.tick();
    check(timer.isFinished());
    check(!timer.isRunning());
    System.out.println("Exercise_16_40 OK");
  }

  private static void check(boolean condition) {
    if (!condition) {
      throw new IllegalStateException("check failed");
    }
  }
}
